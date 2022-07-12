package Controller;

public class User {
    public static int id;
    public static String login;
    public static int group;
    public static int accessLevel = 0;
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
}
