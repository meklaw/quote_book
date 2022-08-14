package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MakeQuote {
    @FXML
    private TextField quotField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField teacherField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField monthField;
    @FXML
    private TextField yearField;


    @FXML
    public void changeSceneToMenu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void makeQuote() {
        if (ModelLayer.USER_ACCOUNT.getAccessLevel() == 0)
            return;

        String quot = quotField.getText();
        String teacher = teacherField.getText();
        String subject = subjectField.getText();
        String date = dayField.getText()+"/"+ monthField.getText()+"/"+ yearField.getText();
        ModelLayer model = Main.getModel();
        try {
            model.createQuote(quot,teacher,subject,date);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
