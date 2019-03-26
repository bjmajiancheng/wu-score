!function($){
  /**
   * Description: jquery飘窗插件，可自适应浏览器宽高的变化
   * Author: ddqre12345
   * CreateTime: 2017.5.3
   * param: args={startL:default, startT:default, angle:-Math.PI/4, speed: 125}
   * 参数说名: startL飘窗初始时距离左边的距离, startT飘窗初始化距离顶部的距离, angle飘窗初始运动方向, speed运动速度(px/s)
   */
  $.fn.autoMove = function(args){
    //先定义一些工具函数判断边距
    function isTop(pos, w_w, w_h, d_w, d_h){//飘窗到达上边距
      return (pos.top<=0) ? true : false;
    }
    function isBottom(pos, w_w, w_h, d_w, d_h){//飘窗到达下边距
      return (pos.top>=(w_h-d_h)) ? true : false;
    }
    function isLeft(pos, w_w, w_h, d_w, d_h){//飘窗到达左边距
      return (pos.left<=0) ? true : false;
    }
    function isRight(pos, w_w, w_h, d_w, d_h){//飘窗到达右边距
      return (pos.left>=(w_w-d_w)) ? true : false;
    }
    return this.each(function(){
      var w_w = parseInt($(window).width()),
        w_h = parseInt($(window).height()),
        d_w = parseInt($(this).width()),
        d_h = parseInt($(this).height()),
        d_l = (w_w-d_w)/2,
        d_t = (w_h-d_h)/2,
        max_l = w_w - d_w;
      max_t = w_h - d_h;
      //位置及参数的初始化
      var setobj = $.extend({startL:d_l, startT:d_t, angle:Math.PI/4, speed:100}, args);
      $(this).css({position: 'absolute', left: setobj['startL']+'px', top: setobj['startT']+'px'});
      var position = {left: setobj['startL'], top: setobj['startT']};//飘窗位置对象
      var that = $(this);
      var angle= setobj.angle;
      var time = 10;//控制飘窗运动效果，值越小越细腻
      var step = setobj.speed * (time/1000);//计算运动步长
      var decoration = {x:step*Math.cos(angle), y:step*Math.sin(angle)};//计算二维上的运动增量
      var mvtid;
      $(window).on('resize', function(){//窗口大小变动时重新设置运动相关参数
          w_w = parseInt($(window).width());
          w_h = parseInt($(window).height());
          max_l = w_w - d_w;
          max_t = w_h - d_h;
      });
      function move(){
        position.left += decoration.x;
        position.top  += decoration.y;
        if(isLeft(position, w_w, w_h, d_w, d_h)||isRight(position, w_w, w_h, d_w, d_h)){
          decoration.x = -1*decoration.x;
        }
        if(isRight(position, w_w, w_h, d_w, d_h)){
          position.left = max_l;
        }
        if(isTop(position, w_w, w_h, d_w, d_h)||isBottom(position, w_w, w_h, d_w, d_h)){
          decoration.y = -1*decoration.y;
        }
        if(isBottom(position, w_w, w_h, d_w, d_h)){
          position.top = max_t;
        }
        //that.css({left:position.left, top:position.top});
        that.animate({left:position.left, top:position.top}, time);//改用jquery动画函数使其更加平滑
        mvtid = setTimeout(move, time);//递归调用，使飘窗连续运动
      }
      move();//触发动作
      that.on('mouseenter', function(){ clearTimeout(mvtid) });//添加鼠标事件
      that.on('mouseleave', function(){ move() });
    });
  }; //end plugin definition
}(jQuery);