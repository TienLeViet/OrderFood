package com.example.administrator.orderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if (editText_username.getText().toString().equals("admin")
                        && editText_password.getText().toString().equals("admin")) {
                    // If true then opens the activity_type_of_menu.xml interface.
                    Intent intent = new Intent(MainActivity.this, TypeOfMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_LONG).show();
                    // If false then decreases the countNumber variable, and if it equals 0 then hide the button.
                    countNumber--;
                    if (countNumber == 0) {
                        button_login.setEnabled(false);
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
