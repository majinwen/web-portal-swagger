<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/rent-detail.css?v=${staticversion}">
    <title>头条房产看租房</title>
    <meta name="description" content="">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<#include "../StatisticsHeader.ftl">
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
        <#if rentHouse['rent_house_img']?exists && (rentHouse['rent_house_img']?size gt 0)>
            <#list rentHouse['rent_house_img'] as rentphoto>
                <li onclick="initphoto(this,${rentphoto_index},window.location.href)" class="swiper-slide">
                    <#if rentphoto['image_path']?index_of("http") gt -1>
                        <img src="${rentphoto['image_path']}" data-src="${rentphoto['image_path']}" alt="">
                    <#else>
                        <img src="${qiniuzufangimage}/${rentphoto['image_path']}-ttfc1200x640" data-src="${qiniuzufangimage}/${rentphoto['image_path']}-ttfc1200x640" alt="">
                    </#if>
                </li>
            </#list>
        <#else>
            <li onclick="initphoto(this,0,window.location.href)" class="swiper-slide">
                <img src="${staticurl}/images/global/tpzw_banner_image.png" height="100%" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="">
            </li>
        </#if>
        </ul>
        <div class="banner-title rent">
            <div class="banner-house-number">编号${rentHouse['house_id']}</div>
            <div class="swiper-pagination pictrue-index"></div>
        </div>
    </div>
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                <div class="pswp__top-bar">
                    <div class="pswp__counter"></div>
                    <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                    <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                    <div class="pswp__preloader">
                        <div class="pswp__preloader__icn">
                            <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                    <div class="pswp__share-tooltip"></div>
                </div>
                <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
                <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
                <div class="pswp__caption">
                    <div class="pswp__caption__center"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<section class="primary-message">
    <div class="primary-header">
        <h2><#if rentHouse['rent_house_price']?exists>¥${rentHouse['rent_house_price']}元/月<#else>暂无数据</#if><#if rentHouse['pay_mode_name']?exists>(${rentHouse['pay_mode_name']})</#if></h2>
        <p>${rentHouse['zufang_name']} · ${rentHouse['house_area']}㎡ ${rentHouse['forward']} ${rentHouse['room']}室${rentHouse['hall']}厅</p>
        <#if rentHouse['rent_type_name']?exists || rentHouse['rent_sign_name']?exists || (rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0))>
            <div class="primary-header-rent-tag house-labelling">
                <span class="company">${rentHouse['rent_type_name']}</span><span class="company">${rentHouse['rent_sign_name']}</span><#if agent?exists&&agent['house_tags_name']?exists&&agent['house_tags_name']?size gt 0><#list agent['house_tags_name'] as label><#if label?exists><span>${label}</span></#if></#list><#elseif  rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0)><#list rentHouse['rent_house_tags_name'] as label><#if label?exists><span>${label}</span></#if></#list></#if>
            </div>
        </#if>
    </div>
