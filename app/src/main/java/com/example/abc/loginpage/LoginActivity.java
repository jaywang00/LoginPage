package com.example.abc.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_PREFIX_NAME = "credential_";
    private EditText etUserName, etPassword;
    private Button btnSignIn;
    private TextView tvRegister;
    private String userName, password;
    private SharedPreferences credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
    }

    private void findViews() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
    }

    private void setListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = etUserName.getText().toString();
                password = etPassword.getText().toString();

                if (!userName.equals("") && !password.equals("")) {
                    credentials = getSharedPreferences();
                    String prefAccount = credentials.getString(userName, "");
                    String prefPassword = credentials.getString("password", "");

                    if (userName.equals(prefAccount) && password.equals(prefPassword)) {
                        Toast.makeText(LoginActivity.this, "帳號驗證成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, UserHomePage.class);
                        intent.putExtra("USER_NAME", userName);
                        startActivity(intent);
                    } else  // TODO: register the new account here.
                        Toast.makeText(LoginActivity.this, "帳號驗證失敗", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "欄位不可空白", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences(PREF_PREFIX_NAME + userName, 0);
    }
}
