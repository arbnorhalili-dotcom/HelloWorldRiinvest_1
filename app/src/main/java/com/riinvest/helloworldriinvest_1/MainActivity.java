package com.riinvest.helloworldriinvest_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,
                        "Username: "+usersAdapter.userList.get(i).getEmail(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        lvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Kujdes!");
                builder.setMessage("A jeni i sigurt qe deshironi te fshini kete rekord?");
                builder.setNegativeButton("Jo",null);
                User tempUser = usersAdapter.userList.get(i);
                builder.setPositiveButton("Po", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase objDb = (new DatabaseHelper(MainActivity.this)).getReadableDatabase();
                        objDb.delete("Users", "Id=?",new String[]{ tempUser.getId()+"" });

                        usersAdapter.userList.remove(tempUser);
                        usersAdapter.notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
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
        switch (item.getItemId())
        {
            case R.id.addNewUser:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                ClearSharedPreferences();
                intent = new Intent(MainActivity.this, LoginActivitiy.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                         Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }
        return true;
    }

    private void ClearSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(
                "RiinvestApp", MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Username");
        editor.remove("Name");
        editor.remove("Surname");
        editor.commit();
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