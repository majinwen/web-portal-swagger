<!DOCTYPE html>
<html>
<head>
    <#include "staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/my-report.css?v=${staticversion}">
    <title>我的购房报告</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "StatisticsHeader.ftl">
</head>
<body>
<div class="report-page">
    <div class="module-bottom-fill"></div>
        <#if userReport?exists>
            <#list userReport as myReport>
                <div>
                    <div class="module-bottom-fill">
                        <div class="my-report-item">
                            <div class="report-item-content">
                                <a class="report-link-block" href="${router_city('/findhouse/showMyReport/'+myReport.id)}">
                                    <i class="item-report-icon"></i>
                                    <div class="item-report-title">
                                        <#if myReport['downPayment']?exists && myReport['downPayment']?number gt 0>
                                            <h3>首付${myReport.downPayment}万 购房报告</h3>
                                        <#elseif myReport['totalPrice']?exists && myReport['totalPrice']?number gt 0 >
                                            <h3>总价${myReport.totalPrice}万 购房报告</h3>
                                        <#else >
                                            <h3>懂房帝 购房报告</h3>
                                        </#if>
                                        <p>${myReport.createTime}</p>
                                    </div>
                                </a>
                                <i class="slide-icon-button"></i>
                            </div>
                            <ul class="more-menu">
                                <li class="examine-report">
                                    <a href="${router_city('/findhouse/showMyReport/'+myReport.id)}">
                                        <span>查看报告</span>
                                        <i></i>
                                    </a>
                                </li>
                                <li class="recommond-plot current">
                                    <span>推荐小区</span>
                                    <i></i>
                                </li>
                                <li class="cancel-collection" id="report_${myReport.id}">
                                    <span>取消收藏</span>
                                    <i></i>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="module-bottom-fill none">
                        <section>
                            <#assign json="${myReport.fhResult}"?eval />
                            <#list json as item>
                                <ul>
                                    <li><a class="list-item" href="${router_city('/xiaoqu/'+item.newcode?c+'.html')}">
                                        <div class="clear">
                                            <div class="list-item-img-box">
                                                <#if item.plotImage?exists>
                                                    <img src="${qiniuimage}/${item.plotImage?split(',')[0]}-tt400x300" alt="<#if item.projname?exists>${item.projname}</#if>">
                                                    <#else><img src="${staticurl}/images/global/tpzw_image.png" alt="暂无数据">
                                                </#if>
                                            </div>
                                            <div class="list-item-cont">
                                                <h3 class="cont-block-1"><span><#if item.projname?exists>${item.projname}<#else>暂无数据</#if></span></h3>
                                                <p class="cont-block-2 plot"><#if item.finishdate?exists>${item.finishdate?split("-")[0]}年建成<#else>暂无数据</#if></p>
                                                <p class="cont-block-3 distance"><i class="icon"></i>
                                                    <#if item.districtName?exists&&item.areaName?exists>
                                                    ${item.districtName}-${item.areaName}
                                                    <#else >
                                                        <#if item.districtName?exists>${item.districtName}</#if>
                                                        <#if item.areaName?exists>${item.areaName}</#if>
                                                    </#if>
                                                </p>
                                                <div class="cont-block-4 house-labelling gray">
                                                    <#if item.buildTags?exists>
                                                        <#assign json1=item.buildTags/>
                                                        <#list json1 as lage >
                                                            <#if lage?exists>
                                                                <#if lage_index lt 3>
                                                                    <#if lage==1><span>近地铁</span></#if>
                                                                    <#if lage==3><span>现房</span></#if>
                                                                    <#if lage==4><span>精装修</span></#if>
                                                                    <#if lage==5><span>花园洋房</span></#if>
                                                                    <#if lage==6><span>复式</span></#if>
                                                                    <#if lage==7><span>车位充足</span></#if>
                                                                    <#if lage==8><span>低密度</span></#if>
                                                                    <#if lage==9><span>强房企</span></#if>
                                                                    <#if lage==10><span>优质物业</span></#if>
                                                                    <#if lage==11><span>购物方便</span></#if>
                                                                    <#if lage==12><span>教育配套</span></#if>
                                                                    <#if lage==13><span>医疗配套</span></#if>
                                                                    <#if lage==14><span>换手率高</span></#if>
                                                                    <#if lage==15><span>租金月供比高</span></#if>
                                                                    <#if lage==16><span>五证齐全</span></#if>
                                                                    <#if lage==17><span>人车分流</span></#if>
                                                                </#if>
                                                            </#if>
                                                        </#list>
                                                    </#if>
                                                 </div>
                                                <div class="cont-block-price plot">
                                                    <em><#if item.esfPrice?exists>${item.esfPrice}元/㎡<#else>暂无数据</#if></em>
                                                </div>
                                            </div>
                                        </div>
                                    </a></li>
                                </ul>
                            </#list>
                        </section>
                    </div>
                </div>
            </#list>
        </#if>
    <#if message?exists> <p style="color: red">${message}</p></#if>
</div>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script>
    $('.slide-icon-button').on('click', function () {
        $(this).parents('.report-item-content').toggleClass('animate-left');
        $(this).parents('.report-item-content').next('.more-menu').toggleClass('animate-left');
        $(this).parents('.module-bottom-fill').next('.module-bottom-fill').addClass('none');
    });
    $('.recommond-plot').on('click', function () {
        $(this).toggleClass('current');
        $(this).parents('.module-bottom-fill').next('.module-bottom-fill').toggleClass('none');
    });

    $(".cancel-collection").on('click', function () {
        var id=$(this).attr("id").split("_")[1];
        console.log(id);
        $.ajax({
            type: "GET",
            async: true,
            url: router_city('/findhouse/cancleMyReport/')+id,
            dataType: "json",
            success: function (data) {

                if (data.code == "success") {
                    //重定向到登录页面
                    window.location.href ="${backUrl}";
                }
                if(data.code="fail"){
                    //取下失败给个提示

                }
            }
        });
    });
    function router_city(urlparam) {
        urlparam = urlparam || '';
        if (urlparam[0] != '/') {
            urlparam = '/' + urlparam
        }
        var uri = new URI(window.location.href);
        var segmens = uri.segment();
        var city = '';
        if (segmens.length > 0) {
            city = '/' + segmens[0]
        }
        return city + urlparam
    }


</script>
</body>
</html>