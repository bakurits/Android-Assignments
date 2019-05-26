package com.example.assignment4.view;

import com.example.assignment4.data.NoteEntity;

public interface EditNotePageContract {
    interface View {

        void showData(NoteEntity note);

        NoteEntity getData();

        void navigateBack();

        void showContentLoading();

        void setPinned(boolean pin);

        void hideContentLoading();

        void addNewItem(String name);

        void migrateItem(boolean checked, int position);


        void showContentError();

        void hideContentError();

        void showEmptyResultsView();

        void hideEmptyResultsView();
    }

    interface Presenter {
        void backButtonClicked();

        void togglePin();

        void setPin(boolean pin);

        void showNote();

        void newItemAdded(String name);

        void migrateItem(boolean checked, int position);
    }
}
