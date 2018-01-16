/**
 * 当前URL路径
 * @type {string}
 * @private
 */
var idwidth;
$(function () {
    idwidth = window.document.documentElement.getBoundingClientRect().width;
    scaleImg(idwidth);             // 大首页图片拖拽

    //describeAllShow();      // 描述展示全部

    detailContactState();   // 分享，收藏，咨询展示状态

    carouselSwiper();       // 顶部swiper轮播点击弹层photoswipe轮播

    houseTypeState();       // 户型类型切换

    textSlider();           // 首页头条公告滚动
    
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

$(window).resize(function () {
    idwidth = window.document.documentElement.getBoundingClientRect().width;
    if (idwidth / dpr > 540) {
        idwidth = 540 * dpr
    }
    scaleImg(idwidth);
});

function moreInfoClick() {
    $('.module-header-message h3').click(function () {
        $(this).parent().find('a').click();
    });
}

function scaleImg(idWidth) {
    console.log(idWidth);
    if (0 == $('.scaleImg').length) {
        return;
    }
    var mybody = document.getElementsByTagName('body')[0];
    var startX, startY, moveEndX, moveEndY, X, Y, base;
    // var idWidth = document.body.clientWidth;
    var idHeight = (idWidth * 3) / 5;
    $('.scaleImg').height(idHeight + 'px');
    $('.scaleImg').width(idWidth + 'px');
    mybody.addEventListener('touchstart', function(e) {
        // e.preventDefault();
        startX = e.touches[0].pageX;
        startY = e.touches[0].pageY;
    }, false);
    mybody.addEventListener('touchmove', function(e) {
        // e.preventDefault();
        moveEndX = e.changedTouches[0].pageX;
        moveEndY = e.changedTouches[0].pageY;
        X = moveEndX - startX;
        Y = moveEndY - startY;
        if( Math.abs(Y) > Math.abs(X) && Y > 0) {// down
            base = Y / 1000 + 1;
            $('.scaleImg').css({
                'width': idWidth * base,
                'height': idHeight * base,
                'margin-left': (idWidth * (Y / 1000) * 0.5) * -1
            });
        }
        console.log(idHeight);
    });

    mybody.addEventListener('touchend', function(e) {
        $('.scaleImg').animate({
            "width": idWidth,
            "height": idHeight,
            'margin-left': 0
        }, 100);
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
    var newIndexloop;
    if ($('.swiper-container').length) {
        // 详情页
        if (($('#detail-swiper').find('li').not('.swiper-slide-duplicate').length) == 1) {
            bannerloop = false;
            $('#detail-swiper').children('.banner-title').addClass('none');
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

function initphoto(a, i) {

    zhuge.track("小区-点击图片");

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
    var scrollDom = $('.text-scroll'),
        $ul = scrollDom.find('ul'),
        $li = scrollDom.find('li'),
        $length = $li.length,
        $lilength = $li.height(),
        num = 0;

    if (scrollDom.length === 0) {
        return;
    }

    if ($length > 1) {
        $ul.append($li.eq(0).clone());
        setInterval(function () {
            num++;
            $ul.addClass('animate').css('-webkit-transform', 'translateY(-' + $lilength * (num) + 'px)');
            setTimeout(function () {
                if (num == $length) {
                    $ul.removeClass('animate').css('-webkit-transform', 'translateY(0)');
                    num = 0;
                }
            }, 300)
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

    $(".index-esf").click(function () {
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                executed = true;
                lon = r.point.lng;
                lat = r.point.lat;
                var point = new BMap.Point(lon, lat);//创建点坐标
                var gc = new BMap.Geocoder();
                gc.getLocation(point, function (rs) {
                    location.href = router_city('/esf') + "?lat=" + lat + "&lon=" + lon;
                });
            }
            else {
            }
        });
    });
    $(".index-xiaoqu").click(function () {
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                executed = true;
                lon = r.point.lng;
                lat = r.point.lat;
                var point = new BMap.Point(lon, lat);//创建点坐标
                var gc = new BMap.Geocoder();
                gc.getLocation(point, function (rs) {
                    location.href = router_city('/xiaoqu') + "?lat=" + lat + "&lon=" + lon;
                });
            }
            else {
            }
        });
    })

}
