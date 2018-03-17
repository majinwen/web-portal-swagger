var params = '';
var url;
var submitClickState = false;
var req = GetRequest();
var _localHref = window.location.pathname;
var quyu;
var shangquan;
var diteixian;
var diteizhan;
var junjia = [];
var huxing = [];
var louling = [];
var moreChooseZhuge = [];
$(function () {
    var disStr = '<li id="district-option">区域</li>';
    var subStr = '<li id="subway-option">地铁</li>';
    var nearbyStr = '<li id="nearby-option">附近</li>';

    if ((_localHref.indexOf('/zufang')) > 0) {
        $('#level1').append(disStr+subStr+nearbyStr);
    } else {
        $('#level1').append(disStr+subStr);
    }

    //列表页排序切换
    listSortTab();

    //上拉分页
    var top = $(document).scrollTop();
    if (top == 0 ){
        pullDownAction();
    }
    //下拉分页
    if (true) {//$('#valueList').find('li').length>=10
        // 拦截默认的下拉加载
        if(!window["$toutiao_customer_pullUpAction"]){
            pullUpAction();
        }

    } else {
        $('.tip-box').removeClass('none');
    }

    $('#category-tab').on('click', 'li', function () {
        var $dom = getDataDom($(this),'panel');
        $(this).toggleClass('current').toggleClass('choose').siblings().removeClass('current');
        $dom.toggleClass('active').siblings().removeClass('active');

        if ($('#level2').is(':hidden') && $dom.attr('data-mark') == 'panel-place') {
            if (req['districtId'] || req['subwayLineId'] || req['near']) {
                $('#level1').find('li').removeClass('current');
                if (req['districtId']) {
                    $('#district-option').addClass('current');
                    if ((_localHref.indexOf('/xingfang')) > 0 || (_localHref.indexOf('/loupan')) > 0) {
                        showOnlyDistrict(req['districtId']);
                    } else {
                        showDistrict(req['districtId'], req['areaId']);
                    }
                } else if (req['subwayLineId']) {
                    $('#subway-option').addClass('current');
                    showSubway(req['subwayLineId'], req['subwayStationId']);
                } else if (req['near']) {
                    $('#nearby-option').addClass('current');
                    showNearby(req['near']);
                }
            } else {
                $('#district-option').addClass('current');
                if ((_localHref.indexOf('/xingfang')) > 0 || (_localHref.indexOf('/loupan')) > 0) {
                    showOnlyDistrict();
                } else {
                    showDistrict();
                }
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

    $('#level1').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
        if ($(this).attr('id') == 'district-option') {
            if ((_localHref.indexOf('/xingfang')) > 0 || (_localHref.indexOf('/loupan')) > 0) {
                showOnlyDistrict();
            } else {
                showDistrict();
            }
        } else if ($(this).attr('id') == 'subway-option') {
            showSubway();
        } else if ($(this).attr('id') == 'nearby-option') {
            showNearby();
        }
    });

    $('#level2').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });

    /**
     * 区域商圈筛选
     * */
    if (req['districtId'] || req['areaId']) {
        districtArea(req);
    }
    /**
     * 区域商圈处理
     * @param req
     */
    function districtArea(req) {

        if ((_localHref.indexOf('/loupan')) > 0) {
            $.getJSON('/static/mock/xfcircle.json',function (districtList) {
                var _districtId = req['districtId'] || [];
                var _districtName;
                for (var i in districtList) {
                    if (_districtId == districtList[i].districtId) {
                        _districtName = districtList[i].name;
                    }
                }
                $('li[data-mark="tab-place"]').addClass('choose').find('em').text(_districtName);
            });
        }else{
            $.getJSON('/static/mock/circle.json',function (districtList) {
                var _districtId = req['districtId'] || [];
                var _areaId = req['areaId'] || [];

                var _circle, _districtName, _circleName;
                for (var i in districtList) {
                    if (_districtId == districtList[i].districtId) {
                        _districtName = districtList[i].name;
                        _circle = districtList[i].children;
                    }
                }

                for (var index in _circle) {
                    if (_areaId == _circle[index].circle) {
                        _circleName = _circle[index].name;
                    }
                }
                $('li[data-mark="tab-place"]').addClass('choose').find('em').text(_circleName?_circleName:_districtName);
            });
        }
    }

    /**
     * 地铁筛选
     */
    if (req['subwayLineId'] || req['subwayStationId']) {
        subway(req);
    }
    /**
     * 地铁线站处理
     * @param req
     */
    function subway(req) {
        $.getJSON('/static/mock/subway.json',function (subwayList) {
            var _subwayLineId = req['subwayLineId'] || [];
            var _subwayStationId = req['subwayStationId'] || [];

            var _station, _subwayLineName, _subwayStationName;
            for (var i in subwayList) {
                if (_subwayLineId == subwayList[i].subwayid) {
                    _subwayLineName = subwayList[i].name;
                    _station = subwayList[i].children;
                }
            }

            for (var index in _station) {
                if (_subwayStationId == _station[index].stationid) {
                    _subwayStationName = _station[index]['station_name'];
                }
            }

            $('li[data-mark="tab-place"]').addClass('choose').find('em').text(_subwayStationName?_subwayStationName:_subwayLineName);
        });
    }
    /**
     * 附近筛选
     */
    if (req['near']){
        near(req)
    }
    /**
     * 附近距离处理
     * @param req
     */
    function near(req) {
        var _nearArr = [1,3,5];
        var _nearNumber = req['near'] || [];
        var _nearText;
        for (var i = 0; i < _nearArr.length; i++) {

            if (_nearNumber == _nearArr[i]) {
                _nearText = _nearArr[i] + 'km'
            }
        }
        $('li[data-mark="tab-place"]').addClass('choose').find('em').text(_nearText);
    }

    /**
     * 价格筛选
     */
    if (req['beginPrice'] && req['endPrice']) {
        $('.price-list li:gt(0)').each(function () {

            if (req['beginPrice'] == $(this).data('begin-price') && req['endPrice'] == $(this).data('end-price')) {
                $('.price-list li').removeClass('current');
                $(this).addClass('current');

                if ($('li[data-mark="tab-rent-price"]')) {
                    $('li[data-mark="tab-rent-price"]').addClass('choose').find('em').text($(this).text());
                } else if ($('li[data-mark="tab-price"]')) {
                    $('li[data-mark="tab-price"]').addClass('choose').find('em').text($(this).text());
                }
            }
        });
    }

    /**
     * 楼龄筛选
     */
    if (req['age']) {
        $('.age-list li:gt(0)').each(function () {

            if (req['age'] == $(this).data('info')) {
                $('.age-list li').removeClass('current');
                $(this).addClass('current');

                $('li[data-mark="tab-age"]').addClass('choose').find('em').text($(this).text());
            }
        });
    }

    /**
     * 户型筛选
     */
    if (req['layoutId']) {
        var layoutArray = req['layoutId'].split(',');

        $('.type-list li').removeClass('current');
        $.each(layoutArray, function (index, item) {

            $('.type-list li:gt(0)').each(function () {
                if (item == $(this).data('type')) {

                    $(this).addClass('current');

                    if (layoutArray.length > 1) {
                        $('li[data-mark="tab-type"]').addClass('choose').find('em').text('多选');
                    } else {
                        $('li[data-mark="tab-type"]').addClass('choose').find('em').text($(this).text());
                    }
                }
            });
        });
    }

    /**
     * 物业类型筛选--多选
     */
    if (req['propertyTypeId']) {
        moreOption('propertyTypeId');
    }
    /**
     * 面积筛选---多选
     */
    if (req['houseAreaSize']) {
        moreOption('houseAreaSize');
    }
    /**
     * 电梯筛选---单选
     */
    if (req['elevatorFlag']) {
        moreOption('elevatorFlag');
    }
    /**
     * 建筑类型---多选
     */
    if (req['buildingType']) {
        moreOption('buildingType');
    }
    /**
     * 销售状态筛选---多选
     */
    if (req['saleType']) {
        moreOption('saleType');
    }
    /**
     * 楼盘特色筛选---多选
     */
    if (req['buildingFeature']) {
        moreOption('buildingFeature');
    }
    /**
     * 装修标准筛选---多选
     */
    if (req['deliverStyle']) {
        moreOption('deliverStyle');
    }
    /**
     * 朝向筛选---多选
     */
    if (req['houseOrientationId']) {
        moreOption('houseOrientationId');
    }
    /**
     * 标签筛选---多选
     */
    if (req['houseLabelId']) {
        moreOption('houseLabelId');
    }
    /**
     * 楼龄筛选---单选
     */
    if (req['houseYearId']) {
        moreOption('houseYearId');
    }
    /**
     * 建筑类型筛选---多选
     */
    if (req['buildingTypeId']) {
        moreOption('buildingTypeId');
    }
    /**
     * 权属筛选---多选
     */
    if (req['ownership']) {
        moreOption('ownership');
    }
    /**
     * 朝向筛选---多选(租房)
     */
    if (req['forward']) {
        moreOption('forward');
    }
    /**
     * 来源筛选---多选(租房)
     */
    if (req['source']) {
        moreOption('source');
    }
    /**
     * 供暖筛选---多选(租房)
     */
    if (req['ht']) {
        moreOption('ht');
    }
    /**
     * 整租筛选---多选
     */
    if (req['elo']) {
        moreOption1('elo');
    }
    /**
     * 合租筛选---多选
     */
    if (req['jlo']) {
        moreOption1('jlo');
    }

    /**
     * 更多筛选项处理
     * @param optionType
     */
    function moreOption(optionType) {
        $('#category-tab').find('li[data-mark="tab-more"]').addClass('choose').find('em').text('多选');
        var dataTypeCont = $('dt[data-type="' + optionType + '"]').next('dd').find('span').hasClass('only');
        if (dataTypeCont) {
            $('dt[data-type="' + optionType + '"]').next('dd').find('span').each(function () {
                if (req["" +optionType +""] == $(this).data('info')) {
                    $(this).siblings('span').removeClass('current');
                    $(this).addClass('current');
                }
            });
        } else {
            var moreOptionArray = req[optionType].split(',');
            $('dt[data-type="' + optionType + '"]').next('dd').find('span').removeClass('current');
            $.each(moreOptionArray, function (index, item) {
                $('dt[data-type="' + optionType + '"]').next('dd').find('span').each(function () {
                    if (item == $(this).data('info')) {
                        $(this).addClass('current');
                    }
                });
            });
        }
    }
    /**
     * 更多筛选项处理
     * @param optionType
     */
    function moreOption1(optionType) {
        $('#category-tab').find('li[data-mark="tab-rent-type"]').addClass('choose').find('em').text('多选');
        var dataTypeCont = $('dt[data-type="' + optionType + '"]').next('dd').find('span').hasClass('only');
        if (dataTypeCont) {
            $('dt[data-type="' + optionType + '"]').next('dd').find('span').each(function () {
                if (req["" +optionType +""] == $(this).data('info')) {
                    $(this).siblings('span').removeClass('current');
                    $(this).addClass('current');
                }
            });
        } else {
            var moreOptionArray = req[optionType].split(',');
            $('dt[data-type="' + optionType + '"]').next('dd').find('span').removeClass('current');
            $.each(moreOptionArray, function (index, item) {
                $('dt[data-type="' + optionType + '"]').next('dd').find('span').each(function () {
                    if (item == $(this).data('info')) {
                        $(this).addClass('current');
                    }
                });
            });
        }
    }
});


