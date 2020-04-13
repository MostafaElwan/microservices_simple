<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Product List</title>
        <link rel="stylesheet" type="text/css" href="${Session.contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Product List</h1>
        
        <div>
            <nobr>
            	<#if Session.customer??>
                	Hi <a href="${Session.contextPath + '/customers/' + Session.customer.id}">${Session.customer.name}</a>, you can check your <a href="${Session.contextPath}/show/cart/customer/${Session.customer.id}">Shopping Cart</a> from here.
                </#if>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border=1>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Description</th>
                </tr>
                <#if Session.products??>
	                <#list Session.products as product>
	                    <tr>
	                        <td><a href="${Session.contextPath + '/products/' + product.id}">${product.name}</a></td>
	                        <td>${product.price}</td>
	                        <td>${product.shortDescription}</td>
	                        <td>
	                        	<form action="${Session.contextPath + '/cart/add/' + product.id}" method="POST">
							        <input type="image" src="${Session.contextPath}/images/add-to-cart.png" border="0" alt="Submit" style="width:50px;height:50px;"/>
						        </form>
	                        </td>
	                    </tr>
	                </#list>
                </#if>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${Session.contextPath + '/products/all?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${Session.contextPath + '/products/all?page=' + next}">Next</a></#if>
            </nobr>
        </div>
        <!-- Check if errorMessage is not null and not empty -->
        <br/><br/>       
        <#if infoMsg??>
            <div class="info">${infoMsg}</div>
        </#if>
    </body>
</html>