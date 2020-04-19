<template>
  <div class="editor-container">
    <textarea class="ckeditor-textarea" :id="editorid"></textarea>
  </div>
</template>

<script>
  import {getToken} from "@/utils/auth";

  export default {
    name: "ckeditor",
    data() {
      return {
        inited: false,
        editorid: null,
        content: this.value
      }
    },
    props: {
      value: {type: String, default: ''}
    },
    watch: {
      value(){

      }
    },
    created() {

    },
    mounted() {
      this.init();
    },
    updated() {
      console.info('editor updated')

    },
    beforeDestroy() {

    },
    methods: {
      init: function () {
        this.editorid = 'editor-' + (new Date()).getTime()
        //CKEDITOR.replace(this.editorid)
        /*CKEDITOR.replace(document.querySelector('#'+this.editorid)).then(editor => {
          console.info(editor)
        }).catch(err => {
          console.error(err)
        })*/
        //if (CKEDITOR.instances[this.editorid]) {
        //  console.info('remove editor')
        //  CKEDITOR.remove(CKEDITOR.instances[this.editorid]);
        //}
        CKEDITOR.replace(this.editorid);
      }

    }
  }
</script>

<style scoped>

</style>
