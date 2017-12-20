<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/index.css">
    <title>大首页</title>
</head>
<body>
<header class="gradient">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input  type="text"onclick="searchNewHouse()" placeholder="中骏·西山天璟">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<div class="module-bottom-fill">
    <section class="banner-index-box">
        <div class="swiper-container carousel-swiper" id="index-swiper">
            <ul class="swiper-wrapper" id="house-pic-container">
                <li class="swiper-slide">
                    <img src="${staticurl}/images/index/dsy_banner.jpg" alt="年末特惠">
                </li>
            </ul>
            <div class="swiper-pagination pictrue-index"></div>
        </div>
        <div class="banner-nav">
            <div class="banner-nav-item index-nav-item"><a href="/findProjHouseInfo">
                <i class="index-esf-icon"></i><p>二手房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a href="/fingNearVillageAndDistance">
                <i class="index-plot-icon"></i><p>小区</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a href="/newhouse/searchNewHouse">
                <i class="index-new-icon"></i><p>新房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a href="#">
                <i class="index-intelligent-icon"></i><p>智能找房</p>
            </a></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img index-img"><img src="${staticurl}/images/index/index_shopping_guide.png" alt="购物指南"></div>
        <div class="text-scroll index-text">
            <ul>
                <li><em class="scroll-text-tag">市场</em>北京二手房现全面“砍价”全年成交13.4万</li>
                <li><em class="scroll-text-tag">市场</em>猛犸温泉高档小区开盘在即，各大商铺积极响猛犸温泉高档小区开盘在即，各大商铺积极响</li>
                <li><em class="scroll-text-tag">市场</em>猛犸温泉高档小区开盘在即，各大商铺猛犸温泉高档小区开盘在即，各大商铺积极响铺积极响铺积极响</li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="index-module-header border-bot-none">
            <h3>精选主题</h3>
        </div>
        <div class="hot-topic pt0">
            <div class="column">
                <div class="hot-topic-item index-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>热门优选房集合</h5>
                        <p>热门区域推荐</p>
                    </div>
                    <img class="item-1" src="${staticurl}/images/index/dsy_jxzt_image1@3x.png" alt="热门优选房集合">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>新上房源专区</h5>
                        <p>每日集结</p>
                    </div>
                    <img class="item-2" src="${staticurl}/images/index/dsy_jxzt_image2@3x.png" alt="新上房源专区">
                </a></div>
            </div>
            <div class="column">
                <div class="hot-topic-item index-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>改善三居室</h5>
                        <p>全家幸福住</p>
                    </div>
                    <img class="item-3" src="${staticurl}/images/index/dsy_jxzt_image3@3x.png" alt="改善三居室">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>高端大气豪宅</h5>
                        <p>低密度舒适宜居</p>
                    </div>
                    <img class="item-4" src="${staticurl}/images/index/dsy_jxzt_image4@3x.png" alt="高端大气豪宅">
                </a></div>
            </div>
        </div>
    </section>
</div>
<section>
    <div class="index-module-header">
        <h3>新房推荐</h3>
    </div>
    <ul><#if newbuilds?exists>
    <#assign builds = newbuilds['data']>
    <#list builds as map>
     <#if map_index==1><li><a class="list-item new new-ad-item" href="#">
         <div class="list-item-cont-ad">
             <h3 class="cont-block-1">中骏·西山天璟<em>别墅</em></h3>
             <p class="cont-block-3">东城/88㎡—526㎡</p>
         </div>
         <div class="clear">
             <div class="list-item-img-box">
                 <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
             </div>
             <div class="list-item-img-box">
                 <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
             </div>
             <div class="list-item-img-box">
                 <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
             </div>
         </div>
         <div class="pr">
             <div class="cont-block-4 house-labelling gray middle">
                 <span>复式</span>
                 <span>五证齐全</span>
                 <span>花园洋房</span>
             </div>
             <p class="cont-block-2 high-light-red">68000元/㎡</p>
         </div>

         <div class="new-active">
             <i class="icon"></i><em>活动：</em>
             <span>梦马温泉项目位于门头沟双屿岛...梦马温泉项目位于门...</span>
         </div>
     </a></li></#if>
        <li><a class="list-item new" href="/newhouse/getNewHouseDetails?id=${map['building_name_id']}">
            <div class="clear">
                <div class="list-item-img-box">
                    <#assign item = map['building_imgs']>
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="${map['building_name']}">
                </div>
                <div class="list-item-cont">
                    <span hidden="hidden">${map['building_name_id']}</span>
                    <h3 class="cont-block-1">${map['building_name']} <em>${map['property_type']}</em></h3>
                    <p class="cont-block-2 high-light-red">${map['average_price']}/㎡</p>
                    <p class="cont-block-3">
                        <#if map['nearsubway']??>
                        ${map['nearsubway']}
                        <#else>${map['district_name']}
                        </#if>
                        /${map['house_min_area']}㎡—${map['house_max_area']}㎡</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <#assign item =  map['building_tags']>
                        <#list item as itemValue>
                            <span>${itemValue}</span>
                        </#list>
                    </div>
                    <div class="cont-block-sale">
                        <em>${map['sale_status_name']}</em>
                    </div>
                </div>
            </div>
            <div class="new-active">
                <i class="icon"></i><em>活动：</em>
                <span>${map['activity_desc']}</span>
            </div>
        </a></li>
    </#list>
    </#if>




    </ul>
</section>
<section>
    <div class="index-module-header">
        <h3>小区推荐</h3>
    </div>
    <ul>
<#if villageList?exists>
    <#list villageList as map>
        <#if map_index==3>
            <li><a class="list-item new new-ad-item" href="#">
                <div class="list-item-cont-ad">
                    <h3 class="cont-block-1">新龙城</h3>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <p class="cont-block-2">2008年建成</p>
                </div>
                <div class="clear">
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                    </div>
                </div>
                <div class="pr">
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>五证齐全</span>
                        <span>花园洋房</span>
                    </div>
                    <p class="cont-block-2 high-light-red">68000元/㎡</p>
                </div>
            </a></li>
        </#if>
        <li><a class="list-item" href="">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                </div>
                <div class="list-item-cont">
                    <input type="hidden" value="${map['id']}">
                    <h3 class="cont-block-1">${map['rc']}</h3>
                    <p class="cont-block-2">2008年建成</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                       <#list map['label'] as label>
                           <span>${label}</span>
                       </#list>
                    </div>
                    <div class="cont-block-price plot">
                        <em>${map['avgPrice']}元/㎡</em>
                    </div>
                </div>
            </div>
        </a></li>
    </#list>
    </#if>
       <#-- <li><a class="list-item" href="">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟</h3>
                    <p class="cont-block-2">2008年建成</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>近地铁</span>
                        <span>优质物业</span>
                        <span>车位充足</span>
                    </div>
                    <div class="cont-block-price plot">
                        <em>498000元/㎡</em>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟</h3>
                    <p class="cont-block-2">2008年建成</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>近地铁</span>
                        <span>优质物业</span>
                        <span>车位充足</span>
                    </div>
                    <div class="cont-block-price plot">
                        <em>498000元/㎡</em>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esf_list_image1@3x.png" alt="中骏·西山天璟">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟</h3>
                    <p class="cont-block-2">2008年建成</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>近地铁</span>
                        <span>优质物业</span>
                        <span>车位充足</span>
                    </div>
                    <div class="cont-block-price plot">
                        <em>498000元/㎡</em>
                    </div>
                </div>
            </div>
        </a></li>
    </ul>
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
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
</body>
</html>
<script type="text/javascript" >
            function searchNewHouse() {
                alert("bb")
            }
</script>