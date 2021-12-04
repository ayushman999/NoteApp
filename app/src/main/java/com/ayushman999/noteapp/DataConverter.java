package com.ayushman999.noteapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class DataConverter {
    public static byte[] convertImage2ByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        return stream.toByteArray();
    }

    public  static Bitmap convertByteArray2Image(byte[] array){
        return BitmapFactory.decodeByteArray(array,0, array.length);
    }
}
