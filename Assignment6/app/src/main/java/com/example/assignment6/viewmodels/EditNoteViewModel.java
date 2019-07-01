package com.example.assignment6.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.assignment6.data.Item;
import com.example.assignment6.data.Note;
import com.example.assignment6.data.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class EditNoteViewModel extends AndroidViewModel {


    private NoteRepository repository;
    private MutableLiveData<List<Item>> checkedItems;
    private MutableLiveData<List<Item>> uncheckedItems;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        checkedItems = new MutableLiveData<>();
        uncheckedItems = new MutableLiveData<>();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void setCheckedItems(List<Item> checkedItems) {
        this.checkedItems.setValue(checkedItems);
    }

    public void setUncheckedItems(List<Item> uncheckedItems) {
        this.uncheckedItems.setValue(uncheckedItems);
    }

    public void newItemAdded(String title) {
        List<Item> items = uncheckedItems.getValue();
        if (items == null) items = new ArrayList<>();
        items.add(new Item(title, false));
        uncheckedItems.setValue(items);
    }

    public void changeItemChecked(int position, boolean isChecked) {
        List<Item> unchecked = uncheckedItems.getValue();
        if (unchecked == null) unchecked = new ArrayList<>();
        List<Item> checked = checkedItems.getValue();
        if (checked == null) checked = new ArrayList<>();
        Log.d("position", "" + position);
        if (isChecked) {
            if (position < checked.size())
                unchecked.add(checked.remove(position));
        } else {
            if (position < unchecked.size())
                checked.add(unchecked.remove(position));
        }
        checkedItems.setValue(checked);
        uncheckedItems.setValue(unchecked);
    }

    public MutableLiveData<List<Item>> getCheckedItems() {
        return checkedItems;
    }

    public MutableLiveData<List<Item>> getUncheckedItems() {
        return uncheckedItems;
    }
}
