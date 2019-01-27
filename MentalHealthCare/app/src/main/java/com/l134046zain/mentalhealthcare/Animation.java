package com.l134046zain.mentalhealthcare;

/**
 * Created by Zan on 12/6/2016.
 */
public class Animation {

    private String title;
    private String id; //link
    private String description;
    private String download_link;

    public Animation(String t, String ID,String des,String down)
    {
        this. title=t;
        this. id=ID;
        this. description=des;
        this.download_link=down;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
