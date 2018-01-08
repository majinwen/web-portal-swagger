<!DOCTYPE html>
<html>
<head>
    <#include "staticHeader.ftl">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>地图页</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/map.css">
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<ul id="mapTypeNav" class="map-type-nav">
    <li class="map-type-item current" data-w="交通" data-icon="traffic" data-key="地铁站,停车场,公交站">交通</li>
    <li class="map-type-item" data-w="教育" data-icon="education" data-key="亲子教育,幼儿园,小学,中学,高等院校,特殊教育">教育</li>
    <li class="map-type-item" data-w="医疗" data-icon="medical" data-key="综合医院,专科医院,诊所,疗养院,急救中心,疾控中心,">医疗</li>
    <li class="map-type-item" data-w="购物" data-icon="shopping" data-key="购物中心,超市,集市">商超</li>
    <li class="map-type-item" data-w="美食" data-icon="gastronomy" data-key="中餐厅,外国餐厅,小吃快餐店,咖啡厅,茶座,酒吧">美食</li>
    <li class="map-type-item" data-w="休闲" data-icon="fallow" data-key="电影院,KTV,剧院,休闲广场,游戏场所">休闲</li>
    <li class="map-type-item" data-w="健身" data-icon="fitness" data-key="体育场馆,极限运动,健身中心">健身</li>
</ul>
<#if build?exists>
<#if build['location']?exists>
    <#assign locations = build['location']?split(",")>
<div class="map-container" id="mapContainer" data-mapx="${locations[1]}" data-mapy="${locations[0]}" data-cnname="富尔大厦"></div>
<#else ><div class="map-container" id="mapContainer" data-mapx="${locations[1]}" data-mapy="${locations[0]}" data-cnname="富尔大厦"></div>
</#if><#else><div class="map-container" id="mapContainer" data-mapx="${locations[1]}" data-mapy="${locations[0]}" data-cnname="富尔大厦"></div>
</#if>
<div class="type-result-box">
    <div class="results-panel-box">
        <div class="results-panel-btn"><p></p></div>
        <div id="slideContent">
            <div class="results-title">
                <p><i class="traffic"></i><em class="title-map-type">地铁站</em></p><span>3km内共<em class="result-num high-light-red">1</em>个</span>
            </div>
            <ul id="resultsPanel" class="results-panel"></ul>
        </div>
    </div>
    <div class="type-item-sel-box">
        <ul id="typeNavSel" class="type-item-sel"></ul>
    </div>
</div>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<script type="text/javascript" src="${staticurl}/js/around-map.js"></script>
<script>
    var x = $('#mapContainer').attr('data-mapx'),
            y = $('#mapContainer').attr('data-mapy'),
            cnName = $('#mapContainer').attr('data-cnname');
    Map.init(x, y, 15, 1, cnName);

    $('.results-panel-btn').on('click', function () {
        $('#slideContent').slideToggle();
    });
</script>
</body>
</html>