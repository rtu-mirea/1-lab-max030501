package com.company;

import java.util.*;

public class Map {
    int INF = Integer.MAX_VALUE / 2;
    private List<Edge> edges = new ArrayList<Edge>();
    private List<String> nodes = new ArrayList<>();

    public void addBackupInfo(String place1, String place2, int backup, User currentUser) throws Exception {
        if (backup > 10 | backup < 0) throw new Exception("Выбран неверный уровень загруженности.");
        String buffEqual[] = {place1, place2};
        String buffEqual1[] = {place2, place1};

        for (Edge edge : edges) {
            if (Arrays.equals(edge.getPoints(), buffEqual) | Arrays.equals(edge.getPoints(), buffEqual1)) {
                edge.setBackup(backup);
                return;
            }
        }
        if (currentUser instanceof Driver) throw new Exception("Такого узла не существует.");
        else {
            addEdge(place1, place2);
            edges.get(edges.size() - 1).setBackup(backup);
        }
    }

    public void addEdge(String place1, String place2) throws Exception {
        if (place1.equals(place2)) throw new Exception("Районы не должны совпадать.");
        String buffEqual[] = {place1, place2};
        String buffEqual1[] = {place2, place1};
        for (Edge edge : edges) {
            if (Arrays.equals(edge.getPoints(), buffEqual) | Arrays.equals(edge.getPoints(), buffEqual1))
                throw new Exception("Такой узел уже существует.");
        }
        if (!nodes.contains(place1))
            nodes.add(place1);
        if (!nodes.contains(place2))
            nodes.add(place2);

        edges.add(new Edge(place1, place2));
    }

    public float getAverageBackup() {
        float buffBackup = 0;
        for (Edge edge : edges) {
            if (edge.getBackup() != -1)
                buffBackup += edge.getBackup();
        }
        return buffBackup / edges.size();
    }

    int[][] generateMatrix() {
        boolean check;
        int matrix[][] = new int[nodes.size()][nodes.size()];
        for (int i = 0; i < nodes.size(); i++)
            for (int j = i; j < nodes.size(); j++) {

                if (i == j) {
                    matrix[i][j] = 0;
                    continue;
                }
                String buff1 = nodes.get(i);
                String buff2 = nodes.get(j);
                check = true;
                for (Edge edge : edges) {
                    if (edge.getPoints()[0].equals(buff1) & edge.getPoints()[1].equals(buff2) | edge.getPoints()[1].equals(buff1) & edge.getPoints()[0].equals(buff2)) {
                        int buff = edge.getBackup();
                        if (buff == -1) {
                            check = false;
                            matrix[i][j] = INF;
                            matrix[j][i] = INF;
                            break;
                        }
                        matrix[i][j] = buff;
                        matrix[j][i] = buff;
                        check = false;
                    }
                }
                if (check) {
                    matrix[i][j] = INF;
                    matrix[j][i] = INF;
                }

            }
        return matrix;
    }

    public List generatePath(String place1, String place2) throws  Exception {
        if (place1.equals(place2)) throw new Exception("Районы не должны совпадать.");
        if(!nodes.contains(place1) | !nodes.contains(place2))
            throw new Exception("Таких узлов нет");
        int indexBuff1 = nodes.indexOf(place1);
        int indexBuff2 = nodes.indexOf(place2);
        List<String> res = new ArrayList<>();
        int matrix[][] = generateMatrix();
        int path[][] = new int[nodes.size()][nodes.size()];
        for (int i = 0; i < nodes.size(); i++)
            for (int j = 0; j < nodes.size(); j++)
                for (int k = 0; k < nodes.size(); k++)
                    if (matrix[j][k] > matrix[j][i] + matrix[i][k]) {
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                        path[j][k] = i;
                    }
        res.add(place2);
        while (path[indexBuff1][indexBuff2] != 0) {
            res.add(nodes.get(path[indexBuff1][indexBuff2]));
            indexBuff2 = path[indexBuff1][indexBuff2];

        }
        res.add(place1);
        Collections.reverse(res);
        return res;
    }

    private static class Edge {
        private String[] points = new String[2];
        private int backup;

        Edge(String place1, String place2) {
            points[0] = place1;
            points[1] = place2;
            backup = -1;
        }

        public String[] getPoints() {
            return points;
        }

        public int getBackup() {
            return backup;
        }

        public void setBackup(int backup) {
            this.backup = backup;
        }
    }
}