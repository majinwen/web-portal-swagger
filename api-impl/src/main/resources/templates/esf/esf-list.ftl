<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/list.css?v=${staticversion}">
    <title>来懂房帝看二手房</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<#include "../StatisticsHeader.ftl">
</head>
<#setting url_escaping_charset="UTF-8">
<body>
<img class="shareTopImg" height="0" width="0" src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="懂房帝">
<header class="main-top-header">
    <input id="url" type="hidden" value="${router_city('/esf')}">
    <a href="/" onclick="esf_title(this)" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="懂房帝"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="" value="<#if RequestParameters.keyword??>${RequestParameters.keyword}</#if>">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="懂房帝"></a>
</header>
<section class="category-box">
    <ul id="category-tab">
        <li data-mark="tab-place"><span><em>区域</em><i></i></span></li>
        <li data-mark="tab-price"><span><em>总价</em><i></i></span></li>
        <li data-mark="tab-type"><span><em>户型</em><i></i></span></li>
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
                        <li data-begin-price="0.0" data-end-price="200.0">200万以下</li>
                        <li data-begin-price="200.0" data-end-price="250.0">200-250万</li>
                        <li data-begin-price="250.0" data-end-price="300.0">250-300万</li>
                        <li data-begin-price="300.0" data-end-price="400.0">300-400万</li>
                        <li data-begin-price="400.0" data-end-price="500.0">400-500万</li>
                        <li data-begin-price="500.0" data-end-price="800.0">500-800万</li>
                        <li data-begin-price="800.0" data-end-price="1000.0">800-1000万</li>
                        <li data-begin-price="1000.0" data-end-price="100000000.0">1000万以上</li>
                    </ul>
                </div>
            </div>
            <!-- 户型 -->
            <div class="filter-item" data-mark="panel-type">
                <div class="type-list">
                    <ul>
                        <li class="current" data-type="0">不限</li>
                        <li data-type="1">一居 <i></i></li>
                        <li data-type="2">二居 <i></i></li>
                        <li data-type="3">三居 <i></i></li>
                        <li data-type="4">四居 <i></i></li>
                        <li data-type="5">五居及五居以上 <i></i></li>
                    </ul>
                    <div class="submit-wrapper">
                        <a href="javascript:;" class="operation-button type-submit" id="typeSubmit">确定</a>
                    </div>
                </div>
            </div>
            <!-- 更多 -->
            <div class="filter-item" data-mark="panel-more">
                <div class="more-list">
                    <dl>
                        <dt data-type="propertyTypeId">物业类型</dt>
                        <dd>
                            <span data-info="1">普通住宅</span>
                            <span data-info="2">公寓</span>
                            <span data-info="3">酒店式公寓</span>
                            <span data-info="4">花园洋房</span>
                        <#--<span data-info="5">商住楼</span>-->
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="houseOrientationId">朝向</dt>
                        <dd>
                            <span data-info="1">东</span>
                            <span data-info="2">西</span>
                            <span data-info="3">南</span>
                            <span data-info="4">北</span>
                            <span data-info="5">东南</span>
                            <span data-info="6">西南</span>
                            <span data-info="7">东北</span>
                            <span data-info="8">西北</span>
                            <span data-info="9">东西</span>
                            <span data-info="10">南北</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="houseLabelId">标签</dt>
                        <dd>
                            <span data-info="1">近地铁</span>
                            <span data-info="3">随时看</span>
                            <span data-info="5">满二年</span>
                            <span data-info="4">满五年</span>
                            <span data-info="2">近公园</span>
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
                        <dt data-type="houseYearId">楼龄</dt>
                        <dd>
                            <span class="only" data-info="[0-5]">5年内</span>
                            <span class="only" data-info="[0-10]">10年内</span>
                            <span class="only" data-info="[0-15]">15年内</span>
                            <span class="only" data-info="[0-20]">20年内</span>
                            <span class="only" data-info="[20-120]">20年以上</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="elevatorFlag">电梯</dt>
                        <dd>
                            <span class="only" data-info="1">有</span>
                            <span class="only" data-info="2">无</span>
                        </dd>
                    </dl>
                <#--<dl>-->
                <#--<dt data-type="buildingTypeId">建筑类型</dt>-->
                <#--<dd>-->
                <#--<span data-info="1">板楼</span>-->
                <#--<span data-info="2">塔楼</span>-->
                <#--<span data-info="3">板塔结合</span>-->
                <#--</dd>-->
                <#--</dl>-->
                <#--<dl>-->
                <#--<dt data-type="ownership">权属</dt>-->
                <#--<dd>-->
                <#--<span data-info="1">已购公房</span>-->
                <#--<span data-info="2">商品房</span>-->
                <#--<span data-info="3">空置房</span>-->
                <#--<span data-info="4">使用权房</span>-->
                <#--<span data-info="5">央产</span>-->
                <#--<span data-info="6">经济适用房</span>-->
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
        {{if $value.claimHouseId!=''}}
            <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId={{$value.claimHouseId}}&__topic__=esfbaoguang'/>-->
            <a id="{{$value.total}}" class="list-item" data-id = "{{$value.pageNum}}" house-id ="{{$value.claimHouseId}}"  onclick="esf_list(this)"  url="${router_city('/esf/{{$value.claimHouseId}}.html')}" href="javascript:void(0);">
        {{else}}
            <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId={{$value.houseId}}&__topic__=esfbaoguang'/>-->
            <a id="{{$value.total}}" class="list-item" data-id = "{{$value.pageNum}}" house-id ="{{$value.houseId}}"  onclick="esf_list(this)"  url="${router_city('/esf/{{$value.houseId}}.html')}" href="javascript:void(0);">
        {{/if}}
            <div class="clear">
                {{if $value.claimHouseId==''}}
                <div class="list-item-img-box">
                    {{if $value.housePhotoTitle && $value.housePhotoTitle.length > 0}}
                    <img src="{{$value.housePhotoTitle}}" alt="{{$value.houseBusinessName}}">
                    {{else}}
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    {{/if}}
                </div>
                {{else}}
                <div class="list-item-img-box">
                    {{if $value.claimHousePhotoTitle && $value.claimHousePhotoTitle.length > 0}}
                    <img src="{{$value.claimHousePhotoTitle}}" alt="{{$value.houseBusinessName}}">
                    {{else}}
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    {{/if}}
                </div>
                {{/if}}
                <div class="list-item-cont">
                    {{if $value.claimHouseId==''}}
                    <h3 class="cont-block-1"><span>{{$value.houseTitle}}</span></h3>
                    {{else}}
                    <h3 class="cont-block-1"><span>{{$value.claimHouseTitle}}</span></h3>
                    {{/if}}
                    <p class="cont-block-2">
                        {{if $value.buildArea && $value.buildArea > 0}}
                        {{$value.buildArea}}㎡
                        {{/if}}
                        {{if $value.room && $value.hall}}
                        / {{$value.room}}室{{$value.hall}}厅
                        {{/if}}
                        {{if $value.forwardName}}
                        / {{$value.forwardName}}
                        {{/if}}
                        {{if $value.plotName}}
                        / {{$value.plotName}}
                        {{/if}}
                    </p>
                    <p class="cont-block-3 distance">
                        <i class="icon"></i>
                        {{if $value.subwayDesc}}
                        {{$value.subwayDesc}}
                        {{else}}
                        {{if $value.area && $value.houseBusinessName}}
                        {{$value.area}}-{{$value.houseBusinessName}}
                        {{/if}}
                        {{/if}}
                    </p>
                    {{if $value.claimHouseId==''}}
                        <div class="cont-block-4 house-labelling gray middle esf">
                            {{if $value.tagsName}}
                            {{each $value.tagsName value index}}
                            <span>{{value}}</span>
                            {{/each}}
                            {{/if}}
                        </div>
                    {{else}}
                    <div class="cont-block-4 house-labelling gray middle esf">
                        {{if $value.claimTagsName}}
                        {{each $value.claimTagsName value index}}
                        <span>{{value}}</span>
                        {{/each}}
                        {{/if}}
                    </div>
                    {{/if}}
                    <div class="cont-block-price">
                        {{if $value.houseTotalPrices && $value.houseTotalPrices > 0}}
                        <em>{{$value.houseTotalPrices}}万</em>
                        {{/if}}
                        {{if $value.houseTotalPrices && $value.buildArea}}
                        {{if $value.houseTotalPrices > 0 && $value.buildArea > 0}}
                        <span>{{$value.unitCost}}元/㎡</span>
                        {{/if}}
                        {{/if}}
                    </div>
                </div>
            </div>
        </a>
    </li>
    {{/if}}
    {{/each}}
