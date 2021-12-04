package com.ayushman999.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ayushman999.noteapp.databinding.ActivityLoginDashBinding;

public class LoginDash extends AppCompatActivity {
    ActivityLoginDashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.emailSignIn.setOnClickListener(v->{
            startActivity(new Intent(LoginDash.this,EmailLogin.class));
        });
        binding.phoneOtpBtn.setOnClickListener(v->{
            startActivity(new Intent(LoginDash.this,PhonAuthActivity.class));
        });
    }
}