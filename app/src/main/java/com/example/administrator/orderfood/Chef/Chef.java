package com.example.administrator.orderfood.Chef;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.orderfood.Database.OrderFoodDatabase;
import com.example.administrator.orderfood.Database.Task;
import com.example.administrator.orderfood.R;

import java.util.ArrayList;

public class Chef extends AppCompatActivity {

    TextView textView_prepare;
    Button button_cook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        // Ánh xạ.
        addControls();

        addEvents();
    }

//==================================================================================================

    public void addControls() {
        textView_prepare = (TextView) findViewById(R.id.textView_prepare);
        button_cook = (Button) findViewById(R.id.button_cook);
    }

    public void addEvents() {
        solveTextView_prepare();
        solveButton_cook();
    }

//==================================================================================================

    public void solveTextView_prepare() {
        OrderFoodDatabase db = new OrderFoodDatabase(this);
        ArrayList<Task> tasks = db.getTasks("Foods");
        String content = "";
        for (Task t : tasks) {
            content += t.getNumber() + "    | " + t.getContent() + "\n";
        }
        textView_prepare.setText("Bàn | Nội dung\n\n" + content);
    }

    public void solveButton_cook() {
        button_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_prepare.setText("");
            }
        });
    }
}
