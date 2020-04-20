package com.example.songsusp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsusp.R;
import com.example.songsusp.db.entities.Song;

import java.util.ArrayList;
import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songsList = new ArrayList<>();

    class SongViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView artistTextView;

        SongViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            artistTextView = itemView.findViewById(R.id.artistTextView);

        }
    }

    void setSongsList(List<Song> SongsList) {
        this.songsList = SongsList;
        notifyDataSetChanged();
    }


    Song getSong(int position) {
        return this.songsList.get(position);
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.titleTextView.setText(songsList.get(position).getTitle());
        holder.artistTextView.setText(songsList.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

}
