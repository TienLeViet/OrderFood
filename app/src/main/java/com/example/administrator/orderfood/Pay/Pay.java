package com.example.administrator.orderfood.Pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.orderfood.Database.OrderFoodDatabase;
import com.example.administrator.orderfood.Database.Task;
import com.example.administrator.orderfood.Pay.CustomDisplay.Display;
import com.example.administrator.orderfood.Pay.CustomDisplay.MyDisplayAdapter;
import com.example.administrator.orderfood.R;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {

    // Lấy Intent để trả về bàn order.
    TextView textView_orderTable;
    // Lấy dữ liệu trong cơ sở dữ liệu để hiển thị.
    ListView listView_display;
    Button button_thanhToan;

    ArrayList<Display> displayArrayList = null;
    MyDisplayAdapter displayAdapter = null;

    OrderFoodDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        // Ánh xạ.
        addControls();
        // Xử lý sự kiện.
        addEvents();
    }

//==================================================================================================

    public void addControls() {
        textView_orderTable = (TextView) findViewById(R.id.textView_orderTable);
        listView_display = (ListView) findViewById(R.id.listView_display);
        button_thanhToan = (Button) findViewById(R.id.button_thanhToan);

        displayArrayList = new ArrayList<Display>();
        displayAdapter = new MyDisplayAdapter(this, R.layout.my_display, displayArrayList);
        listView_display.setAdapter(displayAdapter);

        db = new OrderFoodDatabase(this);
    }

    public void addEvents() {
        // Hiển thị bàn cần tính tiền.
        solveNumberTable();
        // Hiển thị nội dung order của bàn.
        solveDataContentTable();
        // Nút thanh toán, báo giá, số lượng, xóa nội dung cũ.
        solvePayButton();
    }

//==================================================================================================

    // Biến tạm thời lưu trữ số bàn được chọn.
    int tempNumber = 0;

    public void solveNumberTable() {
        // Nhận Intent từ Menu.
        Intent intent = getIntent();
        // Lấy bundle trong Intent có tên "MyPackage".
        Bundle bundle = intent.getBundleExtra("MyPackage2");
        // Lấy nội dung trong bundle có tên "tableNumber".
        int number = bundle.getInt("tableNumber2");
        // Hiển thị lên textView_orderTable.
        textView_orderTable.setText("Bàn số " + number);
        tempNumber = number;
    }

    public void solveDataContentTable() {
        // Tạo đối tượng database để truy xuất dữ liệu.
        OrderFoodDatabase db = new OrderFoodDatabase(this);
        ArrayList<Task> tasks = db.getTasks("Foods");
        for (Task t : tasks) {
            if (t.getNumber() == tempNumber) {
                displayArrayList.add(new Display(t.getContent()));
            }
        }
    }

    public void solvePayButton() {
        button_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Task> tasks = db.getTasks("Foods");
                for (Task t : tasks) {
                    if (t.getNumber() == tempNumber) {
                        int deleteID = db.deleteTask(t.getId());
                        if (deleteID == 1) {
                            Toast.makeText(Pay.this, "Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}
