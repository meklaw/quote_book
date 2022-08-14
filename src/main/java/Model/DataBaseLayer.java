package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseLayer extends ModelLayer {
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
        String selectSql = "SELECT * FROM пользователь WHERE логин = ? AND хэш_пароля = ?";
        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
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
                USER_ACCOUNT.setId(personID);
                USER_ACCOUNT.setLogin(rs.getString("логин"));
                USER_ACCOUNT.setGroup(rs.getInt("номер_группы"));
                USER_ACCOUNT.setAccessLevel(rs.getInt("уровень_доступа"));
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
            updatePreparedStatement.setInt(1, USER_ACCOUNT.getId());
            updatePreparedStatement.setString(2, quote);
            updatePreparedStatement.setString(3, teach);
            updatePreparedStatement.setString(4, subj);
            updatePreparedStatement.setString(5, date);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteQuote(int id) {
        String sql = "DELETE FROM цитата WHERE id = ?";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(1, id);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getGroup(int id) {
        String selectSql = "SELECT номер_группы FROM пользователь WHERE id = ?";

        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            selectPreparedStatement.setInt(1, id);
            ResultSet rs = selectPreparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("номер_группы");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public ResultSet getAllQuotes() {
        String selectSql = "SELECT id, цитата, преподаватель, предмет, дата FROM цитата";
        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            return selectPreparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getMyQuotes() {
        String selectSql = "SELECT id, цитата, преподаватель, предмет, дата FROM цитата WHERE автор = ?";
        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            selectPreparedStatement.setInt(1, USER_ACCOUNT.getId());
            return selectPreparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getAuthor(int id) {
        String selectSql = "SELECT автор FROM цитата WHERE id = ?";

        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            selectPreparedStatement.setInt(1, id);
            ResultSet rs = selectPreparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("автор");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public void changeQuote(int id, String quote) {
        String sql = "UPDATE цитата SET цитата = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, id);
            updatePreparedStatement.setString(1, quote);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeTeacher(int id, String teacher) {
        String sql = "UPDATE цитата SET преподаватель = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, id);
            updatePreparedStatement.setString(1, teacher);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeSubject(int id, String subject) {
        String sql = "UPDATE цитата SET предмет = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, id);
            updatePreparedStatement.setString(1, subject);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeDate(int id, String date) {
        String sql = "UPDATE цитата SET дата = str_to_date(?, '%d/%m/%Y')  WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, id);
            updatePreparedStatement.setString(1, date);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUserLogin(int id, String newLogin) {
        String sql = "UPDATE пользователь SET логин = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, USER_ACCOUNT.getId());
            updatePreparedStatement.setString(1, newLogin);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUserPassword(int id, String newPassword) {
        String sql = "UPDATE пользователь SET хэш_пароля = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, USER_ACCOUNT.getId());
            updatePreparedStatement.setString(1, String.valueOf(newPassword.hashCode()));
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUserGroup(int id, int newGroup) {
        String sql = "UPDATE пользователь SET номер_группы = ? WHERE id = ?;";
        try {
            PreparedStatement updatePreparedStatement = connection.prepareStatement(sql);
            updatePreparedStatement.setInt(2, USER_ACCOUNT.getId());
            updatePreparedStatement.setInt(1, newGroup);
            updatePreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet getMyData(int id) {
        String selectSql = "SELECT логин,номер_группы,уровень_доступа FROM пользователь WHERE id = ?";

        try {
            PreparedStatement selectPreparedStatement = connection.prepareStatement(selectSql);
            selectPreparedStatement.setInt(1, id);
            return selectPreparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
