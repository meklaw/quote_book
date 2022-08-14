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
    private TableColumn<Quote,String> idColumn;

    @FXML
    public void showAllQuotes() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getAllQuotes();
        updateTabel(set);
    }
    @FXML
    public void showUserQuotes() throws SQLException {
        ModelLayer model = Main.getModel();
        ResultSet set = model.getMyQuotes();
        updateTabel(set);
    }


    @FXML
    public void changeSceneToMenu() {
        Main.changeScene("/View/menu.fxml");
    }

    private void updateTabel(ResultSet set) throws SQLException {
        table.getItems().clear();
        while (set.next()) {
            String quote = set.getString("цитата");
            String teacher = set.getString("преподаватель");
            String subject = set.getString("предмет");
            Date date = set.getDate("дата");
            int idQo = set.getInt("id");
            table.getItems().addAll(new Quote(quote, teacher, subject, date, idQo));
        }
        quote.setCellValueFactory(new PropertyValueFactory<>("Quote"));
        teacher.setCellValueFactory(new PropertyValueFactory<>("Teacher"));
        subject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    }
}
