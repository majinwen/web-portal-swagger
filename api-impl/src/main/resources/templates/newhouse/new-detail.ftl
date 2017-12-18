<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <script src="${staticurl}/js/echarts.js"></script>
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/new-detail.css">
    <title>新房详情</title>
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <#assign imglist = build['building_imgs']>
            <#list imglist as item>
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/esf/esxq_banner1.png" data-src="${staticurl}/images/esf/esxq_banner1.png" alt="${build['building_name']}">
            </li>
            </#list>
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
    <section class="primary-message">
        <div class="primary-header">
            <h2>${build['building_name']}<em class="sale-state">${build['sale_status_name']}</em></h2>
            <p>别名：${build['building_nickname']}</p>
            <div class="primary-header-tag">
            <#assign tags = build['building_tags']>
            <#list tags as item>
                <span>${item}</span>
            </#list>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>均价：<em class="high-light-red">${build['average_price']}</em>/㎡</p>
            </li>
            <li>
                <p>
                    地址：[${build['district_name']}]${build['building_address']}
                    <a href="#" class="primary-map-icon"></a>
                    <a href="#" class="arrows-right"></a>
                </p>
                <p>
                    交通信息：${build['roundstation']} <#--1.0km<em class="primary-distance">0.6km</em>-->
                </p>
            </li>
            <li>
                <p>最新开盘：${build['opened_time']}</p>
                <p>交房时间：${build['deliver_time']}</p>
                <p>售楼许可证：${build['sell_licence']}</p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <div class="active-module-box">
        <a href="tel:1234567" class="active-module-content">
            <p class="active-text"><i class="active-icon"></i><span>最新活动：${build['activity_desc']}</span></p>
            <div class="consule-message">
                <p>
                    <span>更多优惠信息</span>
                    <span>请咨询售楼处</span>
                </p>
                <i></i>
            </div>
        </a>
    </div>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>楼盘描述</h3>
            <a href="/newhouse/getNewHouseDiscript?id=${build['building_name_id']}" class="more-arrows">查看全部<i class="arrows-right"></i></a>
        </div>
        <dl class="module-table-item">
            <dt>开发商：${build['developers']}</dt>
            <dd class="odd-item">物业类型：<span>${build['property_type']}</span></dd>
            <dd class="even-item">建筑类型：<em>${build['building_type']}</em></dd>
            <dd class="odd-item">产权年限：<em>${build['building_life']}年</em></dd>
            <dd class="even-item">车位配比：<em>${build['park_radio']}</em></dd>
        </dl>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>户型信息</h3>
            <a href="/newhouse/getNewHouseLayoutCountByRoom?id=${build['building_name_id']}&&tags=0" class="more-arrows">全部户型<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
     <#if layout?exists>
    <#list layout as item>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/newhouse/huxing_img.png" alt="户型图">
                    <span class="sale-state">在售</span>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first"><span>${item['room']}室${item['hall']}厅${item['toilet']}卫</span><span>${item['building_area']}㎡</span></p>
                    <h4 class="cont-last">均价：${item['reference_price']}元/㎡</h4>
                    <div class="house-labelling normal small tilelist-tag">
        <#assign layouttagitem = item['layout_tag']>
        <#list layouttagitem as tagatem>
            <span>${tagatem}</span>
        </#list>
                    </div>
                </div>
            </a>
        </li>
    </#list>
    </#if>
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
        <div style="height: 800px" id="main" class="module-header-message">
            <h3>价格走势</h3>
        </div>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>看了本楼盘的用户还看了</h3>
    </div>
    <ul class="tilelist">
    <#if nearbybuild?exists>
    <#list nearbybuild as nearitem>
        <li>
            <a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">${nearitem['building_name']}</p>
                    <p class="cont-center"><span>${nearitem['district_name']}</span><span>${nearitem['area_name']}</span></p>
                    <h4 class="cont-last">均价：<em>${nearitem['average_price']}</em>/㎡</h4>
                </div>
            </a>
        </li>
    </#list>
    </#if>
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
<script type="text/javascript">

     <#assign ptCD0 = tradeline['buildingline']>
     <#assign ptCD1 = tradeline['arealine']>
     <#assign ptCD2 = tradeline['tradearealine']>
        // 基于准备好的dom，初始化echarts实例

        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '未来一周气温变化',
                subtext: '纯属虚构'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['楼盘价格','区域价格','商圈价格']
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis:  {
                type: 'category',
                boundaryGap: false,
                data: [<#list xlist as item >'${item}',</#list>]
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            series: [

            <#if (ptCD0?size<12)>
                {
                    name:'楼盘价格',
                    type:'scatter',
                    data:[[10,19]],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    }
                },

            <#else> {
                name:'楼盘价格',
                type:'line',
                data:[<#list ptCD0 as item >${item['price']},</#list>],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            </#if>
                {
                    name:'区域价格',
                    type:'line',
                    data:[<#list ptCD1 as item >${item['price']},</#list>],
                    markPoint: {
                        data: [
                            {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'},
                            [{
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最大值'
                                    }
                                },
                                type: 'max',
                                name: '最高点'
                            }]
                        ]
                    }
                },
                {
                    name:'商圈价格',
                    type:'line',
                    data:[<#list ptCD2 as item >${item['price']},</#list>],
                    markPoint: {
                        data: [
                            {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'},
                            [{
                                symbol: 'none',
                                x: '90%',
                                yAxis: 'max'
                            }, {
                                symbol: 'circle',
                                label: {
                                    normal: {
                                        position: 'start',
                                        formatter: '最大值'
                                    }
                                },
                                type: 'max',
                                name: '最高点'
                            }]
                        ]
                    }
                }
            ]
        };

        myChart.setOption(option);




    // 使用刚指定的配置项和数据显示图表。

</script>
