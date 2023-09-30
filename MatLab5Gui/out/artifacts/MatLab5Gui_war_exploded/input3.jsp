<%--
  Created by IntelliJ IDEA.
  User: Irina
  Date: 20.09.2023
  Time: 23:15
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
        Ввод функцией
    </h1>
</header>

<br>
<div>
    Выберете функцию из представленных:
    <select id="selectFunc">
        <option value="1" selected>x^2-x+2</option>
        <option value="2" selected>e^x</option>
        <option value="3" selected>x^2</option>
    </select>
</div>

<div>
    Введите значения границ исследуемых интервалов:
    <br>
</div>

<div>
    <input type="text" id = "a" required placeholder="a">
</div>

<div>
    <input type="text" id = "b" required placeholder="b">
</div>

<div>
    Введите количество точек на интервале:
    <br>
</div>

<div>
    <input type="text" id = "countDots" required>
</div>
<br>

<div>
    Введите значение для искомого x:
    <br>
</div>

<div>
    <input type="text" id = "x_val1" required>
</div>

<div>
    <input type="button" id="send1" onclick="sendFuncDots()" value="Отправить">
</div>
</body>
</html>
