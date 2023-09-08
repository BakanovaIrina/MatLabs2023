package com.matlab.io;

import java.util.Scanner;

public class InputManager {

    private final Scanner in;

    public InputManager() {
        in = new Scanner(System.in);
    }

    public Data inputMethod() throws NumberFormatException{
        System.out.println("Выберете метод для вычисления:");
        System.out.println("(1)Метод левых прямоугольников\n(2)Метод средних прямоугольников\n(3)Метод правых прямоугольников");
        System.out.println("(4)Метод трапеций\n(5)Метод Симпсона");
        int method = Integer.parseInt(in.nextLine());

        System.out.println("Выберете функцию для интегрирования:");
        System.out.println("(1): x^2");
        System.out.println("(2): x^5-x^2+4x-6");
        System.out.println("(3): 1 / (x^2+1)");
        System.out.println("(4): 1 / sqrt(sin(x)^2 + 16)");
        System.out.println("(5): sqrt(x^2+5) - x^2");
        int id = Integer.parseInt(in.nextLine());

        System.out.println("Введите интервалы:");
        System.out.println("a = ");
        double a = Double.parseDouble(in.nextLine().replace(",", "."));

        System.out.println("b = ");
        double b = Double.parseDouble(in.nextLine().replace(",", "."));

        System.out.println("Введите точность алгоритма:");
        double eps = Double.parseDouble(in.nextLine().replace(",", "."));

        System.out.println("Введите начальное число разбиения интервала n (по умолчанию 4):");
        int n = 4;
        try {
            n = Integer.parseInt(in.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Будет использовано значение по умолчанию");
        }

        return new Data(method, a, b, eps, id, n);
    }

}
