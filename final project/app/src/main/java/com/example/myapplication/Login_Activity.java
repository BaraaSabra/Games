package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivitySettingsBinding;


public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sp= getSharedPreferences("register",MODE_PRIVATE) ;//الملف الافتراضي للمشروع باكمله
        SharedPreferences.Editor edit=sp.edit();//موشر ع كلاس التعديل الخاص بالملف هذا

        ActivityResultLauncher<Intent> activityLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        String name = result.getData().getStringExtra("username");
                        String password = result.getData().getStringExtra("password");
                        binding.etLoginUsername.setText(name);
                        binding.etLoginPassword.setText(password);
                    }
                });

                        binding.btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                                activityLauncher.launch(intent);


                            }
                        });


                        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                String username = sp.getString("username", "");
                                String password = sp.getString("password", "");

                                String usernamea = binding.etLoginUsername.getText().toString();
                                String passworde = binding.etLoginPassword.getText().toString();
                                if (username.equals(usernamea) && password.equals(passworde)) {

                                    Intent intent = new Intent(Login_Activity.this, Game_Activity.class);
                                    startActivity(intent);

                                }

                             else{
                                        Toast.makeText(getBaseContext(), "Enter the same name and password", Toast.LENGTH_SHORT).show();
                                    }

                            }
                        });

                        binding.checkedTextView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (compoundButton.isChecked()) {
                                    SharedPreferences Preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = Preferences.edit();
                                    editor.putString("remember", "true");
                                    edit.apply();

                                } else if (!compoundButton.isChecked()) {
                                    SharedPreferences Preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = Preferences.edit();
                                    editor.putString("remember", "fales");

                                }

                            }
                        });


                    }
                }






