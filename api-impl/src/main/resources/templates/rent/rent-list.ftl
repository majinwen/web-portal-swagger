<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/rent-list.css?v=${staticversion}">
    <title>懂房帝看租房</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<#include "../StatisticsHeader.ftl">
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="懂房帝">
<header class="main-top-header">
    <input id="url" type="hidden" value="${router_city('/zufang')}">
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
        <li data-mark="tab-rent-price"><span><em>租金</em><i></i></span></li>
        <li data-mark="tab-rent-type"><span><em>户型</em><i></i></span></li>
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
            <div class="filter-item" data-mark="panel-rent-price">
                <div class="price-list">
                    <ul>
                        <li data-begin-price="" data-end-price="" class="current">不限</li>
                        <li data-begin-price="0.0" data-end-price="2000.0">2000以下</li>
                        <li data-begin-price="2000.0" data-end-price="3000.0">2000-3000元</li>
                        <li data-begin-price="3000.0" data-end-price="4000.0">3000-4000元</li>
                        <li data-begin-price="4000.0" data-end-price="5000.0">4000-5000元</li>
                        <li data-begin-price="5000.0" data-end-price="8000.0">5000-8000元</li>
                        <li data-begin-price="8000.0" data-end-price="">8000元以上</li>
                    </ul>
                </div>
            </div>
            <!-- 租房类型 -->
            <div class="filter-item" data-mark="panel-rent-type">
                <div class="more-list" id="rentType">
                    <dl class="rent-sign">
                        <dt class="text-center" data-type="elo">整租</dt>
                        <dd>
                            <span class="rent-only ert" data-info="0">全部</span>
                            <span data-info="1">一居</span>
                            <span data-info="2">二居</span>
                            <span data-info="3">三居+</span>
                        </dd>
                    </dl>
                    <dl class="rent-sign">
                        <dt class="text-center" data-type="jlo">合租</dt>
                        <dd>
                            <span class="rent-only jrt" data-info="0">全部</span>
                            <span data-info="1">一居</span>
                            <span data-info="2">二居</span>
                            <span data-info="3">三居+</span>
                        </dd>
                    </dl>
                </div>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button more-reset" id="moreRentReset">重置</a>
                    <a href="javascript:;" class="operation-button more-submit" id="moreRentSubmit">确定</a>
                </div>
            </div>
            <!-- 更多 -->
            <div class="filter-item" data-mark="panel-more">
                <div class="more-list">
                    <#--<dl>-->
                        <#--<dt data-type="source">来源</dt>-->
                        <#--<dd>-->
                            <#--<span data-info="0">经纪人</span>-->
                            <#--<span data-info="1">相寓</span>-->
                        <#--</dd>-->
                    <#--</dl>-->
                    <dl>
                        <dt data-type="houseAreaSize">面积</dt>
                        <dd>
                            <span data-info="[0-20]">20㎡以下</span>
                            <span data-info="[20-40]">20-40㎡</span>
                            <span data-info="[40-60]">40-60㎡</span>
                            <span data-info="[60-80]">60-80㎡</span>
                            <span data-info="[80-100]">80-100㎡</span>
                            <span data-info="[100-10000]">100㎡以上</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="forward">朝向</dt>
                        <dd>
                            <span data-info="1">东</span>
                            <span data-info="2">西</span>
                            <span data-info="3">南</span>
                            <span data-info="4">北</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="ht">供暖</dt>
                        <dd>
                            <span data-info="1">集体供暖</span>
                            <span data-info="2">自供暖</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="tags">特色</dt>
                        <dd>
                            <span data-info="1">近地铁</span>
                            <#--<span data-info="2">免佣</span>-->
                            <span data-info="6">集中供暖</span>
                            <span data-info="4">有电梯</span>
                        </dd>
                    </dl>
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
    <a href="https://at.umeng.com/onelink/K9nqai" class="app-download-top-tips-wrapper">
        <img src="${staticurl}/images/download/rent-list-download.jpg" width="100%" alt="下载懂房帝APP">
    </a>
    <ul id="valueList"></ul>
</section>
<div class="download-app-bottom-tips">
    <div class="detail">
        <img src="http://wap-qn.toutiaofangchan.com/ic_launcher.png" alt="懂房帝">
        <p>
            <strong>下载懂房帝APP</strong>
            <span>查看更多减价房源</span>
        </p>
    </div>
    <div class="btn">打开APP</div>
    <div class="download-app-bottom-tips-close">
        <img src="${staticurl}/images/download/download-app-bottom-tips-close.png" alt="">
    </div>
