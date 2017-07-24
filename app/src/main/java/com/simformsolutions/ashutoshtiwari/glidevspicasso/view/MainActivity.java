package com.simformsolutions.ashutoshtiwari.glidevspicasso.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.simformsolutions.ashutoshtiwari.glidevspicasso.R;
import com.simformsolutions.ashutoshtiwari.glidevspicasso.databinding.ActivityMainBinding;
import com.simformsolutions.ashutoshtiwari.glidevspicasso.model.ImageModel;
import com.simformsolutions.ashutoshtiwari.glidevspicasso.utils.ImageUtils;
import com.simformsolutions.ashutoshtiwari.glidevspicasso.viewmodel.ImageViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setImageViewModel(
                new ImageViewModel(
                        new ImageModel(
                                "https://pbs.twimg.com/profile_images/2652314177/3c0f918ced0ad55d8a628c1df7739b62_400x400.png"
                        )
                )
        );
    }

    @BindingAdapter({"glideImage"})
    public static void loadGlide(ImageView imageView, String imageUrl) {

        Animation animation = AnimationUtils.loadAnimation(imageView.getContext(), android.R.anim.fade_in);

        Glide
                .with(imageView.getContext())
                .load(imageUrl)
                .thumbnail(0.5f) //Thumbnail
                .override(200, 200) //Optional to mention
                .centerCrop()
                .transform(new GlideCircleTransform(imageView.getContext()))
                .placeholder(R.drawable.google_photos_400x400)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .animate(animation) //Animation
                .into(imageView);

        //Supports Thumbnails, Animation
    }

    @BindingAdapter({"picassoImage"})
    public static void loadPicasso(ImageView imageView, String imageUrl) {
        Picasso
                .with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .resize(200, 200) // Compulsory to mention for using centerCrop
                .transform(new PicassoCircleTransform())
                .placeholder(R.drawable.google_photos_400x400)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        //Doesn't support thumbnail and no direct approach for animations
    }

    private static class GlideCircleTransform extends BitmapTransformation {

        GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return ImageUtils.getCirclularBitmapImage(toTransform);
        }

        @Override
        public String getId() {
            return "Glide_Circle_Transformation";
        }
    }

    private static class PicassoCircleTransform implements Transformation {

        PicassoCircleTransform() {
        }

        @Override
        public Bitmap transform(Bitmap source) {
            return ImageUtils.getCirclularBitmapImage(source);
        }

        @Override
        public String key() {
            return "circle-image";
        }
    }
}
