package com.matlab.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputManager {

    private final Scanner in;

    public InputManager() {
        in = new Scanner(System.in);
    }

    public HashMap<Double, Double> inputMethod() throws NumberFormatException, FileNotFoundException, ArrayIndexOutOfBoundsException {
        HashMap<Double, Double> data = null;
        System.out.println("Выберите способ ввода данных: \nИз консоли (1)\nИз файла (2)");
        int inputType = Integer.parseInt(in.nextLine());

        switch (inputType){
            case 1:
                data = readConsole();
                break;
            case 2:
                data = readFile();
                break;
            default:
                System.out.println("Такого способа не существует!");
                break;
        }
        return data;
    }

    public HashMap<Double, Double> readConsole(){
        HashMap<Double, Double> data = new HashMap<>();
        System.out.println("Какое количество точек желаете ввести?");
        int count = Integer.parseInt(in.nextLine());

        while (count > 12 || count < 8){
            System.out.println("Нужно ввести от 8 до 12 точек включительно! Введите еще раз:");
            count = Integer.parseInt(in.nextLine());
        }

        System.out.println("Напишите через пробел значение аргумента и функции:");
        double x;
        double y;
        for (int i = 0; i < count; i++){
            String s = in.nextLine();
            x = Double.parseDouble(s.split(" ")[0]);
            y = Double.parseDouble(s.split(" ")[1]);
            data.put(x, y);
        }
        return data;
    }

    public HashMap<Double, Double> readFile() throws FileNotFoundException {
        HashMap<Double, Double> data = new HashMap<>();
        double x;
        double y;

        System.out.println("Введите имя файла:");
        String fileName = in.nextLine();
        Scanner fileScanner = new Scanner(new FileReader(fileName));

        while (fileScanner.hasNext()){
            String s = fileScanner.nextLine();
            x = Double.parseDouble(s.split(" ")[0]);
            y = Double.parseDouble(s.split(" ")[1]);
            data.put(x, y);
        }
        return data;
    }

    public boolean askInFile(){
        System.out.println("Выберите способ вывода данных: \nВ консоль (1)\nВ файл (2)");
        int inputType = Integer.parseInt(in.nextLine());

        boolean res = false;
        switch (inputType){
            case 1:
                res = false;
                break;
            case 2:
                res = true;
                break;
            default:
                System.out.println("Такого способа не существует!");
                break;
        }
        return res;
    }
}
