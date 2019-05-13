package com.example.assignmen3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmen3.Model.FileModel;
import com.example.assignmen3.R;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.res.TypedArrayUtils.getString;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int LIST_VIEW = 0;
    public static final int GRID_VIEW = 1;


    private List<FileModel> files;
    private boolean isGrid;

    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView icon;

        GridViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.grid_file_name);
            icon = view.findViewById(R.id.grid_file_icon);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name, itemCnt, lastModified;
        ImageView icon;

        ListViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.list_file_name);
            itemCnt = view.findViewById(R.id.list_file_item_cnt);
            lastModified = view.findViewById(R.id.list_file_last_modify_time);
            name = view.findViewById(R.id.list_file_name);
            icon = view.findViewById(R.id.list_file_icon);
        }
    }

    RecyclerViewAdapter(List<FileModel> files, boolean isGrid) {
        this.files = files;
        this.isGrid = isGrid;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case LIST_VIEW:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
                return new ListViewHolder(itemView);

            case GRID_VIEW:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_item, parent, false);
                return new GridViewHolder(itemView);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FileModel file = files.get(i);
        if (!isGrid) {
            ListViewHolder view = (ListViewHolder) viewHolder;
            view.icon.setImageResource(file.getIconID());

            view.name.setText(file.getName());

            if (file.list() != null) {
                int asd = 1;
            }

            if (file.isDirectory()) {
                String itemCntString = view.itemCnt.getContext().getString(R.string.item_cnt, file.itemCnt());
                view.itemCnt.setText(itemCntString);
            } else {
                view.itemCnt.setText("");
            }

            Date date = new Date(file.lastModified());
            view.lastModified.setText(date.toString());

        } else {
            GridViewHolder view = (GridViewHolder) viewHolder;
            view.icon.setImageResource(file.getIconID());
            view.name.setText(file.getName());
        }
    }


    @Override
    public int getItemCount() {
        return files.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isGrid) return GRID_VIEW;
        else return LIST_VIEW;
    }


    public void toggleView() {
        isGrid = !isGrid;
    }


}
