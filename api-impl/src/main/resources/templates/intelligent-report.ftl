<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script>
        (function(h,k){var p=h.document;var b=p.documentElement;var m=p.querySelector('meta[name="viewport"]');var e=p.querySelector('meta[name="flexible"]');var q=0;var d=0;var f;var j=k.flexible||(k.flexible={});if(m){console.warn("将根据已有的meta标签来设置缩放比例");var g=m.getAttribute("content").match(/initial\-scale=([\d\.]+)/);if(g){d=parseFloat(g[1]);q=parseInt(1/d)}}else{if(e){var i=e.getAttribute("content");if(i){var c=i.match(/initial\-dpr=([\d\.]+)/);var o=i.match(/maximum\-dpr=([\d\.]+)/);if(c){q=parseFloat(c[1]);d=parseFloat((1/q).toFixed(2))}if(o){q=parseFloat(o[1]);d=parseFloat((1/q).toFixed(2))}}}}if(!q&&!d){var n=h.devicePixelRatio;if(n>=3&&(!q||q>=3)){q=3}else{if(n>=2&&(!q||q>=2)){q=2}else{q=1}}d=1/q}b.setAttribute("data-dpr",q);if(!m){m=p.createElement("meta");m.setAttribute("name","viewport");m.setAttribute("content","initial-scale="+d+", maximum-scale="+d+", minimum-scale="+d+", user-scalable=no");if(b.firstElementChild){b.firstElementChild.appendChild(m)}else{var a=p.createElement("div");a.appendChild(m);p.write(a.innerHTML)}}function l(){var r=b.getBoundingClientRect().width;if(r/q>540){r=540*q}var s=r/10;b.style.fontSize=s+"px";j.rem=h.rem=s}h.addEventListener("resize",function(){clearTimeout(f);f=setTimeout(l,300)},false);h.addEventListener("pageshow",function(r){if(r.persisted){clearTimeout(f);f=setTimeout(l,300)}},false);if(p.readyState==="complete"){p.body.style.fontSize=12*q+"px"}else{p.addEventListener("DOMContentLoaded",function(r){p.body.style.fontSize=12*q+"px"},false)}l();j.dpr=h.dpr=q;j.refreshRem=l})(window,window["lib"]||(window["lib"]={}));
    </script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css">
    <link rel="stylesheet" href="${staticurl}/css/intelligent-report.css">
    <title>智能找房 预见所想</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/scrolloverflow.js"></script>
    <script src="${staticurl}/js/jquery.fullpage.min.new.js"></script>
    <script src="${staticurl}/js/modernizr.custom.js"></script>
    <script src="${staticurl}/js/echarts.min.js"></script>
