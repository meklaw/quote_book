package Controller;

import java.sql.Date;

public class Quote {
    private String quote;
    private String teacher;
    private String subject;
    private Date date;

    public Quote(String quote, String teacher, String subject, Date date) {
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.date = date;
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
}
