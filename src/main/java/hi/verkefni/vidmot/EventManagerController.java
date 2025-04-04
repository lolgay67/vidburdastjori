package hi.verkefni.vidmot;

import java.io.IOException;

import javafx.fxml.FXML;
import hi.verkefni.vinnsla.EventModel;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import hi.verkefni.vidmot.EventView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.Calendar;
import java.util.Calendar.Builder;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EventManagerController {
    private EventView midja;
    private EventManagerController events;
    @FXML
    private HBox mainView;
    @FXML
    private HBox calendarNode;

    private VBox mondayBox;
    private VBox tuesdayBox;
    private VBox wednesdayBox;
    private VBox thursdayBox;
    private VBox fridayBox;
    private VBox saturdayBox;
    private VBox sundayBox;
    private VBox[] days = new VBox[7];

    private Calendar today;
    private Calendar firstOfMonth;
    private int offset = 0;
    private int[] monthArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] dayStrings = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

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

    @FXML
    public void initialize(){
        today = Calendar.getInstance();
        firstOfMonth = Calendar.getInstance();
        firstOfMonth.set(firstOfMonth.DATE, 1);
        System.out.println(today.getTime());
        System.out.println(firstOfMonth.getTime());
        mondayBox = new VBox();
        tuesdayBox = new VBox();
        wednesdayBox = new VBox();
        thursdayBox = new VBox();
        fridayBox = new VBox();
        saturdayBox = new VBox();
        sundayBox = new VBox();
        days[0] = sundayBox;
        days[1] = mondayBox;
        days[2] = tuesdayBox;
        days[3] = wednesdayBox;
        days[4] = thursdayBox;
        days[5] = fridayBox;
        days[6] = saturdayBox;
    }

    private void generateMonth(){
        calendarNode.getChildren().clear();
        for (VBox vBox : days) {
            vBox.getChildren().clear();
        }
        int firstDay = firstOfMonth.get(firstOfMonth.DAY_OF_WEEK);
        for (int i = 0; i < dayStrings.length; i++) {
            days[i].getChildren().add(new Label(dayStrings[i]));
        }
        if(firstDay != 7){
            for (int i = (firstDay-1); i != -1; i--) {
                days[i].getChildren().add(new Rectangle(20, 20, Paint.valueOf("ffffff")));
        }}
        for (int i = 0; i < daysInMonth(firstOfMonth.get(firstOfMonth.MONTH)); i++) {
            days[firstDay%7].getChildren().add(new Button(""+(i+1)));
            firstDay += 1;
        }
        
        calendarNode.getChildren().addAll(days);
        
        
    }

    private int daysInMonth(int month){
        
        if (today.get(today.YEAR)%4==0){
            if(month==1){
                return 29;
            }
        }
        return monthArray[month];
    }

    @FXML
    public void increaseOffset(){
        today.roll(today.MONTH, 1);
        firstOfMonth.roll(firstOfMonth.MONTH, 1);
        generateMonth();
    }
    @FXML
    public void decreaseOffset(){
        today.roll(today.MONTH, -1);
        firstOfMonth.roll(firstOfMonth.MONTH, -1);
        generateMonth();
    }
}