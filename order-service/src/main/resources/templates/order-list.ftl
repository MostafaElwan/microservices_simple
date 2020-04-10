<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Order List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Order List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/orders/add">Add Order</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                    <th>Order</th>
                    <th>Status</th>
                    <th>Comment</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list orders as order>
                    <tr>
                        <td><a href="${contextPath + '/orders/' + order.id}">${order.id}</a></td>
                        <td>${order.status}</td>
                        <td>${order.shortComment}</td>
                        <td>${order.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${order.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'orders?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'orders?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>