/**
 * 当前URL路径
 * @type {string}
 * @private
 */
$(function () {

    //describeAllShow();      // 描述展示全部

    detailContactState();   // 分享，收藏，咨询展示状态

    carouselSwiper();       // 顶部swiper轮播点击弹层photoswipe轮播

    houseTypeState();       // 户型类型切换


    $('.header-user').click(function () {
        userSideNav();      // 个人中心导航
    });

    moduleExpand();         // 小区详情模块状态

    marketsState();         // 小区市场详情切换

    showfujian();           //获取用户地理位置

    moreInfoClick();        // 获取更多信息

    $('#more-map-info-new').on('click',function () {
        var hrefurl = $(this).attr('href');
        /*location.replace(hrefurl);*/
        location.href = hrefurl
    });
});

function moreInfoClick() {
    $('.module-header-message h3').click(function () {
        if ($(this).text()=="配套地图"){
            zhuge.track('新房-点击配套地图', {
                '配套内容' : '配套地图'
            });
        }
        $(this).parent().find('a').click();
    });
}

function describeAllShow() {
    if ($('.describe-cont').length) {
        $('.describe-cont p').each(function () {
            $(this).data("orig_desc", $(this).text());
            $(this).text($(this).text().substr(0, 50));
            var p = $(this);

            $(this).siblings('span.describe-show-btn').click(function () {
                $(this).hide();
                p.text(p.data("orig_desc"));
            });
        });
    }
}

function detailContactState() {
    if ($('#detailContactState').length) {
        $('.contact-collect').click(function () {
            $(this).toggleClass('current');
        });
    }
}
function carouselSwiper() {
    var bannerloop;
    var newIndexloop = false;
    if ($('.swiper-container').length) {
        // 详情页
        if (($('#detail-swiper').find('li').not('.swiper-slide-duplicate').length) == 1) {
            bannerloop = false;
            $('#detail-swiper').children('.banner-title').find('.pictrue-index').addClass('none');
        } else {
            bannerloop = true;
        }
        var bannerSwiper = new Swiper('#detail-swiper', {
            autoplay: 2000,//可选选项，自动滑动
            loop: bannerloop,
            pagination: '.swiper-pagination',
            paginationType: 'fraction'
        });

        // 新房首页
        if (($('#index-swiper').find('li').not('.swiper-slide-duplicate').length) == 1) {
            newIndexloop = false;
            $('#index-swiper').children('.swiper-pagination').addClass('none');
        } else {
            newIndexloop = true
        }
        var newIndexSwiper = new Swiper('#index-swiper', {
            autoplay: 2000,//可选选项，自动滑动
            loop: newIndexloop,
            pagination: '.swiper-pagination'
        })
    }
}

function initphoto(a, i, url) {
    if (typeof url != 'undefined') {
        if(url.indexOf("/xiaoqu") > 0){
            zhuge.track("小区-点击图片");
        }else{
            zhuge.track('新房-点击图片');
        }
    }

    if (typeof i == 'undefined') {
        i = 0;
    }
    var b = [];
    var imgW = $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").width();
    var imgH = $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").height();
    $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").each(function (a, e) {
        var c = $(e).attr("data-src");
        $(e).attr("data-title");
        c && b.push({src: c, w: imgW, h: imgH})
    });
    a = document.querySelectorAll(".pswp")[0];
    (new PhotoSwipe(a, PhotoSwipeUI_Default, b, {
        history: false,
        index: i, addCaptionHTMLFn: function (a, b, c) {
            if (!a.title)return b.children[0].innerHTML = "", !1;
            b.children[0].innerHTML = a.title;
            return !0
        }
    })).init()
}

function houseTypeState() {
    if ($('.house-type-state').length) {
        $('.house-type-state').on('click', 'span', function () {
            $(this).addClass('current').siblings().removeClass('current');
            /* location.href="/newhouse/getNewHouseLayoutCountByRoom?id="+$(this).data('bid')+"&&tags="+$(this).data('id');*/
            $('#all-type').children("section").siblings().hide();
            if ($(this).data('id') == "0") {
                $('#all-type').children("section").siblings().show();
            } else {
                var roomDom = document.getElementsByClassName("room" + $(this).data('id'));
                var $roomid = $(roomDom);
                $roomid.show();
            }
            /*else if ($(this).data('id')=="2"){
             $(".room2").show();
             }else if ($(this).data('id')=="3"){
             $(".room3").show();
             }else if ($(this).data('id')=="4"){
             $(".room4").show();
             }else if ($(this).data('id')=="5"){
             $(".room5").show();
             }else if ($(this).data('id')=="1"){
             $(".room1").show();
             }*/

        });
    }
}

