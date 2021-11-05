package com.snayab.ahooraelevator.ui.fragments.category.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.snayab.ahooraelevator.BuildConfig;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityElevatorDetailsMainBinding;
import com.snayab.ahooraelevator.helpers.GetDisplaySize;
import com.snayab.ahooraelevator.helpers.SizeResponsiveHelper;
import com.snayab.ahooraelevator.ui.base.BaseActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ElevatorDetailsActivity extends BaseActivity implements View.OnClickListener, Html.ImageGetter {

    private String TAG = this.getClass().getName();
    private ActivityElevatorDetailsMainBinding binding;
    private Intent intent;
    private Drawable empty;
    private GetDisplaySize getDisplaySize;
    private SizeResponsiveHelper sizeResponsiveHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityElevatorDetailsMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        intent = getIntent();


        if (intent.hasExtra("title"))
            binding.textViewTitle.setText(intent.getStringExtra("title"));

        if (intent.hasExtra("description")) {

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                binding.textViewDescription.setText(Html.fromHtml("" + intent.getStringExtra("description"), Html.FROM_HTML_MODE_COMPACT));
//            } else {
//                binding.textViewDescription.setText(Html.fromHtml("" + intent.getStringExtra("description")));
//            }


            Spanned spanned = Html.fromHtml(intent.getStringExtra("description"), ElevatorDetailsActivity.this, null);
            binding.textViewDescription.setText(spanned);

        }

        if (intent.hasExtra("picture"))
            Glide.with(this).load(BuildConfig.SITE_URL + intent.getStringExtra("picture")).into(binding.imageView);

        initView();
        initObjects();
    }

    private void initView() {
        binding.imageViewBack.setOnClickListener(this);
    }

    private void initObjects() {
        binding.imageViewBack.setOnClickListener(this);

        getDisplaySize = new GetDisplaySize(this);
        sizeResponsiveHelper = new SizeResponsiveHelper(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewBack: {
                onBackPressed();
                break;
            }
        }
    }

    @Override
    public Drawable getDrawable(String s) {
        LevelListDrawable d = new LevelListDrawable();
        empty = getResources().getDrawable(R.drawable.ic_loading);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(s, d);
        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {
        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);

                mDrawable.setBounds(0, 0, getDisplaySize.getWidth(),
                        sizeResponsiveHelper.getDimenResponsive(150));

//                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
//                mDrawable.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());
                mDrawable.setLevel(1);
                CharSequence t = binding.textViewDescription.getText();
                binding.textViewDescription.setText(t);
            }
        }

    }
}