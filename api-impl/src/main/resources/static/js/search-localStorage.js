$(function(){

    if ($('.type-tab-box').hasClass('none')) {
        $('.searchpage-search-content').addClass('only');
    }
    $('.search-link').on('focus', function () {
        $('.search-page-wrapper').addClass('active');
    });
    $('.searchpage-search-btn').on('click', function () {
        $('.search-page-wrapper').removeClass('active');
    });
    $('.searchpage-current-item').on('click', function () {
        $('.type-menu-box').show();
    });

    $('.type-menu').on('click', 'span', function () {
        $(this).addClass('current').siblings().removeClass('current');
        $('.searchpage-current-item').text($(this).text());
        $('.type-menu-box').hide();
    });

    var hisTime;	// 获取搜索时间数组
    var hisItem;	// 获取搜索内容数组

    function init() {
        hisTime = [];	// 时间数组置空
        hisItem = [];	// 内容数组置空

        for (var i = 0; i < localStorage.length; i++) {	// 数据去重
            if (!isNaN(localStorage.key(i))) {
                hisTime.push(localStorage.key(i));
            }
        }

        if (hisTime.length > 0) {
            hisTime.sort(sortNumber);		// 排序
            if (hisTime.length > 7) {
                hisTime.length = 7;
            }
            for (var y = 0; y < hisTime.length; y++) {
                localStorage.getItem(hisTime[y]).trim() && hisItem.push(localStorage.getItem(hisTime[y]));
            }
        }

        $('.searchpage-history').html('');		// 执行init(),清空之前添加的节点
        for (var i = 0; i < hisItem.length; i++) {
            $('.searchpage-history').append('<a href="#" class="word-break">' + hisItem[i] + '</a>');
        }
    }
    
    function sortNumber(a, b) {
        return b - a;
    }

    init();

    $('.key-words').on({focus: focusFn, change: changeFn});

    function focusFn() {
        if ($(this).val() != null && $.trim($(this).val()) != '') {
            // 匹配数据
        }
    }

    function changeFn() {

        if ($(this).val() != null && $.trim($(this).val()) != '') {
            // 匹配数据

            // localStorage
            var value = $('.key-words').val();
            var time = (new Date()).getTime();

            if ($.inArray(value, hisItem) >= 0) {
                for (var j = 0; j < localStorage.length; j++) {
                    if (value == localStorage.getItem(localStorage.key(j))) {
                        localStorage.removeItem(localStorage.key(j))
                    }
                }
                localStorage.setItem(time, value);
            } else {
                localStorage.setItem(time, value);
            }
            init();
    /*       alert("http://192.168.1.8:8085"+$('.type-menu>span.current').data( "value")+$.trim($(this).val()))*/
            location.href="http://localhost:8085"+$('.type-menu>span.current').data( "value" )+$.trim($(this).val())

        }
    }
    
    $('.clear-icon').on('click', function () {
        for (var f = 0; f < hisTime.length; f++) {
            localStorage.removeItem(hisTime[f]);
        }
        init();
    });
    $('.searchpage-history').on('click', '.word-break', function () {
        var text = $(this).text();
        $('.key-words').val(text);
        $('.search-page-wrapper').removeClass('active');
    });
});