package com.matlab.GaussMethod;

public interface GaussMethod {
    public float countDeterminant(float[][] matrix);
    public float[][] doTriangularMatrix(float[][] matrix);
    public boolean checkTriangularMatrix(float[][] matrix);
    public float[][] permutation(float[][] triangularMatrix);
    public float[] getRx(float[][] triangularMatrix, float[] roots);
    public float[] getXn(float[][] triangularMatrix);

    public void printTriangularMatrix(float[][] triangularMatrix);
    public void printVariablesXn(float[][] triangularMatrix);
    public void printVectorRn(float[][] triangularMatrix, float[] roots);
    public void printDeterminant(float[][] triangularMatrix);
}
