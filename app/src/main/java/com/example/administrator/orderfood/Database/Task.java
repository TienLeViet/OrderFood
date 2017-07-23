package com.example.administrator.orderfood.Database;

/**
 * Created by Administrator on 22/07/2017.
 */

public class Task {

    private int id;
    private int listId;
    private int number;
    private String content;

    public Task() {}

    public Task(int listId, int number, String content) {
        this.listId = listId;
        this.number = number;
        this.content = content;
    }

    public Task(int id, int listId, int number, String content) {
        this.id = id;
        this.listId = listId;
        this.number = number;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int numberTable) {
        this.number = numberTable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
