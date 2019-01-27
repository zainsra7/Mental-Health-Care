package com.l134046zain.mentalhealthcare;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class Comment_Detail extends Activity {

    private String description="";
    private String author="";
    private String date="";
    private String likes="";
    EditText _description;
    TextView _likes,_detail;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment__detail);
        overridePendingTransition(R.anim.from_bottom,R.anim.hold);
        _description=(EditText) findViewById(R.id.commentdetail_description);
        _likes=(TextView) findViewById(R.id.commentdetail_numLikes);
        _detail=(TextView) findViewById(R.id.commentdetail_authordate);

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            description=b.getString("Description","");
            author=b.getString("Author","");
            likes=b.getString("Likes","");
            date=b.getString("Date","");
        }


_description.setText(description);
        _likes.setText(likes);
        _detail.setText("By "+author+", "+date);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
materialDesignFAM.close(true);
                Toast.makeText(getApplicationContext(),"First Item Clicked ! ",Toast.LENGTH_SHORT).show();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                materialDesignFAM.close(true);
                Toast.makeText(getApplicationContext(),"2nd Item Clicked ! ",Toast.LENGTH_SHORT).show();
            }


        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                materialDesignFAM.close(true);
                Toast.makeText(getApplicationContext(),"3rd Item Clicked ! ",Toast.LENGTH_SHORT).show();


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
