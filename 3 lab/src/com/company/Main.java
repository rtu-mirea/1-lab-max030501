package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String buffInput,buffInput1;
        int num;
        boolean end = true;
        Scanner in = new Scanner(System.in);
        while(end) {
            System.out.println("Возможные действия: ");
            System.out.println("1. 1 задача");
            System.out.println("2. 2 задача");
            System.out.println("3. 3 задача");
            System.out.println("4. Выход");
            num = in.nextInt();
            switch (num) {
                case 1:
                    in.nextLine();
                    Task1 Example = new Task1();
                    System.out.println(Example.outputOper());
                    System.out.println(Example.outputVar());
                    System.out.println(Example.maxVal());
                    break;
                case 2:
                    in.nextLine();
                    Task2 Example1 = new Task2();
                    System.out.println(Example1.oper());
                    System.out.println(Example1.sum());
                    System.out.println(Example1.oper2());
                    break;
                case 3:
                    in.nextLine();
                    buffInput = in.nextLine();
                    buffInput1 = in.nextLine();

                    Task3 task = new Task3(buffInput, buffInput1);
                    System.out.println(task.check16());
                    System.out.println(task.delete());
                    break;
                case 4:
                    String buffer = "<<=";
                    String buff1 = "=";
                    System.out.println(buffer.contains(buffer));
                    break;
                case 5:
                    end = false;
                    break;
            }
        }
    }
}
