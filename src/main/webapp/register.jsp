<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="renderer" content="webkit">
    <title>用户注册</title>
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
        <!-- 网上注册 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head title-bar-yhzc">用户注册</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title">
            网上注册
        </div>
        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-wszc">网上注册信息</div>
        </div>
        <!-- 用户注册表格 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>
                        <span class="color-red">*</span>用&nbsp; 户 &nbsp;名：</td>
                    <td>
                        <input class="integral-input w450 ml50" placeholder="请使用英文或数字，6–20位，请注意区分大小写" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                    <td>
                        <input class="integral-input w450 ml50" placeholder="请使用5--12位" type="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>确认密码：</td>
                    <td>
                        <input class="integral-input w450 ml50" placeholder="请再输入一遍您上面填写的密码" type="password">
                    </td>
                </tr>
            </table>
        </div>
        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-dwjb">事业单位基本信息</div>
        </div>
        <!-- 事业单位基本信息表格 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>
                        <span class="color-red">*</span>单位名称：</td>
                    <td>
                        <input class="integral-input w450" placeholder="单位名称必须与营业执照一致" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>单位类型：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;企事业单位
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;个体商户
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>统一社会信用代码：</td>
                    <td>
                        <input class="integral-input w450" placeholder="如您没有统一社会信用代码，请输入机构代码" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>单位联系电话：</td>
                    <td>
                        <input class="integral-input w450" placeholder="" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>经办人：</td>
                    <td>
                        <input class="integral-input w450" placeholder="单位指定办理专员" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>联系手机：</td>
                    <td>
                        <input class="integral-input w450" placeholder="请填写真实可用的手机号码，注册成功后，系统会通过短信方式发送登录确认码" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        联系地址：</td>
                    <td>
                        <input class="integral-input w450" placeholder="" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span class="color-red">*</span>验证码：</td>
                    <td>
                        <input class="integral-input w200" type="text"> <div class="checkimg"><img src="img/checkcode.png" alt=""></div><span class="click_me">看不清点我</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="color-red">*注：标有红色 * 为必填项</div>
                    </td>
                </tr>
            </table>
        </div>

        <!-- 登录按钮 -->
        <div class="integral-bottom-btn-group mt150">
            <div class="interal-main-btn big-btn m15">注册</div>
            <div class="interal-main-btn big-btn interal-main-btn2 ml50">返回</div>
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
