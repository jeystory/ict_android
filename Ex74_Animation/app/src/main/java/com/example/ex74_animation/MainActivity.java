package com.example.ex74_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;

    Animation ani_left, ani_right;

    LinearLayout base, page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        base = findViewById(R.id.base);
        ani_left = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        ani_right = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener aniListener = new SlidingPageAnimationListener();

        ani_left.setAnimationListener(aniListener);
        ani_right.setAnimationListener(aniListener);

        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    page.startAnimation(ani_right);
                }else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(ani_left);

                }
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) { }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.VISIBLE);
                isPageOpen = false;
            }else{
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) { }
    }
}
