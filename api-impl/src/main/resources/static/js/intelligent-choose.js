$(function () {
    $('#superContainer').fullpage({
        fitToSection: true,
        resize: false
    });
    $.fn.fullpage.setAllowScrolling(false, 'up, down');

    $('.begin').on('click', function () {
        $.fn.fullpage.moveSectionDown();
    });

    $('.start-btn').on('click', function () {
        if (!$(this).hasClass('none')) {
            $.fn.fullpage.moveSectionDown();
            rada_animit.init();
            $.ajax({
                type: "GET",
                async: true,
                url: router_city('/findhouse/showUserPortrayal'),
                data: options,
                success: function (dataInfo) {
                    // console.log(dataInfo.data);
                    try {
                        rada_animit.id = dataInfo.data.id;
                    }
                    catch (e) {
                        console.error(e)
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.error(errorThrown)
                }
            })
        }
    });

    chooseUserType();   // 用户选择类型



    // reportEchartAssemble(); // 报考页图表集合
});

/**
 * 筛选条件对象
 * @type {Object}
 */
var options = new Object();

/**
 * Step1:Choose user type
 */
function chooseUserType() {
    $('.choose-wrapper').on('click', '.choose-item-box', function () {
        $('.choose-wrapper').find('.choose-item-box').removeClass('current');
        $(this).addClass('current');
    });
    $('#userTypeSubmit').on('click', function () {
        if ($('.choose-wrapper').find('.current').length > 0) {
            $.fn.fullpage.moveSectionDown();
            var userTypeParams = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');

            options['userType'] = userTypeParams;

            $.ajax({
                type: "GET",
                url: router_city('/findhouse/xuanzeleixing'),
                data: options,
                success: function (data) {
                    // console.log(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {

                }
            });
            chooseUserFinds();  // 用户开启智能找房之旅
        }
    });
}

function chooseUserFinds() {
    var asyn_check={
        isLoading:null,
        count:0,
        get:function (success_callback,error_callback) {
            var defaults={
                "preconcTotal":100
            }
            var params=$.extend({},defaults,options);
            var that=this;
            if(this.isLoading){
                try{
                    this.isLoading.abort();
                }
                catch(e){
                    console.error(e);
                }
            }
            this.isLoading=$.ajax({
                type: 'GET',
                url: router_city('/findhouse/queryUserChoice'),
                data: params,
                success:function(data){
                    that.isLoading=null
                    try {
                        if(options['userPortrayalType']!=7) {
                            options['userPortrayalType'] = data.data.userPortrayalType;
                        }
                        if(data.data.plotCount==0){
                            that.count=data.data.plotCount;
                            $('.result-begin').removeClass('none').find('p').text("无匹配小区，换个条件看看").addClass('high-light-red');
                            $('.result-container').addClass('none');
                        }else{
                            var ratio = new Number(data.data.ratio / 100);
                            $('#plot_Count').find('em').text(data.data.plotCount);
                            that.count=data.data.plotCount;
                            $('#plot_Ratio').find('em').text(ratio.toFixed(3) == '0.000' ? '0' + '%' : ratio.toFixed(3) + '%');
                            $('.result-begin').addClass('none');
                            $('.result-container').removeClass('none');
                        }
                        /*var ratio = new Number(data.data.ratio / 100);
                        $('#plot_Count').find('em').text(data.data.plotCount);
                        that.count=data.data.plotCount;
                        $('#plot_Ratio').find('em').text(ratio.toFixed(3) == '0.000' ? '0' + '%' : ratio.toFixed(3) + '%');
                        $('.result-begin').addClass('none');
                        $('.result-container').removeClass('none');*/

                    }
                    catch (e){
                        console.error(e)
                    }
                    success_callback(data);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    that.isLoading=null;
                    error_callback(XMLHttpRequest, textStatus, errorThrown);
                }
            });
        }
    }
    var base_Model={
        parents:[],
        has_choose:false,
        key:0,
        parent_key:0,
        setParent:function (p,resetParentKey) {
            this.parents = p;
            this.parent_key=0;
            for (var i = 0; i < this.parents.length; i++) {
                this.parent_key += this.parents[i].key;
            }
            if(resetParentKey){
                this.parent_key=0;
            }
        },
        disable:function () {

            this.context=null
            this.el.unbind('click');
            this.el.removeClass('current');
            this.el.removeClass('optional');
            this.el.removeClass('choose-end');
        },
        enable:function () {
            if(this.has_choose)
                return
            this.el.addClass('current');
            this.el.addClass('optional');
            var that = this;
            this.el.click(function () {
                that.dialog_show();
            })
        }

    }
    var yusuan_model={
        el:$('.list-item li').eq(0),
        check:function () {
            var parent_check=true;
            var current_parent_key=0
            for (var i = 0; i < this.parents.length; i++) {
                current_parent_key += this.parents[i].key;
                if (!this.parents[i].has_choose) {
                    parent_check = false;
                }
            }
            var parent_change = this.parent_key!=current_parent_key;
            this.parent_key = current_parent_key;
            if(this.after_parent_check){
                this.after_parent_check(parent_check,parent_change);
            }

        },
        after_parent_check:function (parent_check, parent_change) {
            this.enable();
        },
        dialog_show:function () {
            var that = this;
            $('.layer1').removeClass('none');
            // 绑定滑块的事件
            $('.month-slide').on('touchstart', '.slider-thumb', function (evt) {
                slide($(this), evt, '')
            });
            $('.down-slide').on('touchstart', '.slider-thumb', function (evt) {
                slide($(this), evt, '万')
            });
            $('.total-slide').on('touchstart', '.slider-thumb', function (evt) {
                slide($(this), evt, '万')
            });
            /**
             * 切换预算
             * */
            // $('.price-tab').unbind('click');
            $('.price-tab').on('click', 'span', function () {
                $(this).addClass('current').siblings().removeClass('current');
                if ($('.total-price').hasClass('current')) {
                    $('.total-conent').removeClass('none');
                    $('.month-content').addClass('none');
                } else if ($('.month-price').hasClass('current')) {
                    $('.total-conent').addClass('none');
                    $('.month-content').removeClass('none');
                }
            });
            $("#submitPrice").unbind('click');
            $("#submitPrice").click(function () {
                //选择总价
                if ($('.total-price').hasClass('current')) {
                    var priceInit = $('.total-conent').find('.slide-text').text();
                    var totalPrice = parseInt(priceInit);
                    options['preconcTotal'] = totalPrice;
                    options['downPayMent'] = null;
                    options['monthPayMent'] = null;
                    var totalPriceHtml = '<p><span>总价：<em>' + priceInit + '</em></span></p>';
                    that.el.find('.result-animate').html(totalPriceHtml);

                }
                //首付+月供
                else {
                    var payInit = $('.down-slide').find('.slide-text').text();
                    var payPrice = parseInt(payInit);
                    var monthPrice = $('.month-slide').find('.slide-text').text();

                    options['downPayMent'] = payPrice;
                    options['monthPayMent'] = monthPrice;
                    options['preconcTotal'] = null;
                    var payPriceHtml = '<p><span>首付：<em>' + payInit + '</em></span><span>月供：<em>' + monthPrice + '元</em></span></p>';
                    that.el.find('.result-animate').html(payPriceHtml);

                    /*$('.list-item').find('li').eq(0).find('.result-animate').html(payPriceHtml);*/
                }
                that.dialog_finish();
                asyn_check.get(function () {
                    that.context=1
                    flow_instance.go();
                })


            })
        },
        dialog_finish:function () {
            this.has_choose=true;
            this.key += 1;
            // this.el.siblings().removeClass('current');
            this.el.removeClass('current').addClass('choose-end');
            $('.layer1').addClass('none');
        }
    };
    yusuan_model = $.extend({},base_Model,yusuan_model)
    var huxing_model={
        el:$('.list-item li').eq(1),
        isfirsChoose:true,
        check:function () {
            var parent_check=true;
            var current_parent_key=0
            for (var i = 0; i < this.parents.length; i++) {
                current_parent_key += this.parents[i].key;
                if (!this.parents[i].has_choose) {
                    parent_check = false;
                }
            }
            //111111111111111
            var parent_change = this.parent_key!=current_parent_key;
            this.parent_key = current_parent_key;
            if(this.after_parent_check){
                this.after_parent_check(parent_check,parent_change);
            }

        },
        after_parent_check:function (parent_check,parent_change) {
            if(parent_check){
                this.enable();
            }

        },
        dialog_show:function () {
            var that = this;
            $('.layer2').removeClass('none');
            $('.content-list li').unbind('click');
            $('.content-list').on('click', 'li', function () {
                $(this).addClass('current').siblings().removeClass('current');
            });

            $('#submitHouseType').unbind('click');
            $('#submitHouseType').on('click', function () {


                options['layOut'] = $('#layOut').find('li.current').data('layout');
                var layOutHtml = '<p><span>' + $('#layOut').find('li.current').find('span').text() + '</span></p>';
                that.el.find('.result-animate').html(layOutHtml);
                if(that.isfirsChoose) {
                    that.isfirsChoose=false;
                    quyu_model.setParent([yusuan_model, huxing_model])
                }
                that.dialog_finish();

                asyn_check.get(function () {

                    that.context=1
                    flow_instance.go();
                })
            });

        },
        dialog_finish:function () {
            this.has_choose=true;
            this.key += 1;
            this.el.removeClass('current').addClass('choose-end');
            $('.layer2').addClass('none');
        }
    };
    huxing_model = $.extend({},base_Model,huxing_model)
    var quyu_model={
        el:$('.list-item li').eq(2),
        check:function () {
            var parent_check=true;
            var current_parent_key=0
            for (var i = 0; i < this.parents.length; i++) {
                current_parent_key += this.parents[i].key;
                if (!this.parents[i].has_choose) {
                    parent_check = false;
                }
            }
            var parent_change = this.parent_key!=current_parent_key;
            this.parent_key = current_parent_key;
            if(this.after_parent_check){
                this.after_parent_check(parent_check,parent_change);
            }
        },
        distictInfo:[],
        after_parent_check:function (parent_check,parent_change) {
            // if(parent_check){
            //     this.enable();
            // }
            if(parent_change){
                this.disable();
                this.has_choose=false;
                var that=this;
                $('#option_distict li').removeClass('current').removeClass('optional').addClass('disabled')
                asyn_check.get(function (data) {
                        if (data.data.distictInfo != null && data.data.distictInfo.length>0) {
                            that.distictInfo = data.data.distictInfo;
                            var canenable=false;
                            $('#option_distict').find('li.disabled').each(function (i, orgin) {
                                $(data.data.distictInfo).each(function (index, item) {
                                    if ($(orgin).data('value') == item.districtId) {
                                        canenable=true;
                                        $(orgin).removeClass('disabled').addClass('optional');
                                        $('#submitArea').addClass('disabled');
                                    }
                                });
                            });
                            if(canenable) {
                                that.enable();
                            }
                        }


                    },
                    function () {
                        console.error(arguments)
                    })
            }

        },

        dialog_show:function () {
            var that = this;
            $('.layer3').removeClass('none');
            /**
             * 选择区域
             * */
            $('.area-content').on('click', 'li.optional', function () {
                $(this).addClass('current').removeClass('optional');
                var currentChoose = $('.area-content').find('li.current').length;
                if (currentChoose > 2) {
                    $('.area-content').find('li:not(.current)').removeClass('optional').addClass('disabled');
                }
                if (currentChoose >= 1) {
                    $('#submitArea').removeClass('disabled');
                }
            });
            $('.area-content').on('click', 'li.current', function () {
                $(this).addClass('optional').removeClass('current');
                var currentChoose = $('.area-content').find('li.current').length;
                if (currentChoose < 3) {
                    $('.area-content').find('li.disabled').each(function (i, orgin) {
                        $.each(that.distictInfo,function (index, item) {
                            if ($(orgin).data('value') == item.districtId) {
                                $(orgin).removeClass('disabled').addClass('optional');
                            }
                        });
                    });
                    $('#submitArea').addClass('disabled');
                }
                if (currentChoose >= 1) {
                    $('#submitArea').removeClass('disabled');
                }

            });

            $('#submitArea').unbind('click');
            $('#submitArea').on('click', function () {
                var currentOptinos = $('#option_distict').find('li.current');
                var districtIdStr = [];
                var districtNameStr = [];
                for (var i = 0; i < currentOptinos.length; i++) {
                    districtIdStr.push($(currentOptinos[i]).data('value'));
                    districtNameStr.push($(currentOptinos[i]).text());
                }
                options['districtId'] = districtIdStr.join();
                if($('#option_distict').find('li.current').length > 0){
                    var districtHtml = '<p><span>' + districtNameStr.join(' ') + '</span></p>';
                    $('.list-item').find('li').eq(2).find('.result-animate').html(districtHtml);
                    that.dialog_finish();
                }
                asyn_check.get(function () {
                    that.context=1
                    flow_instance.go();
                })
            });
        },
        dialog_finish:function () {
            this.has_choose=true;
            this.key += 1;
            this.el.removeClass('current').addClass('choose-end');
            $('.layer3').addClass('none');
        }
    }
    quyu_model = $.extend({},base_Model,quyu_model)
    var jiating_model={
        el:$('.list-item li').eq(3),
        check:function () {
            var parent_check=true;
            var current_parent_key=0
            for (var i = 0; i < this.parents.length; i++) {
                current_parent_key += this.parents[i].key;
                if (!this.parents[i].has_choose) {
                    parent_check = false;
                }
            }
            var parent_change = this.parent_key!=current_parent_key;
            this.parent_key = current_parent_key;
            if(this.after_parent_check){
                this.after_parent_check(parent_check,parent_change);
            }

        },
        after_parent_check:function (parent_check, parent_change) {
            if(parent_check) {
                this.enable();
            }
            else if(!this.has_choose){
                this.disable();
            }
        },
        dialog_show:function () {
            var that = this;
            $('.layer4').removeClass('none');

            $('#family-box').on('click', 'li', function () {
                $(this).addClass('current').siblings().removeClass('current');
            });
            $('#submitFamily').unbind('click');
            $('#submitFamily').on('click', function () {
                options['hasChild'] = $('#hasChild').find('li.current').data('child');
                options['hasOldman'] = $('#oldMan').find('li.current').data('old-man');
                var familyHtml = '<p><span>孩子：<em>' + $('#hasChild').find('li.current').find('span').text() + '</em></span>' +
                    '<span>老人：<em>' + $('#oldMan').find('li.current').find('span').text() + '</em></span></p>';
                $('.list-item').find('li').eq(3).find('.result-animate').html(familyHtml);
                that.dialog_finish();
                asyn_check.get(function () {
                    that.context=1
                    flow_instance.go();
                })
            });
        },
        dialog_finish:function () {
            this.has_choose=true;
            this.key += 1;
            this.el.removeClass('current').addClass('choose-end');
            $('.layer4').addClass('none');
        }
    }
    jiating_model=$.extend({},base_Model,jiating_model)
    var end_model={
        el:$('.start-btn'),
        parents:[],
        context:null,
        check:function () {
            var parent_check=true;
            var current_parent_key=0
            for (var i = 0; i < this.parents.length; i++) {
                current_parent_key += this.parents[i].key;
                if (!this.parents[i].has_choose) {
                    parent_check = false;
                }
            }
            var parent_change = this.parent_key!=current_parent_key;
            this.parent_key = current_parent_key;
            if(this.after_parent_check){
                this.after_parent_check(parent_check,parent_change);
            }

        },
        disable:function () {
            this.el.addClass('none');

        },
        enable:function () {
            this.el.removeClass('none');
        },
        after_parent_check:function (parent_check, parent_change) {
            if(parent_check && asyn_check.count>0){
                this.enable();
            }
            else {
                this.disable();
            }
        }
    }
    end_model=$.extend({},base_Model,end_model)


    var flow_instance={
        init:function () {
            yusuan_model.enable();
            huxing_model.disable();
            quyu_model.disable();
            jiating_model.disable();
            end_model.disable();
            huxing_model.setParent([yusuan_model]);
            quyu_model.setParent([huxing_model]);
            jiating_model.setParent([quyu_model])
            end_model.setParent([yusuan_model,huxing_model,quyu_model,jiating_model]);
        },
        go:function () {
            yusuan_model.check();
            huxing_model.check();
            quyu_model.check();
            jiating_model.check();
            end_model.check();
        }
    }


    if(3 == options['userType']){
        options['userPortrayalType'] = 7;
        flow_instance = $.extend({},flow_instance,{
            init:function () {
                yusuan_model.enable();
                huxing_model.disable();
                quyu_model.disable();
                jiating_model.disable();
                end_model.disable();

                end_model.setParent([yusuan_model]);
            },
            go:function () {
                yusuan_model.check();
                end_model.check();
            }});
    }
    else {
        flow_instance = $.extend({},flow_instance)
    }
    flow_instance.init();

    /*
     * 滑块滑动
     * @params ele 当前dom
     * @params evt event 对象
     * @params cm 单位
     * */
    function slide(ele, evt, cm) {
        var thisDom = ele;
        var oldX = evt.originalEvent.targetTouches[0].pageX;
        var trackWidth = thisDom.parent().width();
        var left = parseInt(thisDom.css('left'));
        var sildeColor = thisDom.prevAll('.slide-color');
        var slideText = thisDom.parent().prevAll('.slide-text');
        var price = thisDom.next().children('em').text() - thisDom.prev().children('em').text();

        function tt(evt) {
            var newX = evt.originalEvent.targetTouches[0].pageX - oldX;
            thisDom.css('left', left + newX + "px");
            sildeColor.css('width', left + newX + "px");
            slideText.css('left', left + newX + "px");
            if (parseInt(thisDom.css('left')) < 0) {
                thisDom.css('left', 0);
                slideText.css('left', 0);
                sildeColor.css('width', 0)
            }
            if (parseInt(thisDom.css('left')) > trackWidth) {
                thisDom.css('left', trackWidth + "px");
                slideText.css('left', trackWidth + "px");
                sildeColor.css('width', trackWidth + "px")
            }
            var totalPrice = Math.ceil(parseInt(thisDom.css('left')) / trackWidth * price)+parseInt(thisDom.prev().children('em').text())

            if(totalPrice>=1500&&thisDom.prev().text().split("万")[0]==100){
                slideText.text('1500' + cm + '+')
            }else {
                slideText.text(Math.ceil(parseInt(thisDom.css('left')) / trackWidth * price)+parseInt(thisDom.prev().children('em').text()) + cm)
            }
        }

        $(document).on('touchmove', tt);
        $(document).on('touchend', function (evt) {
            $(document).unbind('touchmove', tt);
            ele.unbind('touchstart');
        })
    }



}

function router_city(urlparam) {
    urlparam = urlparam || '';
    if (urlparam[0] != '/') {
        urlparam = '/' + urlparam
    }
    var uri = new URI(window.location.href);
    var segmens = uri.segment();
    var city = '';
    if (segmens.length > 0) {
        city = '/' + segmens[0]
    }
    return city + urlparam
}

/**
 * 拼接参数
 * @param req
 * @param sortFlag 非排序时排序置空
 * @returns {string}
 */
function joinParams(req) {
    var targetUrl = '';

    for (var key in req) {
        if (null != req[key]) {
            targetUrl += '&' + key + "=" + req[key];
        }
    }

    if (targetUrl.length > 1) {
        targetUrl = '?' + targetUrl.substring(1);
    }

    return targetUrl;
}