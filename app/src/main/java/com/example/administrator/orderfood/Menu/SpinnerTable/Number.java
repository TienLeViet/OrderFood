package com.example.administrator.orderfood.Menu.SpinnerTable;

/**
 * Created by Administrator on 05/07/2017.
 */

public class Number {
    private int number;

    public Number() {
    }

    public Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + "";
    }
}
