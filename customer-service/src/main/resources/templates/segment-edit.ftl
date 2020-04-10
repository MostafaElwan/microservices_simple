<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><#if add>Create a Segment<#else>Edit a Segment</#if></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>	
    <body>
        <h1><#if add>Create a Segment:<#else>Edit a Segment:</#if></h1>
        <a href="${contextPath}/segments">Back to Segment List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>${contextPath}/segments/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${contextPath + '/segments/' + segment.id + '/edit'}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <form action="${urlAction}" name="segment" method="POST">
            <table border="0">
                <#if segment.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${segment.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td><input type="text" name="name" value="${(segment.name)!''}" /></td>              
                </tr>
                <tr>
                    <td>Description</td>
                    <td>:</td>
                    <td><textarea name="description" rows="4" cols="50">${(segment.description)!""}</textarea></td>
                </tr>
                <#if segment.createdAt??>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${segment.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${segment.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
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