package com.example.administrator.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class TypeOfMenu_2 extends AppCompatActivity {

    Spinner spinner_table;
    Button button_foods;
    Button button_drinks;
    Button button_pay;
    ArrayList<NumberOfTables> arrayList = null;
    MySpinnerAdapter mySpinnerAdapter = null;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_menu);
        // Ánh xạ của activity_type_of_menu.
        addControls();
        // Events handler of activity_type_of_menu.xml.
        addEvents();
    }

//==================================================================================================

    public void addControls() {
        spinner_table = (Spinner) findViewById(R.id.spinner_table);
        button_foods  = (Button) findViewById(R.id.button_foods);
        button_drinks = (Button) findViewById(R.id.button_drinks);
        button_pay    = (Button) findViewById(R.id.button_pay);
    }
    // Lưu trữ dữ liệu tạm thời của số được chọn.
    int temp;

    public void addEvents() {
        spinnerEvent();
        foodsButtonEvent();
        drinksButtonEvent();
    }

//==================================================================================================

    public void spinnerEvent() {
        // Create data source.
        arrayList = new ArrayList<NumberOfTables>();
        arrayList.add(new NumberOfTables(1));
        arrayList.add(new NumberOfTables(2));
        arrayList.add(new NumberOfTables(3));
        arrayList.add(new NumberOfTables(4));
        arrayList.add(new NumberOfTables(5));
        arrayList.add(new NumberOfTables(6));
        arrayList.add(new NumberOfTables(7));
        arrayList.add(new NumberOfTables(8));
        arrayList.add(new NumberOfTables(9));
        arrayList.add(new NumberOfTables(10));
        arrayList.add(new NumberOfTables(11));
        // Đưa data source vào adapter.
        mySpinnerAdapter = new MySpinnerAdapter(TypeOfMenu_2.this, R.layout.my_number, arrayList);
        // Hiển thị các phần tử con trong danh sách.
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Đưa adapter vào spinner để lựa chọn.
        spinner_table.setAdapter(mySpinnerAdapter);
        // Khi spinner được chọn.
        spinner_table.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp = arrayList.get(position).getNumber();
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
                Intent intent = new Intent(TypeOfMenu_2.this, Foods_3.class);
                // Hiểu như là một container.
                Bundle bundle = new Bundle();
                // Đưa dữ liệu vào bundle.
                bundle.putInt("tableNumber", temp);
                // Đưa bundle vào Intent.
                intent.putExtra("MyPackage", bundle);
                startActivity(intent);
            }
        });
    }

    public void drinksButtonEvent() {
        button_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
