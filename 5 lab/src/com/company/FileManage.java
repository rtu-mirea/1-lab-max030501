package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManage {
    private Toy T = null;
    private ArrayList<Toy> toys = new ArrayList<>();
    private String FileName;
    FileManage(){};

    public void createFile() throws IOException {
        String buff;
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите имя файла");
                buff = in.next() + ".dat";
                File F1 = new File(buff);
                if (F1.exists())
                    System.out.println("Такой файл уже существует, введите другое название.");
                else {
                    F1.createNewFile();
                    FileName = buff;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Output() throws IOException {
        String buffManufacturer;
        float buffCost;
        int buffKidLimitation;
        int[] buffAgeInterval;
        try {
            DataOutputStream DO = new DataOutputStream(new FileOutputStream(FileName));
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите количество объектов: ");
            int max = sc.nextInt();
            for (int i = 0; i < max; i++) {
                System.out.println("Введите производителя: ");
                buffManufacturer = sc.next();
                System.out.println("Введите цену: ");
                buffCost = sc.nextFloat();
                System.out.println("Введите возрастное ограничение: ");
                buffKidLimitation = sc.nextInt();
                System.out.println("Введите, для кого игрушка предназначена(два значения - начальный возраст и конечный): ");
                buffAgeInterval = new int[]{sc.nextInt(), sc.nextInt()};
                DO.writeUTF(buffManufacturer);
                DO.writeUTF(buffCost + "");
                DO.writeUTF(buffKidLimitation + "");
                for (int j = 0; j < 2; j++) {
                    DO.writeUTF(buffAgeInterval[j] + "");
                }
//            DO.writeUTF("|");
            }
            DO.flush();
            DO.close();
        }catch (IOException e){e.printStackTrace();}
    }
    public void Input() throws IOException {
        String buffManufacturer;
        float buffCost;
        int buffKidLimitation;
        int[] buffAgeInterval;
        try {
            DataInputStream DI = new DataInputStream(new FileInputStream(FileName));
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите производителя, по которому необходимо произвести сортировку: ");
            String findManufacturer = sc.next();
            System.out.println("Список игрушек, отсортированных по производителю:");
            while (DI.available() != 0) {
                buffManufacturer = DI.readUTF();
                buffCost = Float.parseFloat(DI.readUTF());
                buffKidLimitation = Integer.parseInt(DI.readUTF());
                buffAgeInterval = new int[]{Integer.parseInt(DI.readUTF()), Integer.parseInt(DI.readUTF())};
                if (buffManufacturer.equals(findManufacturer))
                    toys.add(new Toy(buffManufacturer, buffCost, buffKidLimitation, buffAgeInterval));
            }
            DI.close();
            for (Toy t : toys) {
                System.out.println(t.toString());
                System.out.println();
            }
        }catch(IOException e){e.printStackTrace();}
    }
    public String prepare(String str){
        int size = 26;
        while(str.length()< size)
            str+= " ";
        return str;
    }
    public void RandomAcсess() throws IOException {
        String buffManufacturer;
        float buffCost;
        int buffKidLimitation;
        int[] buffAgeInterval;
        RandomAccessFile rf = new RandomAccessFile("RandomAccessFile.txt", "rw");
        for(Toy toy: toys){
            rf.writeUTF(prepare(toy.getManufacturer()));
            rf.writeUTF(prepare(toy.getCost()+""));
            rf.writeUTF(prepare(toy.getKidLimitation()+""));
            for (int i = 0; i< 2; i++){
                rf.writeUTF(prepare(toy.getAgeInterval()[i] + ""));
            }
        }
        rf.seek(0);
        ArrayList<Toy> buffToys = new ArrayList<>();
        for(int i = 0; i < toys.size(); i++){
            buffManufacturer= rf.readUTF().trim();
            buffCost = Float.parseFloat(rf.readUTF().trim());
            buffKidLimitation = Integer.parseInt(rf.readUTF().trim());
            if (buffKidLimitation < 6){
                buffCost /= 2;
                rf.seek(rf.getFilePointer()-28*2);
                rf.writeUTF(prepare(buffCost + ""));
                rf.seek(rf.getFilePointer()+28);
            }
            buffAgeInterval = new int[]{Integer.parseInt(rf.readUTF().trim()), Integer.parseInt(rf.readUTF().trim())};
            buffToys.add(new Toy(buffManufacturer,buffCost,buffKidLimitation,buffAgeInterval));
        }
        System.out.println("Список игрушек с уменьшенной ценой для детей младшего возраста:");
        for(Toy t: buffToys){
            System.out.println(t.toString());
        }
    }


}
