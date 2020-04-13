<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>ShoppingCart List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>ShoppingCart List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/shoppingCarts/add">Add ShoppingCart</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table bshoppingCart="1">
                <tr>
                    <th>ShoppingCart</th>
                    <th>Customer</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list shoppingCarts as shoppingCart>
                    <tr>
                        <td><a href="${contextPath + '/shoppingCarts/' + shoppingCart.id}">${shoppingCart.id}</a></td>
                        <td>${shoppingCart.customerId}</td>
                        <td>${shoppingCart.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${shoppingCart.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'shoppingCarts?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'shoppingCarts?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>