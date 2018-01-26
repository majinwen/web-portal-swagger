var idwidth;
$(function() {
    FastClick.attach(document.body);
    scaleImgInit();
});

$(window).resize(function() {
    scaleImgInit();

});

function scaleImgInit() {
    idwidth = window.document.documentElement.getBoundingClientRect().width;
    if (idwidth / dpr > 540) {
        idwidth = 540 * dpr
    }
    scaleImg(idwidth);
}

function scaleImg(idWidth) {
    var startX, startY, moveEndX, moveEndY, swipeX, swipeY, base, status;
    var idHeight = (idWidth * 3) / 5;

    if (0 != $('.scaleImg').length) {
        $('.scaleImg').width(idWidth + 'px');
        $('.scaleImg').height(idHeight + 'px');
    }

    $('body').on('touchstart', function(e) {
        startX = e.originalEvent.touches[0].pageX;
        startY = e.originalEvent.touches[0].pageY;
        swipeX = true;
        swipeY = true;

        var element = document.elementFromPoint(e.originalEvent.touches[0].clientX, e.originalEvent.touches[0].clientY);

        if ($(element).parents().is('.tilelist')) {
            status = false;
        } else {
            status = true;
        }

        $('body').on('touchmove', function(e) {
            moveEndX = e.originalEvent.changedTouches[0].pageX;
            moveEndY = e.originalEvent.changedTouches[0].pageY;
            var Y = moveEndY - startY;
            if (Math.abs(moveEndX - startX) - Math.abs(moveEndY - startY) > 0) { // 左右滑动
                if (status) {
                    e.preventDefault(); // 阻止浏览器默认事件
                }
                swipeY = false;
            } else if (swipeY && Math.abs(moveEndX - startX) - Math.abs(moveEndY - startY) < 0) { // 上下滑动
                base = Y / 1000 + 1;
                if ($(document).scrollTop() <= 0) {
                    $('.scaleImg').css({
                        'width': idWidth * base,
                        'height': idHeight * base,
                        'margin-left': (idWidth * (Y / 1000) * 0.5) * -1
                    });
                }
                status = false;
                swipeX = false;
            }
        });

    });

    $('body').on('touchend', function(e) {
        $('.scaleImg').css({
            'width': idWidth,
            'height': idHeight,
            'margin-left': 0
        }, 100);

        if (status) {
            e.preventDefault();
        }
    });
}