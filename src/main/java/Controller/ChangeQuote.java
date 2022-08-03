package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeQuote {
    @FXML
    private TextField quotField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField teacherNameField;
    @FXML
    private TextField dayQuotField;
    @FXML
    private TextField monthQuotField;
    @FXML
    private TextField yearQuotField;
    @FXML
    private TextField idQuotField;

    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void change() {
        if (ModelLayer.USER.getAccessLevel() == 0)
            return;
        int idQuot = Integer.parseInt(idQuotField.getText());
        String quot = quotField.getText();
        String teacherName = teacherNameField.getText();
        String subject = subjectField.getText();
        String date = dayQuotField.getText() + "/" + monthQuotField.getText() + "/" + yearQuotField.getText();
        ModelLayer model = Main.getModel();
        int authorQuot = model.getAuthor(idQuot);
        int groupNumber = model.getGroup(authorQuot);
        if (!(
                ModelLayer.USER.getAccessLevel() == 3 ||
                        (ModelLayer.USER.getAccessLevel() == 2 && groupNumber == ModelLayer.USER.getGroup()) ||
                        authorQuot == ModelLayer.USER.getId()
        ))
            return;
        model.changeQuote(idQuot, quot);
        model.changeTeacher(idQuot, teacherName);
        model.changeSubject(idQuot, subject);
        model.changeDate(idQuot, date);
    }
    @FXML
    public void delete() {
        if (ModelLayer.USER.getAccessLevel() == 0)
            return;
        int idQuot = Integer.parseInt(idQuotField.getText());
        ModelLayer model = Main.getModel();
        int authorQuot = model.getAuthor(idQuot);
        int groupNumber = model.getGroup(authorQuot);
        if (!(
                ModelLayer.USER.getAccessLevel() == 3 ||
                        (ModelLayer.USER.getAccessLevel() == 2 && groupNumber == ModelLayer.USER.getGroup()) ||
                        authorQuot == ModelLayer.USER.getId()
        ))
            return;
        model.deleteQuote(idQuot);
    }
}
