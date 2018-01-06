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
    <title>智能找房 预见所想</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/jquery.fullpage.min.new.js"></script>
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
    <div class="section page3 ">
        <div class="bgbox bg3">
            <div class="page-content">
                <div class="result-text">
                    <div class="result-content">
                        <div class="result-line"></div>
                        <div class="result-begin">
                            <p>开启智能找房之旅</p>
                        </div>
                        <div class="result-container none">
                            <p id="plot_Count">为您匹配了<em class="high-light-red">0</em>个小区</p>
                            <p id="plot_Ratio">有<em class="high-light-red">0%</em>的用户和您的需求相同</p>
                        </div>
                    </div>
                </div>
                <ul class="list-item">
                    <li class="current">
                        <div class="animate-box">
                            <div class="trangle-animate"></div>
                            <p>预算</p>
                        </div>
                        <div class="result-animate"></div>
                    </li>
                    <li>
                        <div class="animate-box">
                            <div class="trangle-animate"></div>
                            <p>户型</p>
                        </div>
                        <div class="result-animate"></div>
                    </li>
                    <li>
                        <div class="animate-box">
                            <div class="trangle-animate"></div>
                            <p>区域</p>
                        </div>
                        <div class="result-animate"></div>
                    </li>
                    <li>
                        <div class="animate-box">
                            <div class="trangle-animate"></div>
                            <p>家庭</p>
                        </div>
                        <div class="result-animate"></div>
                    </li>
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
                                    <span class="slide-text">100万</span>
                                    <div class="slider-track">
                                        <div class="slide-color"></div>
                                        <span class="min"><em>100</em>万</span>
                                        <span class="slider-thumb"></span>
                                        <span class="max"><em>3000</em>万</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="month-content none">
                            <div class="slide-item">
                                <label class="layer-type-text">首付</label>
                                <div class="slide-line down-slide">
                                    <span class="slide-text">50万</span>
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
                                        <span class="max"><em>50000</em></span>
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
                                <button class="modify-reset">修改预算</button>
                            </div>
                        </div>
                        <ul id="option_distict" class="area-content clear">
                            <li class="disabled" data-value="105041">石景山</li>
                            <li class="disabled" data-value="105045">顺义</li>
                            <li class="disabled" data-value="105043">大兴</li>
                            <li class="disabled" data-value="105034">海淀</li>
                            <li class="disabled" data-value="105035">朝阳</li>
                            <li class="disabled" data-value="105036">东城</li>
                            <li class="disabled" data-value="105037">西城</li>
                            <li class="disabled" data-value="105040">丰台</li>
                            <li class="disabled" data-value="105046">昌平</li>
                            <li class="disabled" data-value="105047">密云</li>
                            <li class="disabled" data-value="105048">怀柔</li>
                            <li class="disabled" data-value="105049">延庆</li>
                            <li class="disabled" data-value="105050">平谷</li>
                            <li class="disabled" data-value="105051">门头沟</li>
                            <li class="disabled" data-value="105044">通州</li>
                            <li class="disabled" data-value="105042">房山</li>
                            <li class="disabled" data-value="106013">北京周边</li>
                            <li class="disabled" data-value="106022">武清</li>
                            <li class="disabled" data-value="106023">燕郊</li>
                            <li class="disabled" data-value="106024">香河</li>
                            <li class="disabled" data-value="106025">大厂</li>
                            <li class="disabled" data-value="106026">固安</li>
                            <li class="disabled" data-value="106027">永清</li>
                            <li class="disabled" data-value="106028">廊坊</li>
                            <li class="disabled" data-value="106029">霸州</li>
                            <li class="disabled" data-value="106030">涿州</li>
                            <li class="disabled" data-value="106031">涞水</li>
                            <li class="disabled" data-value="106032">怀来</li>
                            <li class="disabled" data-value="106033">崇礼</li>
                            <li class="disabled" data-value="106034">秦皇岛</li>
                            <li class="disabled" data-value="106035">天津</li>
                        </ul>
                        <div class="layer-footer">
                            <button type="button" class="button disabled" id="submitArea">确定</button>
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
                                    <li data-child="KG"><span>0-3岁</span></li>
                                    <li data-child="L"><span>4-10岁</span></li>
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
    <div class="section page4 ">
        <div class="bgbox bg4">
            <div class="page-content">
                <div class="city-title">
                    <p>北京</p>
                </div>
                <div class="animate-wrapper">
                    <div class="animate-item-1">
                        <div class="animate-big-box" id="left_circle_div">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box">
                            <#-- 扫描红线（改变top） -->
                                <div class="animate-line"></div>
                                <div class="text-box" style="display: none;">
                                    <p><em>7</em>年</p>
                                    <p><em>305586</em>位</p>
                                    <p>用户择居及生活样本</p>
                                </div>
                            </div>
                        </div>
                        <div class="animate-small-box">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box">
                                <i></i>
                            </div>
                        </div>
                    </div>
                    <div class="animate-item-2">
                        <div class="animate-big-box">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box" id="center_circle_div">
                                <#-- 扫描红线（改变top） -->
                                <div class="animate-line"></div>
                                <div class="text-box"  style="display: none">
                                    <p><em>8123</em>个社区</p>
                                    <p><em>40397</em>套房产</p>
                                    <p><em>142</em>类生活配套设施</p>
                                </div>
                            </div>
                        </div>
                        <div class="animate-small-box">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box">
                                <i></i>
                            </div>
                        </div>
                    </div>
                    <div class="animate-item-3">
                        <div class="animate-big-box" id="right_circle_div">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box">
                            <#-- 扫描红线（改变top） -->
                                <div class="animate-line"></div>
                                <div class="text-box" style="display: none">
                                    <p><em>1</em>秒</p>
                                    <p><em>962837</em>次运算</p>
                                </div>
                            </div>
                        </div>
                        <div class="animate-small-box">
                            <div class="animate-ring-box"></div>
                            <div class="animate-border-box">
                                <i></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="button-bottom">
                    <a class="button-box" id="button_report" href="javascript:void(0)">
                        <#-- 百分比进度显示 -->
                        <div class="loading-number">0%</div>
                        <#-- 黑色进度条（改变width） -->
                        <div class="button-mask-box">
                            <div class="button-mask"></div>
                        </div>
                        <div class="button"></div>
                        <p class="loading-text">正在生成用户画像</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${staticurl}/js/URI.min.js"></script>
