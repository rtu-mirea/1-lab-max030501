package com.company;


import java.io.Serializable;
import java.util.*;

public class Map implements Serializable {
    int INF = Integer.MAX_VALUE / 2;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ArrayList<String> nodes = new ArrayList<>();
    private static final long serialVersionUID = 42L;

    public void addBackupInfo(String place1, String place2, int backup) {
        String buffEqual[] = {place1, place2};
        String buffEqual1[] = {place2, place1};

        for (Edge edge : edges) {
            if (Arrays.equals(edge.getPoints(), buffEqual) | Arrays.equals(edge.getPoints(), buffEqual1)) {
                edge.setBackup(backup);
                return;
            }
        }
        return;
    }
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    public void clearEdges(){
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public void addEdge(Edge edge)  {
        if (!nodes.contains(edge.getPoints()[0]))
            nodes.add(edge.getPoints()[0]);
        if (!nodes.contains(edge.getPoints()[1]))
            nodes.add(edge.getPoints()[1]);
        edges.add(edge);
    }
    public void deleteEdge(String place1,String place2){
        String buffEqual[] = {place1, place2};
        String buffEqual1[] = {place2, place1};
        nodes.remove(place1);
        nodes.remove(place2);
        for (Edge edge : edges) {
            if (Arrays.equals(edge.getPoints(), buffEqual) | Arrays.equals(edge.getPoints(), buffEqual1)) {
                edges.remove(edge);
                return;
            }
        }
    }

    public String[] getNodes(){
        if (nodes.isEmpty())
            return new String[0];
        else
            return nodes.toArray(new String[nodes.size()]);
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

    public List generatePath(String place1, String place2){
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
}