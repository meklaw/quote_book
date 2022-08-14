package Controller;

import Model.ModelLayer;
import javafx.fxml.FXML;

public class Menu {
    @FXML
    public void authorize() {
        if (ModelLayer.USER_ACCOUNT.getAccessLevel() == 0)
            Main.changeScene("/View/authorize.fxml");
    }

    @FXML
    public void profile() {
        if (ModelLayer.USER_ACCOUNT.getAccessLevel() == 0)
            return;
        Main.changeScene("/View/profile.fxml");
    }

    @FXML
    public void allQuotes() {
        Main.changeScene("/View/allQuotes.fxml");
    }

    @FXML
    public void makeQuote() {
        if (ModelLayer.USER_ACCOUNT.getAccessLevel() == 0)
            return;
        Main.changeScene("/View/makeQuote.fxml");
    }
    @FXML
    public void changeQuote() {
        if (ModelLayer.USER_ACCOUNT.getAccessLevel() == 0)
            return;
        Main.changeScene("/View/changeQuote.fxml");
    }

}