</section>
<#if rentHouse?exists && rentHouse['supporting_facilities']?exists && rentHouse['supporting_facilities']?size gt 0>
<div class="border-box">
    <section>
        <div  class="module-header-message">
            <h3>配套设施</h3>
        </div>
        <ul class="rent-support-nav">
            <#if rentHouse['supporting_facilities']?exists>
                ${rentHouse['supporting_facilities']?seq_contains("空调")?string('<li class="support-item support"><i class="kongtiao"></i><span>空调</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("电视")?string('<li class="support-item support"><i class="dianshi"></i><span>电视</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("洗衣机")?string('<li class="support-item support"><i class="xiyiji"></i><span>洗衣机</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("热水器")?string('<li class="support-item support"><i class="reshuiqi"></i><span>热水器</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("冰箱")?string('<li class="support-item support"><i class="bingxiang"></i><span>冰箱</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("微波炉")?string('<li class="support-item support"><i class="weibolu"></i><span>微波炉</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("床")?string('<li class="support-item support"><i class="chuang"></i><span>床</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("衣柜")?string('<li class="support-item support"><i class="yigui"></i><span>衣柜</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("沙发")?string('<li class="support-item support"><i class="shafa"></i><span>沙发</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("桌子")?string('<li class="support-item support"><i class="zhuozi"></i><span>桌子</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("椅子")?string('<li class="support-item support"><i class="yizi"></i><span>椅子</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("暖气")?string('<li class="support-item support"><i class="nuanqi"></i><span>暖气</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("煤气")?string('<li class="support-item support"><i class="meiqi"></i><span>煤气</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("网络")?string('<li class="support-item support"><i class="wangluo"></i><span>网络</span></li>', '')}
                ${rentHouse['supporting_facilities']?seq_contains("智能锁")?string('<li class="support-item support"><i class="zhinengsuo"></i><span>智能锁</span></li>', '')}
            </#if>
        </ul>
    </section>
</div>
</#if>
<#if plot?exists&&rentHouse['rent_sign'] == 0>
<div class="border-box">
    <section>
        <div  class="module-header-message">
            <h3>小区信息</h3>
            <a onclick="rentDetailInfo_1(this)" href="${router_city('/xiaoqu/'+rentHouse['zufang_id']+'.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li><a onclick="rentDetailInfo_2(this)" href="${router_city('/xiaoqu/'+rentHouse['zufang_id']+'.html')}" style="display: block">
                <div class="picture-box">
                    <#assign photo = plot['photo']>
                    <#if photo[0]?exists>
                        <img src="${qiniuimage}/${photo[0]}-tt400x300" alt="${plot.rc}">
                    <#else >
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    </#if>
                </div>
                <div id="tilePlotDesc" class="tilelist-content">
                    <h4><em>小区：</em>${plot['rc']} [${plot['area']} ${plot['tradingArea']}]</h4>
                    <p><em>年代：</em><#if plot['abbreviatedAge']?exists>${plot['abbreviatedAge']}年建成住宅</#if><#if plot['sumBuilding']?exists>，共${plot['sumBuilding']}栋</#if></p>
                    <p><em>待租：</em><#if total?exists><em class="link">${total}套</em><#else >暂无其他房源</#if></p>
                </div>
            </a></li>
        </ul>
    </section>
</div>
</#if>
<div class="border-box">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a onclick="rent_map_1(this)" href="${router_city('/zufang/'+rentHouse.house_id+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <a onclick="rent_map_2(this)" href="${router_city('/zufang/'+rentHouse.house_id+'/map.html')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if rentHouse.location?exists>
                <#assign lon = rentHouse['location']?split(',')[0]>
                <#assign lat = rentHouse['location']?split(',')[1]>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${lat},${lon}&&zoom=16" alt="">
            <#else >
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
        <#if rentHouse['nearest_subway']?exists && rentHouse['nearest_subway']!=''>
            <#assign subwayDistance = rentHouse['nearest_subway']?split('$')[2]?number>
            <p class="map-distance"><i class="rent-traffic-icon"></i>距${rentHouse['nearest_subway']?split('$')[1]}[${rentHouse['nearest_subway']?split('$')[0]}]<#if subwayDistance gt 1000>${(subwayDistance/1000)?string('0.0')}km<#else >${subwayDistance}m</#if></p>
        </#if>
    </section>
</div>
<div class="border-box">
    <#if rentHouse['rent_sign'] == 0>
        <#if agent?exists>
            <section>
                <div class="module-header-message">
                    <h3>房源点评</h3>
                </div>
                <div class="describe-box">
                    <div class="describe-header">
                        <#if agent['agent_headphoto']?exists && agent['agent_headphoto'] != ''>
                            <#if agent['agent_headphoto']?index_of("http") gt -1>
                                <img class="source-icon" src="${agent['agent_headphoto']}" alt="">
                            <#else>
                                <img class="source-icon" src="${qiniuzufangimage}/${agent['agent_headphoto']}" alt="">
                            </#if>
                        <#else >
                            <img class="source-icon" src="http://pic.centanet.com/beijing/pic/agent/2016050161.jpg" alt="">
                        </#if>
                        <p>
                            <span><#if agent['agent_name']?exists&&agent['agent_name']!=''>${agent['agent_name']}</#if></span>
                            <em><#if agent['of_company']?exists&&agent['of_company']!=''> ${agent['of_company']}</em></#if>
                        </p>
                        <#if agent['agent_phone']?exists&&agent['agent_phone']!=''>
                            <a href="tel:${agent['agent_phone']}" class="issuer-tel-icon rent" id="rentDescPhone"></a>
                        </#if>
                    </div>
                    <#if agent['house_desc']?exists && agent['house_desc'] != ''>
                        <div class="describe-cont">
                            <p>${agent['house_desc']}</p>
                        </div>
                    </#if>
                </div>
            </section>
        <#else>
            <section>
                <div class="module-header-message">
                    <h3>房源点评</h3>
                </div>
                <div class="describe-box">
                    <div class="describe-header">
                        <#if rentHouse['agent_headphoto']?exists && rentHouse['agent_headphoto'] != ''>
                            <#if rentHouse['agent_headphoto']?index_of("http") gt -1>
                                <img class="source-icon" src="${rentHouse['agent_headphoto']}" alt="">
                            <#else>
                                <img class="source-icon" src="${qiniuzufangimage}/${rentHouse['agent_headphoto']}" alt="">
                            </#if>
                        <#else >
                            <img class="source-icon" src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                        <p>
                            <span>${rentHouse['estate_agent']}</span>
                            <em>${rentHouse['brokerage_agency']}</em>
                        </p>
                        <#if rentHouse['phone']?exists>
                            <a href="tel:${rentHouse['phone']}" class="issuer-tel-icon rent"></a>
                        </#if>
                    </div>
                    <#if rentHouse['house_desc']?exists && rentHouse['house_desc'] != ''>
                        <div class="describe-cont">
                            <p>${rentHouse['house_desc']}</p>
                        </div>
                    </#if>
                </div>
            </section>
        </#if>
    <#else>
        <section>
            <div class="module-header-message">
                <h3>公寓简介</h3>
            </div>
            <div class="describe-box">
                <div class="describe-header">
                    <#if rentHouse['agent_headphoto']?exists && rentHouse['agent_headphoto'] != ''>
                        <#if rentHouse['agent_headphoto']?index_of("http") gt -1>
                            <img class="source-icon" src="${rentHouse['agent_headphoto']}" alt="">
                        <#else>
                            <img class="source-icon" src="${qiniuzufangimage}/${rentHouse['agent_headphoto']}" alt="">
                        </#if>
                    <#else >
                        <img class="source-icon" src="http://pic.centanet.com/beijing/pic/agent/2016050161.jpg" alt="">
                    </#if>
                    <p>
                        <span>${rentHouse['zufang_name']}</span>
                        <em>${rentHouse['brokerage_agency']}</em>
                    </p>
                    <#if agent?exists&&agent['agent_phone']?exists>
                        <a href="tel:${agent['agent_phone']}" class="issuer-tel-icon rent" id="rentDescPhone"></a>
                    <#elseif rentHouse['phone']?exists>
                        <a href="tel:${rentHouse['phone']}" class="issuer-tel-icon rent"></a>
                    </#if>
                </div>
                <#if rentHouse['house_desc']?exists && rentHouse['house_desc'] != ''>
                <div class="describe-cont">
                    <p>${rentHouse['house_desc']}</p>
                </div>
                </#if>
            </div>
        </section>
    </#if>
</div>
<#if rentHouse['rent_sign'] != 0 && rentHouse['demand']?exists && rentHouse['demand'] != ''>
    <div class="border-box">
        <section>
            <div class="module-header-message">
                <h3>入住要求</h3>
            </div>
            <ul class="demand-list">
                <li>${rentHouse['demand']}</li>
            </ul>
        </section>
    </div>
</#if>
<#if nearHouse?exists && nearHouse?size gt 0>
<div id="nearbyRent">
    <section>
        <div class="module-header-message">
            <h3><#if rentHouse['rent_sign'] == 0>附近相似好房<#else>${rentHouse['zufang_name']}好房推荐</#if></h3>
        </div>
        <ul><#list nearHouse as builditem>
            <li><a class="list-item" href="${router_city('/zufang/'+builditem['house_id']+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if builditem.house_title_img?exists && builditem.house_title_img!=''>
                            <#if builditem.house_title_img?index_of("http") gt -1>
                                <img src="${builditem.house_title_img}" alt="${builditem.zufang_name}">
                            <#else>
                                <img src="${qiniuzufangimage}/${builditem.house_title_img}-tt400x300" alt="${builditem.zufang_name}">
                            </#if>
                        <#else >
                            <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <h3 class="cont-block-top"><span>${builditem['zufang_name']} · ${builditem['house_area']}㎡ ${builditem['forward']} ${builditem['room']}室${builditem['hall']}厅</span></h3>
                        <div class="address distance"><i class="icon"></i>${builditem['district_name']} ${builditem['area_name']}</div>
                        <#if rentHouse['rent_type_name']?exists || (rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0))>
                            <div class="house-labelling big normal">
                                <span class="company">${builditem['rent_type_name']}</span>
                                <#if rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0)><#list rentHouse['rent_house_tags_name'] as label><#if label?exists><span>${label}</span> </#if></#list></#if>
                            </div>
                        </#if>
                        <div class="cont-block-bottom">
                            <p class="high-light-red"><#if builditem['rent_house_price']?exists>¥${builditem['rent_house_price']}<em> 元/月</em><#else>暂无数据</#if></p>
                        </div>
                    </div>
                </div>
            </a></li></#list>
        </ul>
    </section>
</div></#if>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <#if agent?exists&&agent['agent_phone']?exists>
                <a href="tel:${agent['agent_phone']}" class="contact-telephone-counseling" id="rentBottomPhone">咨询经纪人</a>
            <#elseif rentHouse['phone']?exists>
                <a href="tel:${rentHouse['phone']}" class="contact-telephone-counseling" id="rentBottomPhone">咨询经纪人</a>
            </#if>
            <#--<a href="#" class="contact-like">喜欢</a>-->
            <a href="javascript:void(0);" onclick="nextPage(this)" class="contact-next"><i class="next-icon"></i>下一个</a>
        </div>
    </section>
</div>
<!-------- photoswipe -------->
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script>
    var rentUrl = sessionStorage.getItem('rentUrl')||window.location.protocol+'//'+window.location.host+router_city('/zufang');
    var rentSortId = sessionStorage.getItem('rentSortId')||-1;

    function nextPage(e) {

        if (rentUrl.split('?').length>1){
            rentUrl = rentUrl.split('&from=')[0]+'&from='+(parseInt(rentSortId)+1);
        }else if (rentUrl.split('?').length==1){
            rentUrl = rentUrl+'?from='+(parseInt(rentSortId)+1)
        }
        var flag = sessionStorage.getItem(rentUrl)||false;
        if (flag){
            return;
        }
        $.ajax({
            type: "get",
            contentType:'application/json',
            url: rentUrl,
            async: true,
            dataType:'json',
            success:function (data) {
                if(data.code=='fail'){
                    return;
                }
                if (!data.data.data.length>0){
                    sessionStorage.setItem(rentUrl,true);
                    return;
                }
                sessionStorage.setItem('rentSortId',parseInt(rentSortId)+1);
                window.location.replace(router_city('/zufang/'+data.data.data[0].house_id+'.html'))
            }
        })
    }

    $(function () {
        var text = $("tilePlotDesc").find("p").text();
        if (text.indexOf(",") == 0) {
            var s = text.substring(1);
            $("tilePlotDesc").find("p").html(s);
        }
    });
    function desc(url) {
        if(url.indexOf('zufang')>-1){
            return '租房-进入租房详情页'
        }
        if(url.indexOf(window.location.hostname)>-1){
            return '大首页-点击租房推荐'
        }
    }
    zhuge.track(desc(document.referrer), {
        '区域' : '<#if rentHouse.district_name?exists && rentHouse.district_name!=''>${rentHouse.district_name}</#if>',
        '商圈' : '<#if rentHouse.area_name?exists && rentHouse.area_name!=''>${rentHouse.area_name}</#if>',
        '<#if rentHouse.rent_sign?exists && rentHouse.rent_sign == 1>小区名称</#if>' : '<#if rentHouse.zufang_name?exists&& rentHouse.zufang_name!=''>${rentHouse.zufang_name}</#if>',
        '出租方式' : '<#if rentHouse.rent_sign_name?exists>${rentHouse.rent_sign_name}</#if>',
        '租金' : '<#if rentHouse.rent_house_price?exists && (rentHouse.rent_house_price!=0)>${rentHouse.rent_house_price}</#if>元',
        '面积' : '<#if rentHouse.house_area?exists && rentHouse.house_area!=0>${rentHouse.house_area}㎡</#if>',
        '户型' : '<#if rentHouse.room?exists>${rentHouse.room}室</#if><#if rentHouse.hall?exists>${rentHouse.hall}厅</#if>',
        '发布公司' : '<#if rentHouse.brokerage_agency?exists>${rentHouse.brokerage_agency}</#if>',
        'ID' : '<#if rentHouse.house_id?exists>${rentHouse.house_id}</#if>',
        'location' : '${rentHouse.location}'
    });

    zhuge.track('租房详情页点击量', {
        '区域' : '<#if rentHouse.district_name?exists && rentHouse.district_name!=''>${rentHouse.district_name}</#if>',
        '商圈' : '<#if rentHouse.area_name?exists && rentHouse.area_name!=''>${rentHouse.area_name}</#if>',
        '<#if rentHouse.rent_sign?exists && rentHouse.rent_sign == 1>小区名称</#if>' : '<#if rentHouse.zufang_name?exists&& rentHouse.zufang_name!=''>${rentHouse.zufang_name}</#if>',
        '出租方式' : '<#if rentHouse.rent_sign_name?exists>${rentHouse.rent_sign_name}</#if>',
        '租金' : '<#if rentHouse.rent_house_price?exists && (rentHouse.rent_house_price!=0)>${rentHouse.rent_house_price}</#if>元',
        '面积' : '<#if rentHouse.house_area?exists && rentHouse.house_area!=0>${rentHouse.house_area}㎡</#if>',
        '户型' : '<#if rentHouse.room?exists>${rentHouse.room}室</#if><#if rentHouse.hall?exists>${rentHouse.hall}厅</#if>',
        '发布公司' : '<#if rentHouse.brokerage_agency?exists>${rentHouse.brokerage_agency}</#if>',
        'ID' : '<#if rentHouse.house_id?exists>${rentHouse.house_id}</#if>',
        'location' : '${rentHouse.location}'
    });
    function rent_map_1(a) {
        var link = $(a);
        zhuge.track('租房-点击配套地图', {
            "租房-点击配套地图": link.attr('href')
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    }
    function rent_map_2(a) {
        var link = $(a);
        zhuge.track('租房-点击配套地图', {
            "租房-点击配套地图": link.attr('href')
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    }
    function rentDetailInfo_1(a) {
        var link = $(a);
        zhuge.track('租房-点击查看小区详情', {
            "租房-点击查看小区详情": link.attr('href')
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    }
    function rentDetailInfo_2(a) {
        var link = $(a);
        zhuge.track('租房-点击查看小区详情', {
            "租房-点击查看小区详情": link.attr('href')
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    }
    $('#rentDescPhone').on('click', function () {
        if('<#if rentHouse['rent_sign'] == 0>true</#if>') {
            zhuge.track('租房-点击咨询经纪人', {
                "位置": "经纪人描述旁"
            })
        } else {
            zhuge.track('租房-点击咨询公寓客服', {
                "位置": "公寓品牌介绍旁"
            })
        }
    });
    $('#rentBottomPhone').on('click', function () {
        if('<#if rentHouse['rent_sign'] == 0>true</#if>') {
            zhuge.track('租房-点击拨打电话', {
                "位置": "底部"
            })
        } else {
            zhuge.track('租房-点击拨公寓客服', {
                "位置": "底部"
            })
        }
    });
</script>
</body>
</html>