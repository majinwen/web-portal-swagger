<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>新房列表</title>
</head>
<body>
<header>
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" placeholder="中骏·西山天璟">
    </div>
    <a href="/" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<section class="category-box">
    <ul id="category-nav">
        <li><span><em>区域</em><i></i></span></li>
        <li><span><em>总价</em><i></i></span></li>
        <li><span><em>户型</em><i></i></span></li>
        <li><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none">
        <div class="category-cont">
            <div class="none">
                <ul class="category-parent">
                    <li>区域</li>
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
                    <li>一居</li>
                    <li>二居</li>
                    <li>三居</li>
                    <li>四居</li>
                    <li>五居及五居以上</li>
                </ul>
                <div class="button-group">
                    <button type="button" class="reset reset-huxing">重置</button>
                    <button type="button" class="confrim confrim-huxing">确定</button>
                </div>
            </div>
            <div class="none">
                <ul class="category-parent">
                    <li>
                        <h4>物业类型</h4>
                        <div class="more-options">
                            <span>住宅</span>
                            <span>别墅</span>
                            <span>写字楼</span>
                            <span>商铺</span>
                            <span>底商</span>
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
                        <h4>电梯</h4>
                        <div class="more-options">
                            <span>有电梯</span>
                            <span>无电梯</span>
                        </div>
                    </li>
                    <li>
                        <h4>建筑类型</h4>
                            <div class="more-options">
                                <span>板楼</span>
                                <span>塔楼</span>
                                <span>板塔结合</span>
                                <span砖楼</span>
                            </div>
                    </li>
                    <li>
                        <h4>销售状态</h4>
                            <div class="more-options">
                                <span>售完</span>
                                <span>在售</span>
                                <span>不在售</span>
                                <span>出租</span>
                                <span>租售</span>
                                <span>待售</span>
                            </div>
                    </li>
                    <li>
                        <h4>楼盘特色</h4>
                            <div class="more-options">
                                <span>别墅</span>
                                <span>花园洋房</span>
                                <span>近地铁</span>
                                <span>车位充足</span>
                                <span>低密度</span>
                                <span>高绿化</span>
                            </div>
                    </li>
                    <li>
                        <h4>装修</h4>
                            <div class="more-options">
                                <span>毛坯</span>
                                <span>普通装修</span>
                                <span>精装修</span>
                                <span>豪华装修</span>
                                <span>其他</span>
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
    <#if builds?exists>
        <#list builds as map>
            <li><a class="list-item new" href="/newhouse/getNewHouseDetails?id=${map['building_name_id']}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#assign item = map['building_imgs']>
                        <img src="${staticurl}/images/esf/${item[0]}" alt="${map['building_name']}">
                    </div>
                    <div class="list-item-cont">
                        <span hidden="hidden" >${map['building_name_id']}</span>
                        <h3 class="cont-block-1">${map['building_name']}<em>${map['property_type']}</em></h3>
                        <p class="cont-block-2">${map['average_price']}元/㎡</p>
                        <p class="cont-block-3">${map['district_name']}/${map['house_min_area']}㎡—${map['house_max_area']}㎡</p>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#assign item = map['building_tags']>
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

<script src="${staticurl}/js/zepto.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>
</body>
</html>