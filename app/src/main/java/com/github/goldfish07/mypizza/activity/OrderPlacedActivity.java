package com.github.goldfish07.mypizza.activity;

import static com.github.goldfish07.mypizza.Constants.REQUEST_CODE_ORDER_PLACED;

import android.animation.Animator;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.github.goldfish07.mypizza.R;

/**
 * start this activity when trigger order from {@link ViewCartActivity}
 */
public class OrderPlacedActivity extends ViewCartActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        LottieAnimationView  animationView = findViewById(R.id.animationView);
        animationView.setAnimation(R.raw.success_tick);
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setResult(REQUEST_CODE_ORDER_PLACED);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
              //  animator.pause();
            }
        });
    }
}
