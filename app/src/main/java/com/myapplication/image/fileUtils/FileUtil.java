package com.myapplication.image.fileUtils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Administrator on 2021/6/5 0005.
 */

public class FileUtil {
    public static Bitmap bitmapFactory(Context context,Uri imageUri){
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(imageUri, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
        c.close();
        Bitmap image = BitmapFactory.decodeFile(imagePath);
        Bitmap b = Bitmap.createScaledBitmap(image, (int)(image.getWidth()*0.7), (int)(image.getHeight()*0.6), false);
        return b;
    }
}
