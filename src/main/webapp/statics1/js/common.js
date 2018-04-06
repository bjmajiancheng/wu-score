
//表单序列化
function serializeForm(form) {
    var result = {};// 要传递给后台的对象数据
    var array = form.serializeArray();// 序列化表单内容
    $(array).each(function() {
        result[this.name] = this.value.trim();
    });

    return result;
}
