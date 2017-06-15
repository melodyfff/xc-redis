<%--
  Created by IntelliJ IDEA.
  User: xinchen
  Date: 2017/6/13
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/common/include.jsp" %>
<html>
<head>
    <title>Redis Main</title>
</head>
<body>
<div class="container">
    <div id="main" class="row">
        <table class="table table-hover">
            <thead>
            <tr><th>NAME</th></tr>
            </thead>
            <tbody id="tcontent">

            </tbody>
        </table>
    </div>
</div>
<script>
    $(document).ready(function(){
        $.get("${pageContext.request.contextPath}/redis/list", function(data){
            var tempHtml='';
            for(var i in data){
                tempHtml+='<tr><td class="goPage"><a href="${pageContext.request.contextPath}/redis/list/'+data[i]+'">'+data[i]+'</a></tr></td>';
            }
            $('#tcontent').append(tempHtml);
        });
    });
</script>
</body>
</html>
