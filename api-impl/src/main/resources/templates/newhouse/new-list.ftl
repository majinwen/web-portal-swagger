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
        <li><span><em>位置</em><i></i></span></li>
        <li><span><em>总价</em><i></i></span></li>
        <li><span><em>户型</em><i></i></span></li>
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
    <#if builds?exists>
        <#list builds as map>
            <li><a class="list-item new" href="">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#assign item = map['building_imgs']>
                        <img src="${staticurl}/images/esf/${item[0]}" alt="${map['building_name']}">
                    </div>
                    <div class="list-item-cont">
                        <span hidden="hidden" >${map['building_name_id']}</span>
                        <h3 class="cont-block-1">${map['building_name']} <em>${map['property_type']}</em></h3>
                        <p class="cont-block-2">${map['average_price']}元/㎡</p>
                        <p class="cont-block-3">${map['district_name']}/100㎡—588㎡</p>
                        <div class="cont-block-4">
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

<script src="${staticurl}/js/zepto.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>
</body>
</html>