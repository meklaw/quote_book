package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MakeQuote {
    @FXML
    private TextField quot;
    @FXML
    private TextField subject;
    @FXML
    private TextField teacher;
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField year;


    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void write() {
        if (ModelLayer.USER.getAccessLevel() == 0)
            return;

        String q = quot.getText();
        String t = teacher.getText();
        String s = subject.getText();
        String date = day.getText()+"/"+month.getText()+"/"+year.getText();
        ModelLayer model = Main.getModel();
        try {
            model.createQuote(q,t,s,date);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
