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
    }

    @Override
    public void start() {
        currentDirectory = new FileModel(Environment.getExternalStorageDirectory().getAbsolutePath());
        view.showListView(currentDirectory);
    }


    @Override
    public void loadData() {

    }

    @Override
    public void insertData(String title, String desc) {

    }

}

