package fr.utbm.entity;

/**
 * Created by Exige on 06/10/2017.
 */

public class Date {

    private long id;
    private java.util.Date date;

    public Date(java.util.Date date) {
        this.date = date;
    }

    public Date(long id, java.util.Date date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
