package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sign_in extends AppCompatActivity {
    Database db;
    Button btn_sign_in, btn_continue;
    EditText username, password;
    TextView btn_new_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new Database(this);

        username = findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        btn_new_account = findViewById(R.id.btn_new_account);


        //register
        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregister = new Intent(sign_in.this, register.class);
                startActivity(gotoregister);
            }
        });

        //login
        btn_sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                Boolean masuk = db.checkLogin(strUsername, strPassword);
                if (masuk == true) {
                    Boolean updateSession = db.upgradesession("ada", 1);
                    if(updateSession == true){
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(sign_in.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Masuk Gagagl", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
