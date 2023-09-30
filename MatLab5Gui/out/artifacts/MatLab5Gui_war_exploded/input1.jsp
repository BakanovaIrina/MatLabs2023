<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ввод значений</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<header>
    <h1>
        Ввод из файла
    </h1>
</header>

<div>
    <input type="file" id="fileInput" accept=".txt" />
    <br>
    <button onclick="loadFile1()">Загрузить файл</button>
</div>

<script src="main.js"></script>
<script>
    function loadFile1() {
        var fileInput = document.getElementById('fileInput');
        var file = fileInput.files[0];

        var reader = new FileReader();

        reader.onload = function(e) {
            var content = e.target.result;
            sendFileInput(content)
        };

        reader.readAsText(file);

    }
</script>

</body>
</html>
