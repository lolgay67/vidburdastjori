package hi.verkefni.vidmot;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import hi.verkefni.vinnsla.StorageManager;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.control.*;

public class EventManagerController {

    @FXML
    private HBox mainView;
    @FXML
    private HBox calendarNode;
    @FXML
    private VBox eventdialog;

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
    private int[] monthArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] dayStrings = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private String[] flokkar = new String[]{"Skemmtun", "Vinna", "Fundur"};
    private StorageManager storageManager;

    @FXML public void switchToLicense() throws IOException{
    App.setRoot("license");
    }

    @FXML
    private void dummy() {
        System.out.println("dum");
    }

    @FXML
    private void exit() {
        System.exit(0);;
    }

    @FXML
    private void nytt() {
        generateEvent();
    }

    @FXML
    private void save(Object nameString, int year, int month, int day, String description) {
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(nameString);
        objects.add(year);
        objects.add(month);
        objects.add(day);
        objects.add(description);
        storageManager.store(objects);

    }

    @FXML
    public void initialize(){
        today = Calendar.getInstance();
        firstOfMonth = Calendar.getInstance();
        firstOfMonth.set(firstOfMonth.DATE, 1);
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
    @FXML
    public void generateEvent(){
        eventdialog.getChildren().clear();
        TextField text = new TextField("Nafn viðburðar.");
        eventdialog.getChildren().add(text);
        DatePicker datePicker = new DatePicker(today.getTime().
            toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        eventdialog.getChildren().add(datePicker);
        TextField description = new TextField("Lýsing á viðburði");
        eventdialog.getChildren().add(description);


        Button saveButton = new Button("Vista.");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e){
                LocalDate tempDate = datePicker.getValue();
                save(((Object)text.getText()), tempDate.getYear(), tempDate.getMonth().getValue(), tempDate.getDayOfMonth(), description.getText());
            }
        });
        Button deleteButton = new Button("Eyða.");
        HBox buttonContainer = new HBox();
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Flokkar");
        MenuItem skemmtun = new MenuItem("Skemmtun");
        skemmtun.setOnAction(e -> Skemmtun());
        MenuItem vinna = new MenuItem("Vinna");
        vinna.setOnAction(e -> Vinna());
        MenuItem fundur = new MenuItem("Fundur");
        fundur.setOnAction(e -> Fundur());
        menuBar.getMenus().add(menu);
        menu.getItems().add(skemmtun);
        menu.getItems().add(vinna);
        menu.getItems().add(fundur);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().add(saveButton);
        buttonContainer.getChildren().add(deleteButton);
        buttonContainer.getChildren().add(menuBar);
        eventdialog.getChildren().add(buttonContainer);

        
    }

    public void Skemmtun(){
        
    }
    public void Vinna(){
        
    }
    public void Fundur(){
        
    }
}