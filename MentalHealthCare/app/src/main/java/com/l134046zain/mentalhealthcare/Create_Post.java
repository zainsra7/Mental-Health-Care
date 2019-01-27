package com.l134046zain.mentalhealthcare;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create_Post extends Activity {

    Button cancel,_submit;
    Button submit;
    EditText _title,_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);

        cancel=(Button) findViewById(R.id.createpost_CancelButn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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
