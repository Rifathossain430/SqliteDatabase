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
    private String updateId,updateName,updateAge;
    private  int id;

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
        getdata();

    }



    public void showdata(View view) {
        startActivity(new Intent(this,ShowDataActivity.class));
    }

    public void updateData(View view) {

        updateId= getIntent().getStringExtra("id");
        id = Integer.parseInt(updateId);
        name = binding.nameET.getText().toString();
        age = binding.ageET.getText().toString();
        helper.updateData(id,name,age);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

    }


    private void getdata() {
        updateName= getIntent().getStringExtra("name");
        updateAge = getIntent().getStringExtra("age");

        binding.nameET.setText(updateName);
        binding.ageET.setText(updateAge);

    }
}
