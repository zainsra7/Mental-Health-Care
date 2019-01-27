package com.l134046zain.mentalhealthcare;

/**
 * Created by Zan on 12/7/2016.
 */
public class Post
{

    private String title;
    private String description;
    private String author;
    private String date;
    private String likes;

    Post(String t,String d,String auth,String dat,String lik)
    {
        title=t;
        description=d;
        author=auth;
        date=dat;
        likes=lik;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getLikes() {
        return likes;
    }
}
