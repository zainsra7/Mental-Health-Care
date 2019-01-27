package com.l134046zain.mentalhealthcare;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.*;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    ImageView splashimage;
    android.view.animation.Animation bounceAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        splashimage=(ImageView) findViewById(R.id.splashImage);
       bounceAnimation=AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);

        splashimage.setVisibility(View.VISIBLE);
        splashimage.startAnimation(bounceAnimation);

        bounceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                //Start the rest of Animation
                splashimage.setImageResource(R.drawable.logotransition);
                TransitionDrawable drawable = (TransitionDrawable) splashimage.getDrawable();
                drawable.startTransition(5000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, StartActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);



                // close this activity
                finish();
            }
        }, 5000);
    }



    }

