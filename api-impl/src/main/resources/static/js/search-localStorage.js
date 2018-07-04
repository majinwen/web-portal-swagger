$(function(){
    var _localHref = window.location.pathname;

    $('.search-container-item').addClass('none');
    $('#search-index').removeClass('none');



    var houseTypeChoose = $('.type-menu').find('span.current').index();

    if ($('.type-tab-box').hasClass('none')) {
        $('.searchpage-search-content').addClass('only');
    }

    /**
     * 各列表页搜索框获取焦点
     */
    $('.search-link').on('focus', function () {

        if ($('.search-link').hasClass('recommend-index')) {
            $('.hot-recommend-index').removeClass('none');
        }

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

    function init() {

        $('.searchpage-history').html('');

        if(_localHref.indexOf('xiaoqu')>0){
            getLocalstorageMsg('plot');
        } else if(_localHref.indexOf('esf')>0){
            getLocalstorageMsg('esf');
        } else if(_localHref.indexOf('loupan')>0 || _localHref.indexOf('xinfang')>0) {
            getLocalstorageMsg('newHouse');
        } else if(_localHref.indexOf('zufang')>0){
            getLocalstorageMsg('rent')
        } else {
            getLocalstorageMsg('plot');
            getLocalstorageMsg('esf');
            getLocalstorageMsg('newHouse');
            getLocalstorageMsg('rent')
        }
    }
    
    function getLocalstorageMsg(styleArray) {
        var indexStorageArray = JSON.parse(localStorage.getItem(styleArray)) || [];
        var start = (indexStorageArray.length-5)>0?(indexStorageArray.length-5):0;
        var end = indexStorageArray.length;
        for (var i = start; i < end; i++) {
            var _history = indexStorageArray.pop();
            var history = _history.split(',');
            if(history.length < 2){
                continue;
            }
            var urlText = '';
            if (styleArray == 'plot') {
                urlText = 'xiaoqu';
            } else if (styleArray == 'esf') {
                urlText = 'esf';
            } else if (styleArray == 'newHouse') {
                urlText = 'loupan';
            } else if (styleArray == 'rent'){
                urlText = 'zufang';
            }
            if (history[3]==1){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?districtId=' + history[1] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else if(history[3]==2){
                var districtId = '';
                $.getJSON('/static/mock/district_area.json',function (districtList) {
                    var flag = false;
                    $.ajaxSettings.async = false;
                    for(var i in districtList){
                        if (history[1]==districtList[i].area){
                            districtId = districtList[i].district
                            flag = true
                        }
                    }
                    if(flag){
                        $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?districtId='+districtId+'&areaId=' + history[1] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
                    }else {
                        $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?areaId=' + history[1] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
                    }
                })
            }else if(urlText=='esf'){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?keyword=' + history[0] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else if(urlText=='xiaoqu'&&history[3]==''){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?keyword=' + history[0] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else if(urlText=='loupan'&&history[3]==''){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?keyword=' + history[0] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else if(urlText=='zufang'&&history[4]==3){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?rentSign=0&keyword=' + history[0] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else if(urlText=='zufang'&&history[4]==4){
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'?rentSign=1&keyword=' + history[0] ) + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }else {
                $('.searchpage-history').append('<a href="' + router_city('/'+urlText+'/' + history[1] )+'.html' + '" class="word-break" data-name="'+history[0]+'">' + history[0] + '<em style="float: right">'+ history[2]+'</em></a>')
            }
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
            var rentStorageArray = JSON.parse(localStorage.getItem('rent')) || [];
            var plotNum = localStorage.getItem('plotNum')||'';
            var esfNum = localStorage.getItem('esfNum')||'';
            var newHouseNum = localStorage.getItem('newHouseNum')||'';
            var rentNum = localStorage.getItem('rentNum')||'';
            var apartmentNum = localStorage.getItem('apartmentNum')||'';


            if(_localHref.indexOf('xiaoqu')>0){
                hashPush(plotStorageArray,_keyword+',,小区,,1');
                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                location.href= router_city('/xiaoqu?keyword=') + $.trim($(this).val());
            } else if(_localHref.indexOf('esf')>0){
                hashPush(esfStorageArray,_keyword+',,二手房,,2');
                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                location.href= router_city('/esf?keyword=') + $.trim($(this).val());
            } else if(_localHref.indexOf('loupan')>0||_localHref.indexOf('xinfang')>0){
                hashPush(newHouseStorageArray,_keyword+',,新房,,0');
                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                location.href= router_city('/loupan?keyword=') + $.trim($(this).val());
            } else if(_localHref.indexOf('zufang')>0){
                hashPush(rentStorageArray,_keyword+',,出租,,3')
                localStorage.setItem('rent', JSON.stringify(rentStorageArray));
                hashPush(rentStorageArray,_keyword+',,公寓,,4')
                localStorage.setItem('rent', JSON.stringify(rentStorageArray));
                location.href= router_city('/zufang?keyword=') + $.trim($(this).val());
            } else {
                if (_keyword!=null&&_keyword!=''){
                    $('#search-index').addClass('none');
                    $('#automatedWord').addClass('none');
                    $('#indexWord').html('');
                    if (newHouseNum>0){
                        $('#indexWord').append('<li id="3" class="click_index" >'+'查看包含"'+_keyword+'"新房'+/*' <em style="float: right; color: #bcbcbc;">约'+newHouseNum+'条></em>*/'</li>');
                    }
                    if (esfNum>0){
                        $('#indexWord').append('<li id="2" class="click_index" >'+'查看包含"'+_keyword+'"二手房'+/*' <em style="float: right; color: #bcbcbc;">约'+esfNum+'条></em>*/'</li>');
                    }
                    if (plotNum>0){
                        $('#indexWord').append('<li id="1" class="click_index" >'+'查看包含"'+_keyword+'"小区'+/*' <em style="float: right; color: #bcbcbc;">约'+plotNum+'条></em>*/'</li>');
                    }
                    if (rentNum>0){
                        $('#indexWord').append('<li id="4" class="click_index" >'+'查看包含"'+_keyword+'"出租'+/*' <em style="float: right; color: #bcbcbc;">约'+rentNum+'条></em>*/'</li>');
                    }
                    if (apartmentNum>0){
                        $('#indexWord').append('<li id="5" class="click_index" >'+'查看包含"'+_keyword+'"公寓'+/*' <em style="float: right; color: #bcbcbc;">约'+rentNum+'条></em>*/'</li>');
                    }

                    $('.click_index').on('click',function () {
                        var id = $(this).attr('id')
                        var url = window.location.href.split("?")[0];
                        if(id == 1){
                            hashPush(plotStorageArray,_keyword+',,小区,,1');
                            localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                            window.location.href= url+'/xiaoqu?keyword='+_keyword
                        }
                        if(id == 2){
                            hashPush(esfStorageArray,_keyword+',,二手房,,2');
                            localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                            window.location.href = url+'/esf?keyword='+_keyword
                        }
                        if(id == 3){
                            hashPush(newHouseStorageArray,_keyword+',,新房,,0');
                            localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                            window.location.href = url+'/loupan?keyword='+_keyword
                        }
                        if(id == 4){
                            hashPush(rentStorageArray,_keyword+',,出租,,3')
                            localStorage.setItem('rent',JSON.stringify(rentStorageArray));
                            window.location.href= url+'/zufang?rentSign=0&keyword='+_keyword
                        }
                        if(id == 5){
                            hashPush(rentStorageArray,_keyword+',,公寓,,4')
                            localStorage.setItem('rent',JSON.stringify(rentStorageArray));
                            window.location.href= url+'/zufang?rentSign=1&keyword='+_keyword
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
        $('.key-words').val($(this).attr("data-name"));
        $('.search-page-wrapper').removeClass('active');
    });


    /**
     *  
     * @Description：排除重复
     *
     * @Param
     * @Return 
     * @Author zengqingzhou
     * @Date 2018/2/7 17:37
     */
    function hashPush(StorageArray,keyword) {
        var flag = false;
        for(var i = 0;i<StorageArray.length;i++){
            if(StorageArray[i].split(',')[0]==keyword.split(',')[0]&&StorageArray[i].split(',')[2]==keyword.split(',')[2]){
                flag = true;
            }
        }
        if (!flag){
            StorageArray.push(keyword)
        }
    }

    function getUrlWithDistrictIdByAreaId(search_id,url) {
        var districtId = '';
        $.getJSON('/static/mock/district_area.json',function (districtList) {
            var flag = false;
            $.ajaxSettings.async = false;
            for(var i in districtList){
                if (search_id==districtList[i].area){
                    districtId = districtList[i].district
                    flag = true
                }
            }
            if(flag){
                window.location.href = url+'?districtId='+districtId+'&areaId='+search_id
            }else {
                window.location.href = url+'?areaId='+search_id
            }
        })
    }

    function getHomeUrlWithDistrictIdByAreaId(search_id,url,location_type) {
        var districtId = '';
        $.getJSON('/static/mock/district_area.json',function (districtList) {
            var flag = false;
            $.ajaxSettings.async = false;
            for(var i in districtList){
                if (search_id==districtList[i].area){
                    districtId = districtList[i].district
                    flag = true
                }
            }
            if(flag){
                window.location.href =  url+'/'+location_type+'?districtId='+districtId+'&areaId='+search_id
            }else {
                window.location.href =  url+'/'+location_type+'?areaId='+search_id
            }
        })
    }

    function getUrl(url,search_type,search_id,search_name,location_type_sings,search_type_sings) {
        if (search_type_sings == 1){
            if (location_type_sings == 1){
                window.location.href = url+'?districtId='+search_id
            }else if (location_type_sings ==2){
                getUrlWithDistrictIdByAreaId(search_id,url)
            }else {
                window.location.href = url+'/'+search_id+'.html'
            }
        }
        if (search_type_sings == 2){
            if (location_type_sings == 1){
                window.location.href = url+'?districtId='+search_id
            }else if (location_type_sings ==2){
                getUrlWithDistrictIdByAreaId(search_id,url)
            }else {
                window.location.href = url+'?keyword='+search_name
            }
        }
        if (search_type_sings == 0){
            if (location_type_sings == 1){
                window.location.href = url+'?districtId='+search_id
            }else if (location_type_sings ==2){
                getUrlWithDistrictIdByAreaId(search_id,url)
            }else {
                window.location.href = url+'/'+search_id+'.html'
            }
        }
        if (search_type_sings == 3||search_type_sings == 4){
            if (location_type_sings == 1){
                window.location.href = url+'?districtId='+search_id
            }else if (location_type_sings ==2){
                getUrlWithDistrictIdByAreaId(search_id,url)
            }else {
                window.location.href = url+'?keyword='+search_name
            }
        }
    }

    function getHomePageUrl(search_type,location_type_sings,url,search_name,search_id,search_type_sings) {
        if(search_type_sings == 3||search_type_sings == 4){
            if(location_type_sings == 1){
                window.location.href = url+'/zufang?districtId='+search_id
            }else if(location_type_sings == 2){
                window.location.href = url+'/zufang?areaId='+search_id
            }else {
                window.location.href = url+'/zufang/'+search_id+'.html'
            }
        }
        if(search_type_sings == 0){
            if(location_type_sings == 1){
                window.location.href = url+'/loupan?districtId='+search_id
            }else if(location_type_sings == 2){
                window.location.href = url+'/loupan?areaId='+search_id
            }else {
                window.location.href = url+'/loupan/'+search_id+'.html'
            }
        }
        if (search_type_sings == 1){
            if(location_type_sings == 1){
                window.location.href = url+'/xiaoqu?districtId='+search_id
            }else if(location_type_sings == 2){
                window.location.href = url+'/xiaoqu?areaId='+search_id
            }else {
                window.location.href = url+'/xiaoqu/'+search_id+'.html'
            }
        }
        if(search_type_sings == 2){
            if(location_type_sings == 1){
                window.location.href = url+'/esf?districtId='+search_id
            }else if(location_type_sings == 2){
                window.location.href = url+'/esf?areaId='+search_id
            }else {
                window.location.href = url+'/esf?keyword='+search_name
            }
        }

    }

    /**
     *  
     * @Description：添加房源个数缓存
     *
     * @Param 
     * @Return 
     * @Author zengqingzhou
     * @Date 2018/2/26 18:13
     */
    function setLocalStorage(name,value) {
        localStorage.removeItem(name);
        localStorage.setItem(name,value);
    }

    /**
     *
     * @Description：搜索
     *
     * @Param
     * @Return
     * @Author zengqingzhou
     * @Date 2018/2/7 16:11
     */

    // $('.key-words').bind('input',function () {
    //
    //     var now = +new Date();
    //     if (now - evTimeStamp < 100) {
    //          return;
    //     }
    //     evTimeStamp = now;
    //
    //     var _keyword = $('.key-words').val();
    //
    //     $('#automatedWord').removeClass('none')
    //     $('#indexWord').html('')
    //
    //     if (_keyword=='') { $('#automatedWord').hide(); return };
    //     if (_keyword!=null&&_keyword.length>0){
    //
    //         var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];
    //         var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];
    //         var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];
    //
    //         var url = null
    //         var herf = window.location.href;
    //         if(herf.indexOf('/xiaoqu')>0){
    //             url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=小区"
    //         }else if(herf.indexOf('/esf')>0){
    //             url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=二手房"
    //         }else if(herf.indexOf('/loupan')>0||herf.indexOf('/xinfang')>0){
    //             url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=新房"
    //             url = url.replace('loupan','xinfang')
    //         }else {
    //             url = herf+"/search?keyword="+_keyword
    //         }
    //         $.ajax({
    //             type: "get",
    //             contentType:'application/json',
    //             url: url,
    //             async: true,
    //             dataType:'json',
    //             success: function (data) {
    //                     plotNum = data.plotNum;
    //                     esfNum = data.esfNum;
    //                     newHouseNum = data.newHouseNum;
    //                     $('#automatedWord').empty().show();
    //                     if (data.list.length>0){
    //                         $('#automatedWord').html('')
    //                         $('#automatedWord').append('<li  >'+'猜您可能在找：'+'</li>');
    //                         for(var i = 0;i<data.list.length;i++){
    //                             var searchType = data.list[i].search_type
    //                             var locationTypeSings = data.list[i].location_type_sings||-1
    //                             var searchTypeSings = data.list[i].search_type_sings||0
    //                             $('#automatedWord').append('<li id='+data.list[i].search_id+' class="click_work" location_type_sings='+locationTypeSings+' search_type_sings='+ searchTypeSings +'>'+ data.list[i].search_name+' <em style="float: right; color: #bcbcbc;">'+searchType+'</em></li>');
    //                         }
    //                         $('.click_work').on('click',function(){
    //                             var word = $(this).text();
    //                             var search_id = $(this).attr('id')
    //                             var location_type_sings = $(this).attr('location_type_sings')||''
    //                             // var search_type_sings = $(this).attr('search_type_sings')||''
    //                             var search_name = word.split(' ')[0].trim()
    //                             var search_type = word.split(' ')[word.split(' ').length-1].trim()
    //                             var url = window.location.href.split('?')[0]
    //                             if (url.indexOf('xiaoqu')>0){
    //                                 hashPush(plotStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                 localStorage.setItem('plot', JSON.stringify(plotStorageArray));
    //                                 window.location.href = getUrl(url,search_type,search_id,search_name,location_type_sings);
    //                             }
    //                             if (url.indexOf('esf')>0){
    //                                 hashPush(esfStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                 localStorage.setItem('esf', JSON.stringify(esfStorageArray));
    //                                 window.location.href = getUrl(url,search_type,search_id,search_name,location_type_sings);
    //                             }
    //                             if (url.indexOf('xinfang')>0||url.indexOf('loupan')>0){
    //                                 hashPush(newHouseStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                 localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
    //                                 url = url.replace('xinfang','loupan')
    //                                 window.location.href = getUrl(url,search_type,search_id,search_name,location_type_sings);
    //                             }
    //                             if (url.indexOf('xinfang')==-1&&url.indexOf('loupan')==-1&&url.indexOf('esf')==-1&&url.indexOf('xiaoqu')==-1){
    //                                 if (search_type == '新房'){
    //                                     hashPush(newHouseStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                     localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
    //                                     if(location_type_sings == 1){
    //                                         window.location.href = url+'/loupan?districtId='+search_id
    //                                     }else if(location_type_sings == 2){
    //                                         window.location.href = url+'/loupan?areaId='+search_id
    //                                     }else {
    //                                         window.location.href = url+'/loupan/'+search_id+'.html'
    //                                     }
    //                                 }
    //                                 if(search_type == '小区'){
    //                                     hashPush(plotStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                     localStorage.setItem('plot', JSON.stringify(plotStorageArray));
    //                                     if(location_type_sings == 1){
    //                                         window.location.href = url+'/xiaoqu?districtId='+search_id
    //                                     }else if(location_type_sings == 2){
    //                                         window.location.href = url+'/xiaoqu?areaId='+search_id
    //                                     }else {
    //                                         window.location.href = url+'/xiaoqu/'+search_id+'.html'
    //                                     }
    //                                 }
    //                                 if(search_type == '二手房'){
    //                                     hashPush(esfStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings)
    //                                     localStorage.setItem('esf', JSON.stringify(esfStorageArray));
    //                                     if(location_type_sings == 1){
    //                                         window.location.href = url+'/esf?districtId='+search_id
    //                                     }else if(location_type_sings == 2){
    //                                         window.location.href = url+'/esf?areaId='+search_id
    //                                     }else {
    //                                         window.location.href = url+'/esf?keyword='+search_name
    //                                     }
    //                                 }
    //                             }
    //                         })
    //                     }
    //             }
    //         })
    //     }
    // })

    // $('.key-words').on({
    //
    //     keyup : function(e){
    //         console.log('keyup','e.target.isNeedPrevent:'+e.target.isNeedPrevent)
    //         var flag = e.target.isNeedPrevent;
    //         if(flag)  return;
    //         console.log('keyup:response()')
    //         response() ;
    //         e.target.keyEvent = false ;
    //         console.log('e.target.keyEvent:'+e.target.keyEvent)
    //     },
    //     keydown : function(e){
    //         console.log('keydown','e.target.keyEvent = true ;')
    //         e.target.keyEvent = true ;
    //     },
    //     input : function(e){
    //         console.log('input:')
    //         if(!e.target.keyEvent){
    //             console.log('input:'+'response()')
    //             response()
    //         }
    //     },
    //     compositionstart : function(e){
    //         console.log('compositionstart:'+'e.target.isNeedPrevent = true ;')
    //         console.log('e.target.keyEvent:'+e.target.keyEvent)
    //         e.target.isNeedPrevent = true ;
    //     },
    //     compositionend : function(e){
    //         console.log('compositionend:e.target.isNeedPrevent = false;')
    //         e.target.isNeedPrevent = false;
    //
    //     }
    // })
    /**
     * 中文输入拼音时不查询
     * @type {number}
     */
    var evTimeStamp = 0;
    var cpLock = false;
    $('.key-words').on({
        input : function(e){
            if(!cpLock){
                response()
            }
        },
        propertychange : function () {
            if(!cpLock){
                response()
            }
        },
        compositionstart : function(e){
            cpLock = true;
        },
        compositionend : function(e){
            cpLock = false;
            response()
        }
    })

    function response(){
        var now = +new Date();
        if (now - evTimeStamp < 100) {
            return;
        }
        evTimeStamp = now;

        var _keyword = $('.key-words').val();

        $('#automatedWord').removeClass('none')
        $('#indexWord').html('')

        if (_keyword=='') {
            $('#automatedWord').hide();
            $('#search-container-wrapper').removeClass('none');
            return
        };
        if (_keyword!=null&&_keyword.length>0){
            var newHouseStorageArray = JSON.parse(localStorage.getItem('newHouse')) || [];
            var esfStorageArray = JSON.parse(localStorage.getItem('esf')) || [];
            var plotStorageArray = JSON.parse(localStorage.getItem('plot')) || [];
            var rentStorageArray = JSON.parse(localStorage.getItem('rent')) || [];

            var url = null
            var herf = window.location.href.split("?")[0];
            if(herf.indexOf('/xiaoqu')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=小区"
            }else if(herf.indexOf('/esf')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=二手房"
            }else if(herf.indexOf('/loupan')>0||herf.indexOf('/xinfang')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=新房"
                url = url.replace('loupan','xinfang')
            }else if(herf.indexOf('/zufang')>0){
                url = herf.split('?')[0]+"/search?keyword="+_keyword+"&property=租房"
            }else {
                url = herf+"/search?keyword="+_keyword
                $('#search-index').removeClass('none')
            }
            $.ajax({
                type: "get",
                contentType:'application/json',
                url: url,
                async: true,
                dataType:'json',
                success: function (data) {
                    setLocalStorage('plotNum',data.plotNum)
                    setLocalStorage('esfNum',data.esfNum)
                    setLocalStorage('newHouseNum',data.newHouseNum)
                    setLocalStorage('rentNum',data.rentNum)
                    setLocalStorage('apartmentNum',data.apartmentNum)
                    $('#automatedWord').empty().show();
                    if (data.list.length>0){
                        $('#automatedWord').html('')
                        $('#automatedWord').append('<li  >'+'猜您可能在找：'+'</li>');
                        for(var i = 0;i<data.list.length;i++){
                            var searchType = data.list[i].search_type
                            var locationTypeSings = data.list[i].location_type_sings||-1
                            var searchTypeSings = data.list[i].search_type_sings||0
                            var listContent = ''
                            if (null!=data.list[i].search_nickname&&''!=data.list[i].search_nickname) {
                                listContent = '<li id='+data.list[i].search_id+' class="click_work" location_type_sings='+locationTypeSings+' search_type_sings='+ searchTypeSings +'>'+ data.list[i].search_name+ '<em style="color: #666">(' +data.list[i].search_nickname + ')</em>' +' <em style="float: right; color: #bcbcbc;">'+searchType+'</em></li>'
                            } else {
                                listContent = '<li id='+data.list[i].search_id+' class="click_work" location_type_sings='+locationTypeSings+' search_type_sings='+ searchTypeSings +'>'+ data.list[i].search_name+ ' <em style="float: right; color: #bcbcbc;">'+searchType+'</em></li>'
                            }
                            $('#automatedWord').append(listContent);
                        }
                        $('.click_work').on('click',function(){
                            var word = $(this).text();
                            var search_id = $(this).attr('id')
                            var location_type_sings = $(this).attr('location_type_sings')||''
                            var search_type_sings = $(this).attr('search_type_sings')||''
                            var search_name = word.split(' ')[0].trim()
                            var search_type = word.split(' ')[word.split(' ').length-1].trim()
                            var url = window.location.href.split('?')[0]
                            if (url.indexOf('zufang')>0){
                                hashPush(rentStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                localStorage.setItem('rent', JSON.stringify(rentStorageArray));
                                getUrl(url,search_type,search_id,search_name,location_type_sings,search_type_sings);
                            }
                            if (url.indexOf('xiaoqu')>0){
                                hashPush(plotStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                getUrl(url,search_type,search_id,search_name,location_type_sings,search_type_sings);
                            }
                            if (url.indexOf('esf')>0){
                                hashPush(esfStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                getUrl(url,search_type,search_id,search_name,location_type_sings,search_type_sings);
                            }
                            if (url.indexOf('xinfang')>0||url.indexOf('loupan')>0){
                                hashPush(newHouseStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                url = url.replace('xinfang','loupan')
                                getUrl(url,search_type,search_id,search_name,location_type_sings,search_type_sings);
                            }
                            if (url.indexOf('xinfang')==-1&&url.indexOf('loupan')==-1&&url.indexOf('esf')==-1&&url.indexOf('xiaoqu')==-1&&url.indexOf('zufang')==-1){
                                if (search_type == '新房'){
                                    hashPush(newHouseStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                    localStorage.setItem('newHouse', JSON.stringify(newHouseStorageArray));
                                    if(search_type == '新房'){
                                        if(location_type_sings == 1){
                                            window.location.href = url+'/loupan?districtId='+search_id
                                        }else if(location_type_sings == 2){
                                            var location_type = 'loupan';
                                            getHomeUrlWithDistrictIdByAreaId(search_id,url,location_type)
                                        }else {
                                            window.location.href = url+'/loupan/'+search_id+'.html'
                                        }
                                    }
                                }
                                if(search_type == '小区'){
                                    hashPush(plotStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                    localStorage.setItem('plot', JSON.stringify(plotStorageArray));
                                    if(location_type_sings == 1){
                                        window.location.href = url+'/xiaoqu?districtId='+search_id
                                    }else if(location_type_sings == 2){
                                        var location_type = 'xiaoqu';
                                        getHomeUrlWithDistrictIdByAreaId(search_id,url,location_type)
                                    }else {
                                        window.location.href = url+'/xiaoqu/'+search_id+'.html'
                                    }
                                }
                                if(search_type == '二手房'){
                                    hashPush(esfStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                    localStorage.setItem('esf', JSON.stringify(esfStorageArray));
                                    if(location_type_sings == 1){
                                        window.location.href = url+'/esf?districtId='+search_id
                                    }else if(location_type_sings == 2){
                                        var location_type = 'esf';
                                        getHomeUrlWithDistrictIdByAreaId(search_id,url,location_type)
                                    }else {
                                        window.location.href = url+'/esf?keyword='+search_name
                                    }
                                }
                                if(search_type == '出租'||search_type == '公寓'){
                                    hashPush(rentStorageArray,search_name+','+search_id+','+search_type+','+location_type_sings+','+search_type_sings)
                                    console.log('*******************1111111111111')
                                    localStorage.setItem('rent', JSON.stringify(rentStorageArray));
                                    if(location_type_sings == 1){
                                        window.location.href = url+'/zufang?districtId='+search_id
                                    }else if(location_type_sings == 2){
                                        var location_type = 'zufang';
                                        getHomeUrlWithDistrictIdByAreaId(search_id,url,location_type)
                                    }else {
                                        window.location.href = url+'/zufang?keyword='+search_name
                                    }
                                }
                            }
                        })

                        $('#search-container-wrapper').addClass('none');
                    }
                }
            })
        }
    }
});