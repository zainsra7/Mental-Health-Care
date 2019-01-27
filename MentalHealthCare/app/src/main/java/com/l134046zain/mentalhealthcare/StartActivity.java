package com.l134046zain.mentalhealthcare;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    AnimationDrawable rocketAnimation;
    Button loginButton;
    Button signUpButton;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
      //  rocketAnimation.start();
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        View viewObj= (View) findViewById(R.id.imageView5);
      //  viewObj.setBackgroundResource(R.drawable.animatedbackground);
//        rocketAnimation=(AnimationDrawable) viewObj.getBackground();

        loginButton=(Button) findViewById(R.id.button6);
        signUpButton=(Button) findViewById(R.id.button7);

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    Intent i = new Intent(StartActivity.this, IntroActivity.class);
                    startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();



        firebaseAuth = FirebaseAuth.getInstance();
        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null)
        {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open Home activity
            startActivity(new Intent(getApplicationContext(), Home.class));
        }


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent= new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

     signUpButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent= new Intent(StartActivity.this,SignUpActivity.class);
             startActivity(intent);
         }
     });


    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.from_bottom, R.anim.hold);
        super.onPause();
    }
}
