package com.example.assignmen3.Presenter;

public interface IPresenter {
    void start();

    void loadData();

    void changeFolder(String name);

    boolean goToParent();

    void insertData(String title, String desc);
}
