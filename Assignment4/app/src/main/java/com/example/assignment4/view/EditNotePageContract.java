package com.example.assignment4.view;

import com.example.assignment4.data.NoteEntity;

public interface EditNotePageContract {
    interface View {

        void showData(NoteEntity note);

        void showContentLoading();

        void hideContentLoading();

        void showContentError();

        void hideContentError();

        void showEmptyResultsView();

        void hideEmptyResultsView();
    }

    interface Presenter {
        void backButtonClicked();

        void pinNote();
    }
}
