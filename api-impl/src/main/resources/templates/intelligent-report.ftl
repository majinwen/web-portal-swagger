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
    <title>智能找房 预见所想</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/scrolloverflow.js"></script>
    <script src="${staticurl}/js/jquery.fullpage.min.new.js"></script>
    <script src="${staticurl}/js/modernizr.custom.js"></script>
    <script src="${staticurl}/js/echarts.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
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
                    <p>总价<em class="inte-color-red" id="totlePrice"><#if intelligenceFhRes?exists>${intelligenceFhRes['totalPrice']}</#if></em>左右的房源市场为您的目标市场
                    </p>
                </div>
                <div class="echart-box">
                    <div id="priceChart"></div>
                </div>

                <ul class="results-contrast">
                    <li>
                    <#if fhpt['maxTarget']?exists&&fhpt['maxTarget']?number gt 0 &&fhpt['target']?exists&&fhpt['target']?number gt 0 >
                        <span class="contrast-mark type-red">涨</span>
                        <p>目标市场环比 最高涨幅为<em class="inte-color-red" id="maxTarget">
                        ${fhpt['maxTarget']?string('#.##')}
                            %</em>，<em id="priceMaxCompare"><#if fhpt['maxTarget'] gte fhpt['target']>高<#else>
                            低</#if></em>于北京市场均价涨幅</p>
                    </#if>
                    </li>
                    <li>
                    <#if fhpt['minTarget']?exists&&fhpt['minTarget']?number gt 0&&fhpt['target']?exists&&fhpt['target']?number gt 0 >
                        <span class="contrast-mark type-dark-green">跌</span>
                        <p>目标市场环比 最高跌幅为<em class="inte-color-red" id="minTarget">${fhpt['minTarget']?abs?string('#.##')}
                            %</em>，<em id="priceMinCompare"><#if fhpt['minTarget'] gte fhpt['target']>高<#else>
                            低</#if></em>于北京市场均价跌幅</p>
                        </#if>
                    </li>
                </ul>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>目标市场供需情况</p>
                </div>
                <div class="report-caption">
                    <p>根据您的检索条件</p>
                    <p>总价<em class="inte-color-red" id="totlePrice1"><#if intelligenceFhRes?exists>${intelligenceFhRes['totalPrice']}</#if></em>左右的房源市场为您的目标市场
                    </p>
                </div>
                <div class="echart-box">
                    <div id="marketChart"></div>
                </div>

                <ul class="results-contrast">
                <#if fhtp['ratio']?exists&&fhtp['ratio']!=''>
                    <li>
                        <#if fhtp['ratio']['maxVolume']?exists&&fhtp['ratio']['maxVolumeRatio']?exists>
                        <span class="contrast-mark type-red">高</span>
                        <p>目标市场 月度最高成交量为<em id="maxVolume" class="inte-color-red">${fhtp['ratio']['maxVolume']}</em>，为北京市场的<em id="maxVolumeRatio" class="inte-color-red">${fhtp['ratio']['maxVolumeRatio']}</em></p>
                        </#if>
                    </li>
                    <li>
                    <#if fhtp['ratio']['minVolume']?exists&&fhtp['ratio']['minVolumeRatio']?exists>
                        <span class="contrast-mark type-dark-green">低</span>
                        <p>目标市场 月度最低成交量为<em id="minVolume" class="inte-color-red">${fhtp['ratio']['minVolume']}</em>，为北京市场的<em id="minVolumeRatio" class="inte-color-red">${fhtp['ratio']['minVolumeRatio']}</em></p>
                    </#if>
                    </li>
                    <li>
                    <#if fhtp['ratio']['averageVolume']?exists&&fhtp['ratio']['averageVolumeRatio']?exists>
                        <span class="contrast-mark type-yellow">均</span>
                        <p>目标市场 年平均成交量为<em id="averageVolume" class="inte-color-red">${fhtp['ratio']['averageVolume']}</em>，为北京市场的<em id="averageVolumeRatio" class="inte-color-red">${fhtp['ratio']['averageVolumeRatio']}</em></p>
                    </#if>
                    </li>
                </#if>
                </ul>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>智能推荐结果</p>
                </div>
                <div class="report-caption">
                    <#if intelligenceFhRes['fhResult']?exists>
                    <p>您的意向区域中，有<em class="inte-color-red">${intelligenceFhRes['fhResult']?eval?size!''}</em>个小区符合要求</p>
                    </#if>
                </div>
                <div id="allmap" class="echart-box">

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
                                <li>总价：<#if intelligenceFhRes?exists>${intelligenceFhRes['totalPrice']}</#if>万</li>
                                <li><#if intelligenceFhRes['districtId']?exists&&intelligenceFhRes['districtId']!=''>
                                    <#assign Districts = intelligenceFhRes['districtId']?split(',')>
                                    <#list Districts as district>
                                        <em>${district}</em>
                                    </#list>
                                <#else >
                                    <em>-</em>
                                </#if></li>
                                <li>${intelligenceFhRes['layout']}居</li>
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
                    <#list intelligenceFhRes['fhResult']?eval as intelligenceFhRe>
                        <#if intelligenceFhRe['projname']?exists&&intelligenceFhRe['projname']!=''>
                            <li class="plot_class" data-type="${intelligenceFhRe.coordX?c}_${intelligenceFhRe.coordY?c}_${intelligenceFhRe_index}">
                                <p>${intelligenceFhRe['projname']}</p>
                            </li>
                        <#else >
                            <li> -</li>
                        </#if>
                    </#list>
                    </ul>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title-type1">
                    <p>针对这${intelligenceFhRes['fhResult']?eval?size!''}个小区<br>为您做详细的分析和对比</p>
                </div>
                <div class="plot-title-box">
                    <div class="plot-title-block">
                        <div>小区</div>
                        <ul>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['projname']?exists&&fhResult['projname']!=''>
                                    <li>${fhResult['projname']}</li>
                                <#else >
                                    <li> -</li>
                                </#if>
                            </#list>
                        </#if>
                        </ul>
                    </div>
                </div>
                <section class="elastics-stack-box">
                    <div class="elastics-stack-content">
                        <ul id="elastics-stack" class="elastics-stack report">
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <li class="bgtype-${fhResult_index+1}">
                                    <a class="clear" href="${router_city('/xiaoqu/'+fhResult['newcode']?c+'.html')}">
                                        <div>
                                            <h4>${fhResult['projname']}</h4>
                                            <#if fhResult['esfPrice']?exists&&fhResult['esfPrice']?number gt 0>
                                                <p>${fhResult['esfPrice']}元/㎡</p>
                                            <#else >
                                                <p>${fhResult['price']}元/㎡</p>
                                            </#if>
                                            <#if fhResult['newhRangeS']?exists&&fhResult['newhRangeS']?number gt 0>
                                                <p>${fhResult['newhRangeS']}㎡-${fhResult['newhRangeE']}㎡</p>
                                            <#else >
                                                <p>${fhResult['villageRangeS']}㎡-${fhResult['villageRangeE']}㎡</p>
                                            </#if>
                                        </div>
                                        <#if fhResult['plotImage'][0]?exists>
                                            <img src="${qiniuimage}/${fhResult['plotImage']?split(',')[0]}" alt="${(.now?string("yyyy年MM月dd日")?substring(0,4))}纯新盘">
                                        <#else >
                                            <img src="${staticurl}/images/global/tpzw_image.png" alt="暂无数据">
                                        </#if>
                                    </a>
                                </li>
                            </#list>
                        </#if>
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
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['projname']?exists&&fhResult['projname']!=''&&fhResult['nearestSubwayDesc']?exists&&fhResult['nearestSubwayDesc']!=''&&fhResult_index lt 3>
                                    <div class="traffic-text"><span class="type1">1</span><p>${fhResult['projname']}，距${fhResult['nearestSubwayDesc']?split('$')[1]}<em>${fhResult['nearestSubwayDesc']?split('$')[2]}m</em><#--,约步行<em>3</em>分钟--></p></div>
                                </#if>
                            </#list>
                        </#if>
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
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['projname']?exists&&fhResult['projname']!=''&&fhResult['nearestSubwayDesc']?exists&&fhResult['nearestSubwayDesc']!=''&&fhResult_index gt 2>
                                    <div class="traffic-text"><span class="type1">1</span><p>${fhResult['projname']}，距${fhResult['nearestSubwayDesc']?split('$')[1]}<em>${fhResult['nearestSubwayDesc']?split('$')[2]}m</em><#--,约步行<em>3</em>分钟--></p></div>
                                </#if>
                            </#list>
                        </#if>
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
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['finishdate']?exists&&fhResult['finishdate']!=''>
                                    <#assign date = (.now?string("yyyy年MM月dd日")?substring(0,4)?number - fhResult['finishdate']?date("yyyy")?string("yyyy年MM月dd日")?substring(0,4)?number)?string + '年'>
                                    <td>${date!'-'}</td>
                                </#if>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>绿化率</em>
                        </td>
                    <#if intelligenceFhRes?exists>
                        <#assign fhResults =intelligenceFhRes['fhResult']>
                        <#list fhResults?eval as fhResult>
                        <#if fhResult['virescencerate']?exists&&fhResult['virescencerate']?number gt 0>
                            <td>${fhResult['virescencerate']+'%'!'-'}</td>
                        </#if>
                        </#list>
                    </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>车位比</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <td>${fhResult['parkRadio']!'-'}</td>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>空气质量</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <td>${fhResult['airQuality']!'-'}</td>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>电梯</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <td>${fhResult['liftDoorRadio']!'-'}</td>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>供暖</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['heatingMode']?exists>
                                    <#if fhResult['heatingMode']?number == 0>
                                        <td>未知</td>
                                    <#elseif fhResult['heatingMode']?number == 1>
                                        <td>集中供暖</td>
                                    <#elseif fhResult['heatingMode']?number == 2>
                                        <td>自供暖</td>
                                    <#else >
                                        <td> -</td>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
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
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['propertyfee']?exists&&fhResult['propertyfee']?number gt 0>
                                    <td>${fhResult['propertyfee']}</td>
                                <#else >
                                    <td> -</td>
                                </#if>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>水电费<br>(/吨)<br>(/度)</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <td>
                                    <#if fhResult['waterSupply']?exists&&fhResult['waterSupply']!=''>
                                        <#if fhResult['waterSupply'] == '商水'>
                                            <span>6元</span>
                                        <#else >
                                            <span>5元</span>
                                        </#if>
                                    </#if>
                                    <hr>
                                    <#if fhResult['electricSupply']?exists&&fhResult['electricSupply']!=''>
                                        <#if fhResult['electricSupply'] == '商电'>
                                            <span>1.33元</span>
                                        <#else >
                                            <span>0.48元</span>
                                        </#if>
                                    </#if>
                                </td>
                            </#list>
                        </#if>
                    </tr>
                    <tr>
                        <td>
                            <i></i>
                            <em>停车费<br>(/年)</em>
                        </td>
                        <#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['carRentPrice']?exists&&fhResult['carRentPrice']?number gt 0>
                                    <td>${fhResult['carRentPrice']}元</td>
                                <#else >
                                    <td> -</td>
                                </#if>
                            </#list>
                        </#if>
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
                <div class="echart-box nearby">
                    <div id="shoppingChart"></div>
                </div>
            </div>
            <div class="module-item type2">
                <div class="report-title-type2">
                    <p>教育配套</p>
                    <span>3km内教育配套，就这样陪你长大</span>
                </div>
            <#--<i class="show-echart-detail"></i>-->
                <div class="echart-box nearby">
                    <div id="educationChart"></div>
                </div>
            </div>
            <div class="module-item type2">
                <div class="report-title-type2">
                    <p>医疗配套</p>
                    <span>3km内医疗配套，为您的健康保驾护航</span>
                </div>
            <#--<i class="show-echart-detail"></i>-->
                <div class="echart-box nearby">
                    <div id="medicalChart"></div>
                </div>
            </div>
            <div class="end-bottom">
                <p class="end-text">End</p>
                <div class="end-bottom-content">
                    <div class="collect-tips">
                        <p>您可以添加收藏，方便之后查阅！</p>
                    </div>
                    <div class="collect-button" >
                        <i class="collect" data-type="0"></i>
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
<#--<script src="/static/js/intelligent-chart.js"></script>-->
<script>
    new ElastiStack(document.getElementById('elastics-stack'));
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
            var reportId=${reportId};
            $(this).find('.collect').toggleClass('active');
            var count= $(this).find('.collect').attr('data-type');
            console.log(count);
            if(reportId!=""&&reportId!=null){
                $.ajax({
                    type: "GET",
                    async: true,
                    url: router_city('/findhouse/collectMyReport')+"?reportId="+reportId,
                    data: reportId,
                    success: function(data){
                        //改变状态
                        if(data.data=="ok"){
                            console.log(data.data)
                            //缺少收藏样式
                        }
                        if(data.data=="fail"){
                            console.log(data);
                            //重定向到登陆页面
                            window.location.href = "/user/login?reportId="+reportId;
                        }
                    }
                })
            }
        })
    });
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

    var datajson =${datajson};
    var ptlists = ${ptlists};
    var trend = ${trend}

    //    console.log(ptlists)
    //    console.log(trend)
        console.log(datajson)
    var dpr = window.devicePixelRatio;
    var baseFontSize = 12 * dpr;
    var baseItemWidth = 25 * dpr;

    function getJiagezoushiYuefen() {
        var res = [];
        for (var i = ptlists.length - 1; i >= 0; i--) {
            res.push(ptlists[i]["periodicTime"])
        }
        return res;
    }

    function getJiagezoushiBeijing() {
        var res = [];
        for (var i = ptlists.length - 1; i >= 0; i--) {
            res.push([ptlists[i]["periodicTime"], ptlists[i]["totalPrice"]])
        }
        return res;
    }

    function getJiagezoushiMuBiao() {
        var res = [];
        for (var i = ptlists.length - 1; i >= 0; i--) {
            res.push([ptlists[i]["periodicTime"], ptlists[i]["price"]])
        }
        return res;
    }

    function getGongxuqingkuangYuefen() {
        var res = [];
        for (var i = 0; i < trend.length; i++) {
            res.push(trend[i]["periodicTime"])
        }
        return res;
    }

    function getGongxuqingkuangBeijing() {
        var res = [];
        for (var i = 0; i < trend.length; i++) {
            res.push(trend[i]["allSd"])
        }
        return res;
    }

    function getGongxuqingkuangMuBiao() {
        var res = [];
        for (var i = 0; i < trend.length; i++) {
            res.push(trend[i]["targetSd"])
        }
        return res;
    }

    function getPlotName() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push(datajson[i]["projname"])
        }
        return res;
    }

    function dict_getValueOrDefault(obj,key,default_value) {
        obj = obj || {}
        var v = obj[key]
        if(typeof(v) == "undefined"){
            return default_value
        }
        return v
    }

    function getXiuxiangouwu() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            var typecount = dict_getValueOrDefault(datajson[i],"typeCount",{})
            var xiuxian=dict_getValueOrDefault(typecount,"xiuxian",{})

            res.push([dict_getValueOrDefault(xiuxian,"caishichang",0),dict_getValueOrDefault(xiuxian,"chaoshi",0),dict_getValueOrDefault(xiuxian,"shangchang",0),dict_getValueOrDefault(xiuxian,"canting",0),dict_getValueOrDefault(xiuxian,"jianshenzhongxin",0)])
        }
        return res;
    }

    function getJiaoyupeitao() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            var typecount = dict_getValueOrDefault(datajson[i],"typeCount",{})
            var xiuxian=dict_getValueOrDefault(typecount,"jiaoyu",{})
            res.push([dict_getValueOrDefault(xiuxian,"qinzi",0),dict_getValueOrDefault(xiuxian,"youeryuan",0),dict_getValueOrDefault(xiuxian,"xiaoxue",0),dict_getValueOrDefault(xiuxian,"zhongxue",0),dict_getValueOrDefault(xiuxian,"gaodeng",0)])

        }
        return res;
    }

    function getYiliaopeitao() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            var typecount = dict_getValueOrDefault(datajson[i],"typeCount",{})
            var xiuxian=dict_getValueOrDefault(typecount,"yiliao",0)
            res.push([xiuxian])

        }
        return res;
    }

    function getSubway() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push([parseInt(datajson[i]["nearestSubwayDesc"].split("$")[2])/1000])
        }
        return res;
    }

    function getMetroStation() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push((datajson[i]["nearestSubwayDesc"]||"".split("$")[1]))
        }
        return res;
    }


    function getNearbyQiao() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push(datajson[i]["nearbyQiao"]||"")
        }
        return res;
    }


    function getNearbyRoadMeter() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push([parseInt(datajson[i]["nearbyRoadMeter"]||"")/1000])
        }
        return res;
    }

    /**
     * 报告页图表集合
     * */
    $(function () {
        var locationUrl = window.location.href;
        locationBaseUrl = parseInt(locationUrl.substr(locationUrl.lastIndexOf('/') + 1));

        var chartGrid = {
            left: 0,
            right: '6%',
            bottom: 0,
            containLabel: true
        };

        /**
         * 市场价格走势
         * */
        var priceChart = echarts.init(document.getElementById('priceChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标轴
        priceChart.setOption({
            color: ['#455765', '#f25a5a', '#fece6c', '#7f7f7f', '#4a7aa3'],
            textStyle: {fontSize: baseFontSize},
            tooltip: {
                trigger: 'axis',
                textStyle: {fontSize: baseFontSize}
            },
            legend: {
                itemGap: 20,
                itemWidth: baseItemWidth,
                data: [{
                    name: '北京市场',
                    icon: 'line'
                }, {
                    name: '目标市场',
                    icon: 'line'
                }]
            },
            grid: chartGrid,
            xAxis: {
                show: true,
                boundaryGap: false,
                scale: true,
                axisLabel: {fontSize: baseFontSize - 10},
                data: getJiagezoushiYuefen()
            },
            yAxis: {
                scale: true,
                axisLine: {show: true},
                axisTick: {show: false},
                axisLabel: {show: false},
                splitLine: {show: false}
            },
            dataZoom: [
                {
                    type: 'inside',
                    start: 50,
                    end: 100,
                    filterMode: 'empty',
                    zoomLock: true
                }
            ],
            series: [{
                name: '北京市场',
                type: 'line',
                showSymbol: false,
                data: getJiagezoushiBeijing()
            }, {
                name: '目标市场',
                type: 'line',
                showSymbol: false,
                data: getJiagezoushiMuBiao()
            }]
        });

        /**
         * 市场供需情况
         * */
        var marketChart = echarts.init(document.getElementById('marketChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标
        marketChart.setOption({
            textStyle: {fontSize: baseFontSize},
            tooltip: {
                show: true,
                trigger: 'axis',
                textStyle: {fontSize: baseFontSize},
                axisPointer: {type: 'shadow'}
            },
            legend: {
                itemGap: 20,
                itemWidth: baseItemWidth,
                data: [{
                    name: '北京市场',
                    icon: 'line'
                }, {
                    name: '目标市场',
                    icon: 'line'
                }]
            },
            grid: chartGrid,
            yAxis: {
                type: 'value',
                axisTick: {show: false},
                axisLine: {show: false},
                splitLine: {show: false},
                axisLabel: {show: false}
            },
            xAxis: [
                {
                    type: 'category',
                    axisTick: {show: false},
                    axisLine: {show: true},
                    axisLabel: {fontSize: baseFontSize - 10},
                    data: getGongxuqingkuangYuefen()
                }, {
                    type: 'category',
                    axisLine: {show: false},
                    axisTick: {show: false},
                    axisLabel: {show: false},
                    splitArea: {show: false},
                    splitLine: {show: false},
                    data: getGongxuqingkuangYuefen()
                }
            ],
            series: [
                {
                    name: '北京市场',
                    type: 'bar',
                    xAxisIndex: 1,
                    itemStyle: {
                        normal: {
                            show: true,
                            color: '#277ace',
                            barBorderRadius: 10,
                            borderWidth: 0,
                            borderColor: '#333'
                        }
                    },
                    barGap: 0,
                    barCategoryGap: '70%',
                    data: getGongxuqingkuangBeijing()
                },
                {
                    name: '目标市场',
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            show: true,
                            color: '#5de3e1',
                            barBorderRadius: 10,
                            borderWidth: 0,
                            borderColor: '#333'
                        }
                    },
                    barGap: 0,
                    barCategoryGap: '70%',
                    data: getGongxuqingkuangMuBiao()
                }
            ]
        });
//        marketChart.showLoading();

        /**
         * 地铁信息
         * */
        var trafficSubwayChart = echarts.init(document.getElementById('trafficSubwayChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标

        var trafficSubwayLabel = {
            normal: {
                show: true,
                position: 'bottom',
                color: '#666',
                fontSize: baseFontSize - 5,
                formatter: '{c}km\n'/*+getMetroStation()*/
            }
        };
        trafficSubwayChart.setOption({
            color: ['#455765', '#f25a5a', '#fece6c', '#7f7f7f', '#4a7aa3'],
            textStyle: {fontSize: baseFontSize},
            grid: {
                left: 0,
                right: '6%',
                bottom: 0,
                top: 0,
                containLabel: true
            },
            xAxis: {
                show: false,
                data: []
            },
            yAxis: {
                show: false,
                inverse: true,
                min: 0,
                max: 3
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#455765' }
                    },
                    data: getSubway()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#f25a5a' }
                    },
                    data: getSubway()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#fece6c' }
                    },
                    data: getSubway()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#7f7f7f' }
                    },
                    data: getSubway()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#4a7aa3' }
                    },
                    data: getSubway()[4]
                }
            ]
        });
        // trafficSubwayChart.showLoading();


        /**
         * 环线桥信息
         * */
        var trafficRondChart = echarts.init(document.getElementById('trafficRondChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标

        var trafficRondLabel = {
            normal: {
                show: true,
                position: 'bottom',
                color: '#666',
                fontSize: baseFontSize - 5
//                formatter: '{c}km: \'' + subwayStation + '\''
            }
        };
        trafficRondChart.setOption({
            color: ['#455765', '#f25a5a', '#fece6c', '#7f7f7f', '#4a7aa3'],
            textStyle: {fontSize: baseFontSize},
            grid: {
                left: 0,
                right: '6%',
                bottom: 0,
                top: 0,
                containLabel: true
            },
            xAxis: {
                show: false,
                data: []
            },
            yAxis: {
                show: false,
                inverse: true,
                min: 0,
                max: 3
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#455765' }
                    },
                    data: getNearbyRoadMeter()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#f25a5a' }
                    },
                    data: getNearbyRoadMeter()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#fece6c' }
                    },
                    data: getNearbyRoadMeter()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#7f7f7f' }
                    },
                    data: getNearbyRoadMeter()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 1.5,
                    barWidth: '8%',
                    itemStyle: {
                        normal: { color: '#4a7aa3' }
                    },
                    data: getNearbyRoadMeter()[4]
                }
            ]
        });
        // trafficRondChart.showLoading();

        var nearbyChartGrid = {
            top: 0,
            left: '3%',
            right: '4%',
            bottom: 0,
            containLabel: true
        };
        /**
         * 休闲购物
         * */
        var shoppingChart = echarts.init(document.getElementById('shoppingChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标
        shoppingChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: nearbyChartGrid,
            xAxis: {
                type: 'value',
                axisLabel: {fontSize: baseFontSize - 10}
            },
            yAxis: {
                type: 'category',
                axisLabel: {fontSize: baseFontSize - 10},
                data: ['菜市场', '超市', '购物中心', '餐饮', '健身']
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#455765' }
                    },
                    data: getXiuxiangouwu()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#f25a5a' }
                    },
                    data: getXiuxiangouwu()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#fece6c' }
                    },
                    data: getXiuxiangouwu()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#7f7f7f' }
                    },
                    data: getXiuxiangouwu()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#4a7aa3' }
                    },
                    data: getXiuxiangouwu()[4]
                }
            ]
        });


        /**
         * 教育配套
         * */
        var educationChart = echarts.init(document.getElementById('educationChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标
        educationChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: nearbyChartGrid,
            xAxis: {
                type: 'value',
                axisLabel: {fontSize: baseFontSize - 10}
            },
            yAxis: {
                type: 'category',
                axisLabel: {fontSize: baseFontSize - 10},
                data: ['亲子教育', '幼儿园', '小学', '中学', '大学']
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#455765' }
                    },
                    data: getJiaoyupeitao()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#f25a5a' }
                    },
                    data: getJiaoyupeitao(1)
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#fece6c' }
                    },
                    data: getJiaoyupeitao()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#7f7f7f' }
                    },
                    data: getJiaoyupeitao()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#4a7aa3' }
                    },
                    data: getJiaoyupeitao()[4]
                }
            ]
        });


        /**
         * 医疗配套
         * */
        var medicalChart = echarts.init(document.getElementById('medicalChart'), null, {renderer: 'svg'}, {
            devicePixelRatio: dpr,
            width: '100%',
            height: '100%'
        });
        // 显示标题，图例和空的坐标
        medicalChart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: nearbyChartGrid,
            xAxis: {
                type: 'value',
                axisLabel: {fontSize: baseFontSize - 10}
            },
            yAxis: {
                type: 'category',
                axisLabel: {fontSize: baseFontSize - 10},
                data: ['医疗']
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#455765' }
                    },
                    data: getYiliaopeitao()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#f25a5a' }
                    },
                    data: getYiliaopeitao()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#fece6c' }
                    },
                    data: getYiliaopeitao()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#7f7f7f' }
                    },
                    data: getYiliaopeitao()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: { color: '#4a7aa3' }
                    },
                    data: getYiliaopeitao()[4]
                }
            ]
        });

    });
