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
            console.log('zqz')
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
                        var ratio = new Number(data.data.ratio / 100);
                        $('#plot_Count').find('em').text(data.data.plotCount);
                        that.count=data.data.plotCount;
                        $('#plot_Ratio').find('em').text(ratio.toFixed(3) == '0.000' ? '0' + '%' : ratio.toFixed(3) + '%');
                        $('.result-begin').addClass('none');
                        $('.result-container').removeClass('none');
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
        childs:[],
        context:null,
        disable:function (reset) {
            if(!reset)
                return
            this.context=null
            this.el.unbind('click');
            this.el.removeClass('current');
            this.el.removeClass('optional');
            this.el.removeClass('choose-end');
        },
        enable:function (reset) {
            if(!reset)
                return
            this.el.addClass('current');
            this.el.addClass('optional');
            var that = this;
            this.el.click(function () {
                that.dialog_show();
            })
        },
        next:function (resetSelf,count) {
            resetSelf = resetSelf || false
            if(typeof(count) == "undefined"){
                count=2
            }
            if(resetSelf) {
                this.check(resetSelf, count>0, false);
            }
            for(var i=0;i<this.childs.length;i++){
                this.childs[i].next(true,count-1);
            }
        },
        check:function (reset,checkParent,needBubble) {
            reset = reset || false
            needBubble = needBubble || false
            checkParent = checkParent || false
            this.disable(reset);
            var parent_check=true && checkParent;
            if(checkParent) {
                for (var i = 0; i < this.parents.length; i++) {
                    if (!this.parents[i].check(reset && needBubble,checkParent && needBubble,needBubble)) {
                        parent_check = false;
                    }
                }
            }
            if(parent_check){
                if(this.after_parent_check){
                    this.after_parent_check(reset,checkParent,needBubble);
                }
            }
            var self_check=this.context;
            //todo 验证自己的选择值是否正确

            return  self_check;
        },
    }
    var yusuan_model={
        el:$('.list-item li').eq(0),

        after_parent_check:function (reset, checkParent, needBubble) {
            this.enable(reset);
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
                    var payPriceHtml = '<p><span>首付：<em>' + payInit + '</em></span><span>月供：<em>' + monthPrice + '元</em></span></p>';
                    that.el.find('.result-animate').html(payPriceHtml);

                    /*$('.list-item').find('li').eq(0).find('.result-animate').html(payPriceHtml);*/
                }
                that.dialog_finish();
                asyn_check.get(function () {
                    that.context=1
                    that.next();
                })


            })
        },
        dialog_finish:function () {
            // this.el.siblings().removeClass('current');
            this.el.removeClass('current').addClass('choose-end');
            $('.layer1').addClass('none');
        }
    };
    yusuan_model = $.extend({},base_Model,yusuan_model)
    var huxing_model={
        el:$('.list-item li').eq(1),
        after_parent_check:function (reset, checkParent, needBubble) {
            this.enable(reset);
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

                that.dialog_finish();
                asyn_check.get(function () {
                    that.context=1
                    that.next();
                })
            });

        },
        dialog_finish:function () {
            this.el.removeClass('current').addClass('choose-end');
            $('.layer2').addClass('none');
        }
    };
    huxing_model = $.extend({},base_Model,huxing_model)
    var quyu_model={
        el:$('.list-item li').eq(2),
        distictInfo:[],
        after_parent_check:function (reset, checkParent, needBubble) {
            var that=this;
            $('#option_distict li').removeClass('current').removeClass('optional').addClass('disabled')
            asyn_check.get(function (data) {
                    if (data.data.distictInfo != null) {
                        that.distictInfo = data.data.distictInfo;
                        $('#option_distict').find('li.disabled').each(function (i, orgin) {
                            $(data.data.distictInfo).each(function (index, item) {
                                if ($(orgin).data('value') == item.districtId) {
                                    $(orgin).removeClass('disabled').addClass('optional');
                                    $('#submitArea').addClass('disabled');
                                }
                            });
                        });
                    }
                    that.enable(reset);
                },
                function () {
                    console.error(arguments)
                })
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
                if(options['districtId'].split(",").length>=1){
                    var districtHtml = '<p><span>' + districtNameStr.join(' ') + '</span></p>';
                    $('.list-item').find('li').eq(2).find('.result-animate').html(districtHtml);
                    that.dialog_finish();
                }
                asyn_check.get(function () {
                    that.context=1
                    that.next();
                })
            });
        },
        dialog_finish:function () {
            this.el.removeClass('current').addClass('choose-end');
            $('.layer3').addClass('none');
        }
    }
    quyu_model = $.extend({},base_Model,quyu_model)
    var jiating_model={
        el:$('.list-item li').eq(3),
        after_parent_check:function (reset, checkParent, needBubble) {
            this.enable(reset);
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
                    that.next();
                })
            });
        },
        dialog_finish:function () {
            this.el.removeClass('current').addClass('choose-end');
            $('.layer4').addClass('none');
        }
    }
    jiating_model=$.extend({},base_Model,jiating_model)
    var end_model={
        el:$('.start-btn'),
        parents:[],
        childs:[],
        context:null,
        disable:function (reset) {
            if(!reset)
                return
            this.context=null
            this.el.addClass('none');

        },
        enable:function (reset) {
            if(!reset)
                return
            this.el.removeClass('none');
            var that = this;
            this.el.click(function () {
            })

        },
        next:function (resetSelf,count) {
            resetSelf = resetSelf || false
            count = count || 1
            if(resetSelf) {
                this.check(resetSelf, count>0, false);
            }
            for(var i=0;i<this.childs.length;i++){
                this.childs[i].next(true,count-1);
            }
        },
        check:function (reset,checkParent,needBubble) {
            reset = reset || false
            needBubble = needBubble || false
            checkParent = checkParent || false
            this.disable(reset);
            var parent_check=true && checkParent;
            if(checkParent) {
                for (var i = 0; i < this.parents.length; i++) {
                    if (!this.parents[i].check(reset && needBubble,checkParent && needBubble,needBubble)) {
                        parent_check = false;
                    }
                }
            }
            if(parent_check && asyn_check.count>0){
                var that=this;
                that.enable(reset);
            }
            var self_check=parent_check;
            //todo 验证自己的选择值是否正确

            return  self_check && asyn_check.count>0;
        }
    }
    if(3 == options['userType']){
        options['userPortrayalType'] = 7;
        yusuan_model.childs=[end_model]
        end_model.parents=[yusuan_model]
    }
    else {
        yusuan_model.childs=[quyu_model]
        huxing_model.childs=[quyu_model]
        quyu_model.parents=[yusuan_model,huxing_model]
        quyu_model.childs=[end_model]
        jiating_model.childs=[end_model]
        end_model.parents=[quyu_model,jiating_model]
    }

    end_model.check(true,true,true);



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