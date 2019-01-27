package com.l134046zain.mentalhealthcare;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("Welcome", "Mental Health Care is an undergrad project " +
                "aiming towards helping youth in fighting against depression , anxiety and bipolar disorder.", R.drawable.main, Color.parseColor("#536DFE")));

        addSlide(AppIntroFragment.newInstance("Inside View", "Mental Health Care brings every solution to your problem in a" +
                "single app, from Standard Questionnaires ,to Exercises and Forums that are suggested and used by psychiatrists in curing of diseases mentioned in previous slide.", R.drawable.drawer, Color.parseColor("#0097A7")));

        addSlide(AppIntroFragment.newInstance("Standard Questionnaire", "This app will examine your condition through a series of" +
                "standard Questionnaires approved by psychiatrists. You will be given a test at first sign up, and then afterwards every month to " +
                "record your progress", R.drawable.test, Color.parseColor("#00BCD4")));

        addSlide(AppIntroFragment.newInstance("Progress", "With each Questionnaire you solve , your progress would be recorded" +
                "You can view your progress along with details for each month.", R.drawable.progress, Color.parseColor("#0097A7")));

        addSlide(AppIntroFragment.newInstance("Exercises", "Motivational and Effective  Speeches and Animations and videos that not only boosts moral but lets you look" +
                "on life with a positive image in other words greatly reducing the impact of diseases like depression , anxiety and bipolar disorder", R.drawable.exercise, Color.parseColor("#536DFE")));

        addSlide(AppIntroFragment.newInstance("Forum", "It's better to fight alongside people than to fight alone , specially when the enemy is same for everyone (not a physical entity , but a psychological one !) " +
                "Majority of problems are solves in real life when you share and let it out ! So share your thoughts and feelings , knowing that everyone in this forum is also on the same path. Help each other in defeating that common enemy!" +
                "In this Forum you can Create Post/ Comment on a Post / Report a Comment / Share a Comment / Like,Unlike a Comment.", R.drawable.forum, Color.parseColor("#00BCD4")));

        addSlide(AppIntroFragment.newInstance("Final Message", "Believe in Yourself.", R.drawable.finalmessage, Color.parseColor("#28CB20")));


    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        finish();
    }
}
