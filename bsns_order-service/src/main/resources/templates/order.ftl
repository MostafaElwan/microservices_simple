<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Order</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View Order</h1>
        <a href="${contextPath}/orders">Back to Order List</a>
        <br/><br/>
        <#if order??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${order.id}</td>          
                </tr>
                <tr>
                    <td>Status</td>
                    <td>:</td>
                    <td>${order.status}</td>             
                </tr>
                <tr>
                    <td>Comment</td>
                    <td>:</td>
                    <td>${order.comment}</td>              
                </tr>
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
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/orders/' + order.id + '/delete'}" method="POST">
                    Delete this order? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/orders/' + order.id + '/edit'}">Edit</a> | 
                    <a href="${contextPath + '/orders/' + order.id + '/delete'}">Delete</a> | 
                    <a href="${contextPath}/orders/add">Add Another</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
