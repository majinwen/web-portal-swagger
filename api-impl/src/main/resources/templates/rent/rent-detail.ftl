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
        <#if rentHouse['rent_hsoue_img']?exists && (rentHouse['rent_hsoue_img']?size gt 0)>
            <#list rentHouse['rent_hsoue_img'] as rentphoto>
                <li onclick="initphoto(this,${rentphoto_index},window.location.href)" class="swiper-slide">
                    <img src="${qiniuimage}/${rentphoto['image_path']}-ttfdc1200x640" data-src="${qiniuimage}/${rentphoto['image_path']}-ttfdc1200x640" alt="">
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
        <p>${rentHouse['village_name']} · ${rentHouse['house_area']}㎡ ${rentHouse['forward']} ${rentHouse['room']}室${rentHouse['hall']}厅</p>
        <div class="primary-header-rent-tag house-labelling">
            <span>首次出租</span><span>离地铁近</span>
        </div>
    </div>
</section>
<div class="border-box">
    <section>
        <div  class="module-header-message">
            <h3>小区信息</h3>
            <a href="" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li>
                <a href="/bj/xiaoqu/11111737.html" style="display: block">
                    <div class="picture-box">
                        <#if rentHouse.house_title_img?exists && rentHouse.house_title_img!=''>
                            <img src="${rentHouse.house_title_img}" alt="${rentHouse.village_name}">
                        <#else >
                            <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                    </div>
                    <div id="tilePlotDesc" class="tilelist-content">
                        <h4><em>小区：</em>${rentHouse.village_name}</h4>
                        <p><em>年代：</em>2008年建成住宅,共7栋</p>
                        <p><em>待租：</em><em class="link">123套</em></p>
                    </div>
                </a>
            </li>

        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a onclick="esf_map_1(this)" href="#" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <a onclick="esf_map_2(this)" href="#" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if rentHouse.lat?exists && rentHouse.lon?exists>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${rentHouse.lat?if_exists?string("####.#######################")},${rentHouse.lon?if_exists?string("####.#######################")}&&zoom=16" alt="">
            <#else >
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
        <p class="map-distance">距四惠站[1号线]2.1km </p>
    </section>
</div>
<div class="border-box">
    <section>
        <div class="module-header-message">
            <h3>房源点评</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" src="http://pic.centanet.com/beijing/pic/agent/2016050161.jpg" alt="">
                <p>
                    <span style="left: 0">李宝飞</span>
                    <em>中原地产</em>
                </p>
                <a href="tel:13164223605" class="issuer-tel-icon"></a>
            </div>
            <div class="describe-cont">
                <p>此房的房源编号为：BJCY0000020853，我们将全心全意为您服务。<br>此户型在小区属于户型，小区出房率不是很，采光面超大，朝向好，通风效果更好，夏天不用开空调，家具电器好摆放，实际看起来要比想象中的大很多。可以拎包入住，省去您的装修费用。</p>
            </div>
        </div>
    </section>
</div>
<#if nearHouse?exists>
<div id="nearbynewesf">
    <section>
        <div class="module-header-message">
            <h3>附近相似好房</h3>
        </div>
        <#--<#assign nearHouseList = nearHouse['nearHouse']>-->
        <ul><#list nearHouse as builditem>
            <li><a class="list-item" href="${router_city('/zufang/'+builditem['house_id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <img src="/static/images/global/tpzw_image.png" alt="拍摄中">
                    </div>
                    <div class="list-item-cont">
                        <h3 class="cont-block-top"><span>${builditem['village_name']} · ${builditem['house_area']}㎡ ${builditem['forward']} ${builditem['room']}室${builditem['hall']}厅</span></h3>
                        <div class="address distance"><i class="icon"></i>海淀 蓟门桥</div>
                        <div class="house-labelling big normal">
                            <span class="company">整租</span>
                            <span>地铁近</span>
                        </div>
                        <div class="cont-block-bottom">
                            <p class="high-light-red"><#if builditem['rent_house_price']?exists>¥${builditem['rent_house_price']}<em> 元/月</em><#else>暂无数据</#if></p>
                        </div>
                    </div>
                </div>
            </a></li></#list>
        <#--<#list newbuilds as builditem>-->
            <#--<li data-type="<#if builditem['district_name']?exists&&builditem['district_name']!="">${builditem.district_name}</#if>">-->
                <#--<a href="${router_city('/loupan/'+builditem['building_name_id']?c+'.html')}">-->
                    <#--<div class="picture-box">-->
                        <#--<#assign imglist = builditem['building_title_img']>-->
                        <#--<#if imglist?exists >-->
                            <#--<#if imglist?split(",")[0]?? && imglist?split(",")[0] != ''><img src="${qiniuimage}/${imglist?split(",")[0]}-tt400x300" alt="${imglist?split(",")[0]}">-->
                            <#--<#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">-->
                            <#--</#if>-->
                        <#--</#if>-->
                    <#--</div>-->
                    <#--<div class="tilelist-content">-->
                        <#--<h4 class="cont-first">${builditem['building_name']!''}</h4>-->
                        <#--<#if builditem['average_price']?exists && builditem['average_price'] gt 0>-->
                            <#--<p class="cont-last">均价：<em>${builditem['average_price']}元</em>/㎡</p>-->
                        <#--<#else>-->
                            <#--<#if builditem['total_price']?exists && builditem['total_price'] gt 0>-->
                                <#--<p class="cont-last">总价：<em>${builditem['total_price']}万</em>/套</p>-->
                            <#--<#else><p class="cont-last"><em>售价待定</em></p>-->
                            <#--</#if>-->
                        <#--</#if>-->
                    <#--</div>-->
                <#--</a>-->
            <#--</li>-->
        <#--</#list>-->
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

    function nextPage() {
        if (rentUrl.split('?').length>1){
            rentUrl = rentUrl+'&from='+(parseInt(rentSortId)+1);
        }else if (rentUrl.split('?').length==1){
            rentUrl = rentUrl+'?from='+(parseInt(rentSortId)+1)
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
                sessionStorage.setItem('rentSortId',parseInt(rentSortId)+1);
                window.location.replace(router_city('/zufang/'+data.data.data[0].id+'.html'))
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

</script>
</body>
</html>