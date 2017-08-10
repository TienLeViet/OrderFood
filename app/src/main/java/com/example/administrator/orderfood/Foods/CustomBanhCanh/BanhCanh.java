package com.example.administrator.orderfood.Foods.CustomBanhCanh;

/**
 * Created by Administrator on 06/07/2017.
 */

public class BanhCanh {
    private int id;
    private int table;
    private String content;

    public BanhCanh() {
    }

    public BanhCanh(int table, String content) {
        this.table = table;
        this.content = content;
    }

    public BanhCanh(int id, int table, String content) {
        this.id = id;
        this.table = table;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
