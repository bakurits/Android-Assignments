package com.example.assignmen3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

public class MyDialogFragment extends DialogFragment {

    private EditText path;
    private Button save;
    private Button cancel;
    private File file;
    private MyDialogListener listener;


    public MyDialogFragment() {
        // Empty constructor is required for MyDialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static MyDialogFragment newInstance(String path) {
        MyDialogFragment frag = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("path", path);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.save_text_leyout, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        path = (EditText) view.findViewById(R.id.file_name);
        save = view.findViewById(R.id.save_button);
        cancel = view.findViewById(R.id.cancel_button);
        // Fetch arguments from bundle and set title
        String fileName = getArguments().getString("path", "");
        file = new File(fileName);


        // Show soft keyboard automatically and request focus to field
        path.setText(file.getName());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.saveFile(path.getText().toString());
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (MyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement MyDialogListener");
        }
    }

    public interface MyDialogListener {
        void saveFile(String fileName);
    }
}