package hi.verkefni.vidmot;

import java.io.IOException;

import javafx.fxml.FXML;

public class licenseController {

    @FXML
    public void switchToMain() throws IOException {
        App.setRoot("eventManager");
    }
}
