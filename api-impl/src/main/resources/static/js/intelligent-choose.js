$(function () {
    $('#superContainer').fullpage({
        fitToSection: true,
        resize: false,
        onLeave: function (index, nextIndex, direction) {
            if (nextIndex == 4 && direction == 'down') {
                console.log(joinParams(options));
                $.ajax({
                    type: "GET",
                    async: true,
                    url: router_city('/findhouse/showUserPortrayal'),
                    data: options,
                    success: function (dataInfo) {
                         console.log(dataInfo.data);
                         $("#button_report").attr("href", router_city('/findhouse/showMyReport/') + dataInfo.data);
                    }
                })
            }
        }
    });
    $.fn.fullpage.setAllowScrolling(false, 'up, down');

    $('.begin').on('click', function () {
        $.fn.fullpage.moveSectionDown();
    });

    chooseUserType();   // 用户选择类型

    chooseUserFinds();  // 用户开启智能找房之旅
});

/**
 * 筛选条件对象
 * @type {Object}
 */
var options = new Object();

/**
 * Step1:Choose user type
 */
function chooseUserType() {
    $('.choose-wrapper').on('click', '.choose-item-box', function () {
        $('.choose-wrapper').find('.choose-item-box').removeClass('current');
        $(this).addClass('current');
    });
    $('#userTypeSubmit').on('click', function () {
        if ($('.choose-wrapper').find('.current').length > 0) {
            $.fn.fullpage.moveSectionDown();
            var userTypeParams = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');

            options['userType'] = userTypeParams;

            $.ajax({
                type: "GET",
                url: router_city('/findhouse/xuanzeleixing'),
                data: options,
                success: function (data) {
                    // console.log(data);
                }
            });
        }
    });
}

