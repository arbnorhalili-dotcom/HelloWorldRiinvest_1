package com.riinvest.helloworldriinvest_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UniversitiesActivity extends AppCompatActivity {

    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);
    }

    private void GetUniversities(String country)
    {
        Request.Builder request =
                new Request.Builder()
                        .url("http://universities.hipolabs.com/search?country="+country)
                        .get();
        Call call = client.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(UniversitiesActivity.this,
                        e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String strResponse = response.body().string();
                    Log.i("Response", strResponse);
                }
                else
                {
                    Toast.makeText(UniversitiesActivity.this,
                            "Ka ndodhur nje gabim!\n"+response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}