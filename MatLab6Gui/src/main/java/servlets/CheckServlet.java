package servlets;

import FuncCounter.FuncCounter;
import methods.AdamsMethod;
import methods.EilerMethod;
import methods.Runge4Method;
import res.AdamsRes;
import res.EilerRes;
import res.ErrRes;
import res.RungeRes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CheckServlet", value = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        String x0s, xns, y0s, hs, epsS, idFuncS;

        try {
            x0s = request.getParameter("x0").replace("\r", "");
            xns = request.getParameter("xn").replace("\r", "");
            y0s = request.getParameter("y0").replace("\r", "");
            hs = request.getParameter("h").replace("\r", "");
            epsS = request.getParameter("eps").replace("\r", "");
            idFuncS = request.getParameter("idFunc").replace("\r", "");
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(400);
            return;
        }

        Double x0, xn, y0, h, eps;
        int idFunc;


        try {
            idFunc = getInt(idFuncS);
            x0 = getDouble(x0s);
            xn = getDouble(xns);
            y0 = getDouble(y0s);
            h = getDouble(hs);
            eps = getDouble(epsS);

            if(x0 >= xn){
                throw new IOException();
            }

            FuncCounter funcCounter = new FuncCounter();
            EilerMethod eilerMethod = new EilerMethod(funcCounter);
            Runge4Method runge4Method = new Runge4Method(funcCounter);
            AdamsMethod adamsMethod = new AdamsMethod(funcCounter, runge4Method);

            EilerRes eilerRes = eilerMethod.doMethod(x0, xn, y0, h, eps, idFunc);
            RungeRes rungeRes = runge4Method.doMethod(x0, xn, y0, h, eps, idFunc);
            AdamsRes adamsRes = adamsMethod.doMethod(x0, xn, y0, h, eps, idFunc);

            request.getSession().setAttribute("eilerRes", eilerRes);
            request.getSession().setAttribute("rungeRes", rungeRes);
            request.getSession().setAttribute("adamsRes", adamsRes);

            request.getSession().setAttribute("plotE",
                    eilerMethod.doMethod(x0, xn, y0, (xn - x0) / 50, eps, idFunc));
            request.getSession().setAttribute("plotR",
                    runge4Method.doMethod(x0, xn, y0, (xn - x0) / 50, eps, idFunc));
            request.getSession().setAttribute("plotA",
                    adamsMethod.doMethod(x0, xn, y0, (xn - x0) / 50, eps, idFunc));


            ErrRes result2 = new ErrRes("");
            request.getSession().setAttribute("err", result2);

        }
        catch (Exception e) {
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

    private double getDouble(String x) throws Exception{
        return Double.parseDouble(x);
    }

    private int getInt(String x) throws Exception{
        return Integer.parseInt(x);
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
