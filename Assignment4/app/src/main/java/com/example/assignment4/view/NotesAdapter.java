package com.example.assignment4.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment4.R;
import com.example.assignment4.data.NoteEntity;

import java.util.List;

import static com.example.assignment4.App.getContext;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NoteEntity> notes;
    private HomePageContract.Presenter presenter;


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ListView listView;

        public SimpleViewHolder(final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            listView = itemView.findViewById(R.id.note_item_list);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    public NotesAdapter(List<NoteEntity> notes, HomePageContract.Presenter presenter) {
        this.notes = notes;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_grid_item, viewGroup, false);
        return new SimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NoteEntity note = notes.get(i);
        SimpleViewHolder view = (SimpleViewHolder) viewHolder;
        view.title.setText(note.getName());
        view.listView.setAdapter(new UneditableListViewAdapter(getContext(), note.getContent()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int id = notes.get(position).getId();
            presenter.showEditNotePage(id);
        }
    };
}
