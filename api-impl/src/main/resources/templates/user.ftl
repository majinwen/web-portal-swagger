<#-- 个人中心 侧栏菜单 -->
<section class="side-nav-cont" xmlns="http://www.w3.org/1999/html">
    <div class="side-user">
        <img id="click_login" src="${staticurl}/images/global/grcl_tx.png" alt="用户头像">
        <p>
        <#if getUser()?exists>
           ${getUser()}
        <#else >
        </#if>
        </p>
    </div>
    <div class="side-nav-item-wrapper">
        <ul class="side-nav-item item-link">
            <li><a href="${router_city()}"><i class="icon-index"></i><span>首页</span></a></li>
            <li><a href="${router_city('/esf')}"><i class="icon-esf"></i><span>找二手房</span></a></li>
            <li><a href="${router_city('/xiaoqu')}"><i class="icon-plot"></i><span>找小区</span></a></li>
            <li><a href="${router_city('/xinfang')}"><i class="icon-new"></i><span>找新房</span></a></li>
        </ul>
        <ul class="side-nav-item item-my">
            <#--<li><a href="#"><i class="icon-collect"></i><span>我的收藏</span></a></li>-->
            <li><a href="#"><i class="icon-report"></i><span>我的报告</span></a></li>
            <li><a id="out_login" href="#"><i class="icon-exit"></i><span>注销</span></a></li>
        </ul>
    </div>
    <div class="side-house-intelligent">
        <a href="#"><em>智能找房</em></a>
    </div>
</section>
<div class="scroll-mask"></div>

<script>
    $("#click_login").click(function () {

    <#if (getUser()=="请登录")>
        window.location.href = "/user/login";
    <#else >
    </#if>
    });
    //注销功能
    $("#out_login").click(function () {
      <#if (getUser()!="请登录")>
        window.location.href = "/user/logout?phone=" +${getUser()};
      <#else >
      </#if>
    });
</script>