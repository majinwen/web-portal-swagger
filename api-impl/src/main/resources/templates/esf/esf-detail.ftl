<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/esf-detail.css">
    <title>二手房详情</title>
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner@3x.png" data-src="${staticurl}/images/esf/esxq_banner@3x.png" alt="1">
            </li>
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner@3x.png" data-src="${staticurl}/images/esf/esxq_banner@3x.png" alt="2">
            </li>
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner@3x.png" data-src="${staticurl}/images/esf/esxq_banner@3x.png" alt="3">
            </li>
        </ul>
        <div class="banner-title">
            <div class="banner-house-number">房源编号：HJ8520</div>
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
                    <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>
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
    <section class="primary-message">
        <div class="primary-header text-center">
            <h2>东贸国际 南北通透无遮挡 好视野</h2>
            <div class="primary-header-tag">
                <span>满二</span>
                <span>满五</span>
                <span>随时看房</span>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <ol>
                    <li>
                        <span>总价</span>
                        <em>275万</em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em>3室1厅/南</em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em>127㎡</em>
                    </li>
                </ol>
            </li>
            <li>
                <p>单价：34220元/㎡</p>
            </li>
            <li>
                <p>预算：参考首付267万，月供5432元/月</p>
            </li>
            <li>
                <dl class="module-table-item">
                    <dd class="odd-item">楼层：<span>低楼层/27层</span></dd>
                    <dd class="even-item">电梯：<em>有电梯</em></dd>
                    <dd class="odd-item">类别：<em>花园洋房</em></dd>
                    <dd class="even-item">楼龄：<em>10年</em></dd>
                    <dt>小区：<em>东贸国际[朝阳-双井]</em></dt>
                    <dt>更新时间：2017.12.05</dt>
                </dl>
            </li>
            <li>
                <p>
                    交通信息：距离地铁国贸站[1号线]<em class="primary-distance">0.6km</em>
                    <a href="#" class="primary-map-icon"></a>
                    <a href="#" class="arrows-right"></a>
                </p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>房源描述</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" src="${staticurl}/images/esf/esxq_icon_conpany@3x.png" alt="链家">
                <p>
                    <span>【链家】刘焱</span>
                    <em>房屋信息发布人</em>
                </p>
                <a href="tel:123456789" class="issuer-tel-icon"></a>
            </div>
            <div class="describe-cont">
                <p>房子南北通透，客厅南向，厨房和餐厅北向，带有独立立北向阳台，主卧室南向，带明卫生间，次卧一个南向立北向阳台，主卧室南向房子南北通透，客厅南向，厨房和餐厅北向，带有独立立北向阳台，主卧室南向，带明卫生间，次卧一个南向立北向阳台，主卧室南向房子南北通透，客厅南向，厨房和餐厅北向，带有独立立北向阳台，主卧室南向，带明卫生间，次卧一个南向立北向阳台，主卧室南向 </p>
                <span class="describe-show-btn">>>展开</span>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>小区信息</h3>
            <a href="#" class="more-arrows">小区详情<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li>
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4>首城国际</h4>
                    <p>共18栋（2558户），板楼/板塔结合</p>
                    <p>参考均价<em>115982元</em>/㎡</p>
                </div>
            </li>
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
            <h3>附近好房</h3>
        </div>
        <ul class="tilelist">
            <li>
                <a href="#">
                    <div class="picture-box">
                        <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em>1800万</em>/262㎡/5室</p>
                        <h4 class="cont-last">首城国际</h4>
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="picture-box">
                        <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em>1800万</em>/262㎡/5室</p>
                        <h4 class="cont-last">首城国际</h4>
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="picture-box">
                        <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em>1800万</em>/262㎡/5室</p>
                        <h4 class="cont-last">首城国际</h4>
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="picture-box">
                        <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em>1800万</em>/262㎡/5室</p>
                        <h4 class="cont-last">首城国际</h4>
                    </div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div class="picture-box">
                        <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em>1800万</em>/262㎡/5室</p>
                        <h4 class="cont-last">首城国际</h4>
                    </div>
                </a>
            </li>
        </ul>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>附近小区</h3>
    </div>
    <ul class="tilelist">
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4 class="cont-first">后现代城</h4>
                    <p class="cont-last"><em>68960元</em>/㎡</p>
                </div>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4 class="cont-first">后现代城</h4>
                    <p class="cont-last"><em>68960元</em>/㎡</p>
                </div>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4 class="cont-first">后现代城</h4>
                    <p class="cont-last"><em>68960元</em>/㎡</p>
                </div>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4 class="cont-first">后现代城</h4>
                    <p class="cont-last"><em>68960元</em>/㎡</p>
                </div>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <h4 class="cont-first">后现代城</h4>
                    <p class="cont-last"><em>68960元</em>/㎡</p>
                </div>
            </a>
        </li>
    </ul>
</section>
<script src="${staticurl}/js/zepto.min.js"></script>
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script>
    var describeAllContent = $('.describe-cont p').text();
    $('.describe-cont p').text(describeAllContent.substr(0,59));
    $('.describe-show-btn').click(function () {
        $(this).hide();
        $('.describe-cont p').text(describeAllContent);
    });
</script>
<script>
    var mySwiper = new Swiper('.carousel-swiper', {
        autoplay: 2000,//可选选项，自动滑动
        loop: true,
        pagination : '.swiper-pagination',
        paginationType : 'fraction'
    });

    function initphoto(a,i) {
        if(typeof i == 'undefined'){
            i = 0;
        }
        var b = [];
        var imgW = $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").width();
        var imgH = $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").height();
        $(a).parent().find("li").not('.swiper-slide-duplicate').find("img").each(function (a, e) {
            var c = $(e).attr("data-src");
            $(e).attr("data-title");
            c && b.push({src: c, w: imgW, h: imgH})
        });
        a = document.querySelectorAll(".pswp")[0];
        (new PhotoSwipe(a, PhotoSwipeUI_Default, b, {
            index: i, addCaptionHTMLFn: function (a, b, c) {
                if (!a.title)return b.children[0].innerHTML = "", !1;
                b.children[0].innerHTML = a.title;
                return !0
            }
        })).init()
    }
</script>
</body>
</html>