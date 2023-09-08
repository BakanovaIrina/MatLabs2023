package com.matlab.methods;

import com.matlab.EpsCounter.TableCounter;
import com.matlab.io.ResultView;
import com.matlab.plot.Graph;
import com.matlab.resultFunc.FunctionCounter;

import java.util.ArrayList;
import java.util.HashMap;

public class MethodManager {
    public String countAll(HashMap<Double, Double> input){
        Linear linear = new Linear();
        Polynomial2 polynomial = new Polynomial2();
        Polynomial3 polynomial3 = new Polynomial3();
        Exponential exponential = new Exponential();
        Logarithm logarithm = new Logarithm();
        Power power = new Power();

        String res = "";
        ArrayList<Double> s = new ArrayList<>();

        FunctionCounter linearFunc = linear.count(input);
        ResultView resultViewLinear = new ResultView(linearFunc);
        TableCounter tableCounterLinear = new TableCounter();
        res += "\nРезультат вычисления линейной аппроксимации:" + resultViewLinear.doTable(
                input, tableCounterLinear);
        s.add(tableCounterLinear.getS());
        resultViewLinear.doGraph(input, linearFunc, "Линейная аппроксимация");

        FunctionCounter polyFunc = polynomial.count(input);
        ResultView resultViewPolynomial = new ResultView(polyFunc);
        TableCounter tableCounterPoly = new TableCounter();
        res += "\nРезультат вычисления полиномиальной аппроксимации (2):" + resultViewPolynomial.doTable(
                input, tableCounterPoly);
        s.add(tableCounterPoly.getS());
        resultViewPolynomial.doGraph(input, polyFunc, "Полиномиальная аппроксимация (2)");

        FunctionCounter poly3Func = polynomial3.count(input);
        ResultView resultViewPoly3 = new ResultView(poly3Func);
        TableCounter tableCounterPoly3 = new TableCounter();
        res += "\nРезультат вычисления полиномиальной аппроксимации (3):" + resultViewPoly3.doTable(
                input, tableCounterPoly3);
        s.add(tableCounterPoly3.getS());
        resultViewPoly3.doGraph(input, poly3Func, "Полиномиальная аппроксимация (3)");

        FunctionCounter expFunc = exponential.count(input);
        ResultView resultViewExp = new ResultView(expFunc);
        TableCounter tableCounterExp = new TableCounter();
        res += "\nРезультат вычисления экспоненциальной аппроксимации:" + resultViewExp.doTable(
                input, tableCounterExp);
        s.add(tableCounterExp.getS());
        resultViewExp.doGraph(input, expFunc, "Экспоненциальная аппроксимация");

        FunctionCounter logFunc = logarithm.count(input);
        ResultView resultViewLog = new ResultView(logFunc);
        TableCounter tableCounterLog = new TableCounter();
        res += "\nРезультат вычисления логарифмический аппроксимации:" + resultViewLog.doTable(
                input, tableCounterLog);
        s.add(tableCounterLog.getS());
        resultViewLog.doGraph(input, logFunc, "Логарифмическая аппроксимация");

        FunctionCounter powFunc = power.count(input);
        ResultView resultViewPow = new ResultView(powFunc);
        TableCounter tableCounterPow = new TableCounter();
        res += "\nРезультат вычисления степенной аппроксимации:" + resultViewPow.doTable(
                input, tableCounterPow);
        s.add(tableCounterPow.getS());
        resultViewPow.doGraph(input, powFunc, "Степенная аппроксимация");

        double sMin = -1;
        for(Double i: s){
            if(i < sMin || sMin < 0){
                sMin = i;
            }
        }

        if(Math.abs(sMin - tableCounterLinear.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - линейная\n" + linearFunc.getStringFunction();
        }
        if(Math.abs(sMin - tableCounterPoly.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - полиномиальная (2)\n" + polyFunc.getStringFunction();
        }
        if(Math.abs(sMin - tableCounterExp.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - экспоненциальная\n" + expFunc.getStringFunction();
        }
        if(Math.abs(sMin - tableCounterLog.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - логарифмическая\n" + logFunc.getStringFunction();
        }
        if(Math.abs(sMin - tableCounterPow.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - степенная\n" + polyFunc.getStringFunction();
        }
        if(Math.abs(sMin - tableCounterPoly3.getS()) < 0.001){
            res += "\nНаилучшая аппроксимация - полиномиальная (3)\n" + polyFunc.getStringFunction();
        }

        return res;
    }
}