function getHouseName(_localHref,houseName) {
    if (_localHref.indexOf('/xiaoqu')>0){
        houseName = '小区';
    }
    if (_localHref.indexOf('/esf')>0){
        houseName = '二手房';
    }
    if (_localHref.indexOf('/loupan')>0){
        houseName = '新房';
    }
    if (_localHref.indexOf('/zufang')>0){
        houseName = '租房';
    }
    return houseName;
}

function getDataDom(attrDom, attrStr) {
    var str = attrDom.attr('data-mark'),
        index = str.indexOf('-'),
        substr = str.substring(index),
        domClass = attrStr + substr,
        $dom = $('[data-mark='+domClass+']');
    return $dom
}

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strArray = str.split("&");

        for (var i in strArray) {
            theRequest[strArray[i].split("=")[0]]=(strArray[i].split("=")[1]);
        }
    }
    return theRequest;
}

/**
 * 拼接参数
 * @param req
 * @param sortFlag 非排序时排序置空
 * @returns {string}
 */
function joinParams(req, noPageFlag) {
    var targetUrl = '';

    if (noPageFlag) {
        req['pageNum'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }

    for (var key in req) {
        if (req[key]) {
            targetUrl += '&' + key + "=" + req[key];
        }
    }

    if (targetUrl.length > 1) {
        targetUrl = '?' + targetUrl.substring(1);
    }

    return targetUrl;
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
    // tabMarkDom.find('em').text('');
    tabMarkDom.find('em').text(text);
    $('.filter-item').removeClass('active');
    $('.global-mark').addClass('none');
    $('body').removeClass('fixed-scroll');
}

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

/**
 * 新房只显示区域
 * */
function showOnlyDistrict(districtId) {
    $.getJSON('/static/mock/xfcircle.json', function (districtList) {
        circleData = districtList;
        $('#level2').removeClass('none');
        $('#level3').addClass('none');
        $('#level2').empty();

        var str;
        if (districtId) {
            str = '<li onclick="submitPlace(event)">不限</li>';
        } else {
            str = '<li class="current" onclick="submitPlace(event)">不限</li>';
        }

        for (var i in districtList) {

            if (districtId && districtId == districtList[i].districtId) {
                str += '<li ' + 'class="current"' + ' onclick="submitDirstrict('+ districtList[i].districtId +',event)">'+ districtList[i].name +'</li>';
            } else {
                str += '<li onclick="submitDirstrict('+ districtList[i].districtId +',event)">'+ districtList[i].name +'</li>';
            }
        }
        $('#level2').append(str);
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
}

/**
 * 区域不限
 * */
function submitPlace(e) {
    req['pageNum'] = null;
    req['areaId'] = null;
    req['districtId'] = null;
    req['subwayLineId'] = null;
    req['subwayStationId'] = null;
    req['lat'] = null;
    req['near'] = null;
    req['lon'] = null;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e, '区域');
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'区域位置':'不限'});
        location.replace(url) ;
    })
}

