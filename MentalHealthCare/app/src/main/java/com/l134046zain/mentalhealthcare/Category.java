package com.l134046zain.mentalhealthcare;

/**
 * Created by Zan on 12/6/2016.
 */
public class Category {

    private String title;
    private String  description;

Category(String t,String d)
{
    this.title=t;
    this.description=d;
}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }
}
