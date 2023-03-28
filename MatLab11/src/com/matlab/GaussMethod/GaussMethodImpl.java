package com.matlab.GaussMethod;

public class GaussMethodImpl implements GaussMethod{
    @Override
    public float countDeterminant(float[][] matrix) {
        int n = matrix.length;
        float determinant = 1;

        for (int i = 0; i < n; i++) {
            determinant = determinant * matrix[i][i];
        }
        return determinant;
    }

    @Override
    public float[][] doTriangularMatrix(float[][] matrix) {
        int n = matrix.length;

        float[][] newMatrix = matrix;
        newMatrix[0] = matrix[0];

        for(int t = 0; t < n - 1; t++) {
            for (int i = t; i < n - 1; i++) {
                float k = -newMatrix[i + 1][t] / newMatrix[t][t];
                for (int j = 0; j < n + 1; j++) {
                    newMatrix[i + 1][j] = matrix[t][j] * k + matrix[i + 1][j];
                }
            }
        }
        return newMatrix;
    }

    @Override
    public boolean checkTriangularMatrix(float[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++){
            if(matrix[i][i] == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public float[][] permutation(float[][] triangularMatrix) {
        int n = triangularMatrix.length;
        float[][] newMatrix = triangularMatrix;
        float a;

        for(int i = 0; i < n; i++){
            if(triangularMatrix[i][i] == 0){
                for(int j = i + 1; j < n; j++){
                    if(triangularMatrix[j][j] != 0){
                        for(int t = 0; t < n + 1; t++){
                            a = newMatrix[i][t];
                            newMatrix[i][t] = triangularMatrix[j][t];
                            newMatrix[j][t] = a;
                        }
                    }
                }
            }
        }
        return triangularMatrix;
    }

    @Override
    public float[] getXn(float[][] triangularMatrix) {
        int n = triangularMatrix.length;
        float[] x = new float[n];

        for(int i = n - 1; i >= 0; i--){
            x[i] = triangularMatrix[i][n];
            for(int j = n - 1; j > i; j--){
                x[i] = x[i] - triangularMatrix[i][j] * x[i + 1];
            }
            x[i] = x[i] / triangularMatrix[i][i];
        }

        return x;
    }

    @Override
    public float[] getRx(float[][] triangularMatrix, float[] roots) {
        int n = triangularMatrix.length;
        float[] dis = new float[n];

        for(int i = 0; i < n; i++){
            float r = triangularMatrix[i][n];
            for(int j = 0; j < n; j++){
                r -= triangularMatrix[i][j] * roots[j];
            }

            dis[i] = r;
        }
        return dis;
    }

    @Override
    public void printTriangularMatrix(float[][] triangularMatrix) {
        System.out.println("Преобразованная матрица:");
        for (int i = 0; i < triangularMatrix.length; i++) {
            for (int j = 0; j < triangularMatrix[0].length; j++){
                if(j == triangularMatrix[0].length -1){
                    System.out.printf("%15s", "(" + triangularMatrix[i][j] + ")");
                    continue;
                }
                System.out.printf("%15s", triangularMatrix[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void printVariablesXn(float[][] triangularMatrix) {
        System.out.println("Полученные значения Xn:");
        int n = triangularMatrix.length;
        float[] x = getXn(triangularMatrix);

        for(int i = 0; i < n; i++){
            System.out.println("x" + (i+1) + " = " + x[i]);
        }
    }

    @Override
    public void printVectorRn(float[][] triangularMatrix, float[] roots) {
        System.out.println("Полученные значения Rn:");
        int n = triangularMatrix.length;
        float[] r = getRx(triangularMatrix, roots);

        for(int i = 0; i < n; i++){
            System.out.println("r" + (i+1) + " = " + r[i]);
        }
    }

    @Override
    public void printDeterminant(float[][] triangularMatrix) {
        System.out.println("Определитель матрицы равен: " + countDeterminant(triangularMatrix));
    }
}
