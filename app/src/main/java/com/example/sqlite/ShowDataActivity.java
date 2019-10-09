package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> users;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        init();
        getData();

    }


    private void init() {
        users= new ArrayList<>();
        recyclerView= findViewById(R.id.rv);
        adapter = new UserAdapter(this,users,helper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        helper= new DatabaseHelper(this);
    }

    private void getData() {
        Cursor cursor = helper.showdata();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String  name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String age = cursor.getString(cursor.getColumnIndex(helper.COL_AGE));

            users.add(new User(name,age,id));
            adapter.notifyDataSetChanged();
        }
    }

}
