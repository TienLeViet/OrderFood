package com.example.administrator.orderfood.Pay.CustomDisplay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.orderfood.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 08/08/2017.
 */

public class MyDisplayAdapter extends ArrayAdapter<Display> {

    Activity activity;
    int myLayout;
    ArrayList<Display> myArr = null;

    public MyDisplayAdapter(Activity activity, int myLayout, ArrayList<Display> myArr) {
        super(activity, myLayout, myArr);
        this.activity = activity;
        this.myLayout = myLayout;
        this.myArr = myArr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(myLayout, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView_itemDisplay);
        textView.setText(myArr.get(position).toString());

        return convertView;
    }
}
