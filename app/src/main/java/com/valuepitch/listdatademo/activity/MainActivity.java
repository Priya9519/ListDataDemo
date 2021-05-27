package com.valuepitch.listdatademo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.valuepitch.listdatademo.R;
import com.valuepitch.listdatademo.adapter.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        recyclerView=findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManagerList = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        try {
            JSONObject jsonobject = new JSONObject(loadJSONFromAsset());
            JSONArray jarray = (JSONArray) jsonobject.getJSONArray("data");
            mAdapter = new CustomAdapter(this, jarray);
            recyclerView.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}