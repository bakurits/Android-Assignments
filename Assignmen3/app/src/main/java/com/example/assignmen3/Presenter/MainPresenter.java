package com.example.assignmen3.Presenter;


import android.content.Context;
import android.os.Environment;

import com.example.assignmen3.MainActivity;
import com.example.assignmen3.Model.FileModel;

import java.io.File;

public class MainPresenter implements IPresenter {

    private MainActivity view;
    private FileModel currentDirectory;
    private boolean isGrid;

    public MainPresenter(MainActivity view) {
        this.view = view;
        isGrid = false;
    }

    @Override
    public void start() {
        currentDirectory = new FileModel(Environment.getExternalStorageDirectory().getPath());
        draw();
    }


    @Override
    public void loadData() {

    }

    @Override
    public void changeFolder(String name) {
        currentDirectory = new FileModel(currentDirectory, name);
        draw();
    }

    @Override
    public void insertData(String title, String desc) {

    }

    @Override
    public boolean goToParent() {
        if (currentDirectory.getPath() == null) return false;
        currentDirectory = new FileModel(currentDirectory.getParent());
        draw();
        return true;
    }

    private void draw() {
        if (isGrid) view.showGridView(currentDirectory);
        if (!isGrid) view.showListView(currentDirectory);
    }

    @Override
    public void toggleView() {
        isGrid = !isGrid;
        draw();
    }
}

