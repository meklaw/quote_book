package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Authorize {
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    @FXML
    public void signIn() {
        String name = loginField.getText();
        String password = passwordField.getText();
        ModelLayer model = Main.getModel();
        try {
            int id = model.authorizePerson(name, password);
            if (id == -1)
                return;
            model.updateUser(id);

            Main.changeScene("/View/menu.fxml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void changeSceneToRegistration() {
        Main.changeScene("/View/registration.fxml");
    }

    @FXML
    public void changeSceneToMenu() {
        Main.changeScene("/View/menu.fxml");
    }
}
