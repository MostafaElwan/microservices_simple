<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>${Session.title}</title>
        <link rel="stylesheet" type="text/css" href="${Session.contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>
        ${Session.title}
        <img src="${Session.contextPath}/images/redisdb.png" alt="REdis DB" style="width:200px;height:80px;">
        </h1>
        
        <a href="${Session.productContextPath}/">Product Admin</a> |
        <a href="${Session.customerContextPath}/">Customer Admin</a> |
        <a href="${Session.orderContextPath}/">Order Admin</a>
        <br/><br/>
        
        <#assign urlAction>${Session.contextPath}/customers/email</#assign>
        <form action="${urlAction}" name="customer" method="POST">
	        <table>
	        	<tr>
		            <td>Customer E-Mail</td>
		            <td>:</td>
	            	<td><input type="text" name="email" value="${(session.customer.email)!''}" /></td>
		        </tr>
		        <tr>
		        	<td span='3'> 
		        		<input type="submit" value="NEXT" /> 
		        	</td>
		        </tr>
	        </table>
        </form>
        <br/><br/>
        <div>Copyright © Elw@n</div>
    </body>
</html>