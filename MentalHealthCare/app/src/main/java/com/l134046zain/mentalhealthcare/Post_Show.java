package com.l134046zain.mentalhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Post_Show extends AppCompatActivity {

    EditText detail;
    EditText comment;

    ListView listView;
    CommentAdapter commentAdapter;

    private String title="";
    private String description="";
    private String author="";
    private String date="";
    private String likes="";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__show);


        commentAdapter=new CommentAdapter(Post_Show.this,R.layout.forum_3rdlistview);
        listView=(ListView) findViewById(R.id.listView5);

        detail=(EditText) findViewById(R.id.editText3);
        comment=(EditText) findViewById(R.id.editText5);

        setTitle("MHC | Post");

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            title=b.getString("Title","");
            description=b.getString("Description","");
            author=b.getString("Author","");
            likes=b.getString("Likes","");
            date=b.getString("Date","");
        }


        Comment c1=new Comment("The Thing is that i can't do this anymore","w33","Aug 20,2013","20");
        Comment c2=new Comment("Thing is that i CAN DO THAT ^","SuFail","Aug 21,2013","322");

        commentAdapter.add(c1);
        commentAdapter.add(c2);

        listView.setAdapter(commentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent=new Intent(Post_Show.this,Comment_Detail.class);
                Comment temp = (Comment) commentAdapter.getItem(i);
                intent.putExtra("Description",temp.getDescription());
                intent.putExtra("Author",temp.getAuthor());
                intent.putExtra("Likes",temp.getLikes());
                intent.putExtra("Date",temp.getDate());
                startActivity(intent);
            }
        });



detail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {

        Intent intent=new Intent(Post_Show.this,Post_Detail.class);
        intent.putExtra("Title",title);
        intent.putExtra("Description",description);
        intent.putExtra("Author",author);
        intent.putExtra("Likes",likes);
        intent.putExtra("Date",date);
        startActivity(intent);

    }
});

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(Post_Show.this,Write_Comment.class);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the right
        overridePendingTransition(R.anim.hold, R.anim.pull_out_to_right);
        super.onPause();
    }

}
