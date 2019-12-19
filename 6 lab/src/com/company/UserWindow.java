package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;


public class UserWindow extends MainWindow implements ActionListener {
    JButton setBackupButton;
    JButton getShortwayButton;
    JLabel setBackupLabel;
    JComboBox ComboBoxBackup;
    JComboBox ComboBox1;
    JComboBox ComboBox2;
    JLabel shortWay;
    String []backupLevels = new String[]{"1","2","3","4","5","6","7","8","9","10"};

    UserWindow(){
        setTitle("Меню программы");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Box ComboBoxes = Box.createHorizontalBox();
        Box BackupBoxes = Box.createHorizontalBox();
        Box ButtonBoxes = Box.createHorizontalBox();
        Box ShortWay = Box.createHorizontalBox();
        Box main = Box.createVerticalBox();
        ComboBox1 = new JComboBox(map.getNodes());
        ComboBox2 = new JComboBox(map.getNodes());
        setBackupLabel = new JLabel("Выберите уровень загруженности:");
        setBackupLabel.setFont(font);
        setBackupLabel.setForeground(Color.blue);
        ComboBoxBackup = new JComboBox(backupLevels);
        ((JLabel)ComboBoxBackup.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ComboBoxBackup.setFont(font);
        ComboBoxBackup.setForeground(Color.blue);
        setBackupButton = new JButton("Обновить загруженность");
        setBackupButton.setFont(font);
        setBackupButton.addActionListener(this);
        setBackupButton.setForeground(Color.blue);
        getShortwayButton = new JButton("Найти кратчайший путь");
        getShortwayButton.setFont(font);
        getShortwayButton.addActionListener(this);
        getShortwayButton.setForeground(Color.blue);
        shortWay = new JLabel(" ");
        shortWay.setFont(font);
        shortWay.setForeground(Color.blue);

        ShortWay.add(shortWay);
        ComboBoxes.add(ComboBox1);
        ComboBoxes.add(Box.createHorizontalStrut(15));
        ComboBoxes.add(ComboBox2);
        BackupBoxes.add(setBackupLabel);
        BackupBoxes.add(Box.createHorizontalStrut(15));
        BackupBoxes.add(ComboBoxBackup);
        ButtonBoxes.add(getShortwayButton);
        ButtonBoxes.add(Box.createHorizontalStrut(15));
        ButtonBoxes.add(setBackupButton);
        main.add(ComboBoxes);
        main.add(Box.createVerticalStrut(15));
        main.add(BackupBoxes);
        main.add(Box.createVerticalStrut(15));
        main.add(ShortWay);
        main.add(Box.createVerticalStrut(15));
        main.add(ButtonBoxes);
        main.setBorder(new EmptyBorder(12,12,12,12));

        setContentPane(main);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setBackground(Color.lightGray);
        if (map.getNodes().length == 0) {
            JOptionPane.showMessageDialog(null, "Узлов нет");
            dispose();
        }

    }




    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String buffPlace1 =ComboBox1.getSelectedItem().toString();
        String buffPlace2 = ComboBox2.getSelectedItem().toString();
        if(buffPlace1.equals(buffPlace2)){
            JOptionPane.showMessageDialog(null, "Вы выбрали два одинаковых узла.");
            return;
        }
        if(actionEvent.getSource() == setBackupButton){
            ArrayList<Edge> buffList = getEdges();
            int buffBackup = Integer.parseInt(ComboBoxBackup.getSelectedItem().toString());
            boolean isInEdges = false;
            for(Edge edge: buffList){
                if(edge.getPoints()[0].equals(buffPlace1) & edge.getPoints()[1].equals(buffPlace2)|edge.getPoints()[1].equals(buffPlace1) & edge.getPoints()[0].equals(buffPlace2)) {
                    isInEdges = true;
                    break;
                }
            }
            if (isInEdges) {
                map.addBackupInfo(buffPlace1, buffPlace2, buffBackup);
                JOptionPane.showMessageDialog(null, "Загруженность узла изменена.");
            }
            else
                JOptionPane.showMessageDialog(null, "Таких узлов в системе нет.");
        }
        if(actionEvent.getSource() == getShortwayButton){
            shortWay.setText(map.generatePath(buffPlace1,buffPlace2).toString());
        }
    }
}
