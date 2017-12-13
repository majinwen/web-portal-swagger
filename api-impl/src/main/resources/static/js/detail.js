Zepto(function () {
    // 描述展示全部
    describeAllShow();

    // 分享，收藏，咨询展示状态
    detailContactState();

    // 顶部swiper轮播点击弹层photoswipe轮播
    carouselSwiper();

    // 户型类型切换
    houseTypeState();

    // 首页头条公告滚动
    textSlider();
});

function describeAllShow() {
    if ($('.describe-cont').length) {
        var describeAllContent = $('.describe-cont p').text();
        $('.describe-cont p').text(describeAllContent.substr(0,59));
        $('.describe-show-btn').click(function () {
            $(this).hide();
            $('.describe-cont p').text(describeAllContent);
        });
    }
}

function detailContactState() {
    if ($('#detailContactState').length) {
        $(window).scroll(function(){
            var winHeight = $(window).height();
            var docHeight = $(document.body).height();
            var scrollTop = $(window).scrollTop();
            if (scrollTop > ((docHeight - winHeight) -5 )) {
                $('.detail-contact-box').addClass('bottom');
            } else {
                $('.detail-contact-box').removeClass('bottom');
            }
        });
        $('.contact-collect').click(function () {
            $(this).toggleClass('current');
        });
    }
}

function carouselSwiper() {
    if ($('.swiper-container').length){

        // 详情页
        var bannerSwiper = new Swiper('.carousel-swiper', {
            autoplay: 2000,//可选选项，自动滑动
            loop: true,
            pagination : '.swiper-pagination',
            paginationType : 'fraction'
        });

        // 新房首页
        var newIndexSwiper = new Swiper('.carousel-swiper', {
            autoplay: 2000,//可选选项，自动滑动
            loop: true,
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

/*
$(function(){
    var scrollDiv = $(".scroll"),
        $ul = scrollDiv.find("ul"),
        $li = scrollDiv.find("li"),
        $length = $li.length,
        $liHeight = $li.height(),
        num = 0;

    if(scrollDiv.length == 0){
        return;
    }

    if($length > 1){
        $ul.append($li.eq(0).clone());
        setInterval(
            function(){
                num++;
                $ul.addClass("animate").css("-webkit-transform","translateY(-"+ $liHeight*(num) +"px)");
                setTimeout(
                    function(){
                        if(num == $length){
                            $ul.removeClass("animate").css("-webkit-transform","translateY(0)");
                            num = 0;
                        }
                    },300);
            },3000);
    }
});*/