/**
 * 选中区域条件
 * @param districtid
 * @param e
 * */
function submitDirstrict(districtid, e) {
    if (districtid) {
        req['pageNum'] = null;
        req['subwayLineId'] = null;
        req['subwayStationId'] = null;
        req['areaId'] = null;
        req['near'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['districtId'] = districtid;
    var params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    tabTextReplace(e);
    quyu = $('#level2').find('li.current').text();
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'区域位置':quyu});
        location.replace(url);
    });
}

/**
 * 筛选商圈条件
 * @param districtid
 * @param areaId
 * @param e
 * */
function submitBussiness(districtid, areaId, e) {
    if (districtid) {
        req['pageNum'] = null;
        req['subwayLineId'] = null;
        req['subwayStationId'] = null;
        req['near'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['districtId'] = districtid;
    req['areaId'] = areaId;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e);
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    quyu = $('#level2').find('li.current').text();
    shangquan = e.currentTarget.innerText;
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'区域位置': quyu +'-'+shangquan});
        location.replace(url);
    });
}

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
        if (lineId || stationId) {
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

        if (lineId) {
            showStation(lineId, stationId);
        }

        $('#level2').append(str);
    })
}

/**
 * 地铁不限
 * */
function submitSubway(e) {
    req['pageNum'] = null;
    req['districtId'] = null;
    req['areaId'] = null;
    req['subwayLineId'] = null;
    req['subwayStationId'] = null;
    req['near'] = null;
    req['lat'] = null;
    req['lon'] = null;

    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e, '地铁');
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'地铁位置':'不限'});
        location.replace(url);
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
}

