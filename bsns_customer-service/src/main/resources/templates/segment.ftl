<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Segment</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View Segment</h1>
        <a href="${contextPath}/segments">Back to Segment List</a>
        <br/><br/>
        <#if segment??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${segment.id}</td>          
                </tr>
                <tr>
                    <td>Name</td>
                    <td>:</td>
                    <td>${segment.name}</td>             
                </tr>
                <tr>
                    <td>Description</td>
                    <td>:</td>
                    <td>${segment.description}</td>             
                </tr>
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
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/segments/' + segment.id + '/delete'}" method="POST">
                    Delete this segment? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/segments/' + segment.id + '/edit'}">Edit</a> |
                    <a href="${contextPath + '/segments/' + segment.id + '/delete'}">Delete</a> |
                    <a href="${contextPath}/segments/add">Add Another</a> |
                    <a href="${contextPath + '/customers/segment/' + segment.id}">Show Customers</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
