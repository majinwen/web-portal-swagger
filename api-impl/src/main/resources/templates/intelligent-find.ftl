<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script>
        (function(h,k){var p=h.document;var b=p.documentElement;var m=p.querySelector('meta[name="viewport"]');var e=p.querySelector('meta[name="flexible"]');var q=0;var d=0;var f;var j=k.flexible||(k.flexible={});if(m){console.warn("将根据已有的meta标签来设置缩放比例");var g=m.getAttribute("content").match(/initial\-scale=([\d\.]+)/);if(g){d=parseFloat(g[1]);q=parseInt(1/d)}}else{if(e){var i=e.getAttribute("content");if(i){var c=i.match(/initial\-dpr=([\d\.]+)/);var o=i.match(/maximum\-dpr=([\d\.]+)/);if(c){q=parseFloat(c[1]);d=parseFloat((1/q).toFixed(2))}if(o){q=parseFloat(o[1]);d=parseFloat((1/q).toFixed(2))}}}}if(!q&&!d){var n=h.devicePixelRatio;if(n>=3&&(!q||q>=3)){q=3}else{if(n>=2&&(!q||q>=2)){q=2}else{q=1}}d=1/q}b.setAttribute("data-dpr",q);if(!m){m=p.createElement("meta");m.setAttribute("name","viewport");m.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no");if(b.firstElementChild){b.firstElementChild.appendChild(m)}else{var a=p.createElement("div");a.appendChild(m);p.write(a.innerHTML)}}function l(){var r=b.getBoundingClientRect().width;if(r/q>540){r=540*q}var s=r/10;b.style.fontSize=s+"px";j.rem=h.rem=s}h.addEventListener("resize",function(){clearTimeout(f);f=setTimeout(l,300)},false);h.addEventListener("pageshow",function(r){if(r.persisted){clearTimeout(f);f=setTimeout(l,300)}},false);if(p.readyState==="complete"){p.body.style.fontSize=12*q+"px"}else{p.addEventListener("DOMContentLoaded",function(r){p.body.style.fontSize=12*q+"px"},false)}l();j.dpr=h.dpr=q;j.refreshRem=l})(window,window["lib"]||(window["lib"]={}));
    </script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css">
    <link rel="stylesheet" href="${staticurl}/css/intelligent.css">
    <title>筛选</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/jquery.fullPage.min.js"></script>
    <script>
        $(function () {
            $('.list-item').on('click', 'li', function () {
                var index = $(this).index() + 1;
                $('.layer' + index).removeClass('none');
            });
            $('.layer').click(function () {
                $('.layer').addClass('none');
            });
            $('.layer-content').click(function (e) {
                e.stopPropagation();
            });
            $('.month-slide').on('touchstart', '.slider-thumb', function (evt) {
               slide($(this), evt, '')
            });
            $('.down-slide').on('touchstart', '.slider-thumb', function (evt) {
                slide($(this), evt, '万')
            });
            $('.total-slide').on('touchstart', '.slider-thumb', function (evt) {
                slide($(this), evt, '万')
            });

            /*
            * 滑块滑动
            *
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

                $(document).on('touchmove', function (evt) {
                    var newX = evt.originalEvent.targetTouches[0].pageX - oldX;
                    thisDom.css('left', left + newX + "px");
                    sildeColor.css('width', left + newX + "px");
                    slideText.css('left', left + newX + "px");
                    if ( parseInt(thisDom.css('left')) < 0) {
                        thisDom.css('left', 0);
                        slideText.css('left', 0);
                        sildeColor.css('width', 0)
                    }
                    if (parseInt(thisDom.css('left')) > trackWidth) {
                        thisDom.css('left', trackWidth + "px");
                        slideText.css('left', trackWidth + "px");
                        sildeColor.css('width', trackWidth + "px")
                    }
                    slideText.text(Math.ceil(parseInt(thisDom.css('left')) / trackWidth * price) + cm)
                });
                $(document).on('touchend', function (evt) {
                    $(document).unbind('touchmove');
                    $(document).unbind('touchstart')
                })
            }
        })
    </script>
</head>
<body>
<div id="superContainer">
    <div class="section page1 active">
        <div class="bgbox bg1">
            <div class="page-content">
                <h1>智能找房</h1>
                <p>房产头条大数据<br>根据您的需求推荐最匹配的小区和房源</p>
                <button type="button" class="button begin">开始体验</button>
            </div>
        </div>
    </div>
    <div class="section page2">
        <div class="bgbox bg2">
            <div class="page-content">
                <h2>请选择购房目的</h2>
                <p>方便为您提供更精准的服务</p>
                <div class="choose-wrapper">
                    <div class="choose-item-box">
                        <div class="box-line-triangle"></div>
                        <div class="choose-item-cont">
                            <div class="dashed-line one"></div>
                            <p data-user-type="1">
                                <span>自住</span>
                                <span>刚需</span>
                                <span>交通便利</span>
                            </p>
                        </div>
                    </div>
                    <div class="clear">
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <div class="dashed-line two"></div>
                                <p data-user-type="2">
                                    <span>自住</span>
                                    <span>改善</span>
                                    <span>配套完善</span>
                                </p>
                            </div>
                        </div>
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <div class="dashed-line three"></div>
                                <p data-user-type="3">
                                    <span>出租</span>
                                    <span>保值</span>
                                    <span>回报率</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="button" id="userTypeSubmit">确定</button>
            </div>
        </div>
    </div>
    <div class="section page3">
        <div class="bgbox bg3">
            <div class="page-content">
                <div class="result-text">
                    <div class="result-content">
                        <div class="result-line"></div>
                        <div class="result-begin">
                            <p>开启智能找房之旅</p>
                        </div>
                        <div class="result-container none">
                            <p id="plot_Count">为您匹配了<em class="high-light-red"></em>个小区</p>
                            <p id="plot_Ratio">有<em class="high-light-red"></em>的用户和您的需求相同</p>
                        </div>
                    </div>
                </div>
                <ul class="list-item">
                    <li>预算</li>
                    <li>户型</li>
                    <li>区域</li>
                    <li>家庭</li>
                </ul>
                <div class="layer layer1 none">
                    <div class="layer-content">
                        <div class="layer-header">
                            <p>告诉我您的预算</p>
                            <div class="price-tab">
                                <span class="total-price current">总价</span>
                                <span>|</span>
                                <span class="month-price">首付+月供</span>
                            </div>
                        </div>
                        <div class="total-conent">
                            <div class="slide-item">
                                <label class="layer-type-text">总价</label>
                                <div class="slide-line total-slide">
                                    <span class="slide-text">0万</span>
                                    <div class="slider-track">
                                        <div class="slide-color"></div>
                                        <span class="min"><em>50</em>万</span>
                                        <span class="slider-thumb"></span>
                                        <span class="max"><em>1000</em>万</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="month-content none">
                            <div class="slide-item">
                                <label class="layer-type-text">首付</label>
                                <div class="slide-line down-slide">
                                    <span class="slide-text">0万</span>
                                    <div class="slider-track">
                                        <div class="slide-color"></div>
                                        <span class="min"><em>50</em>万</span>
                                        <span class="slider-thumb"></span>
                                        <span class="max"><em>1000</em>万</span>
                                    </div>
                                </div>
                            </div>
                            <div class="slide-item">
                                <label class="layer-type-text">月供</label>
                                <div class="slide-line month-slide">
                                    <span class="slide-text">0</span>
                                    <div class="slider-track">
                                        <div class="slide-color"></div>
                                        <span class="min"><em>0</em></span>
                                        <span class="slider-thumb"></span>
                                        <span class="max"><em>30000</em></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layer-footer">
                            <button type="button" class="button" id="submitPrice">确定</button>
                        </div>
                    </div>
                </div>
                <div class="layer layer2 none">
                    <div class="layer-content">
                        <div class="layer-header">
                            <p>告诉我您想要的户型</p>
                        </div>
                        <ul class="content-list" id="layOut">
                            <li data-layout="1"><span>一居</span></li>
                            <li data-layout="2"><span>两居</span></li>
                            <li class="current" data-layout="3"><span>三居</span></li>
                            <li data-layout="4"><span>四居</span></li>
                            <li data-layout="5"><span>五居</span></li>
                            <li data-layout="6"><span>五居以上</span></li>
                        </ul>
                        <div class="layer-footer">
                            <button type="button" class="button" id="submitHouseType">确定</button>
                        </div>
                    </div>
                </div>
                <div class="layer layer3 none">
                    <div class="layer-content">
                        <div class="layer-header">
                            <p>选择您的意向区域</p>
                            <div class="area-tips">
                                <span>意向区域不能点么</span>
                                <button>修改预算</button>
                            </div>
                        </div>
                        <ul class="area-content clear">
                            <li class="optional">东城</li>
                            <li class="optional">西城</li>
                            <li class="optional">海淀</li>
                            <li class="disabled">朝阳</li>
                            <li class="disabled">丰台</li>
                            <li class="disabled">门头沟</li>
                            <li class="optional">石景山</li>
                            <li class="optional">房山</li>
                            <li class="optional">顺义</li>
                            <li class="optional">通州</li>
                            <li class="optional">昌平</li>
                            <li class="optional">大兴</li>
                            <li class="optional">怀柔</li>
                            <li class="optional">平谷</li>
                            <li class="optional">延庆</li>
                            <li class="optional">密云</li>
                            <li class="optional">北京周边</li>
                        </ul>
                        <div class="layer-footer">
                            <button type="button" class="button" id="submitArea">确定</button>
                        </div>
                    </div>
                </div>
                <div class="layer layer4 none">
                    <div class="layer-content">
                        <div class="layer-header">
                            <p>告诉我您的家庭结构</p>
                        </div>
                        <div id="family-box">
                            <div class="child-box">
                                <label>孩子</label>
                                <ul class="content-list" id="hasChild">
                                    <li class="current" data-child="0"><span>无</span></li>
                                    <li data-child="1"><span>0-3岁</span></li>
                                    <li data-child="2"><span>4-10岁</span></li>
                                </ul>
                            </div>
                            <div class="old-man-box">
                                <label>老人</label>
                                <ul class="choose-radio" id="oldMan">
                                    <li data-old-man='0' class="current"><i></i><span>无</span></li>
                                    <li data-old-man='1'><i></i><span>有</span></li>
                                </ul>
                            </div>
                        </div>
                        <div class="layer-footer">
                            <button type="button" class="button" id="submitFamily">确定</button>
                        </div>
                    </div>
                </div>
                <div class="start-btn none">
                    <p>启动</p>
                </div>
            </div>
        </div>
    </div>
    <div class="section page4">
        <div class="bgbox bg4">
            <div class="page-content">
                <div class="text-content">
                    <p>数据多</p>
                    <p>房源多</p>
                    <p>用户样本多</p>
                    <p>处理速度快</p>
                    <p>技术</p>
                    <p>技术</p>
                </div>
                <a href="${router_city('/findhouse/showUserPortrayal')}" class="button">打开报告</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#superContainer').fullpage({
            fitToSection: true,
            resize: false,
            onLeave: function (index, nextIndex, direction) {
                if (nextIndex == 3) {
                    $.fn.fullpage.setAllowScrolling(true, 'down');
                }
            }
        });

        $.fn.fullpage.setAllowScrolling(false, 'up, down');

        $('.begin').on('click', function () {
            $.fn.fullpage.moveSectionDown();
        });

        // 用户类型选择
        $('.choose-wrapper').on('click', '.choose-item-box', function () {
            $('.choose-wrapper').find('.choose-item-box').removeClass('current');
            $(this).addClass('current');
        });

        if ($('.choose-wrapper').find('.current').length > 0) {
            $('.button').on('click', function () {
                $.fn.fullpage.moveSectionDown();
            });
        }
        // 提交选中用户类型
        $('#userTypeSubmit').on('click', function () {
            if ($('.choose-wrapper').find('.current').length > 0){
                var params = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');
                var userTypeUrl = 'userType=' + params;
                $.fn.fullpage.moveSectionDown();
                console.log(userTypeUrl);
                $.ajax({
                    type: "GET",
                    url: "${router_city('/findhouse/xuanzeleixing')}",
                    data: userTypeUrl,
                    success: function(data){
//                        alert(data.data+"这是啥玩意！！！");
                    }
                });
                $('.result-begin').addClass('none');
                $('.result-container').removeClass('none');
            }
        });
        // 提交选中用户类型
        var params;
        $('#userTypeSubmit').on('click', function () {
            if ($('.choose-wrapper').find('.current').length > 0) {
                params = $('.choose-wrapper').find('.choose-item-box.current').find('p').data('user-type');
                var userTypeUrl = 'userType=' + params;
                $.fn.fullpage.moveSectionDown();
                console.log(userTypeUrl);
                $('.result-begin').addClass('none');
                $('.result-container').removeClass('none');
            }
        });

        // 切换预算
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
        // 提交预算
        var priceAnduserTppeUrl;
        var userPortrayalType;
        $('#submitPrice').on('click', function () {
            if ($('.total-price').hasClass('current')) {
                $(this).parents('.layer').addClass('none');
                var price = $('.total-conent').find('.slide-text').text();
                priceAnduserTppeUrl = 'userType=' + params + '&preconcTotal=' + price;
                console.log(priceAnduserTppeUrl);
                $.ajax({
                    type: 'GET',
                    url: '${router_city('/findhouse/goCheckPrice')}',
                    data: priceAnduserTppeUrl,
                    success: function (data) {
                        console.log(data.data);
                        $('#plot_Count').find('em').html(data.data.plotCount);
                        $('#plot_Ratio').find('em').html(data.data.ratio);
                        params = data.data.userType;
                        console.log(params);
                        //用户类型是第三类
                        if (params == 3) {
                            ////将第七种画像附给当前用户userPortrayalType
                            userPortrayalType = data.data.userPortrayalType = 7;
                            priceAnduserTppeUrl += '&userPortrayalType=' + userPortrayalType;
                            console.log(priceAnduserTppeUrl);
                            //跳转到用户画像页面
                            //为实现
                        }
                    }

                });
                $('.result-begin').addClass('none');
                $('.result-container').removeClass('none');
                return
            } else {
                $(this).parents('.layer').addClass('none');
                var downPrice = $('.down-slide').find('.slide-text').text();
                var monthPrce = $('.month-slide').find('.slide-text').text();
                priceAnduserTppeUrl = 'userType=' + params + '&downPayMent=' + downPrice + '&monthPayMent=' + monthPrce;
                console.log('首付=' + downPrice, '月供=' + monthPrce);
                $.ajax({
                    type: 'GET',
                    url: '${router_city('/findhouse/goCheckPrice')}',
                    data: priceAnduserTppeUrl,
                    success: function (data) {
                        console.log(data.data);
                        $("#plot_Count").find("em").html(data.data.plotCount);
                        $("#plot_Ratio").find("em").html(data.data.ratio);
                        params = data.data.userType;
                        console(params);
                        //用户类型是第三类
                        if (params == 3) {
                            ////将第七种画像附给当前用户userPortrayalType
                            userPortrayalType = data.data.userPortrayalType = 7;
                            priceAnduserTppeUrl += '&userPortrayalType=' + userPortrayalType;
                            console.log(priceAnduserTppeUrl);
                            //跳转到用户画像页面
                            //为实现
                        }
                    }

                });
                $('.result-begin').addClass('none');
                $('.result-container').removeClass('none');
            }
        });

        // 选择户型
        $('.content-list').on('click', 'li', function () {
            $(this).addClass('current').siblings().removeClass('current');
        });
        // 提交户型
        var next2;
        $('#submitHouseType').on('click', function () {
            $(this).parents('.layer').addClass('none');
            var params = $('#layOut').find('li.current').data('layout');
            var layOut = '&layOut=' + params;
            next2 = priceAnduserTppeUrl + layOut;
            $.ajax({
                type: 'GET',
                url: '${router_city('/findhouse/userCheckCategoryPage')}',
                data: next2,
                success: function (data) {
                    console.log(data.data);
                    $("#plot_Count").find("em").html(data.data.plotCount);
                    $("#plot_Ratio").find("em").html(data.data.ratio);
                    params = data.data.userType;
                    next2 += '&hospitalFlag=' + data.data.hospitalFlag + '&schoolFlag=' + data.data.schoolFlag + '&ratio=' +data.data.ratio;
                    console.log(next2);
                    //区域数组
                    console.log(data.data.distictList);
                    //没写完

                }
            })
        });

        // 选择区域
        $('.area-content').on('click', 'li.optional', function () {
            $(this).addClass('current').removeClass('optional');
            var currentChoose = $('.area-content').find('li.current').length;
            if (currentChoose > 2) {
                $('.area-content').find('li:not(.current)').removeClass('optional').addClass('disabled')
            }
        });
        // 提交选中区域
        var next4;
        $('#submitArea').on('click', function () {
            if ($('.area-content').find('li.current').length == 3) {
                $(this).parents('.layer').addClass('none');
                next4 += next2 + '&districtId=' + '106013';
                console.log(1);
                $.ajax({
                    type: 'GET',
                    url: '${router_city('/findhouse/queryPlotCountByDistrict')}',
                    data: next4,
                    success: function (data) {
                        console.log(data);
                        //此处要判断是否是一居用户
                        if(data.data.layOut==1){
                            //如果是一居用户则直接跳转到过渡页 如果不是则去家庭页面

                        }

                    }
                });
                $('.result-begin').addClass('none');
                $('.result-container').removeClass('none');
            }
        });

        // 选择家庭结构
        $('#family-box').on('click', 'li', function () {
            $(this).addClass('current').siblings().removeClass('current');
        });
        // 提交选中家庭结构内容
        var next3;
        $('#submitFamily').on('click', function () {
            $(this).parents('.layer').addClass('none');
            var childParams = $('#hasChild').find('li.current').data('child');
            var oldManParams = $('#oldMan').find('li.current').data('old-man');
            var familyStructureUrl = '&hasChild=' + childParams + '&oldMan=' + oldManParams;
            next3 = next4 + familyStructureUrl;
            console.log(familyStructureUrl);
            //直接到过度页面
            /*$.ajax({
                type: 'GET',
                url: '',
                data: layOut,
                success: function (data) {
                    console.log(data);
                }
            })*/
            $('.result-begin').addClass('none');
            $('.result-container').removeClass('none');
        });
    })
</script>
</body>
</html>