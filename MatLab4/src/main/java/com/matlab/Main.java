package com.matlab;

import com.matlab.io.InputManager;
import com.matlab.io.OutputManager;
import com.matlab.methods.MethodManager;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        InputManager inputManager = new InputManager();
        HashMap<Double, Double> input = null;
        try {
            input = inputManager.inputMethod();
        } catch (FileNotFoundException | NumberFormatException | ArrayIndexOutOfBoundsException |
                NullPointerException e) {
            System.out.println("Введены некорректные значения");
            System.exit(0);
        }

        MethodManager methodManager = new MethodManager();
        OutputManager outputManager = new OutputManager(inputManager.askInFile());
        outputManager.print(methodManager.countAll(input));

    }
}
