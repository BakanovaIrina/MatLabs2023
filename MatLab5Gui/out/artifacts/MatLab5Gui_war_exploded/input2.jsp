<%--
  Created by IntelliJ IDEA.
  User: Irina
  Date: 15.09.2023
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ввод значений</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<script src="main.js"></script>

<header>
    <h1>
        Ввод "ручками"
    </h1>
</header>

<br>
<div>
    Введите значения аргументов через пробел
    <br>
</div>

<div>
    <input type="text" id = "x" required>
</div>

<div>
    Введите значения функций через пробел
    <br>
</div>

<div>
    <input type="text" id = "y" required>
</div>
<br>

<div>
    Введите значение для искомого x:
    <br>
</div>

<div>
    <input type="text" id = "x_val" required>
</div>

<div>
    <input type="button" id="send1" onclick="sendInput()" value="Отправить">
</div>
</body>
</html>
