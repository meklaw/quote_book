package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Registration {
    @FXML
    private TextField loginField;
    @FXML
    private TextField groupField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void registerAccount() {
        String login = loginField.getText();
        String group = groupField.getText();
        String password = passwordField.getText();
        ModelLayer model = Main.getModel();
        try {
            model.registerPerson(login, password, Integer.parseInt(group));
            changeSceneToAuthorize();
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void changeSceneToAuthorize() {
        Main.changeScene("/View/authorize.fxml");
    }

    @FXML
    public void changeSceneToMenu() {
        Main.changeScene("/View/menu.fxml");
    }

}
