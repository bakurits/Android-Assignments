package com.example.assignment4.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteItem;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<NoteItem> {
    private List<NoteItem> items;
    private Context context;
    private boolean checked;
    private EditNotePageContract.Presenter presenter;

    private static class ViewHolder {
        EditText text;
        CheckBox checkBox;
        Button remove;
    }


    public ListViewAdapter(EditNotePageContract.Presenter presenter, Context context, List<NoteItem> items, boolean checked) {
        super(context, R.layout.items_list_view_editable);
        this.items = items;
        this.context = context;
        this.checked = checked;
        this.presenter = presenter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final NoteItem item = items.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.items_list_view_editable, parent, false);
        }
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.checkBox = convertView.findViewById(R.id.checkBox);
        viewHolder.text = convertView.findViewById(R.id.editText);
        viewHolder.remove = convertView.findViewById(R.id.cancel_button);


        assert item != null;

        viewHolder.checkBox.setChecked(checked);
        viewHolder.text.setText(item.getTitle());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.migrateItem(checked, position);
            }
        });
        viewHolder.text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    item.setTitle(viewHolder.text.getText().toString());
                    notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void add(NoteItem item) {
        item.setChecked(checked);
        items.add(item);
        notifyDataSetChanged();
    }


    public List<NoteItem> getItems() {
        if (items == null) return new ArrayList<>();
        return items;
    }
}
