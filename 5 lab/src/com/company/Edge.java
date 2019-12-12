package com.company;

import java.io.Serializable;

public class Edge implements Serializable {
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

    public void setPoints(String[] points){
        this.points = points;
    }

    public void setBackup(int backup) {
        this.backup = backup;
    }
    @Override
    public String toString(){
        return "Начальный пункт - " + points[0] + "\n"+"Конечный пункт - "+points[1]+"\n" + "Загруженность пути - " + backup;
    }
}