/*
 * 提交选中地铁线路
 * 站点为不限
 * */
function submitSubwayLine(subwayid, e) {
    if (subwayid) {
        req['pageNum'] = null;
        req['districtId'] = null;
        req['areaId'] = null;
        req['near'] = null;
        req['subwayStationId'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['subwayLineId'] = subwayid;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e);
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    diteixian =$('#level2').find('li.current').text();
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'地铁位置':diteixian});
        location.replace(url);
    })
}

/*
 * 提交选中地铁站点
 * */
function submitStation(subwayid, subwayStationId, e) {
    if (subwayid) {
        req['pageNum'] = null;
        req['districtId'] = null;
        req['areaId'] = null;
        req['lat'] = null;
        req['lon'] = null;
        req['near'] = null;
    }
    req['subwayLineId']=subwayid;
    req['subwayStationId']=subwayStationId;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e);
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    diteixian = $('#level2').find('li.current').text();
    diteizhan = e.currentTarget.innerText;
    $.get(url, function () {
        zhuge.track(houseName+'-按区域筛选',{'地铁位置':diteixian+'-'+diteizhan});
        location.replace(url);
    });
}

/*
 * 显示附近
 * */
function showNearby(nearNumber) {
    $('#level2').removeClass('none');
    $('#level3').addClass('none');
    $('#level2').empty();

    var str;
    if (showNearby) {
        str = '<li onclick="submitNearby(event)">不限</li>';
    } else {
        str = '<li class="current" onclick="submitNearby(event)">不限</li>';
    }
    var _nearArr = [1,3,5];
    for (var i = 0; i < _nearArr.length; i++) {

        if (nearNumber == _nearArr[i]) {
            str += '<li class="current" onclick="submitNearbyNumber(_nearArr[i],event)">'+ _nearArr[i]  +'km</li>';
        } else {
            str += '<li onclick="submitNearbyNumber('+ _nearArr[i] +',event)">'+ _nearArr[i]  +'km</li>';
        }
    }
    $('#level2').append(str);
}
/*
 * 提交选中附近不限
 * */
function submitNearby(e) {
    req['pageNum'] = null;
    req['districtId'] = null;
    req['areaId'] = null;
    req['lat'] = null;
    req['lon'] = null;
    req['near'] = null;
    req['subwayLineId'] = null;
    req['subwayStationId'] = null;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e);
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $.get(url, function () {
        location.replace(url);
    });
}
/*
 * 提交选中附近距离
 * */
