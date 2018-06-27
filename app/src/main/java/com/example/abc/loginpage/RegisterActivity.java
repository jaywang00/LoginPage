package com.example.abc.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String PREF_PREFIX_NAME = "credential_";
    private EditText etEmail, etUserName, etPassword, etAge;
    private Button btnRegister;
    private String email, userName, password, age;
    private SharedPreferences credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        setListeners();
    }

    private void findViews() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAge = (EditText) findViewById(R.id.etAge);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();
                age = etAge.getText().toString();

                if (!email.equals("") && !userName.equals("") && !password.equals("") && !age.equals("")) {
                    credentials = getSharedPreferences();
                    boolean isAccountExisted = credentials.getString(userName, "").equals(userName);
                    boolean isEmailExisted = credentials.getString(email, "").equals(email);

                    if (!isAccountExisted && !isEmailExisted) {
                        savePersonalData();
                        Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, UserHomePage.class);
                        intent.putExtra("USER_NAME", etUserName.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "帳號已存在，請重新輸入",
                            Toast.LENGTH_LONG).show();
                    }
                } else
                    Toast.makeText(RegisterActivity.this, "欄位不可空白", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void savePersonalData() {
        if (credentials == null)
            credentials = getSharedPreferences();

        SharedPreferences.Editor editor = credentials.edit();
        editor.putString(email, email);
        editor.putString(userName, userName);
        editor.putString("password", password);
        editor.putString("age", age).apply();
    }

    private SharedPreferences getSharedPreferences(){
        return getSharedPreferences(PREF_PREFIX_NAME + userName, 0);
    }
}
