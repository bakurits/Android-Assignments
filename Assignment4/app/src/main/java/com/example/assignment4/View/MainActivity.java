package com.example.assignment4.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomePageContract.View {
    private HomePageContract.Presenter presenter;

    private RecyclerView pinnedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new HomePagePresenter(this);
        presenter.load("");

        pinnedItems = findViewById(R.id.listPinned);

    }

    @Override
    public void showPinnedView() {

    }

    @Override
    public void showWithoutPinned(List<NoteEntity> notes) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        pinnedItems.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new NotesAdapter(notes);
        pinnedItems.setAdapter(mAdapter);
    }


    @Override
    public void showContentLoading() {

    }

    @Override
    public void hideContentLoading() {

    }

    @Override
    public void showContentError() {

    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showEmptyResultsView() {

    }

    @Override
    public void hideEmptyResultsView() {

    }
}
