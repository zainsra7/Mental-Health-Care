package com.l134046zain.mentalhealthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Forum_main extends AppCompatActivity {

    ForumMainAdapter forumMainAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);
        overridePendingTransition(R.anim.pull_in_from_left,R.anim.hold);
    forumMainAdapter=new ForumMainAdapter(this,R.layout.forum_mainlistview);
    listView=(ListView) findViewById(R.id.forum_main_listview);

        setTitle("MHC | Forum");

        Category c1=new Category("General Discussion","Discuss any general topic");
        Category c2=new Category("Depression","Depression related topics");
        Category c3=new Category("Bipolar Disorder","Bipolar Disorder related topics");
        Category c4=new Category("Anxiety","Anxiety related topics");

        forumMainAdapter.add(c1);
        forumMainAdapter.add(c2);
        forumMainAdapter.add(c3);
        forumMainAdapter.add(c4);

        listView.setAdapter(forumMainAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(Forum_main.this,Categories_Show.class);
                Category temp = (Category) forumMainAdapter.getItem(i);
                intent.putExtra("Category",temp.getTitle());
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
