package com.example.assignment4.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteItem;

import java.util.ArrayList;
import java.util.List;

public class UneditableListViewAdapter extends ArrayAdapter<NoteItem> {
    private List<NoteItem> items;
    private Context context;


    private static class ViewHolder {
        TextView text;
        CheckBox checkBox;
        Button remove;
    }


    public UneditableListViewAdapter(Context context, List<NoteItem> items) {
        super(context, R.layout.note_item_list);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NoteItem item = items.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_item_list, parent, false);
        }
        final ViewHolder viewHolder = new ViewHolder();
        viewHolder.checkBox = convertView.findViewById(R.id.checkBox);
        viewHolder.text = convertView.findViewById(R.id.editText);
        viewHolder.remove = convertView.findViewById(R.id.cancel_button);


        assert item != null;
        viewHolder.text.setText(item.getTitle());
        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public void add(NoteItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

}
