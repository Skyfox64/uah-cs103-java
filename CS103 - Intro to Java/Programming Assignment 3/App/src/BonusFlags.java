import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Programming Assignment 3
 * 
 * Question 4(Bonus): Country Flags JavaFx
 */
public class BonusFlags extends Application {

    public class Flag {
        private String name;
        private String path;

        public Flag(String name, String path) {
            this.name = name;
            this.path = path;
        }

        @Override
        public String toString() {
            return this.name.toString();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        VBox vBox = new VBox(flagsUIGroup());
        Scene scene = new Scene(vBox, 700, 400, Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Country Flags");
        primaryStage.show();
    }

    private Node flagsUIGroup() throws FileNotFoundException {

        BorderPane flagsUIGroup = new BorderPane();

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(150, 400, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(500, 400, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2);

        Label topLabel = new Label("Select Country to display flag");
        gridpane.add(topLabel, 1, 0);
        GridPane.setHalignment(topLabel, HPos.CENTER);

        Label countriesLabel = new Label("Countries");
        gridpane.add(countriesLabel, 0, 1);
        GridPane.setHalignment(countriesLabel, HPos.CENTER);

        final ObservableList<Flag> flagsList = FXCollections.observableArrayList(getFlags());
        final ListView<Flag> allCountryFlagsListView = new ListView<>(flagsList);
        gridpane.add(allCountryFlagsListView, 0, 2);

        Label countryFlagLabel = new Label("");
        GridPane.setHalignment(countryFlagLabel, HPos.CENTER);
        gridpane.add(countryFlagLabel, 1, 1);

        ImageView imageView = new ImageView();

        allCountryFlagsListView.setOnMouseClicked((MouseEvent event) -> {
            Flag flag = allCountryFlagsListView.getSelectionModel().getSelectedItem();
            if (flag != null) {
                // allCountryFlagsListView.getSelectionModel().clearSelection();
                countryFlagLabel.setText(flag.name + "'s Flag");
                Image image = null;
                try {
                    image = new Image(new FileInputStream(flag.path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageView.setImage(image);
            }
        });

        HBox hbox = new HBox(imageView);
        gridpane.add(hbox, 1, 2);

        flagsUIGroup.setCenter(gridpane);
        return flagsUIGroup;
    }

    private ArrayList<Flag> getFlags() {
        ArrayList<Flag> flags = new ArrayList<Flag>();

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        String d = File.separator;
        // File flagsFolder = new File(s + d 
        //     + "src" + d + "main" + d + "resources" + d + "Flags");
        File flagsFolder = new File(s + d + "Flags");

        if (flagsFolder.exists()) {
            File flagsFileList[] = flagsFolder.listFiles();
            for (File flagFile : flagsFileList) {
                String countryName = convertToTitleCaseSplitting(
                        flagFile.getName().substring(0, flagFile.getName().lastIndexOf("-flag")));
                Flag flag = new Flag(countryName, flagFile.getAbsolutePath());
                flags.add(flag);
            }
        }

        return flags;
    }

    public static String convertToTitleCaseSplitting(String text) {
        String delimiter = "-";
        if (text == null || text.isEmpty()) {
            return text;
        }

        return Arrays.stream(text.split(delimiter)).map(
                word -> word.isEmpty() ? word : Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
