<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static java.lang.Double.isNaN" %>
<%@ page import="res.ErrRes" %>
<%@ page import="res.LagrangeRes" %>
<%@ page import="res.NewtonRes" %>
<%@ page import="res.NewtonPlotRes" %>

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

<div class="method_name">
    Введенные значения аргумента и функции:
    <br>
</div>

<%

%>

<table id="argsTable">
    <tr>
        <th>X</th>
        <%
            ArrayList<Double> args = (ArrayList<Double>) request.getSession().getAttribute("args");

            for(int i = 0; i < args.size(); i ++){

        %>
        <th class="xs"><%=String.format(Locale.ROOT,"%.4f\t", args.get(i))%></th>
        <%
            }
        %>
    </tr>
    <tr>
        <th>Y</th>
        <%
            ArrayList<Double> values = (ArrayList<Double>) request.getSession().getAttribute("values");

            for(int i = 0; i < values.size(); i ++){

        %>
        <th class="ys"><%=String.format(Locale.ROOT,"%.4f\t", values.get(i))%></th>
        <%
            }
        %>
    </tr>
</table>


<%
    LagrangeRes result = (LagrangeRes) request.getSession().getAttribute("resultLagrange");
    NewtonRes newtonRes = (NewtonRes) request.getSession().getAttribute("resultNewton");

%>

<div class="method_name">Многочлен Лагранжа</div>
<br>
<div>
    Получившееся значение:
    <br>
</div>
<div>y(<%=request.getSession().getAttribute("x")%>) =
    <%=result.getValue()%></div>

<table class="result_table">
    <tr>
        <th>i</th>
        <%
            ArrayList<Double> l = result.getL();
            for(int i = 0; i < l.size(); i++){

        %>
        <th><%=i%></th>
        <%
            }
        %>
    </tr>
    <tr>
        <th>li(x)</th>
        <%
            for(int i = 0; i < l.size(); i++){

        %>
        <th><%=String.format(Locale.ROOT,"%.4f\t", l.get(i))%></th>
        <%
            }
        %>
    </tr>
</table>

<div class="method_name">Многочлен Ньютона с конечными разностями </div>
<br>
<div>
    Получившееся значение:
    <br>
</div>

<div>y(<%=request.getSession().getAttribute("x")%>) =
    <%=String.format(Locale.ROOT,"%.4f\t", newtonRes.getRes())%></div>

<input type="text" id = "newX" required value="<%=request.getSession().getAttribute("x")%>" hidden>
<input type="text" id = "newY" required value="<%=newtonRes.getRes()%>" hidden>




<%
    if(!newtonRes.isCorrect() && !Double.isNaN(newtonRes.getRes())){
%>

<div>
    Функция не задана на равномерной сетке. Конечные разности не применимы.
</div>

<%
    }
%>

<%
    if(Double.isNaN(newtonRes.getRes())){
%>

<div>
    При вычислениях был выход за пределы размерной сетки
</div>

<%
    }
%>

<%
    if(newtonRes.isCorrect()){

%>
<table id="newton">
    <%
        double dyn[][] = newtonRes.getDyn();
        for(int i = 0; i < dyn.length; i++){

    %>
    <tr>
        <%
            String d;
            for(int j = 0; j < dyn.length; j++){

                if(dyn.length - i - 1 < j){
                    d = "";
                }
                else {
                    d = String.format(Locale.ROOT,"%.4f\t", dyn[j][i]);
                }
        %>
            <th><%=d%></th>
            <%
        }
            %>
    </tr>
    <%
        }
    %>
</table

<table id="plot" hidden>

</table>

<%
    NewtonPlotRes newtonPlotRes = (NewtonPlotRes) request.getSession().getAttribute("plot");
%>

<table id="plotTable" hidden>
    <tr>
        <th>X</th>
        <%
            NewtonPlotRes newtonPlotRes1 = (NewtonPlotRes) request.getSession().getAttribute("plot");
            ArrayList<Double> argsPlot1 = newtonPlotRes.getArgs();

            for(int i = 0; i < argsPlot1.size(); i++){

        %>
        <th><%=argsPlot1.get(i)%></th>
        <%
            }
        %>
    </tr>
    <tr>
        <th>Y</th>
        <%
            ArrayList<Double> values1 = newtonPlotRes1.getValues();

            for(int i = 0; i < values1.size(); i ++){

        %>
        <th><%=values1.get(i)%></th>
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
