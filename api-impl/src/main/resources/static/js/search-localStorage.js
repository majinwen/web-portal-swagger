$(function(){
    var houseTypeChoose = $('.type-menu').find('span.current').index();

    if ($('.type-tab-box').hasClass('none')) {
        $('.searchpage-search-content').addClass('only');
    }
    $('.search-link').on('focus', function () {
        $('.search-page-wrapper').addClass('active');
        $('.key-words').focus();
    });
    $('.searchpage-search-btn').on('click', function () {
        $('.search-page-wrapper').removeClass('active');
    });
    $('.searchpage-current-item').on('click', function () {
        $('.type-menu-box').show();
    });
    $('.searchpage-current-item').text($('.type-menu').find('span.current').text());

    $('.type-menu').on('click', 'span', function () {
        houseTypeChoose = $(this).index();
        $(this).addClass('current').siblings().removeClass('current');
        $('.searchpage-current-item').text($(this).text());

        $('.type-menu-box').hide();
        $('#search-container-wrapper').find('.search-container-item').addClass('none');
        $('#search-container-wrapper').find('.search-container-item').eq(houseTypeChoose).removeClass('none');
    });
    $('#search-container-wrapper').find('.search-container-item').eq(houseTypeChoose).removeClass('none');

    if(BaseUrl =="/findVillageByConditions"){
        $('#plot').addClass('current').siblings().removeClass('current');
    } else  if(BaseUrl =="/findProjHouseInfo"){
        $('#erhouse').addClass('current').siblings().removeClass('current');
    }else  if(BaseUrl =="/newhouse/searchNewHouse"){
        $('#nhouse').addClass('current').siblings().removeClass('current');
    }

   /* if($('#url').val()=="/findVillageByConditions"){
        $('#plot').addClass('current').siblings().removeClass('current');
    } else  if($('#url').val()=="/findProjHouseInfo"){
        $('#erhouse').addClass('current').siblings().removeClass('current');
    }else  if($('#url').val()=="/newhouse/searchNewHouse"){
        $('#nhouse').addClass('current').siblings().removeClass('current');
    }*/




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
            location.href=$('.type-menu>span.current').data( "value" )+$.trim($(this).val())
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