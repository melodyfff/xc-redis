<%--
  Created by IntelliJ IDEA.
  User: xinchen
  Date: 2017/6/13
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redis Main</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
