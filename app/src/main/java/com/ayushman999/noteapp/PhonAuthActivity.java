package com.ayushman999.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayushman999.noteapp.databinding.ActivityPhonAuthBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhonAuthActivity extends AppCompatActivity {
    ActivityPhonAuthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhonAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(PhonAuthActivity.this,MainActivity.class));
            finish();
        }
        binding.sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhonAuthActivity.this,OTPActivity.class);
                intent.putExtra("phone_num",binding.editTextPhone.getText().toString());
                startActivity(intent);
            }
        });
    }
}