function submitNearbyNumber(nearNumber, e) {
    if (nearNumber) {
        req['pageNum'] = null;
        req['districtId'] = null;
        req['areaId'] = null;
        req['subwayLineId'] = null;
        req['subwayStationId'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['near'] = nearNumber;
    params = joinParams(req);
    url = _localHref + params;
    tabTextReplace(e);
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $.get(url, function () {
        location.replace(url);
    });
}

/**
 * 选择价格
 * */
$('.price-list').on('click', 'li', function (e) {
    $(this).addClass('current').siblings().removeClass('current');
    var beginPrice = $(this).attr('data-begin-price');
    var endPrice = $(this).attr('data-end-price');
    if (beginPrice != '' || endPrice != '') {
        req['beginPrice']= beginPrice ;
        req['endPrice']=endPrice;
    } else if (beginPrice == '' || endPrice == '') {
        req['beginPrice']= null;
        req['endPrice']= null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    tabTextReplace(e, $(this).text());
    junjia.push($('.price-list').find('li.current').text());
    $.get(url, function () {
        var split = url.split('?');
        if (split.length == 1){
            zhuge.track(houseName+'-按均价筛选',{'均价范围':'不限'});
        }else {
            zhuge.track(houseName+'-按均价筛选',{'均价范围':junjia[0]});
        }
        location.replace(url);
    })
});

/**
 * 选择楼龄
 * */
$('.age-list').on('click', 'li', function (e) {
    $(this).addClass('current').siblings().removeClass('current');
    var ageNumber = $(this).attr('data-info');
    if (ageNumber != '') {
        req['age'] = ageNumber;
    } else if (ageNumber == '') {
        req['age'] = null;
        req['lat'] = null;
        req['lon'] = null;
    }
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref, houseName);
    tabTextReplace(e, $(this).text());
    louling.push($('.age-list').find('li.current').text());
    $.get(url, function () {
        var split = url.split('?');
        if (split.length == 1) {
            zhuge.track(houseName + '-按楼龄筛选', {'楼龄': '不限'});
        } else {
            zhuge.track(houseName + '-按楼龄筛选', {'楼龄': louling[0]});
        }
        location.replace(url);
    })
});

/**
 * 选择户型
 * */
$('.type-list').on('click','li', function (e) {
    submitClickState = false;
    if ($(this).attr('data-type') == 0) {
        $(this).addClass('current').siblings().removeClass('current');
    } else {
        $(this).siblings('li[data-type="0"]').removeClass('current');
        $(this).toggleClass('current');
    }
});

/**
 * 户型提交
 * */
$('#typeSubmit').on('click', function (e) {
    submitClickState = true;
    var layoutText = $('.type-list').find('li.current').text(),
        chooseType = $('.type-list').find('li.current'),
        layoutTextArr = [];

    if (layoutText == '不限') {
        tabTextReplace(e, layoutText);
        req['pageNum'] = null;
        req['layoutId'] = null;
        req['lat'] = null;
        req['lon'] = null;
        params = joinParams(req);
        url = _localHref + params;
        var houseName = null;
        houseName = getHouseName(_localHref,houseName);
        $.get(url, function () {
            zhuge.track(houseName+'-按户型筛选',{'户型':'不限'});
            location.replace(url);
        });
        return;
    }

    for (var i = 0; i < chooseType.length; i++) {
        layoutTextArr.push(chooseType[i].dataset['type']);
    }

    if (layoutTextArr.length > 1) {
        tabTextReplace(e, '多选');
    } else {
        tabTextReplace(e, layoutText);
    }
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    req['layoutId'] = layoutTextArr.join(',');
    params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    huxing = $('.type-list').find('li.current').text().split(' ');
    $.get(url, function () {
        if (huxing.length == 1) {
            zhuge.track(houseName + '-按户型筛选', {'户型': huxing[0]});
        } else {
            for (var i = 0; i < huxing.length - 1; i++) {
                zhuge.track(houseName + '-按户型筛选', {'户型': huxing[i]});
            }
        }
        location.replace(url);
    })
});

/**
 * 更多选择
 * */
$('.more-list').on('click','span', function () {
    if ($(this).hasClass('only')) {
        $(this).toggleClass('current').siblings().removeClass('current');
    } else {
        $(this).toggleClass('current');
    }

    if ($(this).hasClass('rent-only')) {
        $(this).addClass('current').siblings().removeClass('current');
    } else {
        $(this).siblings('span[data-info=""]').removeClass('current');
        $(this).addClass('current');
    }
});
/**
 * 更多选择提交
 * */
$('#moreSubmit').on('click', function (e) {
    var domList = $('.more-list').find('dl');

    $(domList).each(function () {
        var dataType = $(this).find('dt').attr('data-type'),
            dataTypeArr = $(this).find('.current'),
            arr = [];
        if (dataTypeArr.length) {
            $(dataTypeArr).each(function () {
                arr.push($(this).attr('data-info'));
            });
            req[dataType]= arr.join().toString();
        }else {
            req[dataType] = null;
            req['lat'] = null;
            req['lon'] = null;
        }
    });

    if ($('.more-list').find('.current').length > 0) {
        tabTextReplace(e, '多选')
    } else {
        tabTextReplace(e, '更多');
        $('#category-tab').find('li[data-mark="tab-more"]').removeClass('choose');
    }
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $('.more-list').find('span.current').each(function () {
        moreChooseZhuge.push($(this).text())
    });

    $.get(url, function () {
        if (moreChooseZhuge.length == 0){
            zhuge.track(houseName+'-更多筛选条件',{'筛选条件':'不限'});
        }else {
            for(var i = 0;i<moreChooseZhuge.length;i++){
                zhuge.track(houseName+'-更多筛选条件',{'筛选条件':moreChooseZhuge[i]});
            }
        }
        location.replace(url);
    });
});
/*
 * 更多筛选重置
 * */
$('#moreReset').on('click', function () {
    $('.more-list').find('.current').removeClass('current');
    req['pageNum'] = null;
    req['propertyTypeId'] = null;
    req['houseAreaSize'] = null;
    req['elevatorFlag'] = null;
    req['buildingType'] = null;
    req['saleType'] = null;
    req['buildingFeature'] = null;
    req['deliverStyle'] = null;
    req['houseOrientationId'] = null;
    req['houseLabelId'] = null;
    req['houseYearId'] = null;
    req['buildingTypeId'] = null;
    req['ownership'] = null;
    req['lat'] = null;
    req['lon'] = null;
    req['forward'] = null;
});
/**
 * 租房类型提交
 * */
$('#moreRentSubmit').on('click', function (e) {
    var domList = $('#rentType').find('dl');

    $(domList).each(function () {
        var dataType = $(this).find('dt').attr('data-type'),
            dataTypeArr = $(this).find('.current');
            var arr = [];
        if (dataTypeArr.length) {
            $(dataTypeArr).each(function () {
                arr.push($(this).attr('data-info'));
            });
            req[dataType]= arr.join().toString();
        } else {
            req[dataType] = null;
            req['lat'] = null;
            req['lon'] = null;
        }
    });

    if ($('#rentType').find('.current').length > 0) {
        tabTextReplace(e, '多选')
    } else {
        tabTextReplace(e, '更多');
        $('#category-tab').find('li[data-mark="tab-rent-type"]').removeClass('choose');
    }
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    params = joinParams(req);
    url = _localHref + params;
    var houseName = null;
    houseName = getHouseName(_localHref,houseName);
    $('#rentType').find('span.current').each(function () {
        moreChooseZhuge.push($(this).text())
    });

    $.get(url, function () {
        if (moreChooseZhuge.length == 0){
            zhuge.track(houseName+'-更多筛选条件',{'筛选条件':'不限'});
        }else {
            for(var i = 0;i<moreChooseZhuge.length;i++){
                zhuge.track(houseName+'-更多筛选条件',{'筛选条件':moreChooseZhuge[i]});
            }
        }
        location.replace(url);
    });
});
/*
 * 更多筛选重置(租房)
 * */
$('#moreRentReset').on('click', function () {
    $('.more-list').find('.current').removeClass('current');
    req['pageNum'] = null;
    req['lat'] = null;
    req['lon'] = null;
    req['lo1'] = null;
    req['lo2'] = null;
});

/**
 * 排序
 */
function listSortTab() {

    if ($('.sort-icon').length > 0) {
        $('.sort-icon').on('click', function () {
            $('.sort-content-box').slideDown();
        });
        $('.sort-mask').on('click', function () {
            $('.sort-content-box').slideUp();
        });
        $('.sort-content').on('click', 'li', function () {
            $(this).addClass('current').siblings().removeClass('current');

            req['sort'] = $(this).val();
            var _optionParams = joinParams(req, true);

            location.href=_localHref + _optionParams;

            $('.sort-content-box').slideUp();
        })
    }
}


function arrayPush(url,pageNum) {
    // if (url.indexOf('xiaoqu')>-1){
    //     plotPageNums.push(pageNum)
    // }
    // if (url.indexOf('esf')>-1){
    //     esfPageNums.push(pageNum)
    // }
    // if (url.indexOf('loupan')>-1){
    //     newPageNums.push(pageNum)
    // }
    if (url.indexOf('zufang')>-1){
        rentPageNums.push(pageNum)
    }
}

/**
 * 下拉分页
 * @param pageNumber
 */

function getDefaultPageNum() {
    var hash = window.location.href.split('#');
    hash = hash[1] || '';
    hash = hash.split('&');
    var pageNum=1;
    $.each(hash,function (index,ele) {
        try {
            if (ele.indexOf('pageNum=') == 0) {
                pageNum = parseInt(ele.split('=')[1]) || 1;
                return false;
            }
        }
        catch (e){}
    });
    return pageNum;
}

var pageNumDown = getDefaultPageNum();
var pageNumUp =pageNumDown;
var initLoad_pageNum=pageNumDown;
function setPageNum(page) {
    if (page < 1) {
        return;
    }
    var hash = window.location.href.split('#');
    var path = hash[0];
    hash = hash[1] || '';
    hash = hash.split('&');
    var res = [];
    var hasPageNum = false;
    $.each(hash, function (index, ele) {
        if ($.trim(ele) == '') {
            return true;
        }
        try {
            if (ele.indexOf('pageNum=') == 0) {
                hasPageNum = true;
                ele = 'pageNum=' + page;
            }
        }
        catch (e) {
        }
        res.push(ele);
    });
    if (!hasPageNum) {
        res.push('pageNum=' + page);
    }
    if (!!(window.history && history.replaceState)) {
        window.history.replaceState({}, document.title, path + '#' + res.join('&'));
    } else {
        window.location.replace(path + '#' + res.join('&'));
    }

}


function pullUpAction() {
    $('#result-section').dropload({
        scrollArea : window,
        autoLoad:true,
        domDown : {                                                          // 下方DOM
            domClass   : 'dropload-down',
            // domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
            domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>',
            domNoData  : '<p class="tip-box">有新上房源，我们会及时通知您哦！</p>'
        },
        loadDownFn : function(me){
            var paramData = req;
            paramData['pageNum'] = (initLoad_pageNum==pageNumUp?pageNumUp:pageNumUp+1);

            if (_localHref.indexOf('/zufang') > 0){
                if(("near" in paramData)){
                    if (window["$toutiao_customer_pullUpAction_latlon"]) {
                        paramData["lat"] =window["$toutiao_customer_pullUpAction_latlon"][0] ;
                        paramData["lon"] =window["$toutiao_customer_pullUpAction_latlon"][1];
                        if(hasAnotherParam){
                            delete paramData["lat"];
                            delete paramData["lon"];
                        }
                    }
                }
            }else{
                if (window["$toutiao_customer_pullUpAction_latlon"]) {
                    var hasAnotherParam=false;
                    for(var key in paramData){
                        if(!$.inArray(key,['lat','lon'])){
                            hasAnotherParam = true;
                        }
                    }
                    paramData["lat"] =window["$toutiao_customer_pullUpAction_latlon"][0] ;
                    paramData["lon"] =window["$toutiao_customer_pullUpAction_latlon"][1];
                    if(hasAnotherParam){
                        delete paramData["lat"];
                        delete paramData["lon"];
                    }
                }
            }


            params = joinParams(paramData);

            if (_localHref.indexOf('/loupan') > 0) {
                url = router_city('/loupan') + params;
            } else if (_localHref.indexOf('/esf') > 0) {
                url = router_city('/esf' )+ params;
            } else if (_localHref.indexOf('/xiaoqu') > 0){
                url = router_city('/xiaoqu') + params;
            } else if (_localHref.indexOf('/zufang') > 0){
                url = router_city('/zufang') + params;
            }

            $.ajax({
                type: "get",
                contentType:'application/json',
                url: url,
                async: true,
                dataType:'json',
                success: function (data) {

                    console.log(data.data);
                    localStorage.setItem('listUrl',url);
                    if (data.code == 'success') {
                        //第一次加载
                        if (data.data != null && data.data.total > 0) {
                            if (initLoad_pageNum != pageNumUp) {
                                pageNumUp++;
                                setPageNum(pageNumUp);
                                arrayPush(url,pageNumUp);
                            }
                            else {
                                initLoad_pageNum = -1;
                            }

                            var dataCon = data.data.data || [];
                            for (var i = 0; i < dataCon.length; i++) {
                                if (_localHref.indexOf('loupan') > 0) {
                                    //组织地铁描述信息
                                    if (dataCon[i]['nearsubway']) {
                                        var _subwayArray = dataCon[i]['nearsubway'].split('$');
                                        if (_subwayArray.length > 2) {
                                            var _subwayDesc;

                                            var _distance = parseInt(_subwayArray[2]);
                                            if (_distance > 1000) {
                                                var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + parseFloat(_tempDistance) + "km";
                                            } else {
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + _distance + "m";
                                            }
                                            dataCon[i]['subwayDesc'] = _subwayDesc;
                                        }
                                    }
                                }

                                // 二手房列表单价
                                if (_localHref.indexOf('/esf') > 0) {
                                    var _buildArea = dataCon[i].buildArea;

                                    if (null != _buildArea && _buildArea > 0) {
                                        var unitCost = parseInt((dataCon[i].houseTotalPrices / _buildArea) * 10000);
                                        dataCon[i].unitCost = unitCost;

                                        dataCon[i]['buildArea'] = _buildArea.toFixed(0);
                                    }

                                    //组织地铁描述信息
                                    var _subwayObj = dataCon[i]['subwayDistince'];
                                    var _key = dataCon[i]['key'];
                                    if (_subwayObj && _key) {
                                        var _subwayArray = _subwayObj[_key].split('$');
                                        if (_subwayArray.length > 2) {
                                            var _subwayDesc;

                                            var _distance = parseInt(_subwayArray[2]);
                                            if (_distance > 1000) {
                                                var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + parseFloat(_tempDistance) + "km";
                                            } else {
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + _distance + "m";
                                            }
                                            dataCon[i]['subwayDesc'] = _subwayDesc;
                                        }
                                    }
                                }

                                if (_localHref.indexOf('xiaoqu') > 0) {
                                    //组织地铁描述信息
                                    var _subwayObj = dataCon[i]['metroWithPlotsDistance'];
                                    var _key = dataCon[i]['key'];
                                    if (_subwayObj && _key) {
                                        var _subwayArray = _subwayObj[_key].split('$');
                                        if (_subwayArray.length > 2) {
                                            var _subwayDesc;

                                            var _distance = parseInt(_subwayArray[2]);
                                            if (_distance > 1000) {
                                                var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + parseFloat(_tempDistance) + "km";
                                            } else {
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + _distance + "m";
                                            }
                                            dataCon[i]['subwayDesc'] = _subwayDesc;
                                        }
                                    }
                                }

                                if (_localHref.indexOf('zufang') > 0) {
                                    //组织地铁描述信息
                                    if (dataCon[i]['nearsubway']) {
                                        var _subwayArray = dataCon[i]['nearsubway'].split('$');
                                        if (_subwayArray.length > 2) {
                                            var _subwayDesc;

                                            var _distance = parseInt(_subwayArray[2]);
                                            if (_distance > 1000) {
                                                var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + parseFloat(_tempDistance) + "km";
                                            } else {
                                                _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                    + _distance + "m";
                                            }
                                            dataCon[i]['subwayDesc'] = _subwayDesc;
                                        }
                                    }
                                }
                            }
                        }

                        if(data.data==null){
                            me.lock();

                            // 无数据
                            me.noData();
                        }else{
                            if (data.data.total <= 0) {

                                // $('.tip-box').removeClass('none');
                                // 锁定
                                me.lock();
                                // 无数据
                                me.noData();
                            }
                        }

                        var html = template('listContent', data.data);
                        $('#valueList ').append(html);
                        // setTimeout(function () {
                        //     $(document).scrollTop(0);
                        // },500)

                        me.opts.hasfirstload = true;
                        // 每次数据插入，必须重置
                        me.resetload();
                    }
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        }
    });
}

/**
 * 上拉分页
 * @param pageNumber
 */
function pullDownAction() {
    // $(window).scroll(function() {
    //     if($(window).scrollTop()==0){
    //
    //     }
    // })
    // if (href.length>1){
    //     name = href[href.length-1].split('=')[0]
    //     if(name =='pageNum'){
    //         pageNum = parseInt(href[href.length-1].split('=')[1])-1
    //     }
    // }
    if (pageNumDown > 1) {
        $('#result-section').dropload({
            scrollArea: window,
            autoLoad: false,
            domUp: {
                domClass: 'dropload-up',
                domRefresh: '<div class="dropload-refresh">↓下拉刷新</div>',
                domUpdate: '<div class="dropload-update">↑释放更新</div>'
            },
            loadUpFn: function (me) {
                var paramData = req;
                paramData['pageNum'] = pageNumDown - 1;
                params = joinParams(paramData);
                if (_localHref.indexOf('/loupan') > 0) {
                    url = router_city('/loupan') + params;
                } else if (_localHref.indexOf('/esf') > 0) {
                    url = router_city('/esf') + params;
                } else if (_localHref.indexOf('/xiaoqu') > 0) {
                    url = router_city('/xiaoqu') + params;
                } else if (_localHref.indexOf('/zufang') > 0) {
                    url = router_city('/zufang') + params;
                }
                $.ajax({
                    type: "get",
                    contentType: 'application/json',
                    url: url,
                    async: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 'success') {
                            pageNumDown--;
                            setPageNum(pageNumDown);
                            arrayPush(url,pageNumDown);
                            if (pageNumDown < 2) {
                                me.lock()
                            }

                            if (data.data != null) {
                                var dataCon = data.data.data || [];
                                for (var i = 0; i < dataCon.length; i++) {
                                    if (_localHref.indexOf('loupan') > 0) {
                                        //组织地铁描述信息
                                        if (dataCon[i]['nearsubway']) {
                                            var _subwayArray = dataCon[i]['nearsubway'].split('$');
                                            if (_subwayArray.length > 2) {
                                                var _subwayDesc;

                                                var _distance = parseInt(_subwayArray[2]);
                                                if (_distance > 1000) {
                                                    var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + parseFloat(_tempDistance) + "km";
                                                } else {
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + _distance + "m";
                                                }
                                                dataCon[i]['subwayDesc'] = _subwayDesc;
                                            }
                                        }
                                    }

                                    // 二手房列表单价
                                    if (_localHref.indexOf('/esf') > 0) {
                                        var _buildArea = dataCon[i].buildArea;

                                        if (null != _buildArea && _buildArea > 0) {
                                            var unitCost = parseInt((dataCon[i].houseTotalPrices / _buildArea) * 10000);
                                            dataCon[i].unitCost = unitCost;

                                            dataCon[i]['buildArea'] = _buildArea.toFixed(0);
                                        }

                                        //组织地铁描述信息
                                        var _subwayObj = dataCon[i]['subwayDistince'];
                                        var _key = dataCon[i]['key'];
                                        if (_subwayObj && _key) {
                                            var _subwayArray = _subwayObj[_key].split('$');
                                            if (_subwayArray.length > 2) {
                                                var _subwayDesc;

                                                var _distance = parseInt(_subwayArray[2]);
                                                if (_distance > 1000) {
                                                    var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + parseFloat(_tempDistance) + "km";
                                                } else {
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + _distance + "m";
                                                }
                                                dataCon[i]['subwayDesc'] = _subwayDesc;
                                            }
                                        }
                                    }

                                    if (_localHref.indexOf('xiaoqu') > 0) {
                                        //组织地铁描述信息
                                        var _subwayObj = dataCon[i]['metroWithPlotsDistance'];
                                        var _key = dataCon[i]['key'];
                                        if (_subwayObj && _key) {
                                            var _subwayArray = _subwayObj[_key].split('$');
                                            if (_subwayArray.length > 2) {
                                                var _subwayDesc;

                                                var _distance = parseInt(_subwayArray[2]);
                                                if (_distance > 1000) {
                                                    var _tempDistance = parseFloat(_distance / 1000).toFixed(1);
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + parseFloat(_tempDistance) + "km";
                                                } else {
                                                    _subwayDesc = "距离" + _subwayArray[1] + "[" + _subwayArray[0] + "] "
                                                        + _distance + "m";
                                                }
                                                dataCon[i]['subwayDesc'] = _subwayDesc;
                                            }
                                        }
                                    }
                                }
                            }

                            if (data.data == null) {
                                me.lock();
                                // 无数据
                                me.noData();
                            } else {
                                if (dataCon.length <= 0) {
                                    // $('.tip-box').removeClass('none');
                                    // 锁定
                                    me.lock();
                                    // 无数据
                                    me.noData();
                                }
                            }

                            var html = template('listContent', data.data);
                            $('#valueList li:first-child').before(html);
                            // 每次数据插入，必须重置
                            me.resetload();
                        }
                    },
                    error: function (xhr, type) {
                        alert('Ajax error!');
                        // 即使加载出错，也得重置
                        me.resetload();
                    }
                });
                me.resetload();
            }
        });
    }
}