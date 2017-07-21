package com.example.administrator.orderfood.Food;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.orderfood.Food.BanhCanh;
import com.example.administrator.orderfood.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 06/07/2017.
 */

public class MyFoodAdapter extends ArrayAdapter<BanhCanh> {

    Activity context;// Màn hình sẽ hiển thị cái giao diện my_food.xml.
    int myFoodLayout;// Giao diện my_foods.xml.
    ArrayList<BanhCanh> arrayList = null;// Món ăn đã được order.

    public MyFoodAdapter(Activity context, int myFoodLayout, ArrayList<BanhCanh> arrayList) {
        super(context, myFoodLayout, arrayList);
        this.context = context;
        this.myFoodLayout = myFoodLayout;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(myFoodLayout, null);

        ImageView imageView_tableNumber = (ImageView) convertView.findViewById(R.id.imageView_tableNumber);
        TextView textView_content = (TextView) convertView.findViewById(R.id.textView_content);

        // Lấy số được chọn trong Spinner và được chứa trong arrayList.
        int so = arrayList.get(position).getTable();
        solveNumbers(so, imageView_tableNumber);
        // Lấy nội dung từ arrayList đưa vào textView_content.
        textView_content.setText(arrayList.get(position).getContent());

        return convertView;
    }

//==================================================================================================

    public void solveNumbers(int so, ImageView imageView_tableNumber) {
        if (so == 1) {
            imageView_tableNumber.setImageResource(R.drawable.mot);
        }
        if (so == 2) {
            imageView_tableNumber.setImageResource(R.drawable.hai);
        }
        if (so == 3) {
            imageView_tableNumber.setImageResource(R.drawable.ba);
        }
        if (so == 4) {
            imageView_tableNumber.setImageResource(R.drawable.bon);
        }
        if (so == 5) {
            imageView_tableNumber.setImageResource(R.drawable.nam);
        }
        if (so == 6) {
            imageView_tableNumber.setImageResource(R.drawable.sau);
        }
        if (so == 7) {
            imageView_tableNumber.setImageResource(R.drawable.bay);
        }
        if (so == 8) {
            imageView_tableNumber.setImageResource(R.drawable.tam);
        }
        if (so == 9) {
            imageView_tableNumber.setImageResource(R.drawable.chin);
        }
        if (so == 10) {
            imageView_tableNumber.setImageResource(R.drawable.muoi);
        }
        if (so == 11) {
            imageView_tableNumber.setImageResource(R.drawable.muoimot);
        }
    }
}
