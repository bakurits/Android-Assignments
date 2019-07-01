package com.example.assignment6.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class NotesViewMotel extends AndroidViewModel {

    private NoteRepository repository;
    private MutableLiveData<List<Note>> notes;

    public NotesViewMotel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        notes = new MutableLiveData<>();
        updateMatchedNotes("");
    }

    public void updateMatchedNotes(String pattern) {
        new GetMatchedResultsAsyncTask(repository, notes).execute(pattern);
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }


    private static class GetMatchedResultsAsyncTask extends AsyncTask<String, Void, List<Note>> {
        private NoteRepository repository;
        private MutableLiveData<List<Note>> notes;

        private GetMatchedResultsAsyncTask(NoteRepository noteRepository, MutableLiveData<List<Note>> notes) {
            this.repository = noteRepository;
            this.notes = notes;
        }

        @Override
        protected List<Note> doInBackground(String... strings) {
            return repository.getMatchedNotes(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Note> list) {
            super.onPostExecute(list);
            notes.setValue(list);
        }
    }
}