package hi.verkefni.vidmot;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MenuController {

    @FXML
    VBox namesRoot;
    

    @FXML
    private void switchToGame() throws IOException {
        App.setRoot("eventManager-view");
    }

    @FXML
    private void addLeikmadur(){
        if(namesRoot.getChildren().size() >= 9){
            return;
        }
        TextField leikmadur = new TextField("leikmadur: "+namesRoot.getChildren().size());
        namesRoot.getChildren().add(leikmadur);
    }

    @FXML
    private void removeLeikmadur(){
        if (namesRoot.getChildren().size()<=1) {
            return;
        }
        namesRoot.getChildren().remove(namesRoot.getChildren().size()-1);
    }
}
