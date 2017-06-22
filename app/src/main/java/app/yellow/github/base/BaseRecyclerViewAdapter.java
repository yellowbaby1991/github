package app.yellow.github.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        initAnimation();

        setOnLoadMoreListener(
                new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        loadMoreRequest();
                    }
                }
        );


    }

    protected abstract void loadMoreRequest();

    private void initAnimation() {
        openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1),
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1)
                };
            }
        });
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {

    }

}
