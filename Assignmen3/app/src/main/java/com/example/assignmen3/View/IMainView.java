package com.example.assignmen3.View;

import com.example.assignmen3.Model.FileModel;

import java.util.List;

public interface IMainView {
    void showGridView(FileModel file);

    void showListView(FileModel file);


    void showEmptyDataError();
}
