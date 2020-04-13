<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Shopping Cart List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Shopping Cart Item List</h1>
        
        <br/><br/>
        <div>
            <table bshoppingCart="1">
                <tr>
                    <th>Shopping Cart Item</th>
                    <th>Product</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list items as item>
                    <tr>
                        <td><a href="${contextPath + '/shoppingCarts/details/' + item.id}">${item.id}</a></td>
                        <td>${item.productId}</td>
                        <td>${item.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${item.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>
					        <form action="${contextPath + '/shoppingCarts/' + item.cart.id + '/details/' + item.id + '/delete'}" method="POST">
			                    <input type="image" src="${contextPath}/images/remove-from-cart.png" border="0" alt="Submit" style="width:40px;height:40px;"/>
			                </form>
                        </td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
    </body>
</html>