function chooseUserFinds() {
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
        function tt(evt) {
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
        }
        $(document).on('touchmove', tt);
        $(document).on('touchend', function (evt) {
            $(document).unbind('touchmove',tt);
            ele.unbind('touchstart');
        })
    }

    /**
     * 切换预算
     * */
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
    /**
     * 提交预算
     * */
    $('#submitPrice').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.result-begin').addClass('none');
        $('.result-container').removeClass('none');
        if (3 == options['userType']) {
            $('.start-btn').removeClass('none');
            $.fn.fullpage.setAllowScrolling(true, 'down');
            $('.list-item').find('li').eq(0).removeClass('current').addClass('choose-end');
        } else {
            $.fn.fullpage.setAllowScrolling(false, 'up, down');
            $('.list-item').find('li').eq(0).removeClass('current').addClass('choose-end').next().addClass('current');
        }

        if ($('.total-price').hasClass('current')) {//choose totalPrice
            var priceInit = $('.total-conent').find('.slide-text').text();
            var totalPrice = priceInit.substr(0, priceInit.length-1);
            options['preconcTotal'] = totalPrice;
            var totalPriceHtml = '<p><span>总价：<em>'+ priceInit +'</em></span></p>';
            $('.list-item').find('li').eq(0).find('.result-animate').html(totalPriceHtml);

            $.ajax({
                type: "GET",
                url: router_city('/findhouse/goCheckPrice'),
                data: options,
                success: function (data) {
                    $('#plot_Count').find('em').text(data.data.plotCount);
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    //将第七种画像附给当前用户userPortrayalType
                    if (options['userType'] == 3) {
                        options['userPortrayalType'] = 7;
                    }
                }
            });
            return
        } else {//choose monthPrice
            var payInit = $('.down-slide').find('.slide-text').text();
            var payPrice = payInit.substr(0,payInit.length-1);
            var monthPrice = $('.month-slide').find('.slide-text').text();

            options['downPayMent'] = payPrice;
            options['monthPayMent'] = monthPrice;
            var payPriceHtml = '<p><span>首付：<em>'+ payInit +'</em></span><span>月供：<em>' + monthPrice +'</em></span></p>';
            $('.list-item').find('li').eq(0).find('.result-animate').html(payPriceHtml);

            $.ajax({
                type: "GET",
                url: router_city('/findhouse/goCheckPrice'),
                data: options,
                success: function (data) {
                    $("#plot_Count").find('em').html(data.data.plotCount);
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    //将第七种画像附给当前用户userPortrayalType
                    if (options['userType'] == 3) {
                        options['userPortrayalType'] = 7;
                    }
                }
            });
        }
    });

    /**
     * 选择户型
     * */
    $('.content-list').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });
    /**
     * 提交户型
     * */
    $('#submitHouseType').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.list-item').find('li').eq(1).removeClass('current').addClass('choose-end').next().addClass('current');
        options['layOut'] = $('#layOut').find('li.current').data('layout');
        var layOutHtml = '<p><span>'+ $('#layOut').find('li.current').find('span').text() +'</span></p>';
        $('.list-item').find('li').eq(1).find('.result-animate').html(layOutHtml);

        $.ajax({
            type: 'GET',
            url: router_city('/findhouse/userCheckCategoryPage'),
            data: options,
            success: function (data) {
                var ratio = new Number(data.data.ratio);
                $('#plot_Count').find('em').text(data.data.plotCount);
                $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                if (data.data.distictInfo != null) {
                    $('#option_distict').find('li.disabled').each(function (i, orgin) {
                        $(data.data.distictInfo).each(function (index, item) {
                            if ($(orgin).data('value') == item.districtId) {
                                $(orgin).removeClass('disabled').addClass('optional');
                            }
                        });
                    });
                }
            }
        })
    });

    /**
     * 选择区域
     * */
    $('.area-content').on('click', 'li.optional', function () {
        $(this).addClass('current').removeClass('optional');
        var currentChoose = $('.area-content').find('li.current').length;
        if (currentChoose > 2) {
            $('.area-content').find('li:not(.current)').removeClass('optional').addClass('disabled');
            $('#submitArea').removeClass('disabled');
        }
    });
    /**
     * 提交选中区域
     * */
    $('#submitArea').on('click', function () {
        if (!$(this).hasClass('disabled')) {
            $(this).parents('.layer').addClass('none');
            if (options['layOut'] == 1) {
                $('.list-item').find('li').eq(2).removeClass('current').addClass('choose-end');
                $('.start-btn').removeClass('none');
                $.fn.fullpage.setAllowScrolling(true, 'down');
            } else {
                $('.list-item').find('li').eq(2).removeClass('current').addClass('choose-end').next().addClass('current');
            }
            var currentOptinos = $('#option_distict').find('li.current');
            var districtIdStr = [];
            var districtNameStr = [];
            for (var i = 0; i < currentOptinos.length; i++) {
                districtIdStr.push($(currentOptinos[i]).data('value'));
                districtNameStr.push($(currentOptinos[i]).text());
            }
            options['districtId'] = districtIdStr.join();


            var districtHtml = '<p><span>'+ districtNameStr.join(' ') +'</span></p>';
            $('.list-item').find('li').eq(2).find('.result-animate').html(districtHtml);

            $.ajax({
                type: 'GET',
                url: router_city('/findhouse/queryPlotCountByDistrict'),
                data: options,
                success: function (data) {
                    var ratio = new Number(data.data.ratio);
                    $('#plot_Count').find('em').text(data.data.plotCount);
                    $('#plot_Ratio').find('em').text(ratio.toFixed(1) + '%');
                    if (options['layOut'] == 1) {
                        options['schoolFlag'] = 0;
                        options['hospitalFlag'] = 0;
                    }
                }
            })
        }
    });
    /**
     * 修改预算/重置
     * */
    $('.modify-reset').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.result-begin').removeClass('none');
        $('.result-container').addClass('none');
        $('.start-btn').addClass('none');
        $('.list-item').find('li').attr('class','').eq(0).addClass('current');
        var tempUserType = options['userType'];
        options = new Object();
        options['userType'] = tempUserType;
        $('.month-slide').on('touchstart', '.slider-thumb', function (evt) {
            slide($(this), evt, '')
        });
        $('.down-slide').on('touchstart', '.slider-thumb', function (evt) {
            slide($(this), evt, '万')
        });
        $('.total-slide').on('touchstart', '.slider-thumb', function (evt) {
            slide($(this), evt, '万')
        });

        $('.result-animate').html();
    });
    /**
     * 选择家庭结构
     * */
    $('#family-box').on('click', 'li', function () {
        $(this).addClass('current').siblings().removeClass('current');
    });
    /**
     * 提交家庭结构内容
     * */
    $('#submitFamily').on('click', function () {
        $(this).parents('.layer').addClass('none');
        $('.list-item').find('li').eq(3).removeClass('current').addClass('choose-end');
        $('.start-btn').removeClass('none');
        $.fn.fullpage.setAllowScrolling(true, 'down');

        options['childParams'] = $('#hasChild').find('li.current').data('child');
        options['oldManParams'] = $('#oldMan').find('li.current').data('old-man');

        var familyHtml = '<p><span>孩子：<em>'+ $('#hasChild').find('li.current').find('span').text() +'</em></span>' +
            '<span>老人：<em>' + $('#oldMan').find('li.current').find('span').text() +'</em></span></p>';
        $('.list-item').find('li').eq(3).find('.result-animate').html(familyHtml);
    });
}

function router_city(urlparam) {
    urlparam = urlparam || '';
    if (urlparam[0] != '/') {
        urlparam = '/' + urlparam
    }
    var uri = new URI(window.location.href);
    var segmens = uri.segment();
    var city = '';
    if (segmens.length > 0) {
        city = '/' + segmens[0]
    }
    return city + urlparam
}

/**
 * 拼接参数
 * @param req
 * @param sortFlag 非排序时排序置空
 * @returns {string}
 */
function joinParams(req) {
    var targetUrl = '';

    for (var key in req) {
        if (null != req[key]) {
            targetUrl += '&' + key + "=" + req[key];
        }
    }

    if (targetUrl.length > 1) {
        targetUrl = '?' + targetUrl.substring(1);
    }

    return targetUrl;
}