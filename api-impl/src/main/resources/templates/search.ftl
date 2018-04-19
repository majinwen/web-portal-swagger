<div class="search-page-wrapper none">
    <div style="position: absolute; width: 100%; height: 100%; overflow-y: auto">
        <header class="searchpage-header">
            <div class="searchpage-search-box">
                <#--<div class="type-tab-box none">-->
                    <#--<p class="searchpage-current-item">二手房</p>-->
                    <#--<div class="type-menu-box">-->
                        <#--<i class="triangle-top"></i>-->
                        <#--<div class="type-menu">-->
                            <#--<span id="nhouse" data-value="${router_city('/loupan')}?keyword=">新房</span>-->
                            <#--<span id="erhouse" data-value="${router_city('/esf')}?keyword=" class="current">二手房</span>-->
                            <#--<span id="plot" data-value="${router_city('/xiaoqu')}?keyword=">小区</span>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="searchpage-search-content">
                    <i class="icon"></i>
                    <form action="" onsubmit="return false;">
                        <input type="search" placeholder="" class="key-words" value="<#if RequestParameters.keyword??>${RequestParameters.keyword}</#if>">
                    </form>
                </div>
            </div>
            <button type="button" class="searchpage-search-btn">取消</button>
        </header>

        <ul id="indexWord" class="index-word"></ul>
        <ul id="automatedWord" class="automated-word"></ul>
        <div id="search-container-wrapper">
            <#--index start-->
            <div id="search-index" class="search-container-item none">

                <div class="module-bottom-fill hot-recommend-index none" id="index-hot-recommend">
                    <#--<section>-->
                        <#--<div class="searchpage-content-header">-->
                            <#--<h3>热门推荐</h3>-->
                            <#--&lt;#&ndash;<a href="javascript:;" class="refresh-icon"></a>&ndash;&gt;-->
                        <#--</div>-->
                        <#--<div class="searchpage-hot-recommend clear" >-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}/11122242.html">梵悦·108</a>&ndash;&gt;-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}?keyword=北京壹号院">北京壹号院</a>&ndash;&gt;-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}?keyword=保利首开·天誉">保利首开·天誉</a>&ndash;&gt;-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}?keyword=首创·天阅西山">首创·天阅西山</a>&ndash;&gt;-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}?keyword=翡翠公园">翡翠公园</a>&ndash;&gt;-->
                            <#--&lt;#&ndash;<a href="${router_city('/loupan')}?keyword=北京壹号庄园">北京壹号庄园</a>&ndash;&gt;-->
                        <#--</div>-->
                    <#--</section>-->
                </div>


                <section>
                    <div class="searchpage-content-header">
                        <h3>历史记录</h3>
                        <a href="javascript:;" class="clear-icon"></a>
                    </div>
                    <div class="searchpage-history"></div>
                </section>
            </div>

            <#--newhouse start-->
            <div id="search-newhouse" class="search-container-item none">
                <section>
                    <div class="searchpage-content-header">
                        <h3>历史记录</h3>
                        <a href="javascript:;" class="clear-icon"></a>
                    </div>
                    <div class="searchpage-history"></div>
                </section>
            </div>

            <#--esf start-->
            <div id="search-erhouse" class="search-container-item none">
                <section>
                    <div class="searchpage-content-header">
                        <h3>历史记录</h3>
                        <a href="javascript:;" class="clear-icon"></a>
                    </div>
                    <div class="searchpage-history"></div>
                </section>
            </div>
            <#--esf end-->

            <#--plot start-->
            <div id="search-plot" class="search-container-item none">
                <section>
                    <div class="searchpage-content-header">
                        <h3>历史记录</h3>
                        <a href="javascript:;" class="clear-icon"></a>
                    </div>
                    <div class="searchpage-history"></div>
                </section>
            </div>
            <#--plot end-->

        </div>
    </div>
</div>
<script src="${staticurl}/js/search-localStorage.js?v=${staticversion}"></script>
