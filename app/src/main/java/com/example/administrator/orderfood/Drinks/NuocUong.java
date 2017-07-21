package com.example.administrator.orderfood.Drinks;

/**
 * Created by Administrator on 19/07/2017.
 */

public class NuocUong {
    private int id;
    private String tenNuoc;
    private int soLuong;

    public NuocUong() {
    }

    public NuocUong(String tenNuoc) {
        this.tenNuoc = tenNuoc;
    }

    public NuocUong(int id, String tenNuoc) {
        this.id = id;
        this.tenNuoc = tenNuoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNuoc() {
        return tenNuoc;
    }

    public void setTenNuoc(String tenNuoc) {
        this.tenNuoc = tenNuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return this.getTenNuoc();
    }
}
