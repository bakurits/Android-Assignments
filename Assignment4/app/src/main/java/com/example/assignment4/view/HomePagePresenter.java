package com.example.assignment4.view;

import android.os.AsyncTask;

import com.example.assignment4.data.Database;
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
        List<NoteEntity> res = new ArrayList<>();
        List<NoteItem> items = new ArrayList<>();
        items.add(new NoteItem("დავალება 1", false));
        items.add(new NoteItem("დავალება 2", false));
        items.add(new NoteItem("დავალება 3", false));
        res.add(new NoteEntity(1, "ბაკური", items));
        res.add(new NoteEntity(2, "გვანცა", items));
        res.add(new NoteEntity(3, "თეკლა", items));
        res.add(new NoteEntity(4, "ცოტნე", items));
        view.showWithoutPinned(res);
        //new LoadNoteTask(searchString, view).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class LoadNoteTask extends AsyncTask<Void, Void, List<NoteEntity>> {
        private String searchString;
        private HomePageContract.View view;

        private LoadNoteTask(String searchString, HomePageContract.View view) {
            this.view = view;
            this.searchString = searchString;
        }

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
    }

    @Override
    public void showNewNotePage() {
    }

    @Override
    public void showEditNotePage(int id) {
        int dasd = 1;
    }

    @Override
    public void pinNote(int id) {

    }

    @Override
    public void saveNote(NoteEntity noteEntity) {

    }
}
