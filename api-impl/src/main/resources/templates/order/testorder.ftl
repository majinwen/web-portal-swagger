<!DOCTYPE html>
<html>
<head>

    <title>充值</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-purchase.css?v=${staticversion}">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
</head>
<body>
<div class="detail-contact-wrapper">

    <div class="detail-contact-content">
        <a id="buildCommodityOrder" >生成商品购买订单</a>
    </div>

</div>
</body>
</html>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>

<script>

    $("#buildCommodityOrder").click(function(){
        var url = router_city('/order')+"/buildCommodityOrder";
        console.log(url)
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify({productNo: "2", buildingId:"11122299"}),
            async: false,
            contentType:"application/json",
            success: function (data) {
                console.log(JSON.stringify(data))
            }
        });
    });
</script>