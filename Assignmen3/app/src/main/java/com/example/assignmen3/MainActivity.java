package com.example.assignmen3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.assignmen3.Model.FileModel;
import com.example.assignmen3.Presenter.IPresenter;
import com.example.assignmen3.Presenter.MainPresenter;
import com.example.assignmen3.View.IMainView;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private IPresenter presenter;
    private Toolbar toolbar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        presenter = new MainPresenter(this);

        getPermissions();

    }

    @Override
    public void showGridView(FileModel file) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(file.listFileModels(), true, presenter);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showListView(FileModel file) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(file.listFileModels(), false, presenter);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showEmptyDataError() {

    }

    private void getPermissions() {
        switch (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            case PackageManager.PERMISSION_DENIED:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                break;
            case PackageManager.PERMISSION_GRANTED:
                presenter.start();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                presenter.start();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (presenter.goToParent()) return;
        super.onBackPressed();
    }
}
