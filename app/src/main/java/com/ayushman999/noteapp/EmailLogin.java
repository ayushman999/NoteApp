package com.ayushman999.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ayushman999.noteapp.databinding.ActivityEmailLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class EmailLogin extends AppCompatActivity {
    ActivityEmailLoginBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEmailLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        binding.checkboxLoginpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    binding.loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    binding.loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        binding.clickTosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfer=new Intent(EmailLogin.this, SignupActivity.class);
                startActivity(transfer);
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin();
            }
        });
        binding.resetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfer=new Intent(EmailLogin.this,ForgotPassword.class);
                startActivity(transfer);
            }
        });

    }

    private void callLogin() {
        String email=binding.loginEmail.getText().toString().trim();
        String password=binding.loginPassword.getText().toString().trim();
        if(email.isEmpty())
        {
            Toast.makeText(this,"Enter your email!",Toast.LENGTH_SHORT).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(this,"Enter a valid email address!",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user=task.getResult().getUser();
                        startMainActivity(user);
                    }
                    else
                    {

                        if(task.getException() instanceof FirebaseAuthInvalidUserException)
                        {
                            createAlert("Error","This email is not registered","OK");
                        }
                        else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                        {
                            createAlert("Error","Wrong Password!","OK");
                        }
                        else
                        {
                            Toast.makeText(EmailLogin.this,"Unable to login.",Toast.LENGTH_SHORT).show();
                            task.getException().printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void startMainActivity(FirebaseUser user) {
        if(user!=null)
        {
            Intent transfer=new Intent(this,MainActivity.class);
            transfer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(transfer);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=auth.getCurrentUser();
        startMainActivity(user);
    }

    private void createAlert(String heading, String message, String possitive)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(heading)
                .setMessage(message)
                .setPositiveButton(possitive,null)
                .create().show();
    }
}