</head>
<body>
<div id="superContainer">
    <div class="section page1 active">
        <div class="bgbox bg1">
            <div class="page-content">
                <div class="user-header-box">
                    <div class="user-line-triangle"></div>
                    <img src="/static/images/intelligent/user-header.png" alt="用户头像">
                </div>
                <div class="word-cont" data-user-type="1">
                    <p>繁华都市中，每个人都想有自己的空间。多年打拼后，您终于开始寻找第一个家园。我们明白，您挣的每分每厘都得来不易，凝聚无数的早起通勤和深夜加班。因此我们根据您的条件，为您精心挑选最具性价比的社区，可以让你拥有第一个舒适小家，争取做到：</p>
                    <ol>
                        <li>- 尽量离交通站近，睡多一点</li>
                        <li>- 餐饮便利，到家能吃口热饭</li>
                        <li>- 有休闲地儿，周末看场大片</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="2">
                    <p>我们深知您买房的每一分钱，都来自全家打拼，甚至还有亲友支援。为这第一个家，您大概找寻了很久，已有多次挑选。因此我们尽量给你更多信息，为您精心挑选最具生活价值的社区，让这第一个“幸福家”争取做到：</p>
                    <ol>
                        <li>- 下班回家，社区清静又安全</li>
                        <li>- 尽量离交通站近，睡多一点</li>
                        <li>- 好商圈，让家人享受生活时间</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="3">
                    <p>恭喜您经过多年打拼，事业终有所成！今天，您以无数汗滴换来的一丝安逸，将凝聚在这第一套本地房子——它区位好品质高、既时尚又便利。我们根据您的条件，为您精心挑选社区，争取做到：</p>
                    <ol>
                        <li>- 离市区近，尊享都市时尚繁华</li>
                        <li>- 或者单位近，下班轻松就到家</li>
                        <li>- 社区配套好，烦恼琐事全放下</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="4">
                    <p>您日夜操劳，只为家人安好。现在选房，是让上老下小，有更大的生活空间和更好的周边配套。为改善家庭居住条件，您大概找寻了很长时间，我们将根据您的需求，帮您仔细对比各种社区，争取做到：</p>
                    <ol>
                        <li>- 学校近，确保孩子未来成长</li>
                        <li>- 或者有医院，呵护老人健康</li>
                        <li>- 业态多样，轻松生活不抓狂</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="5">
                    <p>为了让更多家庭成员和远来亲友，居住得更宽敞、更舒适；同时也能让老人和孩子，能在社区里安心的行走和奔跑——您已左选右挑，辛劳多日。我们理解您的需求，因此帮您仔细地搜寻对比各种可能合适的社区，争取做到：</p>
                    <ol>
                        <li>- 抚幼养老，备好教育医疗</li>
                        <li>- 安享无忧，选好社区安保</li>
                        <li>- 休闲解压，找好生活配套</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="6">
                    <p>恭喜您事业有成！多年辛劳，终于有时间享受人生。平时您很忙，但也注重片刻的静思明月、小酌清风；同时，您大概也想着让整个乐享生活，自在从容。我们将帮助您搜寻和对比各种社区，争取做到：</p>
                    <ol>
                        <li>- 物业服务高端，开发商品牌知名</li>
                        <li>- 有绿地能让孩子们自由奔行</li>
                        <li>- 有绿荫能让老人们促膝谈心</li>
                        <li>- 有会所空间，与友人一道品茗</li>
                    </ol>
                </div>
                <div class="word-cont none" data-user-type="7">
                    <p>房产，在全球都是家庭资产配置重要一环，长远看，是让您今天不懈的奋斗带来明天稳定的收益。您肯定希望在周边小区价格、换手率和历史成交量价曲线等角度，看到清晰的数据对比，以做好投资决策。我们争取做到：</p>
                    <ol>
                        <li>- 帮您对比目标社区的周边价格</li>
                        <li>- 给您提供该社区的换手率信息</li>
                        <li>- 为您提供该社区的历史成交数据</li>
                    </ol>
                </div>
                <div class="down-triangle"></div>
            </div>
        </div>
    </div>
    <div class="section page2">
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
                    <p>您的意向区域中，有<em class="inte-color-red">18</em>个小区符合要求</p>
                </div>
                <div class="echart-box">

                </div>

                <div class="report-title-type3">
                    <p>根据您的需求，为您挑选5个最贴合的</p>
                </div>
                <div class="water-wrapper">
                    <div id="collieContainer">
                    <#--水滴  的容器-->
                    </div>
                    <div class="water-bg">
                        <div class="water-text-item">
                            <ul>
                                <li>总价：2000万</li>
                                <li><em>朝阳</em><em>海淀</em><em>丰台</em></li>
                                <li>三居</li>
                            </ul>
                            <div class="tip-text">
                                <span>交通便利</span>
                                <span>楼龄</span>
                                <span>绿化率</span>
                                <span>宜居性</span>
                                <span>电梯</span>
                                <span>性价比高</span>
                                <span>空气质量</span>
                                <span>配套设施</span>
                            </div>
                        </div>
                    </div>
                    <ul class="water-item">
                        <li><p>观湖国际</p></li>
                        <li><p>正邦家园</p></li>
                        <li><p>新苑花园小区</p></li>
                        <li><p>华莱国际</p></li>
                        <li><p>政豪园</p></li>
                    </ul>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>针对这5个小区<br>为您做详细的分析和对比</p>
                </div>
                <div class="plot-title-box">
                    <div class="plot-title-block">
                        <div>小区</div>
                        <ul>
                            <li>中粮万科长阳半岛</li>
                            <li>首创天禧</li>
                            <li>翡翠公园</li>
                            <li>天润富玺大厦</li>
                            <li>骏豪中央公园广场</li>
                        </ul>
                    </div>
                </div>
                <section class="elastics-stack-box">
                    <div class="elastics-stack-content">
                        <ul id="elastics-stack" class="elastics-stack report">
                            <li class="bgtype-1">
                                <a class="clear" href="#">
                                    <div>
                                        <h4>观湖国际</h4>
                                        <p>23400元/㎡</p>
                                        <p>89㎡-256㎡</p>
                                    </div>
                                    <img src="${staticurl}/images/index/dsy_ts_image1.jpg" alt="2018纯新盘">
                                </a>
                            </li>
                            <li class="bgtype-2">
                                <a class="clear" href="#">
                                    <div>
                                        <h4>海淀热门房源</h4>
                                        <p>看看大家关注哪里的房</p>
                                    </div>
                                    <img src="${staticurl}/images/index/dsy_ts_image2.jpg" alt="海淀热门房源">
                                </a>
                            </li>
                            <li class="bgtype-3">
                                <a class="clear" href="#">
                                    <div>
                                        <h4>200万电梯房</h4>
                                        <p>少花钱多办事上下自由</p>
                                    </div>
                                    <img src="${staticurl}/images/index/dsy_ts_image3.jpg" alt="200万电梯房">
                                </a>
                            </li>
                            <li class="bgtype-4">
                                <a class="clear" href="#">
                                    <div>
                                        <h4>200万电梯房</h4>
                                        <p>少花钱多办事上下自由</p>
                                    </div>
                                    <img src="${staticurl}/images/index/dsy_ts_image3.jpg" alt="200万电梯房">
                                </a>
                            </li>
                            <li class="bgtype-5">
                                <a class="clear" href="#">
                                    <div>
                                        <h4>200万电梯房</h4>
                                        <p>少花钱多办事上下自由</p>
                                    </div>
                                    <img src="${staticurl}/images/index/dsy_ts_image3.jpg" alt="200万电梯房">
                                </a>
                            </li>
                        </ul>
                    </div>
                </section>
            </div>
            <div class="module-item type2">
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
                            <div class="traffic-text"><span class="type1">1</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                            <div class="traffic-text"><span class="type2">2</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
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
                            <div class="traffic-text"><span class="type3">1</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                            <div class="traffic-text"><span class="type4">2</span><p>珠江帝景，距大望路站<em>0.6km</em>,约步行<em>3</em>分钟</p></div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="module-item type2">
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
            <div class="module-item type2">
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
            <div class="report-title-type3 mt0">
                <p>工作再忙，也要享受生活，看看3km内生活圈</p>
            </div>
            <div class="module-item type2">
                <div class="report-title-type2">
                    <p>休闲购物</p>
                    <span>3km生活圈，吃喝玩乐买买买</span>
                </div>
                <div class="echart-box">
                    <div id="shoppingChart"></div>
                </div>
            </div>
            <div class="module-item type2">
                <div class="report-title-type2">
                    <p>教育配套</p>
                    <span>3km内教育配套，就这样陪你长大</span>
                </div>
                <#--<i class="show-echart-detail"></i>-->
                <div class="echart-box">
                    <div id="educationChart"></div>
                </div>
            </div>
            <div class="module-item type2">
                <div class="report-title-type2">
                    <p>医疗配套</p>
                    <span>3km内医疗配套，为您的健康保驾护航</span>
                </div>
                <#--<i class="show-echart-detail"></i>-->
                <div class="echart-box">
                    <div id="medicalChart"></div>
                </div>
            </div>
            <div class="end-bottom">
                <p class="end-text">End</p>
                <div class="end-bottom-content">
                    <div class="collect-tips">
                        <p>您可以添加收藏，方便之后查阅！</p>
                    </div>
                    <div class="collect-button">
                        <i class="collect"></i>
                        <span>收藏</span>
                    </div>
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
    $(function () {
        $('#superContainer').fullpage({
            resize: false,
            scrollOverflow: true
        });
        $.fn.fullpage.setAllowScrolling(false, 'up');

        /*$('.show-echart-detail').on('click', function () {
            $(this).toggleClass('down');
            $(this).next('.echart-box').toggleClass('none');
        })*/

        $('.collect-button').on('click', function () {
            $(this).find('.collect').toggleClass('active');
        })
    });
