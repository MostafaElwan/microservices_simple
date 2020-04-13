<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>View Customer</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>View Customer</h1>
        <a href="${contextPath}/customers">Back to Customer List</a>
        <br/><br/>
        <#if customer??>
            <table border="0">
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${customer.id}</td>          
                </tr>
                <tr>
                    <td>Segment</td>
                    <td>:</td>
                    <td>${customer.segment.name}</td>          
                </tr>
                <tr>
                    <td>First Name</td>
                    <td>:</td>
                    <td>${customer.firstName}</td>             
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td>:</td>
                    <td>${customer.lastName}</td>              
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>:</td>
                    <td>${customer.email !""}</td>              
                </tr>
                <tr>
                    <td>Age</td>
                    <td>:</td>
                    <td>${customer.age}</td>              
                </tr>
                <tr>
                    <td>Address</td>
                    <td>:</td>
                    <td>${customer.address}</td>             
                </tr>
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
            </table>
            <br/><br/>
            <#if allowDelete>
                <form action="${contextPath + '/customers/' + customer.id + '/delete'}" method="POST">
                    Delete this customer? <input type="submit" value="Yes" />
                </form>
            <#else>
                <div>
                    <a href="${contextPath + '/customers/' + customer.id + '/edit'}">Edit</a> |
                    <a href="${contextPath + '/customers/' + customer.id + '/delete'}">Delete</a> |
                    <a href="${contextPath}/customers/add">Add Another</a>
                </div>
            </#if>
        </#if>
        <#if errorMessage?has_content>
            <div class="error">${errorMessage}</div>
        </#if>
    </body>
</html>
