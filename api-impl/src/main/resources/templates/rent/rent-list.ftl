<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/list.css?v=${staticversion}">
    <title>头条房产看租房</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<#include "../StatisticsHeader.ftl">
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="头条·房产">
<header class="main-top-header">
    <input id="url" type="hidden" value="${router_city('/zufang')}">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
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
        <li data-mark="tab-rent-type"><span><em>整租/合租</em><i></i></span></li>
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
            <div class="filter-item" data-mark="tab-rent-price">
                <div class="price-list">
                    <ul>
                        <li data-begin-price="" data-end-price="" class="current">不限</li>
                        <li data-begin-price="0.0" data-end-price="2000.0">2000以下</li>
                        <li data-begin-price="2000.0" data-end-price="3000.0">2000-3000元</li>
                        <li data-begin-price="3000.0" data-end-price="4000.0">3000-4000元</li>
                        <li data-begin-price="4000.0" data-end-price="5000.0">4000-5000元</li>
                        <li data-begin-price="5000.0" data-end-price="8000.0">5000-8000元</li>
                        <li data-begin-price="8000.0" data-end-price="2000000000.0">8000元以上</li>
                    </ul>
                </div>
            </div>
            <!-- 楼龄 -->
            <div class="filter-item" data-mark="tab-rent-type">
                <div class="more-list">
                    <dl>
                        <dt class="text-center" data-type="propertyTypeId">整租</dt>
                        <dd>
                            <span data-info="1">不限</span>
                            <span data-info="1">一居</span>
                            <span data-info="2">二居</span>
                            <span data-info="2">三居</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="text-center" data-type="houseAreaSize">合租</dt>
                        <dd>
                            <span data-info="1">不限</span>
                            <span data-info="1">一居</span>
                            <span data-info="2">二居</span>
                            <span data-info="2">三居+</span>
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
                    <dl>
                        <dt data-type="propertyTypeId">来源</dt>
                        <dd>
                            <span data-info="1">经纪人</span>
                            <span data-info="2">乐乎公寓</span>
                            <span data-info="2">美丽遇</span>
                            <span data-info="2">青遇home</span>
                            <span data-info="2">大象公寓</span>
                        </dd>
                    </dl>
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
                        <dt data-type="elevatorFlag">朝向</dt>
                        <dd>
                            <span data-info="1">东</span>
                            <span data-info="0">西</span>
                            <span data-info="0">南</span>
                            <span data-info="0">北</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="buildingType">供暖</dt>
                        <dd>
                            <span data-info="1">集体供暖</span>
                            <span data-info="2">自供暖</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="buildingFeature">特色</dt>
                        <dd>
                            <span data-info="1">近地铁</span>
                            <span data-info="2">免佣</span>
                            <span data-info="3">供暖方式</span>
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
    <ul id="valueList"></ul>
</section>
<#include "../user.ftl">
<#include "../search.ftl">
<script id="listContent" type="text/html">
    {{each data}}
    <li><a class="list-item" data-id="{{$value.pageNum}}" onclick="rent_list(this)" url="<%= $imports.router_city('/zufang/'+$value.house_id+'.html') %>" href="javascript:void(0);">
        <div class="clear">
            <div class="list-item-img-box">
                {{if $value.house_title_img && $value.house_title_img.length > 0}}
                <img src="${staticurl}/images/global/tpzw_image.png" alt="{{$value.village_name}}">
                <#--<img src="${qiniuimage}/{{$value.house_title_img}}-tt400x300" alt="{{$value.village_name}}">-->
                {{else}}
                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                {{/if}}
            </div>
            <div class="list-item-cont">
                <h3 class="cont-block-top"><span>{{$value.village_name}}·{{$value.house_area}}㎡ {{$value.forward}} {{$value.room}}室{{$value.hall}}厅</span></h3>
                <div class="address distance"><i class="icon"></i>{{if $value.district_name}}{{$value.district_name}}{{else}}暂无数据{{/if}} {{if $value.area_name}}{{$value.area_name}}{{else}}暂无数据{{/if}}</div>
                {{if $value.rent_house_tags_name && $value.rent_house_tags_name.length > 0}}
                <div class="house-labelling big normal">
                    {{each $value.rent_house_tags_name value i}}
                    {{if i < 3}}
                    <span>{{value}}</span>
                    {{/if}}
                    {{/each}}
                </div>
                {{/if}}
                <div class="cont-block-bottom">
                    <p class="high-light-red">¥{{$value.rent_house_price}}<em> 元/月</em></p>
                </div>
            </div>
        </div>
    </a></li>
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
    /*$('.sort-content-box').on('click', function (){
        var sortZhuge;
        if(GetQueryString('sort')==1){
            sortZhuge = '价格由高到低';
        }
        if(GetQueryString('sort')==2){
            sortZhuge = '价格由低到高';
        }
    });*/
    function rent_list(e) {
        setPageNum($(e).attr('data-id'));
        window.location.href = $(e).attr('url')
    }
</script>
</html>