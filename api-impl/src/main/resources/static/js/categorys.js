var uu = $('#url');
var BaseUrl=uu.val();
var params="";
var url;
var submitClickState = false;


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

// 更多
$('.more-list').on('click','span', function () {
    if ($(this).hasClass('only')) {
        $(this).toggleClass('current').siblings().removeClass('current');
    } else {
        $(this).toggleClass('current');
    }
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

/*
 * 显示区域，获取circle json 数据
 * cicleData 数据存储
 * */
var circleData = null;
function showDistrict(districtId, circleId) {
    $.getJSON('/static/mock/circle.json', function (districtList) {
        circleData = districtList;
        $('#level2').removeClass('none');
        $('#level3').addClass('none');
        $('#level2').empty();

        var str;
        if (districtId || circleId) {
            str = '<li onclick="submitPlace(event)">不限</li>';
        } else {
            str = '<li class="current" onclick="submitPlace(event)">不限</li>';
        }

        for (var i in districtList) {

            if (districtId && districtId == districtList[i].districtId) {
                str += '<li ' + 'class="current"' + ' onclick="showBusiness('+ districtList[i].districtId +'">'+ districtList[i].name +'</li>';
            } else {
                str += '<li onclick="showBusiness('+ districtList[i].districtId +')">'+ districtList[i].name +'</li>';
            }
        }
        $('#level2').append(str);

        if (districtId) {
            showBusiness(districtId, circleId);
        }
    });
}
/*
 * 显示商圈，使用circleData json 数据
 * businessData 存储商圈信息
 * */
function showBusiness(districtid, circleId) {
    $('#level3').removeClass('none');
    $('#level3').empty();

    $.getJSON('/static/mock/circle.json',function (districtList) {
        var _circle;
        for (var i in districtList) {
            if (districtid == districtList[i].districtId) {
                _circle = districtList[i].children;
            }
        }

        var str;
        if (circleId) {
            str = '<li onclick="submitDirstrict('+ districtid +',event)">不限</li>';
        } else {
            str = '<li class="current" onclick="submitDirstrict('+ districtid +',event)">不限</li>';
        }

        for (var i in _circle) {

            if (circleId == _circle[i].circle) {
                str += '<li class="current" onclick="submitBussiness('+ districtid +',' + _circle[i].circle +',event)">'+ _circle[i].name +'</li>'
            } else {
                str += '<li onclick="submitBussiness('+ districtid +',' + _circle[i].circle +',event)">'+ _circle[i].name +'</li>'
            }
        }
        $('#level3').append(str);
    });
};



/*
 * 显示地铁线，获取subway json 数据
 * subwayData 数据存储
 * */
var subwayData = null;
function showSubway(lineId, stationId) {
    $.getJSON('/static/mock/subway.json',function (subwayList) {
        subwayData = subwayList;
        $('#level2').removeClass('none');
        $('#level3').addClass('none');
        $('#level2').empty();

        var str;
        if (lineId) {
            str = '<li onclick="submitSubway(event)">不限</li>';
        } else {
            str = '<li class="current" onclick="submitSubway(event)">不限</li>';
        }

        for (var i in subwayList) {

            if (lineId == subwayList[i].subwayid) {
                str += '<li class="current" onclick="showStation('+ subwayList[i].subwayid + ')">'+ subwayList[i].name +'</li>';
            } else {
                str += '<li onclick="showStation('+ subwayList[i].subwayid + ')">'+ subwayList[i].name +'</li>';
            }
        }

        if (stationId) {
            showStation(lineId, stationId);
        }

        $('#level2').append(str);
    })
}
/*
 * 显示站点，使用subwayData json 数据
 * stationData 存储站点信息
 * */
function showStation(lineId, stationId) {

    $('#level3').removeClass('none');
    $('#level3').empty();

    $.getJSON('/static/mock/subway.json',function (subwayList) {
        var _station;
        for (var index in subwayList) {
            if (lineId == subwayList[index].subwayid) {
                _station = subwayList[index].children;
            }
        }

        var str;
        if (stationId) {
            str = '<li onclick="submitSubwayLine('+ lineId +',event)">不限</li>';
        } else {
            str = '<li class="current" onclick="submitSubwayLine('+ lineId +',event)">不限</li>';
        }

        for (var i in _station) {

            if (stationId == _station[i].stationid) {
                str += '<li class="current" onclick="submitStation('+ lineId +',' + _station[i].stationid +',event)">'+ _station[i]['station_name'] +'</li>';
            } else {
                str += '<li onclick="submitStation('+ lineId +',' + _station[i].stationid +',event)">'+ _station[i]['station_name'] +'</li>';
            }
        }

        $('#level3').append(str);
    });
};


/*
 * 地铁不限,更改导航内容
 * */
/*function submitSubway(e) {
    tabTextReplace(e,'地铁');
    location.href=BaseUrl
}*/
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
    // tabMarkDom.find('em').text(text);
    $('.filter-item').removeClass('active');
    $('.global-mark').addClass('none');
    $('body').removeClass('fixed-scroll');
}