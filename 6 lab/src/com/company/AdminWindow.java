package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;


public class AdminWindow  extends MainWindow implements ActionListener {
    private Object[] columnsHeader = new String[]{"Начальный пункт", "Конечный пункт", "Загруженность"};
    private Object[] edge = new Object[3];
    private Object[] backupLevels = new Object[]{-1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private JButton addEdgeBtn;
    private JButton userMenu;
    private JButton saveEdges;
    private JButton deleteEdges;
    private DefaultTableModel tableModel;
    private JTable tableEdges;
    private JScrollPane scrollPane;
    private JComboBox comboBox;

    AdminWindow() {
        setTitle("Админ меню");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Box main = Box.createVerticalBox();
        Box table = Box.createHorizontalBox();
        Box buttons = Box.createHorizontalBox();
        comboBox = new JComboBox(backupLevels);
        comboBox.setFont(font);
        comboBox.setForeground(Color.blue);
        ((JLabel) comboBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);
        for (Edge currEdge : map.getEdges()) {
            edge[0] = currEdge.getPoints()[0];
            edge[1] = currEdge.getPoints()[1];
            edge[2] = currEdge.getBackup();
            tableModel.addRow(edge);
        }
        tableEdges = new JTable(tableModel) {
            DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

            {
                renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
            }

            DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();

            {
                renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
            }

            @Override
            public TableCellRenderer getCellRenderer(int arg0, int arg1) {
                if (arg1 == 2)
                    return renderRight;
                else
                    return renderLeft;
            }
        };
        tableEdges.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBox));
        tableEdges.setFont(font);
        tableEdges.setForeground(Color.blue);
        scrollPane = new JScrollPane(tableEdges);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        addEdgeBtn = new JButton("Добавить");
        addEdgeBtn.addActionListener(this);
        addEdgeBtn.setFont(font);
        addEdgeBtn.setForeground(Color.blue);
        userMenu = new JButton("Открыть меню пользователя");
        userMenu.addActionListener(this);
        userMenu.setFont(font);
        userMenu.setForeground(Color.blue);
        saveEdges = new JButton("Сохранить");
        saveEdges.addActionListener(this);
        saveEdges.setFont(font);
        saveEdges.setForeground(Color.blue);
        deleteEdges = new JButton("Удалить выделенные");
        deleteEdges.addActionListener(this);
        deleteEdges.setForeground(Color.blue);
        deleteEdges.setFont(font);

        buttons.add(Box.createHorizontalGlue());
        buttons.add(addEdgeBtn);
        buttons.add(Box.createHorizontalStrut(15));
        buttons.add(deleteEdges);
        buttons.add(Box.createHorizontalStrut(15));
        buttons.add(saveEdges);
        buttons.add(Box.createHorizontalStrut(15));
        buttons.add(userMenu);

        table.add(scrollPane);

        main.add(scrollPane);
        main.add(Box.createVerticalStrut(15));
        main.add(buttons);
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
        if (actionEvent.getSource() == addEdgeBtn) {
            tableModel.addRow(new Object[]{"", "", -1});
        }
        if(actionEvent.getSource() == userMenu){
            new UserWindow();
        }
        if(actionEvent.getSource() == saveEdges){
            ArrayList<Edge> buffList = new ArrayList<>();
            String buffPlace1,buffPlace2;
            String[] buffEqual;
            String[] buffEqual1;
            int buffBackup;
            for(int i = 1; i < tableModel.getRowCount()+1; i++) {
                buffPlace1 = tableModel.getValueAt(i - 1, 0).toString().trim();
                buffPlace2 = tableModel.getValueAt(i - 1, 1).toString().trim();
                if(buffPlace1.length() == 0 | buffPlace2.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Поля не должны быть пустыми.");
                    return;
                }
                buffBackup = Integer.parseInt(tableModel.getValueAt(i - 1, 2).toString());
                if (buffPlace1.equals(buffPlace2)){
                    JOptionPane.showMessageDialog(null, "Узлы в " + i + " строке одинаковые.");
                    return;
            }
                for(Edge edge: buffList) {
                    buffEqual = new String[]{buffPlace1, buffPlace2};
                    buffEqual1 = new String[]{buffPlace2, buffPlace1};
                    if (Arrays.equals(edge.getPoints(), buffEqual) | Arrays.equals(edge.getPoints(), buffEqual1)) {
                        JOptionPane.showMessageDialog(null, "Узлы в " + i + " строке уже создавались ранее.");
                        return;
                    }
                }
                buffList.add(new Edge(buffPlace1, buffPlace2, buffBackup));
            }
            map.clearEdges();
            for(Edge edge: buffList){
                map.addEdge(edge);
            }
            JOptionPane.showMessageDialog(null, "Узлы сохранены.");
            }
        if(actionEvent.getSource() == deleteEdges){
            int [] selectedRows = tableEdges.getSelectedRows();
            int temp;
            int n = selectedRows.length;
            for (int i = 0; i < n/2; i++) {
                temp = selectedRows[n-i-1];
                selectedRows[n-i-1] = selectedRows[i];
                selectedRows[i] = temp;
            }
            for(int selectedRow: selectedRows){
                map.deleteEdge(tableModel.getValueAt(selectedRow,0).toString(),tableModel.getValueAt(selectedRow,1).toString());
                tableModel.removeRow(selectedRow);
            }
            JOptionPane.showMessageDialog(null, "Узлы удалены.");
        }
        }
    }



