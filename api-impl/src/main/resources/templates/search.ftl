<div class="search-page-wrapper">
    <header class="searchpage-header">
        <div class="searchpage-search-box">
            <div class="type-tab-box none">
                <p class="searchpage-current-item"></p>
                <div class="type-menu-box">
                    <i class="triangle-top"></i>
                    <div class="type-menu">
                        <span data-value="/newhouse/searchNewHouse?keywords=" class="current">新房</span>
                        <span data-value="/queryBySearchBox?text=">二手房</span>
                        <span data-value="/findVillageByConditions?rc=">小区</span>
                        <#--<span data-value="/newhouse/searchNewHouse?keywords=" <#if searchType=="newhouse">class="current"</#if>>新房</span>
                        <span data-value="/queryBySearchBox?text=" <#if searchType=="projhouse">class="current"</#if>>二手房</span>
                        <span data-value="/findVillageByConditions?rc=" <#if searchType=="plot">class="current"</#if>>小区</span>-->
                    </div>
                </div>
            </div>
            <div class="searchpage-search-content">
                <i class="icon"></i>
                <input type="text" placeholder="中骏·西山天璟" class="key-words">
            </div>
        </div>
            <button type="button" class="searchpage-search-btn">取消</button>
    </header>
    <div class="module-bottom-fill">
        <section>
            <div class="searchpage-content-header">
                <h3>热门推荐</h3>
                <a href="javascript:;" class="refresh-icon"></a>
            </div>
            <div class="searchpage-hot-recommend clear">
                <a href="#">峨眉时光</a>
                <a href="#">西宸原著</a>
                <a href="#">一渡龙湾</a>
                <a href="#">潮白河孔雀英国宫</a>
                <a href="#">融创千章墅</a>
                <a href="#">润泽御府</a>
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

<script src="${staticurl}/js/search-localStorage.js"></script>


