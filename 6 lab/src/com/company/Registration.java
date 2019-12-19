package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Registration extends MainWindow implements ActionListener {
    JTextField nameField;
    JLabel nameLabel;
    JTextField loginField;
    JLabel loginLabel;
    JPasswordField passwordField;
    JLabel passwordLabel;
    JPasswordField passwordFieldRepeation;
    JLabel passwordLabelRepeation;
    JButton enter;
    Registration(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Регистрация");
        Box main = Box.createVerticalBox();
        Box nameFields = Box.createHorizontalBox();
        Box loginFields = Box.createHorizontalBox();
        Box passwordFields = Box.createHorizontalBox();
        Box passwordFieldsRepeation = Box.createHorizontalBox();
        Box btn = Box.createHorizontalBox();
        nameField = new JTextField(15);
        nameField.setFont(font);
        nameField.setForeground(Color.blue);
        nameLabel = new JLabel("Имя:");
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.blue);
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
        passwordFieldRepeation = new JPasswordField(15);
        passwordFieldRepeation.setFont(font);
        passwordFieldRepeation.setForeground(Color.blue);
        passwordLabelRepeation = new JLabel("Повторите пароль:");
        passwordLabelRepeation.setFont(font);
        passwordLabelRepeation.setForeground(Color.blue);
        enter = new JButton("Зарегистрироваться");
        enter.setFont(font);
        enter.setForeground(Color.blue);
        enter.addActionListener(this);
        nameFields.add(nameLabel);
        nameFields.add(Box.createHorizontalStrut(20));
        nameFields.add(nameField);
        loginFields.add(loginLabel);
        loginFields.add(Box.createHorizontalStrut(20));
        loginFields.add(loginField);
        passwordFields.add(passwordLabel);
        passwordFields.add(Box.createHorizontalStrut(20));
        passwordFields.add(passwordField);
        passwordFieldsRepeation.add(passwordLabelRepeation);
        passwordFieldsRepeation.add(Box.createHorizontalStrut(20));
        passwordFieldsRepeation.add(passwordFieldRepeation);
        btn.add(Box.createHorizontalGlue());
        btn.add(enter);
        nameLabel.setPreferredSize(passwordLabelRepeation.getPreferredSize());
        loginLabel.setPreferredSize(passwordLabelRepeation.getPreferredSize());
        passwordLabel.setPreferredSize(passwordLabelRepeation.getPreferredSize());
        main.add(nameFields);
        main.add(Box.createVerticalStrut(12));
        main.add(loginFields);
        main.add(Box.createVerticalStrut(12));
        main.add(passwordFields);
        main.add(Box.createVerticalStrut(12));
        main.add(passwordFieldsRepeation);
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
            if(addUser(nameField.getText(),loginField.getText(),passwordField.getText(),passwordFieldRepeation.getText())) {
                JOptionPane.showMessageDialog(null, "Пользователь зарегистрирован");
                dispose();
            }
        }
    }
}
