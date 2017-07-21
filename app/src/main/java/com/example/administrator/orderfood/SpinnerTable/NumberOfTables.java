package com.example.administrator.orderfood.SpinnerTable;

/**
 * Created by Administrator on 05/07/2017.
 */

public class NumberOfTables {
    private int number;

    public NumberOfTables() {
    }

    public NumberOfTables(int number) {
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
        return this.number + "";
    }
}
