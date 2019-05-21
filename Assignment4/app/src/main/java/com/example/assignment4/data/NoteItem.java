package com.example.assignment4.data;

public class NoteItem {
    private String title;
    private boolean checked;

    public NoteItem(String title, boolean checked) {
        this.title = title;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getTitle() {
        return title;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
