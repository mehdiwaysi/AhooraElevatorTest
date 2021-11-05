package com.snayab.ahooraelevator.helpers;

import android.app.Activity;
import android.util.DisplayMetrics;

public class GetDisplaySize {

    private Activity context;
    private int count;
    private Double ratioWidth;
    private Double ratioHeight;
    private Double minor;

    public GetDisplaySize(Activity context , int count , Double minor, Double ratioWidth, Double ratioHeight) {
        this.context = context;
        this.count = count;
        this.ratioWidth = ratioWidth;
        this.ratioHeight = ratioHeight;
        this.minor = minor;
    }

    public GetDisplaySize(Activity context) {
        this.context = context;
        this.count = count;
        this.ratioWidth = ratioWidth;
        this.ratioHeight = ratioHeight;
        this.minor = minor;
    }


    public int getHeightItem() {

       return  (int) (getWidthItem() *(ratioHeight/ratioWidth));
    }


    public int getWidthItem() {

        return  (int) (getWidth()  / (count+minor));
    }





    public  int getWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        return displayMetrics.widthPixels;
    }

    public int getHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


}


