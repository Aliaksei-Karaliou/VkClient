package com.github.aliaksei_karaliou.vkclient.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class PicassoRoundTranformation implements Transformation {

    private static final String ROUND = "round";

    @Override
    public Bitmap transform(final Bitmap source) {
        final int size = Math.min(source.getWidth(), source.getHeight());

        final int x = (source.getWidth() - size) / 2;
        final int y = (source.getHeight() - size) / 2;

        final Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        final Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        final BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        final float r = size / 2;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return ROUND;
    }
}