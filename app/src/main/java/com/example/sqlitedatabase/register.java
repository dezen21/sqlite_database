package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    Database db;
    Button btn_continue;
    EditText username, password, passwordConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new Database(this);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        passwordConf = (EditText)findViewById(R.id.passwordConf);
        btn_continue = (Button) findViewById(R.id.btn_continue);


        //register
        btn_continue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                if (strPassword.equals(strPasswordConf)){
                    Boolean daftar = db.insertUser(strUsername, strPassword);
                    if (daftar == true){
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(register.this, sign_in.class);
                        //Intent loginIntent = new Intent(btn_continue.this, btn_sign_in.class);
                        startActivity(login);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
