package com.snayab.ahooraelevator.helpers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.snayab.ahooraelevator.app.App;

import java.io.File;


public class MathHelper {

    Context context;

    public MathHelper(Context context) {
        this.context = context;
    }

    public int convertDipToPixels(float dips) {
        return (int) (dips * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public int convertPxToDp(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density - 0.5f);
    }


    public String getView(int view) {
        String result = view + "";
        if (view > 1000) {
            return String.format("%.1f", (float) view / 1000) + " k";
        } else {
            return result;
        }
    }


    public String getPoint(float view) {
        return String.format("%.1f", view);
    }

    public int calculateDiscount(int withDiscount, int withoutDiscount) {
        return Math.round(((withoutDiscount - withDiscount) / (float) withoutDiscount) * 100);
    }


    public int[] getImageSize(Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(new File(uri.getPath()).getAbsolutePath(), options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        return new int[]{imageWidth, imageHeight};
    }

    public String convertMoneyToWithComma(String prc) {

        StringBuilder stringBuilder = new StringBuilder();

        int counter = 0;
        for (int i = prc.length() - 1; i > -1; i--) {
            if (counter == 3) {
                stringBuilder.append(",");
                stringBuilder.append(prc.charAt(i));
                counter = 1;
                continue;
            }
            counter++;
            stringBuilder.append(prc.charAt(i));
        }


        return stringBuilder.reverse().toString();
    }
}
