package com.company;
import java.io.*;
import java.util.ArrayList;

public class ClassSeriazableUsers {
    private String fileName;

    public ClassSeriazableUsers(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists())
            file.createNewFile();
        this.fileName = fileName;

    }

    public void writeCollection(ArrayList<User> users) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        for (User user: users){
            out.writeObject(user);
        }
        out.close();
    }
    public ArrayList<User> readCollection() throws IOException {
        ArrayList<User> buffUsers = new ArrayList<>();
        User buffUser;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            while (true) {
                buffUser = (User) in.readObject();
                buffUsers.add(buffUser);
            }
        } catch (EOFException e) {
        }catch (ClassNotFoundException e){
        }
        return buffUsers;
    }


}