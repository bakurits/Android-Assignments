package com.example.assignmen3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmen3.Model.FileModel;
import com.example.assignmen3.Presenter.IPresenter;
import com.example.assignmen3.R;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.v4.content.res.TypedArrayUtils.getDrawable;
import static android.support.v4.content.res.TypedArrayUtils.getString;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int LIST_VIEW = 0;
    public static final int GRID_VIEW = 1;

    private View.OnClickListener mOnItemClickListener;


    private List<FileModel> files;
    private boolean isGrid;
    private IPresenter presenter;


    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView icon;

        GridViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.grid_file_name);
            icon = view.findViewById(R.id.grid_file_icon);
            view.setTag(this);
            view.setOnClickListener(onItemClickListener);
            view.setOnLongClickListener(onItemLongClickListener);
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
            view.setTag(this);
            view.setOnClickListener(onItemClickListener);
            view.setOnLongClickListener(onItemLongClickListener);
        }
    }

    RecyclerViewAdapter(List<FileModel> files, boolean isGrid, IPresenter presenter) {
        this.files = files;
        this.isGrid = isGrid;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == LIST_VIEW) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
            return new ListViewHolder(itemView);
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_item, parent, false);
        return new GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FileModel file = files.get(i);
        if (!isGrid) {
            ListViewHolder view = (ListViewHolder) viewHolder;
            view.icon.setImageResource(file.getIconID());

            view.name.setText(file.getName());

            if (file.isDirectory()) {
                String itemCntString = view.itemCnt.getContext().getString(R.string.item_cnt, file.itemCnt());
                view.itemCnt.setText(itemCntString);
            } else {
                view.itemCnt.setText("");
            }

            Date date = new Date(file.lastModified());
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            view.lastModified.setText(dt.format(date));

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


    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            presenter.fileClick(position, files.get(position).getName());
        }
    };


    private View.OnLongClickListener onItemLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            presenter.fileLongClick(position, files.get(position).getName());
            return true;
        }
    };


}
