<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title><#if add>Create a Customer<#else>Edit a Customer</#if></title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1><#if add>Create a Customer:<#else>Edit a Customer:</#if></h1>
        <a href="${contextPath}/customers">Back to Customer List</a>
        <br/><br/>
        <#if add>
            <#assign urlAction>${contextPath}/customers/add</#assign>
            <#assign submitTitle>Create</#assign>
        <#else>
            <#assign urlAction>${contextPath + '/customers/' + customer.id + '/edit'}</#assign>
            <#assign submitTitle>Update</#assign>
        </#if>
        <form action="${urlAction}" name="customer" method="POST">
            <table border="0">
                <#if customer.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${customer.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Segment</td>
                    <td>:</td>
                    <td>
                    	<select name="selectedSegment">
                    		<option value="" selected disabled hidden>-- Select Segment --</option>
						    <#list segments as segment>
						        <option value="${segment.id}" <#if customer.segmentId == segment.id>selected</#if> >${segment.name}</option>
						    </#list>
						</select>
                    </td>              
                </tr>
                <tr>
                    <td>First Name</td>
                    <td>:</td>
                    <td><input type="text" name="firstName" value="${(customer.firstName)!''}" /></td>              
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td>:</td>
                    <td><input name="lastName" value="${(customer.lastName)!''}" /></td>              
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>:</td>
                    <td><input name="email" value="${(customer.email)!''}" /></td>              
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td><input name="age" value="${(customer.age)!''}" /></td>              
                </tr>
                <tr>
                    <td>Adress</td>
                    <td>:</td>
                    <td><textarea name="address" rows="4" cols="50">${(customer.address)!""}</textarea></td>
                </tr>
                <#if customer.createdAt??>
                <tr>
                    <td>Created At</td>
                    <td>:</td>
                    <td>${customer.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                <tr>
                    <td>Updated At</td>
                    <td>:</td>
                    <td>${customer.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>              
                </tr>
                </#if>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>
 
        <br/>
        <!-- Check if errorMessage is not null and not empty -->       
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>       
    </body>
</html>