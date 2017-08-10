package com.example.administrator.orderfood.Foods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.orderfood.Database.OrderFoodDatabase;
import com.example.administrator.orderfood.Database.Task;
import com.example.administrator.orderfood.Foods.CustomBanhCanh.BanhCanh;
import com.example.administrator.orderfood.Foods.CustomBanhCanh.MyFoodAdapter;
import com.example.administrator.orderfood.R;

import java.util.ArrayList;

public class Foods extends AppCompatActivity {

    CheckBox checkBox_long;
    CheckBox checkBox_ca;
    CheckBox checkBox_cha;
    CheckBox checkBox_khongBot;
    CheckBox checkBox_khongMau;
    CheckBox checkBox_khongHanh;
    CheckBox checkBox_khongGiaVi;
    CheckBox checkBox_goiVe;

    RadioGroup radioGroup_hinhThuc;
    RadioButton radioButton_normalBowl;
    RadioButton radioButton_smallBowl;
    RadioButton radioButton_specialBowl;

    Button button_add;
    ImageButton imageButton_delete;
    ListView listView_foodsWaiting;
    Button button_next;

    ArrayList<BanhCanh> myBanhCanhArrayList = null;
    MyFoodAdapter myFoodAdapter = null;

    OrderFoodDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        // Ánh xạ.
        addControls();
        // Solve custom listView.
        customWaitingListview();

        // Event handler.
        addEvents();
    }

//==================================================================================================

    public void addControls() {
        checkBox_long = (CheckBox) findViewById(R.id.checkBox_long);
        checkBox_ca = (CheckBox) findViewById(R.id.checkBox_ca);
        checkBox_cha = (CheckBox) findViewById(R.id.checkBox_cha);
        checkBox_khongBot = (CheckBox) findViewById(R.id.checkBox_khongBot);
        checkBox_khongMau = (CheckBox) findViewById(R.id.checkBox_khongMau);
        checkBox_khongHanh = (CheckBox) findViewById(R.id.checkBox_khongHanh);
        checkBox_khongGiaVi = (CheckBox) findViewById(R.id.checkBox_khongGiaVi);
        checkBox_goiVe = (CheckBox) findViewById(R.id.checkbox_goiVe);

        radioGroup_hinhThuc = (RadioGroup) findViewById(R.id.radioGroup_hinhThuc);
        radioButton_normalBowl = (RadioButton) findViewById(R.id.radioButton_normalBowl);
        radioButton_smallBowl = (RadioButton) findViewById(R.id.radioButton_smallBowl);
        radioButton_specialBowl = (RadioButton) findViewById(R.id.radioButton_specialBowl);

        button_add = (Button) findViewById(R.id.button_add);
        imageButton_delete = (ImageButton) findViewById(R.id.imageButton_delete);
        button_next = (Button) findViewById(R.id.button_next);

        db = new OrderFoodDatabase(this);
    }

    public void customWaitingListview() {
        listView_foodsWaiting = (ListView) findViewById(R.id.listView_foodsWaiting);
        myBanhCanhArrayList = new ArrayList<BanhCanh>();
        myFoodAdapter = new MyFoodAdapter(Foods.this, R.layout.my_food, myBanhCanhArrayList);
        listView_foodsWaiting.setAdapter(myFoodAdapter);
    }

    public void addEvents() {

        solve_TypeOfMenu_Intent();
        solve_CheckBoxs_And_RadioButtons();
        eventAddButton();
        eventDeleteButton();
        eventCompleteButton();

    }

