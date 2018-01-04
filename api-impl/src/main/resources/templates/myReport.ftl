<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/404.css">
    <title>404</title>
</head>
<body>
<div class="page-empty">
    <div>
        <#if userReport?exists>
            <#list userReport as myReport>
                <ul>
                    <li>${myReport.id}</li>
                    <li>${myReport.phone}</li>
                    <#if myReport.downPayment?exists>
                        <li>${myReport.downPayment}</li>
                    </#if>
                    <#if myReport.monthPayment?exists>
                        <li>${myReport.monthPayment}</li>
                    </#if>
                    <#if myReport.userType?exists>
                        <li>${myReport.userType}</li>
                    </#if>
                    <#if myReport.totalPrice?exists>
                        <li>${myReport.totalPrice}</li>
                    </#if>
                    <#if myReport.layout?exists>
                        <li>${myReport.layout}</li>
                    </#if>
                    <#if myReport.downPayment?exists>
                        <li>${myReport.downPayment}</li>
                    </#if>
                    <#if myReport.hasChild?exists>
                        <li> ${(myReport.hasChild == '0')?string('无','有')}</li>
                    </#if>
                    <#if myReport.hasOldman?exists>
                        <li>${(myReport.hasOldman == '0')?string('无','有')}</li>
                    </#if>
                    <#if myReport.createTime?exists>
                        <li>${myReport.createTime}</li>
                    </#if>
                    <#if myReport.userPortrait?exists>
                        <li>${myReport.userPortrait}</li>
                    </#if>
                </ul>
            </#list>
        </#if>
    </div>
    <a href="javascript:history.go(-1)">返回</a>
</div>
</body>
</html>