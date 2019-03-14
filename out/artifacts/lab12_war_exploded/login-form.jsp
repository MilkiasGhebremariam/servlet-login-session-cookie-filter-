<%--
  Created by IntelliJ IDEA.
  User: milkias
  Date: 2019-03-14
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOG~IN</title>
</head>
<body>
<form action="/login" method="post">
    <p> UserName : <input name="user_name" type="text" value=${uname}></p>
    <p> Password : <input name="password" type="password" ></p>
    <label><input type="checkbox" name="rememberme"  ${rememberme} />Remember me?</label>
    <button type="submit">LOG IN </button>

</form>
</body>
</html>
