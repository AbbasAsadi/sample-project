package com.baseProject.android.util.uploadImage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.OpenableColumns;
import android.util.Log;

import com.baseProject.android.util.requestBody.ContentUriRequestBody;
import com.baseProject.android.util.requestBody.UploadRequestBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadImageUtils {

    public static Intent createIntentForOpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png", "image/jpg"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        return galleryIntent;
    }

    public static Bitmap createBitmapFromUri(Uri imageUri, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = contentResolver.openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap optimizeBitmap(Bitmap fullSizeBitmap) {
        return reduceBitmapSize(fullSizeBitmap, 563000);
    }

    public static MultipartBody.Part getMultiPartFromBitmap(Bitmap bitmap, String name, Context context, UploadRequestBody.UploadCallback callback) {
        File reduceFile = getBitMapFile(bitmap, context, name);
        RequestBody requestBody = new UploadRequestBody(reduceFile, "image", callback);
        return MultipartBody.Part.createFormData(
                name, name, requestBody);
    }

    private static File getBitMapFile(Bitmap bitmap, Context context, String name) {
        File file = new File(context.getExternalCacheDir().getAbsolutePath() + File.separator + "reduced_file-" + name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bitmapData = byteArrayOutputStream.toByteArray();

        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bitmapData);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Bitmap reduceBitmapSize(Bitmap bitmap, int MAX_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / MAX_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        bitmap = Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
        Matrix matrix = new Matrix();
        matrix.postRotate(0);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static RequestBody getRequestBodyFromUri(Uri imageUri, String name, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return new ContentUriRequestBody(contentResolver, imageUri);
    }

    public static String getFileName(Uri uri, Context context) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor =
                    context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
