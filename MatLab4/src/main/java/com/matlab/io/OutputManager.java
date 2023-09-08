package com.matlab.io;

import java.io.FileWriter;
import java.io.IOException;

public class OutputManager {

    FileWriter writer;
    boolean inFile;

    public OutputManager(boolean inFile) {
        if(inFile){
            try {
                this.writer = new FileWriter("output.txt");
            } catch (IOException e) {
                System.out.println("Невозможно записать в файл!");
            }
        }
        this.inFile = inFile;
    }

    public void print(String res){
        if(inFile) {
            try {
                writer.write(res);
                writer.flush();
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл!");
            }
        }
        else {
            System.out.println(res);
        }
    }
}
