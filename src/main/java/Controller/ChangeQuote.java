package Controller;

import Model.DBLayer;
import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeQuote {
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
    private TextField id;

    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }

    @FXML
    public void change() {
        if (User.accessLevel == 0)
            return;
        int index = Integer.parseInt(id.getText());
        String q = quot.getText();
        String t = teacher.getText();
        String s = subject.getText();
        String date = day.getText() + "/" + month.getText() + "/" + year.getText();
        ModelLayer model = new DBLayer();
        int author = model.getAuthor(index);
        int group = model.getGroup(author);
        if (!(User.accessLevel == 3 || author == User.id || (User.accessLevel == 2 && group == User.group)))
            return;
        model.changeQuote(index, q);
        model.changeTeacher(index, t);
        model.changeSubject(index, s);
        model.changeDate(index, date);
    }
}
