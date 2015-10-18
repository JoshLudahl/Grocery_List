package com.example.ohlaph.first;

import android.app.Activity;
import android.content.res.AssetManager;
import java.lang.Object;
import java.io.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class List implements Serializable {

    private ArrayList<Item> list;
    private Scanner reader;

    public List() throws Exception {
        this.list = new ArrayList<Item>();
    }

    public void addItem(String name) {

        if (!name.trim().isEmpty()) {
            this.list.add(new Item(name));
        }

    }

    public void removeItem(int name) {
        this.list.remove(name);
    }


    public void importList(String filePath) throws Exception {
        File file = new File(filePath);
        reader = new Scanner(file);

        try {
            while (reader.hasNext()) {
                String line = reader.nextLine();
                addItem(line);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        reader.close();
    }


    public ArrayList<Item> populate() {
        return this.list;
    }


    public void exportList(String filePath) {

        File file = new File(filePath);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            for (Item item : list) {
                out.write(item.getItem());
                out.newLine();
            }
            out.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }


    public void clearList(String filePath) {

        try {
            File f = new File(filePath);
            f.delete();
        }

        catch (Exception e) {
            System.out.println(e);
        }
        this.list.clear();
    }
}
