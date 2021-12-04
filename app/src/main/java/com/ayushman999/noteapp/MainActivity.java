package com.ayushman999.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ayushman999.noteapp.databinding.ActivityMainBinding;
import com.ayushman999.noteapp.model.Note;
import com.ayushman999.noteapp.model.NoteDAO;
import com.ayushman999.noteapp.model.NoteDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NoteClickListener {
    ActivityMainBinding binding;
    NoteDAO noteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteDAO= NoteDatabase.getInstance(this).noteDAO();
        binding.noteFab.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,CreateNote.class));
        });
        NoteAdapter adapter=new NoteAdapter(noteDAO.getAllNotes(), this);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        binding.notesRecycler.setLayoutManager(manager);
        binding.notesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.sign_out:
                Toast.makeText(MainActivity.this, "SignOut!", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent=new Intent(MainActivity.this,ShowNote.class);
        intent.putExtra("object",note);
        startActivity(intent);
    }
}