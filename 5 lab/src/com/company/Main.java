package com.company;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException, Exception {

        // Упражнение 1
        try {
            System.out.println("Задание 1.");
            File F1 = new File("MyFile1.txt");
            F1.createNewFile();
            if (F1.exists())
                System.out.println("Файл " + F1.getName() + " создан.");
            else
                throw new FileNotFoundException("Файл " + F1.getName() + " не создан.");

            File F2 = new File("C:\\MyFile2.txt");
            F2.createNewFile();
            if (F2.exists())
                System.out.println("Файл " + F2.getName() + " создан.");
            else
                throw new FileNotFoundException("Файл " + F2.getName() + " не создан.");
            File dir = new File("C:\\Folder");
            dir.mkdir();
            File F3 = new File("C:\\Folder\\MyFile3.txt");
            F3.createNewFile();
            if (F3.exists())
                System.out.println("Файл " + F3.getName() + " создан.");
            else
                throw new FileNotFoundException("Файл " + F3.getName() + " не создан.");

            File Folder = new File("First\\Second\\Third");
            Folder.mkdirs();
            if (Folder.exists())
                System.out.println("Папка " + Folder.getName() + " создана.");
            else
                throw new FileNotFoundException("Папка " + Folder.getName() + " не создана.");

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.toString());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.toString());
        }



        // Упражнение 2
        File F1 = new File("C:\\Users\\Максим\\IdeaProjects\\Labla5\\MyFile1.txt");
        if (F1.isFile())
            System.out.println(F1.getParent() +"\\" +  F1.getName());
        File Folder = new File ("First");
        if(Folder.isDirectory())
            System.out.println(Folder.getName());
        if (F1.exists())
            System.out.println("Файл " + F1.getName() + " существует.");
        System.out.println(F1.getAbsolutePath());
        System.out.println("Размер " + F1.getName()+ " " + F1.length() + " байт.");
        if(F1.isFile())
            System.out.println(F1.getName() + " файл");
        else
            System.out.println(F1.getName() + " папка");

        // Упражнение 3
        Folder =new File("C:\\Users\\Максим\\IdeaProjects\\Labla5\\Folder");
        Folder.mkdir();
        String[] arr = Folder.getParentFile().list();
        System.out.println(Arrays.asList(arr));
        File[] arrFiles = Folder.getParentFile().listFiles();
        System.out.println(Arrays.asList(arrFiles));
        int count = 0;
        for(File buff: arrFiles){
            if (buff.isDirectory())
                    count++;
        }
        System.out.println(count);
        F1 = new File("MyFile1.txt");
        F1.delete();
        F1 = new File("C:\\Folder\\MyFile3.txt");
        F1.delete();
        F1 = new File("C:\\Folder");
        F1.delete();
        F1 = new File("C:\\MyFile2.txt");
        F1.delete();
        F1 = new File("First\\Second\\Third");
        F1.delete();
        F1 = new File("First\\Second");
        F1.delete();
        F1 = new File("First");
        F1.delete();
        F1 = new File("Folder");
        F1.delete();

        // Задание 2
        System.out.println("Задание 2.");
        FileManage F = new FileManage();
        F.createFile();
        F.Output();
        F.Input();
        F.RandomAcсess();
        // Задание 3
        System.out.println("Задание 3.");
        Task1();
        Task2();
        Task3();

        // Задание 4
        System.out.println("Задание 4.");
        ClassSerializableFile edge = new ClassSerializableFile("Edges");
        edge.writeCollection();
        edge.readCollection();
        edge.printList();
    }
    public static void Task1() throws  IOException{
        try{
        InputStreamReader read = new InputStreamReader(new FileInputStream("T1.txt"));
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("T2.txt"));
        int buff;
            String buffStr1 = "", buffStr2 = "";
        while((buff = read.read())!= -1){
            write.write((char)buff);
            buffStr1+=(char)buff;
        }
        write.close();
        read = new InputStreamReader(new FileInputStream("T2.txt"));
        while((buff = read.read())!= -1){
            buffStr2+=(char)buff;
        }
        if(buffStr1.equals(buffStr2))
            System.out.println("Файл T2.txt успешно заполнен");
        read.close();
        } catch (IOException e){e.printStackTrace();}
    }

    public static void Task2() throws IOException{
        try {
            char buff[] = new char[128];
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream("A.txt"));
            for (int i = 0; i < 512; i++) {
                write.write('a');
            }
            write.close();
            BufferedReader inb = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt")), 128);
            BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("B.txt")), 128);
            for (int i = 0; i < 4; i++) {
                inb.read(buff);
                outb.write(buff);
                outb.newLine();
            }
            inb.close();
            outb.close();
        }catch (IOException e){e.printStackTrace();}
    }
    public static void Task3() throws IOException{
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("A2.txt"), "Cp1251"));
            System.out.println(Charset.defaultCharset().name());
            String str = in.readLine();
            System.out.println(str);
            in = new BufferedReader(new InputStreamReader(new FileInputStream("B2.txt"), "UTF-8"));
            str = in.readLine();
            System.out.println(str);
        }catch (IOException e){e.printStackTrace();}
    }



}