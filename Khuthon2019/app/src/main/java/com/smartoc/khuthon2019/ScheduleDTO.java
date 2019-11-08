package com.smartoc.khuthon2019;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {

    private String time;
    private String place;
    private String note;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
