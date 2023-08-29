package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityRegisterBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.regex.Pattern;


public class Register_Activity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    public static int date;




    public boolean validateEmail(){
        String emailInput=binding.etLoginEmail.getText().toString();
        if (emailInput.isEmpty()){
            binding.etLoginEmail.setError("Fieid cant be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            binding.etLoginEmail.setError("please enter a valid email address");
            return false;
        }else {
            Intent intent=new Intent(Register_Activity.this,Login_Activity.class
            );
            binding.etLoginEmail.setError(null);
            return true;
        }

    }

    private boolean validePassword(){
        String passwordInput = binding.etLoginPassword.getText().toString().trim();
        String confitmpasswordInput = binding.etLoginRePassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            Toast.makeText(getBaseContext(), "Field cant be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!passwordInput.equals(confitmpasswordInput)) {
            binding.etLoginRePassword.setError("password would not be matched");
            return false;
        } else {
            Toast.makeText(getBaseContext(), "password matched", Toast.LENGTH_SHORT).show();
            return true;


        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sp= getSharedPreferences("register",MODE_PRIVATE) ;//الملف الافتراضي للمشروع باكمله
        SharedPreferences.Editor edit=sp.edit();//موشر ع كلاس التعديل الخاص بالملف هذا








        ActivityResultLauncher<String> ar1 = registerForActivityResult(new ActivityResultContracts
                .GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.imageView.setImageURI(result);
            }
        });

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar1.launch("image/*");
            }
        });

        binding.etLoginBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year,
                                                  int monthOfYear, int dayOfMonth) {//اول ما يضغط حيعطي السنة والتاريخ والشهر
                                binding.etLoginBirthdate.setText(dayOfMonth
                                        + "/" +(monthOfYear+1)  + "/" + year);
                                date=Calendar.getInstance().get(Calendar.YEAR)-year;

                            }
                        },
                        now.get(Calendar.YEAR), // السنة الافتراضية اول ما بفتح بكون حاطط تاريخ اليوم
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
// If you're calling this from a support Fragment
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");

            }
        });



        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validePassword();
                validateEmail();
                String Fullname = binding.etLoginName.getText().toString();
                String emali = binding.etLoginEmail.getText().toString();
                String username = binding.etLoginUsername.getText().toString();
                String password = binding.etLoginPassword.getText().toString();
                String repassword = binding.etLoginRePassword.getText().toString();
                String bithedate = binding.etLoginBirthdate.getText().toString();
                String spiner = binding.spinner.getSelectedItem().toString();



                edit.putString("name",Fullname);
                edit.putString("emali",emali);
                edit.putString("birthdate",bithedate);
                edit.putString("username",username);
                edit.putString("password",password);
                edit.putString("repassword",repassword);
                edit.putString("country",spiner);
                edit.apply();


                Intent intent=new Intent();
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                setResult(RESULT_OK,intent);
                finish();


            }
        });
    }
    public  static int age(){

        return date;
    }

}





