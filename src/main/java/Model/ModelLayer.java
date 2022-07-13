package Model;

import java.sql.ResultSet;

public abstract class ModelLayer {

    public final static User USER = new User();

    public abstract void registerPerson(String login, String password, int group);

    public abstract int authorizePerson(String login, String password);


    public abstract void updateUser(int personID);

    public abstract void createQuote(String quote, String teach, String subj, String date);

    public abstract int getGroup(int id);

    public abstract ResultSet getAllQuotes();

    public abstract ResultSet getMyQuotes();

    public abstract int getAuthor(int id);

    public abstract void changeQuote(int id, String quote);

    public abstract void changeTeacher(int id, String teacher);

    public abstract void changeSubject(int id, String subject);

    public abstract void changeDate(int id, String date);

    public abstract void changeUserLogin(int id, String newLogin);

    public abstract void changeUserPassword(int id, String newPassword);

    public abstract void changeUserGroup(int id, int newGroup);

    public abstract ResultSet getMyData(int id);


}
