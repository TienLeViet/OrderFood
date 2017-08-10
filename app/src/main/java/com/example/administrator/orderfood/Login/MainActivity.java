package com.example.administrator.orderfood.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.orderfood.Chef.Chef;
import com.example.administrator.orderfood.R;
import com.example.administrator.orderfood.Menu.Menu;

public class MainActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_password;
    Button button_login;
    Button button_cancel;
    // Create a count variable when access acount to limit the number of false entries.
    int countNumber = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ánh xạ của activity_main.xml.
        addControls();
        // Events handler of activity_main.xml.
        addEvents();
    }

//==================================================================================================

    public void addControls() {
        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        button_login      = (Button)   findViewById(R.id.button_login);
        button_cancel     = (Button)   findViewById(R.id.button_cancel);
    }

    public void addEvents() {
        // Log in button.
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra tài khoản và mật khẩu.
                if (editText_username.getText().toString().equals("staff")
                        && editText_password.getText().toString().equals("staff")) {
                    // If true then opens the activity_menu.xmlce.
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                } else {
                    if (editText_username.getText().toString().equals("chef")
                            && editText_password.getText().toString().equals("chef")) {
                        // If true then opens the activity_chefs.xml interface.
                        Intent intent = new Intent(MainActivity.this, Chef.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không đúng!",
                                Toast.LENGTH_LONG).show();
                        // If false then decreases the countNumber variable,
                        // and if it equals 0 then hide the button.
                        countNumber--;
                        if (countNumber == 0) {
                            button_login.setEnabled(false);
                        }
                    }
                }
            }
        });

        // Cancel button.
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit this application.
                finish();
            }
        });
    }
}
