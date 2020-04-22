package com.example.songsusp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.songsusp.R;
import com.example.songsusp.db.SongValidator;
import com.example.songsusp.db.entities.Song;

public class EditSongFragment extends DialogFragment {

    private static final String TITLE_KEY = "TITLE";
    private static final String ARTIST_KEY = "ARTIST";
    private static final String ALBUM_KEY = "ALBUM";
    private static final String GENRE_KEY = "GENRE";
    private static final String DURATION_KEY = "DURATION";
    private static final String YEAR_KEY = "YEAR";
    private static final String ID_KEY = "ID";

    public interface EditSongListener {
        void onEditSong(Song song);
    }

    static EditSongFragment newInstance(Song song) {
        EditSongFragment editSongFragment = new EditSongFragment();
        Bundle args = new Bundle();
        args.putInt(ID_KEY, song.getId());
        args.putString(TITLE_KEY, song.getTitle());
        args.putString(ARTIST_KEY, song.getArtist());
        args.putString(ALBUM_KEY, song.getAlbum());
        args.putString(GENRE_KEY, song.getGenre());
        args.putString(DURATION_KEY, song.getDurationFormatted());
        args.putString(YEAR_KEY, String.valueOf(song.getYear()));
        editSongFragment.setArguments(args);
        return editSongFragment;
    }

    public EditSongFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_layout,  container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configure dialog
        final Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnCancelListener(d -> dismiss());
            dialog.setCanceledOnTouchOutside(false);
        }

        final EditText titleEditText = view.findViewById(R.id.titleEditText);
        final EditText artistEditText = view.findViewById(R.id.artistEditText);
        final EditText albumEditText = view.findViewById(R.id.albumEditText);
        final EditText durationEditText = view.findViewById(R.id.durationEditText);
        final EditText genreEditText = view.findViewById(R.id.genreEditText);
        final EditText yearEditText = view.findViewById(R.id.yearEditText);

        titleEditText.setText(getArguments().getString(TITLE_KEY, ""));
        artistEditText.setText(getArguments().getString(ARTIST_KEY, ""));
        albumEditText.setText(getArguments().getString(ALBUM_KEY, ""));
        durationEditText.setText(getArguments().getString(DURATION_KEY, ""));
        genreEditText.setText(getArguments().getString(GENRE_KEY, ""));
        if (getArguments().getString(YEAR_KEY, "").equals("0")) {
            yearEditText.setText("");
        } else {
            yearEditText.setText(getArguments().getString(YEAR_KEY, ""));
        }


        final Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> dismiss());

        final Button confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {

            final String title = titleEditText.getText().toString();
            final String artist = artistEditText.getText().toString();
            final String album = albumEditText.getText().toString();
            final String duration = durationEditText.getText().toString();
            final String genre = genreEditText.getText().toString();
            final String yearEditTextValue = yearEditText.getText().toString();
            final int year = (yearEditTextValue.isEmpty()) ? 0 : Integer.parseInt(yearEditTextValue);

            if (SongValidator.isSongValid(title, artist, album, duration, genre, year)) {
                EditSongListener listener = (EditSongListener) getActivity();
                if (listener != null) {
                    Song updatedSong = new Song(title, artist, album, duration, genre, year);
                    updatedSong.setId(getArguments().getInt(ID_KEY));
                    listener.onEditSong(updatedSong);
                    dismiss();
                }
            } else {
                displayError(SongValidator.getLastError());
            }
        });
    }
    private void displayError(int error) {
        switch (error) {
            case SongValidator.TITLE_ERROR:
                Toast.makeText(getContext(), getString(R.string.title_validator_error), Toast.LENGTH_SHORT).show(); break;
            case SongValidator.ARTIST_ERROR:
                Toast.makeText(getContext(), getString(R.string.artist_validator_error), Toast.LENGTH_SHORT).show(); break;
            case SongValidator.ALBUM_ERROR:
                Toast.makeText(getContext(), getString(R.string.album_validator_error), Toast.LENGTH_SHORT).show(); break;
            case SongValidator.DURATION_ERROR:
                Toast.makeText(getContext(), getString(R.string.duration_validation_error), Toast.LENGTH_SHORT).show(); break;
            case SongValidator.GENRE_ERROR:
                Toast.makeText(getContext(), getString(R.string.genre_validator_error), Toast.LENGTH_SHORT).show(); break;
            case SongValidator.YEAR_ERROR:
                Toast.makeText(getContext(), String.format(getString(R.string.year_validation_error), SongValidator.MINIMUM_YEAR, SongValidator.MAXIMUM_YEAR), Toast.LENGTH_SHORT).show(); break;
        }
    }
}
