package com.riinvest.helloworldriinvest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivitiy extends AppCompatActivity {

    Button btnLogin;
    EditText etUsername, etPassword;
    SQLiteDatabase objDb;
    String strName = "", strSurname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        objDb = (new DatabaseHelper(LoginActivitiy.this)).getReadableDatabase();

        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = etUsername.getText().toString();
                String strPassword = etPassword.getText().toString();

                /*Log.i("LoginFormData", strUsername+" - "+strPassword);
                Toast.makeText(LoginActivitiy.this,
                        strUsername+" - "+strPassword,
                        Toast.LENGTH_LONG).show();*/
                if(CheckCredentials(strUsername, strPassword))
                {
                    Toast.makeText(LoginActivitiy.this,
                            "Kredencialet jane ne rregull.",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivitiy.this, MainActivity.class);
                    intent.putExtra("username", strUsername);
                    intent.putExtra("name", strName);
                    intent.putExtra("surname", strSurname);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivitiy.this,
                            "Kredencialet jane gabim.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean CheckCredentials(String email, String password)
    {
        Cursor c = objDb.query("Users",
                new String[]{"Id", "Email", "Password", "Name", "Surname"},
                "Email=?",
                new String[]{email},
                "",
                "",
                "");
        if(c.getCount()>0)
        {
            c.moveToFirst();
            String strUserPasswordDb = c.getString(2);
            if(password.equals(strUserPasswordDb)) {
                strName = c.getString(3);
                strSurname = c.getString(4);
                return true;
            }
            else
                return false;
        }
        else
        {
            return false;
        }
    }
}