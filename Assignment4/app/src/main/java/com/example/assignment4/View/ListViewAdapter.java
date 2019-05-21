package com.example.assignment4.View;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteItem;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<NoteItem> {
    private List<NoteItem> items;


    private static class ViewHolder {
        EditText text;
        CheckBox checkBox;
        Button remove;
    }


    public ListViewAdapter(Context context, int resource, List<NoteItem> items) {
        super(context, R.layout.note_item_list);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteItem item = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.checkBox = convertView.findViewById(R.id.checkBox);
        viewHolder.text = convertView.findViewById(R.id.textView);
        viewHolder.remove = convertView.findViewById(R.id.button);


        assert item != null;
        viewHolder.text.setText(item.getTitle());
        return convertView;
    }
}
