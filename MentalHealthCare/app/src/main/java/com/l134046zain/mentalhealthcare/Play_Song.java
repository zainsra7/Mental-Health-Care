package com.l134046zain.mentalhealthcare;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Play_Song extends AppCompatActivity {

    private WebView mSoundCloudPlayer;
    String title="";
    String id="";
    String download="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__song);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            id=b.getString("ID","");
            title=b.getString("Title","SongTitle");
            download=b.getString("Download","");
        }


        mSoundCloudPlayer= (WebView) findViewById(R.id.webview);
        String html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" /></head> <body style=\"background:black;margin:0 0 0 0; padding:0 0 0 0;\"> <iframe id=\"sc-widget " +
                "\" width=\"100%\" height=\"100%\"" + // Set Appropriate Width and Height that you want for SoundCloud Player
                " src=\"" + id   // Set Embedded url
                + "\" frameborder=\"no\" scrolling=\"no\"></iframe>" +
                "<script src=\"https://w.soundcloud.com/player/api.js\" type=\"text/javascript\"></script> </body> </html> ";

        if (mSoundCloudPlayer != null)
        {
            mSoundCloudPlayer.setVisibility(View.VISIBLE);
            mSoundCloudPlayer.getSettings().setJavaScriptEnabled(true);
            mSoundCloudPlayer.getSettings().setLoadWithOverviewMode(true);
            mSoundCloudPlayer.getSettings().setUseWideViewPort(true);
            mSoundCloudPlayer.loadDataWithBaseURL("",html,"text/html", "UTF-8", "");
        }


    }
    @Override
    protected void onStop()
    {
        super.onStop();
        mSoundCloudPlayer.destroy();
    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the right
        overridePendingTransition(R.anim.hold, R.anim.towards_top);
        super.onPause();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//       switch (item.getItemId())
//       {
//
//           case R.id.download:
//               Uri uri = Uri.parse(download);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//               return true;
//
//       }
//
//    return false;
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater inflator = getMenuInflater();
//        inflator.inflate(R.menu.action, menu);
//        return true;
//    }
}
