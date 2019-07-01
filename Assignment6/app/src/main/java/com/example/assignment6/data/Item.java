package com.example.assignment6.data;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private boolean checked;

    public Item(String title, boolean checked) {
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
