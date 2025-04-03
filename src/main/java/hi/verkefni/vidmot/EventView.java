package hi.verkefni.vidmot;
import javafx.fxml.FXML;

import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import is.vidmot.FXML_Lestur;

import hi.verkefni.vinnsla.EventModel;
public class EventView extends StackPane {
    FXML_Lestur lesari = new FXML_Lestur();
    public EventView(){
        lesari.lesa(this,"EventView.fxml");
    }
    @FXML
    private TextField nafnvidburd;
    @FXML
    private DatePicker dagur;
    @FXML
    private TextField time;
    @FXML
    private TextField label;
    @FXML
    public void Initialize(){

    }


}
