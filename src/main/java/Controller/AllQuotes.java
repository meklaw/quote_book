package Controller;

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
    private TableView<Quote> table;

    @FXML
    private TableColumn<Quote,String> quote;

    @FXML
    private TableColumn<Quote,String> teacher;

    @FXML
    private TableColumn<Quote,String> subject;

    @FXML
    private TableColumn<Quote,String> date;

    @FXML
    public void allQuotes() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getAllQuotes();
        table.getItems().clear();
        while (set.next()) {
            String quote = set.getString("цитата");
            String teacher = set.getString("преподаватель");
            String subject = set.getString("предмет");
            Date date = set.getDate("дата");
            table.getItems().addAll(new Quote(quote, teacher, subject, date));
        }
        quote.setCellValueFactory(new PropertyValueFactory<>("quote"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    @FXML
    public void myQuotes() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getAllQuotes();
        table.getItems().clear();
        while (set.next()) {
            String quote = set.getString("цитата");
            String teacher = set.getString("преподаватель");
            String subject = set.getString("предмет");
            Date date = set.getDate("дата");
            table.getItems().addAll(new Quote(quote, teacher, subject, date));
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
