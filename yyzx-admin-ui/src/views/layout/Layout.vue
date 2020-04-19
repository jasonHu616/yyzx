<template>
  <div class="app-wrapper" :class="classObj">
    <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside"></div>
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navbar class="top-navbar"></navbar>
      <tags-view class="top-tags"></tags-view>
      <app-main class="main-box"></app-main>
    </div>
    <audio id='audioPlay' src='../../../static/audio/tip.wav' hidden='true'></audio>
    <audio id='audioPlayXP' src='../../../static/audio/tipxp.wav' hidden='true'></audio>
  </div>
</template>

<script>
    import {Navbar, Sidebar, AppMain, TagsView} from './components'
    import ResizeMixin from './mixin/ResizeHandler'
    import {getToken} from "@/utils/auth";

    export default {
        name: 'layout',
        data() {
            return {
                synth: null,
                msg: null,
                wsUrl: process.env.SOCKET_URL,
                lockReconnect: false,//是否真正建立连接
                timeout: 28 * 1000,//30秒一次心跳
                timeoutObj: null,//心跳心跳倒计时
                serverTimeoutObj: null,//心跳倒计时
                isFirst: true
            }
        },
        components: {
            Navbar,
            Sidebar,
            AppMain,
            TagsView
        },
        mixins: [ResizeMixin],
        computed: {
            sidebar() {
                return this.$store.state.app.sidebar
            },
            device() {
                return this.$store.state.app.device
            },
            classObj() {
                return {
                    hideSidebar: !this.sidebar.opened,
                    withoutAnimation: this.sidebar.withoutAnimation,
                    mobile: this.device === 'mobile'
                }
            }
        },
        created() {
            this.synth = window.speechSynthesis;
            this.msg = new SpeechSynthesisUtterance();
            this.initWebSocket();
        },
        destroyed() { // 离开页面生命周期函数
            this.websocketclose();
            clearTimeout(this.timeoutObj);
            clearTimeout(this.serverTimeoutObj);
        },
        methods: {
            playSound(message) {

                var node = null;
                let os=this.detectOS();
                if (os!="WinXP")
                {
                    node=document.getElementById("audioPlay");
                }else{
                    node=document.getElementById("audioPlayXP");
                }

                if (node != null) {
                    node.play();
                    if (os!="WinXP") {
                        this.$nextTick(() => {
                            setTimeout(() => {
                                message = message.replace("待", "带");
                                this.handleSpeak(message);
                            }, 1500)
                        });
                    }
                }

            },
            handleSpeak(e) {
                this.msg.text = e;
                this.msg.lang = 'zh-CN';
                this.msg.volume = '1';
                this.msg.rate = 1.5;
                this.msg.pitch = 5;
                this.synth.speak(this.msg);
            },
            /* * 语音停止  */
            handleStop(e) {
                this.msg.text = e;
                this.msg.lang = 'zh-CN';
                this.synth.cancel(this.msg);
            },
            initWebSocket: function () {
                // WebSocket与普通的请求所用协议有所不同，ws等同于http，wss等同于https
                this.websock = new WebSocket(this.wsUrl + "/catchMedicine/" + getToken());
                this.websock.onopen = this.websocketonopen;
                this.websock.onerror = this.websocketonerror;
                this.websock.onmessage = this.websocketonmessage;
                this.websock.onclose = this.websocketclose;
            },
            reconnect() {//重新连接
                var that = this;
                if (that.lockReconnect) {
                    return;
                }
                ;
                that.lockReconnect = true;
                //没连接上会一直重连，设置延迟避免请求过多
                that.timeoutnum && clearTimeout(that.timeoutnum);
                that.timeoutnum = setTimeout(function () {
                    //新连接
                    that.initWebSocket();
                    that.lockReconnect = false;
                }, 1000);
            },
            reset() {//重置心跳
                var that = this;
                //清除时间
                clearTimeout(that.timeoutObj);
                clearTimeout(that.serverTimeoutObj);
                //重启心跳
                that.start();
            },
            start() {//开启心跳
                var self = this;
                self.timeoutObj && clearTimeout(self.timeoutObj);
                self.serverTimeoutObj && clearTimeout(self.serverTimeoutObj);
                self.timeoutObj = setTimeout(function () {
                    //这里发送一个心跳，后端收到后，返回一个心跳消息，
                    if (self.websock.readyState == 1) {//如果连接正常
                        self.websock.send("heartCheck");
                    } else {//否则重连
                        self.reconnect();
                    }
                    self.serverTimeoutObj = setTimeout(function () {
                        //超时关闭
                        self.websock.close();
                    }, self.timeout);

                }, self.timeout)
            },
            websocketonopen: function () {
                console.log("WebSocket连接成功");
                //开启心跳
                this.start();
            },
            websocketonerror: function (e) {
                console.log("WebSocket连接发生错误");
                //重连
                this.reconnect();
            },
            websocketonmessage: function (e) {
                //收到服务器信息，心跳重置
                this.reset();
                var da = JSON.parse(e.data);
                const h = this.$createElement;
                if (da.success) {
                    if (da.data == "catchmedicine-0") {
                        if (!this.isFirst) {
                            return;
                        }
                        this.isFirst = false;
                    }
                    this.$notify({
                        title: '审核提醒',
                        message: h('i', {style: 'color: #f4516c'}, da.message),
                        duration: 10 * 1000,
                        type: 'info',
                        position: 'bottom-right'

                    });
                    this.playSound(da.message);
                }
            },
            websocketclose: function (e) {
                console.log("connection closed (" + e.code + ")", e);
                //重连
                this.reconnect();
            },
            websocketsend(msg) {//向服务器发送信息
                //数据发送
                this.websock.send(msg);
            },
            handleClickOutside() {
                this.$store.dispatch('closeSideBar', {withoutAnimation: false})
            },
            detectOS() {
                var sUserAgent = navigator.userAgent;
                var isWin = (navigator.platform == "Win32") || (navigator.platform == "Windows");
                var isMac = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
                if (isMac)
                    return "Mac";
                var isUnix = (navigator.platform == "X11") && !isWin && !isMac;
                if (isUnix) return "Unix";
                var isLinux = (String(navigator.platform).indexOf("Linux") > -1);
                if (isLinux) return "Linux";
                if (isWin) {
                    var isWin2K = sUserAgent.indexOf("Windows NT 5.0") > -1 || sUserAgent.indexOf("Windows 2000") > -1;
                    if (isWin2K) return "Win2000";
                    var isWinXP = sUserAgent.indexOf("Windows NT 5.1") > -1 || sUserAgent.indexOf("Windows XP") > -1;
                    if (isWinXP) return "WinXP";
                    var isWin2003 = sUserAgent.indexOf("Windows NT 5.2") > -1 || sUserAgent.indexOf("Windows 2003") > -1;
                    if (isWin2003) return "Win2003";
                    var isWin2003 = sUserAgent.indexOf("Windows NT 6.0") > -1 || sUserAgent.indexOf("Windows Vista") > -1;
                    if (isWin2003) return "WinVista";
                    var isWin2003 = sUserAgent.indexOf("Windows NT 6.1") > -1 || sUserAgent.indexOf("Windows 7") > -1;
                    if (isWin2003) return "Win7";
                }
                return "other";
            }
        }
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixin.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }
</style>
