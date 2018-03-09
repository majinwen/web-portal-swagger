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
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="http://image.5i5j.com/picture/house/3877/38770403/shinei/kpccdaipdb38cd53.jpg" data-src="http://image.5i5j.com/picture/house/3877/38770403/shinei/kpccdaipdb38cd53.jpg" alt="">
            </li>
        <div class="banner-title">
            <div class="banner-house-number">房源编号：2873673</div>
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
        <h2>¥4800元/月(押一付三)</h2>
        <div class="primary-header-tag house-labelling gray">
            <span>首次出租</span>
            <span>离地铁近</span>
        </div>
    </div>
</section>
<section>
    <div  class="module-header-message">
        <h3>小区信息</h3>
        <a href="" class="more-arrows"><i class="arrows-right"></i></a>
    </div>
    <ul class="tilelist row">
        <li>
            <a href="/bj/xiaoqu/11111737.html" style="display: block">
                <div class="picture-box">
                    <img src="http://s1.qn.toutiaofangchan.com/c1-6d5924c0-daf9-11e7-92d8-14abc5f0dc77.jpg-tt400x300" alt="UHN国际村">
                </div>
                <div id="tilePlotDesc" class="tilelist-content">
                    <h4><em>小区：</em>UHN国际村</h4>
                    <p><em>年代：</em>2008年建成住宅,共7栋</p>
                    <p><em>待租：</em><em>123套</em></p>
                </div>
            </a>
        </li>

    </ul>
</section>
<section>
    <div class="module-header-message">
        <h3>配套地图</h3>
        <a onclick="esf_map_1(this)" href="#" class="more-arrows"><i class="arrows-right"></i></a>
    </div>
    <a onclick="esf_map_2(this)" href="/bj/esf/11111737/map.html" class="detail-map">
        <i class="map-marker-icon"></i>
        <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&amp;width=700&amp;height=350&amp;center=116.455055236816,39.9732894897461&amp;&amp;zoom=16" alt="">
    </a>
</section>
<section>
    <div class="module-header-message">
        <h3>房源描述</h3>
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
<div id="nearbynewesf">
    <section>
        <div class="module-header-message">
            <h3>附近相似好房</h3>
        </div>
        <ul>
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
                        <div class="cont-block-bottom house-labelling big normal">
                            <span>离地铁近</span>
                            <span>集中供暖</span>
                        </div>
                    </div>
                </div>
            </a></li>
        </ul>
    </section>
</div>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <a href="tel:" class="only contact-telephone-counseling">咨询经纪人</a>
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