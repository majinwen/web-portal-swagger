<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <script>
        (function(h,k){var p=h.document;var b=p.documentElement;var m=p.querySelector('meta[name="viewport"]');var e=p.querySelector('meta[name="flexible"]');var q=0;var d=0;var f;var j=k.flexible||(k.flexible={});if(m){console.warn("将根据已有的meta标签来设置缩放比例");var g=m.getAttribute("content").match(/initial\-scale=([\d\.]+)/);if(g){d=parseFloat(g[1]);q=parseInt(1/d)}}else{if(e){var i=e.getAttribute("content");if(i){var c=i.match(/initial\-dpr=([\d\.]+)/);var o=i.match(/maximum\-dpr=([\d\.]+)/);if(c){q=parseFloat(c[1]);d=parseFloat((1/q).toFixed(2))}if(o){q=parseFloat(o[1]);d=parseFloat((1/q).toFixed(2))}}}}if(!q&&!d){var n=h.devicePixelRatio;if(n>=3&&(!q||q>=3)){q=3}else{if(n>=2&&(!q||q>=2)){q=2}else{q=1}}d=1/q}b.setAttribute("data-dpr",q);if(!m){m=p.createElement("meta");m.setAttribute("name","viewport");m.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no");if(b.firstElementChild){b.firstElementChild.appendChild(m)}else{var a=p.createElement("div");a.appendChild(m);p.write(a.innerHTML)}}function l(){var r=b.getBoundingClientRect().width;if(r/q>540){r=540*q}var s=r/10;b.style.fontSize=s+"px";j.rem=h.rem=s}h.addEventListener("resize",function(){clearTimeout(f);f=setTimeout(l,300)},false);h.addEventListener("pageshow",function(r){if(r.persisted){clearTimeout(f);f=setTimeout(l,300)}},false);if(p.readyState==="complete"){p.body.style.fontSize=12*q+"px"}else{p.addEventListener("DOMContentLoaded",function(r){p.body.style.fontSize=12*q+"px"},false)}l();j.dpr=h.dpr=q;j.refreshRem=l})(window,window["lib"]||(window["lib"]={}));
    </script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css">
    <link rel="stylesheet" href="${staticurl}/css/intelligent-report.css">
    <title>报告页</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/jquery.fullPage.min.js"></script>
    <script src="${staticurl}/js/modernizr.custom.js"></script>
    <script src="${staticurl}/js/echarts.min.js"></script>
</head>
<body>
<div id="superContainer">
    <#--<div class="section page1 active">
        <div class="bgbox bg1">
            <div class="page-content">
                <div class="user-header-box">
                    <div class="user-line-triangle"></div>
                    <img src="/static/images/intelligent/user-header.png" alt="用户头像">
                </div>
                <div class="word-cont">
                    <p>繁华都市中，每个人都想有自己的空间。多年打拼后，您终于开始寻找第一个家园。我们明白，您挣的每分每厘都得来不易，凝聚无数的早起通勤和深夜加班。因此我们根据您的条件，为您精心挑选最具性价比的社区，可以让你拥有第一个舒适小家，争取做到：</p>
                    <ol>
                        <li>-    尽量离交通站近，睡多一点</li>
                        <li>-    餐饮便利，到家能吃口热饭</li>
                        <li>-    有休闲地儿，周末看场大片</li>
                    </ol>
                </div>
                <div class="down-triangle"></div>
            </div>
        </div>
    </div>-->
    <div class="section page2 active">
        <div class="page-content">
            <div class="module-item">
                <div class="report-title-type1">
                    <p>目标市场价格走势</p>
                </div>
                <div class="report-caption">
                    <p>根据您的检索条件</p>
                    <p>总价<em class="inte-color-red">1000万</em>左右的房源市场为您的目标市场</p>
                </div>
                <div class="echart-box">
                    <div id="priceChart"></div>
                </div>

                <ul class="results-contrast">
                    <li>
                        <span class="contrast-mark type-red">涨</span>
                        <p>目标市场环比 最高涨幅为<em class="inte-color-red" id="maxTarget"></em>，<em id="priceMaxCompare"></em>于北京市场均价涨幅</p>
                    </li>
                    <li>
                        <span class="contrast-mark type-dark-green">跌</span>
                        <p>目标市场环比 最高跌幅为<em class="inte-color-red" id="minTarget"></em>，<em id="priceMinCompare"></em>于北京市场均价跌幅</p>
                    </li>
                </ul>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>目标市场供需情况</p>
                </div>
                <div class="report-caption">
                    <p>根据您的检索条件</p>
                    <p>总价<em class="inte-color-red">1000万</em>左右的房源市场为您的目标市场</p>
                </div>
                <div class="echart-box">
                    <div id="marketChart"></div>
                </div>

                <ul class="results-contrast">
                    <li>
                        <span class="contrast-mark type-red">高</span>
                        <p>目标市场 月度最高成交量为<em id="maxVolume" class="inte-color-red"></em>，为北京市场的<em id="maxVolumeRatio" class="inte-color-red"></em></p>
                    </li>
                    <li>
                        <span class="contrast-mark type-dark-green">低</span>
                        <p>目标市场 月度最低成交量为<em id="minVolume" class="inte-color-red"></em>，为北京市场的<em id="minVolumeRatio" class="inte-color-red"></em></p>
                    </li>
                    <li>
                        <span class="contrast-mark type-yellow">均</span>
                        <p>目标市场 年平均成交量为<em id="averageVolume" class="inte-color-red"></em>，为北京市场的<em id="averageVolumeRatio" class="inte-color-red"></em></p>
                    </li>
                </ul>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>智能推荐结果</p>
                </div>
                <div class="report-caption">
                    <p>您的意向区域中，有<em class="inte-color-red">18</em>个小区符合要求，占北京市小区总量的<em class="inte-color-red">12.45%</em></p>
                </div>
                <div class="echart-box">

                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>针对这5个小区<br>为您做详细的分析和对比</p>
                </div>
                <section class="elastics-stack-box">
                    <div class="elastics-stack-content">
                        <ul id="elastics-stack" class="elastics-stack">
                            <li class="bgtype-1">
                                <div>
                                    <h4>2018纯新盘</h4>
                                    <p>北京全新楼盘抢先看</p>
                                </div>
                                <img src="${staticurl}/images/index/dsy_ts_image1.jpg" alt="2018纯新盘">
                            </li>
                            <li class="bgtype-2">
                                <div>
                                    <h4>海淀热门房源</h4>
                                    <p>看看大家关注哪里的房</p>
                                </div>
                                <img src="${staticurl}/images/index/dsy_ts_image2.jpg" alt="海淀热门房源">
                                </a></li>
                            <li class="bgtype-3">
                                <div>
                                    <h4>200万电梯房</h4>
                                    <p>少花钱多办事上下自由</p>
                                </div>
                                <img src="${staticurl}/images/index/dsy_ts_image3.jpg" alt="200万电梯房">
                            </li>
                        </ul>
                    </div>
                </section>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>交通</p>
                    <span>交通便利，赶得上节奏，跑得过大盘</span>
                </div>
                <div class="traffic-box">
                    <div class="left-line"></div>
                    <div>
                        <div class="traffic-title">
                            <h5>地铁出行，感谢您为环保事业做出的努力</h5>
                        </div>
                        <div class="echart-wrapper subway">
                            <div class="echart-box">
                                <div id="trafficSubwayChart"></div>
                            </div>
                        </div>
                        <div class="traffic-text-box">
                            <div class="traffic-text"><span>1</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                            <div class="traffic-text"><span>2</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                        </div>
                    </div>
                    <div class="vertical-line">
                        <div class="traffic-title">
                            <h5>自驾出行，快去最近的环线桥</h5>
                        </div>
                        <div class="echart-wrapper rond">
                            <div class="echart-box">
                                <div id="trafficRondChart"></div>
                            </div>
                        </div>
                        <div class="traffic-text-box">
                            <div class="traffic-text"><span>1</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                            <div class="traffic-text"><span>2</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>宜居</p>
                    <span>住的舒服点，从空气质量到景观，从人口密度到车位</span>
                </div>
                <table>
                    <tr>
                        <td>
                            <i></i>
                            <em>楼龄</em>
                        </td>
                        <td>1岁</td>
                        <td>2岁</td>
                        <td>3岁</td>
                        <td>4岁</td>
                        <td>5岁</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>户均绿化</em>
                        </td>
                        <td>35%</td>
                        <td>40%</td>
                        <td>42%</td>
                        <td>38%</td>
                        <td>45%</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>车位比</em>
                        </td>
                        <td>2：1</td>
                        <td>3：1</td>
                        <td>4：1</td>
                        <td>3：1</td>
                        <td>2：1</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>空气质量</em>
                        </td>
                        <td>2</td>
                        <td>3</td>
                        <td>6</td>
                        <td>3</td>
                        <td>2</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>电梯</em>
                        </td>
                        <td>
                            <em>一户</em>
                            <em>一梯</em>
                        </td>
                        <td>
                            <em>二户</em>
                            <em>一梯</em>
                        </td>
                        <td>
                            <em>一户</em>
                            <em>一梯</em>
                        </td>
                        <td>
                            <em>二户</em>
                            <em>一梯</em>
                        </td>
                        <td>
                            <em>二户</em>
                            <em>一梯</em>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>供暖</em>
                        </td>
                        <td>集中<br>供暖</td>
                        <td>集中<br>供暖</td>
                        <td>集中<br>供暖</td>
                        <td>集中<br>供暖</td>
                        <td>集中<br>供暖</td>
                    </tr>
                </table>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>生活成本</p>
                    <span>处处都要钱，那一个亿的小目标，看来得快点实现啊</span>
                </div>
                <table>
                    <tr>
                        <td>
                            <i></i>
                            <em>物业费<br>(/㎡·年)</em>
                        </td>
                        <td>18元</td>
                        <td>18元</td>
                        <td>18元</td>
                        <td>18元</td>
                        <td>18元</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>供暖费<br>(/㎡·年)</em>
                        </td>
                        <td>9元</td>
                        <td>9元</td>
                        <td>9元</td>
                        <td>9元</td>
                        <td>9元</td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>水电费<br>(/吨)<br>(/度)</em>
                        </td>
                        <td>
                            <span>5元</span>
                            <hr>
                            <span>0.48元</span>
                        </td>
                        <td>
                            <span>5元</span>
                            <hr>
                            <span>0.48元</span>
                        </td>
                        <td>
                            <span>5元</span>
                            <hr>
                            <span>0.48元</span>
                        </td>
                        <td>
                            <span>5元</span>
                            <hr>
                            <span>0.48元</span>
                        </td>
                        <td>
                            <span>5元</span>
                            <hr>
                            <span>0.48元</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>停车费<br>(/年)</em>
                        </td>
                        <td>178元</td>
                        <td>178元</td>
                        <td>178元</td>
                        <td>178元</td>
                        <td>178元</td>
                    </tr>
                </table>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>休闲购物</p>
                    <span>3km生活圈，吃喝玩乐买买买</span>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>教育配套</p>
                    <span>3km内教育配套，就这样陪你长大</span>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type2">
                    <p>医疗配套</p>
                    <span>3km内医疗配套，为您的健康保驾护航</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/URI.min.js"></script>
<script src="/static/js/draggabilly.pkgd.min.js"></script>
<script src="/static/js/elastiStack.js"></script>
<script src="/static/js/intelligent-chart.js"></script>
<script>
    new  ElastiStack(document.getElementById('elastics-stack'));

</script>
</body>
</html>