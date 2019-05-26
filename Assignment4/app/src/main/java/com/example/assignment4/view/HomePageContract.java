package com.example.assignment4.view;

import com.example.assignment4.data.NoteEntity;

import java.util.List;

public interface HomePageContract {
    interface View {
        void showPinnedView();

        void showWithoutPinned(List<NoteEntity> notes);

        void navigateToEditNote(int id);

        void showContentLoading();

        void hideContentLoading();

        void showContentError();

        void hideContentError();

        void showEmptyResultsView();

        void hideEmptyResultsView();
    }

    interface Presenter {
        void load(String searchString);

        void showNewNotePage();

        void showEditNotePage(int id);

        void pinNote(int id);

        void saveNote(NoteEntity noteEntity);
    }
}
