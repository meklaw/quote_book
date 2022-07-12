package Controller;

import Model.DBLayer;
import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Registration {
    @FXML
    private TextField login;
    @FXML
    private TextField group;
    @FXML
    private PasswordField password;

    @FXML
    public void registration() {
        String log = login.getText();
        String gr = group.getText();
        String pas = password.getText();
        ModelLayer model = new DBLayer();
        try {
            model.registerPerson(log, pas, Integer.parseInt(gr));
            authorize();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void authorize() {
        Main.changeScene("/View/authorize.fxml");
    }

    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }

}
