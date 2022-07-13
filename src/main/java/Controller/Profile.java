package Controller;

import Model.ModelLayer;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile {
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User,String> loginColumn;

    @FXML
    private TableColumn<User,String> groupColumn;

    @FXML
    private TableColumn<User,String> accessColumn;
    @FXML
    private TextField login;
    @FXML
    private TextField group;
    @FXML
    private TextField password;

    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void change() {
        String log = login.getText();
        int g = Integer.parseInt(group.getText());
        String pas = password.getText();
        ModelLayer model = Main.getModel();
        model.changeUserGroup(ModelLayer.USER.getId(), g);
        model.changeUserLogin(ModelLayer.USER.getId(), log);
        model.changeUserPassword(ModelLayer.USER.getId(), pas);
        Main.changeScene("/View/menu.fxml");
    }
    @FXML
    public void showMyData() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getMyData(ModelLayer.USER.getId());
        table.getItems().clear();
        if (set.next()) {
            String l = set.getString("логин");
            int g = set.getInt("номер_группы");
            int a = set.getInt("уровень_доступа");
            table.getItems().addAll(new User(ModelLayer.USER.getId(), l,g,a));
        }
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));
    }


}
