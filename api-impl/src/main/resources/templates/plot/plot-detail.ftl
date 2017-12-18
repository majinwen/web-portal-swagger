<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/plot-detail.css">
    <title>小区详情</title>
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner1.png" data-src="${staticurl}/images/esf/esxq_banner1.png" alt="">
            </li>
        </ul>
        <div class="banner-title">
            <#--<div class="banner-house-number">房源编号：${build['building_name']}</div>-->
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
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>推荐小区好房</h3>
            <a href="#" class="more-arrows">查看全部房源<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a href="#" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>待售房源</h3>
            <a href="#" class="more-arrows">查看全部待售<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>看了本楼盘的用户还看了</h3>
        </div>
        <ul class="tilelist">
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">五和万科长阳天地</p>
                    <p class="cont-center"><span>房山</span><span>长阳</span></p>
                    <h4 class="cont-last">均价：<em>59850</em>/㎡</h4>
                </div>
            </a></li>
        </ul>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>新房推荐</h3>
    </div>
    <ul class="tilelist">
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
        <li><a href="#">
            <div class="picture-box">
                <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">后现代城</h4>
                <p class="cont-last">均价：<em>68960元</em>/㎡</p>
            </div>
        </a></li>
    </ul>
</section>
<section class="detail-contact-box" id="detailContactState">
    <div class="detail-contact-content">
        <a href="#" class="contact-share"><i></i>分享</a>
        <a href="#" class="contact-collect"><i></i>收藏</a>
        <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
    </div>
</section>

<script src="${staticurl}/js/zepto.min.js"></script>
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>