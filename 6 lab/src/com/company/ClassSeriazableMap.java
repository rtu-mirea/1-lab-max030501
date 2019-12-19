package com.company;

import java.io.*;


public class ClassSeriazableMap {
    private String fileName;

    public ClassSeriazableMap(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists())
            file.createNewFile();
        this.fileName = fileName;
    }

    public void write(Map map) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(map);
        out.close();
    }

    public Map read() throws IOException{
        Map buffMap = new Map();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            buffMap = (Map) in.readObject();
            in.close();
        } catch (EOFException e) {
        }catch (ClassNotFoundException e){
        }
        return buffMap;
    }
}
