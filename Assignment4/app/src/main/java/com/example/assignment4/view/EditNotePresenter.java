package com.example.assignment4.view;

import android.os.AsyncTask;

import com.example.assignment4.data.AppDatabase;
import com.example.assignment4.data.NoteEntity;

public class EditNotePresenter implements EditNotePageContract.Presenter {
    private EditNotePageContract.View view;
    private int id;
    private boolean isPinned;

    public EditNotePresenter(EditNotePageContract.View view, int id) {
        this.view = view;
        this.id = id;
    }


    @Override
    public void backButtonClicked() {
        NoteEntity note = view.getData();
        if (id == -1) {
            AppDatabase.getInstance().dataDao().insertData(note);
        } else {
            AppDatabase.getInstance().dataDao().updateEntity(note.getId(), note.getName(), note.getContent(), note.isPinned());
        }
        view.navigateBack();
    }

    @Override
    public void togglePin() {
        isPinned = !isPinned;
        view.setPinned(isPinned);
    }

    @Override
    public void setPin(boolean pin) {
        isPinned = pin;
    }

    @Override
    public void showNote() {
        new LoadNoteByIdTask(id, view).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void newItemAdded(String name) {
        view.addNewItem(name);
    }

    @Override
    public void migrateItem(boolean checked, int position) {
        view.migrateItem(checked, position);
    }


    private static class LoadNoteByIdTask extends AsyncTask<Void, Void, NoteEntity> {
        private int id;
        private EditNotePageContract.View view;

        private LoadNoteByIdTask(int id, EditNotePageContract.View view) {
            this.view = view;
            this.id = id;
        }

        @Override
        protected NoteEntity doInBackground(Void... voids) {

            return AppDatabase.getInstance().dataDao().getNote(id);
        }

        @Override
        protected void onPreExecute() {
            view.showContentLoading();
        }

        @Override
        protected void onPostExecute(NoteEntity note) {

            view.hideContentLoading();
            if (note == null) {
                view.showEmptyResultsView();
            } else {
                view.showData(note);
            }

        }
    }


}
