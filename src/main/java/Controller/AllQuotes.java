package Controller;

import Model.DBLayer;
import Model.ModelLayer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AllQuotes {

    @FXML
    private TableView<Obj> table;

    @FXML
    private TableColumn<Obj,String> quote;

    @FXML
    private TableColumn<Obj,String> teacher;

    @FXML
    private TableColumn<Obj,String> subject;

    @FXML
    private TableColumn<Obj,String> date;

    @FXML
    public void allQuotes() throws SQLException {
        ModelLayer model = new DBLayer();
        ResultSet set = model.getAllQuotes();
        while (set.next()) {
            String quote = set.getString("цитата");
            String teacher = set.getString("преподаватель");
            String subject = set.getString("предмет");
            Date date = set.getDate("дата");
            table.getItems().addAll(new Obj(quote, teacher, subject, date));
        }
        quote.setCellValueFactory(new PropertyValueFactory<>("Quote"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("Teacher"));
        subject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }


    @FXML
    public void menu() {
        Main.changeScene("/View/menu.fxml");
    }
}
