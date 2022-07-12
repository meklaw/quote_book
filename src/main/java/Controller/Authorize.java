package Controller;

import Model.DBLayer;
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
        try (ModelLayer model = new DBLayer()) {
            int id = model.authorizePerson(name, password);
            if (id == -1)
                return;
            model.updateUser(id);
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void signUp() {
        Main.changeScene("/View/registration.fxml");
    }

    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }
}
