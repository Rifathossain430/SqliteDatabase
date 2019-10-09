package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private  String name,age;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        helper = new DatabaseHelper(this);


        binding.insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= binding.nameET.getText().toString();
                age= binding.ageET.getText().toString();

                long id= helper.insertData(name,age);
                Toast.makeText(MainActivity.this, "id is"+id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showdata(View view) {
        startActivity(new Intent(this,ShowDataActivity.class));
    }
}
