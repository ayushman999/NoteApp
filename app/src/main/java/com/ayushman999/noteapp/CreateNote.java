package com.ayushman999.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.ayushman999.noteapp.databinding.ActivityCreateNoteBinding;
import com.ayushman999.noteapp.model.Note;
import com.ayushman999.noteapp.model.NoteDAO;
import com.ayushman999.noteapp.model.NoteDatabase;

public class CreateNote extends AppCompatActivity {
    ActivityCreateNoteBinding binding;
    final int CAMERA_ACCESS=1;
    Bitmap bmpImage;
    NoteDAO noteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteDAO= NoteDatabase.getInstance(this).noteDAO();
        binding.uploadBtn.setOnClickListener(v->{
            if(ContextCompat.checkSelfPermission(CreateNote.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(CreateNote.this,new String[]{Manifest.permission.CAMERA},CAMERA_ACCESS);
            }
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null)
            {
                startActivityForResult(intent,CAMERA_ACCESS);
            }
        });
        binding.addNoteBtn.setOnClickListener(v->{

            String title=binding.createTitle.getText().toString();
            String desc=binding.createDescription.getText().toString();
            if(title.isEmpty() || desc.isEmpty() || bmpImage==null){
                Toast.makeText(CreateNote.this, "Note data missing", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Note note=new Note();
                note.setTitle(title);
                note.setDescription(desc);
                note.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                noteDAO.insertNotes(note);
                Toast.makeText(CreateNote.this, "Insertions Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_ACCESS:
                if(resultCode== Activity.RESULT_OK){
                    bmpImage=(Bitmap) data.getExtras().get("data");
                    if(bmpImage!=null){
                        binding.uploadBtn.setText("Uploaded!");
                        Toast.makeText(CreateNote.this, "Stored!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(CreateNote.this, "bitmap is null", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(CreateNote.this, "Result not ok", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}