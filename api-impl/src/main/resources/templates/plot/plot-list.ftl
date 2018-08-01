<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/list.css?v=${staticversion}">
    <title>懂房帝看二手房小区</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<#include "../StatisticsHeader.ftl">
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="懂房帝">
<header class="main-top-header">
    <input id="url" type="hidden" value="${router_city('/xiaoqu')}">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="懂房帝"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text"  class="search-link" placeholder="" value="<#if RequestParameters.keyword??>${RequestParameters.keyword}</#if>">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<section class="category-box">
    <ul id="category-tab">
        <li data-mark="tab-place"><span><em>区域</em><i></i></span></li>
        <li data-mark="tab-price"><span><em>均价</em><i></i></span></li>
        <li data-mark="tab-age"><span><em>楼龄</em><i></i></span></li>
        <li data-mark="tab-more"><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none">
        <div class="category-cont">
            <!-- 区域 -->
            <div class="filter-item" data-mark="panel-place">
                <div class="place-list">
                    <ul id="level1" class="nav" data-mark="level1"></ul>
                    <ul id="level2" class="guide none" data-mark="level2"></ul>
                    <ul id="level3" class="cont none" data-mark="level3"></ul>
                </div>
            </div>
            <!-- 价格 -->
            <div class="filter-item" data-mark="panel-price">
                <div class="price-list">
                    <ul>
                        <li data-begin-price="" data-end-price="" class="current">不限</li>
                        <li data-begin-price="0.0" data-end-price="20000.0">2万元以下</li>
                        <li data-begin-price="20000.0" data-end-price="30000.0">2-3万元</li>
                        <li data-begin-price="30000.0" data-end-price="40000.0">3-4万元</li>
                        <li data-begin-price="40000.0" data-end-price="60000.0">4-6万元</li>
                        <li data-begin-price="60000.0" data-end-price="80000.0">6-8万元</li>
                        <li data-begin-price="80000.0" data-end-price="2000000000.0">8万元以上</li>
                    </ul>
                </div>
            </div>
            <!-- 楼龄 -->
            <div class="filter-item" data-mark="panel-age">
                <div class="age-list">
                    <ul>
                        <li class="current" data-info="">不限</li>
                        <li data-info="[0-5]">5年内</li>
                        <li data-info="[0-10]">10年内</li>
                        <li data-info="[0-15]">15年内</li>
                        <li data-info="[0-20]">20年内</li>
                        <li data-info="[20-120]">20年以上</li>
                    </ul>
                </div>
            </div>
            <!-- 更多 -->
            <div class="filter-item" data-mark="panel-more">
                <div class="more-list">
                    <dl>
                        <dt data-type="propertyTypeId">物业类型</dt>
                        <dd>
                            <span data-info="1">住宅</span>
                            <span data-info="2">别墅</span>
                        <#--<span data-info="3">写字楼</span>
                        <span data-info="4">商铺</span>
                        <span data-info="5">底商</span>-->
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="houseAreaSize">面积</dt>
                        <dd>
                            <span data-info="[0-60]">60平以下</span>
                            <span data-info="[60-90]">60-90平</span>
                            <span data-info="[90-110]">90-110平</span>
                            <span data-info="[110-130]">110-130平</span>
                            <span data-info="[130-150]">130-150平</span>
                            <span data-info="[150-200]">150-200平</span>
                            <span data-info="[200-10000]">200平以上</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="elevatorFlag">电梯</dt>
                        <dd>
                            <span class="only" data-info="1">有</span>
                            <span class="only" data-info="0">无</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="buildingType">建筑类型</dt>
                        <dd>
                            <span data-info="1">板楼</span>
                            <span data-info="2">塔楼</span>
                            <span data-info="3">板塔结合</span>
                        <#--<span data-info="4">砖楼</span>-->
                        </dd>
                    </dl>
                <#--<dl>-->
                <#--<dt data-type="saleType">销售状态</dt>-->
                <#--<dd>-->
                <#--<span data-info="1">售完</span>-->
                <#--<span data-info="2">在售</span>-->
                <#--&lt;#&ndash;<span data-info="3">不在售</span>-->
                <#--<span data-info="4">出租</span>-->
                <#--<span data-info="5">待租</span>&ndash;&gt;-->
                <#--<span data-info="6">待售</span>-->
                <#--</dd>-->
                <#--</dl>-->
                    <dl>
                        <dt data-type="buildingFeature">楼盘特色</dt>
                        <dd>
                            <#--<span data-info="1">别墅</span>-->
                            <span data-info="5">花园洋房</span>
                            <span data-info="1">近地铁</span>
                            <span data-info="7">车位充足</span>
                            <span data-info="9">500强房企</span>
                            <#--<span data-info="6">高绿化</span>-->
                        </dd>
                    </dl>
                <#--<dl>-->
                <#--<dt data-type="deliverStyle">装修标准</dt>-->
                <#--<dd>-->
                <#--<span data-info="1">毛坯</span>-->
                <#--<span data-info="2">普通装修</span>-->
                <#--<span data-info="3">精装修</span>-->
                <#--<span data-info="4">豪华装修</span>-->
                <#--<span data-info="5">其他</span>-->
                <#--</dd>-->
                <#--</dl>-->
                </div>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button more-reset" id="moreReset">重置</a>
                    <a href="javascript:;" class="operation-button more-submit" id="moreSubmit">确定</a>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="result-section">
    <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.toutiaofangchan.bidewucustom" class="app-download-top-tips-wrapper">
        <div class="download-btn">
            <i></i>
            <span>下载懂房帝APP</span>
        </div>
    </a>
    <ul id="valueList" class="list-item-wrapper"></ul>
