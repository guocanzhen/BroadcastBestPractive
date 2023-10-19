package com.example.broadcastbestpractive;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.accountEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            // 如果账号是admin 且 密码是123456，就认为登录成功
            if ("admin".equals(account) && "123456".equals(password)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}