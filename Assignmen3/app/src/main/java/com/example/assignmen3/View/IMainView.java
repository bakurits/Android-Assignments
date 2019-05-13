package com.example.assignmen3.View;

import com.example.assignmen3.Model.FileModel;

import java.util.List;

public interface IMainView {
    void showGridView(FileModel file);

    void showListView(FileModel file);

    void selectItem(int position);

    void unSelectItem(int position);

    void startSelectionMode();

    void endSelectionMode();

    void showEmptyDataError();
}
