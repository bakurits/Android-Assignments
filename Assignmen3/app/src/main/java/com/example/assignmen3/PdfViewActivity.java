package com.example.assignmen3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if (b != null)
            value = b.getString("fileName");

        if (value == null || value.length() == 0) return;


        File file = new File(value);
        PDFView view = new PDFView(this, null);
        ((PDFView) findViewById(R.id.pdfView)).fromFile(file).load();

    }

}
