package Controller;

import javafx.fxml.FXML;

public class Menu {
    @FXML
    public void authorize() {
        if (User.accessLevel == 0)
            Main.changeScene("/View/authorize.fxml");
    }

    @FXML
    public void profile() {
        if (User.accessLevel == 0)
            return;
        Main.changeScene("/View/profile.fxml");
    }

    @FXML
    public void allQuotes() {
        Main.changeScene("/View/allQuotes.fxml");
    }

    @FXML
    public void myQuotes() {
        if (User.accessLevel == 0)
            return;
        Main.changeScene("/View/myQuotes.fxml");
    }

    @FXML
    public void makeQuote() {
        if (User.accessLevel == 0)
            return;
        Main.changeScene("/View/makeQuote.fxml");
    }

}
