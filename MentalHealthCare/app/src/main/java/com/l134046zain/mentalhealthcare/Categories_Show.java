package com.l134046zain.mentalhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Categories_Show extends AppCompatActivity
{

    PostAdapter postAdapter;
    EditText createpost;
    ListView listView;
    private String category="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories__show);
        overridePendingTransition(R.anim.pull_in_from_left,R.anim.hold);
        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            category=b.getString("Category","");
        }

        setTitle("MHC | "+category);

        postAdapter=new PostAdapter(this,R.layout.forum_2ndlistview);
        listView=(ListView) findViewById(R.id.listView4);
        createpost=(EditText) findViewById(R.id.createpostButn);

        Post p1=new Post("I am in Depression Oh NO !","It was a raining day , i was farming as PA ... Oh ho ... Everything was going" +
                "just fine but there was one .. one freaking Clinks that had to ruin my whole farm and game .... We lost ! son","Zain","April 20,2016","9");


postAdapter.add(p1);


        Post p2=new Post("I was weak , my soul wasn't !","It was a sunny day , i though i could win the match but the problem was that" +
                "i was a noob .. but not anymore son!  .... Not anymore ! son","Miracle","Oct 20,2016","100");
postAdapter.add(p2);


        listView.setAdapter(postAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent=new Intent(Categories_Show.this,Post_Show.class);
                Post temp = (Post) postAdapter.getItem(i);
                intent.putExtra("Title",temp.getTitle());
                intent.putExtra("Description",temp.getDescription());
                intent.putExtra("Author",temp.getAuthor());
                intent.putExtra("Likes",temp.getLikes());
                intent.putExtra("Date",temp.getDate());
                startActivity(intent);


            }
        });
        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Categories_Show.this,Create_Post.class);
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
