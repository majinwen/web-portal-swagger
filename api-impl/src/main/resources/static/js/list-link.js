var req = GetRequest();
var pageNum = 2;
$(function () {

    /**
     * 下滑翻页
     */
    $(window).scroll(function () {
        if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
            showNextPage(pageNum);
        }
    });


    $('#category-tab').on('click','li', function () {
        var $dom = getDataDom($(this),'panel');
        $(this).toggleClass('current').removeClass('choose').siblings().removeClass('current');
        $dom.toggleClass('active').siblings().removeClass('active');

        if ($dom.hasClass('active')) {
            if ($('#level2').is(':hidden') && $dom.attr('data-mark') == 'panel-place') {
                $('#level1').find('li').removeClass('current');
                $('#district-option').addClass('current');
                showDistrict();
            }
        }
        //有区域条件
        if (req['districtId']) {
            $('#level1').find('li').removeClass('current');
            $('#district-option').addClass('current');
            showDistrict(req['districtId'], req['areaId']);
        }

        //有线路条件
        if (req['subwayLineId']) {
            $('#level1').find('li').removeClass('current');
            $('#subway-option').addClass('current');
            showSubway(req['subwayLineId'], req['subwayStationId']);
        }
        if ($('#category-tab').find('.current').length <= 0) {
            $('.global-mark').addClass('none');
            $('body').removeClass('fixed-scroll');
        } else {
            $('.global-mark').removeClass('none');
            $('body').addClass('fixed-scroll');
        }
    });

    /**
     * 区域商圈筛选
     */
    $('#district-option').on('click', function () {
        showDistrict(req['districtId'], req['areaId']);
    });

    /**
     * 地铁筛选
     */
    $('#subway-option').on('click', function () {
        showSubway(req['subwayLineId'], req['subwayStationId']);
    });

    /**
     * 区域商圈筛选
     */
    if (req['districtId'] || req['areaId']) {
        districtArea(req);
        showDistrict(req['districtId'], req['areaId']);
    };

    /**
     * 地铁筛选
     */
    if (req['subwayLineId'] || req['subwayStationId']) {
        subway(req);
    };

    /**
     * 价格筛选
     */
    if (req['beginPrice'] && req['endPrice']) {
        $('.price-list li:gt(0)').each(function () {

            if (req['beginPrice'] == $(this).data('begin-price') && req['endPrice'] == $(this).data('end-price')) {
                $('.price-list li').removeClass('current');
                $(this).addClass('current');

                $('li[data-mark="tab-price"]').addClass('choose').find('em').html($(this).html());
            }
        });
    };

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
                        $('li[data-mark="tab-type"]').addClass('choose').find('em').html('多选');
                    } else {
                        $('li[data-mark="tab-type"]').addClass('choose').find('em').html($(this).html());
                    }
                }
            });
        });

    };

    /**
     * 物业类型筛选--多选
     */
    if (req['propertyTypeId']) {
        moreOption('propertyTypeId');
    };

    /**
     * 面积筛选---多选
     */
    if (req['houseAreaSize']) {
        moreOption('houseAreaSize');
    };

    /**
     * 电梯筛选
     */
    if (req['elevatorFlag']) {
        $('dt[data-type="elevatorFlag"]').next('dd').find('span').each(function () {

            if (req['elevatorFlag'] == $(this).data('info')) {
                $(this).siblings('span').removeClass('current');
                $(this).addClass('current');
            }
        });
    };

    /**
     * 建筑类型---多选
     */
    if (req['buildingType']) {

        moreOption('buildingType');
    };

    /**
     * 销售状态筛选---多选
     */
    if (req['saleType']) {

        moreOption('saleType');
    };

    /**
     * 楼盘特色筛选---多选
     */
    if (req['buildingFeature']) {

        moreOption('buildingFeature');
    };

    /**
     * 装修标准筛选---多选
     */
    if (req['deliverStyle']) {
        moreOption('deliverStyle');
    };

    /**
     * 区域商圈处理
     * @param req
     */
    function districtArea(req) {
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

            $('li[data-mark="tab-place"]').addClass('choose').find('em').html(_circleName?_circleName:_districtName);
        });
    };

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

            $('li[data-mark="tab-place"]').addClass('choose').find('em').html(_subwayStationName?_subwayStationName:_subwayLineName);
        });
    };

    /**
     * 更多筛选项处理
     * @param optionType
     */
    function moreOption(optionType) {
        $('#category-tab').find('li[data-mark="tab-more"]').addClass('choose').find('em').text('多选');

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
});

/**
 * 选中区域条件
 * @param districtid
 * @param e
 */
