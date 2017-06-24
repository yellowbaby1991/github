package app.yellow.github.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.pnikosis.materialishprogress.ProgressWheel;

import app.yellow.github.R;

public class GlideUtil {

    public static void loadImageWithProgressWheel(String url, ImageView imageView, final ProgressWheel progressWheel){
        Glide.with(UIUtils.getContext())
                .load(url)
                .error(R.mipmap.icon_login)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressWheel.setProgress(0);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressWheel.setProgress(0);
                        return false;
                    }
                })
                .into(imageView);
    }
}
