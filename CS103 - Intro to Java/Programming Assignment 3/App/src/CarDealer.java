import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Programming Assignment 3
 * 
 * Question 3: Car Dealer
 * JavaFx
 */
public class CarDealer extends Application {
    Stage window;
    Scene scene1, scene2;

    public static Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

    public class Car {
        private Integer id;
        private Integer year;
        private String make;
        private String model;
        private Integer numOwners;
        private Boolean conditionNew;
        private Integer mpg;

        public Car(int id, int year, String make, String model, int numOwners, boolean conditionNew, int mpg) {
            this.id = id;
            this.year = year;
            this.make = make;
            this.model = model;
            this.numOwners = numOwners;
            this.conditionNew = conditionNew;
            this.mpg = mpg;
        }

        @Override
        public String toString() {
            return this.id.toString();
        }
    }

    public void recordCarInformation(Car car) {

        // Example
        // Car car2 = new Car(1234, 2020, "Toyota", "Camry", 0, true, 30);
        // car = car2;

        // Creating the directory
        File carsFolder = new File("Cars");
        carsFolder.mkdir(); // Make Folder
        boolean bool = carsFolder.exists();
        if (bool) {
            // Creating a File object
            File carFile = new File(carsFolder.getAbsolutePath() + "\\" + car.id + ".json");

            try (FileWriter writer = new FileWriter(carFile)) {
                gson.toJson(car, writer);
            } catch (IOException ex) {
                System.out.println(ex);
            }

            System.out.println("Car Recorded Successfully");
            System.out.println(car.id);
            System.out.println(car.year);
            System.out.println(car.make);
            System.out.println(car.model);
            System.out.println(car.numOwners);
            System.out.println(car.conditionNew);
            System.out.println(car.mpg);

        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    private static ArrayList<Car> retrieveCarRecords() throws IOException {
        ArrayList<Car> cars = new ArrayList<Car>();

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        
        // Creating a File object for cars directory
        File carsFolder = new File(s +File.separator + "Cars");

        if (carsFolder.exists()){ 
            // List of all files and directories
            File carsFileList[] = carsFolder.listFiles();
            System.out.println("List of files and directories in the specified directory:");
            for (File carFile : carsFileList) {
                System.out.println(" ");
                System.out.println("File name: " + carFile.getName());
                System.out.println("File path: " + carFile.getAbsolutePath());
                System.out.println("Size :" + carFile.getTotalSpace());
                System.out.println(" ");

                // create a reader
                try (Reader reader = Files.newBufferedReader(Paths.get(carFile.getAbsolutePath()))) {
                    // convert JSON string to Car object
                    Car car = gson.fromJson(reader, Car.class);
                    cars.add(car);

                    // print car object
                    System.out.println("Car Retrieved Successfully");
                    System.out.println(car.id);
                    System.out.println(car.year);
                    System.out.println(car.make);
                    System.out.println(car.model);
                    System.out.println(car.numOwners);
                    System.out.println(car.conditionNew);
                    System.out.println(car.mpg);

                    // close reader
                    reader.close();
                }
            }
        }

        return cars;
    }

    /**
     * This uses one window, two scenes
     */
    // @Override
    public void start3(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        // Layout 1 - children are laid out in vertical column
        VBox layout1 = new VBox(20);

        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        // Button 2
        Button button2 = new Button("Go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        // Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);

        scene2 = new Scene(layout2, 600, 600);

        window.setScene(scene1);
        window.setTitle("Switching Scenes");
        window.show();
    }

    /**
     * This uses tabPane, one scene
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Record New Car", recordCarUIGroup());
        Tab tab2 = new Tab("All Cars", retrieveCarsUIGroup());

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabMinWidth(200);
        tabPane.setTabMinHeight(33);
        tabPane.setTabMaxWidth(600);
        tabPane.setTabMaxHeight(50);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox, 500, 350, Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Dealer App");
        primaryStage.show();
    }
    
    private Node recordCarUIGroup() {
        
        GridPane recordCarUIGroup = new GridPane();
        recordCarUIGroup.setHgap(5);
        recordCarUIGroup.setVgap(5);
        recordCarUIGroup.setAlignment(Pos.CENTER);
        
        recordCarUIGroup.add(new Label("Enter Car Information Below:"), 0, 0);
        recordCarUIGroup.add(new Label("ID"), 0, 1);
        TextField tfID = new TextField();
        tfID.setAlignment(Pos.BOTTOM_RIGHT);
        recordCarUIGroup.add(tfID, 1, 1);

        recordCarUIGroup.add(new Label("Year"), 0, 2);
        ComboBox<Integer> cbYear = new ComboBox<Integer>();
        for (int year = 2000; year <= 2020; year++)
        {
            cbYear.getItems().add(year);
        }
        recordCarUIGroup.add(cbYear, 1, 2);

        recordCarUIGroup.add(new Label("Make"), 0, 3);
        ComboBox<String> cbMake = new ComboBox<String>();
        cbMake.getItems().add("Toyota");
        cbMake.getItems().add("Honda");
        cbMake.getItems().add("Ford");
        cbMake.getItems().add("Mercedes-Benz");
        cbMake.getItems().add("BMW");
        cbMake.getItems().add("Subaru");
        recordCarUIGroup.add(cbMake, 1, 3);
        
        recordCarUIGroup.add(new Label("Model"), 0, 4);
        TextField tfModel = new TextField();
        tfModel.setAlignment(Pos.BOTTOM_RIGHT);
        recordCarUIGroup.add(tfModel, 1, 4);
        
        recordCarUIGroup.add(new Label("# of Owners"), 0, 5);
        TextField tfNumOwners = new TextField();
        tfNumOwners.setAlignment(Pos.BOTTOM_RIGHT);
        recordCarUIGroup.add(tfNumOwners, 1, 5);
        
        recordCarUIGroup.add(new Label("Condition"), 0, 6);
        ToggleGroup tgConditionGroup = new ToggleGroup();
        RadioButton rbConditionNew = new RadioButton("New");
        rbConditionNew.setToggleGroup(tgConditionGroup);
        RadioButton rbConditionUsed = new RadioButton("Used");
        rbConditionUsed.setToggleGroup(tgConditionGroup);
        HBox hbox = new HBox(rbConditionNew, rbConditionUsed);
        hbox.setSpacing(15);
        recordCarUIGroup.add(hbox, 1, 6);
        
        recordCarUIGroup.add(new Label("MPG"), 0, 7);
        TextField tfMpg = new TextField();
        tfMpg.setAlignment(Pos.BOTTOM_RIGHT);
        recordCarUIGroup.add(tfMpg, 1, 7);

        Button btRecord = new Button("Submit");
        GridPane.setHalignment(btRecord, HPos.RIGHT);        
        btRecord.setOnAction((ActionEvent event) -> {
            Boolean conditionNew = false;
            RadioButton selectedRadioButton = (RadioButton) tgConditionGroup.getSelectedToggle();
            if (selectedRadioButton != null) { 
                String conditionText = selectedRadioButton.getText();
                if (conditionText.equals("New")) {
                    conditionNew = true;
                }
            }
            Car car = new Car(
                Integer.parseInt(tfID.getText()),
                cbYear.getValue(),
                cbMake.getValue(),
                tfModel.getText(),
                Integer.parseInt(tfNumOwners.getText()),
                conditionNew,
                Integer.parseInt(tfMpg.getText())
            );

            recordCarInformation(car);
        });
        recordCarUIGroup.add(btRecord, 1, 8);

        return recordCarUIGroup;
    }

    private Node retrieveCarsUIGroup() throws IOException {
        BorderPane retrieveCarsUIGroup = new BorderPane();

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(150, 300, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(100);
        ColumnConstraints column3 = new ColumnConstraints(250, 300, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2, column3);

        Label tabLabel = new Label("Show all cars available"); 
        gridpane.add(tabLabel, 0, 0);
        GridPane.setHalignment(tabLabel, HPos.CENTER);

        Label carsInLotLabel = new Label("Cars in Lot");
        gridpane.add(carsInLotLabel, 0, 1);
        GridPane.setHalignment(carsInLotLabel, HPos.CENTER);

        final ObservableList<Car> allCarsList = FXCollections.observableArrayList(retrieveCarRecords());
        final ListView<Car> allCarsListView = new ListView<>(allCarsList);
        gridpane.add(allCarsListView, 0, 2);

        Label carInfoLabel = new Label("Car Information");
        GridPane.setHalignment(carInfoLabel, HPos.LEFT);
        gridpane.add(carInfoLabel, 2, 1);
        
        GridPane carRecordGridPane = new GridPane();
        carRecordGridPane.setHgap(10);
        carRecordGridPane.setVgap(10);

        carRecordGridPane.add(new Label("ID"), 0, 1);
        Label lbID = new Label("");
        carRecordGridPane.add(lbID, 1, 1);

        carRecordGridPane.add(new Label("Year"), 0, 2);
        Label lbYear = new Label("");
        carRecordGridPane.add(lbYear, 1, 2);

        carRecordGridPane.add(new Label("Make"), 0, 3);
        Label lbMake = new Label("");
        carRecordGridPane.add(lbMake, 1, 3);
        
        carRecordGridPane.add(new Label("Model"), 0, 4);
        Label lbModel = new Label("");
        carRecordGridPane.add(lbModel, 1, 4);
        
        carRecordGridPane.add(new Label("# of Owners"), 0, 5);
        Label lbNumOwners = new Label("");
        carRecordGridPane.add(lbNumOwners, 1, 5);
        
        carRecordGridPane.add(new Label("Condition"), 0, 6);
        Label lbCondition = new Label("");
        carRecordGridPane.add(lbCondition, 1, 6);
        
        carRecordGridPane.add(new Label("MPG"), 0, 7);
        Label lbMPG = new Label("");
        carRecordGridPane.add(lbMPG, 1, 7);
        gridpane.add(carRecordGridPane, 2, 2);

        Button refreshButton = new Button(" Refresh  ");
        refreshButton.setOnAction((ActionEvent event) -> {
            try {
                allCarsListView.getItems().removeAll();
                allCarsListView.getItems().setAll(FXCollections.observableArrayList(retrieveCarRecords()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button retrieveButton = new Button(" Retrieve ");
        retrieveButton.setOnAction((ActionEvent event) -> {
            Car car = allCarsListView.getSelectionModel().getSelectedItem();

            lbID.setText(car.id.toString());
            lbYear.setText(car.year.toString());
            lbMake.setText(car.make);
            lbModel.setText(car.model);
            lbNumOwners.setText(car.numOwners.toString());
            if (car.conditionNew) {
                lbCondition.setText("New");
            }
            else {
                lbCondition.setText("Used");
            }
            lbMPG.setText(car.mpg.toString());
        });

        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(refreshButton, retrieveButton);
        gridpane.add(vbox, 1, 2);

        retrieveCarsUIGroup.setCenter(gridpane);
        return retrieveCarsUIGroup;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
