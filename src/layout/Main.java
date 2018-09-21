package layout;

import function.ButtonFunctions;
import function.HDU_Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.util.Optional;

import static layout.Controller.*;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private String light = getClass().getResource("light.css").toExternalForm();
    private String dark = getClass().getResource("dark.css").toExternalForm();
    private String solarized = getClass().getResource("solarized.css").toExternalForm();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Application frame
        BorderPane root;
        root = FXMLLoader.load(getClass().getResource("layout.fxml"));
        root.setId("bg_menu");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 850, 670);
        primaryStage.setScene(scene);

        // File menu
        Menu file = new Menu("File");

        // Store number option
        MenuItem store = new MenuItem("Enter Store #");

        // Theme menu, within File Menu
        Menu themeMenu = new Menu("Theme");

        // Light and Dark options
        MenuItem lightTheme = new MenuItem("Light");
        MenuItem darkTheme = new MenuItem("Dark");
        MenuItem solarizedTheme = new MenuItem("Solarized");

        // Add items to themeMenu
        themeMenu.getItems().add(lightTheme);
        themeMenu.getItems().add(darkTheme);
        themeMenu.getItems().add(solarizedTheme);

        // Exit option
        MenuItem exit = new MenuItem("Exit");

        // Add items to File menu, including themeMenu
        file.getItems().add(store);
        file.getItems().add(themeMenu);
        file.getItems().add(exit);

        // Help menu: About button
        Menu help = new Menu("Help");

        // About option
        MenuItem about = new MenuItem("About");

        // Add About to help menu
        help.getItems().add(about);

        // Menu Bar setup
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(file);
        menuBar.getMenus().add(help);
        menuBar.setMaxHeight(0);
        // Set the menu bar at the top of the application
        root.setTop(menuBar);
        menuBar.setId("bg_menu");

        Label label1 = new Label("Store: " + storeNumber);
        //label1.setTranslateY(0);
        //label1.setTranslateX(100);

        root.bottomProperty().setValue(label1);

        /********************
         * MenuItem methods *
         ********************/

        // Sets the storeNumber variable
        store.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Set the image to use here
                Image logo = new Image("res/d.png");

                TextInputDialog dialog = new TextInputDialog("");

                // Dialog values
                dialog.setTitle("Enter Store Number");
                dialog.setHeaderText("Enter Store Number:");
                dialog.setGraphic(new ImageView(logo));
                dialog.setContentText("Store:");

                // Set every applicable label
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    storeNumber = result.get();
                    label1.setText("Store: " + storeNumber);
                    String msg = " The store number was successfully updated to: ";
                    HDU_Logger.appendLog(msg, storeNumber);
                }
            }
        });

        // Changes the theme to light.css
        lightTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.getStylesheets().remove(dark);
                if(!scene.getStylesheets().contains(light)) scene.getStylesheets().add(light);
            }
        });

        // Changes the theme to dark.css
        darkTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.getStylesheets().remove(light);
                if(!scene.getStylesheets().contains(dark)) scene.getStylesheets().add(dark);
            }
        });

        // Changes the theme to solarized
        solarizedTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.getStylesheets().remove(light);
                scene.getStylesheets().remove(dark);
                if(!scene.getStylesheets().contains(solarized)) scene.getStylesheets().add(solarized);
            }
        });

        // Exits the application
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String msg = " Application closed by user.";
                HDU_Logger.appendLog(msg);
                ButtonFunctions.close_app();
            }
        });

        // Launches the About box
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String msg = " Program information accessed by user.";
                HDU_Logger.appendLog(msg);
                ButtonFunctions.loadAboutInfoAlpha();
            }
        });

        /* These methods are to handle mouse clicks and drags on the top of the
         * visible window to allow for movement despite being set to TRANSPARENT.
         */
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
