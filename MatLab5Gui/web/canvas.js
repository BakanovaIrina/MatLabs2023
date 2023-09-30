let xMin, xMax, yMin, yMax;
let width, height;
function drawCanvas() {
    let canvas = document.getElementById("canvas");
    let context = canvas.getContext("2d");
    canvas.width = 400;
    canvas.height = 400;

    width = 400;
    height = 400;

    context.fillStyle = "#FFFFFF"

    //Оси
    context.beginPath();
    context.lineWidth = 2;
    context.strokeStyle = "#000000";

    context.moveTo(0, canvas.height);
    context.lineTo(0, 0);

    context.moveTo(canvas.width, canvas.height);
    context.lineTo(0, canvas.height);

    context.moveTo(canvas.width, canvas.height);
    context.lineTo(canvas.width - 15,  canvas.height - 5);

    context.moveTo(0, 0);
    context.lineTo(5, 15)

    context.stroke();
    context.closePath();

    drawAxes();
}

function drawAxes(){
    let canvas = document.getElementById("canvas");
    let context = canvas.getContext("2d");
    let x = Array.from(document.getElementsByClassName("xs")).map(v => v.innerHTML);
    xMin = Math.min.apply(null, x) - 1;
    xMax = Math.max.apply(null, x) + 1;

    let newX = parseFloat(document.getElementById("newX").value);
    let newY = parseFloat(document.getElementById("newY").value);

    let y = Array.from(document.getElementsByClassName("ys")).map(v => v.innerHTML);
    yMin = Math.min.apply(null, y) - 1;
    yMax = Math.max.apply(null, y) + 1;

    if(newX + 1 > xMax){
        xMax = newX + 1;
    }
    if(newX - 1 < xMin){
        xMin = newX - 1;
    }

    if(newY + 1 > yMax){
        yMax = newY + 1;
    }
    if(newY - 1 < yMin){
        yMin = newY - 1;
    }

    context.lineWidth = 2;
    context.strokeStyle = "#000000";
    context.font='10pt Arial'

    context.stroke();
    context.closePath();

    drawNewton()
    drawDots(x, y, 0)
    drawRawPoint(newX, newY, 3)
}

function drawDots(Xs, Ys, id_color){
    for (let i = 0; i < Xs.length; i++) {
        drawRawPoint(Xs[i], Ys[i], id_color)
    }
}

function drawRawPoint(x, y, id_color) {
    if(x > xMax + width / 10000 || x < xMin - width / 10000 || y > yMax + height / 10000 || y < yMin - height / 10000){
        return;
    }

    if(x < 0 && y > 0){
        drawPoint(Math.abs(x) * 300 / (xMax - xMin), Math.abs(y) * 300 / (yMax - yMin), id_color);
    }
    if(x > 0 && y > 0){
        drawPoint(x * 300 / (xMax - xMin), 400 - y * 300 / (yMax - yMin), id_color);
    }

    if(x < 0 && y < 0){
        drawPoint(400 - Math.abs(x) * 300 / (xMax - xMin), Math.abs(y) * 300 / (yMax - yMin), id_color);
    }

    if(x > 0 && y < 0){
        drawPoint(x * 300 / (xMax - xMin), Math.abs(y) * 300 / (yMax - yMin), id_color);
    }
}

function drawPoint(x, y, id_color) {
    let canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d");

    switch (id_color){
        case 0:
            context.fillStyle = "#000000";
            context.strokeStyle = "#000000";
            break;
        case 1:
            context.fillStyle = "#FF1822";
            context.strokeStyle = "#FF1822";
            break;
        case 2:
            context.fillStyle = "#FFDC18";
            context.strokeStyle = "#FFDC18";
            break;
        case 3:
            context.fillStyle = "#2BFF18";
            context.strokeStyle = "#2BFF18";
            break;
        case 4:
            context.fillStyle = "#18FFFF";
            context.strokeStyle = "#18FFFF";
            break;
        case 5:
            context.fillStyle = "#1F75FE";
            context.strokeStyle = "#1F75FE";
            break;
        case 6:
            context.fillStyle = "#FC74FD";
            context.strokeStyle = "#FC74FD";
            break;

    }
    context.beginPath();
    context.arc(x, y, 3, 0, 2 * Math.PI);
    context.fill();
    context.stroke();
    context.closePath();
}

function drawNewton(){
    var table = document.getElementById("plotTable");
    var data = [];

// Перебор строк таблицы (tr)
    for (var i = 0; i < table.rows.length; i++) {
        var row = table.rows[i];
        var rowData = [];

        // Перебор ячеек строки (td)
        for (var j = 0; j < row.cells.length; j++) {
            var cell = row.cells[j];
            if(cell.innerHTML != ""){
                rowData.push(parseFloat(cell.innerHTML.substring(0, cell.innerHTML.length - 1)));
            }
        }

        data.push(rowData);
    }


    for(let i = 1; i < data[0].length; i++){
        drawRawPoint(data[0][i], data[1][i], 2)
    }


}
