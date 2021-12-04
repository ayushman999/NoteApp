package com.ayushman999.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.ayushman999.noteapp.databinding.ActivityShowNoteBinding;
import com.ayushman999.noteapp.model.Note;
import com.ayushman999.noteapp.model.NoteDatabase;

public class ShowNote extends AppCompatActivity {
    ActivityShowNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Note note=(Note)getIntent().getSerializableExtra("object");
        binding.showNoteDesc.setText(note.getDescription());
        binding.showNoteTitle.setText(note.getTitle());
        binding.showNoteImage.setImageBitmap(DataConverter.convertByteArray2Image(note.getImage()));
        binding.deleteFab.setOnClickListener(v->{
            NoteDatabase.getInstance(this).noteDAO().deleteNote(note);
            finish();
        });
    }
}