function textSlider() {
    if(window["index_lunbo_guanggao"]){
        clearInterval(window["index_lunbo_guanggao"]);
    }
    var scrollDom = $('.text-scroll'),
        $ul = scrollDom.find('ul'),
        $li = scrollDom.find('li'),
        $length = $li.length,
        $lilength = $li.height();

    if (scrollDom.length === 0) {
        return;
    }

    if ($length > 1) {
        window["index_lunbo_guanggao"] = setInterval(function () {
            window["index_lunbo_guanggao"] = (window["index_lunbo_guanggao"] || 0) + 1;
            if(window["index_lunbo_guanggao"]>=$length){
                window["index_lunbo_guanggao"] = 0;
            }
            $ul.addClass('animate').css('-webkit-transform', 'translateY(-' + $lilength * (window["index_lunbo_guanggao"]) + 'px)');
        }, 3000);
    }
}

function userSideNav() {
    $('.scroll-mask').show();
    $('body').addClass('fixed-scroll');
    $('.side-nav-cont').addClass('active');
    setTimeout(maskClick, 500);
}
function maskClick() {
    $('.scroll-mask').click(function () {
        $('.scroll-mask').hide();
        $('body').removeClass('fixed-scroll');
        $('.side-nav-cont').removeClass('active');
    });
}

function moduleExpand() {
    $('.expand-btn').on('click', function () {
        var zginfo = ($(this).attr('data-zg'));
        if(zginfo=='医疗配套'){
            zhuge.track('新房-点击医疗配套', {
                '配套内容' : '医疗配套'
            });
        }else if (zginfo=='生活成本'){
            zhuge.track('新房-点击生活成本', {
                '配套内容' : '生活成本'
            });
        }

        $(this).toggleClass('expand');
        if ($(this).hasClass('expand')) {
            $(this).parent().next('.expand-content').slideDown();
        } else {
            $(this).parent().next('.expand-content').slideUp();
        }
    });
}

function marketsState() {
    $('.price-trend-btn').on('click', function () {
        $(this).addClass('current').siblings().removeClass('current');
        $(this).parent().prev().find('.subtitle').text('价格走势');
        $('.price-trend').removeClass('none');
        $('.supply-contrast').addClass('none');
    });
    $('.supply-contrast-btn').on('click', function () {
        $(this).addClass('current').siblings().removeClass('current');
        $(this).parent().prev().find('.subtitle').text('供需对比');
        $('.supply-contrast').removeClass('none');
        $('.price-trend').addClass('none');
    })
}

function router_city(urlparam) {
    urlparam = urlparam || "";
    if (urlparam[0] != '/') {
        urlparam = '/' + urlparam
    }
    var uri = new URI(window.location.href);
    var segmens = uri.segment();
    var city = "";
    if (segmens.length > 0) {
        city = "/" + segmens[0]
    }
    return city + urlparam
}


/**
 * 获取当前用户坐标
 *
 */
function showfujian() {
    var executed = false;

    // $(".index-esf").click(function () {
    //     // var timeout =  setTimeout(function(){
    //     //     location.href = router_city('/esf');
    //     // },2000);
    //     console.log(111);
    //     zhuge.track('导航_大首页', {'导航名称': '二手房', '页面来源URL': window.location.href});
    //     var geolocation = new BMap.Geolocation();
    //     geolocation.getCurrentPosition(function (r) {
    //         executed = true;
    //         lon = r.point.lng;
    //         lat = r.point.lat;
    //         console.log(lon,lat);
    //         var point = new BMap.Point(lon, lat);//创建点坐标
    //         var gc = new BMap.Geocoder();
    //         gc.getLocation(point, function (rs) {
    //             console.log(lon,lat);
    //             // clearTimeout(timeout);
    //             //console.log(lon,lat);
    //             if(lon==116.40387397 && lat == 39.91488908){
    //                  // location.href = router_city('/esf');
    //             }else{
    //                 location.href = router_city('/esf') + "?lat=" + lat + "&lon=" + lon;
    //             }
    //         });
    //     });
    //
    //
    // });

    $(".index-xiaoqu").click(function () {
        var timeout = setTimeout(function(){
            location.href = router_city('/xiaoqu');
        },2000);
        zhuge.track('导航_大首页', {'导航名称': '小区', '页面来源URL': window.location.href});
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            executed = true;
            lon = r.point.lng;
            lat = r.point.lat;
            clearTimeout(timeout);
            if(lon==116.40387397 && lat == 39.91488908){
                   location.href = router_city('/xiaoqu');
            }else{
                location.href = router_city('/xiaoqu') + "?lat=" + lat + "&lon=" + lon;
            }
        });
    })

}