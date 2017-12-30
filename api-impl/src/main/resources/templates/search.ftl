<div class="search-page-wrapper">
    <header class="searchpage-header">
        <div class="searchpage-search-box">
            <div class="type-tab-box none">
                <p class="searchpage-current-item">二手房</p>
                <div class="type-menu-box">
                    <i class="triangle-top"></i>
                    <div class="type-menu">
                        <span id="nhouse" data-value="${router_city('/loupan')}?keyword=">新房</span>
                        <span id="erhouse" data-value="${router_city('/esf')}?keyword=" class="current">二手房</span>
                        <span id="plot" data-value="${router_city('/xiaoqu')}?keyword=">小区</span>
                    </div>
                </div>
            </div>
            <div class="searchpage-search-content">
                <i class="icon"></i>
                <input type="text" placeholder="中骏·西山天璟" class="key-words" value="<#if RequestParameters.keyword??>${RequestParameters.keyword}</#if>">
            </div>
        </div>
            <button type="button" class="searchpage-search-btn">取消</button>
    </header>
    <div id="search-container-wrapper">
        <#--newhouse start-->
        <div id="search-newhouse" class="search-container-item none">
            <div class="module-bottom-fill">
                <section>
                    <div class="searchpage-content-header">
                        <h3>热门推荐</h3>
                        <a href="javascript:;" class="refresh-icon"></a>
                    </div>
                    <div class="searchpage-hot-recommend clear">
                        <a href="${router_city('/loupan')}?keyword=东亚尚院">东亚尚院</a>
                        <a href="${router_city('/loupan')}?keyword=西宸原著">西宸原著</a>
                        <a href="${router_city('/loupan')}?keyword=一渡龙湾">一渡龙湾</a>
                        <a href="${router_city('/loupan')}?keyword=潮白河孔雀英国宫">潮白河孔雀英国宫</a>
                        <a href="${router_city('/loupan')}?keyword=融创千章墅">融创千章墅</a>
                        <a href="${router_city('/loupan')}?keyword=润泽御府">润泽御府</a>
                    </div>
                </section>
            </div>
            <section>
                <div class="searchpage-content-header">
                    <h3>历史记录</h3>
                    <a href="javascript:;" class="clear-icon"></a>
                </div>
                <div class="searchpage-history"></div>
            </section>
        </div>
        <#--newhouse end-->

        <#--esf start-->
        <div id="search-erhouse" class="search-container-item none">
            <div class="module-bottom-fill">
                <section>
                    <div class="searchpage-content-header">
                        <h3>热门推荐</h3>
                        <a href="javascript:;" class="refresh-icon"></a>
                    </div>
                    <div class="searchpage-hot-recommend clear">
                        <a href="${router_city('/esf')}?keyword=华夏奥韵">华夏奥韵</a>
                        <a href="${router_city('/esf')}?keyword=英嘉花园">英嘉花园</a>
                        <a href="${router_city('/esf')}?keyword=金芳公寓">金芳公寓</a>
                        <a href="${router_city('/esf')}?keyword=盛华苑">盛华苑</a>
                        <a href="${router_city('/esf')}?keyword=寰太大厦">寰太大厦</a>
                        <a href="${router_city('/esf')}?keyword=椿树馆小区">椿树馆小区</a>
                    </div>
                </section>
            </div>
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
            <div class="module-bottom-fill">
                <section>
                    <div class="searchpage-content-header">
                        <h3>热门推荐</h3>
                        <a href="javascript:;" class="refresh-icon"></a>
                    </div>
                    <div class="searchpage-hot-recommend clear">
                        <a href="${router_city('/xiaoqu')}?keyword=华夏奥韵">华夏奥韵</a>
                        <a href="${router_city('/xiaoqu')}?keyword=英嘉花园">英嘉花园</a>
                        <a href="${router_city('/xiaoqu')}?keyword=金芳公寓">金芳公寓</a>
                        <a href="${router_city('/xiaoqu')}?keyword=盛华苑">盛华苑</a>
                        <a href="${router_city('/xiaoqu')}?keyword=寰太大厦">寰太大厦</a>
                        <a href="${router_city('/xiaoqu')}?keyword=椿树馆小区">椿树馆小区</a>
                    </div>
                </section>
            </div>
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

<script src="${staticurl}/js/search-localStorage.js"></script>


