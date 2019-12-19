package  com.company;

import java.io.Serializable;

public class Edge implements Serializable {
    private String[] points = new String[2];
    private int backup;
    private static final long serialVersionUID = 42L;

    Edge(String place1, String place2, int backup) {
        points[0] = place1;
        points[1] = place2;
        this.backup = backup;
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