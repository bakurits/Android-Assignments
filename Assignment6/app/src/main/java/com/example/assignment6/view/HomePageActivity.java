package com.example.assignment6.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.adapters.NotesAdapter;
import com.example.assignment6.data.Note;
import com.example.assignment6.viewmodels.NotesViewMotel;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private NotesViewMotel model;

    private RecyclerView pinnedItems;
    private RecyclerView unpinnedItems;
    private TextView takeANote;
    private EditText searchField;

    private NotesAdapter pinnedAdapter;
    private NotesAdapter unpinnedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pinnedItems = findViewById(R.id.listPinned);
        unpinnedItems = findViewById(R.id.listOthers);
        takeANote = findViewById(R.id.take_note);
        searchField = findViewById(R.id.search_field);

        pinnedAdapter = new NotesAdapter();
        unpinnedAdapter = new NotesAdapter();

        pinnedItems.setAdapter(pinnedAdapter);
        unpinnedItems.setAdapter(unpinnedAdapter);

        pinnedItems.setLayoutManager(new GridLayoutManager(this, 2));
        unpinnedItems.setLayoutManager(new GridLayoutManager(this, 2));


        model = ViewModelProviders.of(this).get(NotesViewMotel.class);
        model.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> list) {
                final ArrayList<Note> pinned = new ArrayList<>();
                ArrayList<Note> unPinned = new ArrayList<>();
                assert list != null;
                for (Note note : list) {
                    if (note.isPinned()) pinned.add(note);
                    else unPinned.add(note);
                }
                pinnedAdapter.updateData(pinned);
                unpinnedAdapter.updateData(unPinned);
            }
        });

        takeANote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, EditNoteActivity.class);
                startActivity(intent);
            }
        });

        pinnedAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(HomePageActivity.this, EditNoteActivity.class);
                intent.putExtra(EditNoteActivity.NOTE, note);
                startActivity(intent);
            }
        });
        unpinnedAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(HomePageActivity.this, EditNoteActivity.class);
                intent.putExtra(EditNoteActivity.NOTE, note);
                startActivity(intent);
            }
        });

        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    model.updateMatchedNotes(searchField.getText().toString());
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        searchField.setText("");
        model.updateMatchedNotes("");
    }
}