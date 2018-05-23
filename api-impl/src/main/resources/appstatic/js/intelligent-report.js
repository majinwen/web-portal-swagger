$(function () {

    if (location.href.indexOf('#') > 0) {
        $('.page1').removeClass('active');
        $('.page2').addClass('active');
    } else {
        $('.page1').removeClass('none');
    }

    pageTurning();  // 页面翻页

    $('.share-button').on('click', function () {
        zhuge.track('分享报告_懂房帝');
        // $('.share-pop').removeClass('none');
        window.requestHybrid({
            tagname: "sharesdk",
            param: {
                title: '懂房帝',
                text: '懂房帝为您推荐了5个小区',
                url: 'http://appdev.toutiaofangchan.com/bj/findhouse/dongfangdi/',
                imgurl: 'http://m.toutiaofangchan.com/static/images/intelligent/adm_323031383036343130343932.png'
            }
        });
    });
    $('#off-share').on('click', function () {
        $('.share-pop').addClass('none');
    });

    $('.folding-item').click(function() {
        $(this).prevAll().addClass('folding-item-small');
        $(this).nextAll().removeClass('folding-item-small');
        $(this).removeClass('folding-item-small');
    });

    $('.level-slider').on('click', '.level-slider-item', function () {
        var index = $(this).index();
        var parentList = $(this).parent().children();
        for (var i = 0; i<parentList.length; i++) {
            if (index === i) {
                parentList[i].style.zIndex = parentList.length;
            }else if (i < index) {
                parentList[i].style.zIndex = i + 1;
            }else if (i > index) {
                parentList[i].style.zIndex = parentList.length - i;
            }
        }
    });
});

function pageTurning() {
    if ($('.page1').hasClass('active')) {
        $('body').addClass('fixed-scroll');
        // 点击
        function GetSlideDirection(startX, startY, endX, endY) {
            var dy = startY - endY;
            //var dx = endX - startX;
            var result = 0;
            if (dy > 10) { //向上滑动
                result = 1;
            } else { //向下滑动
                result = 2;
            }
            return result;
        }
        //滑动处理
        var startX, startY;
        document.getElementsByClassName('page1')[0].addEventListener('touchstart', function (ev) {
            startX = ev.touches[0].pageX;
            startY = ev.touches[0].pageY;
        }, false);

        document.getElementsByClassName('page1')[0].addEventListener('touchend', function (ev) {
            var endX, endY;
            endX = ev.changedTouches[0].pageX;
            endY = ev.changedTouches[0].pageY;
            var direction = GetSlideDirection(startX, startY, endX, endY);
            switch (direction) {
                case 0:
                    break;
                case 1:
                    // 向上
                    $('.bg1').animate({top: '-100%'}, 500);
                    setTimeout(function () {
                        $('.page1').addClass('none').removeClass('active');
                        $('body').removeClass('fixed-scroll');
                        $('.page2').addClass('active');
                        window.location.replace(location.href + '#active');
                    }, 500);
                    break;
                default:
                    break;
            }
        }, false);
    }
    document.onscroll = function () {
        if ($('.page2').hasClass('active')) {
            var navTop = $('.plot-title-box').offset().top;
            var scrollTop = $(document).scrollTop();
            if (scrollTop > navTop) {
                $('.plot-title-block').removeClass('none');
                $('.plot-title-block').addClass('fixed');
            } else {
                $('.plot-title-block').addClass('none');
                $('.plot-title-block').removeClass('fixed');
            }
        }
    };
}