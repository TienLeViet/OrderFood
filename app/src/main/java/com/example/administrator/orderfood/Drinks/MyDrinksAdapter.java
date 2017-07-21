package com.example.administrator.orderfood.Drinks;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.orderfood.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 19/07/2017.
 */

public class MyDrinksAdapter extends ArrayAdapter<NuocUong> {
    Activity context;
    int myLayout;
    ArrayList<NuocUong> myArr;

    public MyDrinksAdapter(Activity context, int myLayout, ArrayList<NuocUong> myArr) {
        super(context, myLayout, myArr);
        this.context = context;
        this.myLayout = myLayout;
        this.myArr = myArr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(myLayout, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView_nameDrinks);

        textView.setText(myArr.get(position).getTenNuoc());
        return convertView;
    }
}
