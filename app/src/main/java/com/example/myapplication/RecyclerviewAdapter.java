package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.GameCustomBinding;

import java.util.ArrayList;
import java.util.logging.Handler;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.Holder> {
private ArrayList<Games> games;
private Context context;

    public RecyclerviewAdapter(ArrayList<Games> games, Context context) {
        this.games = games;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameCustomBinding binding=GameCustomBinding.inflate(LayoutInflater.from(context),parent,false);
        return new Holder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Games game = games.get(position);
        holder.binding.tvRecyclerScore.setText(String.valueOf(game.getScore()));
        holder.binding.tvRecyclerFullname.setText(game.getName());
        holder.binding.tvRecyclerTimer.setText(game.getDate());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        GameCustomBinding binding;
        public Holder(@NonNull View itemView) {
            super(itemView);
            binding=GameCustomBinding.bind(itemView);
        }
    }



}
