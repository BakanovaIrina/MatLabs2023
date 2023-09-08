package com.matlab;

import com.matlab.io.Data;
import com.matlab.io.InputManager;
import com.matlab.result.Result;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        InputManager inputManager = new InputManager();

        Data data = null;
        try {
            data = inputManager.inputMethod();
        }
        catch (NumberFormatException e){
            System.out.println("Вы ввели что-то не так");
            System.exit(0);
        }


        MethodManager methodManager = new MethodManager(data.getFunctionId());
        Result res = methodManager.countWhileNotRungeCondition(data);

        DecimalFormat format = new DecimalFormat( "0.000" );
        System.out.println("Результат работы программы:");
        System.out.println("I = " + format.format(res.getValue()));
        System.out.println("Число разбиения для данной точности n = " + res.getN());
    }
}
