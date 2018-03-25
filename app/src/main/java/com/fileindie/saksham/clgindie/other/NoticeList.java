package com.fileindie.saksham.clgindie.other;

/**
 * Created by SAKSHIM on 22/03/2018.
 */

public class NoticeList {
    private String title, date;

    public NoticeList(){}

    public NoticeList(String title, String date){
        this.date=date;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String name) {
        this.date = name;
    }
}
