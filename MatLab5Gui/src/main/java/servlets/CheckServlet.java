package servlets;

import method.LagrangeMethod;
import method.NewtonMethod;
import res.ErrRes;
import res.LagrangeRes;
import res.NewtonPlotRes;
import res.NewtonRes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        String x, y, xV;

        try {
            x = request.getParameter("x").replace("\r", "");
            y = request.getParameter("y").replace("\r", "");
            xV = request.getParameter("x_val").replace("\r", "");
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(400);
            return;
        }

        ArrayList<Double> arg;
        ArrayList<Double> values;
        try {
            arg = getArgs(x);
            values = get(y);
            double xVal = getX(xV);

            LagrangeMethod lagrangeMethod = new LagrangeMethod();
            LagrangeRes lagrangeRes = lagrangeMethod.count(arg, values, xVal);

            NewtonRes newtonRes;
            NewtonPlotRes newtonPlotRes;
            if(!checkConditions(arg)){
                newtonRes = new NewtonRes(false);
                newtonPlotRes = new NewtonPlotRes();
            }
            else {
                NewtonMethod newtonMethod = new NewtonMethod();
                newtonRes = newtonMethod.count(arg, values, xVal);
                if(arg.get(0) > xVal){
                    newtonPlotRes = new NewtonPlotRes(arg, values, xVal, arg.get(arg.size() - 1));
                }
                else if(arg.get(arg.size() - 1) < xVal){
                    newtonPlotRes = new NewtonPlotRes(arg, values, arg.get(0), xVal);
                }
                else {
                    newtonPlotRes = new NewtonPlotRes(arg, values, arg.get(0), arg.get(arg.size() - 1));
                }
            }

            request.getSession().setAttribute("resultLagrange", lagrangeRes);
            request.getSession().setAttribute("resultNewton", newtonRes);
            request.getSession().setAttribute("args", arg);
            request.getSession().setAttribute("values", values);
            request.getSession().setAttribute("x", xVal);
            request.getSession().setAttribute("plot", newtonPlotRes);

            ErrRes result2 = new ErrRes("");
            request.getSession().setAttribute("err", result2);


        } catch (Exception e) {
            ErrRes result = new ErrRes("Неправильный ввод");
            request.getSession().setAttribute("err", result);
            dispatchToServlet("/error.jsp", request, response);
        }


    }

    private ArrayList<Double> getArgs(String x) throws Exception{
        ArrayList<Double> data = new ArrayList<>();

        String[] x1 = x.replace(",", ".").split(" ");
        for(int i = 0; i < x1.length; i++){
            data.add(Double.parseDouble(x1[i]));
        }

        if(checkForDuplicates(x1)){
            throw new Exception();
        }

        return data;
    }


    private ArrayList<Double> get(String x) throws Exception{
        ArrayList<Double> data = new ArrayList<>();

        String[] x1 = x.replace(",", ".").split(" ");
        for(int i = 0; i < x1.length; i++){
            data.add(Double.parseDouble(x1[i]));
        }

        return data;
    }

    private double getX(String x) throws Exception{
        return Double.parseDouble(x);
    }

    private static boolean checkForDuplicates(String[] array)
    {
        // для каждого элемента массива проверяем, встречается ли он потом в массиве
        for (int i = 0; i < array.length; i++)
        {
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[i] != null && array[i].equals(array[j])) {
                    return true;
                }
            }
        }

        // дубликат не найден
        return false;
    }


    private boolean checkConditions(ArrayList<Double> args){
        double h = args.get(1) - args.get(0);

        double diff;
        for(int i = 1; i < args.size(); i ++){
            diff = args.get(i) - args.get(i - 1);
            if(Math.abs(diff - h) > 0.000001){
                return false;
            }
        }
        return true;
    }

    private void dispatchToServlet(String servletPath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(servletPath);
        requestDispatcher.forward(request, response);
    }
}

