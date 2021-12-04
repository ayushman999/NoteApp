package com.ayushman999.noteapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Notes")
@SuppressWarnings("serial")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int uid;
    @ColumnInfo(name="Title")
    String title;
    @ColumnInfo(name="Description")
    String description;
    //An SQL BLOB is a built-in type that stores a Binary Large Object as a column value in a row of a database table
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