<#--<#if villageList?exists>-->
        <#--<#list villageList as plot>-->
            <#--<li><a id="${plot.total}" onclick="plot_title(this)" class="list-item" href="${router_city('/xiaoqu/'+plot['id']?c+'.html')}">-->
            <#--&lt;#&ndash;<input type="hidden" name="total" value="${plot.total}">&ndash;&gt;-->
                <#--<div class="clear">-->
                    <#--<#if plot['photo']?exists>-->
                        <#--<div class="list-item-img-box">-->
                            <#--<#if plot['photo']?exists>-->
                                <#--<#assign photo = plot['photo']>-->
                                <#--<#if photo[0]?exists><img src="${qiniuimage}/${photo[0]}-tt400x300" alt="${plot['rc']}">-->
                                    <#--<#else><img src="${staticurl}/images/global/tpzw_image.png" alt="暂无数据">-->
                                <#--</#if>-->
                            <#--</#if>-->
                        <#--</div>-->
                    <#--</#if>-->
                    <#--<div class="list-item-cont">-->
                        <#--<h3 class="cont-block-1"><span><#if plot['rc']?exists>${plot['rc']}<#else>暂无数据</#if></span></h3>-->
                        <#--<p class="cont-block-2 plot"><#if plot['abbreviatedAge']?exists>${plot['abbreviatedAge']}年建成</#if></p>-->
                        <#--<#if plot['metroWithPlotsDistance']?exists>-->
                            <#--<#assign map = plot['metroWithPlotsDistance']>-->
                            <#--<#if plot['key']?exists>-->
                                <#--<#if map[plot['key']]?exists>-->
                                    <#--<#assign split=map[plot['key']]?split("$")/>-->
                                    <#--<p class="cont-block-3 distance">-->
                                        <#--<i class="icon"></i>-->
                                        <#--<#if split[2]?number gt 1000>-->
                                            <#--<#assign x = split[2]?number/1000>-->
                                            <#--距离${split[1]}[${split[0]}] ${x?string("#.#")}km-->
                                        <#--<#else>-->
                                            <#--距离${split[1]}[${split[0]}] ${split[2]}m-->
                                        <#--</#if>-->
                                    <#--</p>-->
                                <#--<#else>-->
                                    <#--<p class="cont-block-3 distance"><i class="icon"></i>-->
                                        <#--<#if plot['area']?exists&&plot['area']!=''>-->
                                        <#--${plot['area']}${'-'+plot['tradingArea']}-->
                                        <#--<#else>-->
                                            <#--<#if plot['tradingArea']?exists&&plot['tradingArea']!=''>-->
                                            <#--${plot['tradingArea']}-->
                                            <#--</#if>-->
                                        <#--</#if>-->
                                    <#--</p>-->
                                <#--</#if>-->
                            <#--<#else>-->
                                <#--<#if plot['tradingArea']?exists>-->
                                    <#--<p class="cont-block-3 distance"><i class="icon"></i>${plot['area']!'暂无数据'}-${plot['tradingArea']!'暂无数据'}</p>-->
                                <#--</#if>-->
                            <#--</#if>-->
                        <#--<#else>-->
                            <#--<#if plot['tradingArea']?exists>-->
                                <#--<p class="cont-block-3 distance"><i class="icon"></i>${plot['area']!'暂无数据'}-${plot['tradingArea']!'暂无数据'}</p>-->
                            <#--</#if>-->
                        <#--</#if>-->
                        <#--<div class="cont-block-4 house-labelling gray">-->
                            <#--<#if plot['label']?exists>-->
                                <#--<#assign item =  plot['label']>-->
                                <#--<#list item as itemValue>-->
                                    <#--<#if itemValue?exists>-->
                                        <#--<#if itemValue_index lt 3>-->
                                            <#--<span>${itemValue}</span>-->
                                        <#--</#if>-->
                                    <#--</#if>-->
                                <#--</#list>-->
                            <#--</#if>-->
                        <#--</div>-->
                        <#--<div class="cont-block-price plot">-->
                            <#--<em>${plot['avgPrice']}元/㎡</em>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</a></li>-->
        <#--</#list>-->
    <#--</#if>-->
    <#--<p class="tip-box none">有新上房源，我们会及时通知您哦！</p>-->
