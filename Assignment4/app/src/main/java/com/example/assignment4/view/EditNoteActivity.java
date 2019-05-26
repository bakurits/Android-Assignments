package com.example.assignment4.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteEntity;
import com.example.assignment4.data.NoteItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment4.App.getContext;

public class EditNoteActivity extends AppCompatActivity implements EditNotePageContract.View {

    private EditNotePageContract.Presenter presenter;

    private Button backButton;
    private Button pinButton;
    private EditText title;
    private ListView doneItems;
    private ListView noDoneItems;
    private CheckBox addItemCheckBox;
    private EditText addItemText;
    private Button addItemCancel;
    private ListViewAdapter notDoneListAdapter;
    private ListViewAdapter doneListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view_layout);
        Bundle b = getIntent().getExtras();
        int value = -1; // or other values
        if (b != null)
            value = b.getInt("id");

        backButton = findViewById(R.id.back_button);
        pinButton = findViewById(R.id.pin_button);

        title = findViewById(R.id.note_title);

        doneItems = findViewById(R.id.checked_items);
        noDoneItems = findViewById(R.id.unchecked_items);

        addItemCheckBox = findViewById(R.id.checkBox2);
        addItemText = findViewById(R.id.add_item_text);
        addItemCancel = findViewById(R.id.cancel_button);


        presenter = new EditNotePresenter(this, value);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backButtonClicked();
            }
        });

        pinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.togglePin();
            }
        });
        addItemText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    presenter.newItemAdded(addItemText.getText().toString());
                    addItemText.setText("");
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
        presenter.showNote();

    }

    @Override
    public void showData(NoteEntity note) {
        List<NoteItem> lst = note.getContent();
        ArrayList<NoteItem> checked = new ArrayList<>();
        ArrayList<NoteItem> unchecked = new ArrayList<>();
        for (NoteItem item : lst) {
            if (item.isChecked()) {
                checked.add(item);
            } else {
                unchecked.add(item);
            }
        }
        notDoneListAdapter = new ListViewAdapter(presenter, getContext(), unchecked, false);
        doneListAdapter = new ListViewAdapter(presenter, getContext(), checked, true);
        doneItems.setAdapter(doneListAdapter);
        noDoneItems.setAdapter(notDoneListAdapter);
        presenter.setPin(note.isPinned());
    }


    @Override
    public NoteEntity getData() {
        ArrayList<NoteItem> items = new ArrayList<>();
        items.addAll(doneListAdapter.getItems());
        items.addAll(notDoneListAdapter.getItems());
        return new NoteEntity(title.getText().toString(), items);
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void showContentLoading() {

    }

    @Override
    public void setPinned(boolean pin) {

    }

    @Override
    public void hideContentLoading() {

    }

    @Override
    public void addNewItem(String name) {
        notDoneListAdapter.add(new NoteItem(name, false));
    }

    @Override
    public void migrateItem(boolean checked, int position) {
        if (checked) {
            NoteItem item = doneListAdapter.getItems().get(position);
            doneListAdapter.getItems().remove(position);
            notDoneListAdapter.add(item);
            doneListAdapter.notifyDataSetChanged();
            notDoneListAdapter.notifyDataSetChanged();
        } else {
            NoteItem item = notDoneListAdapter.getItems().get(position);
            notDoneListAdapter.getItems().remove(position);
            doneListAdapter.add(item);
            doneListAdapter.notifyDataSetChanged();
            notDoneListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showContentError() {

    }

    @Override
    public void hideContentError() {

    }

    @Override
    public void showEmptyResultsView() {
        ArrayList<NoteItem> checked = new ArrayList<>();
        ArrayList<NoteItem> unchecked = new ArrayList<>();
        notDoneListAdapter = new ListViewAdapter(presenter, getContext(), unchecked, false);
        doneListAdapter = new ListViewAdapter(presenter, getContext(), checked, true);
        doneItems.setAdapter(doneListAdapter);
        noDoneItems.setAdapter(notDoneListAdapter);
        presenter.setPin(false);
    }

    @Override
    public void hideEmptyResultsView() {

    }
}
