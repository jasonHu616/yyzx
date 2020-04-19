exports.install = function (Vue, options) {
  /**
   * 字节转换
   */
  Vue.prototype.bytesToSize = function (bytes){
    if (bytes === 0) return '0 B';

    let k = 1024;

    sizes = ['B','KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

    let i = Math.floor(Math.log(bytes) / Math.log(k));

    return (bytes / Math.pow(k, i)).toFixed(2) + ' ' + sizes[i];

  };

};
