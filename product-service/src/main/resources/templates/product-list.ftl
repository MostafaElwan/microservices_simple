<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Product List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Product List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/products/add">Add Product</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                	<th>Category</th>
                    <th>Product</th>
                    <th>Description</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list products as product>
                    <tr>
                    	<td><a href="${contextPath + '/categories/' + product.category.id}">${product.category.name}</a></td>
                        <td><a href="${contextPath + '/products/' + product.id}">${product.name}</a></td>
                        <td>${product.shortDescription}</td>
                        <td>${product.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${product.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'products?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'products?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>