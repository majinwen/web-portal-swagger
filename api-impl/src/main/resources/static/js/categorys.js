var uu = $('#url');
var BaseUrl=uu.val();
console.log(BaseUrl)
var params="";
var url;
var submitClickState = false;

$('#category-tab').on('click','li', function () {
    var $dom = getDataDom($(this),'panel');
    $(this).toggleClass('current').removeClass('choose').siblings().removeClass('current');
    $dom.toggleClass('active').siblings().removeClass('active');

    if ($dom.hasClass('active')) {
        if ($('#level2').is(':hidden') && $dom.attr('data-mark') == 'panel-place'){
            $dom.find('#level1').children().eq(0).addClass('current');
            showDistrict();
        }
    }
    if ($('#category-tab').find('.current').length <= 0) {
        $('.global-mark').addClass('none');
        $('body').removeClass('fixed-scroll');
    } else {
        $('.global-mark').removeClass('none');
        $('body').addClass('fixed-scroll');
    }
});

function getDataDom(attrDom, attrStr) {
    var str = attrDom.attr('data-mark'),
        index = str.indexOf('-'),
        substr = str.substring(index),
        domClass = attrStr + substr,
        $dom = $('[data-mark='+domClass+']');
    return $dom
}

// 位置
$('.place-list').on('click','li', function () {
    $(this).addClass('current').siblings().removeClass('current');
});

// 价格
$('.price-list').on('click','li', function (e) {
    $(this).addClass('current').siblings().removeClass('current');
    var beginPrice = $(this).attr('data-begin-price'),
        endPrice = $(this).attr('data-end-price');
  if(beginPrice!=""||endPrice!=""){
      params = '?beginPrice=' + beginPrice + '&endPrice=' + endPrice;
  }
    url = BaseUrl + params;
    console.log(url);
    tabTextReplace(e,$(this).text());
    $.get(url, function () {
        location.href = url;
    });
});

// 户型
$('.type-list').on('click','li', function (e) {
    submitClickState = false;
    if ($(this).attr('data-type') == 0) {
        $(this).addClass('current').siblings().removeClass('current');
    } else {
        $(this).siblings('li[data-type="0"]').removeClass('current');
        $(this).toggleClass('current');
    }
});
/*
 * 提交选中户型
 * */
$('#typeSubmit').on('click', function (e) {
    submitClickState = true;
    var layoutText = $('.type-list').find('li.current').text(),
        chooseType = $('.type-list').find('li.current'),
        layoutTextArr = [];

    if (layoutText == '不限') {
        tabTextReplace(e,layoutText);
        url = BaseUrl;
        console.log(url);
        location.href=BaseUrl;
        return;
    }

    for (var i = 0; i < chooseType.length; i++) {
        layoutTextArr.push(chooseType[i].dataset['type']);
    }

    if (layoutTextArr.length > 1) {
        tabTextReplace(e,'多选');
    } else {
        tabTextReplace(e,layoutText);
    }

    params = '?layoutId=' + layoutTextArr.join(',');
    url = BaseUrl + params;
    console.log(url);
    $.get(url, function () {
        location.href = url;
    });
});

// 更多
$('.more-list').on('click','span', function () {
    if ($(this).hasClass('only')) {
        $(this).toggleClass('current').siblings().removeClass('current');
    } else {
        $(this).toggleClass('current');
    }
});
/*
 * 更多筛选提交
 * */
$('#moreSubmit').on('click', function (e) {
    var moresubmitUrl = BaseUrl + '?';
    var domList = $('.more-list').children('dl');
    for (var i=0; i < domList.length; i++) {
        var dataType = domList.eq(i).children('dt').attr('data-type'),
            dataTypeArr = domList.eq(i).find('.current'),
            arr = [];
        if (dataTypeArr.length) {
            for(var j=0; j<dataTypeArr.length; j++) {
                arr.push(dataTypeArr.eq(j).attr('data-info'))
            }
            moresubmitUrl += dataType + '=' + arr.join(',') + '&';
        }

    }
    if ($('.more-list').find('.current').length > 0) {
        tabTextReplace(e, '多选')
    } else {
        tabTextReplace(e, '更多');
        $('#category-tab').find('li[data-mark="tab-more"]').removeClass('choose');
    }
    console.log(moresubmitUrl.substr(0, moresubmitUrl.length -1));

});
/*
 * 更多筛选重置
 * */
$('#moreReset').on('click', function () {
    $('.more-list').find('.current').removeClass('current');
});

$('.category-cont').on('click', function (e) {
    e.stopPropagation();
});

/*$('.global-mark').click(function (e) {
    $('.filter-item').removeClass('active');
    $('.global-mark').toggleClass('none');
});*/

/*
 * 显示区域，获取circle json 数据
 * cicleData 数据存储
 * */
