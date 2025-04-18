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
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javafx.scene.control.*;

public class EventManagerController {

    @FXML
    private HBox mainView;
    @FXML
    private HBox calendarNode;
    @FXML
    private VBox eventdialog;
    @FXML
    private HBox buttonBox;

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
    private int[] monthArray = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
            "Nov", "Dec" };
    private String[] dayStrings = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    //private String[] flokkar = new String[] { "#34bdeb", "#eb5234", "#ebe134" };
    private StorageManager storageManager = new StorageManager();
    private HashMap<Calendar, String> eventDays = new HashMap<Calendar, String>();

    /**
     * Shows the license and help screen.
     * @throws IOException javafx error handling.
     */
    @FXML
    public void switchToLicense() throws IOException {
        App.setRoot("license");
    }

    /**
     * Tries to load saved storagemanager from disk but unfortionetly this function doesnt quite work.
     */
    @FXML
    private void open() {
        storageManager.load();
        generateMonth();
    }

    /**
     * exits the program sending a no errors code.
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

    /**
     * lets fxml elements call the generateEvent function creating a new event.
     */
    @FXML
    private void nytt() {
        generateEvent();
    }

    /**
     * Saves all events to disk, unfortionetly loading them doesnt work.
     */
    @FXML
    private void vista() {
        storageManager.save();
    }

    /**
     * saves given event to the storage manager.
     * @param nameString name of the event
     * @param year year of the event
     * @param month INTEGER month of the event
     * @param day day of the month
     * @param description desctiption of the event
     * @param flokkur the type of event
     */
    private void save(Object nameString, int year, int month, int day, String description, String flokkur) {
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(nameString);
        objects.add(year);
        objects.add(month);
        objects.add(day);
        objects.add(description);
        objects.add(flokkur);
        storageManager.store(objects);
        eventDays = storageManager.getDateName();
    }

    /**
     * The fxmlloader calls this function when the file is first loaded.
     */
    @FXML
    public void initialize() {
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
        generateMonth();
    }

    /**
     * Generates a calendar dialog inside the calendarNode area of the fxml file.
     */
    private void generateMonth() {
        int year = firstOfMonth.get(firstOfMonth.YEAR);
        int month = firstOfMonth.get(firstOfMonth.MONTH);
        HashMap<Integer, String> events = new HashMap<Integer, String>();
        for (Calendar cal : eventDays.keySet()) {
            if (cal.get(cal.YEAR) == year) {
                if ((cal.get(cal.MONTH) - 1) == month) {
                    events.put(cal.get(cal.DAY_OF_MONTH), eventDays.get(cal));
                }
            }
        }
        calendarNode.getChildren().clear();
        buttonBox.getChildren().set(1, new Text(months[firstOfMonth.get(firstOfMonth.MONTH)]));
        for (VBox vBox : days) {
            vBox.getChildren().clear();
        }
        int firstDay = firstOfMonth.get(firstOfMonth.DAY_OF_WEEK) - 1;
        for (int i = 0; i < dayStrings.length; i++) {
            days[i].getChildren().add(new Label(dayStrings[i]));
        }
        if (firstDay != 7) {
            for (int i = (firstDay - 1); i != -1; i--) {
                days[i].getChildren().add(new Rectangle(25, 25, Paint.valueOf("ffffff")));
            }
        }
        for (int j = 0; j < daysInMonth(firstOfMonth.get(firstOfMonth.MONTH)); j++) {
            int i = j;
            Button tempButton = new Button("" + (i + 1));
            if (events.containsKey((i + 1))) {
                String litur = storageManager.getStored(events.get((i + 1))).get(5).toString();
                
                tempButton.setBackground(Background.fill(Paint.valueOf(litur)));

                
                tempButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        generateEvent(events.get((i + 1)));
                    }
                });
            } else {
                tempButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        generateEvent();
                    }
                });
            }
            days[firstDay % 7].getChildren().add(tempButton);
            firstDay += 1;
        }

        calendarNode.getChildren().addAll(days);

    }

    /**
     * TO check how many days in this month.
     * @param month the month to check, integer that starts at 0
     * @return the days in the month.
     */ 
    private int daysInMonth(int month) {

        if (today.get(today.YEAR) % 4 == 0) {
            if (month == 1) {
                return 29;
            }
        }
        return monthArray[month];
    }

    /**
     * Increases the month of the calendar by one.
     */
    @FXML
    public void increaseOffset() {
        today.roll(today.MONTH, 1);
        firstOfMonth.roll(firstOfMonth.MONTH, 1);
        generateMonth();
    }

    /**
     * decreases the month of the calendar by one.
     */
    @FXML
    public void decreaseOffset() {
        today.roll(today.MONTH, -1);
        firstOfMonth.roll(firstOfMonth.MONTH, -1);
        generateMonth();
    }

    /**
     * Creates a new empty event.
     */
    @FXML
    public void generateEvent() {
        eventdialog.getChildren().clear();
        final String[] flokkur = new String[1];
        flokkur[0] = "#facafa";
        TextField text = new TextField("Nafn viðburðar.");
        eventdialog.getChildren().add(text);
        DatePicker datePicker = new DatePicker(
                today.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        eventdialog.getChildren().add(datePicker);
        TextField description = new TextField("Lýsing á viðburði");
        eventdialog.getChildren().add(description);

        Button saveButton = new Button("Vista.");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LocalDate tempDate = datePicker.getValue();
                save(((Object) text.getText()), tempDate.getYear(), tempDate.getMonth().getValue(),
                        tempDate.getDayOfMonth(), description.getText(), flokkur[0]);
                generateMonth();
            }
        });
        Button deleteButton = new Button("Eyða.");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                delete(text.getText());
                generateMonth();
            }
        });
        HBox buttonContainer = new HBox();
        MenuItem skemmtun = new MenuItem("Skemmtun");
        skemmtun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#34bdeb";
            }
        });
        MenuItem vinna = new MenuItem("Vinna");
        vinna.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#ebe134";
            }
        });
        MenuItem fundur = new MenuItem("Fundur");
        fundur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#eb34c6";
            }
        });
        MenuButton menuBar = new MenuButton("Flokkar", null, skemmtun, vinna, fundur);
        HBox menuButtonitems = new HBox(menuBar);
        eventdialog.getChildren().add(menuButtonitems);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().add(saveButton);
        buttonContainer.getChildren().add(deleteButton);
        buttonContainer.getChildren().add(menuBar);
        eventdialog.getChildren().add(buttonContainer);

    }

    /**
     * Loads a event with the given name
     * @param name the name of the event to load.
     */
    @FXML
    public void generateEvent(String name) {
        ArrayList<Object> details = storageManager.getStored(name);

        eventdialog.getChildren().clear();
        String[] flokkur = new String[1];
        TextField text = new TextField(String.valueOf(details.get(0)));
        eventdialog.getChildren().add(text);
        DatePicker datePicker = new DatePicker(
                today.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        datePicker.setValue(LocalDate.of(
                Integer.valueOf(String.valueOf(details.get(1))),
                Integer.valueOf(String.valueOf(details.get(2))),
                Integer.valueOf(String.valueOf(details.get(3)))));
        eventdialog.getChildren().add(datePicker);
        TextField description = new TextField(String.valueOf(details.get(4)));
        eventdialog.getChildren().add(description);
        flokkur[0] = String.valueOf(details.get(5));

        Button saveButton = new Button("Vista.");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LocalDate tempDate = datePicker.getValue();
                save(((Object) text.getText()), tempDate.getYear(), tempDate.getMonth().getValue(),
                        tempDate.getDayOfMonth(), description.getText(), flokkur[0]);
                generateMonth();
            }
        });
        Button deleteButton = new Button("Eyða.");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                delete(name);
                generateMonth();
            }
        });
        HBox buttonContainer = new HBox();
        
        MenuItem skemmtun = new MenuItem("Skemmtun");
        skemmtun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#34bdeb";
            }
        });
        MenuItem vinna = new MenuItem("Vinna");
        vinna.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#ebe134";
            }
        });
        MenuItem fundur = new MenuItem("Fundur");
        fundur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                flokkur[0] = "#eb34c6";
            }
        });
        MenuButton menuBar = new MenuButton("Flokkar", null, skemmtun, vinna, fundur);
        HBox menuButtonitems = new HBox(menuBar);
        eventdialog.getChildren().add(menuButtonitems);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().add(saveButton);
        buttonContainer.getChildren().add(deleteButton);
        buttonContainer.getChildren().add(menuBar);
        eventdialog.getChildren().add(buttonContainer);

    }
    /**
     * Deletes a event with a given name
     * @param name the name of the event to delete
     */
    private void delete(String name){
        storageManager.removeStored(name);
        eventDays = storageManager.getDateName();
    }

}