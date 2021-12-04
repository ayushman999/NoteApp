package com.ayushman999.noteapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database( entities = Note.class, version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase noteDB=null;
    /*we create an abstract method of NoteDAO to get a DAO object
    and then we use that object to perform DAO tasks
     */
    public  abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context){
        if(noteDB==null){
            noteDB= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"notedb999")
                    .allowMainThreadQueries()           //to allow DAO methods without creating a thread
                    .build();
        }
        return noteDB;
    }
}
