
var uu = $('#url');
var BaseUrl=uu.val();
$(function () {
    describeAllShow();      // 描述展示全部

    detailContactState();   // 分享，收藏，咨询展示状态

    carouselSwiper();       // 顶部swiper轮播点击弹层photoswipe轮播

    houseTypeState();       // 户型类型切换

    textSlider();           // 首页头条公告滚动
    
    $('.header-user').click(function () {
        userSideNav();      // 个人中心导航
    });

    moduleExpand();         // 小区详情模块状态

    marketsState();         // 小区市场详情切换

    listSortTab();          // 列表页排序切换
});

function describeAllShow() {
    if ($('.describe-cont').length) {
        var describeAllContent = $('.describe-cont p').text();
        $('.describe-cont p').text(describeAllContent.substr(0,56));
        $('.describe-show-btn').click(function () {
            $(this).hide();
            $('.describe-cont p').text(describeAllContent);
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
    if ($('.swiper-container').length){
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
            pagination : '.swiper-pagination',
            paginationType : 'fraction'
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
            pagination : '.swiper-pagination'
        })
    }
}

function initphoto(a,i) {
    if(typeof i == 'undefined'){
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
    if ($('.house-type-state').length){
        $('.house-type-state').on('click','span',function () {
            $(this).addClass('current').siblings().removeClass('current');
          /* location.href="/newhouse/getNewHouseLayoutCountByRoom?id="+$(this).data('bid')+"&&tags="+$(this).data('id');*/
            $('#all-type').children("section").siblings().hide();
            if($(this).data('id')=="0"){
                $('#all-type').children("section").siblings().show();
            }else if ($(this).data('id')=="2"){
                $(".room2").show();
            }else if ($(this).data('id')=="3"){
                $(".room3").show();
            }else if ($(this).data('id')=="4"){
                $(".room4").show();
            }else if ($(this).data('id')=="5"){
                $(".room5").show();
            }else if ($(this).data('id')=="1"){
                $(".room1").show();
            }

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
            $ul.addClass('animate').css('-webkit-transform','translateY(-' + $lilength*(num) + 'px)');
            setTimeout(function () {
                if (num == $length) {
                    $ul.removeClass('animate').css('-webkit-transform','translateY(0)');
                    num = 0;
                }
            },300)
        },3000);
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
            if(BaseUrl=="/findVillageByConditions"){
                location.href=BaseUrl+'?sort='+$(this).val();
            }else {
                location.href=BaseUrl+'?sort='+$(this).val();
            }
            $('.sort-content-box').slideUp();
        })
    }
}