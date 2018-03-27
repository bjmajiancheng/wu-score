$(function () {
  var integral = {
    init: function () {
      var ts = this;
      // 点击展开收起导航扩展项
      $("body").on("click",".integral-more-item", function () {
        var ts = $(this);
        ts.toggleClass("arrow_up");
        ts.find(".integral-left-nav-item").slideToggle();
      });
      // 阻止事件冒泡
      $("body").on("click",".more-box", function (e) {
        e.stopPropagation();
      });
      ts.tplleftnav();
    },
    tplleftnav:function(){
      // // regular 模板注册
      // var integralLfefNav =  Regular.extend({
      //   name:"integralLfefNav",
      //   template:document.getElementById("integralLfefNav")
      // })
      // var app = new integralLfefNav({
      //   data:{
      //     todo:"我要办件"
      //   },
      //   pop:function(){
      //     alert(this.data.todo)
      //   }
      // });
      // app.$inject('#leftnav');
    }
  }
  integral.init();
})
