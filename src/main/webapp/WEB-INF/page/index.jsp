<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common/common.jsp">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="renderer" content="webkit">
    <title>首页</title>
    <script src="support_ie/selectivizr-min.js"></script>
    <link rel="stylesheet" href="css/integral.css">
</head>

<body>
<!-- banner和title部分 -->
<div class="integral-banner-title">
    <!-- title -->
    <div class="integral-title">
        <!-- logo -->
        <div class="integral-logo">

        </div>
        <!-- 地点信息 -->
        <div class="integral-local"></div>
        <!-- 搜索框 -->
        <div class="integral-searchbar"></div>
        <!-- 登录信息 -->
        <div class="integral-register-info">
            <img src="img/user.png" alt="">
            <a href="" class="login_cl">登录</a>
            <a href="">注册</a>
        </div>
    </div>
</div>

<!-- 主体部分 -->
<div class="integral-main-box">

    <!-- 左部导航 -->
    <div class="integral-left-nav">
        <!-- 用户信息 -->
        <div class="integral-user-info">
            <div>
                <img src="img/user.png" alt="">
            </div>
            <p>您好！杜斌</p>
        </div>
        <!-- 导航nav 列表 -->
        <div class="integral-left-nav-list">
            <!-- 导航列表单个项 -->
            <!-- 我要办事 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item doc_bord" on-click={this.pop()}>我要办事</a>
            </div>
            <!-- 我的文件  添加active为选中-->
            <div class="integral-left-nav-item active">
                <a class="integral-single-item wenjian">我的文件</a>
            </div>
            <!-- 我的办件 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item banjian">我的办件</a>
            </div>
            <!-- 我要预约 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item yuyue">我要预约</a>
            </div>
            <!-- 我的互动 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item hudong">我的互动</a>
            </div>
            <!-- 收藏 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item shoucang">我要收藏</a>
            </div>
            <!-- 我的企业 -->
            <div class="integral-left-nav-item">
                <a class="integral-single-item qiye">我的企业</a>
            </div>
            <!-- integral-more-item  有拓展导航的时候加该样式 -->
            <div class="integral-left-nav-item integral-more-item">
                <a class="integral-single-item jifen">居住证积分</a>
                <!-- 导航列伸展项 more 更多 -->
                <div class="integral-left-nav-item more-box" style="display:none;">
                    <a class="integral-single-item">申请人列表</a>
                    <a class="integral-single-item">申请人信息填写</a>
                    <a class="integral-single-item">自助测评</a>
                    <a class="integral-single-item active">预约</a>
                </div>
            </div>
        </div>
    </div>
    <!-- </script> -->

    <!-- 中间二级导航 -->

    <!-- 内容展示区 -->
    <div class="integral-cont">

        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head">居住证积分</div>
            <div class="title-bar-after">申请人列表</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title">
            文件列表
        </div>
        <!-- table表格部分1 -->
        <table class="integral-table">
            <tr>
                <th> </th>
                <th>文件名</th>
                <th>发文日期</th>
            </tr>
            <tr>
                <td>天津市人民政府</td>
                <td>天津市人民政府关于印发天津市居住证管理办法的通知</td>
                <td>2016-03-30</td>
            </tr>
            <tr>
                <td>天津市人民政府</td>
                <td>天津市人民政府关于印发天津市居住证管理办法的通知</td>
                <td>2016-03-30</td>
            </tr>
            <tr>
                <td>天津市人民政府</td>
                <td>天津市人民政府关于印发天津市居住证管理办法的通知</td>
                <td>2016-03-30</td>
            </tr>
            <tr>
                <td>天津市人民政府</td>
                <td>天津市人民政府关于印发天津市居住证管理办法的通知</td>
                <td>2016-03-30</td>
            </tr>
        </table>
        <!-- 分页器 -->
        <div class="integral-paging-device-wrap">
            <div class="integral-paging-device">
                <div class="pageing-first base-color">首页</div>
                <div class="pageing-before base-color">上一页</div>
                <div class="pageing-dot">
                    <span>1</span>
                    <span class="base-color">2</span>
                    <span>3</span>
                    <span>4</span>
                    <span>•••</span>
                </div>
                <div class="pageing-next base-color">下一页</div>
                <div class="pageing-end base-color">尾页</div>
                <div class="pageing-dur">当前第 2 页 / 共 5 页</div>
            </div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title">
            申请人列表
        </div>
        <!-- 表格搜索框和按钮部分 -->
        <div class="interal-tab-title-input">
            <div class="interal-input-title">快速查询</div>
            <input type="text">
            <div class="interal-input-btn">查询</div>
            <div class="interal-input-btn">返回</div>
            <div class="interal-input-btn">添加申请人</div>
            <div class="interal-input-btn">批量预约</div>
        </div>
        <!-- table2表格部分 -->
        <table class="integral-table">
            <tr>
                <th>选择</th>
                <th>序列</th>
                <th>身份证号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>拟定户地</th>
                <th>预约日期</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td>1</td>
                <td>410222199909008789</td>
                <td>李金龙</td>
                <td>男</td>
                <td>河南开封</td>
                <td>2016-08-02</td>
                <td>
                    <span class="integral-table-btn integral-table-btn1">编辑</span>
                    <span class="integral-table-btn integral-table-btn2">测评</span>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td>1</td>
                <td>410222199909008789</td>
                <td>李金龙</td>
                <td>男</td>
                <td>河南开封</td>
                <td>2016-08-02</td>
                <td>
                    <span class="integral-table-btn integral-table-btn1">编辑</span>
                    <span class="integral-table-btn integral-table-btn2">预约申请</span>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td>1</td>
                <td>410222199909008789</td>
                <td>李金龙</td>
                <td>男</td>
                <td>河南开封</td>
                <td>2016-08-02</td>
                <td>
                    <span class="integral-table-btn integral-table-btn1">编辑</span>
                    <span class="integral-table-btn integral-table-btn2">预约凭证</span>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td>1</td>
                <td>410222199909008789</td>
                <td>李金龙</td>
                <td>男</td>
                <td>河南开封</td>
                <td>2016-08-02</td>
                <td>
                    <span class="integral-table-btn integral-table-btn1">编辑</span>
                    <span class="integral-table-btn integral-table-btn2">预约</span>
                </td>
            </tr>
        </table>
        <!-- 分页器 -->
        <div class="integral-paging-device-wrap">
            <div class="integral-paging-device">
                <div class="pageing-first base-color">首页</div>
                <div class="pageing-before base-color">上一页</div>
                <div class="pageing-dot">
                    <span>1</span>
                    <span class="base-color">2</span>
                    <span>3</span>
                    <span>4</span>
                    <span>•••</span>
                </div>
                <div class="pageing-next base-color">下一页</div>
                <div class="pageing-end base-color">尾页</div>
                <div class="pageing-dur">当前第 2 页 / 共 5 页</div>
            </div>
        </div>





        <!-- 第二大部分 -->
        <!-- 表单部分 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head">居住证积分</div>
            <div class="title-bar-after">申请人信息填写</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title">
            申请人信息填写
        </div>
        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-sfz">身份证信息</div>
        </div>
        <!-- 表单主体部分  用table实现布局 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>身份证号：</td>
                    <td>
                        <input class="integral-input w300" type="text">
                        <div class="interal-main-btn m15">身份证上传</div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="integral-idcard-img">
                            <img src="img/IDcard1.png" alt="">
                            <div>身份证正面</div>
                        </div>
                        <div class="integral-idcard-img">
                            <img src="img/IDcard2.png" alt="">
                            <div>身份证正面</div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>出生日期：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
            </table>
        </div>
        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-hjqy">户籍迁移信息</div>
        </div>
        <!-- 表单主体部分  用table实现布局 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>迁出地区：</td>
                    <td>
                        <select name="province" id="province">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        &nbsp;&nbsp;省
                        <select class="ml40" name="city" id="city">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        &nbsp;&nbsp;市
                        <select class="ml40" name="area" id="area">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        &nbsp;&nbsp;区
                    </td>
                </tr>
                <tr>
                    <td>迁出地地址：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>现户籍登记机关：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>现户口性质：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        <span class="integral-td-title ml50">落户性质：</span>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>迁入户籍登记机关：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        <select class="ml50" name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>迁入地址详细地址：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>证明收件人：</td>
                    <td>
                        <input class="integral-input w200" placeholder="户口准予迁入证明快递收件人" type="text" name="">
                        <span class="integral-td-title ml50">电话：</span>
                        <input class="integral-input w200" type="text" name="">
                    </td>
                </tr>
                <tr>
                    <td>拟落户地区：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        <span class="integral-td-title ml50">婚姻状况：</span>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>有无子女</td>
                    <td>
                        <input class="input_radio" type="radio" name="children" value="1">&nbsp;&nbsp;&nbsp;有
                        <input class="input_radio ml30" type="radio" name="children" value="0">&nbsp;&nbsp;&nbsp;无
                        <span class="integral-td-title ml50">子女数量：</span>
                        <input class="integral-input w200" type="text" placeholder="无子女数量可不填" name="" id="">
                    </td>
                </tr>
            </table>
            <!-- 上边选中子女 为有 校验下方子女表 -->
            <div id="children-table">
                <!-- table表格部分1 子女关系表 -->
                <table class="integral-table border-add">
                    <tr>
                        <th width="50"> </th>
                        <th>与本人关系</th>
                        <th>姓名</th>
                        <th width="281">身份证号</th>
                        <th width="100">是否随迁</th>
                        <th>文化程度</th>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="" id="">
                        </td>
                        <td>
                            <input class="table_add_input" type="text">
                        </td>
                        <td>
                            <input class="table_add_input" type="text">
                        </td>
                        <td>
                            <input class="table_add_input" type="text">
                        </td>
                        <td>
                            <input type="radio" name="yes" id="">&nbsp;&nbsp;是
                            <input class="m15" type="radio" name="yes" id="">&nbsp;&nbsp;否</td>
                        <td>
                            <input class="table_add_input" type="text">
                        </td>
                    </tr>
                </table>
                <!-- 子女关系表 控制按钮 -->
                <div class="integral-paging-device-wrap">
                    <!--  -->
                    <div class="interal-main-btn m15 fr mt10">增加</div>
                    <div class="interal-main-btn m15 fr mt10">删除</div>
                </div>
            </div>
        </div>
        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-other">其他信息</div>
        </div>
        <!-- 表单主体部分  用table实现布局 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>申请人类型：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;企业员工
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;投资者
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;个体商户
                    </td>
                </tr>
                <tr>
                    <td>政治面貌：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                        <span class="integral-td-title ml50">军人立功：</span>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>专业：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>现单位名称：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>现单位地址：</td>
                    <td>
                        <input class="integral-input w450" type="text">
                    </td>
                </tr>
                <tr>
                    <td>单位电话：</td>
                    <td>
                        <input class="integral-input w200" placeholder="户口准予迁入证明快递收件人" type="text" name="">
                        <span class="integral-td-title ml50">本人电话：</span>
                        <input class="integral-input w200" type="text" name="">
                    </td>
                </tr>
                <tr>
                    <td>居住证申领日期：</td>
                    <td>
                        <input class="integral-input w200" type="text">
                    </td>
                </tr>
                <tr>
                    <td>社保缴纳：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;是
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;否
                        <span class="grey-color">是否缴纳社保</span>
                    </td>
                </tr>
                <tr>
                    <td>公积金：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;是
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;否
                        <span class="grey-color">是否参加住房公积金</span>
                    </td>
                </tr>
                <tr>
                    <td>缴纳情况：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;是
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;否
                        <span class="grey-color">近3年在本市依法缴纳个人所得税累计10万元及以上</span>
                    </td>
                </tr>
                <tr>
                    <td>拘留记录：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;是
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;否
                        <span class="grey-color">积分期间有无行政拘留记录</span>
                    </td>
                </tr>
                <tr>
                    <td>获刑记录：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;是
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;否
                        <span class="grey-color">积分期间有无犯罪获刑记录</span>
                    </td>
                </tr>
                <tr>
                    <td>奖项荣誉称号：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>

        <!-- 示例title2 二级标题 -->
        <div class="integral-grey-title-bar title-bar2">
            <div class="title-bar-head title-bar-zyzg">职业资格证书</div>
        </div>
        <!-- 表单主体部分  用table实现布局 -->
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>职业资格项：</td>
                    <td>
                        <input class="input_radio" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;无
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;具有职称
                        <input class="input_radio ml30" type="radio" name="baby" id="">&nbsp;&nbsp;&nbsp;具有职业资格
                    </td>
                </tr>
                <tr>
                    <td>职称级别：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>职位：</td>
                    <td>
                        <input class="integral-input w200" type="text" name="">
                        <span class="integral-td-title ml50">发证机关：</span>
                        <input class="integral-input w200" type="text" name="">
                    </td>
                </tr>
                <tr>
                    <td>发证日期：</td>
                    <td>
                        <input class="integral-input w200" type="text" name="">
                        <span class="integral-td-title ml50">证书编号：</span>
                        <input class="integral-input w200" type="text" name="">
                    </td>
                </tr>
                <tr>
                    <td>职业资格级别：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>工种：</td>
                    <td>
                        <select name="" id="">
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>

            </table>
        </div>

        <!-- 验证码和底部按钮 -->
        <table class="check-table">
            <tr>
                <td>
                    <span class="color-red">*</span>验证码：</td>
                <td>
                    <input class="integral-input w200" type="text">
                    <div class="checkimg">
                        <img src="img/checkcode.png" alt="">
                    </div>
                    <span class="click_me">看不清点我</span>
                </td>
            </tr>
        </table>
        <!-- 保存按钮 -->
        <div class="integral-bottom-btn-group">
            <div class="interal-main-btn big-btn m15">保存并继续录入</div>
            <div class="interal-main-btn big-btn ml50">保存</div>
            <div class="interal-main-btn big-btn ml50">返回</div>
        </div>


        <!-- 密码修改 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head title-bar-mmxg">用户密码修改</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title border-bottom">
            用户密码修改
        </div>
        <!-- 修改密码表格 -->
        <table class="login-table">
            <tr>
                <td>
                    <span class="color-red">*</span>原始密码：</td>
                <td>
                    <input class="integral-input w450 ml50" placeholder="请提供您原有密码" type="password">
                </td>
            </tr>
            <tr>
                <td>
                    <span class="color-red">*</span>新&nbsp; 密 &nbsp;码：</td>
                <td>
                    <input class="integral-input w450 ml50" placeholder="请输入您的密码" type="password">
                </td>
            </tr>
            <tr>
                <td>
                    <span class="color-red">*</span>确认密码：</td>
                <td>
                    <input class="integral-input w450 ml50" placeholder="请输入您的密码" type="password">
                </td>
            </tr>
        </table>
        <!-- 登录按钮 -->
        <div class="integral-bottom-btn-group">
            <div class="interal-main-btn big-btn m15">确认修改</div>
            <div class="interal-main-btn big-btn interal-main-btn2 ml50">返回</div>
        </div>





        <!-- 网上注册 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head title-bar-yrdwxg">用人单位修改</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title border-bottom">
            用人单位修改
        </div>
        <!-- 用人单位修改表格 -->
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
                        <input class="integral-input w200" type="text">
                        <div class="checkimg">
                            <img src="img/checkcode.png" alt="">
                        </div>
                        <span class="click_me">看不清点我</span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="color-red">*注：标有红色 * 为必填项</div>
                    </td>
                </tr>
            </table>
        </div>

        <!-- 保存按钮 -->
        <div class="integral-bottom-btn-group mt150">
            <div class="interal-main-btn big-btn m15">保存</div>
            <div class="interal-main-btn big-btn interal-main-btn2 ml50">返回</div>
        </div>



        <!-- 预约信息 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head">居住证积分</div>
            <div class="title-bar-after">预约</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title border-bottom">
            预约信息
        </div>
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table" style="margin-left:180px;">
                <tr>
                    <td>受理地点选择：</td>
                    <td>
                        <select name="" id="" disabled>
                            <option>选项1</option>
                            <option>选项2</option>
                            <option>选项3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>单位名称：</td>
                    <td>北京果敢时代科技有限公司</td>
                </tr>
                <tr>
                    <td>单位类型：</td>
                    <td>
                        <input class="input_radio" type="radio" name="danwei" checked="checked">&nbsp;&nbsp;&nbsp;企事业单位
                    </td>
                </tr>
                <tr>
                    <td>代码类型：</td>
                    <td>
                        <input class="input_radio" type="radio" name="daima" checked="checked">&nbsp;&nbsp;&nbsp;统一社会信用代码
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>123456786543234567</td>
                </tr>
                <tr>
                    <td>单位电话：</td>
                    <td>123456765432</td>
                </tr>
                <tr>
                    <td>经办人：</td>
                    <td>关于</td>
                </tr>
                <tr>
                    <td>联系手机：</td>
                    <td>12378219238</td>
                </tr>
                <tr>
                    <td>联系地址：</td>
                    <td>北京果敢时代科技有限公司</td>
                </tr>
            </table>
            <!-- 验证码和底部按钮 -->
            <table class="check-table">
                <tr>
                    <td>
                        <span class="color-red">*</span>验证码：</td>
                    <td>
                        <input class="integral-input w200" type="text">
                        <div class="checkimg">
                            <img src="img/checkcode.png" alt="">
                        </div>
                        <span class="click_me">看不清点我</span>
                    </td>
                </tr>
            </table>
            <!-- 保存按钮 -->
            <div class="integral-bottom-btn-group">
                <div class="interal-main-btn big-btn m15">下一步</div>
                <div class="interal-main-btn big-btn ml50">关闭</div>
            </div>
        </div>




        <!-- 自助测评信息 -->
        <!-- 示例title 面包屑导航 -->
        <div class="integral-grey-title-bar">
            <div class="title-bar-head">居住证积分</div>
            <div class="title-bar-after">自助测评</div>
        </div>
        <!-- 内容描述title -->
        <div class="integral-desc-title border-bottom">
            自助测评信息
        </div>
        <div class="integral-cont-table-wrap">
            <table class="integral-cont-table">
                <tr>
                    <td>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
                    <td>30周岁&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分20分</td>
                </tr>
                <tr>
                    <td>受教育程度：</td>
                    <td>本科及以上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分40分</td>
                </tr>
                <tr>
                    <td class="nowrap">专业技术职业技能：</td>
                    <td>高级技师&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分50分</td>
                </tr>
                <tr>
                    <td class="lineh21">社保公积金：</td>
                    <td class="lineh21">
                        养老保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">医疗保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">失业保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">工伤保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">生育保险&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">公&nbsp;&nbsp;积&nbsp;&nbsp;金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;20个月</td>
                </tr>
                <tr>
                    <td>住房：</td>
                    <td>
                        <input class="input_radio" type="radio" name="zhufang" id="">&nbsp;&nbsp;&nbsp;无（不得分）
                        <input class="input_radio ml30" type="radio" name="zhufang" id="">&nbsp;&nbsp;&nbsp;天津市有本人产权住房（包括与配偶、父母、子女共有得40分）
                    </td>
                </tr>
                <tr>
                    <td>职业工种：</td>
                    <td>非常紧缺职业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分30分</td>
                </tr>
                <tr>
                    <td>落户地区：</td>
                    <td>申请落户东丽区、西青区、津南区、北辰区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分5分</td>
                </tr>
                <tr>
                    <td>纳税：</td>
                    <td>近三年在本市依法缴纳个人所得税累计10万元及以上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分20分</td>
                </tr>
                <tr>
                    <td>婚姻状况：</td>
                    <td>已婚，且夫妻双方均在本市就业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分10分</td>
                </tr>
                <tr>
                    <td class="nowrap">奖项及荣誉称号：</td>
                    <td>
                        <input class="input_radio" type="checkbox" name="zhufang" id="">&nbsp;&nbsp;&nbsp;获得党中央、国务院授予的奖项和荣誉称号（40分）</td>
                </tr>
                <tr>
                    <td></td>
                    <td class="lineh21">
                        <input class="input_radio" type="checkbox" name="zhufang" id="">&nbsp;&nbsp;&nbsp;获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的劳动模范或先进工作者荣誉称号，并享受省部级劳动模范或先进工作者待遇（30分）</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="input_radio" type="checkbox" name="zhufang" id="">&nbsp;&nbsp;&nbsp;获得省（自治区、直辖市）党委、政府或中央和国家机关部委等授予的奖项和荣誉称号（20分）</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="input_radio" type="checkbox" name="zhufang" id="">&nbsp;&nbsp;&nbsp;拥有成效的中国发明专利（20分）</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="input_radio" type="checkbox" name="zhufang" id="">&nbsp;&nbsp;&nbsp;无</td>
                </tr>
                <tr>
                    <td>工作年限：</td>
                    <td>在津具有合法稳定职业，累计6年及以上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分10分</td>
                </tr>
                <tr>
                    <td>退役军人：</td>
                    <td>一等功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分20分</td>
                </tr>
            </table>
            <!-- 验证码和底部按钮 -->
            <table class="check-table">
                <tr>
                    <td>
                        <span class="color-red">*</span>验证码：</td>
                    <td>
                        <input class="integral-input w200" type="text">
                        <div class="checkimg">
                            <img src="img/checkcode.png" alt="">
                        </div>
                        <span class="click_me">看不清点我</span>
                    </td>
                </tr>
            </table>
            <!-- 保存按钮 -->
            <div class="integral-bottom-btn-group">
                <div class="interal-main-btn big-btn m15">提交</div>
                <div class="interal-main-btn big-btn ml50">关闭</div>
            </div>
        </div>
    </div>
</div>

<!-- 底部栏 footer -->
<div class="integral-footer">
    <div class="integral-footer-center">
        <div class="integral-footer-mark">
            <div class="footer-mark1">
                <div>
                    <img src="img/hot_phone.png" alt="">
                </div>
                <div>
                    <p>热线电话</p>
                    <p class="footer-phone">010-4567895678</p>
                </div>
            </div>
            <div class="footer-mark1">
                <div>
                    <img src="img/email.png" alt="">
                </div>
                <div>
                    <p class="mt15">政务信箱</p>
                </div>
            </div>
            <div class="footer-mark1">
                <div>
                    <img src="img/timg.png" alt="">
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
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/regular.min.js"></script>
<script src="support_ie/placeholders.jquery.js"></script>
<script src="js/integral.js"></script>

</html>

