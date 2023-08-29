package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityChangepasswordBinding;

public class changepassword_Activity extends AppCompatActivity {
ActivityChangepasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChangepasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



                        binding.btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                SharedPreferences sp = getSharedPreferences("register", MODE_PRIVATE);//الملف الافتراضي للمشروع باكمله
                                SharedPreferences.Editor edit = sp.edit();

                                String newpassword = binding.newpassword.getText().toString();
                                edit.putString("password", newpassword);
                                edit.apply();
                                Toast.makeText(getBaseContext(), "The password change operation susseed", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