<script src="${staticurl}/js/intelligent-choose.js"></script>
<script type="text/javascript">
    var rada_animit = {
        timers: [],
        id: 0,
        random: function (max, min) {
            return parseInt(Math.random() * max) + min;
        },
        center_process: function () {
            if (!rada_animit.center_process_start) {
                rada_animit.center_process_start = true
            }
            else {
                return;
            }

            $('#center_circle_div .animate-line').hide();
            clearInterval($('#center_circle_div .animate-line').eq(0).data("lada_timer"));
            $('#center_circle_div .text-box').show();
            $('#center_circle_div').prev('.animate-ring-box').addClass("active");
            var timer = setInterval(function () {

                $('#center_circle_div em').eq(0).text(rada_animit.random(10000, 8000));
                $('#center_circle_div em').eq(1).text(rada_animit.random(15000, 30000));
                $('#center_circle_div em').eq(2).text(rada_animit.random(150, 50));
            }, 100);
            setTimeout(function () {
                clearInterval(timer)
            }, 4000)
        },
        left_process: function () {
            if (!rada_animit.left_process_start) {
                rada_animit.left_process_start = true
            }
            else {
                return;
            }
            $('#left_circle_div .animate-line').hide();
            clearInterval($('#left_circle_div .animate-line').eq(0).data("lada_timer"));
            $('#left_circle_div .text-box').show();
            $('#left_circle_div .animate-ring-box').addClass("active");
            var timer = setInterval(function () {
                $('#left_circle_div em').eq(0).text(rada_animit.random(10, 1));
                $('#left_circle_div em').eq(1).text(rada_animit.random(1000000, 500000));

            }, 100);
            setTimeout(function () {
                clearInterval(timer)
            }, 4000)
        },
        right_process: function () {
            if (!rada_animit.right_process_start) {
                rada_animit.right_process_start = true
            }
            else {
                return;
            }
            $('#right_circle_div .animate-line').hide();
            clearInterval($('#right_circle_div .animate-line').eq(0).data("lada_timer"));
            $('#right_circle_div .text-box').show();
            $('#right_circle_div .animate-ring-box').addClass("active");
            var timer = setInterval(function () {
                $('#right_circle_div em').eq(1).text(rada_animit.random(300000, 200000));

            }, 100);
            setTimeout(function () {
                clearInterval(timer)
            }, 4000)
        },
        stop_all: function () {
            for (var i = 0; i < rada_animit.timers.length; i++) {
                clearInterval(rada_animit.timers[i]);
            }
        },
        init: function () {
            $('.animate-line').each(function () {
                var that = $(this);
                var step = 20;
                var timer = setInterval(function () {
                    var maxtop = that.parent().parent().height();

                    if (parseInt(that.css('top')) > maxtop) {
                        that.css('top', maxtop + 'px');
                        step = -20
                    }
                    else if (parseInt(that.css('top')) < 0) {
                        that.css('top', '0px');
                        step = 20
                    }
                    that.css('top', parseInt(that.css('top')) + step + 'px');
                }, 100);
                that.data("lada_timer", timer);
            });
            var process = 0;
            var process_timer = setInterval(function () {
                $('.button-mask').width((process + 1) + 'px');
                process += 4;
                $('.loading-number').css('left', process + 'px');
                var rate = parseInt(process * 100 / parseInt($('#button_report').width()));
                if (rate > 10) {
                    rada_animit.center_process();
                }
                if (rate > 25) {
                    rada_animit.left_process();
                }
                if (rate > 40) {
                    rada_animit.right_process();
                }
                $('.loading-number').text(parseInt(process * 100 / parseInt($('#button_report').width())) + '%');
                if (process >= parseInt($('#button_report').width())) {
                    if (rada_animit.id) {
                        $('.loading-number').hide();
                        $('#button_report .button-mask').text('查看报告');
                        rada_animit.stop_all();
                        clearInterval(process_timer);
                        $("#button_report").click(function () {
                            window.location.replace(router_city('/findhouse/showMyReport/') + rada_animit.id)
                        });
                    }
                }
            }, 80)
        }
    };


</script>
</body>
</html>