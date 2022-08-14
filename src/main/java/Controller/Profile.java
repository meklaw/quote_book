package Controller;

import Model.ModelLayer;
import Model.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile {
    @FXML
    private TableView<UserAccount> table;

    @FXML
    private TableColumn<UserAccount,String> loginColumn;

    @FXML
    private TableColumn<UserAccount,String> groupColumn;

    @FXML
    private TableColumn<UserAccount,String> accessColumn;
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
        model.changeUserGroup(ModelLayer.USER_ACCOUNT.getId(), g);
        model.changeUserLogin(ModelLayer.USER_ACCOUNT.getId(), log);
        model.changeUserPassword(ModelLayer.USER_ACCOUNT.getId(), pas);
        Main.changeScene("/View/menu.fxml");
    }
    @FXML
    public void showUserData() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getMyData(ModelLayer.USER_ACCOUNT.getId());
        table.getItems().clear();
        if (set.next()) {
            String userLogin = set.getString("логин");
            int userGroup = set.getInt("номер_группы");
            int userAccessLevel = set.getInt("уровень_доступа");
            table.getItems().addAll(new UserAccount(ModelLayer.USER_ACCOUNT.getId(), userLogin,userGroup,userAccessLevel));
        }
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        accessColumn.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));
    }


}
