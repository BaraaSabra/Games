package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityGameBinding;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Game_Activity extends AppCompatActivity {
    ActivityGameBinding binding;
    Database database;
    SharedPreferences sp;
    Calendar c = Calendar.getInstance();
    int HiddenNumber;
    int score = 0;
    MediaPlayer mediaPlayer;
    MediaPlayer player;


    public void shoegreenToast() {
        StyleableToast.makeText(this, "The answer is correct ", R.style.GreenToast).show();
    }

    public void shoeredToast() {
        StyleableToast.makeText(this, "the answer is wrong ", R.style.RedToast).show();
    }

    public void win() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.win);
        }
        mediaPlayer.start();
    }

    public void loss() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.loss);
        }
        player.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences("register", MODE_PRIVATE);//الملف الافتراضي للمشروع باكمله
        SharedPreferences.Editor edit = sp.edit();

        setSupportActionBar(binding.Toolbar);


        Question question = Utill.generteQuestion();
        HiddenNumber = question.getHiddenNumber();
        String[][] s = question.getData();
        binding.textView.setText(s[0][0]);
        binding.textView1.setText(s[0][1]);
        binding.textView2.setText(s[0][2]);

        binding.textView3.setText(s[1][0]);
        binding.textView4.setText(s[1][1]);
        binding.textView5.setText(s[1][2]);

        binding.textView6.setText(s[2][0]);
        binding.textView7.setText(s[2][1]);
        binding.textView8.setText(s[2][2]);


        int age1 = Register_Activity.age();
        String username = sp.getString("username", "");

        binding.tvNameandage.setText(username + age1);

        binding.btnNewgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.etNumber.setText("");
                Question question = Utill.generteQuestion();
                HiddenNumber = question.getHiddenNumber();
                String[][] s = question.getData();
                binding.textView.setText(s[0][0]);
                binding.textView1.setText(s[0][1]);
                binding.textView2.setText(s[0][2]);

                binding.textView3.setText(s[1][0]);
                binding.textView4.setText(s[1][1]);
                binding.textView5.setText(s[1][2]);

                binding.textView6.setText(s[2][0]);
                binding.textView7.setText(s[2][1]);
                binding.textView8.setText(s[2][2]);


            }
        });

        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = Integer.parseInt(binding.etNumber.getText().toString());
                if (answer == HiddenNumber) {

                    score = score + 1;
                    binding.tvScores.setText(String.valueOf(score));
                    shoegreenToast();
                    win();

                } else {
                    shoeredToast();
                    loss();


                }
                database = new Database(getBaseContext());
                int Score = Integer.parseInt(binding.tvScores.getText().toString());
                String date = DateFormat.getDateInstance().format(c.getTime());
                String Name = binding.tvNameandage.getText().toString();
                Games games = new Games(Score, Name, date);
                boolean reselt = database.insertGame(games);
                if (reselt) {

                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.Settings:
                Intent intent = new Intent(Game_Activity.this, Settings_Activity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:


                Intent intent1 = new Intent(Game_Activity.this, Login_Activity.class);
                startActivity(intent1);


                finish();
                return true;

        }
        return false;
    }

}