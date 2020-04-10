<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Segment List</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Segment List</h1>
        
        <div>
            <nobr>
                <a href="${contextPath}/segments/add">Add Segment</a> |
                <a href="${contextPath}/">Back to Index</a>
            </nobr>
        </div>
        <br/><br/>
        <div>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                </tr>
                <#list segments as segment>
                    <tr>
                        <td><a href="${'segments/' + segment.id}">${segment.name }</a></td>
                        <td>${segment.shortDescription} ...</a></td>
                        <td>${segment.createdAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                        <td>${segment.updatedAt?string("dd-MM-yyyy HH:mm:ss")}</td>
                    </tr>
                </#list>
            </table>          
        </div>
        <br/><br/>
        <div>
            <nobr>
                <#if hasPrev><a href="${'segments?page=' + prev}">Prev</a>&nbsp;&nbsp;&nbsp;</#if>
                <#if hasNext><a href="${'segments?page=' + next}">Next</a></#if>
            </nobr>
        </div>
    </body>
</html>