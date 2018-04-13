
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

    $("."+pageClass).addClass("active");
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
        success: function(result){
            if(result.code){//查询成功
                layer.alert(result.message);
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
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function(result){
            if(result.code){//查询成功
                layer.alert(result.message);
            }else{
                callback(result.data);
            }
        }
    });
}
