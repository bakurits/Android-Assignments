package com.example.assignment6.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance();
        noteDao = database.noteDao();
        allNotes = noteDao.getMatchedNotes("");
    }


    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void togglePin(Note note) {
        new TogglePinNoteAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }




    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... items) {
            noteDao.delete(items[0]);
            return null;
        }
    }

    private static class TogglePinNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private TogglePinNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            Note note = notes[0];
            if (note == null) return null;

            if (note.isPinned()) {
                noteDao.unpinNote(note.getId());
            } else {
                noteDao.pinNote(note.getId());
            }
            note.setPinned(!note.isPinned());
            return null;
        }
    }


}
