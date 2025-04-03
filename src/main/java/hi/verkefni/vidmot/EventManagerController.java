package hi.verkefni.vidmot;

import java.io.IOException;
import javafx.fxml.FXML;
import hi.verkefni.vinnsla.EventModel;
import javafx.scene.layout.StackPane;
import hi.verkefni.vidmot.EventView;

public class EventManagerController {
    private EventView midja;
    private EventManagerController events;
    @FXML
    private StackPane mainView;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void dummy() {
        System.out.println("dum");
    }

    @FXML
    private void exit() {
        System.exit(1);
    }

    @FXML
    private void nytt() {
        midja = new EventView();
        mainView.getChildren().add(midja);
    }

    @FXML
    private void save() {

    }
}