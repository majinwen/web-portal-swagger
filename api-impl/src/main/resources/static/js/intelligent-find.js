$(function () {

    $('.list-item').on('click', 'li', function () {
        if ($(this).hasClass('current')) {
            var index = $(this).index() + 1;
            $(this).addClass('current').siblings().removeClass('current');
            $(this).prev().addClass('choose-end');
            $('.layer' + index).removeClass('none');
        }
    });
    $('.layer').click(function () {
        $('.layer').addClass('none');
    });
    $('.layer-content').click(function (e) {
        e.stopPropagation();
    });
    $('.month-slide').on('touchstart', '.slider-thumb', function (evt) {
        slide($(this), evt, '')
    });
    $('.down-slide').on('touchstart', '.slider-thumb', function (evt) {
        slide($(this), evt, '万')
    });
    $('.total-slide').on('touchstart', '.slider-thumb', function (evt) {
        slide($(this), evt, '万')
    });

    /*
     * 滑块滑动
     *
     * @params ele 当前dom
     * @params evt event 对象
     * @params cm 单位
     * */
    function slide(ele, evt, cm) {
        var thisDom = ele;
        var oldX = evt.originalEvent.targetTouches[0].pageX;
        var trackWidth = thisDom.parent().width();
        var left = parseInt(thisDom.css('left'));
        var sildeColor = thisDom.prevAll('.slide-color');
        var slideText = thisDom.parent().prevAll('.slide-text');
        var price = thisDom.next().children('em').text() - thisDom.prev().children('em').text();

        $(document).on('touchmove', function (evt) {
            var newX = evt.originalEvent.targetTouches[0].pageX - oldX;
            thisDom.css('left', left + newX + "px");
            sildeColor.css('width', left + newX + "px");
            slideText.css('left', left + newX + "px");
            if (parseInt(thisDom.css('left')) < 0) {
                thisDom.css('left', 0);
                slideText.css('left', 0);
                sildeColor.css('width', 0)
            }
            if (parseInt(thisDom.css('left')) > trackWidth) {
                thisDom.css('left', trackWidth + "px");
                slideText.css('left', trackWidth + "px");
                sildeColor.css('width', trackWidth + "px")
            }
            slideText.text(Math.ceil(parseInt(thisDom.css('left')) / trackWidth * price) + cm)
        });
        $(document).on('touchend', function (evt) {
            $(document).unbind('touchmove');
            $(document).unbind('touchstart')
        })
    }

    $('#superContainer').fullpage({
        fitToSection: true,
        resize: false
    });

    $.fn.fullpage.setAllowScrolling(false, 'up, down');

    $('.begin').on('click', function () {
        $.fn.fullpage.moveSectionDown();
    });

    // 用户类型选择
    $('.choose-wrapper').on('click', '.choose-item-box', function () {
        $('.choose-wrapper').find('.choose-item-box').removeClass('current');
        $(this).addClass('current');
    });

    if ($('.choose-wrapper').find('.current').length > 0) {
        $('.button').on('click', function () {
            $.fn.fullpage.moveSectionDown();
        });
    }
    // 提交选中用户类型
    var params;
    $('#userTypeSubmit').on('click', function () {
        if ($('.choose-wrapper').find('.current').length > 0) {
            params = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');
            var userTypeUrl = 'userType=' + params;
            $.fn.fullpage.moveSectionDown();
            $.ajax({
                type: "GET",
                url: router_city('/findhouse/xuanzeleixing'),
                data: userTypeUrl
            });
        }
    });

    // 切换预算
    $('.price-tab').on('click', 'span', function () {
        $(this).addClass('current').siblings().removeClass('current');
        if ($('.total-price').hasClass('current')) {
            $('.total-conent').removeClass('none');
            $('.month-content').addClass('none');
        } else if ($('.month-price').hasClass('current')) {
            $('.total-conent').addClass('none');
            $('.month-content').removeClass('none');
        }
    });
    // 提交预算
    var priceAnduserTppeUrl;
    var userPortrayalType;
    $('#submitPrice').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.result-begin').addClass('none');
        $('.result-container').removeClass('none');
        if ($('.total-price').hasClass('current')) {
            $('.list-item').find('li').eq(0).removeClass('current').addClass('choose-end').next().addClass('current');
            var priceInit = $('.total-conent').find('.slide-text').text();
            var price = priceInit.substr(0, priceInit.length-1);
            priceAnduserTppeUrl = 'userType=' + params + '&preconcTotal=' + price;
            $.ajax({
                type: 'GET',
                url: router_city('/findhouse/goCheckPrice'),
                data: priceAnduserTppeUrl,
                success: function (data) {
                    console.log(data.data);
                    $('#plot_Count').find('em').text(data.data.plotCount);
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    params = data.data.userType;
                    //用户类型是第三类
                    if (params == 3) {
                        ////将第七种画像附给当前用户userPortrayalType
                        userPortrayalType = data.data.userPortrayalType = 7;
                        priceAnduserTppeUrl += '&userPortrayalType=' + userPortrayalType;
                        console.log(priceAnduserTppeUrl);
                        //跳转到过渡页
                        //为实现
                        $('.start-btn').removeClass('none');
                        $.fn.fullpage.setAllowScrolling(true, 'down');
                        $("#button_report").attr("href", router_city('/findhouse/showUserPortrayal?') + priceAnduserTppeUrl);
                    }
                }
            });
            return
        } else {
            $('.list-item').find('li').eq(0).removeClass('current').addClass('choose-end');
            $('.start-btn').removeClass('none');
            $.fn.fullpage.setAllowScrolling(true, 'down');
            var downPriceInit = $('.down-slide').find('.slide-text').text();
            var downPrice = downPriceInit.substr(0,downPriceInit.length-1);
            var monthPrice = $('.month-slide').find('.slide-text').text();
            priceAnduserTppeUrl = 'userType=' + params + '&downPayMent=' + downPrice + '&monthPayMent=' + monthPrice;
            $.ajax({
                type: 'GET',
                url: router_city('/findhouse/goCheckPrice'),
                data: priceAnduserTppeUrl,
                success: function (data) {
                    $("#plot_Count").find("em").html(data.data.plotCount);
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    params = data.data.userType;
                    //用户类型是第三类
                    if (params == 3) {
                        ////将第七种画像附给当前用户userPortrayalType
                        userPortrayalType = data.data.userPortrayalType = 7;
                        priceAnduserTppeUrl += '&userPortrayalType=' + userPortrayalType;
                        console.log(priceAnduserTppeUrl);
                        //跳转到用户画像页面
                        //为实现
                        $('.start-btn').removeClass('none');
                        $.fn.fullpage.setAllowScrolling(true, 'down');
                        $("#button_report").attr("href", router_city('/findhouse/showUserPortrayal?') + priceAnduserTppeUrl);
                    }
                }

            });
        }
    });

    // 选择户型
    $('.content-list').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });
    // 提交户型
    var next2;
    var prevParams;
    $('#submitHouseType').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.list-item').find('li').eq(1).removeClass('current').addClass('choose-end').next().addClass('current');
        var params = $('#layOut').find('li.current').data('layout');
        var layOut = '&layOut=' + params;
        next2 = priceAnduserTppeUrl + layOut;
        $.ajax({
            type: 'GET',
            url: router_city('/findhouse/userCheckCategoryPage'),
            data: next2,
            success: function (data) {
                console.log(data.data);
                $("#plot_Count").find("em").html(data.data.plotCount);
                var ratio = new Number(data.data.ratio);
                $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                params = data.data.userType;
                prevParams = next2 + '&hospitalFlag=' + data.data.hospitalFlag + '&schoolFlag=' + data.data.schoolFlag + '&ratio=' + data.data.ratio;
                console.log('next2begin=' + next2);
                //区域数组
                var str = "";
                if (data.data.distictInfo != null) {
                    $('#option_distict').find('li.disabled').each(function(i,orgin){

                            $(data.data.distictInfo).each(function (index, item) {
                                if($(orgin).data("value")==item.districtId ){
                                    $(orgin).removeClass('disabled').addClass('optional');
                                }
                            })
                        }
                    );
                }
            }
        });
    });

    // 选择区域
    $('.area-content').on('click', 'li.optional', function () {
        $(this).addClass('current').removeClass('optional');
        var currentChoose = $('.area-content').find('li.current').length;
        if (currentChoose > 2) {
            $('.area-content').find('li:not(.current)').removeClass('optional').addClass('disabled')
        }
    });
    // 提交选中区域
    var next4 = '';
    $('#submitArea').on('click', function () {
        if ($('.area-content').find('li.current').length == 3) {
            $(this).parents('.layer').addClass('none');
            $('.list-item').find('li').eq(2).removeClass('current').addClass('choose-end').next().addClass('current');
            var currentOptinos = $('#option_distict').find('li.current');
            var districtIdStr = [];
            for (var i = 0; i < currentOptinos.length; i++) {
                districtIdStr.push($(currentOptinos[i]).data('value'));
            }
            next4 += prevParams + '&districtId=' + districtIdStr.join();
            $.ajax({
                type: 'GET',
                url: router_city('/findhouse/queryPlotCountByDistrict'),
                data: next4,
                success: function (data) {
                    $("#plot_Count").find("em").html(data.data.plotCount);
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    //此处要判断是否是一居用户
                    if (data.data.layOut == 1) {
                        //如果是一居用户则直接跳转到过渡页 如果不是则去家庭页面
                        //将医疗配套和学校配套还原成默认状态
                        var schoolFlag = data.data.schoolFlag = 0;
                        var hospitalFlag = data.data.hospitalFlag = 0;
                        next4 += "&schoolFlag=" + schoolFlag + "&hospitalFlag=" + hospitalFlag;
                        //直接跳转到过渡页
                        $('.start-btn').removeClass('none');
                        $.fn.fullpage.setAllowScrolling(true, 'down');
                        $("#button_report").attr("href", router_city('/findhouse/showUserPortrayal?') + next4);
                    }
                }
            });
        }
    });

    // 选择家庭结构
    $('#family-box').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });
    // 提交选中家庭结构内容
    var next3;
    $('#submitFamily').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.list-item').find('li').eq(3).removeClass('current').addClass('choose-end').next().addClass('current');
        $('.start-btn').removeClass('none');
        $.fn.fullpage.setAllowScrolling(true, 'down');
        var childParams = $('#hasChild').find('li.current').data('child');
        var oldManParams = $('#oldMan').find('li.current').data('old-man');
        var familyStructureUrl = '&hasChild=' + childParams + '&oldMan=' + oldManParams;
        next3 = next4 + familyStructureUrl;
        $("#button_report").attr("href", router_city('/findhouse/showUserPortrayal?') + next3);
    });

    $('.modify-reset').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.result-begin').removeClass('none');
        $('.result-container').addClass('none');
        $('.start-btn').addClass('none');
        $('.list-item').find('li').attr('class','').eq(0).addClass('current');
    });
});

function router_city(urlparam) {
    urlparam = urlparam || "";
    if(urlparam[0] != '/'){
        urlparam = '/' + urlparam
    }
    var uri = new URI(window.location.href);
    var segmens = uri.segment();
    var city = "";
    if(segmens.length>0){
        city = "/" + segmens[0]
    }
    return city+urlparam
}