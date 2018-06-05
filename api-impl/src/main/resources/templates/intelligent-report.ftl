<!DOCTYPE html>
<html class="no-js">
<head>
    <#include "staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/intelligent-report.css?v=${staticversion}">
    <title>懂房帝</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script src="${staticurl}/js/modernizr.custom.js?v=${staticversion}"></script>
    <script src="${staticurl}/js/echarts.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "StatisticsHeader.ftl">
</head>
<body>
<img src="${staticurl}/images/intelligent/adm_323031383036343130343932.png" class="shareTopImg" height="0" width="0" data-src="${staticurl}/images/intelligent/adm_323031383036343130343932.png" alt="">
<div id="superContainer">
    <div class="section page1 active none">
        <div class="bgbox bg1">
        <div class="page-content">
            <div class="user-header-title">
                <p>将您的检索条件和305586位购房用户<br>的数据样本比较<br>生成您所处族群的用户画像 </p>
            </div>
            <div class="user-header-box">
                <img src="/static/images/intelligent/user-header.png" alt="用户头像">
            </div>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 1>
            <div class="word-cont" data-user-type="1">
                <p>繁华都市中，每个人都想拥有属于自己的独立空间。多年打拼后，您终于开始寻找第一个家。我们明白，您挣的每分每厘都得来不易。因此我们根据您的条件，为您精心挑选，争取做到：</p>
                <ol>
                    <li>尽量离交通站近，睡多一点</li>
                    <li>餐饮便利，到家能吃口热饭</li>
                    <li>有休闲地儿，周末看场大片</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 2>
            <div class="word-cont" data-user-type="2">
                <p>我们深知您买房的每一分钱，都来自全家打拼，甚至还有亲友支援。为这第一个家，您大概找寻了很久，已有多次挑选。因此我们尽量给您更多信息，争取做到：</p>
                <ol>
                    <li>下班回家，社区清静又安全</li>
                    <li>尽量离交通站近，睡多一点</li>
                    <li>好商圈，让家人享受生活时间</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 3>
            <div class="word-cont" data-user-type="3">
                <p>恭喜您经过多年打拼，事业终有所成！今天，您以无数汗水换来的一丝安逸，将凝聚在这第一套房子——它区位好、品质高、既时尚又便利。我们根据您的条件，为您精心挑选，争取做到：</p>
                <ol>
                    <li>离市区近，尊享都市时尚繁华</li>
                    <li>或者单位近，下班轻松就到家</li>
                    <li>社区配套好，烦恼琐事全放下</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 4>
            <div class="word-cont" data-user-type="4">
                <p>您日夜操劳，只为家人安好。现在选房，是让老人小孩有更大的生活空间和更好的周边配套。为改善家庭居住条件，您大概找寻了很长时间，我们根据您的条件，为您精心挑选，争取做到：</p>
                <ol>
                    <li>学校近，确保孩子未来成长</li>
                    <li>或者有医院，呵护老人健康</li>
                    <li>业态多样，轻松生活不抓狂</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 5>
            <div class="word-cont" data-user-type="5">
                <p>为了让更多家庭成员和远来亲友，居住得更宽敞、更舒适，同时也能让老人和小孩能在社区里安心地行走和奔跑，您已左选右挑，辛劳多日。我们根据您的条件，为您精心挑选，争取做到：</p>
                <ol>
                    <li>抚幼养老，备好教育医疗</li>
                    <li>安享无忧，选好社区安保</li>
                    <li>休闲解压，找好生活配套</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 6>
            <div class="word-cont" data-user-type="6">
                <p>恭喜您事业有成！多年辛劳，终于有时间享受人生。平时虽忙，但也注重片刻的静思明月、小酌清风。我们将帮助您搜寻和对比各种社区，争取做到：</p>
                <ol>
                    <li>物业服务高端，开发商品牌知名</li>
                    <li>有绿地能让孩子们自由奔行</li>
                    <li>有绿荫能让老人们促膝谈心</li>
                    <li>有会所空间，与友人一道品茗</li>
                </ol>
            </div>
        </#if>
        <#if intelligenceFhRes['userPortrait']?exists&&intelligenceFhRes['userPortrait']?number == 7>
            <div class="word-cont" data-user-type="7">
                <p>房产，在全球都是家庭资产配置重要一环，长远看，是让您今天不懈的奋斗带来明天稳定的收益。您肯定希望在周边小区价格、换手率和历史成交量价曲线等角度，看到清晰的数据对比，以做好投资决策。我们争取做到：</p>
                <ol>
                    <li>帮您对比目标社区的周边价格</li>
                    <li>给您提供该社区的换手率信息</li>
                    <li>为您提供该社区的历史成交数据</li>
                </ol>
            </div>
        </#if>
            <div class="down-triangle"></div>
        </div>
        </div>
    </div>
    <div class="section page2">
        <div class="page-content">
            <div class="header-summary-box">
                <div class="header-summary">
                    <p>根据您的检索条件<br>总价<#if intelligenceFhRes?exists><#if intelligenceFhRes['totalPrice']?number == 1500>${intelligenceFhRes['totalPrice']?number?round}万以上<#else>${intelligenceFhRes['totalPrice']?number?round}万左右</#if></#if>的房源市场<br>为您的目标市场。</p>
                    <p>以下从市场行情，地理位置，宜居指数，交通及周边配套设施等方面，为您挑选生活家</p>
                </div>
            </div>
            <#if fhpt?exists><div class="module-item">
                <div class="report-title">
                    <i class="icon-title-price"></i>
                    <p>目标市场均价走势</p>
                </div>
                <div class="piece-module">
                    <div class="report-caption echart-title">
                        <p>总价<strong class="high-light-red"><#if intelligenceFhRes?exists><#if intelligenceFhRes['totalPrice']?number == 1500>${intelligenceFhRes['totalPrice']?number?round}万</strong>以上<#else>${intelligenceFhRes['totalPrice']?number?round}万</strong>左右</#if></#if>的房源市场为您的<em class="high-light-red">目标市场</em>，<br>对比北京市场近一年整体价格情况，<br>洞察目标房产的价格走势</p>
                    </div>
                    <ul class="echarts-legend">
                        <li>北京市场</li>
                        <li>目标市场(总价<#if intelligenceFhRes?exists><#if intelligenceFhRes['totalPrice']?number == 1500>${intelligenceFhRes['totalPrice']?number?round}万<#else>${intelligenceFhRes['totalPrice']?number?round}万</#if></#if>)</li>
                    </ul>
                    <div class="echart-box">
                        <div id="priceChart"></div>
                    </div>
                    <ul class="results-contrast">
                        <#if fhpt['maxTarget']?exists&&fhpt['target']?exists ><li>
                            <span class="contrast-mark type-red">涨</span>
                            <p>目标市场 环比最高涨幅为<em class="inte-color-red" id="maxTarget">${fhpt['maxTarget']?string('#.##')}%</em>，<em id="priceMaxCompare"><#if fhpt['maxTarget'] gte fhpt['target']>高<#else>低</#if></em>于北京市场均价涨幅</p>
                        </li></#if>
                        <#if fhpt['minTarget']?exists&&fhpt['target']?exists ><li>
                            <span class="contrast-mark type-dark-green">跌 </span>
                            <p>目标市场 环比最高跌幅为<em class="inte-color-red" id="minTarget">${fhpt['minTarget']?abs?string('#.##')}%</em>，<em id="priceMinCompare"><#if fhpt['minTarget'] gte fhpt['target']>高<#else>低</#if></em>于北京市场均价跌幅</p>
                        </li></#if>
                    </ul>
                </div>
            </div></#if>
            <#if fhtp?exists><div class="module-item">
                <div class="report-title">
                    <i class="icon-title-marker"></i>
                    <p>目标市场成交行情</p>
                </div>
                <div class="piece-module">
                    <div class="report-caption echart-title">
                        <p>总价<strong class="high-light-red"><#if intelligenceFhRes?exists><#if intelligenceFhRes['totalPrice']?number == 1500>${intelligenceFhRes['totalPrice']?number?round}万</strong>以上<#else>${intelligenceFhRes['totalPrice']?number?round}万</strong>左右</#if></#if>的房源市场为您的<em class="high-light-red">目标市场</em>，<br>对比北京市场近一年整体成交情况，<br>把握目标房产的成交量变化</p>
                    </div>
                    <ul class="echarts-legend">
                        <li>北京市场</li>
                        <li>目标市场(总价<#if intelligenceFhRes?exists><#if intelligenceFhRes['totalPrice']?number == 1500>${intelligenceFhRes['totalPrice']?number?round}万<#else>${intelligenceFhRes['totalPrice']?number?round}万</#if></#if>)</li>
                    </ul>
                    <div class="echart-box">
                        <div id="marketChart"></div>
                    </div>
                    <#if fhtp['ratio']?exists&&fhtp['ratio']!=''><ul class="results-contrast">
                        <#if fhtp['ratio']['maxVolume']?exists&&fhtp['ratio']['maxVolumeRatio']?exists><li>
                            <span class="contrast-mark type-red">高</span>
                            <p>目标市场 月度最高成交量为<em id="maxVolume" class="inte-color-red">${fhtp['ratio']['maxVolume']}</em>套，为北京市场的<em id="maxVolumeRatio" class="inte-color-red">${fhtp['ratio']['maxVolumeRatio']}</em></p>
                        </li></#if>
                        <#if fhtp['ratio']['minVolume']?exists&&fhtp['ratio']['minVolumeRatio']?exists><li>
                            <span class="contrast-mark type-dark-green">低</span>
                            <p>目标市场 月度最低成交量为<em id="minVolume" class="inte-color-red">${fhtp['ratio']['minVolume']}</em>套，为北京市场的<em id="minVolumeRatio" class="inte-color-red">${fhtp['ratio']['minVolumeRatio']}</em></p>
                        </li></#if>
                        <#if fhtp['ratio']['averageVolume']?exists&&fhtp['ratio']['averageVolumeRatio']?exists><li>
                            <span class="contrast-mark type-yellow">均</span>
                            <p>目标市场 年平均成交量为<em id="averageVolume" class="inte-color-red">${fhtp['ratio']['averageVolume']}</em>套，为北京市场的<em id="averageVolumeRatio" class="inte-color-red">${fhtp['ratio']['averageVolumeRatio']}</em></p>
                        </li></#if>
                    </ul></#if>
                </div>
            </div></#if>
            <div class="module-item">
                <div class="report-title">
                    <i class="icon-title-result"></i>
                    <p>智能推荐结果</p>
                </div>
                <div class="piece-module">
                    <div class="report-caption">
                        <p>根据您的检索条件，在5394个小区中，<br>为您推荐5个小区，作为您所属族群的理想家</p>
                    </div>
                    <div class="water-wrapper">
                        <div id="collieContainer"><#--水滴  的容器--></div>
                        <div class="water-bg">
                            <div class="water-text-item">
                                <ul>
                                <#--这里的空格保留-->
                                    <li>总价：<#if intelligenceFhRes?exists>
                                        <#if intelligenceFhRes['totalPrice']?number == 1500>
                                        ${intelligenceFhRes['totalPrice']?number?round}万+
                                        <#else>
                                        ${intelligenceFhRes['totalPrice']?number?round}万
                                        </#if>
                                    </#if>
                                    </li>
                                    <li><#if intelligenceFhRes['districtId']?exists&&intelligenceFhRes['districtId']!=''><#assign Districts = intelligenceFhRes['districtId']?split(',')><#list Districts as district><em>${district} </em></#list><#else ><em>-</em></#if></li>
                                    <li><#if intelligenceFhRes['layout']?exists&&intelligenceFhRes['layout']?number gt 0>${intelligenceFhRes['layout']}居<#else >-</#if> </li>
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
                                    <p><span>0${intelligenceFhRe_index+1}</span>${intelligenceFhRe['projname']}</p>
                                </li><#else ><li>-</li>
                            </#if>
                        </#list>
                        </ul>
                    </div>
                    <section class="elastics-stack-box">
                        <div class="elastics-stack-content">
                            <ul id="elastics-stack" class="elastics-stack report">
                            <#if intelligenceFhRes?exists>
                                <#assign fhResults =intelligenceFhRes['fhResult']>
                                <#list fhResults?eval as fhResult>
                                    <li class="bgtype-${fhResult_index+1}" data-href="${router_city('/xiaoqu/'+fhResult['newcode']?c+'.html')}">
                                        <div>
                                            <h4>${fhResult['projname']}</h4>
                                            <#if fhResult['esfPrice']?exists&&fhResult['esfPrice']?number gt 0>
                                                <p>${fhResult['esfPrice']?number?round}元/㎡</p>
                                            <#else >
                                                <p>${fhResult['price']?number?round}元/㎡</p>
                                            </#if>
                                            <#if fhResult['districtName']?exists&&fhResult['areaName']?exists>
                                                <p>${fhResult['districtName']}-${fhResult['areaName']}</p>
                                            </#if>
                                        <#--<#if fhResult['newhRangeS']?exists&&fhResult['newhRangeS']?number gt 0>
                                            <p>${fhResult['newhRangeS']}㎡-${fhResult['newhRangeE']}㎡</p>
                                        <#else >
                                            <p>${fhResult['villageRangeS']}㎡-${fhResult['villageRangeE']}㎡</p>
                                        </#if>-->
                                        </div>
                                        <#if fhResult['plotImage']?exists && fhResult['plotImage'] != ''>
                                            <img src="${qiniuimage}/${fhResult['plotImage']?split(',')[0]}-tt400x300" alt="${(.now?string("yyyy年MM月dd日")?substring(0,4))}纯新盘">
                                        <#else >
                                            <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                                        </#if>
                                    </li>
                                </#list>
                            </#if>
                            </ul>
                        </div>
                    </section>
                </div>
            </div>
            <div class="module-item">
                <div class="plot-title-box">
                    <div class="plot-title-block none">
                        <div><i></i>小区</div>
                        <ul class="clear"><#if intelligenceFhRes?exists>
                            <#assign fhResults =intelligenceFhRes['fhResult']>
                            <#list fhResults?eval as fhResult>
                                <#if fhResult['projname']?exists&&fhResult['projname']!=''>
                                    <li>${fhResult['projname']}</li>
                                <#else >
                                    <li>-</li>
                                </#if>
                            </#list>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title">
                    <i class="icon-title-contrast"></i>
                    <p>针对这${intelligenceFhRes['fhResult']?eval?size!''}个小区为<br>您做详细的分析和对比</p>
                </div>
                <div class="piece-module">
                    <section>
                        <div class="piece-title mt0">
                            <div class="piece-title-border">
                                <p>交通</p>
                                <span>交通便利，赶得上节奏，跑得过大盘</span>
                            </div>
                        </div>
                        <section>
                            <div class="traffic-title-box">
                                <div class="traffic-title ditie">
                                    <p>地铁<br>出行</p>
                                    <div>感谢您为环保事业做出的努力</div>
                                </div>
                            </div>
                            <div class="echart-box mb0">
                                <div id="trafficSubwayChart"></div>
                            </div>
                            <#if intelligenceFhRes?exists><div class="traffic-text-box">
                                <#assign fhResults = intelligenceFhRes['fhResult']?eval>
                                <#list fhResults?sort_by('metroWithPlotDistance') as fhResult>
                                    <#if fhResult['projname']?exists&&fhResult['projname']!=''&&fhResult['nearestSubwayDesc']?exists&&fhResult['nearestSubwayDesc']!=''&&fhResult_index lt 2>
                                        <#if fhResult_index == 0>
                                            <div class="traffic-text">
                                                <div class="traffice-table-row">
                                                    <span>TOP.01</span>
                                                    <p><strong>${fhResult['projname']}</strong> 离地铁最近，距${fhResult['nearestSubwayDesc']?split('$')[1]}<em>${(fhResult['nearestSubwayDesc']?split('$')[2]?number/1000)?string('#.#')}km</em>，约步行<em>${fhResult['nearestSubwayDesc']?split('$')[2]?number/90?round}</em>分钟</p>
                                                </div>
                                            </div>
                                        <#else >
                                            <div class="traffic-text">
                                                <div class="traffice-table-row">
                                                    <span>TOP.02</span>
                                                    <p><strong>${fhResult['projname']}</strong> 次近，距${fhResult['nearestSubwayDesc']?split('$')[1]}<em>${(fhResult['nearestSubwayDesc']?split('$')[2]?number/1000)?string('#.#')}km</em>，约步行<em>${fhResult['nearestSubwayDesc']?split('$')[2]?number/90?round}</em>分钟</p>
                                                </div>
                                            </div>
                                        </#if>
                                    </#if>
                                </#list>
                            </div></#if>
                        </section>
                        <section>
                            <div class="traffic-title-box">
                                <div class="traffic-title zijia">
                                    <p>自驾<br>出行</p>
                                    <div>快去最近的环线桥</div>
                                </div>
                            </div>
                            <div class="echart-box mb0">
                                <div id="trafficRondChart"></div>
                            </div>
                            <#if intelligenceFhRes?exists><div class="traffic-text-box mb0">
                                <#assign fhResults =intelligenceFhRes['fhResult']?eval>
                                <#list fhResults?sort_by('nearbyRoadMeter') as fhResult>
                                    <#if fhResult['projname']?exists&&fhResult['projname']!=''&&fhResult['nearbyQiao']?exists&&fhResult['nearbyQiao']!='' &&fhResult_index lt 2>
                                        <#if fhResult_index == 0>
                                            <div class="traffic-text">
                                                <div class="traffice-table-row">
                                                    <span>TOP.01</span>
                                                    <p><strong>${fhResult['projname']}</strong> 离环线桥最近，距${fhResult['nearbyQiao']}<em>${(fhResult['nearbyRoadMeter']?number/1000)?string('#.#')}km</em>，驾车约<em>${(fhResult['nearbyRoadMeter']?number/800)?ceiling}</em>分钟</p>
                                                </div>
                                            </div>
                                        <#else >
                                            <div class="traffic-text">
                                                <div class="traffice-table-row">
                                                    <span>TOP.02</span>
                                                    <p><strong>${fhResult['projname']}</strong> 次近，距${fhResult['nearbyQiao']}<em>${(fhResult['nearbyRoadMeter']?number/1000)?string('#.#')}km</em>，驾车约<em>${(fhResult['nearbyRoadMeter']?number/800)?ceiling}</em>分钟</p>
                                                </div>
                                            </div>
                                        </#if>
                                    </#if>
                                </#list>
                            </div></#if>
                        </section>
                    </section>
                    <section>
                        <div class="piece-title">
                            <div class="piece-title-border">
                                <p>宜居</p>
                                <span>住的舒服点，从空气质量到景观，从人口密度到车位</span>
                            </div>
                        </div>
                        <table class="table-live">
                            <tr>
                                <td><em>楼龄</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['finishdate']?exists&&fhResult['finishdate']!=''>
                                            <#assign date = .now?string("yyyy年MM月dd日")?substring(0,4)?number - fhResult['finishdate']?date("yyyy")?string("yyyy年MM月dd日")?substring(0,4)?number>
                                            <#if date?exists&&date gt 0>
                                                <td>${date}年</td>
                                            <#else >
                                                <td>-</td>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>绿化率</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['virescencerate']?exists&&fhResult['virescencerate']?number gt 0>
                                            <td>${fhResult['virescencerate']+'%'}</td>
                                        <#else >
                                            <td>-</td>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>车位比</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['parkRadio']?exists&&fhResult['parkRadio']!=''>
                                            <td>${fhResult['parkRadio']}</td>
                                        <#else >
                                            <td>-</td>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>空气<br>质量</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <td>${fhResult['airQuality']!'-'}</td>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>电梯</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['liftDoorRadio']?exists&&fhResult['liftDoorRadio']!=''>
                                            <td>${fhResult['liftDoorRadio']}</td>
                                        <#else >
                                            <td>-</td>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>供暖</em></td>
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
                                                <td>-</td>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                        </table>
                    </section>
                    <section>
                        <div class="piece-title">
                            <div class="piece-title-border">
                                <p>生活成本</p>
                                <span>处处都要钱，那一个亿的小目标，看来得快点实现啊</span>
                            </div>
                        </div>
                        <table class="table-life">
                            <tr>
                                <td><em>物业费<br>(/㎡·月)</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults =intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['propertyfee']?exists&&fhResult['propertyfee']?number gt 0>
                                            <td>${fhResult['propertyfee']}元</td>
                                        <#else >
                                            <td>-</td>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                            <tr>
                                <td><em>水电费<br>(/吨)<br>(/度)</em></td>
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
                                <td><em>停车费<br>(/年)</em></td>
                                <#if intelligenceFhRes?exists>
                                    <#assign fhResults = intelligenceFhRes['fhResult']>
                                    <#list fhResults?eval as fhResult>
                                        <#if fhResult['carRentPrice']?exists&&fhResult['carRentPrice']?number gt 0>
                                            <td>${fhResult['carRentPrice']?number?round}元</td>
                                        <#else >
                                            <td>-</td>
                                        </#if>
                                    </#list>
                                </#if>
                            </tr>
                        </table>
                    </section>

                    <#if intelligenceFhRes?exists>
                            <#assign fhResults = intelligenceFhRes['fhResult']?eval>
                        <#assign xiuxian = 0>
                        <#assign jiaoyu = 0>
                        <#assign yiliao = 0>
                        <#list fhResults as fhResult>
                            <#if fhResult['typeCount']?exists>
                                <#if fhResult['typeCount']['xiuxian']?exists>
                                    <#assign xiuxian = xiuxian+1>
                                    <#assign fhResultXiuxian = fhResult['typeCount']['xiuxian']>
                                </#if>
                                <#if fhResult['typeCount']['jiaoyu']?exists>
                                    <#assign jiaoyu = jiaoyu+1>
                                </#if>
                                <#if fhResult['typeCount']['yiliao']?exists>
                                    <#assign yiliao = yiliao+1>
                                </#if>
                            </#if>
                        </#list>
                        <section>
                            <#if xiuxian != 0><section>
                                <div class="piece-title">
                                    <div class="piece-title-border">
                                        <p>休闲购物</p>
                                        <span>1km生活圈，吃喝玩乐买买买</span>
                                    </div>
                                </div>
                                <div class="level-slider-box">
                                    <div class="level-slider">
                                       <#list fhResults as fhResult>
                                       <#if fhResult_index < 3>
                                        <div class="level-slider-item prev">
                                            <div class="level-item-content-wrapper">
                                                <div class="level-item-title">${fhResult['projname']}</div>
                                                <ul class="item-list">
                                                    <li><i></i><span>超市</span><em>${fhResult['typeCount']['xiuxian']['chaoshi']}个</em></li>
                                                    <li><i></i><span>商场</span><em>${fhResult['typeCount']['xiuxian']['shangchang']}个</em></li>
                                                    <li><i></i><span>菜市场</span><em>${fhResult['typeCount']['xiuxian']['caishichang']}个</em></li>
                                                    <li><i></i><span>餐厅</span><em>${fhResult['typeCount']['xiuxian']['canting']}个</em></li>
                                                </ul>
                                            </div>
                                        </div>
                                       </#if>
                                       </#list>
                                    </div>
                                    <div class="level-slider">
                                    <#list fhResults as fhResult>
                                    <#if (fhResult_index > 2)>
                                        <div class="level-slider-item last">
                                            <div class="level-item-content-wrapper">
                                                <div class="level-item-title">${fhResult['projname']}</div>
                                                <ul class="item-list">
                                                    <li><i></i><span>超市</span><em>${fhResult['typeCount']['xiuxian']['chaoshi']}个</em></li>
                                                    <li><i></i><span>商场</span><em>${fhResult['typeCount']['xiuxian']['shangchang']}个</em></li>
                                                    <li><i></i><span>菜市场</span><em>${fhResult['typeCount']['xiuxian']['caishichang']}个</em></li>
                                                    <li><i></i><span>餐厅</span><em>${fhResult['typeCount']['xiuxian']['canting']}个</em></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                                    </div>
                                </div>
                            </section></#if>
                            <#if jiaoyu !=0><section>
                                <div class="piece-title">
                                    <div class="piece-title-border">
                                        <p>教育配套</p>
                                        <span>1km内教育配套，就这样陪你长大</span>
                                    </div>
                                </div>
                                <section class="folding-slider-wrapper">
                                    <div class="folding-slider-box">
                                        <div class="side-title">
                                            <i class="youeryuan"></i><span>幼 儿 园</span>
                                        </div>
                                        <div class="folding-item-box">
                                          <#list fhResults as fhResult>
                                            <div class="folding-item">
                                                <div class="folding-content-wrapper">
                                                    <div class="folding-item-title">${fhResult['projname']}</div>
                                                    <ul class="item-list">
                                                        <#assign youers = fhResult['dataInfo']['jiaoyu']['youeryuan']>
                                                        <#if (youers?size >0)>
                                                            <#list youers as youeritem>
                                                            <li><span>${youeritem['name']}</span><em>${(youeritem['distance']?float/1000)?string("#.#")}km</em></li>
                                                            </#list>
                                                        <#else ><li><span>1km内暂无幼儿园</span></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                          </#list>
                                        </div>
                                    </div>
                                    <div class="folding-slider-box">
                                        <div class="side-title">
                                            <i class="xiaoxue"></i><span>小 学</span>
                                        </div>
                                        <div class="folding-item-box">
                                          <#list fhResults as fhResult>
                                            <div class="folding-item">
                                                <div class="folding-content-wrapper">
                                                    <div class="folding-item-title">${fhResult['projname']}</div>
                                                    <#assign xiaoxue = fhResult['dataInfo']['jiaoyu']['xiaoxue']>
                                                    <ul class="item-list">
                                                        <#if (xiaoxue?size >0)>
                                                            <#list xiaoxue as xiaoxueitem>
                                                                <li><span>${xiaoxueitem['name']}</span><em>${(xiaoxueitem['distance']?float/1000)?string("#.#")}km</em></li>
                                                            </#list>
                                                        <#else ><li><span>1km内暂无小学</span></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                          </#list>
                                        </div>
                                    </div>
                                    <div class="folding-slider-box">
                                        <div class="side-title">
                                            <i class="zhongxue"></i><span>中 学</span>
                                        </div>
                                        <div class="folding-item-box">
                                          <#list fhResults as fhResult>
                                            <div class="folding-item">
                                                <div class="folding-content-wrapper">
                                                    <div class="folding-item-title">${fhResult['projname']}</div>
                                                    <#assign zhongxue = fhResult['dataInfo']['jiaoyu']['zhongxue']>
                                                    <ul class="item-list">
                                                        <#if (zhongxue?size >0)>
                                                            <#list zhongxue as zhongxueitem>
                                                                <li><span>${zhongxueitem['name']}</span><em>${(zhongxueitem['distance']?float/1000)?string("#.#")}km</em></li>
                                                            </#list>
                                                        <#else ><li><span>1km内暂无中学</span></li>
                                                        </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                          </#list>
                                        </div>
                                    </div>
                                </section>
                            </section></#if>
                            <#if yiliao !=0><section>
                                <div class="piece-title">
                                    <div class="piece-title-border">
                                        <p>医疗配套</p>
                                        <span>3km医疗配套，为您的健康保驾护航</span>
                                    </div>
                                </div>
                                <section class="folding-slider-wrapper">
                                    <div class="folding-slider-box">
                                        <div class="side-title">
                                            <i class="yiyuan"></i><span>综 合 医 院</span>
                                        </div>
                                        <div class="folding-item-box">
                                          <#list fhResults as fhResult>
                                            <div class="folding-item">
                                                <div class="folding-content-wrapper">
                                                    <div class="folding-item-title">${fhResult['projname']}</div>
                                                    <#assign zonghe = fhResult['dataInfo']['yiliao']['zonghe']>
                                                    <ul class="item-list">
                                                     <#if (zonghe?size >0)>
                                                       <#list zonghe as zongheitem>
                                                        <li><span>${zongheitem['name']}</span><em>${(zongheitem['distance']?float/1000)?string("#.#")}km</em></li>
                                                       </#list>
                                                     </#if>
                                                    </ul>
                                                </div>
                                            </div>
                                          </#list>
                                        </div>
                                    </div>
                                </section>
                            </section></#if>
                        </section>
                    </#if>
                </div>
            </div>
            <div class="module-item">
                <div class="report-title">
                    <i class="icon-title-review"></i>
                    <p>为您甄选的5个小区还满意吗？</p>
                </div>
                <div class="piece-module">
                    <#if intelligenceFhRes?exists><ul class="reviwe–item-box clear">
                        <#assign fhResults = intelligenceFhRes['fhResult']>
                        <#list fhResults?eval as fhResult>
                            <li><a href="${router_city('/xiaoqu/'+fhResult['newcode']?c+'.html')}">
                                <div class="review-img-box">
                                    <#if fhResult['plotImage']?exists && fhResult['plotImage'] != ''>
                                        <img width="100%" src="${qiniuimage}/${fhResult['plotImage']?split(',')[0]}-tt400x300" alt="${fhResult['projname']}">
                                    <#else >
                                        <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                                    </#if>
                                </div>
                                <p>${fhResult['projname']}</p>
                            </a></li>
                        </#list>
                    </ul></#if>
                </div>
            </div>
            <div class="module-item">
                <div class="report-end-word">
                    <p>选房就是选生活</p>
                    <p>发现美好家园 就在头条房产</p>
                </div>
                <div class="report-bottom-button">
                    <div class="report-button collect-button">
                        <span>收藏</span>
                    </div>
                    <div class="report-button share-button">
                        <span>分享</span>
                    </div>
                </div>
                <img src="/static/images/intelligent/report-page-bottom.png" width="100%" alt="发现美好家园 就在头条房产">
            </div>
        </div>
    </div>
</div>
<div class="share-pop none">
    <img width="100%" src="/static/images/intelligent/bgy_share_fc.png" alt="分享给好友">
    <img width="49%" id="off-share" src="/static/images/intelligent/bgy_share_btn.png" alt="我知道了">
</div>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/draggabilly.pkgd.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/elastiStack.js?v=${staticversion}"></script>
<script src="${staticurl}/js/intelligent-report.js?v=${staticversion}"></script>
<script>
    new ElastiStack(document.getElementById('elastics-stack'));
    $(function () {
        var status = ${intelligenceFhRes.collectStatus};
        if (status == 1) {
            $('.collect-button').addClass('active');
            $('.collect-button').find('span').text('已收藏')
        } else {
            $('.collect-button').removeClass('active');
            $('.collect-button').find('span').text('收藏')
        }

        $('.collect-button').on('click', function () {
            var reportId = ${reportId};
            var backUrl = "${backUrl}";
            $(this).toggleClass('active');

            if ($(this).hasClass('active')) {
                // 收藏
                zhuge.track('收藏报告_懂房帝');
                if (reportId != "" && reportId != null) {
                    $.ajax({
                        type: "GET",
                        async: true,
                        url: router_city('/findhouse/collectMyReport') + "?reportId=" + reportId,
                        data: reportId,
                        dataType: "json",
                        success: function (data) {
                            if (data.code == "success") {
                                $('.collect-button').find('span').text('已收藏');
                            }
                            if (data.code == "no-login") {
                                //重定向到登陆页面
                                window.location.href = "/user/login?backUrl="+backUrl+"&title="+"dongfangdi";
                            }
                            // 收藏失败
                            if (data.code == "cancel") {
                                $('.collect-button').removeClass('active');
                                $('.collect-button').find('span').text('收藏');
                            }
                        }
                    })
                }
            } else {
                // 取消收藏
                $.ajax({
                    type: "GET",
                    async: true,
                    url: router_city('/findhouse/cancleMyReport/') + reportId,
                    data: reportId,
                    dataType: "json",
                    success: function (data) {

                        // 字段待确定
                        if (data.code == "success") {
                            $('.collect-button').find('span').text('收藏');
                        }
                        if (data.code == "fail") {
                            // 取消收藏失败
                            $('.collect-button').addClass('active');
                            $('.collect-button').find('span').text('已收藏');
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

        that.speed = 12;
        that.el.attr({x: init_x});
        var randomNum = Math.random() * 200;
        cy = 0 - parseInt(randomNum, 10);
        that.el.attr({"y": cy});
        var timer = setInterval(function () {
            var cy = that.el.attr("y");
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
    var trend = ${trend};

    var baseFontSize = 12 * dpr;
    var barBarWidth = 8 * dpr;

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

    function getSubway() {

        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            if (datajson[i]["nearestSubwayDesc"] == null){
                res.push([''])
            }else {
                res.push([(parseInt(datajson[i]["nearestSubwayDesc"].split("$")[2])/1000).toFixed(1)])
            }
        }
        return res;
    }
    function getMetroStation(index) {
        if (index < datajson.length) {
            return datajson[index]["nearestSubwayDesc"].split("$") || ""
        }
        return "";
    }


    function getNearbyQiao(index) {
        if (index < datajson.length) {
            return datajson[index]["nearbyQiao"] || ""
        }
        return "";
    }

    function getNearbyRoad(index) {
        if (index < datajson.length) {
            return datajson[index]["nearbyRoad"] || ""
        }
        return "";
    }


    function getNearbyRoadMeter() {
        var res = [];
        for (var i = 0; i < datajson.length; i++) {
            res.push([(parseInt(datajson[i]["nearbyRoadMeter"] || "") / 1000).toFixed(1).toString()])
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
            top: '20%',
            left: '13%',
            right: '7%',
            bottom: '10%',
            containLabel: false
        };

        /**
         * 市场价格走势
         * */
        var priceChart = echarts.init(document.getElementById('priceChart'));
        // 显示标题，图例和空的坐标轴
        priceChart.setOption({
            color: ['#455765', '#f25a5a', '#fece6c', '#7f7f7f', '#4a7aa3'],
            textStyle: {fontSize: baseFontSize},
            tooltip: {
                trigger: 'axis',
                textStyle: {fontSize: baseFontSize}
            },
            grid: chartGrid,
            xAxis: {
                show: true,
                axisTick: {show: false},
                boundaryGap: false,
                scale: true,
                axisLabel: {fontSize: baseFontSize - 4},
                data: getJiagezoushiYuefen()
            },
            yAxis: [{
                name: '元/㎡',
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {fontSize: baseFontSize - 4},
                max:function(value) {
                    return value.max + 5000;
                },
                min:function(value) {
                    return value.min - 10000;
                }
            },{
                type: 'value',
                scale: true,
                axisLine: {show: false},
                axisTick: {show: false},
                axisLabel: {show: false},
                splitLine: {show: false}
            }],
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
        var marketChart = echarts.init(document.getElementById('marketChart'));
        // 显示标题，图例和空的坐标
        marketChart.setOption({
            textStyle: {fontSize: baseFontSize},
            tooltip: {
                show: true,
                trigger: 'axis',
                textStyle: {fontSize: baseFontSize},
                axisPointer: {type: 'shadow'}
            },
            grid: {
                top: '15%',
                left: '5%',
                right: '5%',
                bottom: '10%',
                containLabel: false
            },
            yAxis: {
                type: 'value',
                axisTick: {show: false},
                axisLine: {show: false},
                splitLine: {show: true},
                axisLabel: {show: false}
            },
            xAxis: [
                {
                    type: 'category',
                    axisTick: {show: false},
                    axisLine: {show: true},
                    axisLabel: {fontSize: baseFontSize - 4},
                    data: getGongxuqingkuangYuefen()
                }
            ],
            series: [
                {
                    name: '北京市场',
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            show: true,
                            color: '#747788',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0],
                            borderWidth: 0
                        }
                    },
                    barGap: 0.2,
                    barWidth: barBarWidth,
                    data: getGongxuqingkuangBeijing()
                },
                {
                    name: '目标市场',
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            show: true,
                            color: '#EA5956',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0],
                            borderWidth: 0
                        }
                    },
                    barGap: 0.2,
                    barWidth: barBarWidth,
                    data: getGongxuqingkuangMuBiao()
                }
            ]
        });

        /**
         * 地铁信息
         * */
        var trafficSubwayChart = echarts.init(document.getElementById('trafficSubwayChart'));
        // 显示标题，图例和空的坐标
        var trafficSubwayGrid = {
            right: '0%',
            left: '5%',
            top: 0,
            bottom: '20%',
            containLabel: true
        };
        var trafficSubwayLabel = {
            normal: {
                show: true,
                position: 'bottom',
                color: '#666',
                fontSize: baseFontSize - 5,
                formatter: function (params, ticket, callback) {
                    var para = '';
                    if(params.data!=null&&params.data!=""){
                        para = para+params.data + "km"
                    }
                    if(getMetroStation(params.seriesIndex)!=null&&getMetroStation(params.seriesIndex)!=""&&getMetroStation(params.seriesIndex).length==3){
                        para = para +"\n"+getMetroStation(params.seriesIndex)[1]+"\n("+getMetroStation(params.seriesIndex)[0]+")"
                    }
                    return para;
                }

            }
        };
        trafficSubwayChart.setOption({
            textStyle: { fontSize: baseFontSize },
            grid: trafficSubwayGrid,
            xAxis: {
                show: true,
                data: []
            },
            yAxis: {
                show: true,
                splitLine: {show: false},
                axisLabel: {show: false},
                axisTick: {show: false}
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#34B99B',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getSubway()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#8C45CE',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getSubway()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#F76284',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getSubway()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#EE8C49',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getSubway()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    label: trafficSubwayLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#6B92F2',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getSubway()[4]
                }
            ]
        });

        /**
         * 环线桥信息
         * */
        var trafficRondChart = echarts.init(document.getElementById('trafficRondChart'));
        // 显示标题，图例和空的坐标

        var trafficRondLabel = {
            normal: {
                show: true,
                position: 'bottom',
                color: '#666',
                fontSize: baseFontSize - 5,
                formatter: function (params, ticket, callback) {
                    var para = '';
                    if(params.data!=null&&params.data!=""){
                        para = para+params.data + "km"
                    }
                    if(getNearbyQiao(params.seriesIndex)!=null&&getNearbyQiao(params.seriesIndex)!=""){
                        para = para+"\n"+getNearbyQiao(params.seriesIndex)
                    }
                    if(getNearbyRoad(params.seriesIndex)!=null&&getNearbyRoad(params.seriesIndex)!=""){
                        para = para+"\n" + "(" + getNearbyRoad(params.seriesIndex) + ")"
                    }
                    return para;
                }
            }
        };
        trafficRondChart.setOption({
            textStyle: {fontSize: baseFontSize},
            grid: trafficSubwayGrid,
            xAxis: {
                show: true,
                data: []
            },
            yAxis: {
                show: true,
                splitLine: {show: false},
                axisLabel: {show: false},
                axisTick: {show: false}
            },
            series: [
                {
                    name: getPlotName()[0],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#34B99B',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getNearbyRoadMeter()[0]
                },
                {
                    name: getPlotName()[1],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#8C45CE',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getNearbyRoadMeter()[1]
                },
                {
                    name: getPlotName()[2],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#F76284',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getNearbyRoadMeter()[2]
                },
                {
                    name: getPlotName()[3],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#EE8C49',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getNearbyRoadMeter()[3]
                },
                {
                    name: getPlotName()[4],
                    type: 'bar',
                    label: trafficRondLabel,
                    barGap: 7,
                    barWidth: barBarWidth,
                    itemStyle: {
                        normal: {
                            color: '#6B92F2',
                            barBorderRadius: [barBarWidth, barBarWidth, 0, 0]
                        }
                    },
                    data: getNearbyRoadMeter()[4]
                }
            ]
        });
    })
</script>
</body>
</html>