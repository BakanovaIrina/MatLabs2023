package com.matlab;

import com.matlab.GaussMethod.GaussMethod;
import com.matlab.GaussMethod.GaussMethodImpl;
import com.matlab.input.InputManager;
import com.matlab.input.InputManagerImpl;

public class Main {

    public static void main(String[] args) {
        InputManager inputManager = new InputManagerImpl();
        float[][] matrix;
        try {
            matrix = inputManager.readSystem(inputManager.readMethodOfInput());
        }
        catch (IllegalArgumentException e){
            System.out.println("Вы ввели неправильные данные");
            return;
        }

        GaussMethod gaussMethod = new GaussMethodImpl();

        if(!gaussMethod.checkTriangularMatrix(matrix)){
            System.out.println("Перестановка строк матрицы");
            matrix = gaussMethod.permutation(matrix);
            gaussMethod.printTriangularMatrix(matrix);
        }
        matrix = gaussMethod.doTriangularMatrix(matrix);
        gaussMethod.printTriangularMatrix(matrix);
        gaussMethod.printDeterminant(matrix);
        if (gaussMethod.countDeterminant(matrix) == 0){
            System.out.println("Система не имеет решений");
            return;
        }
        gaussMethod.printVariablesXn(matrix);
        gaussMethod.printVectorRn(matrix, gaussMethod.getXn(matrix));

    }
}
