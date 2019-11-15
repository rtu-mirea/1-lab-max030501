package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<User>();
    private static Map map = new Map();
    private static User currentUser;

    public static void main(String[] args) {
        addUser("nameAdmin", "loginAdmin", "passwordAdmin");
        int choice = -1;
        while (choice != 3) {
                System.out.println("Добро пожаловать в систему контроля за пробками");
                System.out.println("Выберите действие:");
                System.out.println("1. Зарегистировать нового пользователя.");
                System.out.println("2. Войти в систему.");
                System.out.println("3. Выход.");
                Scanner in = new Scanner(System.in);
                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        SighUp(in);
                        if(currentUser == null)
                            continue;
                        UserMenu(in);
                        break;
                    case 2:
                        SignIn(in);
                        if(currentUser == null)
                            continue;
                        UserMenu(in);
                        break;
                    default:
                        continue;
                }
                currentUser = null;


        }
    }

    private static void addUser(String name, String login, String password, String repeation, int type) throws Exception {
        if (!password.equals(repeation)) throw new Exception("Пароли не совпадают.");
        for (User user : users) {
            if (user.login.equals(login))
                throw new Exception("Пользователь с таким логином уже существует.");
        }
        if (type == 1)
            users.add(new Driver(name, login, password));

    }

    private static void addUser(String name, String login, String password) {
        users.add(new Admin(name, login, password));
    }

    private static User findUser(String login, String password) throws Exception {
        for (User user : users) {
            if (user.enter(login, password))
                return user;
        }
        throw new Exception("Данного пользователя нет в системе, либо введен неверный логин или пароль.");
    }

    private static void SighUp(Scanner in) {
        try {
            String buffName, buffLogin, buffPassword, buffRepeation;
            int buffType;
            System.out.println("Введите имя:");
            buffName = in.next();
            System.out.println("Введите логин:");
            buffLogin = in.next();
            System.out.println("Введите пароль:");
            buffPassword = in.next();
            System.out.println("Повторите пароль:");
            buffRepeation = in.next();
            buffType = 1;
            addUser(buffName, buffLogin, buffPassword, buffRepeation, buffType);
            currentUser = findUser(buffLogin,buffPassword);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void SignIn(Scanner in) {
        try {
            String buffLogin, buffPassword;
            System.out.println("Введите логин:");
            buffLogin = in.next();
            System.out.println("Введите пароль:");
            buffPassword = in.next();
            currentUser = findUser(buffLogin, buffPassword);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void UserMenu(Scanner in) {
        if (currentUser instanceof Admin)
            AdminPanel(in);
        UserPanel(in);
    }

    private static void AdminPanel(Scanner in) {
        int choice = -1;
        String buffPlace1, buffPlace2;
        while (choice != 2) {
            try {
                System.out.println("1. Добавить узел.");
                System.out.println("2. Выход из меню админа.");
                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Введите два пункта: ");
                        buffPlace1 = in.next();
                        buffPlace2 = in.next();
                        map.addEdge(buffPlace1, buffPlace2);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }


        }
    }
    private static void UserPanel(Scanner in){
            int choice = -1;
            String buffPlace1,buffPlace2;
            int buffBackup;
            List<String> buffArray;
            while(choice != 3){
                try{
                System.out.println("1. Сообщить о пробке на пути");
                System.out.println("2. Найти кратчайший путь");
                System.out.println("3. Выход");
                choice = in.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Введите район, где хотите сообщить о пробке: ");
                        buffPlace1 = in.next();
                        buffPlace2 = in.next();
                        System.out.println("Введите загруженность: ");
                        buffBackup = in.nextInt();
                        map.addBackupInfo(buffPlace1,buffPlace2,buffBackup,currentUser);
                        break;
                    case 2:
                        System.out.println("Введите два места, кратчайший путь между которыми хотите найти: ");
                        buffPlace1 = in.next();
                        buffPlace2 = in.next();
                        buffArray = map.generatePath(buffPlace1,buffPlace2);
                        for(String point: buffArray){
                            System.out.print(point + " ");
                        }
                        System.out.println();
                        break;
                    case 3:
                        break;
                }
            }catch (Exception e){
                    System.out.println(e.toString());
                }
        }
    }
}
