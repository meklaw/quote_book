package Controller;

import java.sql.Date;

public class Quote {
    private String quote;
    private String teacher;
    private String subject;
    private int id;
    private Date date;

    public Quote(String quote, String teacher, String subject, Date date, int id) {
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.date = date;
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return date;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
