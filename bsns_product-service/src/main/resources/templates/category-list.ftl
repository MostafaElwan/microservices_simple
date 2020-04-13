<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Category List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Category List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/categories/add">Add Category</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list categories as category>
                    <tr>
                        <td><a href="${contextPath + '/categories/' + category.id}">${category.name}</a></td>
                        <td>${category.shortDescription}</td>
                        <td>${category.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${category.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'categories?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'categories?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>