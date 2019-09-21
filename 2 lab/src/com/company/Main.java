package com.company;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testArray();
    }
    public static void testArray(){
        int buff;
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Введите количество элементов массива: ");
            buff = in.nextInt();
            Array array = new Array(buff);
            array.InitArray();
            array.OutputArray();
            array.DivisionArray();
            array.DeleteArray();
            array.OutputArray();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
