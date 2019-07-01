package com.example.assignment6.view;

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

import com.example.assignment6.R;
import com.example.assignment6.data.EditNoteViewModel;
import com.example.assignment6.data.Item;
import com.example.assignment6.data.Note;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Item> {
    private List<Item> items;
    private Context context;
    private boolean checked;

    private EditNoteViewModel model;

    private static class ViewHolder {
        EditText text;
        CheckBox checkBox;
        Button remove;
    }


    public ListViewAdapter(EditNoteViewModel model, Context context, List<Item> items, boolean checked) {
        super(context, R.layout.items_list_view_editable);
        this.items = items;
        this.context = context;
        this.model = model;
        this.checked = checked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Item item = items.get(position);
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
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.changeItemChecked(position, checked);
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
    public void add(Item item) {
        item.setChecked(checked);
        items.add(item);
        notifyDataSetChanged();
    }


    public List<Item> getItems() {
        if (items == null) return new ArrayList<>();
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
