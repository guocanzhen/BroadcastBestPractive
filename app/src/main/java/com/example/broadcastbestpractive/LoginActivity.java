package com.example.broadcastbestpractive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private CheckBox rememberPass;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.accountEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        rememberPass = findViewById(R.id.save_pass);
        login = findViewById(R.id.login);

        boolean rememberPassword = pref.getBoolean("remember_password", false);
        if (rememberPassword) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            // 如果账号是admin 且 密码是123456，就认为登录成功
            if ("admin".equals(account) && "123456".equals(password)) {

                // 检查复选框是否被选
                editor = pref.edit();
                if (rememberPass.isChecked()) {
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.putBoolean("remember_password", true);
                } else {
                    editor.remove("account");
                    editor.remove("password");
                    editor.remove("remember_password");
                }
                editor.apply();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}