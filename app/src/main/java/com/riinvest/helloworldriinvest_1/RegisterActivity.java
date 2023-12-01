package com.riinvest.helloworldriinvest_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName, etSurname, etEmail, etPassword;
    SQLiteDatabase objDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        objDb = (new DatabaseHelper(RegisterActivity.this)).getReadableDatabase();

        btnRegister = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CreateUser(etName.getText().toString(),etSurname.getText().toString(),
                        etEmail.getText().toString(),etPassword.getText().toString())==true) {

                    Intent intent = new Intent(RegisterActivity.this,
                            LoginActivitiy.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(RegisterActivity.this,
                            "Regjistrimi i perdoruesit deshtoi!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean CreateUser(String name, String surname, String email, String password)
    {
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Surname", surname);
        cv.put("Email", email);
        cv.put("Password", password);
        long result = objDb.insert("Users",null, cv);

        return result>0?true:false;
    }
}