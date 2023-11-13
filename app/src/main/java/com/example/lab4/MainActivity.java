package com.example.lab4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<String> notesList;
    private ArrayAdapter<String> adapter;
    private Button buttonAddNote;
    private Button buttonDeleteNote;
    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteListView = findViewById(R.id.NoteList);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        buttonDeleteNote = findViewById(R.id.buttonDeleteNote);

        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        noteListView.setAdapter(adapter);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        buttonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotesFromSharedPreferences();
    }

    private void loadNotesFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        notesList.clear();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            notesList.add(entry.getKey());
        }
        adapter.notifyDataSetChanged();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