</script>
<script>
    //二手房拦截默认的下拉加载
    window["$toutiao_customer_pullUpAction"]=true;
    $(function () {
        var urlparam =GetRequest();
        if (urlparam["lat"] && urlparam["lon"]) {
            window["$toutiao_customer_pullUpAction_latlon"] = [urlparam["lat"], urlparam["lon"]]
            pullUpAction();
        } else {
            var hasTimeOut = false;
            var timeout = setTimeout(function () {

                if (hasTimeOut) {
                    return
                }
                hasTimeOut = true;
                pullUpAction();
            }, 2000);

            var geolocation = new BMap.Geolocation();
            geolocation.getCurrentPosition(function (r) {

                clearTimeout(timeout);
                if (hasTimeOut) {
                    return
                }
                hasTimeOut = true;
                lon = r.point.lng;
                lat = r.point.lat;

                if (lon == 116.40387397 && lat == 39.91488908) {
                    pullUpAction();
                } else {
                    window["$toutiao_customer_pullUpAction_latlon"] = [lat, lon]
//                    location.href = router_city('/esf') + "?lat=" + lat + "&lon=" + lon;
                    pullUpAction();
                }
            });
        }

        var url = document.referrer;
        zhuge.track('进入二手房列表页',{'导航名称':'二手房','页面来源URL':url});
        if(url.indexOf("/xiaoqu")>0){
            zhuge.track('小区-进入二手房列表页',{'导航名称':'二手房','页面来源URL':url})
        }
        if(url.indexOf("/esf") > 0){
            if(GetQueryString("keyword")!='undefined'){
                zhuge.track("搜索_二手房",{
                    "关键词":GetQueryString("keyword"),
                    "返回结果数量":$("#result-section").find("li").find('a').attr("id")
                })
            }
        }else{
            if(GetQueryString("keyword")!='undefined'){
                zhuge.track("搜索_大首页",{
                    "搜索类型":"二手房",
                    "关键词":GetQueryString("keyword"),
                    "返回结果数量":$("#result-section").find("li").find('a').attr("id")
                })
            }
        }
    });
    function esf_title(a) {
        var link = $(a);
//        zhuge.track('点击二手房头条logo', {
//            "页面来源URL": window.location.href
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false
    }

    /**
     *
     * 318㎡/4室2厅/ 南北/ 美伦堡
     */
    $("#result-section").on('click','li',function () {
        var link = $(this);
        zhuge.track('二手房-点击列表二手房', {
            "楼盘名称":link.find('img').attr('alt'),
            "总价":link.find('div.cont-block-price').find('em').text(),
            "单价":link.find('div.cont-block-price').find('span').text(),
            "面积":link.find('p.cont-block-2').text().split("/")[0],
            "户型":link.find('p.cont-block-2').text().split("/")[1],
            "朝向":link.find('p.cont-block-2').text().split("/")[2],
            "标签":link.find('div.cont-block-4.house-labelling.gray.middle.esf').text(),
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
<script src="${staticurl}/js/list-category-0207.js?v=${staticversion}"></script>
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
//        zhuge.track('二手房-排序',{'排序方式':sortZhuge},function () {
//        });
    });
    function esf_list(e) {
        setPageNum($(e).attr('data-id'))
        window.location.href = $(e).attr('url')
    }
</script>
</html>