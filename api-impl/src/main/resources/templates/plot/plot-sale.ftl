<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>小区待售</title>
</head>
<body>
<section class="category-box">
    <ul id="category-tab">
        <li data-mark="tab-price"><span><em>总价</em><i></i></span></li>
        <li data-mark="tab-type"><span><em>户型</em><i></i></span></li>
        <li data-mark="tab-more"><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none"></div>
    <div class="category-cont">
        <!-- 价格 -->
        <div class="filter-item" data-mark="panel-price">
            <div class="price-list">
                <ul>
                    <li class="current">不限</li>
                    <li data-begin-price="0.0" data-end-price="200.0">200万以下</li>
                    <li data-begin-price="200.0" data-end-price="250.0">200-250万</li>
                    <li data-begin-price="250.0" data-end-price="300.0">250-300万</li>
                    <li data-begin-price="300.0" data-end-price="350.0">300-350万</li>
                    <li data-begin-price="350.0" data-end-price="400.0">350-400万</li>
                    <li data-begin-price="400.0" data-end-price="1000.0">400万以上</li>
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
                        <span data-info="1">住宅</span>
                        <span data-info="2">别墅</span>
                        <span data-info="3">写字楼</span>
                        <span data-info="4">商铺</span>
                        <span data-info="5">底商</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseAreaSize">面积</dt>
                    <dd>
                        <span data-info="0,60">60以下</span>
                        <span data-info="60,90">60-90</span>
                        <span data-info="90,120">90-120</span>
                        <span data-info="120,1000">120以上</span>
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
                        <span data-info="4">砖楼</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="saleType">销售状态</dt>
                    <dd>
                        <span data-info="1">售完</span>
                        <span data-info="2">在售</span>
                        <span data-info="3">不在售</span>
                        <span data-info="4">出租</span>
                        <span data-info="5">待租</span>
                        <span data-info="6">待售</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingFeature">楼盘特色</dt>
                    <dd>
                        <span data-info="1">别墅</span>
                        <span data-info="2">花园洋房</span>
                        <span data-info="3">近地铁</span>
                        <span data-info="4">车位充足</span>
                        <span data-info="5">低密度</span>
                        <span data-info="6">高绿化</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="deliverStyle">装修标准</dt>
                    <dd>
                        <span data-info="1">毛坯</span>
                        <span data-info="2">普通装修</span>
                        <span data-info="3">精装修</span>
                        <span data-info="4">豪华装修</span>
                        <span data-info="5">其他</span>
                    </dd>
                </dl>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button more-reset" id="moreReset">重置</a>
                    <a href="javascript:;" class="operation-button more-submit" id="moreSubmit">确定</a>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <ul>
        <li><a class="list-item" href="/queryByHouseIdandLocation/">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟，特价西南3+1</h3>
                    <p class="cont-block-2">178㎡/2室2厅/南/西山天璟</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>满二</span>
                        <span>近地铁</span>
                    </div>
                    <div class="cont-block-price">
                        <em>980万</em>
                        <span>68000元/㎡</span>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="/queryByHouseIdandLocation/">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟，特价西南3+1</h3>
                    <p class="cont-block-2">178㎡/2室2厅/南/西山天璟</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>满二</span>
                        <span>近地铁</span>
                    </div>
                    <div class="cont-block-price">
                        <em>980万</em>
                        <span>68000元/㎡</span>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="/queryByHouseIdandLocation/">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟，特价西南3+1</h3>
                    <p class="cont-block-2">178㎡/2室2厅/南/西山天璟</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>满二</span>
                        <span>近地铁</span>
                    </div>
                    <div class="cont-block-price">
                        <em>980万</em>
                        <span>68000元/㎡</span>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="/queryByHouseIdandLocation/">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟，特价西南3+1</h3>
                    <p class="cont-block-2">178㎡/2室2厅/南/西山天璟</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>满二</span>
                        <span>近地铁</span>
                    </div>
                    <div class="cont-block-price">
                        <em>980万</em>
                        <span>68000元/㎡</span>
                    </div>
                </div>
            </div>
        </a></li>
        <li><a class="list-item" href="/queryByHouseIdandLocation/">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟，特价西南3+1</h3>
                    <p class="cont-block-2">178㎡/2室2厅/南/西山天璟</p>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>满二</span>
                        <span>近地铁</span>
                    </div>
                    <div class="cont-block-price">
                        <em>980万</em>
                        <span>68000元/㎡</span>
                    </div>
                </div>
            </div>
        </a></li>
    </ul>
    <p class="tip-box">有新上房源，我们会及时通知您哦！</p>
</section>

<script src="${staticurl}/js/zepto.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>