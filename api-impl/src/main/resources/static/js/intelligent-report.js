$(function () {

    pageTurning();  // 页面翻页

    $('.share-button').on('click', function () {
        zhuge.track('分享报告_懂房帝');
        $('.share-pop').removeClass('none');
    });
    $('#off-share').on('click', function () {
        $('.share-pop').addClass('none');
    });
});

function pageTurning() {
    if ($('.page1').hasClass('active')) {
        $('body').addClass('fixed-scroll');
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
        document.addEventListener('touchstart', function (ev) {
            startX = ev.touches[0].pageX;
            startY = ev.touches[0].pageY;
        }, false);

        document.addEventListener('touchend', function (ev) {
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
                $('.plot-title-block').addClass('fixed');
            } else {
                $('.plot-title-block').removeClass('fixed');
            }
        }
    };
}