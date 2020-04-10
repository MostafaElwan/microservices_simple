<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Category</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View Category</h1>
        <a href="${contextPath}/categories">Back to Category List</a>
        <br/><br/>
        <#if category??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${category.id}</td>          
                </tr>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td>${category.name}</td>             
                </tr>
                <tr>
                    <td>Description</td>
                    <td>:</td>
                    <td>${category.description}</td>              
                </tr>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${category.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${category.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/categories/' + category.id + '/delete'}" method="POST">
                    Delete this category? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/categories/' + category.id + '/edit'}">Edit</a> | 
                    <a href="${contextPath + '/categories/' + category.id + '/delete'}">Delete</a> | 
                    <a href="${contextPath}/categories/add">Add Another</a> |
                    <a href="${contextPath + '/products/category/' + category.id}">Show Products</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
