package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener {
    protected static ArrayList<User> users = new ArrayList<User>();
    protected static Map map;
    protected static User currentUser = null;
    private ClassSeriazableUsers fileUsers;
    private ClassSeriazableMap fileMap;
    private JButton login;
    private JButton register;
    protected Font font = new Font("TimesRoman", Font.PLAIN, 14);
    MainWindow(){

    }
    MainWindow(String init) {
        super("Вход в систему");
        try {
            fileUsers = new ClassSeriazableUsers("users.dat");
            fileMap = new ClassSeriazableMap("map.dat");
            users = fileUsers.readCollection();
            map = fileMap.read();

            //users.add(new Admin("Admin","123","123"));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        fileUsers.writeCollection(users);
                        fileMap.write(map);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            Box buttons = Box.createHorizontalBox();
            register = new JButton("Зарегистрироваться");
            register.setFont(font);
            register.setForeground(Color.blue);
            register.addActionListener(this);
            login = new JButton("Войти в систему");
            login.setFont(font);
            login.setForeground(Color.blue);
            login.addActionListener(this);
            buttons.add(login);
            buttons.add(Box.createHorizontalStrut(25));
            buttons.add(register);
            buttons.setBorder(new EmptyBorder(60, 60, 60, 60));
            setContentPane(buttons);
            pack();
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setBackground(Color.lightGray);
        }
        catch (IOException e) { JOptionPane.showMessageDialog(null, "Ошибка при загрузке файла");}
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == login)
            new Login();
        if (actionEvent.getSource() == register)
            new Registration();
    }

    public static boolean addUser(String name, String login, String password, String repeation) {
        if (!password.equals(repeation)) {
            JOptionPane.showMessageDialog(null, "Пароли не совпадают.");
            return false;
        }
        for (User user : users) {
            if (user.login.equals(login)) {
                JOptionPane.showMessageDialog(null, "Пользователь с таким логином уже существует.");
                return false;
            }
        }
        users.add(new Driver(name, login, password));
        return true;

    }



    public static boolean findUser(String login, String password) {
        for (User user : users) {
            if (user.enter(login, password)) {
                currentUser = user;
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
        return false;
    }

    public static ArrayList<Edge> getEdges() {
        ArrayList<String[]> buffEdges = new ArrayList<String[]>();
        ArrayList<Edge> edges = map.getEdges();
        int buff = 0;
        for (Edge edge : edges) {
            buffEdges.add(new String[3]);
            buffEdges.get(buff)[0] = edge.getPoints()[0];
            buffEdges.get(buff)[1] = edge.getPoints()[1];
            buffEdges.get(buff)[2] = edge.getBackup() + "";
            buff++;
        }
        return map.getEdges();
    }
}
