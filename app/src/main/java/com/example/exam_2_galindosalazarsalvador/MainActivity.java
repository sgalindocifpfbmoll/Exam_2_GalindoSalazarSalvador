package com.example.exam_2_galindosalazarsalvador;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // Objects
    private ImageView sunShape;
    private FrameLayout skyFrame;
    private Button buttonSecond;

    // Durations
    private final int animationDayDuration = 3000;
    private final int animationNightDuration = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the proper imageView
        sunShape = findViewById(R.id.image_sun);
        buttonSecond = findViewById(R.id.button_second);
        // Set the sky listener
        skyFrame = findViewById(R.id.layout_sky);
        skyFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation();
            }
        });
        // Set the button listener
        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    private void animation(){
        // Set the objectAnimators. The colors are stored in @values/colors
        ObjectAnimator animatorFirst = ObjectAnimator.ofFloat(sunShape, "y", skyFrame.getHeight());
        ObjectAnimator animatorSky = ObjectAnimator.ofInt(skyFrame, "backgroundColor",
                getResources().getColor(R.color.day), Color.GRAY, getResources().getColor(R.color.evening));
        // Set the preferences
        animatorFirst.setDuration(animationDayDuration);
        animatorSky.setDuration(animationDayDuration);
        animatorSky.setEvaluator(new ArgbEvaluator());
        // Animator set for introducing both animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorFirst, animatorSky);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                secondAnimation();
            }

            private void secondAnimation(){
                ObjectAnimator animatorSky = ObjectAnimator.ofInt(skyFrame, "backgroundColor",
                        getResources().getColor(R.color.evening), getResources().getColor(R.color.night));
                animatorSky.setDuration(animationNightDuration);
                animatorSky.setEvaluator(new ArgbEvaluator());
                animatorSky.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void changeActivity(){
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }
}