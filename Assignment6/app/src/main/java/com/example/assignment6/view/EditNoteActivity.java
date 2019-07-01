package com.example.assignment6.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.assignment6.R;
import com.example.assignment6.adapters.ListViewAdapter;
import com.example.assignment6.viewmodels.EditNoteViewModel;
import com.example.assignment6.data.Item;
import com.example.assignment6.data.Note;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment6.App.getContext;

public class EditNoteActivity extends AppCompatActivity {
    public static final String NOTE =
            "com.example.assignment6.NOTE";

    private EditNoteViewModel model;

    private ImageButton backButton;
    private ImageButton pinButton;
    private EditText title;
    private ListView doneItems;
    private ListView noDoneItems;
    private CheckBox addItemCheckBox;
    private EditText addItemText;
    private Button addItemCancel;
    private ListViewAdapter uncheckedAdapter;
    private ListViewAdapter checkedAdapter;

    private int id = -1;
    private boolean pinned = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view_layout);


        backButton = findViewById(R.id.back_button);
        pinButton = findViewById(R.id.pin_button);

        title = findViewById(R.id.note_title);

        doneItems = findViewById(R.id.checked_items);
        noDoneItems = findViewById(R.id.unchecked_items);

        addItemCheckBox = findViewById(R.id.checkBox2);
        addItemText = findViewById(R.id.add_item_text);
        addItemCancel = findViewById(R.id.cancel_button);

        model = ViewModelProviders.of(this).get(EditNoteViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        pinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinned = !pinned;
                showPin();
            }
        });

        addItemText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addNewItem(addItemText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        addItemCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemText.setText("");
            }
        });

        model.getCheckedItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                checkedAdapter.setItems(items);
            }
        });

        model.getUncheckedItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                uncheckedAdapter.setItems(items);
            }
        });

        drawNote();
    }

    private void drawNote() {
        Intent intent = getIntent();
        Note note;
        if (intent.hasExtra(NOTE)) {
            note = (Note) intent.getSerializableExtra(NOTE);
            id = note.getId();
            pinned = note.isPinned();
        } else {
            note = new Note("", new ArrayList<Item>());
            pinned = false;
        }

        showData(note);

    }

    private Note getData() {
        ArrayList<Item> items = new ArrayList<>();
        for (Item item : checkedAdapter.getItems()) {
            item.setChecked(true);
            items.add(item);
        }
        for (Item item : uncheckedAdapter.getItems()) {
            item.setChecked(false);
            items.add(item);
        }
        return new Note(title.getText().toString(), items);
    }


    private void showData(Note note) {
        title.setText(note.getName());
        List<Item> lst = note.getContent();
        ArrayList<Item> checked = new ArrayList<>();
        ArrayList<Item> unchecked = new ArrayList<>();
        for (Item item : lst) {
            if (item.isChecked()) {
                checked.add(item);
            } else {
                unchecked.add(item);
            }
        }

        model.setCheckedItems(checked);
        model.setUncheckedItems(unchecked);
        uncheckedAdapter = new ListViewAdapter(model, getContext(), unchecked, false);
        checkedAdapter = new ListViewAdapter(model, getContext(), checked, true);

        doneItems.setAdapter(checkedAdapter);
        noDoneItems.setAdapter(uncheckedAdapter);
        showPin();
    }

    private void showPin() {
        if (!pinned) {
            pinButton.setImageDrawable(getDrawable(R.drawable.baseline_notifications_white));
        } else {
            pinButton.setImageDrawable(getDrawable(R.drawable.baseline_notifications_black));
        }
    }

    private void addNewItem(String name) {
        model.newItemAdded(addItemText.getText().toString());
        addItemText.setText("");
    }

    private void saveNote() {
        Note note = getData();
        if (id != -1) {
            note.setId(id);
            model.update(note);
        } else {
            model.insert(note);
        }
        note.setPinned(pinned);
        finish();
    }
}
