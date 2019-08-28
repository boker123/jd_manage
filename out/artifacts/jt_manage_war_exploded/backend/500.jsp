<%--
  Created by IntelliJ IDEA.
  User: Boker
  Date: 2019/8/14
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <meta charset="utf-8">
    <title>500</title>
</head>
<body>
    <h1 style="color:blue; margin:10px 15px">
        服务器内部错误:
        <%= exception.getMessage() %>
    </h1>
</body>
</html>
