package com.l134046zain.mentalhealthcare;

/**
 * Created by Administrator on 12/26/2016.
 */

public class UserInformation {
    String image,name,year,month,day;

    public UserInformation(String image, String name, String year, String month, String day) {
        this.image = image;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
