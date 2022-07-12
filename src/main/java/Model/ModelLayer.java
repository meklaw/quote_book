package Model;

import Controller.User;

import java.sql.ResultSet;

public interface ModelLayer{
    void registerPerson(String login, String password, int group);
    int authorizePerson(String login, String password);
    void updateUser(int personID);

    void createQuote(String quote, String teach, String subj, String date);
    int getGroup(int id);
    ResultSet getAllQuotes();
    int getAuthor(int id);
    void changeQuote(int id, String quote);
    void changeTeacher(int id, String teacher);
    void changeSubject(int id, String subject);
    void changeDate(int id, String date);


}
