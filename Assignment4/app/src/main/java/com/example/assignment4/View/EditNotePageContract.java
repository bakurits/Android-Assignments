package com.example.assignment4.View;

import com.example.assignment4.data.NoteEntity;

public interface EditNotePageContract {
    interface View {
        void showPinnedView();


        void showContentLoading();

        void hideContentLoading();

        void showContentError();

        void hideContentError();

        void showEmptyResultsView();

        void hideEmptyResultsView();
    }

    interface Presenter {

        void showNewNotePage();

        void showEditNotePage(int id);

        void pinNote(int id);

        void saveNote(NoteEntity noteEntity);
    }
}
