package com.example.assignmen3.Presenter;


import android.content.Context;
import android.graphics.Path;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.assignmen3.MainActivity;
import com.example.assignmen3.Model.FileModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.HashSet;
import java.util.List;

public class MainPresenter implements IPresenter {

    private MainActivity view;
    private FileModel currentDirectory;
    private boolean isGrid;
    private boolean selectionMode;
    private HashSet<Integer> selectedPositions;
    private HashSet<String> selectedFiles;

    public MainPresenter(MainActivity view) {
        this.view = view;
        isGrid = false;
        selectionMode = false;
        selectedPositions = new HashSet<>();
        selectedFiles = new HashSet<>();
    }

    @Override
    public void start() {
        currentDirectory = new FileModel(Environment.getExternalStorageDirectory().getPath());
        draw();
    }


    @Override
    public void changeFolder(String name) {
        currentDirectory = new FileModel(currentDirectory, name);
        draw();
    }


    @Override
    public boolean goToParent() {
        if (currentDirectory.getPath() == null) return false;
        currentDirectory = new FileModel(currentDirectory.getParent());
        draw();
        return true;
    }

    private void draw() {
        selectedPositions.clear();
        selectedFiles.clear();
        if (selectionMode) view.endSelectionMode();
        selectionMode = false;
        if (isGrid) view.showGridView(currentDirectory);
        if (!isGrid) view.showListView(currentDirectory);
    }

    @Override
    public void toggleView() {
        isGrid = !isGrid;
        draw();
    }

    @Override
    public void fileClick(int position, String fileName) {
        if (selectionMode) {
            selectItem(position, fileName);
            return;
        }
        displayFile(fileName);
    }

    private void displayFile(String fileName) {
        FileModel file = new FileModel(currentDirectory, fileName);
        if (file.getExtension().equals("pdf")) {
            view.displayPdf(new File(currentDirectory, fileName));
            return;
        }
        if (file.getExtension().equals("txt")) {
            view.displayTxt(new File(currentDirectory, fileName));
            return;
        }
        if (file.isDirectory()) changeFolder(fileName);
    }

    @Override
    public void fileLongClick(int position, String fileName) {
        if (!selectionMode) {
            view.startSelectionMode();
        }
        selectionMode = true;
        selectItem(position, fileName);
    }

    @Override
    public void deleteSelected() {
        for (String fileName : selectedFiles) {
            deleteFile(new File(currentDirectory, fileName));
        }
        draw();
    }

    private void deleteFile(File file) {
        if (!file.exists()) return;
        if (file.isFile()) {
            if (!file.delete()) Log.d("cant delete file", file.getParent());
            return;
        }

        for (File childFile : file.listFiles()) {
            deleteFile(childFile);
        }
        if (!file.delete()) Log.d("cant delete file", file.getParent());
    }

    private void selectItem(int position, String fileName) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position);
            selectedFiles.remove(fileName);
            view.unSelectItem(position);
        } else {
            selectedPositions.add(position);
            selectedFiles.add(fileName);
            view.selectItem(position);
        }
    }
}

