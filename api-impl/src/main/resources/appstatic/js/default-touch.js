var idwidth;
$(function() {
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
        FastClick.attach(document.body);
    }
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
    var startX, startY, moveEndX, moveEndY, swipeX, swipeY, base, status = false;
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
        status = false;

        $('body').on('touchmove', function(e) {
            moveEndX = e.originalEvent.changedTouches[0].pageX;
            moveEndY = e.originalEvent.changedTouches[0].pageY;
            var Y = moveEndY - startY;
            // e.stopPropagation();
            var element = document.elementFromPoint(e.originalEvent.touches[0].clientX, e.originalEvent.touches[0].clientY);
            if ($(element).parents().is('.tilelist')) {
                status = false;
            } else {
                status = true;
            }
            if (Math.abs(moveEndX - startX) - Math.abs(moveEndY - startY) > 0) { // 左右滑动
                if (status) {
                    e.preventDefault(); // 阻止浏览器默认事件
                }
                swipeY = false;
            } else if (swipeY && Math.abs(moveEndX - startX) - Math.abs(moveEndY - startY) < 0) { // 上下滑动
                status = false;
                base = Y / 1000 + 1;
                var endWidth = idwidth * base;
                var endHeight = idHeight * base;
                if (Y < 0) {
                    Y = 0;
                }
                var endLeft = (idWidth * (Y / 1000) * 0.5) * -1;

                if (endWidth < idWidth) {
                    endWidth = idWidth
                }
                if (endHeight < idHeight) {
                    endHeight = idHeight
                }
                if ($(document).scrollTop() <= 0) {
                    $('.scaleImg').css({
                        'width': endWidth,
                        'height': endHeight,
                        'margin-left': endLeft
                    });
                }
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