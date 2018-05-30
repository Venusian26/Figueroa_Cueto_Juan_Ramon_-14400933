package com.example.juan.mianimacionjava;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void onButtonClick(View v) {

        imageView.animate()
                .rotation(360)
                .rotationXBy(70)
                .rotationY(10)
                .translationX(20)
                .translationY(50)
                .rotationXBy(-70)
                .scaleX(2)
                .scaleY(-2)
                .rotation(-360)
                .setDuration(5000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView.animate()
                                .scaleX(-3)
                                .scaleY(-3);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        imageView.animate()
                                .rotation(-360);
                    }
                });
    }


}
