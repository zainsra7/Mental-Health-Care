package com.l134046zain.mentalhealthcare;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Write_Comment extends Activity {

    Button cancel;
    Button submit;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write__comment);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);
        cancel=(Button) findViewById(R.id.button3);
        submit=(Button) findViewById(R.id.button2);
        comment=(EditText) findViewById(R.id.editText6);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                There are a couple of ways to achieve what you want, depending on the circumstances.
//
//                        The most common scenario (which is what yours sounds like) is when a child Activity is used to get user input - such as choosing a contact from a list or entering data in a dialog box. In this case you should use startActivityForResult to launch your child Activity.
//
//                This provides a pipeline for sending data back to the main Activity using setResult. The setResult method takes an int result value and an Intent that is passed back to the calling Activity.
//
//                Intent resultIntent = new Intent();
//// TODO Add extras or a data URI to this intent as appropriate.
//                setResult(Activity.RESULT_OK, resultIntent);
//                finish();
//                To access the returned data in the calling Activity override onActivityResult. The requestCode corresponds to the integer passed in in the startActivityForResult call, while the resultCode and data Intent are returned from the child Activity.
//
//                @Override
//                public void onActivityResult(int requestCode, int resultCode, Intent data) {
//                    super.onActivityResult(requestCode, resultCode, data);
//                    switch(requestCode) {
//                        case (MY_CHILD_ACTIVITY) : {
//                            if (resultCode == Activity.RESULT_OK) {
//                                // TODO Extract the data returned from the child Activity.
//                            }
//                            break;
//                        }
//                    }
//                }


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
