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
<script src="canvas.js"></script>

<header>
  <h1>
    Ввод по умолчанию
  </h1>
</header>

<div id="input">
  <br>
  Выберете уравнение
  <select id="select">
    <option value="1" selected>y' = y + (1 + x) * y ^2</option>
    <option value="2">y' = 2x</option>
    <option value="3">y' = x^2 - 2x</option>
  </select>
  <br>
</div>

<br>
<div>
  Введите значение x0
  <br>
</div>

<div>
  <input type="text" id = "x0" required>
</div>

<br>
<div>
  Введите значение xn
  <br>
</div>

<div>
  <input type="text" id = "xn" required>
</div>

<br>
<div>
  Введите значение y0(x0)
  <br>
</div>

<div>
  <input type="text" id = "y0" required>
</div>

<br>
<div>
  Введите значение h
  <br>
</div>

<div>
  <input type="text" id = "h" required>
</div>

<br>
<div>
  Введите значение eps
  <br>
</div>

<div>
  <input type="text" id = "eps" required>
</div>

<div>
  <input type="button" id="send1" onclick="sendInput()" value="Отправить">
</div>
</body>
</html>
