package com.example.administrator.orderfood.SpinnerTable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.administrator.orderfood.R;
import com.example.administrator.orderfood.SpinnerTable.NumberOfTables;

import java.util.ArrayList;

/**
 * Created by Administrator on 05/07/2017.
 */

public class MySpinnerAdapter extends ArrayAdapter<NumberOfTables> {

    Activity context;// View uses this MySpinnerAdapter.
    int mySpinnerLayout;// The layout (my_number.xml) that you want to use, and it is design by yourself.
    ArrayList<NumberOfTables> mySpinnerList;// Number image.

    // This constructor is custom the spinner in NumberOfTable.java.
    public MySpinnerAdapter(Activity context, int mySpinnerLayout, ArrayList<NumberOfTables> mySpinnerList) {
        super(context, mySpinnerLayout, mySpinnerList);
        this.context = context;
        this.mySpinnerLayout = mySpinnerLayout;
        this.mySpinnerList = mySpinnerList;
    }

    // position: vị trí muốn lấy trong mySpinnerList.
    // convertView: để lấy id trong my_number.xml.
    // parent: ???
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Vẫn chưa rõ.
        LayoutInflater inflater = context.getLayoutInflater();
        // Đưa layout mình muốn hiển thị vào view.
        convertView = inflater.inflate(mySpinnerLayout, null);
        // Ánh xạ của my_number.xml.
        ImageView imageView_number = (ImageView) convertView.findViewById(R.id.imageView_number);

        solveImages(position, imageView_number);

        return convertView;
    }

//==================================================================================================

    public void solveImages(int position, ImageView imageView_number) {
        if (mySpinnerList.get(position).getNumber() == 1) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.mot);
        }
        if (mySpinnerList.get(position).getNumber() == 2) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.hai);
        }
        if (mySpinnerList.get(position).getNumber() == 3) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.ba);
        }
        if (mySpinnerList.get(position).getNumber() == 4) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.bon);
        }
        if (mySpinnerList.get(position).getNumber() == 5) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.nam);
        }
        if (mySpinnerList.get(position).getNumber() == 6) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.sau);
        }
        if (mySpinnerList.get(position).getNumber() == 7) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.bay);
        }
        if (mySpinnerList.get(position).getNumber() == 8) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.tam);
        }
        if (mySpinnerList.get(position).getNumber() == 9) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.chin);
        }
        if (mySpinnerList.get(position).getNumber() == 10) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.muoi);
        }
        if (mySpinnerList.get(position).getNumber() == 11) {
            // Custom spinner lại cho đẹp.
            imageView_number.setImageResource(R.drawable.muoimot);
        }
    }
}
