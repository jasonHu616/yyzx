INSERT INTO sys_config_group (id,title,ordernum) VALUES (1,'后台配置',0);
INSERT INTO sys_config (keycode,typecode,title,groupid,`options`,remark,value,ordernum) VALUES ('allowopenadmin','switch','是否开启后台',1,'0:关闭;1:开启','若后台关闭则只有系统管理员可以登录','0',0);