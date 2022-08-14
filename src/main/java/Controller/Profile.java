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
    private TextField userLoginField;
    @FXML
    private TextField userGroupField;
    @FXML
    private TextField userPasswordField;

    @FXML
    public void changeSceneToMenu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void changeUserData() {
        String log = userLoginField.getText();
        int g = Integer.parseInt(userGroupField.getText());
        String pas = userPasswordField.getText();
        ModelLayer model = Main.getModel();
        model.changeUserGroup(ModelLayer.USER.getId(), g);
        model.changeUserLogin(ModelLayer.USER.getId(), log);
        model.changeUserPassword(ModelLayer.USER.getId(), pas);
        Main.changeScene("/View/menu.fxml");
    }
    @FXML
    public void showUserData() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getMyData(ModelLayer.USER.getId());
        table.getItems().clear();
        if (set.next()) {
            String userLogin = set.getString("логин");
            int userGroup = set.getInt("номер_группы");
            int userAccessLevel = set.getInt("уровень_доступа");
            table.getItems().addAll(new User(ModelLayer.USER.getId(), userLogin,userGroup,userAccessLevel));
        }
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));
    }


}
