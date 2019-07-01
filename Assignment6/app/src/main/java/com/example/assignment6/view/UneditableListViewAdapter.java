package com.example.assignment6.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.data.Item;

import java.util.List;

public class UneditableListViewAdapter extends ArrayAdapter<Item> {
    private List<Item> items;
    private Context context;


    private static class ViewHolder {
        TextView text;
        CheckBox checkBox;
        Button remove;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public UneditableListViewAdapter(Context context, List<Item> items) {
        super(context, R.layout.note_item_list);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item item = items.get(position);
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
    public void add(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

}
