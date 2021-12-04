package com.ayushman999.noteapp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("Select * from Notes")
    List<Note> getAllNotes();

    @Insert
    void insertNotes(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
