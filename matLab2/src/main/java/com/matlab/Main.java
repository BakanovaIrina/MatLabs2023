package com.matlab;

import com.matlab.io.InputManager;
import com.matlab.io.OutputManager;
import com.matlab.io.inputData.Data;
import com.matlab.io.inputData.SystemData;
import com.matlab.methods.NonlinearMethods;
import com.matlab.methods.SystemMethods;
import com.matlab.plot.Graph;
import com.matlab.res.ResultIter;
import com.matlab.res.ResultIterSystem;
import com.matlab.res.StepMethodResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputManager inputManager = new InputManager();
        OutputManager outputManager = new OutputManager();
        NonlinearMethods nonlinearMethods = new NonlinearMethods();
        SystemMethods systemMethods = new SystemMethods();

        int inputMethod = inputManager.readDataInputMethod();

        List<ResultIter> res = new ArrayList<>();
        Data data = null;
        SystemData systemData = null;
        int method = 0;
        try {
            if(inputMethod == 1){
                method = inputManager.readConsole();
                if(method == 1){
                        data = inputManager.readEquation();
                }
                else if (method == 2){
                    systemData = inputManager.readSystem();
                }
            }
            else if (inputMethod == 2){
                method = inputManager.readConsole();
                if(method == 1){
                    data = inputManager.readEquationFromFile();
                }
                else if (method == 2){
                    systemData = inputManager.readSystemFromFile();
                }
            }
        }
        catch (FileNotFoundException e){
            outputManager.print("Проблемы с чтением из файла");
            System.exit(0);
        }
        catch (NumberFormatException e){
            outputManager.print("Вы ввели что-то нехорошее!");
            System.exit(0);
        }

        if(method == 1){
            int countMethod = data.getMethod();
            int output = inputManager.readOutput();
            List<StepMethodResult> stepMethodResults = nonlinearMethods.stepMethod(data.getA(),
                    data.getB(), data.getFunc(), 1000);
            if(stepMethodResults.isEmpty()){
                if(output == 1){
                    try {
                        outputManager.write("На введенном промежутке нет корней");
                    } catch (IOException e) {
                        System.out.println("Проблемы с файлом");
                        System.exit(0);
                    }
                }
                else {
                    outputManager.print("На введенном промежутке нет корней");
                }
                System.exit(0);
            }

            switch (countMethod) {
                case 1:
                    int i = 1;
                    for(StepMethodResult s : stepMethodResults){
                        System.out.println("Для корня " + i + ":");
                        Data cur_data = new Data(data.getMethod(), data.getFunc(), s.getA(),
                                s.getB(), data.getEps());
                        res = nonlinearMethods.newtonMethod(cur_data);
                        if(output == 1){
                            try {
                                outputManager.writeResult(res);
                            } catch (IOException e) {
                                System.out.println("Проблемы с файлом");
                                System.exit(0);
                            }
                        }
                        else {
                            outputManager.printResult(res);
                        }
                        i++;
                    }
                    break;
                case 2:
                    res = nonlinearMethods.simpleIterationMethod(data);
                    if(output == 1){
                        try {
                            outputManager.writeResult(res);
                        } catch (IOException e) {
                            System.out.println("Проблемы с файлом");
                            System.exit(0);
                        }
                    }
                    else {
                        outputManager.printResult(res);
                    }
                    break;
                case 3:
                    res = nonlinearMethods.halfDivisionMethod(data);
                    if(output == 1){
                        try {
                            outputManager.writeResult(res);
                        } catch (IOException e) {
                            System.out.println("Проблемы с файлом");
                            System.exit(0);
                        }
                    }
                    else {
                        outputManager.printResult(res);
                    }
                    break;
            }

            Graph plot = new Graph("title");
            plot.graph(data.getA(), data.getB(), res.get(res.size() - 1).getX(), 0.0, data.getFunc());
        }

        if(method == 2){
            List<ResultIterSystem> resSystem = systemMethods.systemNewtonMethod(systemData);
            outputManager.printResultSystem(resSystem);
            Graph plot = new Graph("title");
            ResultIterSystem last = resSystem.get(resSystem.size() - 1);
            plot.graph(systemData.getX() - 1, systemData.getX() + 1, last.getX(), last.getY(), systemData.getF1());
        }
    }
}
