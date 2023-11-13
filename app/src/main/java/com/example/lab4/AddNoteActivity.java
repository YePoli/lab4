package com.example.lab4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editNoteName;
    private EditText editNoteContent;
    private Button buttonSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        editNoteName = findViewById(R.id.editNoteName);
        editNoteContent = findViewById(R.id.editNoteContent);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);

        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String noteName = editNoteName.getText().toString();
        String noteContent = editNoteContent.getText().toString();

        if (noteName.isEmpty()) {
            showToast("Note name cannot be empty");
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(noteName, noteContent);
            editor.apply();

            showToast("Note saved: " + noteName);
            finish();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
