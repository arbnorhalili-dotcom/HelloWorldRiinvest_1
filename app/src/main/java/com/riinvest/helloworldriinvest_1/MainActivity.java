package com.riinvest.helloworldriinvest_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String strName = "", strSurname = "";
    ListView lvUsers;
    UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvUsers = findViewById(R.id.lvUsers);

        usersAdapter = new UsersAdapter(MainActivity.this);

        lvUsers.setAdapter(usersAdapter);
        LoadUsersFromDb();
//        if(getIntent().hasExtra("username"))
//        {
//            strName = getIntent().getExtras().getString("name");
//            strSurname = getIntent().getExtras().getString("surname");
//            tvWelcome.setText("Mire se erdhe, "
//                    +strName+" "+strSurname);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addNewUser)
        {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void LoadUsersFromDb()
    {
        SQLiteDatabase objDb = (new DatabaseHelper(MainActivity.this)).getReadableDatabase();
        Cursor cursor = objDb.query("Users",
                new String[]{"Id", "Name", "Surname", "Email"},
                "",
                new String[]{},
                "",
                "",
                "");
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while(cursor.isAfterLast()==false)
            {
                User tempUser = new User(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                usersAdapter.userList.add(tempUser);
                cursor.moveToNext();
            }
            cursor.close();

            usersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //usersAdapter = new UsersAdapter(MainActivity.this);
        //LoadUsersFromDb();
    }
}