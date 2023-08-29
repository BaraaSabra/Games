package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityAllgameListBinding;
import com.example.myapplication.databinding.GameCustomBinding;

import java.util.ArrayList;

public class AllgameList_Activity extends AppCompatActivity {
    ActivityAllgameListBinding binding;
    RecyclerviewAdapter adapter;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllgameListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database=new Database(this);
        ArrayList<Games> games=database.getAllGames();
        adapter=new RecyclerviewAdapter(games,this);
        binding.Recyclerview.setAdapter(adapter);
        binding.Recyclerview.setHasFixedSize(true);
        binding.Recyclerview.setLayoutManager(new LinearLayoutManager(this));






        
    }
}