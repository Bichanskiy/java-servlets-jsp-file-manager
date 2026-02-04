<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
    <h1>Вход в систему</h1>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="login" method="post">
        Логин: <input type="text" name="login" required><br><br>
        Пароль: <input type="password" name="password" required><br><br>
        <input type="submit" value="Войти">
    </form>

    <p><a href="register">Регистрация</a></p>
</body>
</html>