</script>
<script src="${staticurl}/js/raphael.min.js"></script>
<script type="text/javascript">
    //水滴的效果
    function waterSharp(pool, laywidth, i) {
        this.pool = pool;
        this.x_index = i;
        this.laywidth = laywidth
    }
    waterSharp.prototype.animale = function () {
        var that = this;
        var init_x = this.laywidth / 6 * (this.x_index % 6);
        var index = Math.random() * 6;
        index = parseInt(index, 10);
        if (Math.random() * 100 < 70) {
            index = 0
        }
        this.el = this.pool[index].clone();
        this.el.toFront();
        if (index > 0) {
            this.el.attr({width: this.el.attr("width") * 2.2, height: this.el.attr("height") * 2.2});
        }
//        var randomNum = Math.random() * 5 + 10;
//        that.speed = parseInt(randomNum, 10)
        that.speed = 12;
        that.el.attr({x: init_x});
        var randomNum = Math.random() * 200;
        cy = 0 - parseInt(randomNum, 10);
        that.el.attr({"y": cy});
        var timer = setInterval(function () {
            var cy = that.el.attr("y");
//            that.speed+=1;
            if (cy > 500) {
                clearInterval(timer);
                that.el.remove();
                setTimeout(function () {
                    that.animale();
                })
            }
            that.el.attr({y: cy + that.speed});
        }, 100)
    };
    var guoduye = {
        up: 100,
        bottom: 100,

        init: function () {
            var width = $('#collieContainer').width();
            var height = $('#collieContainer').height(); // $(document).height() - this.up - this.bottom
            var r = Raphael("collieContainer", width, height);
            var pool = [];
            for (var i = 0; i < 6; i++) {
                pool.push(r.image("http://wap-qn.toutiaofangchan.com/znzf/water/water" + (i + 1) + ".png", 0, -100, 28, 35))
            }
            for (var i = 0; i < 18; i++) {
                setTimeout((function (index) {
                    return function () {
                        var el = new waterSharp(pool, width, index);
                        el.animale();
                    }
                })(i), 500 * i)

            }
        }
    };
    guoduye.init();

</script>
</body>
</html>