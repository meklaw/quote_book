package Controller;

import Model.DBLayer;
import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Profile {
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
        ModelLayer model = new DBLayer();
        model.changeUserGroup(User.id, g);
        model.changeUserLogin(User.id, log);
        model.changeUserPassword(User.id, pas);
        Main.changeScene("/View/menu.fxml");
    }

}
