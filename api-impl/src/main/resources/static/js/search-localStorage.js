$(function(){
    var _localHref = window.location.pathname;

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
    }else {
        $('.search-container-item').addClass('none');
        $('#search-index').removeClass('none');
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
        $('.search-page-wrapper').removeClass('none');
        $('.key-words').focus();
    });
    $('.searchpage-search-btn').on('click', function () {
        $('.search-page-wrapper').addClass('none');
    });
    $('.searchpage-current-item').on('click', function () {
        $('.type-menu-box').show();
    });
    $('.searchpage-current-item').text($('.type-menu').find('span.current').text());

    /*$('.type-menu').on('click', 'span', function () {
        houseTypeChoose = $(this).index();
        $(this).addClass('current').siblings().removeClass('current');
        $('.searchpage-current-item').text($(this).text());

        $('.type-menu-box').hide();
        $('#search-container-wrapper').find('.search-container-item').addClass('none');
        $('#search-container-wrapper').find('.search-container-item').eq(houseTypeChoose).removeClass('none');

        init();
    });*/
    // $('#search-container-wrapper').find('.search-container-item').eq(houseTypeChoose).removeClass('none');

    function init() {

        $('.searchpage-history').html('');

        if(_localHref.indexOf('xiaoqu')>0){

            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];

            var start = (plotStorageArray.length-5)>0?(plotStorageArray.length-5):0;
            var end = plotStorageArray.length;
            for (var i=start;i<end;i++) {
                var _history = plotStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/xiaoqu?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }

        } else if(_localHref.indexOf('esf')>0){

            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];

            var start = (esfStorageArray.length-5)>0?(esfStorageArray.length-5):0;
            var end = esfStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = esfStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/esf?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }

        }else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0){

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];

            var start = (newHouseStorageArray.length-5)>0?(newHouseStorageArray.length-5):0;
            var end = newHouseStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = newHouseStorageArray.pop();
                $('.searchpage-history').append('<a href="' + router_city('/loupan?keyword=' + _history ) + '" class="word-break">' + _history + '</a>')
            }
        } else {
            getLocalstorageMsg('plot');
            getLocalstorageMsg('esf');
            getLocalstorageMsg('newHouse');
        }
    }
    
    function getLocalstorageMsg(styleArray) {
        var indexStorageArray = JSON.parse(localStorage.getItem(styleArray)) || [];
        var start = (indexStorageArray.length-5)>0?(indexStorageArray.length-5):0;
        var end = indexStorageArray.length;
        for (var i = start; i < end; i++) {
            var _history = indexStorageArray.pop();
            var styleText = '';
            var urlText = '';
            if (styleArray == 'plot') {
                urlText = 'xiaoqu';
                styleText = '小区'
            } else if (styleArray == 'esf') {
                urlText = 'esf';
                styleText = '二手房'
            } else if (styleArray == 'newHouse') {
                urlText = 'loupan';
                styleText = '新房'
            }
            $('.searchpage-history').append('<a href="' + router_city('/' + urlText + '?keyword=' + _history ) + '" class="word-break">' + _history + '<em style="float:right">'+ styleText +'</em></a>')
        }
    }
    
    function sortNumber(a, b) {
        return b - a;
    }

    init();

    $('.key-words').on({focus: focusFn, search: changeFn});

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


            if(_localHref.indexOf('xiaoqu')>0){

                plotStorageArray.push(_keyword);
                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                location.href= router_city('/xiaoqu?keyword=') + $.trim($(this).val());
            } else if(_localHref.indexOf('esf')>0){
                
                esfStorageArray.push(_keyword);
                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                location.href= router_city('/esf?keyword=') + $.trim($(this).val());
            } else if(_localHref.indexOf('loupan')>0){

                newHouseStorageArray.push(_keyword);
                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                location.href= router_city('/loupan?keyword=') + $.trim($(this).val());
            }
        }
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

    /**
     *
     * @Description：搜索
     *
     * @Param
     * @Return
     * @Author zengqingzhou
     * @Date 2018/1/29 11:47
     */
    $('.key-words').on('input',function () {
        var _keyword = $('.key-words').val();

        if (_keyword=='') { $('#automatedWord').hide(); return };
        if (_keyword!=null&&_keyword.length>0){

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];
            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];
            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];

            var url = null
            var herf = window.location.href;
            if(herf.indexOf('/xiaoqu')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&houseProperty=小区"
            }else if(herf.indexOf('/esf')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&houseProperty=二手房"
            }else if(herf.indexOf('/loupan')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&houseProperty=新房"
                url = url.replace('loupan','xinfang')
            }else {
                url = herf+"/search?keyword="+_keyword
            }
            $.ajax({
                type: "get",
                contentType:'application/json',
                url: url,
                async: true,
                dataType:'json',
                success: function (data) {
                    if (data.total>0){
                        $('#automatedWord').empty().show();
                        for(var i = 0;i<data.list.length;i++){
                            $('#automatedWord').append('<li id='+data.list[i].village_id+' class="click_work" >'+ data.list[i].villageName+' <em style="float: right; color: #bcbcbc;">'+data.list[i].house_property+'</em></li>');
                        }
                        $('.click_work').on('click',function(){
                            var word = $(this).text();
                            var id = $(this).attr('id')
                            var village = word.split(' ')[0].trim()
                            var house_property = word.split(' ')[1].trim()
                            var url = window.location.href.split('?')[0]
                            if (url.indexOf('xiaoqu')>0){
                                plotStorageArray.push(village);
                                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                window.location.href = url+'?keyword='+village
                            }
                            if (house_property=='小区'&&url.indexOf('xiaoqu')==-1){
                                plotStorageArray.push(village);
                                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                window.location.href = url+'/xiaoqu?keyword='+village
                            }
                            if (url.indexOf('esf')>0){
                                esfStorageArray.push(village);
                                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                window.location.href = url+'?keyword='+village
                            }
                            if (house_property=='二手房'&&url.indexOf('esf')==-1){
                                esfStorageArray.push(village);
                                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                window.location.href = url+'/esf?keyword='+village
                            }
                            if (house_property=='loupan'){
                                newHouseStorageArray.push(village);
                                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                window.location.href = url+'/'+id+'.html'
                            }
                            if (house_property=='新房'&&url.indexOf('xinfang')==-1&&url.indexOf('loupan')==-1){
                                newHouseStorageArray.push(village);
                                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                window.location.href = url+'/loupan/'+id+'.html'
                            }
                        })
                    }
                }
            })
        }
    })
});