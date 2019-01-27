package com.l134046zain.mentalhealthcare;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zan on 12/7/2016.
 */
public class PostAdapter extends ArrayAdapter
{
    List list=new ArrayList<>();
    public PostAdapter(Context context, int resource)
    {
        super(context, resource);
    }


    public void add(Post object)
    {

        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;

        PostHolder jobHolder;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.forum_2ndlistview,parent,false);
            jobHolder=new PostHolder();
            jobHolder.tx_title= (TextView) row.findViewById(R.id.postTitle);
            jobHolder.likes=(TextView) row.findViewById(R.id.numberlikes_post);
            jobHolder.detail=(TextView) row.findViewById(R.id.postDetail);

            row.setTag(jobHolder);
        }
        else
        {
            jobHolder=(PostHolder) row.getTag();
        }

        Post audio_job=(Post) this.getItem(position);
        String Title=audio_job.getTitle();

        String author=audio_job.getAuthor();
        String date=audio_job.getDate();
        String likes=audio_job.getLikes();


if(Title.length()>30)
    jobHolder.tx_title.setText(Title.substring(0,30)+"...");
    else
        jobHolder.tx_title.setText(Title);
        jobHolder.likes.setText(likes);
        jobHolder.detail.setText("By "+author+", "+date);

        return row;
    }

    static class PostHolder
    {
        TextView tx_title,detail,likes;
    }









}
