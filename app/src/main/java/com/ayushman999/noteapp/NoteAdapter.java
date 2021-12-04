package com.ayushman999.noteapp;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayushman999.noteapp.databinding.NoteElementBinding;
import com.ayushman999.noteapp.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    List<Note> notes;
    NoteClickListener listener;
    public NoteAdapter(List<Note> notes,NoteClickListener listener)
    {
        this.notes=notes;
        this.listener=listener;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_element,parent,false);
        NoteViewHolder holder=new NoteViewHolder(view);
        holder.binding.cardElement.setOnClickListener(v->{
            listener.onNoteClick(notes.get(holder.getAdapterPosition()));
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note n=notes.get(position);
        holder.binding.elementNoteDesc.setText(n.getDescription());
        holder.binding.elementNoteTitle.setText(n.getTitle());
        Bitmap bmp=DataConverter.convertByteArray2Image(n.getImage());
        if(bmp!=null) Log.i("BITMAP","Not null");
        else Log.i("BITMAP","null at position"+position);
        holder.binding.elementNoteImage.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
class NoteViewHolder extends RecyclerView.ViewHolder{
    NoteElementBinding binding;
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        binding=NoteElementBinding.bind(itemView);
    }
}
