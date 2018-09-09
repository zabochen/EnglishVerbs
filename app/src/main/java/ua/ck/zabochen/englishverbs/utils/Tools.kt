package ua.ck.zabochen.englishverbs.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

class Tools {

    companion object {

        fun bitmapImageFromAssets(context: Context, imagePath: String): Bitmap {
            return BitmapFactory.decodeStream(context.assets.open(imagePath))
        }

        fun roundedImageFromAssets(context: Context, imagePath: String): RoundedBitmapDrawable {

            // Source image
            val sourceBitmapImage: Bitmap = BitmapFactory.decodeStream(context.assets.open(imagePath))

            // Crop center image
            val editBitmapImage: Bitmap = Bitmap.createBitmap(
                    sourceBitmapImage,
                    sourceBitmapImage.width / 2 - sourceBitmapImage.height / 2,
                    0,
                    sourceBitmapImage.height,
                    sourceBitmapImage.height
            )

            // Rounded image
            val roundedBitmapImage: RoundedBitmapDrawable = RoundedBitmapDrawableFactory.create(
                    context.resources,
                    editBitmapImage
            )
            roundedBitmapImage.cornerRadius = Math.max(editBitmapImage.width.toFloat(), editBitmapImage.height / 2.0f)
            return roundedBitmapImage
        }

    }

}

// Extensions

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}