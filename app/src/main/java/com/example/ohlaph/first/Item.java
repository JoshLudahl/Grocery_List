package com.example.ohlaph.first;

import java.io.Serializable;

/**
 * Created by Ohlaph on 8/24/2015.
 */
public class Item implements Serializable{

    private String item;


    public Item(String item) {
        this.item = item;
    }

    public Item(String item, int price) {
        this.item = item;

    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item;
    }
}
