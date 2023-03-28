package com.matlab.input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputManagerImpl implements InputManager{

    private final Scanner in;

    public InputManagerImpl() {
        in = new Scanner(System.in);
    }

    @Override
    public String readMethodOfInput() {
        System.out.println("Выберете, каким способом ввести данные: " +
                "\nСчитать из файла (1)" +
                "\nСчитать из терминала (2)" +
                "\nВыход(3)");
        return in.nextLine();
    }

    @Override
    public float[][] readSystem(String method) {
        float[][] system;

        if(method.equals("1")){
            system = readSystemFromFile();
        }
        else if (method.equals("2")){
            system = readSystemFromConsole();
        }
        else {
            throw new IllegalArgumentException();
        }
        return system;
    }

    private float[][] readSystemFromConsole(){
        System.out.println("Введите количество уравнений системы (не более 20):");
        int count = Integer.parseInt(in.nextLine());

        if(count > 20 || count < 2){
            throw new IllegalArgumentException();
        }

        float[][] system = new float[count][count +1];

        System.out.println("Введите коэффициенты в уравнении по каждому запросу");

        for(int i = 1; i < count + 1; i++){
            for (int j = 1; j < count + 2; j++){
                System.out.println("Введите " + "a[" + i + "]" + "[" + j + "]");
                system[i - 1][j - 1] = Float.parseFloat(in.nextLine());
            }
        }

        return system;
    }

    private float[][] readSystemFromFile(){
        float[][] system = null;
        System.out.println("Введите название файла:");
        String fileName = in.nextLine();
        try {
            int count = -1;
            Scanner fileScanner = new Scanner(new FileReader(fileName));
            if (fileScanner.hasNext()){
                count = Integer.parseInt(fileScanner.nextLine());
            }

            system = new float[count][count+1];

            for(int i = 0; i < count; i++){
                String[] parts = fileScanner.nextLine().split(" ");
                for (int j = 0; j < count + 1; j++){
                    system[i][j] = Float.parseFloat(parts[j]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с файлом");
        }
        return system;
    }

}
