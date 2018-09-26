$('.menuCard>p').attr({'isclick':'1'});
$('.cardOnes>p').attr({'isTrue':'0'});
$('.menuCard>p').click(function(){
    var clickNum=$(this).attr('isclick');
    if(clickNum==0){
        $(this).parent().find('.cardOne').css({'display':'none'});
        $(this).attr({'isclick':'1'});
        $("#menu").css("height","483px");
        $("#icon").css("display","block");
        $('#icon1').css("display",'none');
        // $(this).next('i').attr({'background':'url(../img/left.png) no-repeat center top;'});
    }else{
        // $(this).next('i').attr({'background':'url(../img/bottom.png) no-repeat center top;});
        $("#menu").css("height","600px");
        $(this).parent().find('.cardOne').css({'display':'block'});
        $(this).attr({'isclick':'0'});
        $("#icon1").css("display","block");
        $('#icon').css("display",'none')
    }
});
$('.cardOnes>p').click(function(){
    var clickNum=$(this).attr('isTrue');
    if(clickNum==0){

        $(this).parent().find('.cardTwo').css({'display':'block'});
        $(this).attr({'isTrue':'1'});

    }else{
        $(this).parent().find('.cardTwo').css({'display':'none'});
        $(this).attr({'isTrue':'0'});
    }
});
$('.cardTwos>p').click(function(){
    if(clickNum==0){
        $(this).parent().find('.cardTwo').css({'display':'block'});
        $(this).attr({'isTrue':'1'});
    }else{
        $(this).parent().find('.cardTwo').css({'display':'none'});
        $(this).attr({'isTrue':'0'});
    }
})
$('.cardOnes>p').mouseover(function(){
    $('.cardOnes>p').css({'color':'#666','background':'#fff'})
    $(this).css({'color':'#f37f32','background':'#dde6ed'})
})
$('.cardTwos>p').mouseover(function(){
    $('.cardTwos>p').css('background','#fff')
    $(this).css('background','#dde6ed')
})
var dataJson='json/aa.json';
function createDemo(name){
    var container = $('#pagination-' + name);
    $.get(dataJson,function(data){
        var sources = data;
        var options = {
            dataSource: sources,
            className: 'paginationjs-theme-blue',
            callback: function(response, pagination){
                var dataHtml = '';

                $.each(response, function(index, item){
                    dataHtml += '<li>'+
                        '<a title="'+item.SXMC+'" href="/tzgg/6387076.jhtml">'+autoAddEllipsis(item.SXMC,100)+'<span>'+autoAddEllipsis(item.BMMC,40)+'</span></a>'+
                        '</li>'
                });
                $('.panel_content .news-list').html(dataHtml);
            }
        };
        $.pagination(container, options);
        return container;

    })
}
/*createDemo('demo1');
$('.s_municipal').click(function(){
    dataJson='json/aa.json';
    createDemo('demo1');
})
$('.q_municipal').click(function(){
    dataJson='json/q_index.json';
    createDemo('demo1');
})*/
function autoAddEllipsis(pStr, pLen) {

    var _ret = cutString(pStr, pLen);
    var _cutFlag = _ret.cutflag;
    var _cutStringn = _ret.cutstring;

    if ("1" == _cutFlag) {
        return _cutStringn + "...";
    } else {
        return _cutStringn;
    }
}
function cutString(pStr, pLen) {

    // 原字符串长度
    var _strLen = pStr.length;

    var _tmpCode;

    var _cutString;

    // 默认情况下，返回的字符串是原字符串的一部分
    var _cutFlag = "1";

    var _lenCount = 0;

    var _ret = false;

    if (_strLen <= pLen / 2) {
        _cutString = pStr;
        _ret = true;
    }

    if (!_ret) {
        for (var i = 0; i < _strLen ; i++) {
            if (isFull(pStr.charAt(i))) {
                _lenCount += 2;
            } else {
                _lenCount += 1;
            }

            if (_lenCount > pLen) {
                _cutString = pStr.substring(0, i);
                _ret = true;
                break;
            } else if (_lenCount == pLen) {
                _cutString = pStr.substring(0, i + 1);
                _ret = true;
                break;
            }
        }
    }

    if (!_ret) {
        _cutString = pStr;
        _ret = true;
    }

    if (_cutString.length == _strLen) {
        _cutFlag = "0";
    }

    return { "cutstring": _cutString, "cutflag": _cutFlag };
}
function isFull(pChar) {
    if ((pChar.charCodeAt(0) > 128)) {
        return true;
    } else {
        return false;
    }
}




