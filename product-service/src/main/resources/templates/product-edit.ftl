<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><#if add>Create a Product<#else>Edit a Product</#if></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1><#if add>Create a Product:<#else>Edit a Product:</#if></h1>
        <a href="${contextPath}/products">Back to Product List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>${contextPath}/products/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${contextPath + '/products/' + product.id + '/edit'}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <form action="${urlAction}" name="product" method="POST">
            <table border="0">
                <#if product.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${product.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Category</td>
                    <td>:</td>
                    <td>
                    	<select name="selectedCategory">
                    		<option value="" selected disabled hidden>-- Select Category --</option>
						    <#list categories as category>
						        <option value="${category.id}" <#if product.categoryId == category.id>selected</#if> >${category.name}</option>
						    </#list>
						</select>
                    </td>              
                </tr>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td><input type="text" name="name" value="${(product.name)!''}" /></td>              
                </tr>
                <tr>
                    <td>Price</td>
                    <td>:</td>
                    <td><input name="price" value="${(product.price)!''}" /></td>              
                </tr>
                <tr>
                    <td>Description</td>
                    <td>:</td>
                    <td><textarea name="description" rows="4" cols="50">${(product.description)!""}</textarea></td>
                </tr>
                <#if product.createdAt??>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${product.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${product.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
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