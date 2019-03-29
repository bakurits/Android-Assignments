package com.example.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillRatedUserCount();
        setButtonListeners();
        setupRatings();
        TextView textView = findViewById(R.id.billionCount);
        textView.setText("1");


    }

    private void fillRatedUserCount() {
        int ratedUserCount = 5493032;

        String ratedUserCnt = NumberFormat.getNumberInstance(Locale.US).format(ratedUserCount);
        final TextView textView1 = findViewById(R.id.ratedUserCntTextView1);
        final TextView textView2 = findViewById(R.id.ratedUserCntTextView2);
        textView1.setText(ratedUserCnt);
        textView2.setText(ratedUserCnt);
    }

    private void setButtonListeners() {
        addSnackBarOnClick(findViewById(R.id.uninstallButton), "\"Uninstall\" button clicked");
        addSnackBarOnClick(findViewById(R.id.openButton), "\"Open\" button clicked");
        addSnackBarOnClick(findViewById(R.id.imageView3), "\"Travel & Local\" Icon clicked");
        addSnackBarOnClick(findViewById(R.id.imageView4), "\"Similar Icon\" clicked");
    }

    private void addSnackBarOnClick(final View clickable, final String textToDisplay) {
        clickable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Snackbar.make(v, textToDisplay, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setupRatings() {
        float averageRating = (float) 4.3;

        ((RatingBar) findViewById(R.id.ratingBarTop)).setRating(averageRating);
        ((RatingBar) findViewById(R.id.ratingBarBottom)).setRating(averageRating);


        ((TextView) findViewById(R.id.averageRatingTextView1)).setText(String.valueOf(averageRating));
        ((TextView) findViewById(R.id.averageRatingTextView1)).setText(String.valueOf(averageRating));

    }

}
