package Model;

public class UserAccount {
    private int id;
    private String login;
    private int group;
    private int accessLevel = 0;
    /*  Уровни доступа
     *   0 - гость
     *   1 - студент
     *   2 - староста группы
     *   3 - супер пользователь
     * */


    @Override
    public String toString() {
        return "id:" + id + "\n" +
                "login:" + login + "\n" +
                "group:" + group + "\n" +
                "accessLevel:" + accessLevel;
    }

    public UserAccount() {
    }

    public UserAccount(int id, String login, int group, int accessLevel) {
        this.id = id;
        this.login = login;
        this.group = group;
        this.accessLevel = accessLevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getGroup() {
        return group;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
