package com.example.assignment6.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.data.Note;

import java.util.ArrayList;
import java.util.List;

import static com.example.assignment6.App.getContext;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Note> notes;
    private OnItemClickListener listener;


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ListView listView;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            listView = itemView.findViewById(R.id.note_item_list);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    public NotesAdapter() {
        this.notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_grid_item, viewGroup, false);
        return new SimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Note note = notes.get(i);
        SimpleViewHolder view = (SimpleViewHolder) viewHolder;
        view.title.setText(note.getName());
        view.listView.setAdapter(new UneditableListViewAdapter(getContext(), note.getContent()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateData(List<Note> list) {
        this.notes = list;
        notifyDataSetChanged();
    }


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SimpleViewHolder viewHolder = (SimpleViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            listener.onItemClick(notes.get(position));
        }
    };


    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
