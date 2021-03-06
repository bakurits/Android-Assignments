package com.example.assignmen3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmen3.Model.FileModel;
import com.example.assignmen3.Presenter.IPresenter;
import com.example.assignmen3.Presenter.MainPresenter;
import com.example.assignmen3.View.IMainView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements IMainView {

    private IPresenter presenter;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView topPathView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        presenter = new MainPresenter(this);

        topPathView = findViewById(R.id.whole_path_view);

        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.deleteSelected();
            }
        });


        getPermissions();

    }

    @Override
    public void showGridView(FileModel file) {
        drawTopPath(file.getPath());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(file.listFileModels(), true, presenter);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showListView(FileModel file) {
        drawTopPath(file.getAbsolutePath());
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(file.listFileModels(), false, presenter);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void selectItem(int position) {
        View v = (recyclerView.getLayoutManager()).findViewByPosition(position);
        assert v != null;
        v.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void unSelectItem(int position) {
        View v = (recyclerView.getLayoutManager()).findViewByPosition(position);
        assert v != null;
        v.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void startSelectionMode() {
        findViewById(R.id.delete_button).setVisibility(View.VISIBLE);
    }

    @Override
    public void endSelectionMode() {
        findViewById(R.id.delete_button).setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayPdf(File file) {
        Bundle bundle = new Bundle();
        bundle.putString("fileName", file.getPath());
        Intent intent = new Intent(MainActivity.this, PdfViewActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void displayTxt(File file) {
        Bundle bundle = new Bundle();
        bundle.putString("fileName", file.getPath());
        Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getPermissions() {
        switch (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            case PackageManager.PERMISSION_DENIED:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                break;
            case PackageManager.PERMISSION_GRANTED:
                presenter.start();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                presenter.start();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (presenter.backButtonClick()) return;
        super.onBackPressed();
    }

    private void drawTopPath(String path) {
        int MAX_CHARACTER_COUNT = 50;
        if (path.length() < MAX_CHARACTER_COUNT) {
            topPathView.setText(path);
            return;
        }
        int pos = 0;
        while (true) {
            pos = path.indexOf('/', pos);

            if (pos == -1) {
                topPathView.setText(path.substring(path.length() - MAX_CHARACTER_COUNT));
                break;
            }

            String st = ".../" + path.substring(pos + 1);


            if (st.length() < MAX_CHARACTER_COUNT) {
                topPathView.setText(st);
                break;
            }
            pos++;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toggle_view) {
            presenter.toggleView();
            return true;
        }
        return false;
    }
}