</script>
<script>
    var datajson =${datajson};
    console.log(datajson.length);
    var res = [];
    for (var i = 0; i < datajson.length; i++) {
        res.push(datajson[i]['coordX']+"&"+datajson[i]['coordY'])
    }

    // 百度地图API功能
    var map = new BMap.Map("allmap", {
        minZoom : 1,
        maxZoom : 18
    });
    var point = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point, 12);
    map.enableScrollWheelZoom(true);

    $('.water-item').on('click', 'li', function () {
        var attr = $(this).attr('data-type');
        var point = new BMap.Point(attr.split("_")[0], attr.split("_")[1]);

        map.centerAndZoom(new BMap.Point(attr.split("_")[0],attr.split("_")[1]),13);

        addMarker(point,attr.split("-")[2]);

    });


    var ctrlNav = new window.BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
    map.addControl(ctrlNav);
    var ctrlOve = new window.BMap.OverviewMapControl({
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
        isOpen: 1
    });
    map.addControl(ctrlOve);
    var ctrlSca = new window.BMap.ScaleControl({
        anchor: BMAP_ANCHOR_BOTTOM_LEFT
    });
    map.addControl(ctrlSca);
    //页面刚进来显示5个小区在地图上的地理坐标信息
    if (res.length > 0) {
        for (var i = 0; i < res.length; i++) {
            var point = new BMap.Point(res[i].split("&")[0], res[i].split("&")[1]);
            addMarker(point, i);
        }
    }
    // 添加标注
    function addMarker(point, index) {
        var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png",
                new BMap.Size(23, 25), {
                    offset: new BMap.Size(10, 25),
                    imageOffset: new BMap.Size(0, 0 - index * 25)

                });
        var marker = new BMap.Marker(point, {icon: myIcon});
        marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
        map.addOverlay(marker);
        return marker;
    }

    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function (e) {
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError", function (e) {
        // 定位失败事件
        alert(e.message);
    });
    map.addControl(geolocationControl);




</script>
</body>
</html>