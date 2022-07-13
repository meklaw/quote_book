package Controller;

import Model.DBLayer;
import Model.ModelLayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private final static ModelLayer model = new DBLayer();
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        stage.setTitle("Цитаты");
        Parent panel = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/View/authorize.fxml")));
        stage.setScene(new Scene(panel));
        stage.show();
    }

    public static void changeScene(String fxml)  {
        try {
            Parent panel = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
            stage.setScene(new Scene(panel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ModelLayer getModel() {
        return model;
    }
}
