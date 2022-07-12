package Model;

import Controller.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBLayer implements ModelLayer {
    private static final Connection connection = createConnection();


    private static Connection createConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/DB.properties"));
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("name"),
                    properties.getProperty("password"));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void registerPerson(String login, String password, int group) {
        String updateSql = "INSERT INTO пользователь (логин, хэш_пароля, номер_группы) Values (?, ?, ?)";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(updateSql);
            updatePreparedStatement.setString(1, login);
            updatePreparedStatement.setString(2, String.valueOf(password.hashCode()));
            updatePreparedStatement.setInt(3, group);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int authorizePerson(String name, String password) {
        Connection con = createConnection();
        String selectSql = "SELECT * FROM пользователь WHERE логин = ? AND хэш_пароля = ?";
        try {
            PreparedStatement selectPreparedStatement = con.prepareStatement(selectSql);
            selectPreparedStatement.setString(1, name);
            selectPreparedStatement.setString(2, String.valueOf(password.hashCode()));
            ResultSet rs = selectPreparedStatement.executeQuery();
            if (rs.next())
                return rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void updateUser(int personID) {
        String selectSql = "SELECT * FROM пользователь WHERE id = ?";

        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            selectPreparedStatement.setInt(1, personID);
            ResultSet rs = selectPreparedStatement.executeQuery();
            if (rs.next()) {
                User.id = personID;
                User.login = rs.getString("логин");
                User.group = rs.getInt("номер_группы");
                User.accessLevel = rs.getInt("уровень_доступа");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createQuote(String quote, String teach, String subj, String date) {
        String sql = "INSERT INTO цитата (автор, цитата, преподаватель, предмет, дата) " +
                "VALUES (?, ?, ?, ?, str_to_date(?, '%d/%m/%Y'))";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(1, User.id);
            updatePreparedStatement.setString(2, quote);
            updatePreparedStatement.setString(3, teach);
            updatePreparedStatement.setString(4, subj);
            updatePreparedStatement.setString(4, date);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
