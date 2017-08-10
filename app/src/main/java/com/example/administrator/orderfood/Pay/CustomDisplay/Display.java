package com.example.administrator.orderfood.Pay.CustomDisplay;

/**
 * Created by Administrator on 08/08/2017.
 */

public class Display {

    private String noiDung;

    public Display() {
    }

    public Display(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return noiDung;
    }
}
