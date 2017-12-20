<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>小区列表</title>
</head>
<body>
<header>
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" placeholder="中骏·西山天璟">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<section class="category-box">
    <ul id="category-nav">
        <li><span><em>位置</em><i></i></span></li>
        <li><span><em>均价</em><i></i></span></li>
        <li><span><em>楼龄</em><i></i></span></li>
        <li><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none">
        <div class="category-cont">
            <div class="none">
                <ul class="category-parent">
                    <li>区域</li>
                    <li>地铁</li>
                </ul>
                <ul class="category-child"></ul>
                <ul class="category-children"></ul>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>不限</li>
                    <li>200万以下</li>
                    <li>200-250万</li>
                    <li>200-250万</li>
                    <li>250-300万</li>
                    <li>350-400万</li>
                </ul>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>不限</li>
                    <li>5年内</li>
                    <li>10年内</li>
                    <li>15年内</li>
                    <li>20年内</li>
                    <li>20年以上</li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-year">重置</button>
                    <button type="button" class="confrim confrim-year">确定</button>
                </div>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>
                        <h4>朝向</h4>
                        <div class="more-options">
                            <span>朝东</span>
                            <span>朝西</span>
                            <span>朝南</span>
                            <span>朝北</span>
                            <span>南北通透</span>
                        </div>
                    </li>
                    <li>
                        <h4>面积</h4>
                        <div class="more-options">
                            <span>60以下</span>
                            <span>60-90</span>
                            <span>90-120</span>
                            <span>120以上</span>
                        </div>
                    </li>
                    <li>
                        <h4>标签</h4>
                        <div class="more-options">
                            <span>满两年</span>
                            <span>满五年</span>
                            <span>近地铁</span>
                        </div>
                    </li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-more">重置</button>
                    <button type="button" class="confrim confrim-more">确定</button>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <ul>
    <#if villageList?exists>
        <#list villageList as plot>
            <li><a class="list-item" href="">
                <div class="clear">
                    <#if plot['photo']?exists>
                        <div class="list-item-img-box">
                            <#assign photo = plot['photo']>
                            <#if photo?exists>
                                <img src="${staticurl}/images/esf/${photo[0]}" alt="${plot['rc']}">
                            </#if>
                        </div>
                    </#if>
                    <div class="list-item-cont">
                        <#if plot['rc']?exists>
                        <h3 class="cont-block-1">${plot['rc']}</h3>
                        </#if>
                        <#if plot['abbreviatedAge']?exists>
                        <p class="cont-block-2">${plot['abbreviatedAge']}</p>
                        </#if>
                        <#if plot['metroWithPlotsDistance']?exists>
                            <#assign map = plot['metroWithPlotsDistance']>
                            <#if plot['key']?exists>
                                <#if map[plot['key']]?exists>
                                    <p class="cont-block-3 distance"><i class="icon"></i>${map[plot['key']]}</p>
                                <#else>
                                    <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                                </#if>
                            <#else>
                            <#if plot['tradingArea']?exists>
                                <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                            </#if>
                            </#if>
                        <#else>
                        <#if plot['tradingArea']?exists>
                            <p class="cont-block-3 distance"><i class="icon"></i>${plot['tradingArea']}</p>
                        </#if>
                        </#if>
                        <div class="cont-block-4">
                            <#assign item =  plot['label']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
                        </div>
                        <div class="cont-block-price plot">
                            <em>${plot['avgPrice']}</em>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </#if>
    </ul>
    <p class="tip-box">有新上房源，我们会及时通知您哦！</p>
</section>
<#-- 个人中心 侧栏菜单 -->
<section class="side-nav-cont">
    <div class="side-user">
        <img src="${staticurl}/images/global/grcl_tx.png" alt="用户头像">
        <p>188********</p>
    </div>
    <div class="side-nav-item-wrapper">
        <ul class="side-nav-item item-link">
            <li><a href="#"><i class="icon-index"></i><span>首页</span></a></li>
            <li><a href="#"><i class="icon-esf"></i><span>找二手房</span></a></li>
            <li><a href="#"><i class="icon-plot"></i><span>找小区</span></a></li>
            <li><a href="#"><i class="icon-new"></i><span>找新房</span></a></li>
        </ul>
        <ul class="side-nav-item item-my">
            <li><a href="#"><i class="icon-collect"></i><span>我的收藏</span></a></li>
            <li><a href="#"><i class="icon-report"></i><span>我的报告</span></a></li>
        </ul>
    </div>
    <div class="side-house-intelligent">
        <a href="#"><em>智能找房</em></a>
    </div>
</section>
<div class="scroll-mask"></div>

<script src="${staticurl}/js/zepto.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>