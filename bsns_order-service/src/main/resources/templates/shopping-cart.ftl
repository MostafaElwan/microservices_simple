<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View ShoppingCart</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View ShoppingCart</h1>
        <a href="${contextPath}/shoppingCarts">Back to ShoppingCart List</a>
        <br/><br/>
        <#if shoppingCart??>
            <table bshoppingCart="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${shoppingCart.id}</td>          
                </tr>
                <tr>
                    <td>Customer</td>
                    <td>:</td>
                    <td>${shoppingCart.customerId}</td>             
                </tr>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${shoppingCart.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${shoppingCart.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/shoppingCarts/' + shoppingCart.id + '/delete'}" method="POST">
                    Delete this shoppingCart? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/shoppingCarts/' + shoppingCart.id + '/delete'}">Delete</a> | 
                    <a href="${contextPath + '/shoppingCarts/' + shoppingCart.id + '/details'}">Show Details</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
