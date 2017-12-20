$(function () {
    if ($('.type-tab-box').hasClass('none')) {
        $('.search-content').addClass('only');
    }

    searchBarState();   // 搜索状态

    historyLocalStorage();  // 历史记录
});

function searchBarState() {
    $('.current-item').on('click', function () {
        $(".type-menu-box").slideToggle(100);
    });

    $('.type-menu').on('click', 'span', function () {
        $(this).addClass('current').siblings().removeClass('current');
        $('.current-item').text($(this).text());
        $(".type-menu-box").slideUp(100);
    });
}

function historyLocalStorage() {
    var hisTime,    // 获取搜索时间数组
        hisItem;    // 获取搜索内容数组

    function init() {
        hisTime = [];   // 时间数组置空
        hisItem = [];   // 内容数组置空

        for (var i = 0; i < localStorage.length; i++) { // 数据去重
            if (!isNaN(localStorage.key(i))) {
                hisTime.push(localStorage.key(i));
            }
        }

        if (hisTime.length > 0) {
            hisTime.sort(); // 排序
            for (var y = 0; y < hisTime.length; y++) {
                localStorage.getItem(hisTime[y]).trim() && hisItem.push(localStorage.getItem(hisTime[y]));
            }
        }

        $('.history').html(''); // 执行init(),每次清空之前添加的节点
        for (var i = 0; i < hisItem.length; i++) {
            $('.history').prepend('<p class="word-break">' + hisItem[i] + '</p>');
        }
    }

    init(); // 调用

    $('.search-btn').on('click', function () {
        var value = $('.key-words').val();
        var time = (new Date()).getTime();

        if (!value) {
            return false;
        }

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
    });

    // 清除记录
    $('.clear-icon').on('click', function () {
        var f = 0;
        for (; f< hisTime.length; f++) {
            localStorage.removeItem(hisTime[f]);
        }
        init();
    });

    $('.history').on('click', '.word-break', function () {
        var text = $(this).text();
        $('.key-words').val(text);
    })
}