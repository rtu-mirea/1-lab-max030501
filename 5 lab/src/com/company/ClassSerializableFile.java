package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ClassSerializableFile {
    private String fileName;
    private LinkedList<Edge> edges = new LinkedList<>();

    ClassSerializableFile(String fileName) throws IOException {
        this.fileName = fileName;
        new File(this.fileName).createNewFile();
    }

    public void write(Edge edge) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(edge);
        out.close();
    }

    public Edge read() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Edge edge = (Edge)in.readObject();
        in.close();
        return edge;
    }
    public LinkedList<Edge> writeCollection() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество узлов:");
        int size = in.nextInt();
        LinkedList<Edge> tempEdges = new LinkedList<>();
        in.nextLine();
        for(int i = 0; i < size; i++){
            System.out.println("Введите два узла:");
           tempEdges.add(new Edge(in.next(), in.next()));
            in.nextLine();
        }
        out.writeObject(tempEdges);
        out.close();
        return tempEdges;
    }

    public void readCollection() throws IOException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        try{
            edges =(LinkedList<Edge>) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void printList(){
        for(Edge e: edges){
            System.out.println(e.toString());
        }
    }
}


