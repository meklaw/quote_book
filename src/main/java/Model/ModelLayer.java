package Model;

import Controller.User;

public interface ModelLayer{
    void registerPerson(String login, String password, int group);
    int authorizePerson(String login, String password);
    void updateUser(int personID);


}
