
//表单序列化
function serializeForm(form) {
    var result = {};// 要传递给后台的对象数据
    var array = form.serializeArray();// 序列化表单内容
    $(array).each(function() {
        result[this.name] = this.value.trim();
    });

    return result;
}

//定位当前页面
function currPage(pageClass) {
    var ts = $("."+pageClass).parents(".integral-more-item");
    ts.toggleClass("arrow_up");
    ts.find(".integral-left-nav-item").slideToggle();

    if(ts.find(".more-box").length == 0) {
        $("."+pageClass).parents(".integral-left-nav-item").addClass("active");
    }

    $("."+pageClass).addClass("active");
}

//定位当前页面(新)
function newCurrPage(pageClass) {
    /*$("."+pageClass).addClass('menuChange');*/
    var style = $("."+pageClass).attr('style');
    style = style + ';color:rgb(243, 127, 50);background: rgb(221, 230, 237);';
    $("."+pageClass).attr('style', style);
}

/**
 * 通过get方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function getResultData(url, data, callback) {
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        dataType: "json",
        success: function (result) {
            if (result.code) {
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            } else {
                callback(result.data);
            }
        }
    });
}

/**
 * 同步通过get方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function getSyncResultData(url, data, callback) {
    $.ajax({
        type: "post",
        url: url,
        data: data,
        dataType: "json",
        async: false,
        success: function(result){
            if(result.code){//查询成功
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            }else{
                callback(result.data);
            }
        }
    });
}

/**
 * 通过post方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function postResultData(url, data, callback) {
    //loading层
    var index = layer.load(1, {
        shade: [0.2,'#000'] //0.1透明度的白色背景
    });

    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function(result){
            layer.close(index);

            if(result.code){//查询失败
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            }else{
                callback(result.data);
            }
        }
    });
}

/**
 * 通过post方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function postResult(url, data, callback) {
    //loading层
    var index = layer.load(1, {
        shade: [0.2,'#000'] //0.1透明度的白色背景
    });

    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function(result){
            layer.close(index);

            if(result.code){//失败
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            }else{
                callback(result);
            }
        }
    });
}

/**
 * 通过post方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function postSyncResult(url, data, callback) {
    //loading层
    var index = layer.load(1, {
        shade: [0.2,'#000'] //0.1透明度的白色背景
    });

    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        async: false,
        success: function(result){
            layer.close(index);

            if(result.code){//失败
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            }else{
                callback(result);
            }
        }
    });
}

/**
 * 通过同步post方式获取数据信息
 * @param url
 * @param data
 * @param callback
 */
function postSyncResultData(url, data, callback) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        async: false,
        success: function(result){
            if(result.code){//查询成功
                layer.alert(result.message);

                if($("input[name=captcha]").length > 0) {
                    refreshCode();
                }
            }else{
                callback(result.data);
            }
        }
    });
}

function funtothree(v){
    window.open(v);
}
