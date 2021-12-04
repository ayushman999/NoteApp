package com.ayushman999.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ayushman999.noteapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        binding.signUpCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    binding.createPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    binding.createPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name=binding.createName.getText().toString().trim();
        String email=binding.createEmail.getText().toString().trim();
        String password=binding.createPassword.getText().toString().trim();
        if(name.equals(""))
        {
            Toast.makeText(this,"Enter your name!",Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty())
        {
            Toast.makeText(this,"Please Enter your email",Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(this,"Please enter a valid email address",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this,"Enter a password!",Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6)
        {
            Toast.makeText(this,"Password must be greater than equal to 6 characters",Toast.LENGTH_SHORT).show();
        }
        else
        {

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(SignupActivity.this, "SignUp successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this,MainActivity.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignupActivity.this,"Unable to signup.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            });
        }
    }
}