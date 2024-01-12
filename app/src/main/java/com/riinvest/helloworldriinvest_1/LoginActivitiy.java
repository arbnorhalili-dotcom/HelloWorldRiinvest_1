package com.riinvest.helloworldriinvest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        String[] sharedPrefsArray = ReadSharedPreferences();
        if(sharedPrefsArray[0].length()>0)
        {
            Intent intent = new Intent(LoginActivitiy.this, MainActivity.class);
            intent.putExtra("username", sharedPrefsArray[0]);
            intent.putExtra("name", sharedPrefsArray[1]);
            intent.putExtra("surname", sharedPrefsArray[2]);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

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
                    WriteSharedPreferences(strUsername, strName, strSurname);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    private void WriteSharedPreferences(String strUsername, String strName, String strSurname)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "RiinvestApp", MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", strUsername);
        editor.putString("Name", strName);
        editor.putString("Surname", strSurname);
        editor.commit();
    }

    private String[] ReadSharedPreferences()
    {
        String[] strResponse = new String[3];
        SharedPreferences sharedPreferences = getSharedPreferences(
                "RiinvestApp", MODE_PRIVATE
        );
        strResponse[0] = sharedPreferences.getString("Username","");
        strResponse[1] = sharedPreferences.getString("Name", "");
        strResponse[2] = sharedPreferences.getString("Surname", "");

        return strResponse;
    }
}