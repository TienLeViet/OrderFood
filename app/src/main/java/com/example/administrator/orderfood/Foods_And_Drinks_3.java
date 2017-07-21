package com.example.administrator.orderfood;

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
import android.widget.TabHost;
import android.widget.Toast;

import com.example.administrator.orderfood.Drinks.MyDrinksAdapter;
import com.example.administrator.orderfood.Drinks.NuocUong;
import com.example.administrator.orderfood.Food.BanhCanh;
import com.example.administrator.orderfood.Food.MyFoodAdapter;

import java.util.ArrayList;

public class Foods_And_Drinks_3 extends AppCompatActivity {

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
    // Những món ăn đã order sẽ được thêm vào danh sách.
    ArrayList<BanhCanh> myBanhCanhArrayList = null;
    MyFoodAdapter myFoodAdapter = null;

    ListView listView_menuDrink;
    ArrayList<NuocUong> myNuocUongArrayList = null;
    MyDrinksAdapter myDrinksAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_and_drinks);
        // Ánh xạ.
        addControls();
        // Solve custom listView.
        customFoodListview();
        customDrinkListView();

        // Cấu hình tab.
        loadTabs();
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
        listView_foodsWaiting = (ListView) findViewById(R.id.listView_foodsWaiting);
        button_next = (Button) findViewById(R.id.button_next);

        listView_menuDrink = (ListView) findViewById(R.id.listView_drinks);
    }

    public void customFoodListview() {
        myBanhCanhArrayList = new ArrayList<BanhCanh>();
        myFoodAdapter = new MyFoodAdapter(Foods_And_Drinks_3.this, R.layout.my_food, myBanhCanhArrayList);
        listView_foodsWaiting.setAdapter(myFoodAdapter);
    }

    public void customDrinkListView() {
        myNuocUongArrayList = new ArrayList<NuocUong>();
        myNuocUongArrayList.add(new NuocUong("Coca cola"));
        myNuocUongArrayList.add(new NuocUong("Pepsi"));
        myNuocUongArrayList.add(new NuocUong("Aquafina"));
        myNuocUongArrayList.add(new NuocUong("Bò húc"));
        myNuocUongArrayList.add(new NuocUong("Sting đỏ"));
        myNuocUongArrayList.add(new NuocUong("Sting vàng"));
        myNuocUongArrayList.add(new NuocUong("7up"));
        myNuocUongArrayList.add(new NuocUong("Nước mía"));
        myNuocUongArrayList.add(new NuocUong("Trà đá"));
        myDrinksAdapter = new MyDrinksAdapter(Foods_And_Drinks_3.this, R.layout.my_drinks, myNuocUongArrayList);
        listView_menuDrink.setAdapter(myDrinksAdapter);
    }

    public void loadTabs() {
        final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        // gọi lệnh setup.
        tab.setup();
        TabHost.TabSpec spec;
        // Tạo Tab1.
        spec = tab.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Thức ăn");
        tab.addTab(spec);
        // Tạo Tab2.
        spec = tab.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Thức uống");
        tab.addTab(spec);
        // Thiết lập tab mặc định được chọn ban đầu là tab 0.
        tab.setCurrentTab(0);
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                String s = "Tab tag = " + tabId + "; index = " + tab.getCurrentTab();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                
            }
        });
    }

    public void addEvents() {
        solve_TypeOfMenu_Intent();
        solve_CheckBoxs_And_RadioButtons();
        eventAddButton();
        eventDeleteButton();
        eventNextButton();

    }

//==================================================================================================

    public int solve_TypeOfMenu_Intent() {
        // Nhận Intent từ TypeOfMenu.java.
        Intent myIntent = getIntent();
        // Nhận bundle dựa vào tên.
        Bundle myBundle = myIntent.getBundleExtra("MyPackage");
        // Lấy nội dung trong bundle.
        int table = myBundle.getInt("tableNumber");
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
                displayListView();
            }
        });
    }

    public void eventDeleteButton() {
        imageButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListView();
            }
        });
    }

    public void eventNextButton() {
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

//==================================================================================================

    public void displayListView() {
        BanhCanh banhCanh = new BanhCanh();
        banhCanh.setContent(solve_CheckBoxs_And_RadioButtons());
        myBanhCanhArrayList.add(banhCanh);
        myFoodAdapter.notifyDataSetChanged();
        // If the CheckBox is checked then setting is false.
        editAsFirst();
    }

    public void deleteListView() {
        // Đi ngược danh sách, kiểm tra phần tử nào checked thì xóa đúng vị trí đó ra khỏi myBanhCanhArrayList.
        for (int i = listView_foodsWaiting.getChildCount() - 1; i >= 0; i--) {
            // Lấy ra dòng thứ i trong ListView.
            // Dòng thứ i sẽ có 3 phần tử: ImageView, TextView, CheckBox.
            View v = listView_foodsWaiting.getChildAt(i);
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

//==================================================================================================

    public void editAsFirst() {
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
