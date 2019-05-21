package com.example.assignment4.View;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.assignment4.data.DataDao;
import com.example.assignment4.data.Database;
import com.example.assignment4.data.NoteEntity;

import java.util.List;

public class HomePagePresenter implements HomePageContract.Presenter {

    private HomePageContract.View view;

    public HomePagePresenter(HomePageContract.View view) {
        this.view = view;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void load(final String searchString) {
        new AsyncTask<Void, Void, List<NoteEntity>>() {

            @Override
            protected List<NoteEntity> doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return Database.getInstance().dataDao().getMatchedNotes(searchString);
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

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void showNewNotePage() {
    }

    @Override
    public void showEditNotePage(int id) {

    }

    @Override
    public void pinNote(int id) {

    }

    @Override
    public void saveNote(NoteEntity noteEntity) {

    }
}