</section>
<#include "../user.ftl">
<#include "../search.ftl">
<div class="sort-icon"></div>
<div class="sort-content-box">
    <div class="sort-mask"></div>
    <ul class="sort-content">
    <#if sort?exists>
        <li value="0" <#if sort==0>class="current"</#if>><p>默认排序</p></li>
        <li value="1" <#if sort==1>class="current"</#if>><p>价格由高到低</p></li>
        <li value="2" <#if sort==2>class="current"</#if>><p>价格由低到高</p></li>
    </#if>
    </ul>
</div>

<script id="listContent" type="text/html">
    {{each data}}
    {{if $index == 3 && $value.pageNum == 1 || $index == 5 && $value.pageNum == 2}}
    <li>
        <a class="list-item" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.toutiaofangchan.bidewucustom">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/house-app-download.jpg" alt="优惠房源">
                </div>
                <div class="list-item-cont download-app-tips-type1">
                    <h3 class="cont-block-1"><span>{{$value.current_month}}月北京优惠房源</span></h3>
                    <p class="cont-block-2">实时更新</p>
                    <p class="cont-block-3">尽在懂房帝APP</p>
                    <div class="cont-block-btn">立即下载</div>
                </div>
            </div>
        </a>
    </li>
    {{else}}
    <li>
        <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId={{$value.id}}&__topic__=xiaoqubaoguang'/>-->
        <a id="{{$value.total}}" class="list-item" data-id = "{{$value.pageNum}}" onclick="plot_list(this)" url="<%= $imports.router_city('/xiaoqu/'+$value.id+'.html') %>" href="javascript:void(0);">
        <div class="clear">
            <div class="list-item-img-box">
                {{if $value.photo && $value.photo.length > 0}}
                <img src="${qiniuimage}/{{$value.photo[0]}}-tt400x300" alt="{{$value.rc}}">
                {{else}}
                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                {{/if}}
            </div>
            <div class="list-item-cont">
                <h3 class="cont-block-1"><span>{{$value.rc}}</span></h3>
                <p class="cont-block-2 plot">{{if $value.abbreviatedAge}}{{$value.abbreviatedAge}}年建成{{else}}暂无数据{{/if}}</p>
                {{if $value.metroWithPlotsDistance || $value.tradingArea}}
                <p class="cont-block-3 distance"><i class="icon"></i>
                    {{if $value.subwayDesc}}
                    {{$value.subwayDesc}}
                    {{else if $value.tradingArea}}
                    {{if $value.area}}{{$value.area}}{{else}}暂无数据{{/if}}-{{if
                    $value.tradingArea}}{{$value.tradingArea}}{{else}}暂无数据{{/if}}
                    {{/if}}
                </p>
                {{else}}
                <p class="cont-block-3 distance">暂无数据</p>
                {{/if}}
                {{if $value.label && $value.label.length > 0}}
                <div class="cont-block-4 house-labelling gray">
                    {{each $value.label as value i}}
                    {{if i < 3}}
                    <span>{{value}}</span>
                    {{/if}}
                    {{/each}}
                </div>
                {{/if}}
                <div class="cont-block-price plot">
                    <em>{{$value.avgPrice}}元/㎡</em>
                </div>
            </div>
        </div>
    </a></li>
    {{/if}}
    {{/each}}
