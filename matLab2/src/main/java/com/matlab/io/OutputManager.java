package com.matlab.io;

import com.matlab.res.ResultIter;
import com.matlab.res.ResultIterSystem;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OutputManager {

    FileWriter writer;

    public OutputManager() {
        try {
            this.writer = new FileWriter("output.txt");
        } catch (IOException e) {
            System.out.println("Невозможно записать в файл!");
        }
        ;
    }

    public void printResult(List<ResultIter> results){
        System.out.println(results.get(0).getTitle());
        for(int i = 0; i < results.size(); i++){
            System.out.println(results.get(i).getLinearResult());
        }
    }


    public void writeResult(List<ResultIter> results) throws IOException {
        writer.write(results.get(0).getTitle());
        for(int i = 0; i < results.size(); i++){
            writer.write(results.get(i).getLinearResult());
            writer.write("\n");
        }
    }

    public void print(String s){
        System.out.println(s);
    }

    public void write(String s) throws IOException {
        writer.write(s);
        writer.flush();
    }

    public void printResultSystem(List<ResultIterSystem> resSystem) {
        System.out.println(resSystem.get(0).getTitle());
        for(int i = 0; i < resSystem.size(); i++){
            System.out.println(resSystem.get(i).getLinearResult());
        }
    }
}
