package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ClassTextFile {
    private String fileName;
    public ClassTextFile(){
        fileName = "";
    }
    public ClassTextFile(String fileName){
        if(new File(fileName).exists())
            this.fileName = fileName;
        else
            System.out.println("Файл с таким именем не существует");
    }
    public Edge read() throws IOException{
        Scanner in = new Scanner(new File(fileName));
        return new Edge(in.next(),in.next());
    }
}
