package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
    private int n;
    private Short Array[];

    Array(int n) throws Exception{
        if(n <= 0) throw new Exception(new String("Количество элементов <= 0"));
        this.n = n;
        this.Array = new Short[n];
    }

    public void InitArray(){
        int max = 1000;
        int min = 1;
        int num;
        Scanner in = new Scanner(System.in);
        System.out.println("Способы заполнения массива: ");
        System.out.println("1. С клавиатуры");
        System.out.println("2. Случайные числа");
        System.out.println("Выберите способ заполнения: ");
        num = in.nextInt();
        switch(num) {
            case 1:
                System.out.println("Введите " + n + " чисел: ");
                for(int i=0; i < n; i++ ){
                    Array[i] = in.nextShort();
                }
                break;
            case 2:
                for(int i = 0; i< n; i++){
                    Array[i] =(short)(Math.random()*max+min);
                }
                break;

        }


    }

    public void OutputArray(){
        int num = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Способы вывода массива: ");
        System.out.println("1. Слева направо");
        System.out.println("2. Справа налево");
        System.out.println("Выберите способ: ");
        num = in.nextInt();
        switch (num) {
            case 1:

                System.out.println("Элементы массива: ");
                for(int i : Array) {
                   System.out.println(i);
                }
                break;
            case 2:
                System.out.println("Элементы массива: ");
                for (int i = n - 1; i >= 0; i--) {
                    System.out.println(Array[i]);
                }
                break;
        }


    }

    public void DivisionArray(){
        int num = (int)(Math.random()*10+1);
        int count;
        int ans = 0;
        for(int i = 0; i < n; i++){
            count = 0;
            for(int j = 1; j <= Array[i]; j++){
                if(Array[i] % j == 0)
                    count++;
            }
            if(count < num)
                ans++;

        }
        System.out.println("Количество элементов массива, у которых количество делителей меньше " + num + ": " + ans);

    }
    public void DeleteArray(){
        String s = new String();
        for(int i = 0 ; i< n; i++){
            s = Array[i].toString();
            int buff = Array[i] / (int) Math.pow(10,s.length()-1);
            if(buff % 2 == 1){
                for(int j = i; j < n - 1 ; j++){
                    Array[j] = Array[j + 1];
                }
                n--;
                i--;
                Array = Arrays.copyOf(Array, n);
            }


        }
    }
}
