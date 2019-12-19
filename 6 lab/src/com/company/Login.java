package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends MainWindow implements ActionListener {
    private JTextField loginField;
    private JLabel loginLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton enter;
    Login(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Вход");
        Box main = Box.createVerticalBox();
        Box loginFields = Box.createHorizontalBox();
        Box passwordFields = Box.createHorizontalBox();
        Box btn = Box.createHorizontalBox();
        loginField = new JTextField(15);
        loginField.setFont(font);
        loginField.setForeground(Color.blue);
        loginLabel = new JLabel("Логин:");
        loginLabel.setFont(font);
        loginLabel.setForeground(Color.blue);
        passwordField = new JPasswordField(15);
        passwordField.setFont(font);
        passwordField.setForeground(Color.blue);
        passwordLabel = new JLabel("Пароль:");
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.blue);
        enter = new JButton("Войти");
        enter.setFont(font);
        enter.setForeground(Color.blue);
        enter.addActionListener(this);
        btn.add(Box.createHorizontalGlue());
        btn.add(enter);
        loginFields.add(loginLabel);
        loginFields.add(Box.createHorizontalStrut(20));
        loginFields.add(loginField);
        passwordFields.add(passwordLabel);
        passwordFields.add(Box.createHorizontalStrut(20));
        passwordFields.add(passwordField);
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
        main.add(loginFields);
        main.add(Box.createVerticalStrut(12));
        main.add(passwordFields);
        main.add(Box.createVerticalStrut(17));
        main.add(btn);
        main.setBorder(new EmptyBorder(12,12,12,12));
        setContentPane(main);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setBackground(Color.lightGray);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == enter) {
           if(findUser(loginField.getText(), passwordField.getText())) {
               dispose();
               if (currentUser instanceof Admin)
                   new AdminWindow();
               else
                   new UserWindow();
           }
        }
    }
}