</div>
<#include "../user.ftl">
<#include "../search.ftl">
<script id="listContent" type="text/html">
    {{each data}}
    {{if $index == 3 && $value.pageNum == 1 || $index == 5 && $value.pageNum == 2}}
    <li><a class="list-item app-download-item" href="https://at.umeng.com/onelink/K9nqai">
        <div class="list-item-cont-ad">
            <h3 class="cont-block-1">北京个人出租房源每天上新，下载懂房帝APP随时查</h3>
        </div>
        <div class="clear">
            <div class="list-item-img-box">
                <img src="${staticurl}/images/zufang-app-download-1.jpg" alt="出租房源每天上新">
            </div>
            <div class="list-item-img-box">
                <img src="${staticurl}/images/zufang-app-download-2.jpg" alt="出租房源每天上新">
            </div>
            <div class="list-item-img-box">
                <img src="${staticurl}/images/zufang-app-download-3.jpg" alt="出租房源每天上新">
            </div>
        </div>
        <div class="pr">
            <div class="house-labelling gray">
                <span>懂房帝APP</span>
            </div>
        </div>
    </a></li>
    {{else}}
    <li>
        <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId={{$value.house_id}}&__topic__=zufangbaoguang'/>-->
        <a class="list-item" data-id="{{$value.pageNum}}" onclick="rent_list(this)" url="<%= $imports.router_city('/zufang/'+$value.house_id+'.html') %>" href="javascript:void(0);">
        <div class="clear">
            <div class="list-item-img-box">
                {{if $value.house_title_img && $value.house_title_img.length > 0}}
                <img src="{{$value.house_title_img}}" alt="{{$value.village_name}}" onerror="this.src='${staticurl}/images/global/tpzw_image.png'">
                {{else}}
                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                {{/if}}
            </div>
            <div class="list-item-cont">
                <h3 class="cont-block-top"><span>{{$value.zufang_name}}·{{$value.house_area}}㎡ {{$value.room}}室{{$value.hall}}厅 {{$value.forward}}</span></h3>
                <div class="address distance"><i class="icon"></i>{{if $value.subwayDesc}}{{$value.subwayDesc}}{{else if $value.area_name}}{{if $value.district_name}}{{$value.district_name}}{{else}}暂无数据{{/if}}-{{if $value.area_name}}{{$value.area_name}}{{else}}暂无数据{{/if}}{{/if}}</div>
                {{if $value.rent_type_name}}
                <div class="house-labelling big normal">
                    <span class="company">{{$value.rent_type_name}}</span>
                    {{if $value.rent_house_tags_name && $value.rent_house_tags_name.length > 0}}
                    {{each $value.rent_house_tags_name value i}}
                    {{if i < 3}}
                    <span>{{value}}</span>
                    {{/if}}
                    {{/each}}
                    {{/if}}
                </div>
                {{/if}}
                <div class="cont-block-bottom">
                    <p class="high-light-red">¥{{$value.rent_house_price}}<em> 元/月</em></p>
                </div>
            </div>
        </div>
    </a></li>
    {{/if}}
    {{/each}}
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
    window["$toutiao_customer_pullUpAction"]=true;

    $(function () {
        $('.download-app-bottom-tips-close').on('click', function () {
            $('.download-app-bottom-tips').hide()
        });
        $('.download-app-bottom-tips .btn').on('click', function () {
            location.href = "https://at.umeng.com/onelink/K9nqai"
        });

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
//                    window["$toutiao_customer_pullUpAction_latlon"] = [39.91931152343750000000, 116.49440002441400000000]
                    pullUpAction();
                } else {
                    window["$toutiao_customer_pullUpAction_latlon"] = [lat, lon]
                    pullUpAction();
                }

//                window["$toutiao_customer_pullUpAction_latlon"] = [lat, lon]
//                pullUpAction();
            });
        }
    });

    var rentPageNums = [];
    $(function () {
        var pageNum = 1;
        if(window.location.href.split('#').length==2){
            pageNum = window.location.href.split('#')[1].split('=')[1];
        }
        rentPageNums.push(parseInt(pageNum));
    });

    function rent_list(e) {
        var pageNum = Math.min.apply(Math,rentPageNums);
        sessionStorage.clear();
        sessionStorage.setItem('rentUrl',window.location.href.split('#')[0]);
        sessionStorage.setItem('rentSortId',parseInt($(e).parent().index())+parseInt(pageNum)*10-10);
        setPageNum($(e).attr('data-id'));
        window.location.href = $(e).attr('url')
    }
</script>
</html>