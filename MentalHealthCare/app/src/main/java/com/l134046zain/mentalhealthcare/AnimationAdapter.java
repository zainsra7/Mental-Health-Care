package com.l134046zain.mentalhealthcare;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zan on 12/6/2016.
 */
public class AnimationAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public AnimationAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Animation object)
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

       AnimationHolder jobHolder;
        if(row==null)
        {
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.frag_anim,parent,false);
            jobHolder=new AnimationHolder();
            jobHolder.tx_title= (TextView) row.findViewById(R.id.animTitle);
            jobHolder.description= (TextView) row.findViewById(R.id.animDescription);

            row.setTag(jobHolder);
        }
        else
        {
            jobHolder=(AnimationHolder) row.getTag();
        }

        Animation audio_job=(Animation) this.getItem(position);
        String Title=audio_job.getTitle();
        String pay=audio_job.getId();
        String description=audio_job.getDescription();

        jobHolder.tx_title.setText(Title);

        if(description.length()>24)
            jobHolder.description.setText(description.substring(0,24)+"....");
        else jobHolder.description.setText(description);

        return row;
    }

    static class AnimationHolder
    {
        TextView tx_title,description;

    }





}
