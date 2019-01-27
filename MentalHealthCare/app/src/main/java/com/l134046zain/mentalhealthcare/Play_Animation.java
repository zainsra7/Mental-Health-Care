package com.l134046zain.mentalhealthcare;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Play_Animation extends AppCompatActivity
{
    private WebView mAnimationPlayer;
    String title="";
    String id="";
    String download="";
    String description="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__animation);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            id=b.getString("ID","");
            title=b.getString("Title","");
            download=b.getString("Download","");
            description=b.getString("Description","");
        }

        Log.d("HI", "onCreate: ID = "+id);
        setTitle("MHC | Animation");


        mAnimationPlayer=(WebView) findViewById(R.id.webView3);
        TextView _title=(TextView) findViewById(R.id.aTitle);
        TextView _description=(TextView) findViewById(R.id.ades);


        _title.setText(title);
        _description.setText(description);

        mAnimationPlayer.loadUrl(id);



    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the right
        overridePendingTransition(R.anim.hold, R.anim.towards_top);
        super.onPause();
    }

}
