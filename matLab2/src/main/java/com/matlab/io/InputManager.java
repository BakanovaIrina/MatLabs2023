package com.matlab.io;

import com.matlab.func.Func;
import com.matlab.func.FuncManager;
import com.matlab.func.SystemFunc;
import com.matlab.io.inputData.Data;
import com.matlab.io.inputData.SystemData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputManager {

    private final Scanner in;
    private FuncManager funcManager;

    public InputManager() {
        in = new Scanner(System.in);
        funcManager = new FuncManager();
    }

    public int readDataInputMethod() throws NumberFormatException{
        System.out.println("Как вы хотите ввести данные?");
        System.out.println("Из консоли (1)\nИз файла (2)");

        return Integer.parseInt(in.nextLine());
    }

    public Data readEquationFromFile() throws FileNotFoundException {
        System.out.println("Введите название файла:");
        String fileName = in.nextLine();
        Scanner fileScanner = new Scanner(new FileReader(fileName));

        int method = Integer.parseInt(fileScanner.nextLine());
        int func_id = Integer.parseInt(fileScanner.nextLine());
        Func func = funcManager.getFunc(func_id);
        float a = Float.parseFloat(fileScanner.nextLine().replace(",", "."));
        float b = Float.parseFloat(fileScanner.nextLine().replace(",", "."));
        float eps = Float.parseFloat(fileScanner.nextLine().replace(",", "."));

        return new Data(method, func, a, b, eps);
    }

    public int readConsole() throws NumberFormatException{
        System.out.println("Выберете, что вы хотите решить:");
        System.out.println("Решить нелинейное уравнение (1)\nРешить систему нелинейных уравнений(2)");

        int method = Integer.parseInt(in.nextLine());
        return method;
    }

    public Data readEquation() throws NumberFormatException{
        System.out.println("Выберете метод для решения уравнения:");
        System.out.println("Метод Ньютона (1)\nМетод простой итерации (2)\nМетод половинного деления (3)");
        int method = Integer.parseInt(in.nextLine());

        System.out.println("Выберете функцию для вычисления:");
        System.out.println("(1) e^x - x^4\n(2) sin(x) + cos(x)\n(3)x^3 + 10x^2 - 7x + 10");
        System.out.println("(4)x^2 - 1.6\n(5)x^3 - 6x^2 + 11x - 6");

        int func_id = Integer.parseInt(in.nextLine());
        Func func = funcManager.getFunc(func_id);

        System.out.println("Введите a:");
        float a = Float.parseFloat(in.nextLine().replace(",", "."));

        System.out.println("Введите b:");
        float b = Float.parseFloat(in.nextLine().replace(",", "."));

        System.out.println("Введите eps:");
        float eps = Float.parseFloat(in.nextLine().replace(",", "."));

        return new Data(method, func, a, b, eps);
    }

    public SystemData readSystem(){
        int method = 4;
        System.out.println("Выберете одну из систем:");
        //Вписать варианты систем
        System.out.println("(1): x^2 + y^2 - 4\n-3x^2 + y");
        System.out.println("(2): xy - 1\nx^3 + y^3 - 4");
        System.out.println("(3): 2y - 5e^x - 2\nx^4 + y - 4");
        int func_id = Integer.parseInt(in.nextLine());

        SystemFunc f1 = funcManager.getSystemFunc(func_id, 0);
        SystemFunc f2 = funcManager.getSystemFunc(func_id, 1);

        System.out.println("Введите x0:");
        float x0 = Float.parseFloat(in.nextLine().replace(",", "."));

        System.out.println("Введите y0:");
        float y0 = Float.parseFloat(in.nextLine().replace(",", "."));

        System.out.println("Введите eps:");
        float eps = Float.parseFloat(in.nextLine().replace(",", "."));

        return new SystemData(method, f1, f2, funcManager.getYFuncFromSystem(func_id), x0, y0, eps);
    }

    public SystemData readSystemFromFile() throws FileNotFoundException {
        System.out.println("Введите название файла:");
        String fileName = in.nextLine();
        Scanner fileScanner = new Scanner(new FileReader(fileName));

        int method = 4;
        int func_id = Integer.parseInt(fileScanner.nextLine());
        SystemFunc f1 = funcManager.getSystemFunc(func_id, 0);
        SystemFunc f2 = funcManager.getSystemFunc(func_id, 1);
        float x0 = Float.parseFloat(fileScanner.nextLine().replace(",", "."));
        float y0 = Float.parseFloat(fileScanner.nextLine().replace(",", "."));
        float eps = Float.parseFloat(fileScanner.nextLine().replace(",", "."));

        return new SystemData(method, f1, f2, funcManager.getYFuncFromSystem(func_id), x0, y0, eps);
    }

    public int readOutput(){
        System.out.println("Как вы хотите вывести результат?\n(1)Файл\n(2)Консоль");
        return Integer.parseInt(in.nextLine());
    }
}
