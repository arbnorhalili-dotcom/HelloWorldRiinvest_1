package com.riinvest.helloworldriinvest_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UniversitiesActivity extends AppCompatActivity {

    OkHttpClient client;
    List<University> universityList = new ArrayList<>();
    List<UniversityGson> universityGsonList = new ArrayList<>();
    EditText etCountry;
    Button btnSearch;
    ListView lvUniversities;
    UniversityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        etCountry = findViewById(R.id.etCountry);
        btnSearch = findViewById(R.id.btnSearch);
        lvUniversities = findViewById(R.id.lvUniversities);

        adapter = new UniversityAdapter(UniversitiesActivity.this);
        lvUniversities.setAdapter(adapter);

        GetUniversitiesGson("Kosovo");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUniversitiesGson(etCountry.getText().toString());
            }
        });
    }

    private void GetUniversities(String country)
    {
        universityList.clear();
        Request.Builder request =
                new Request.Builder()
                        .url("http://universities.hipolabs.com/search?country="+country)
                        .get();
        client = new OkHttpClient();
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
                    //Log.i("Response", strResponse);

                    try {
                        JSONArray jsonArray = new JSONArray(strResponse);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String strName = jsonObject.getString("name");
                            String strAlphaTwoCode = jsonObject.getString("alpha_two_code");
                            String strCountry = jsonObject.getString("country");
                            JSONArray arrayDomains = jsonObject.getJSONArray("domains");
                            JSONArray arrayWebpages = jsonObject.getJSONArray("web_pages");

                            University tempUniversity =
                                    new University(strName, strAlphaTwoCode, strCountry, arrayDomains, arrayWebpages);
                            universityList.add(tempUniversity);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    finally {
                        Log.i("RiinvestAppLog", "Total: "+universityList.size());
                    }
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

    private void GetUniversitiesGson(String country)
    {
        universityGsonList.clear();
        Request.Builder request =
                new Request.Builder()
                        .url("http://universities.hipolabs.com/search?country="+country)
                        .get();
        client = new OkHttpClient();
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
                    //Log.i("Response", strResponse);

                    try {
                        JSONArray jsonArray = new JSONArray(strResponse);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            UniversityGson tempUniversity =
                                    gson.fromJson(jsonObject.toString(), UniversityGson.class);
                            universityGsonList.add(tempUniversity);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    finally {
                        Log.i("RiinvestAppLog", "Total: "+universityGsonList.size());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.universityGsonList = universityGsonList;
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
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