//==================================================================================================

    public int solve_TypeOfMenu_Intent() {
        // Nhận Intent từ Menu.java.
        Intent myIntent = getIntent();
        // Nhận bundle dựa vào tên.
        Bundle myBundle = myIntent.getBundleExtra("MyPackage1");
        // Lấy nội dung trong bundle.
        int table = myBundle.getInt("tableNumber1");
        return table;
    }

    public String solve_CheckBoxs_And_RadioButtons() {
        String content = "";
        // Kiểm tra CheckBox.
        if (checkBox_long.isChecked()) {
            content += checkBox_long.getText() + ", ";
        }
        if (checkBox_ca.isChecked()) {
            content += checkBox_ca.getText() + ", ";
        }
        if (checkBox_cha.isChecked()) {
            content += checkBox_cha.getText() + ", ";
        }
        if (checkBox_khongBot.isChecked()) {
            content += checkBox_khongBot.getText() + ", ";
        }
        if (checkBox_khongMau.isChecked()) {
            content += checkBox_khongMau.getText() + ", ";
        }
        if (checkBox_khongHanh.isChecked()) {
            content += checkBox_khongHanh.getText() + ", ";
        }
        if (checkBox_khongGiaVi.isChecked()) {
            content += checkBox_khongGiaVi.getText() + ", ";
        }
        if (checkBox_goiVe.isChecked()) {
            content += checkBox_goiVe.getText() + ", ";
        }
        // Kiểm tra RadioButton.
        int selected = radioGroup_hinhThuc.getCheckedRadioButtonId();
        if (selected == R.id.radioButton_normalBowl) {
            content += radioButton_normalBowl.getText() + "";
        } else {
            if (selected == R.id.radioButton_smallBowl) {
                content += radioButton_smallBowl.getText() + "";
            } else {
                content += radioButton_specialBowl.getText() + "";
            }
        }
        return content;
    }

    public void eventAddButton() {
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BanhCanh banhCanh = new BanhCanh(solve_TypeOfMenu_Intent(),
                        solve_CheckBoxs_And_RadioButtons());
                // Display on listview.
                myBanhCanhArrayList.add(banhCanh);
                myFoodAdapter.notifyDataSetChanged();
                // If the CheckBox is checked then setting is false.
                editFoodsAsFirst();
            }
        });
    }

    public void eventDeleteButton() {
        imageButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đi ngược danh sách, kiểm tra phần tử nào checked
                // thì xóa đúng vị trí đó ra khỏi myBanhCanhArrayList.
                for (int i = listView_foodsWaiting.getChildCount() - 1; i >= 0; i--) {
                    // Lấy ra dòng thứ i trong ListView.
                    // Dòng thứ i sẽ có 3 phần tử: ImageView, TextView, CheckBox.
                    v = listView_foodsWaiting.getChildAt(i);
                    // Lấy CheckBox ra kiểm tra.
                    CheckBox checkBox_delete = (CheckBox) v.findViewById(R.id.checkbox_delete);
                    if (checkBox_delete.isChecked()) {
                        // Xóa phần tử thứ i ra khỏi danh sách.
                        myBanhCanhArrayList.remove(i);
                    }
                }
                // Update giao diện.
                myFoodAdapter.notifyDataSetChanged();
            }
        });
    }

    public void eventCompleteButton() {
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đưa dữ liệu trong listView_foodsWaiting vào database.
                for (int i = 0; i < listView_foodsWaiting.getChildCount(); i++) {
                    Task task = new Task(1, myBanhCanhArrayList.get(i).getTable(),
                            myBanhCanhArrayList.get(i).getContent());
                    long insertID = db.insertTask(task);
                    if (insertID > 0) {
                        Toast.makeText(Foods.this, "Inserted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

//==================================================================================================

    public void editFoodsAsFirst() {
        if (checkBox_long.isChecked()) {
            checkBox_long.setChecked(false);
        }
        if (checkBox_ca.isChecked()) {
            checkBox_ca.setChecked(false);
        }
        if (checkBox_cha.isChecked()) {
            checkBox_cha.setChecked(false);
        }
        if (checkBox_khongBot.isChecked()) {
            checkBox_khongBot.setChecked(false);
        }
        if (checkBox_khongMau.isChecked()) {
            checkBox_khongMau.setChecked(false);
        }
        if (checkBox_khongHanh.isChecked()) {
            checkBox_khongHanh.setChecked(false);
        }
        if (checkBox_khongGiaVi.isChecked()) {
            checkBox_khongGiaVi.setChecked(false);
        }
        if (checkBox_goiVe.isChecked()) {
            checkBox_goiVe.setChecked(false);
        }
        if (!radioButton_normalBowl.isChecked()) {
            radioButton_normalBowl.setChecked(true);
        }
    }
}
