package com.l134046zain.mentalhealthcare;

/**
 * Created by Zan on 12/7/2016.
 */
public class Comment {


    private String description;
    private String author;
    private String date;
    private String likes;

    Comment(String d,String auth,String dat,String lik)
    {

        description=d;
        author=auth;
        date=dat;
        likes=lik;
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
