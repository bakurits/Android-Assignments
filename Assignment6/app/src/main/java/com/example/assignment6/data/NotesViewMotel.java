package com.example.assignment6.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NotesViewMotel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> notes;

    public NotesViewMotel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        notes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.update(note);
    }

    public void togglePin(Note note) {
        repository.togglePin(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }


}