function submitDirstrict(districtid,e) {
    if (districtid) {
        req['subwayLineId'] = null;
        req['subwayStationId'] = null;
        req['areaId'] = null;
    }
    req['districtId'] = districtid;
    var params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e);
    $.get(url, function () {
        location.href=url;
    });
}
/**
 * 筛选商圈条件
 * @param districtid
 * @param areaId
 * @param e
 */
function submitBussiness(districtid,areaId,e) {
    if (districtid) {
        req['subwayLineId'] = null;
        req['subwayStationId'] = null;
    }
    req['districtId'] = districtid;
    req['areaId'] = areaId;
    params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e);
    $.get(url, function () {
        location.href=url;
    });
};

//区域不限
function submitPlace(e) {
    req['areaId'] = null;
    req['districtId'] = null;
    params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e,'区域');
    $.get(url, function () {
        location.href=url;
    });
}

/*
 * 提交选中地铁线路
 * 站点为不限
 * */
function submitSubwayLine(subwayid,e) {

    if (subwayid) {
        req['districtId'] = null;
        req['areaId'] = null;
        req['subwayStationId'] = null;
    }
   req['subwayLineId']=subwayid
    params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e);
    $.get(url, function () {
        location.href = url;
    });
};


/*
 * 提交选中地铁站点
 * */
function submitStation(subwayid, subwayStationId, e) {
    if (subwayid) {
        req['districtId'] = null;
        req['areaId'] = null;
    }
    req['subwayLineId']=subwayid
    req['subwayStationId']=subwayStationId
    params = joinParams(req);
    url = BaseUrl + params;

    tabTextReplace(e,url);
    $.get(url, function () {
        location.href = url;
    });
};


//地铁不限
function submitSubway(e) {
    req['subwayLineId'] = null;
    req['subwayStationId'] = null;
    params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e,'地铁');
    $.get(url, function () {
        location.href=url;
    });
};


//价格
$('.price-list').on('click','li', function (e) {
    $(this).addClass('current').siblings().removeClass('current');
    var beginPrice = $(this).attr('data-begin-price'),
        endPrice = $(this).attr('data-end-price');
    if(beginPrice!=""||endPrice!=""){
        req['beginPrice']= beginPrice ;
        req['endPrice']=endPrice;
    }else if (beginPrice==""||endPrice==""){
        req['beginPrice']= null;
        req['endPrice']= null;
    }
    params = joinParams(req);
    url = BaseUrl + params;
    tabTextReplace(e,$(this).text());
    $.get(url, function () {
        location.href = url;
    });
});

//户型提交
$('#typeSubmit').on('click', function (e) {
    submitClickState = true;
    var layoutText = $('.type-list').find('li.current').text(),
        chooseType = $('.type-list').find('li.current'),
        layoutTextArr = [];

    if (layoutText == '不限') {
        tabTextReplace(e,layoutText);
        params = joinParams(req);
        url = BaseUrl + params;
        $.get(url, function () {
            location.href=url;
        });
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

    req['layoutId']=layoutTextArr.join(',');
    params = joinParams(req);
    url = BaseUrl + params;
    $.get(url, function () {
        location.href = url;
    });
});

//更多筛选提交
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
            console.log(arr.join());
            console.log(dataType);
            req[dataType]= arr.join().toString();
        }
    });

    if ($('.more-list').find('.current').length > 0) {
        tabTextReplace(e, '多选')
    } else {
        tabTextReplace(e, '更多');
        $('#category-tab').find('li[data-mark="tab-more"]').removeClass('choose');
    }
    params = joinParams(req);
    url = BaseUrl + params;
    $.get(url, function () {
        location.href=url;
    });
});

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
};

function joinParams(req) {
    var targetUrl = '';
    for (var key in req) {
        if (req[key]) {
            targetUrl += '&' + key + "=" + req[key];
        }
    };

    if (targetUrl.length > 1) {
        targetUrl = '?' + targetUrl.substring(1);
    };

    return targetUrl;
};


/**
 * 列表页分页
 * @param pageNum
 */
function showNextPage(pageNumn) {

    var paramData = req;
    paramData['pageNum'] = pageNumn;
    params = joinParams(paramData);

     if(BaseUrl=="/newhouse/searchNewHouse"){
         url ="/newhouse/pageSearchNewHouse" + params;

     }else if (BaseUrl=="/findProjHouseInfo"){

         url ="二手房接口" + params;
     }
      //console.log(paramData)
     console.log(url);

    $.ajax({
        type: "post",
        url: url,
        async: true,
        success: function (data) {
            pageNum+=1;
            //获取异步调用的数据
            if (data.code == 'success') {

                console.log(template)

                var html = template('test',data.data);
              $('#valueList li:last-child').after(html);
            }
         console.log(data);
        }
    });
}



