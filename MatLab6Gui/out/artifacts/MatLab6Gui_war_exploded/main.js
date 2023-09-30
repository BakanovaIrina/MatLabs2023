let select_val;

function sendInput(){
    id = document.getElementById("select").value.substring(0, 12);
    x0 = document.getElementById("x0").value.replace(",", ".");
    xn = document.getElementById("xn").value.replace(",", ".");
    y0 = document.getElementById("y0").value.replace(",", ".");
    h = document.getElementById("h").value.replace(",", ".");
    eps = document.getElementById("eps").value.replace(",", ".");

    sendRequest(x0, xn, y0, h, eps, id)
}

function sendRequest(x0, xn, y0, h, eps, id){
    fetch('check', {
        method: 'POST',
        headers : {
            'Content-Type' : "application/x-www-form-urlencoded"
        },
        body: "x0=" + x0 + "&xn=" + xn + "&y0=" + y0 + "&h=" + h + "&eps=" + eps + "&idFunc=" + id
    }).then(function () {
        location.reload();
        window.location.href = "result.jsp";
    });
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
