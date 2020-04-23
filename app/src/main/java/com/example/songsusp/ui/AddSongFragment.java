package com.example.songsusp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.textfield.TextInputLayout;

import static com.example.songsusp.ui.MainActivity.TAG;

public class AddSongFragment extends DialogFragment {

    public interface AddSongListener {
        void onAddSong(Song song);
    }

    static AddSongFragment newInstance() {
        return new AddSongFragment();
    }

    public AddSongFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        final Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnCancelListener(d -> dismiss());
            dialog.setCanceledOnTouchOutside(false);
        }

        final TextInputLayout titleEditText = view.findViewById(R.id.titleEditText);
        final TextInputLayout artistEditText = view.findViewById(R.id.artistEditText);
        final TextInputLayout albumEditText = view.findViewById(R.id.albumEditText);
        final TextInputLayout durationEditText = view.findViewById(R.id.durationEditText);
        final TextInputLayout genreEditText = view.findViewById(R.id.genreEditText);
        final TextInputLayout yearEditText = view.findViewById(R.id.yearEditText);

        final Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> dismiss());


        final Button confirmButton = view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            final String title = titleEditText.getEditText().getText().toString();
            final String artist = artistEditText.getEditText().getText().toString();
            final String album = albumEditText.getEditText().getText().toString();
            final String duration = durationEditText.getEditText().getText().toString();
            final String genre = genreEditText.getEditText().getText().toString();
            final String yearEditTextValue = yearEditText.getEditText().getText().toString();

            final int year = (yearEditTextValue.isEmpty()) ? 0 : Integer.parseInt(yearEditTextValue);
            Log.d(TAG, yearEditTextValue + ", int = " + year);
            if (SongValidator.isSongValid(title, artist, album, duration, genre, year)) {
                AddSongListener listener = (AddSongListener) getActivity();
                if (listener != null) {
                    listener.onAddSong(new Song(title, artist, album, duration, genre, year));
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
