package com.example.songsusp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsusp.R;
import com.example.songsusp.db.entities.Song;

import java.util.ArrayList;
import java.util.List;


public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> implements Filterable {

    public interface onSongClickListener {
        void onSongClick(Song song);
    }

    private onSongClickListener listener;

    private List<Song> songsList = new ArrayList<>();
    private List<Song> fullSongsList;

    class SongViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView artistTextView;

        SongViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            artistTextView = itemView.findViewById(R.id.artistTextView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onSongClick(songsList.get(position));
                }
            });
        }
    }

    void setSongsList(List<Song> SongsList) {
        this.songsList = SongsList;
        this.fullSongsList = new ArrayList<>(songsList);
        notifyDataSetChanged();
    }

    void setListener(onSongClickListener listener) {
        this.listener = listener;
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = fullSongsList;
            } else {
                String keyword = constraint.toString().toLowerCase().trim();

                List<Song> filteredSongs = new ArrayList<>();
                for(Song song: fullSongsList) {
                    switch (MainActivity.currentFilter) {
                        case TITLE:
                            if (song.getTitle().toLowerCase().contains(keyword)) {
                                filteredSongs.add(song);
                            }
                            break;
                        case GENRE:
                            if (song.getGenre().toLowerCase().contains(keyword)) {
                                filteredSongs.add(song);
                            }
                            break;
                        case ARTIST:
                            if (song.getArtist().toLowerCase().contains(keyword)) {
                                filteredSongs.add(song);
                            }
                            break;
                    }
                }
                results.values = filteredSongs;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            songsList.clear();
            songsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
