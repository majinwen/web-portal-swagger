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
        $('body').addClass('fixed-scroll');
        $('.search-page-wrapper').removeClass('none');
        $('.key-words').focus();
    });
    $('.searchpage-search-btn').on('click', function () {
        $('.search-page-wrapper').addClass('none');
        $('body').removeClass('fixed-scroll');
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
                var history = _history.split(',');
                if (history[2]=='区县'){
                    $('.searchpage-history').append('<a href="' + router_city('/xiaoqu?districtId=' + history[1] ) + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
                }else if(history[2]=='商圈'){
                    $('.searchpage-history').append('<a href="' + router_city('/xiaoqu?areaId=' + history[1] ) + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
                }else {
                    $('.searchpage-history').append('<a href="' + router_city('/xiaoqu/' + history[1] )+'.html' + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
                }
            }

        } else if(_localHref.indexOf('esf')>0){

            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];

            var start = (esfStorageArray.length-5)>0?(esfStorageArray.length-5):0;
            var end = esfStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = esfStorageArray.pop();
                var history = _history.split(',');
                if (history[2]=='区县'){
                    $('.searchpage-history').append('<a href="' + router_city('/esf?districtId=' + history[1] ) + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }else if(history[2]=='商圈'){
                    $('.searchpage-history').append('<a href="' + router_city('/esf?areaId=' + history[1] ) + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }else {
                    $('.searchpage-history').append('<a href="' + router_city('/esf?keyword=' + history[0] ) + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }
            }

        }else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0){

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];

            var start = (newHouseStorageArray.length-5)>0?(newHouseStorageArray.length-5):0;
            var end = newHouseStorageArray.length;
            for (var i=start; i<end; i++) {
                var _history = newHouseStorageArray.pop();
                var history = _history.split(',');
                if (history[2]=='区县'){
                    $('.searchpage-history').append('<a href="' + router_city('/loupan?districtId=' + history[1] ) + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }else if(history[2]=='商圈'){
                    $('.searchpage-history').append('<a href="' + router_city('/loupan?areaId=' + history[1] ) + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }else {
                    $('.searchpage-history').append('<a href="' + router_city('/loupan/' + history[1] )+'.html' + '" class="word-break">' + history[0] +  '<em style="float: right">'+ history[3]+'</em></a>')
                }
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
            var history = _history.split(',');
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
            if (history[2]=='区县'){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?districtId=' + history[1] ) + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
            }else if(history[2]=='商圈'){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?areaId=' + history[1] ) + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
            }else if(urlText=='esf'){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?keyword=' + history[0] ) + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
            }else {
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'/' + history[1] )+'.html' + '" class="word-break">' + history[0] + '<em style="float: right">'+ history[3]+'</em></a>')
            }
            // $('.searchpage-history').append('<a href="' + router_city('/' + urlText + '?keyword=' + _history ) + '" class="word-break">' + _history + '<em style="float:right">'+ styleText +'</em></a>')
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

    var plotNum;
    var esfNum;
    var newHouseNum;

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
            } else if(_localHref.indexOf('loupan')>0||_localHref.indexOf('xinfang')>0){

                newHouseStorageArray.push(_keyword);
                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                location.href= router_city('/loupan?keyword=') + $.trim($(this).val());
            }else {
                if (_keyword!=null&&_keyword!=''){
                    $('#automatedWord').addClass('none');
                    if (newHouseNum>0){
                        $('#indexWord').append('<li id="3" class="click_index" >'+'新房'+/*' <em style="float: right; color: #bcbcbc;">约'+newHouseNum+'条></em>*/'</li>');
                    }
                    if (esfNum>0){
                        $('#indexWord').append('<li id="2" class="click_index" >'+'二手房'+/*' <em style="float: right; color: #bcbcbc;">约'+esfNum+'条></em>*/'</li>');
                    }
                    if (plotNum>0){
                        $('#indexWord').append('<li id="1" class="click_index" >'+'小区'+/*' <em style="float: right; color: #bcbcbc;">约'+plotNum+'条></em>*/'</li>');
                    }

                    $('.click_index').on('click',function () {
                        var id = $(this).attr('id')
                        var url = window.location.href;
                        if(id == 1){
                            plotStorageArray.push(_keyword);
                            localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                            window.location.href= url+'/xiaoqu?keyword='+_keyword
                        }
                        if(id == 2){
                            esfStorageArray.push(_keyword);
                            localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                            window.location.href = url+'/esf?keyword='+_keyword
                        }
                        if(id == 3){
                            newHouseStorageArray.push(_keyword);
                            localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                            window.location.href = url+'/loupan?keyword='+_keyword
                        }
                    })
                }
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

    function getUrl(url,search_type,search_id,search_name,house_type) {
        var href = '';
        if (house_type == '小区'){
            if (search_type == '区县'){
                href = url+'?districtId='+search_id
            }else if (search_type =='商圈'){
                href = url+'?areaId='+search_id
            }else {
                href = url+'/'+search_id+'.html'
            }
        }
        if (house_type == '二手房'){
            if (search_type == '区县'){
                href = url+'?districtId='+search_id
            }else if (search_type =='商圈'){
                href = url+'?areaId='+search_id
            }else {
                href = url+'?keyword='+search_name
            }
        }
        if (house_type == '新房'){
            if (search_type == '区县'){
                href = url+'?districtId='+search_id
            }else if (search_type =='商圈'){
                href = url+'?areaId='+search_id
            }else {
                href = url+'/'+search_id+'.html'
            }
        }
        return href;
    }
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

        $('#automatedWord').removeClass('none')
        $('#indexWord').html('')

        if (_keyword=='') { $('#automatedWord').hide(); return };
        if (_keyword!=null&&_keyword.length>0){

            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];
            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];
            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];

            var url = null
            var herf = window.location.href;
            if(herf.indexOf('/xiaoqu')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=小区"
            }else if(herf.indexOf('/esf')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=二手房"
            }else if(herf.indexOf('/loupan')>0||herf.indexOf('/xinfang')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=新房"
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
                        plotNum = data.plotNum;
                        esfNum = data.esfNum;
                        newHouseNum = data.newHouseNum;
                        $('#automatedWord').empty().show();
                        for(var i = 0;i<data.list.length;i++){
                            var villageType = data.list[i].village_type||-1
                            var searchType = data.list[i].search_type
                            if(villageType==0){
                                $('#automatedWord').append('<li id='+data.list[i].search_id+' class="click_work" search_type='+searchType+'>'+ data.list[i].search_name+' <em style="float: right; color: #bcbcbc;">'+'新房'+'</em></li>');
                            }else if (villageType==1){
                                $('#automatedWord').append('<li id='+data.list[i].search_id+' class="click_work" search_type='+searchType+'>'+ data.list[i].search_name+' <em style="float: right; color: #bcbcbc;">'+'小区'+'</em></li>');
                            }else if (villageType==2){
                                $('#automatedWord').append('<li id='+data.list[i].search_id+' class="click_work" search_type='+searchType+'>'+ data.list[i].search_name+' <em style="float: right; color: #bcbcbc;">'+'二手房'+'</em></li>');
                            }else if (villageType==-1){
                                $('#automatedWord').append('<li id='+data.list[i].search_id+' class="click_work" search_type='+searchType+'>'+ data.list[i].search_name+' <em style="float: right; color: #bcbcbc;">'+searchType+'</em></li>');
                            }
                        }
                        $('.click_work').on('click',function(){
                            var word = $(this).text();
                            var search_id = $(this).attr('id')
                            var search_type = $(this).attr('search_type')
                            var search_name = word.split(' ')[0].trim()
                            var house_type = word.split(' ')[word.split(' ').length-1].trim()
                            var url = window.location.href.split('?')[0]
                            if (url.indexOf('xiaoqu')>0){
                                plotStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                window.location.href = getUrl(url,search_type,search_id,search_name,house_type);
                            }
                            if (url.indexOf('esf')>0){
                                esfStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                window.location.href = getUrl(url,search_type,search_id,search_name,house_type);
                            }
                            if (url.indexOf('xinfang')>0||url.indexOf('loupan')>0){
                                newHouseStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                url = url.replace('xinfang','loupan')
                                window.location.href = getUrl(url,search_type,search_id,search_name,house_type);
                            }
                            if (url.indexOf('xinfang')==-1&&url.indexOf('loupan')==-1&&url.indexOf('esf')==-1&&url.indexOf('xiaoqu')==-1){
                                if (house_type == '新房'){
                                    newHouseStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                    localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                    if(search_type == '区县'){
                                        window.location.href = url+'/loupan?districtId='+search_id
                                    }else if(search_type == '商圈'){
                                        window.location.href = url+'/loupan?areaId='+search_id
                                    }else {
                                        window.location.href = url+'/loupan/'+search_id+'.html'
                                    }
                                }
                                if(house_type == '小区'){
                                    plotStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                    localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                    if(search_type == '区县'){
                                        window.location.href = url+'/xiaoqu?districtId='+search_id
                                    }else if(search_type == '商圈'){
                                        window.location.href = url+'/xiaoqu?areaId='+search_id
                                    }else {
                                        window.location.href = url+'/xiaoqu/'+search_id+'.html'
                                    }
                                }
                                if(house_type == '二手房'){
                                    esfStorageArray.push(search_name+','+search_id+','+search_type+','+house_type);
                                    localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                    if(search_type == '区县'){
                                        window.location.href = url+'/esf?districtId='+search_id
                                    }else if(search_type == '商圈'){
                                        window.location.href = url+'/esf?areaId='+search_id
                                    }else {
                                        window.location.href = url+'/esf?keyword='+search_name
                                    }
                                }
                            }
                        })
                }
            })
        }
    })
});