package com.example.myapplication;

import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        starClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TypedArray stars = getResources().obtainTypedArray(R.array.stars_array);
                int sz = stars.length();
                boolean black = true;
                System.out.println(sz);
                for (int i = 0; i < sz; i++) {
                    int resId = stars.getResourceId(i, -1);
                    if (resId == -1) continue;

                    ImageView img = findViewById(resId);


                    if (black)
                        img.setImageResource(R.drawable.icons_star_filled);
                    else
                        img.setImageResource(R.drawable.icons_star_empty);

                    if (v.equals(img)) black = false;

                }
                stars.recycle();
            }
        };


        fillRatedUserCount();
        setButtonListeners();
        setupRatingBarListeners();
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

    private View.OnClickListener starClickListener;

    private void setupRatingBarListeners() {
        TypedArray stars = getResources().obtainTypedArray(R.array.stars_array);

        int sz = stars.length();

        for (int i = 0; i < sz; i++) {
            int resId = stars.getResourceId(i, -1);
            if (resId > -1)
                findViewById(resId).setOnClickListener(starClickListener);

        }
        stars.recycle();
    }


}