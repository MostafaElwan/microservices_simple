<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Customer List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Customer List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/customers/add">Add Customer</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                	<th>Segment</th>
                    <th>Name</th>
                    <th>E-Mail</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list customers as customer>
                    <tr>
                    	<td><a href="${contextPath + '/segments/' + customer.segment.id}">${customer.segment.name }</a></td>
                        <td><a href="${contextPath + '/customers/' + customer.id}">${customer.firstName + ' ' +  customer.lastName }</a></td>
                        <td>${customer.email !""}</td>
                        <td>${customer.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${customer.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'customers?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'customers?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>