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

    //xMin = parseFloat(document.getElementById("x0").value.replace(",", ".")) - 1;
    //xMax = parseFloat(document.getElementById("xn").value.replace(",", ".")) + 1;

    let data = getDataFromTable("eiler_Plot");

    xMin = parseFloat(data[0][1]) - 1;
    xMax = parseFloat(data[0][data[0].length - 1]) + 1;

    yMin = parseFloat(data[1][1]) - 1;
    yMax = parseFloat(data[1][data[0].length - 1]) + 1;

    if(xMin > xMax){
        t = xMin;
        xMin = xMax;
        xMax = t;
    }


    for(let i = 1; i < data[0].length; i++){
        drawRawPoint(parseFloat(data[0][i]), parseFloat(data[1][i]), 1)
    }

    let data1 = getDataFromTable("runge_Plot")

    for(let i = 1; i < data1[0].length; i++){
        drawRawPoint(parseFloat(data1[0][i]), parseFloat(data1[1][i]), 5)
    }

    let data2 = getDataFromTable("adams_Plot")
    for(let i = 1; i < data2[0].length; i++){
        drawRawPoint(parseFloat(data1[0][i]), parseFloat(data1[1][i]), 3)
    }

    for(let i = 1; i < data2[0].length; i++){
        drawRawPoint(parseFloat(data1[0][i]), parseFloat(data1[2][i]), 4)
    }

    context.lineWidth = 2;
    context.strokeStyle = "#000000";
    context.font='10pt Arial'

    context.stroke();
    context.closePath();

    drawNewton()
    //drawRawPoint(newX, newY, 3)
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

function getDataFromTable(tableName){
    var table = document.getElementById(tableName);
    var data = [];

// Перебор строк таблицы (tr)
    for (let i = 0; i < table.rows.length; i++) {
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
    return data;
}

function drawNewton(){


    for(let i = 1; i < data[0].length; i++){
        drawRawPoint(data[0][i], data[1][i], 2)
    }


}
