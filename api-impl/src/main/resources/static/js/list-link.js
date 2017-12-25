$(function () {
    var req = GetRequest();

    console.log(req);

    console.log(joinParams(req));


    $('#category-tab').on('click','li', function () {
        var $dom = getDataDom($(this),'panel');
        $(this).toggleClass('current').removeClass('choose').siblings().removeClass('current');
        $dom.toggleClass('active').siblings().removeClass('active');

        if ($dom.hasClass('active')) {
            if ($('#level2').is(':hidden') && $dom.attr('data-mark') == 'panel-place'){

                //有区域条件
                if (req['districtId']) {
                    $dom.find('#level1').children().eq(0).addClass('current');
                    showDistrict(req['districtId'], req['areaId']);
                }

                //有线路条件
                if (req['subwayLineId']) {
                    $dom.find('#level1').children().eq(1).addClass('current');
                    showSubway(req['subwayLineId'], req['subwayStationId']);
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

                $('li[data-mark="tab-price"]').addClass('current').find('em').html($(this).html());
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
                        $('li[data-mark="tab-type"]').addClass('current').find('em').html('多选');
                    } else {
                        $('li[data-mark="tab-type"]').addClass('current').find('em').html($(this).html());
                    }
                }
            });
        });

    };

    /**
     * 物业类型筛选--多选
     */
    if (req['propertyTypeId']) {

        var propertyTypeArray = req['propertyTypeId'].split(',');

        $('dt[data-type="propertyTypeId"]').next('dd').find('span').removeClass('current');
        $.each(propertyTypeArray, function (index, item) {

            $('dt[data-type="propertyTypeId"]').next('dd').find('span').each(function () {
                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
    };

    /**
     * 面积筛选---多选
     */
    if (req['houseAreaSize']) {

        var houseAreaArray = req['houseAreaSize'].split(',');

        $('dt[data-type="houseAreaSize"]').next('dd').find('span').removeClass('current');
        $.each(houseAreaArray, function (index, item) {

            $('dt[data-type="houseAreaSize"]').next('dd').find('span').each(function () {

                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
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
        var buildingTypeArray = req['buildingType'].split(',');

        $('dt[data-type="buildingType"]').next('dd').find('span').removeClass('current');
        $.each(buildingTypeArray, function (index, item) {

            $('dt[data-type="buildingType"]').next('dd').find('span').each(function () {
                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
    };

    /**
     * 销售状态筛选---多选
     */
    if (req['saleType']) {
        var saleTypeArray = req['saleType'].split(',');

        $('dt[data-type="saleType"]').next('dd').find('span').removeClass('current');
        $.each(saleTypeArray, function (index, item) {

            $('dt[data-type="saleType"]').next('dd').find('span').each(function () {
                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
    };

    /**
     * 楼盘特色筛选---多选
     */
    if (req['buildingFeature']) {
        var buildingFeatureArray = req['buildingFeature'].split(',');

        $('dt[data-type="buildingFeature"]').next('dd').find('span').removeClass('current');
        $.each(buildingFeatureArray, function (index, item) {

            $('dt[data-type="buildingFeature"]').next('dd').find('span').each(function () {
                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
    };

    /**
     * 装修标准筛选---多选
     */
    if (req['deliverStyle']) {
        var deliverStyleArray = req['deliverStyle'].split(',');

        $('dt[data-type="deliverStyle"]').next('dd').find('span').removeClass('current');
        $.each(deliverStyleArray, function (index, item) {

            $('dt[data-type="deliverStyle"]').next('dd').find('span').each(function () {
                if (item == $(this).data('info')) {
                    $(this).addClass('current');
                }
            });
        });
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

            $('li[data-mark="tab-place"]').addClass('current').find('em').html(_circleName?_circleName:_districtName);
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

                    console.log(_station);
                }
            }

            for (var index in _station) {
                if (_subwayStationId == _station[index].stationid) {
                    _subwayStationName = _station[index]['station_name'];
                }
            }

            $('li[data-mark="tab-place"]').addClass('current').find('em').html(_subwayStationName?_subwayStationName:_subwayLineName);
        });
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
    };

    function joinParams(req) {
        var targetUrl = '';
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
});