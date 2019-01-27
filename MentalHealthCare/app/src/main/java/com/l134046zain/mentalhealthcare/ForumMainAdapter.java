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
 * Created by Zan on 12/6/2016.
 */
public class ForumMainAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public ForumMainAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Category object)
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

        CategoryHolder jobHolder;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.forum_mainlistview,parent,false);
            jobHolder=new CategoryHolder();
            jobHolder.tx_title= (TextView) row.findViewById(R.id.textView5);
            jobHolder.description= (TextView) row.findViewById(R.id.textView6);
            jobHolder.image=(ImageView) row.findViewById(R.id.imageView4);

            row.setTag(jobHolder);
        }
        else
        {
            jobHolder=(CategoryHolder) row.getTag();
        }

        Category audio_job=(Category) this.getItem(position);
        String Title=audio_job.getTitle();
        String description=audio_job.getDescription();

        jobHolder.tx_title.setText(Title);

        jobHolder.description.setText(description);

        if(Title.equals("Depression"))
        {
        jobHolder.image.setImageResource(R.drawable.depression);
        }
        else if(Title.equals("Anxiety"))
        {
            jobHolder.image.setImageResource(R.drawable.anxiety);
        }
        else if(Title.equals("Bipolar Disorder"))
        {
            jobHolder.image.setImageResource(R.drawable.bipolar);
        }
        else if(Title.equals("General Discussion"))
        {
            jobHolder.image.setImageResource(R.drawable.discuss);
        }

        return row;
    }

    static class CategoryHolder
    {
        TextView tx_title,description;
        ImageView image;

    }



}
