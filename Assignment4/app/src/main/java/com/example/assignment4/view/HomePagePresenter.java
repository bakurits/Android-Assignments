package com.example.assignment4.view;

import android.os.AsyncTask;

import com.example.assignment4.data.AppDatabase;
import com.example.assignment4.data.NoteEntity;
import com.example.assignment4.data.NoteItem;

import java.util.ArrayList;
import java.util.List;

public class HomePagePresenter implements HomePageContract.Presenter {

    private HomePageContract.View view;

    public HomePagePresenter(HomePageContract.View view) {
        this.view = view;
    }

    @Override
    public void load(final String searchString) {
        new LoadNotesTask(searchString, view).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class LoadNotesTask extends AsyncTask<Void, Void, List<NoteEntity>> {
        private String searchString;
        private HomePageContract.View view;

        private LoadNotesTask(String searchString, HomePageContract.View view) {
            this.view = view;
            this.searchString = searchString;
        }

        @Override
        protected List<NoteEntity> doInBackground(Void... voids) {

            return AppDatabase.getInstance().dataDao().getAllNotes();
        }

        @Override
        protected void onPreExecute() {
            view.showContentLoading();
        }

        @Override
        protected void onPostExecute(List<NoteEntity> list) {

            view.hideContentLoading();

            if (list.isEmpty()) {
                view.showEmptyResultsView();
            } else {
                view.showWithoutPinned(list);
            }

        }
    }

    @Override
    public void showNewNotePage() {
        view.navigateToEditNote(-1);
    }

    @Override
    public void showEditNotePage(int id) {
        view.navigateToEditNote(id);
    }

    @Override
    public void pinNote(int id) {

    }

    @Override
    public void saveNote(NoteEntity noteEntity) {

    }
}
