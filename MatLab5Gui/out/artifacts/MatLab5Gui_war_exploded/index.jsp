<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Лабораторная 5</title>
    <link rel="stylesheet" href="style.css" type="text/css">
  </head>
  <body>
  <script src="main.js"></script>
  <header>
    <h2>
      Ввод значений
    </h2>
  </header>

  <br>
  <div id="inputFile">
    Выберете способ ввода:
    <select id="select">
      <option value="1" selected>Из файла</option>
      <option value="2">Ввести таблицу</option>
      <option value="3">Выбрать функцию, задать интервал и количество точек</option>
    </select>
    <br>
    <input type="button" id="send" onclick="send()" value="Отправить">
  </div>

  </body>
</html>
