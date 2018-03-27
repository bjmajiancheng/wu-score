<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="renderer" content="webkit">
  <title>登录</title>
  <script src="statics/support_ie/selectivizr-min.js"></script>
  <link rel="stylesheet" href="statics/css/integral.css">
</head>

<body>
  <!-- banner和title部分 -->
  <div class="integral-banner-title">
    <!-- title -->
    <div class="integral-title">
      <!-- logo -->
      <div class="integral-logo">

      </div>
      <!-- 登录信息 -->
      <div class="integral-register-info">
        <img src="statics/img/user.png" alt="">
        <a href="" class="login_cl">登录</a>
        <a href="">注册</a>
      </div>
    </div>
  </div>

  <!-- 主体部分 -->
  <div class="integral-main-box">
    <!-- 内容展示区  登录和注册等不带导航模块需要加 样式 ”cont-center“-->
    <div class="integral-cont ml105">
      <!-- 登录部分 -->
      <!-- 示例title 面包屑导航 -->
      <div class="integral-grey-title-bar">
        <div class="title-bar-head title-bar-login_icon">登录</div>
      </div>
      <!-- 内容描述title -->
      <div class="integral-desc-title border-bottom">
        登录
      </div>
      <!-- 登录表格 -->
      <table class="login-table">
        <tr>
          <td>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</td>
          <td>
            <input class="integral-input w450 ml50" placeholder="请提供您的手机号或邮箱" type="text">
          </td>
        </tr>
        <tr>
          <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
          <td>
            <input class="integral-input w450 ml50" placeholder="请输入您的密码" type="password">
          </td>
        </tr>
      </table>
      <!-- 登录按钮 -->
      <div class="integral-bottom-btn-group">
        <div class="interal-main-btn big-btn m15">登录</div>
        <div class="interal-main-btn big-btn ml50 interal-main-btn2">返回</div>
      </div>
    </div>
  </div>

  <!-- 底部栏 footer -->
  <div class="integral-footer">
    <div class="integral-footer-center">
      <div class="integral-footer-mark">
        <div class="footer-mark1">
          <div>
            <img src="statics/img/hot_phone.png" alt="">
          </div>
          <div>
            <p>热线电话</p>
            <p class="footer-phone">010-4567895678</p>
          </div>
        </div>
        <div class="footer-mark1">
          <div>
            <img src="statics/img/email.png" alt="">
          </div>
          <div>
            <p class="mt15">政务信箱</p>
          </div>
        </div>
        <div class="footer-mark1">
          <div>
            <img src="statics/img/timg.png" alt="">
          </div>
          <div>
            <p class="mt15">政务服务APP</p>
          </div>
        </div>
      </div>
      <p>主办：天津市人民政府行政审批管理办公室 版权所有 津ICP备05004718号</p>
      <p>技术支持：太极计算机股份有限公司</p>
    </div>
  </div>
</body>
<script src="statics/js/jquery-1.11.3.min.js"></script>
<script src="statics/js/regular.min.js"></script>
<script src="statics/support_ie/placeholders.jquery.js"></script>
<script src="statics/js/integral.js"></script>

</html>
