package hi.verkefni.vidmot;

import java.io.IOException;

import javafx.fxml.FXML;

public class licenseController {

    /**
     * Switches to the main application.
     * @throws IOException Incase of javafx errors.
     */
    @FXML
    public void switchToMain() throws IOException {
        App.setRoot("eventManager");
    }
}
