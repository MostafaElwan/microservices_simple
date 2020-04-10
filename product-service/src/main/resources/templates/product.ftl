<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Product</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View Product</h1>
        <a href="${contextPath}/products">Back to Product List</a>
        <br/><br/>
        <#if product??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${product.id}</td>          
                </tr>
                <tr>
                    <td>Category</td>
                    <td>:</td>
                    <td>${product.category.name}</td>          
                </tr>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td>${product.name}</td>             
                </tr>
                <tr>
                    <td>Description</td>
                    <td>:</td>
                    <td>${product.description}</td>              
                </tr>
                <tr>
                    <td>Price</td>
                    <td>:</td>
                    <td>${product.price}</td>             
                </tr>
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
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/products/' + product.id + '/delete'}" method="POST">
                    Delete this product? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/products/' + product.id + '/edit'}">Edit</a> | 
                    <a href="${contextPath + '/products/' + product.id + '/delete'}">Delete</a> | 
                    <a href="${contextPath}/products/add">Add Another</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
