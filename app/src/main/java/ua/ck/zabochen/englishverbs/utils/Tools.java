package ua.ck.zabochen.englishverbs.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import java.io.IOException;

public class Tools {

    public static RoundedBitmapDrawable getRoundedImageFromAssets(Context context, String assetImageUrl) {

        RoundedBitmapDrawable roundedBitmapImage = null;

        try {

            // Source image
            Bitmap sourceBitmapImage = BitmapFactory.decodeStream(context.getAssets().open(assetImageUrl));

            // Crop center image
            Bitmap editBitmapImage = Bitmap.createBitmap(
                    sourceBitmapImage,
                    sourceBitmapImage.getWidth() / 2 - sourceBitmapImage.getHeight() / 2,
                    0,
                    sourceBitmapImage.getHeight(),
                    sourceBitmapImage.getHeight()
            );

            // Rounded image
            roundedBitmapImage = RoundedBitmapDrawableFactory.create(
                    context.getResources(),
                    editBitmapImage
            );
            roundedBitmapImage.setCornerRadius(Math.max(editBitmapImage.getWidth(), editBitmapImage.getHeight() / 2.0f));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return roundedBitmapImage;

    }

    public static RoundedBitmapDrawable getRoundedImageFromResources(Context context, int imageId) {

        // Source image
        Bitmap sourceBitmapImage = BitmapFactory.decodeResource(
                context.getResources(),
                imageId
        );

        // Crop center image
        Bitmap editBitmapImage = Bitmap.createBitmap(
                sourceBitmapImage,
                sourceBitmapImage.getWidth() / 2 - sourceBitmapImage.getHeight() / 2,
                0,
                sourceBitmapImage.getHeight(),
                sourceBitmapImage.getHeight()
        );

        // Rounded image
        RoundedBitmapDrawable roundedBitmapImage = RoundedBitmapDrawableFactory.create(
                context.getResources(),
                editBitmapImage
        );
        roundedBitmapImage.setCornerRadius(Math.max(editBitmapImage.getWidth(), editBitmapImage.getHeight() / 2.0f));

        return roundedBitmapImage;

    }
}
