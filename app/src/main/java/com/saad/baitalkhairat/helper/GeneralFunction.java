package com.saad.baitalkhairat.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saad.baitalkhairat.App;
import com.saad.baitalkhairat.R;
import com.saad.baitalkhairat.utils.ProgressRequestBody;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.saad.baitalkhairat.utils.ImageUtils.blurRenderScript;

public class GeneralFunction {

    public static void tintImage(ImageView imageView, int color) {
        imageView.setColorFilter(ContextCompat.getColor(App.getInstance().getApplicationContext(),
                color), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public static Bitmap getBitmapFromVector(Drawable drawable) {
        try {
            Bitmap bitmap;

            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }

    public static Bitmap addGradient(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);

        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height,
                0xF1DD84, 0xFFB2773C, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);
        TextView textView = new TextView(App.getInstance().getApplicationContext());
        return updatedBitmap;
    }

    public static void addGradientToText(Bitmap originalBitmap, TextView textView) {
        int width = textView.getWidth();
        int height = textView.getHeight();
        Canvas canvas = new Canvas();

        canvas.drawBitmap(originalBitmap, 0, 0, null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height,
                0xF1DD84, 0xFFB2773C, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);
        textView.getPaint().setShader(shader);
    }


    public static void loadImage(Context mContext, String imgUrl, ImageView imageView) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.color.navigation_gray);
        Glide.with(mContext).applyDefaultRequestOptions(requestOptions).load(imgUrl).into(imageView);

    }

    public static void loadImage(Context mContext, Uri imgUrl, ImageView imageView) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.error(R.mipmap.ic_launcher);
        Glide.with(mContext).applyDefaultRequestOptions(requestOptions).load(imgUrl).into(imageView);

    }

    public static void loadImageWithBlure(Context mContext, String imgUrl, ImageView imageView) {
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.ic_loading);
//        requestOptions.error(R.color.navigation_gray);
//        Glide.with(mContext).applyDefaultRequestOptions(requestOptions)
//                .asBitmap()
//                .load(imgUrl)
//                .into(new CustomTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
////                    imageView.setImageBitmap(blurRenderScript(mContext, resource, 1));
//
//                    Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//                        @Override
//                        public void onGenerated(Palette palette) {
//                            // Here's your generated palette
//                            Palette.Swatch swatch = palette.getDarkVibrantSwatch();
//                            int color = palette.getDarkVibrantColor(swatch.getTitleTextColor());
//                        }
//                    });
//                }
//
//                @Override
//                public void onLoadCleared(@Nullable Drawable placeholder) {
//                }
//        });


//        Bitmap blurred = blurRenderScript(getApplicationContext(), bitmap, 10);

        if (!imgUrl.isEmpty()) {
            Picasso.with(mContext).load(imgUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    // loaded bitmap is here (bitmap)
                    imageView.setImageBitmap(blurRenderScript(mContext, bitmap, 1));
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    imageView.setImageResource(R.color.navigation_gray);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            });
        } else {
            imageView.setImageResource(R.color.navigation_gray);
        }
    }

    public static MultipartBody.Part getImageMultipart(String path, String name) {
        File file = new File(path);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(name, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        return filePart;
    }

    public static MultipartBody.Part getImageMultiPartWithProgress(String path, String name, ProgressRequestBody.UploadCallbacks uploadCallbacks) {
        File file = new File(path);
        ProgressRequestBody fileBody = new ProgressRequestBody(file, "image", uploadCallbacks);
        MultipartBody.Part filePart =
                MultipartBody.Part.createFormData(name, file.getName(), fileBody);
        return filePart;
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public static String generateRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static void rotateImageView(ImageView imageView, boolean original) {
        Animation animation;
        if (original) {
            animation = new RotateAnimation(
                    -180,
                    0,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        } else {
            animation = new RotateAnimation(
                    0,
                    -180,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }

        animation.setDuration(200);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
