package org.techtown.bada;

/**
 * Created by LG on 2018-01-20.
 */

public class Diary {
    String year;
    String month;
    String day;
    String id;
    String text;

    public Diary(){

    }

    public Diary(String id, String year, String month, String day, String text){
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.text = text;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
