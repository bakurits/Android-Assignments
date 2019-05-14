package com.example.assignmen3.Presenter;

public interface IPresenter {
    void start();

    void changeFolder(String name);

    boolean goToParent();

    void toggleView();

    void fileClick(int position, String filename);

    void fileLongClick(int position, String filename);

    void deleteSelected();

    boolean backButtonClick();

}
