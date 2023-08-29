package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLastDateBinding;

public class lastDate_Activity extends AppCompatActivity {
    ActivityLastDateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLastDateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Database database=new Database(getBaseContext());
        binding.textView12.setText(database.lastdate());
    }
}