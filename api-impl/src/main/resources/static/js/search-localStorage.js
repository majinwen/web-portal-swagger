$(function(){
    if(_localHref.indexOf('xiaoqu')>0){
        $('#plot').addClass('current').siblings().removeClass('current');

        $('.search-container-item').addClass('none');
        $('#search-plot').removeClass('none');
    } else if(_localHref.indexOf('esf')>0){
        $('#erhouse').addClass('current').siblings().removeClass('current');
        $('.search-container-item').addClass('none');
        $('#search-erhouse').removeClass('none');
    }else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0){
        $('#nhouse').addClass('current').siblings().removeClass('current');
        $('.search-container-item').addClass('none');
        $('#search-newhouse').removeClass('none');
    }

    var houseTypeChoose = $('.type-menu').find('span.current').index();

    if ($('.type-tab-box').hasClass('none')) {
        $('.searchpage-search-content').addClass('only');
    }

    /**
     * 各列表页搜索框获取焦点
     */
    $('.search-link').on('focus', function () {
        init();
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

        init();
    });
    $('#search-container-wrapper').find('.search-container-item').eq(houseTypeChoose).removeClass('none');

    function init() {

        $('.searchpage-history').html('');

        if(_localHref.indexOf('xiaoqu')>0 || $('#plot').hasClass('current')){

            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];

            var start = (plotStorageArray.length-5)>0?(plotStorageArray.length-5):0;
            var end = plotStorageArray.length;
            for (var i=start;i<end;i++) {
                var _history = plotStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/xiaoqu?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }

        } else if(_localHref.indexOf('esf')>0 || $('#erhouse').hasClass('current')){

            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];

            var start = (esfStorageArray.length-5)>0?(esfStorageArray.length-5):0;
            var end = esfStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = esfStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/esf?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }

        }else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0 || $('#nhouse').hasClass('current')){

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];

            var start = (newHouseStorageArray.length-5)>0?(newHouseStorageArray.length-5):0;
            var end = newHouseStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = newHouseStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/loupan?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }
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
            var _keyword = $('.key-words').val();

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];
            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];
            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];

            if(_localHref.indexOf('xiaoqu')>0 || $('#plot').hasClass('current')){

                plotStorageArray.push(_keyword);
                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
            } else if(_localHref.indexOf('esf')>0 || $('#erhouse').hasClass('current')){
                
                esfStorageArray.push(_keyword);
                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
            }else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0 || $('#nhouse').hasClass('current')){

                newHouseStorageArray.push(_keyword);
                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
            }

        }

        location.href=$('.type-menu>span.current').data( "value" )+$.trim($(this).val());
    }

    /**
     * 清除历史记录
     */
    $('.clear-icon').on('click', function () {
        localStorage.clear();
        init();
    });
    $('.searchpage-history').on('click', '.word-break', function () {
        var text = $(this).text();
        $('.key-words').val(text);
        $('.search-page-wrapper').removeClass('active');
    });
});