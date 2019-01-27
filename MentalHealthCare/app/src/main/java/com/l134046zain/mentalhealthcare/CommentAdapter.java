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
public class CommentAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public CommentAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Comment object)
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

        CommentHolder jobHolder;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.forum_3rdlistview,parent,false);
            jobHolder=new CommentHolder();
            jobHolder.tx_description= (TextView) row.findViewById(R.id.postTitle2);
            jobHolder.likes=(TextView) row.findViewById(R.id.numberlikes_post2);
            jobHolder.detail=(TextView) row.findViewById(R.id.postDetail2);

            row.setTag(jobHolder);
        }
        else
        {
            jobHolder=(CommentHolder) row.getTag();
        }

        Comment audio_job=(Comment) this.getItem(position);
        String Title=audio_job.getDescription();
        String author=audio_job.getAuthor();
        String date=audio_job.getDate();
        String likes=audio_job.getLikes();


        if(Title.length()>30)
        jobHolder.tx_description.setText(Title.substring(0,30)+"....");
        else jobHolder.tx_description.setText(Title);
        jobHolder.likes.setText(likes);
        jobHolder.detail.setText("By "+author+", "+date);



        return row;
    }

    static class CommentHolder
    {
        TextView tx_description,detail,likes;
    }






}
