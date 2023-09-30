<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static java.lang.Double.isNaN" %>
<%@ page import="res.ErrRes" %>
<%@ page import="res.EilerRes" %>
<%@ page import="res.RungeRes" %>
<%@ page import="res.AdamsRes" %>

<html>
<head>
    <title>Результат выполнения</title>
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body onload="drawCanvas()">
<script src="main.js"></script>
<script src="canvas.js"></script>

<header>
    <h2>
        Результат выполнения
    </h2>
</header>

<br>

<canvas id="canvas"></canvas>

<br>

<%
    ErrRes errRes = (ErrRes) request.getSession().getAttribute("err");
    if(errRes != null && errRes.getRes().equals("Неправильный ввод")) {
%>
<%=errRes.getRes()%>

<%
    return;
    }
%>

<br>


<%
    EilerRes eilerRes = (EilerRes) request.getSession().getAttribute("eilerRes");
    RungeRes rungeRes = (RungeRes) request.getSession().getAttribute("rungeRes");
    AdamsRes adamsRes = (AdamsRes) request.getSession().getAttribute("adamsRes");

%>

<div class="method_name">Метод Эйлера</div>
<br>
<div>
    Получившееся значение:
    <br>
</div>

<table class="eiler_Table">
    <tr>
        <th>i</th>
        <th>Xi</th>
        <th>Yi</th>
        <th>f(Xi, Yi)</th>
        <th>R</th>
    </tr>


    <%
        ArrayList<Double> x = eilerRes.getX();
        for(int i = 0; i < x.size(); i++){
    %>
    <tr>
        <th><%=i%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", x.get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", eilerRes.getY().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", eilerRes.getF().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", eilerRes.getRunge().get(i))%></th>
    </tr>
    <%
        }
    %>
</table>

<div class="method_name">Метод Рунге-Кутта (4)</div>
<br>
<div>
    Получившееся значение:
    <br>
</div>

<table class="runge_Table">
    <tr>
        <th>i</th>
        <th>Xi</th>
        <th>Yi</th>
        <th>f(Xi, Yi)</th>
        <th>k1</th>
        <th>k2</th>
        <th>k3</th>
        <th>k4</th>
        <th>R</th>
    </tr>


    <%
        ArrayList<Double> x1 = rungeRes.getX();
        for(int i = 0; i < x1.size(); i++){
    %>
    <tr>
        <th><%=i%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", x1.get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getY().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getF().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getK1().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getK2().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getK3().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getK4().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getRunge().get(i))%></th>
    </tr>
    <%
        }
    %>
</table>


<div class="method_name">Метод Адамса</div>
<br>
<div>
    Получившееся значение:
    <br>
</div>

<table class="adams_Table">
    <tr>
        <th>i</th>
        <th>Xi</th>
        <th>Yi</th>
        <th>Yiточн</th>
        <th>R</th>
    </tr>


    <%
        if(adamsRes.isCorrect()){

        ArrayList<Double> x3 = adamsRes.getX();
        for(int i = 0; i < x3.size(); i++){
    %>
    <tr>
        <th><%=i%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", x3.get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", adamsRes.getY().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", adamsRes.geteY().get(i))%></th>
        <th><%=String.format(Locale.ROOT,"%.4f\t", adamsRes.geteAll().get(i))%></th>
    </tr>
    <%
        }
    %>
</table>

<div>
    <br>
    Emax = <%=String.format(Locale.ROOT,"%.4f\t",Collections.max(adamsRes.geteAll()))%>
    <br>
</div>

<%
    }
%>

<%
    if(!adamsRes.isCorrect()){

%>
<div>Не удалось приблизиться к точному значению на введенную eps. Проверьте введенные значения</div>
<%
    }
%>


<table id="eiler_Plot" hidden>
    <tr>
        <th>Xi</th>
        <%
            eilerRes = (EilerRes) request.getSession().getAttribute("plotE");
            x = eilerRes.getX();
            for(int i = 0; i < x.size(); i++){
        %>
            <th><%=String.format(Locale.ROOT,"%.4f\t", x.get(i))%></th>
        <%
            }
        %>
    </tr>

    <tr>
        <th>Yi</th>

        <%
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", eilerRes.getY().get(i))%></th>
        <%
            }
        %>
    </tr>
</table>


<table id="runge_Plot" hidden>
    <tr>
        <th>Xi</th>
        <%
            rungeRes = (RungeRes) request.getSession().getAttribute("plotR");
            x = rungeRes.getX();
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", x.get(i))%></th>
        <%
            }
        %>
    </tr>

    <tr>
        <th>Yi</th>

        <%
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", rungeRes.getY().get(i))%></th>
        <%
            }
        %>
    </tr>
</table>

<%
    adamsRes = (AdamsRes) request.getSession().getAttribute("plotA");
    if(adamsRes.isCorrect()){
%>
<table id="adams_Plot" hidden>
    <tr>
        <th>Xi</th>
        <%
            x = adamsRes.getX();
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", x.get(i))%></th>
        <%
            }
        %>
    </tr>

    <tr>
        <th>Yi</th>

        <%
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", adamsRes.getY().get(i))%></th>
        <%
            }
        %>
    </tr>

    <tr>
        <th>YiТочн</th>

        <%
            for(int i = 0; i < x.size(); i++){
        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", adamsRes.geteY().get(i))%></th>
        <%
            }
        %>
    </tr>
</table>
<%
    }
%>


</body>
</html>
