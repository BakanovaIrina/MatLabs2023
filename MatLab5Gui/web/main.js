let select_val;
function send(){
    select_val = document.getElementById("select").value.substring(0, 12);
    if(select_val == 1){
        window.location.href = "input1.jsp";
    }
    else if(select_val == 2){
        window.location.href = "input2.jsp";
    }
    else {
        window.location.href = "input3.jsp";
    }
}


function sendInput(){
    x = document.getElementById("x").value;
    y = document.getElementById("y").value;
    x_val = document.getElementById("x_val").value;
    sendRequest(x, y, x_val)
}

function sendRequest(x, y, x_val){
    fetch('check', {
        method: 'POST',
        headers : {
            'Content-Type' : "application/x-www-form-urlencoded"
        },
        body: "x=" + x + "&y=" + y + "&x_val=" + x_val
    }).then(function () {
        location.reload();
        window.location.href = "result.jsp";
    });
}

function sendFileInput(text1){
    var lines = text1.split("\n");
    x_val = lines[0];
    x = lines[1];
    y = lines[2];
    sendRequest(x, y, x_val)
}

function sendFuncDots(){
    let a = document.getElementById("a").value.replace(",", ".");
    let b = document.getElementById("b").value.replace(",", ".");
    let n = document.getElementById("countDots").value;
    x_val1 = document.getElementById("x_val1").value.replace(",", ".");

    if(!isNumeric(a) || !isNumeric(b) || !Number.isInteger(Number(n))){
        alert("ksd")
        window.location.href = "error.jsp";
        return;
    }

    a = parseFloat(a);
    b = parseFloat(b);
    n = parseInt(n);

    if(b <= a){
        alert("a > b!");
        window.location.href = "error.jsp";
        return;
    }

    let h = (b - a) / n;
    let x = "";
    let y = "";

    select_val = document.getElementById("selectFunc").value.substring(0, 12);
    if(select_val == 1){
        while (a <= b){
            x = x + " " + a;
            y = y + " " + (a * a - a + 2);
            a += h;
        }
    }
    else if(select_val == 2){
        while (a <= b){
            x = x + " " + a;
            y = y + " " + Math.log(a * a + 1);
            a += h;
        }

    }
    else if(select_val == 3){
        while (a <= b){
            x = x + " " + a;
            y = y + " " + a * a;
            a += h;
        }
    }

    x = x.substring(1)
    y = y.substring(1)

    sendRequest(x, y, x_val1);

}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
