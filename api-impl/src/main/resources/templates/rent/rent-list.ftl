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
    <input id="url" type="hidden" value="${router_city('/xiaoqu')}">
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
        <li data-mark="tab-price"><span><em>租金</em><i></i></span></li>
        <li data-mark="tab-age"><span><em>整租/合租</em><i></i></span></li>
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
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="buildingFeature">楼盘特色</dt>
                        <dd>
                            <span data-info="1">别墅</span>
                            <span data-info="2">花园洋房</span>
                            <span data-info="3">近地铁</span>
                            <span data-info="4">车位充足</span>
                            <span data-info="9">500强房企</span>
                            <span data-info="6">高绿化</span>
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
    <ul id="valueList">
        <li><a class="list-item" href="javascript:void(0);">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-top"><span>万年花城二期·136m2三室·西</span></h3>
                    <div class="address distance"><i class="icon"></i>朝阳 大望路</div>
                    <div class="house-labelling big normal">
                        <span>整租</span>
                        <span>整租</span>
                    </div>
                    <div class="cont-block-bottom">
                        <p class="high-light-red">¥10800<em> 元/月</em></p>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="javascript:void(0);">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-top"><span>万年花城二期·136m2三室·西</span></h3>
                    <div class="address distance"><i class="icon"></i>朝阳 大望路</div>
                    <div class="house-labelling big normal">
                        <span>整租</span>
                        <span>整租</span>
                    </div>
                    <div class="cont-block-bottom">
                        <p class="high-light-red">¥10800<em> 元/月</em></p>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="javascript:void(0);">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-top"><span>万年花城二期·136m2三室·西</span></h3>
                    <div class="address distance"><i class="icon"></i>朝阳 大望路</div>
                    <div class="house-labelling big normal">
                        <span>整租</span>
                        <span>整租</span>
                    </div>
                    <div class="cont-block-bottom">
                        <p class="high-light-red">¥10800<em> 元/月</em></p>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="javascript:void(0);">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-top"><span>万年花城二期·136m2三室·西</span></h3>
                    <div class="address distance"><i class="icon"></i>朝阳 大望路</div>
                    <div class="house-labelling big normal">
                        <span>整租</span>
                        <span>整租</span>
                    </div>
                    <div class="cont-block-bottom">
                        <p class="high-light-red">¥10800<em> 元/月</em></p>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="javascript:void(0);">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-top"><span>万年花城二期·136m2三室·西</span></h3>
                    <div class="address distance"><i class="icon"></i>朝阳 大望路</div>
                    <div class="house-labelling big normal">
                        <span>整租</span>
                        <span>整租</span>
                    </div>
                    <div class="cont-block-bottom">
                        <p class="high-light-red">¥10800<em> 元/月</em></p>
                    </div>
                </div>
            </div>
        </a></li>
    </ul>
</section>
<#include "../user.ftl">
<#include "../search.ftl">
<div class="sort-icon"></div>
<div class="sort-content-box">
    <div class="sort-mask"></div>
    <ul class="sort-content">
        <li value="0" class="current"><p>默认排序</p></li>
        <li value="1"><p>价格由高到低</p></li>
        <li value="2"><p>价格由低到高</p></li>
    </ul>
</div>

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
    });
    function plot_list(e) {
        setPageNum($(e).attr('data-id'));
        window.location.href = $(e).attr('url')
    }
</script>
</html>