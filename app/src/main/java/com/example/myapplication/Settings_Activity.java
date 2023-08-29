package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLastDateBinding;
import com.example.myapplication.databinding.ActivitySettingsBinding;
import com.example.myapplication.databinding.GameCustomBinding;

public class Settings_Activity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAllgames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AllgameList_Activity.class);
                startActivity(intent);
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=new Database(getBaseContext());
               database.deletegame();
                boolean reselt=database.deletegame();
                if (reselt) {
                    Toast.makeText(getBaseContext(), "succeed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnDategame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(),lastDate_Activity.class);
                startActivity(intent);
            }
        });
        binding.btnchpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),changepassword_Activity.class);
                startActivity(intent);
            }
        });

    }
}