var circleData = null;
function showDistrict() {
    $.getJSON('/static/mock/circle.json', function (districtList) {
        circleData = districtList;
        $('#level2').removeClass('none');
        $('#level3').addClass('none');
        $('#level2').empty();
        var str = '<li class="current" onclick="submitPlace(event)">不限</li>';
        for (var i = 0; i < districtList.length; i++) {
            str += '<li onclick="showBusiness('+ districtList[i].districtId +','+ i +')">'+ districtList[i].name +'</li>'
        }
        $('#level2').append(str);
    });
}
/*
 * 显示商圈，使用circleData json 数据
 * businessData 存储商圈信息
 * */
var businessData = null;
function showBusiness(districtid, index) {
    businessData = circleData[index].children;

    $('#level3').removeClass('none');
    $('#level3').empty();
    var str = '<li class="current" onclick="submitDirstrict('+ districtid +',event'+','+ index +')">不限</li>';
    for (var i = 0; i < businessData.length; i++) {
        str += '<li onclick="submitBussiness('+ districtid +',' + businessData[i].circle +',event'+','+ i +')">'+ businessData[i].name +'</li>'
    }
    $('#level3').append(str);
}

/*
 * 提交选中区域
 * */
function submitDirstrict(districtid,e,index) {
    params = '?districtId=' + districtid;
    url = BaseUrl + params;
    console.log(url);
    tabTextReplace(e,circleData[index].name);
    location.href=url
   /* $.get(url, function () {
        location.href = url;
    });*/
}
/*
 * 提交选中商圈(区域id及商圈id)
 * */
function submitBussiness(districtid,areaId,e,index) {
    params = '?districtId=' + districtid + '&areaId=' + areaId;
    url = BaseUrl + params;
    console.log(url);

    tabTextReplace(e,businessData[index].name);
    location.href=url
}

/*
 * 显示地铁线，获取subway json 数据
 * subwayData 数据存储
 * */
var subwayData = null;
function showSubway() {
    $.getJSON('/static/mock/subway.json',function (subwayList) {
        subwayData = subwayList;
        $('#level2').removeClass('none');
        $('#level3').addClass('none');
        $('#level2').empty();
        var str = '<li class="current" onclick="submitSubway(event)">不限</li>';
        for (var i = 0; i < subwayList.length; i++) {
            str += '<li onclick="showStation('+ subwayList[i].subwayid +','+ i +')">'+ subwayList[i].name +'</li>'
        }
        $('#level2').append(str);
    })
}
/*
 * 显示站点，使用subwayData json 数据
 * stationData 存储站点信息
 * */
var stationData = null;
function showStation(subwayid, index) {
    stationData = subwayData[index].children;

    $('#level3').removeClass('none');
    $('#level3').empty();
    var str = '<li class="current" onclick="submitSubwayLine('+ subwayid +',event'+','+ index +')">不限</li>';
    for (var i = 0; i < stationData.length; i++) {
        str += '<li onclick="submitStation('+ subwayid +',' + stationData[i].stationid +',event'+','+ i +')">'+ stationData[i].station_name +'</li>'
    }
    $('#level3').append(str);
}
/*
 * 提交选中地铁线路
 * 站点为不限
 * */
function submitSubwayLine(subwayid,e,index) {
    params = '?districtId=' + subwayid;
    url = BaseUrl + params;
    console.log(url);

    tabTextReplace(e,subwayData[index].name);
    $.ajax({
        type: 'GET',
        url: url,
        success: function (response) {
            console.log(response);
        },
        error: function (err) {
            console.log(err);
        }
    });
    $.get(url, function () {
        location.href = url;
    });
}
/*
 * 提交选中地铁站点
 * */
function submitStation(subwayid,subwayStationId,e,index) {
    params = '?subwayLineId=' + subwayid + '&subwayStationId=' + subwayStationId;
    url = BaseUrl + params;
    console.log(url);

    tabTextReplace(e,stationData[index].station_name,url);
    $.get(url, function () {
        location.href = url;
    });
}

/*
 * 区域不限,更改导航内容
 * */
function submitPlace(e) {
    tabTextReplace(e,'区域');
    location.href=BaseUrl;
}
/*
 * 地铁不限,更改导航内容
 * */
function submitSubway(e) {
    tabTextReplace(e,'地铁');
    location.href=BaseUrl
}
/*
 * 替换导航内容
 * */
function tabTextReplace(e,text) {
    var target = e.target || e.srcElement,
        currentDom = $(target),
        tabMarkText = currentDom.parents('.filter-item').attr('data-mark').substring(6),
        tabMarkDom = $('#category-tab').find('li[data-mark="tab-' + tabMarkText +'"]');
    tabMarkDom.attr('class','choose');
    tabMarkDom.find('em').text('');
    tabMarkDom.find('em').text(text);
    $('.filter-item').removeClass('active');
    $('.global-mark').addClass('none');
    $('body').removeClass('fixed-scroll');
}