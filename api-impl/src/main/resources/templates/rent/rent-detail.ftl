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
                    <img src="${qiniuzufangimage}/${rentphoto['image_path']}" data-src="${qiniuzufangimage}/${rentphoto['image_path']}" alt="">
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
        <p><#if rentHouse['rent_sign'] == 1>${rentHouse['zufang_name']}</#if> · ${rentHouse['house_area']}㎡ ${rentHouse['forward']} ${rentHouse['room']}室${rentHouse['hall']}厅</p>
        <#if rentHouse['rent_type_name']?exists || rentHouse['rent_sign_name']?exists || (rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0))>
            <div class="primary-header-rent-tag house-labelling">
                <span class="company">${rentHouse['rent_type_name']}</span><span class="company">${rentHouse['rent_sign_name']}</span><#if rentHouse['rent_house_tags_name']?exists && (rentHouse['rent_house_tags_name']?size gt 0)><#list rentHouse['rent_house_tags_name'] as label><#if label?exists><span>${label}</span></#if></#list></#if>
            </div>
        </#if>
    </div>
</section>
<#if rentHouse['rent_sign'] == 1 && plot?exists>
<div class="border-box">
    <section>
        <div  class="module-header-message">
            <h3>小区信息</h3>
            <a href="${router_city('/xiaoqu/'+rentHouse['zufang_id']?c+'.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li><a href="${router_city('/xiaoqu/'+rentHouse['zufang_id']?c+'.html')}" style="display: block">
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
                    <p><em>年代：</em><#if plot['abbreviatedAge']?exists&&plot['sumBuilding']?exists> ${plot['abbreviatedAge']}年建成住宅,共${plot['sumBuilding']}栋<#else >暂无</#if></p>
                    <p><em>待租：</em><em class="link">${total}套</em></p>
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
        <#if rentHouse['nearest_subway']?exists>
            <#assign subwayDistance = rentHouse['nearest_subway']?split('$')[2]?number>
            <p class="map-distance"><i class="rent-traffic-icon"></i>距${rentHouse['nearest_subway']?split('$')[1]}[${rentHouse['nearest_subway']?split('$')[0]}]<#if subwayDistance gt 1000>${subwayDistance}km<#else >${subwayDistance}m</#if></p>
        </#if>
    </section>
</div>
<div class="border-box">
    <section>
        <div class="module-header-message">
            <h3><#if rentHouse['rent_sign'] == 1>房源点评</#if></h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <#if rentHouse['agent_headphoto']?exists && rentHouse['agent_headphoto'] != ''>
                    <img class="source-icon" src="${qiniuzufangimage}/${rentHouse['agent_headphoto']}" alt="">
                <#else >
                    <img class="source-icon" src="http://pic.centanet.com/beijing/pic/agent/2016050161.jpg" alt="">
                </#if>
                <p>
                    <span>${rentHouse['estate_agent']}</span>
                    <em>${rentHouse['brokerage_agency']}</em>
                </p>
                <a href="tel:13164223605" class="issuer-tel-icon rent"></a>
            </div>
            <#if rentHouse['house_desc']?exists && rentHouse['house_desc'] != ''>
            <div class="describe-cont">
                <p>${rentHouse['house_desc']}</p>
            </div>
            </#if>
        </div>
    </section>
</div>
<#if nearHouse?exists>
<div id="nearbynewesf">
    <section>
        <div class="module-header-message">
            <h3>附近相似好房</h3>
        </div>
        <ul><#list nearHouse as builditem>
            <li><a class="list-item" href="${router_city('/zufang/'+builditem['house_id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if builditem.house_title_img?exists && builditem.house_title_img!=''>
                            <img src="${qiniuzufangimage}/${builditem.house_title_img}" alt="${builditem.zufang_name}">
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
            <a href="tel:" class="contact-telephone-counseling">咨询经纪人</a>
            <a href="#" class="contact-like">喜欢</a>
            <a href="javascript:void(0);" onclick="nextPage(this)" class="contact-next">下一个</a>
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

    console.log(rentSortId,rentUrl)
    function nextPage(e) {
        if (rentUrl.split('?').length>1){
            rentUrl = rentUrl+'&from='+(parseInt(rentSortId)+1);
        }else if (rentUrl.split('?').length==1){
            rentUrl = rentUrl+'?from='+(parseInt(rentSortId)+1)
        }
        console.log(rentUrl)
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
                console.log(data)
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

</script>
</body>
</html>