</script>
<script>
    $(function () {
        var url = document.referrer;
        if(url.indexOf("/xiaoqu") > 0){
            if(GetQueryString("keyword")!='undefined'){
                zhuge.track("搜索_小区",{
                    "关键词":GetQueryString("keyword"),
                    "返回结果数量":$("#result-section").find("li").find('a').attr("id")
                })
            }
        }else{
            if(GetQueryString("keyword")!='undefined'){
                zhuge.track("搜索_大首页",{
                    "搜索类型":"小区",
                    "关键词":GetQueryString("keyword"),
                    "返回结果数量":$("#result-section").find("li").find('a').attr("id")
                })
            }
        }
    });
    function plot_title(a) {
        var link = $(a);
//        zhuge.track('点击小区列表页头条logo', {
//            "页面来源URL": window.location.href
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false
    }
    $("#result-section").on('click','li',function () {
        var link = $(this);
        zhuge.track('小区-点击小区', {
            "小区名称":link.find('div.list-item-img-box').find('img').attr('alt'),
            "建成年代":link.find('div.list-item-cont').find('p.cont-block-2.plot').find('em').text(),
            "参考均价":link.find('div.cont-block-price.plot').find('em').text(),
            "标签":link.find('div.cont-block-4.house-labelling.gray').text(),
            "位置信息":link.find('div.list-item-cont').find('p.cont-block-3.distance').text(),
            "第几屏":getDefaultPageNum(),
            "是否为广告":"否"
        }, function () {
            location.href = link.find('a').attr('href');
        });
        return false;
    });

    function GetQueryString(name) {
        var r = decodeURI(req[name]);
        if(r!=null)return r; return null;
    }

</script>
</body>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script src="${staticurl}/js/dropload.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/list-category.js?v=${staticversion}"></script>
<script src="${staticurl}/js/template-web.js?v=${staticversion}"></script>
<script>
    $('.sort-content-box').on('click', function (){
        var sortZhuge;
        if(GetQueryString('sort')==1){
            sortZhuge = '价格由高到低';
        }
        if(GetQueryString('sort')==2){
            sortZhuge = '价格由低到高';
        }
        var link = $(this);
//        zhuge.track('小区-排序',{'排序方式':sortZhuge},function () {
//        });
//        return false;
    });
    function plot_list(e) {
        setPageNum($(e).attr('data-id'))
        window.location.href = $(e).attr('url')
    }
</script>
</html>