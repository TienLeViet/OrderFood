package com.example.administrator.orderfood.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.administrator.orderfood.Foods.Foods;
import com.example.administrator.orderfood.Pay.Pay;
import com.example.administrator.orderfood.R;
import com.example.administrator.orderfood.Menu.SpinnerTable.MySpinnerAdapter;
import com.example.administrator.orderfood.Menu.SpinnerTable.Number;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    Spinner spinner_table;
    Button button_foods;
    Button button_pay;
    ArrayList<Number> arrayList = null;
    MySpinnerAdapter mySpinnerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Ánh xạ của activity_menu.
        addControls();
        // Events handler of activity_menu
        addEvents();
    }

//==================================================================================================

    public void addControls() {
        spinner_table = (Spinner) findViewById(R.id.spinner_table);
        button_foods  = (Button) findViewById(R.id.button_foods);
        button_pay    = (Button) findViewById(R.id.button_pay);
    }

    // Lưu trữ dữ liệu tạm thời của số được chọn.
    int temp1, temp2;

    public void addEvents() {
        spinnerEvent();
        foodsButtonEvent();
        payButtonEvent();
    }

//==================================================================================================

    public void spinnerEvent() {
        // Create data source.
        arrayList = new ArrayList<Number>();
        arrayList.add(new Number(1));
        arrayList.add(new Number(2));
        arrayList.add(new Number(3));
        arrayList.add(new Number(4));
        arrayList.add(new Number(5));
        arrayList.add(new Number(6));
        arrayList.add(new Number(7));
        arrayList.add(new Number(8));
        arrayList.add(new Number(9));
        arrayList.add(new Number(10));
        arrayList.add(new Number(11));
        // Đưa data source vào adapter.
        mySpinnerAdapter = new MySpinnerAdapter(Menu.this, R.layout.my_number, arrayList);
        // Hiển thị các phần tử con trong danh sách.
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Đưa adapter vào spinner để lựa chọn.
        spinner_table.setAdapter(mySpinnerAdapter);
        // Khi spinner được chọn.
        spinner_table.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp1 = arrayList.get(position).getNumber();
                temp2 = arrayList.get(position).getNumber();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void foodsButtonEvent() {
        button_foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Foods.class);
                // Hiểu như là một container.
                Bundle bundle = new Bundle();
                // Đưa dữ liệu vào bundle.
                bundle.putInt("tableNumber1", temp1);
                // Đưa bundle vào Intent.
                intent.putExtra("MyPackage1", bundle);
                startActivity(intent);
            }
        });
    }

    public void payButtonEvent() {
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Pay.class);
                // Hiểu như là một container.
                Bundle bundle = new Bundle();
                // Đưa dữ liệu vào bundle.
                bundle.putInt("tableNumber2", temp2);
                // Đưa bundle vào Intent.
                intent.putExtra("MyPackage2", bundle);
                startActivity(intent);
            }
        });
    }
}
