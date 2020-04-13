<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><#if add>Create a Order<#else>Edit a Order</#if></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1><#if add>Create a Order:<#else>Edit a Order:</#if></h1>
        <a href="${contextPath}/orders">Back to Order List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>${contextPath}/orders/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${contextPath + '/orders/' + order.id + '/edit'}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <form action="${urlAction}" name="order" method="POST">
            <table border="0">
                <#if order.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${order.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Status</td>
                    <td>:</td>
                    <td>${order.status}</td>              
                </tr>
                <tr>
                    <td>Comment</td>
                    <td>:</td>
                    <td><textarea name="comment" rows="4" cols="50">${(order.comment)!""}</textarea></td>
                </tr>
                <#if order.createdAt??>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${order.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${order.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                </#if>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>
 
        <br/>
        <!-- Check if errorMessage is not null and not empty -->       
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>       
    </body>
</html>