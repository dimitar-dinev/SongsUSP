package com.example.songsusp.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.songsusp.R;
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

        final EditText titleEditText = view.findViewById(R.id.titleEditText);
        final EditText artistEditText = view.findViewById(R.id.artistEditText);
        final EditText albumEditText = view.findViewById(R.id.albumEditText);
        final EditText durationEditText = view.findViewById(R.id.durationEditText);
        final EditText genreEditText = view.findViewById(R.id.genreEditText);
        final EditText yearEditText = view.findViewById(R.id.yearEditText);

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
            Log.d(TAG, yearEditTextValue + ", int = " + year);

                AddSongListener listener = (AddSongListener) getActivity();
                if (listener != null) {
                    listener.onAddSong(new Song(title, artist, album, duration, genre, year));
                    dismiss();
                }

        });
    }
}
