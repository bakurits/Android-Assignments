package com.example.assignmen3.Presenter;


import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.assignmen3.MainActivity;
import com.example.assignmen3.View.IMainView;

import java.io.File;
import java.security.Permission;
import java.util.Arrays;

public class MainPresenter implements IPresenter {

    private MainActivity view;
    private Context context;
    private File currentDirectory;

    public MainPresenter(MainActivity view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void start() {
        currentDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        loadData();
    }


    @Override
    public void loadData() {
        Log.d("yle", Arrays.toString(currentDirectory.list()));
    }

    @Override
    public void insertData(String title, String desc) {

    }

}

