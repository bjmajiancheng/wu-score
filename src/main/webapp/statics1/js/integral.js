$(function() {
  var integral = {
    init: function() {
      var ts = this;
      // 点击展开收起导航扩展项
      $("body").on("click", ".integral-more-item", function() {
        var ts = $(this);
        ts.toggleClass("arrow_up");
        ts.find(".integral-left-nav-item").slideToggle();
      });
      // 点击展开收起导航栏
      $("body").on("click", ".integral-single-item", function() {
        var ts = $(this);
        $(".integral-single-item").removeClass("active");
        ts.addClass("active");
      });
      // 点击材料上传
      $("body").on("click", ".td_upload", function() {
        var ts = $(this);
        ts.parents("td").find(".upload_input").click();
      });
      //
      // 阻止事件冒泡
      $("body").on("click", ".more-box", function(e) {
        e.stopPropagation();
      });
      $(".integral-paging-device-wrap2").pagination({
        pageCount: 50,
        coping: true,
        keepShowPN: true,
        homePage: "首页",
        prevContent: "上一页",
        nextContent: "下一页",
        endPage: "尾页"
      });
    },
    clickImg: function(obj) {
      $(obj)
          .parent()
          .find(".upload_input")
          .click();
    },
    //选择图片
    change: function(file) {
      //预览
      var pic = $(file)
          .parent()
          .find(".preview");
      //添加按钮
      var addImg = $(file)
          .parent()
          .find(".addImg");
      var title_click = $(file)
          .parent()
          .find(".title_click");
      var title_idcard = $(file)
          .parent()
          .find(".title_idcard");

      //删除按钮
      var deleteImg = $(file)
          .parent()
          .find(".del_idcard");
      var ext = file.value
          .substring(file.value.lastIndexOf(".") + 1)
          .toLowerCase();
      // gif在IE浏览器暂时无法显示
      if (ext != "png" && ext != "jpg" && ext != "jpeg") {
        if (ext != "") {
          alert("图片的格式必须为png或者jpg或者jpeg格式！");
        }
        return;
      }
      //判断IE版本
      var isIE = navigator.userAgent.match(/MSIE/) != null,
          isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;
      isIE10 = navigator.userAgent.match(/MSIE 10.0/) != null;
      if (isIE && !isIE10) {
        file.select();
        var reallocalpath = document.selection.createRange().text;
        // IE6浏览器设置img的src为本地路径可以直接显示图片
        if (isIE6) {
          pic.attr("src", reallocalpath);
        } else {
          // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
          pic.css(
              "filter",
              "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" +
              reallocalpath +
              '")'
          );
          // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
          pic.attr(
              "src",
              "data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="
          );
        }
        pic.show();
        addImg.hide();
        title_click.hide();
        title_idcard.show();
        deleteImg.show();
      } else {
        this.html5Reader(
            file,
            pic,
            addImg,
            title_click,
            title_idcard,
            deleteImg
        );
      }
    },
    //H5渲染
    html5Reader: function(
        file,
        pic,
        addImg,
        title_click,
        title_idcard,
        deleteImg
    ) {
      var file = file.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function(e) {
        pic.attr("src", this.result);
      };
      pic.show();
      addImg.hide();
      title_click.hide();
      title_idcard.show();
      deleteImg.show();
    },
    //删除
    deleteImg: function(obj) {
      $(obj)
          .parent()
          .find("input")
          .val("");
      $(obj)
          .parent()
          .find("img.preview")
          .attr("src", "");
      //IE9以下
      $(obj)
          .parent()
          .find("img.preview")
          .css("filter", "");
      $(obj).hide();
      $(obj)
          .parent()
          .find(".addImg")
          .show();
      $(obj)
          .parent()
          .find("img.preview")
          .hide();
    }
  };
  window.integral = integral;
  integral.init();
});
