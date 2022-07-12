package Model;

import Controller.User;

public interface ModelLayer{
    void registerPerson(String login, String password, int group);
    int authorizePerson(String login, String password);
    void updateUser(int personID);

    void createQuote(String quote, String teach, String subj, String date);


}
