package com.example.assignment4.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteEntity;

import java.util.List;

public class HomePageActivity extends AppCompatActivity implements HomePageContract.View {
    private HomePageContract.Presenter presenter;

    private RecyclerView pinnedItems;
    private RecyclerView otherItems;
    private TextView takeANote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pinnedItems = findViewById(R.id.listPinned);
        otherItems = findViewById(R.id.listOthers);
        takeANote = findViewById(R.id.take_note);

        presenter = new HomePagePresenter(this);

        takeANote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showNewNotePage();
            }
        });

        presenter.load("");


    }

    @Override
    public void showPinnedView() {

    }

    @Override
    public void showWithoutPinned(List<NoteEntity> notes) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        pinnedItems.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new NotesAdapter(notes, presenter);
        pinnedItems.setAdapter(mAdapter);
    }

    @Override
    public void navigateToEditNote(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Intent intent = new Intent(HomePageActivity.this, EditNoteActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
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