package com.l134046zain.mentalhealthcare;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Post_Detail extends Activity {

    private String title="";
    private String description="";
    private String author="";
    private String date="";
    private String likes="";

    EditText _title,_description;
    TextView _likes,_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__detail);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);
        _title=(EditText) findViewById(R.id.editText4);
        _description=(EditText) findViewById(R.id.editText7);
        _likes=(TextView) findViewById(R.id.textView12);
        _detail=(TextView) findViewById(R.id.textView11);

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            title=b.getString("Title","");
            description=b.getString("Description","");
            author=b.getString("Author","");
            likes=b.getString("Likes","");
            date=b.getString("Date","");
        }


        _title.setText(title);
        _description.setText(description);
        _likes.setText(likes);
        _detail.setText("By "+author+", "+date);
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
