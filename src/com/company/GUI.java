package com.company; /**
 * Created by Stephen on 7/28/2018.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;


public class GUI extends Application {

    ListView<String> listView;
    ArrayList<String> listofDB;
    //This is our PrimaryStage (It contains everything)
    private Stage window;
    private Scene scene;
    private Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //1) Declare a primary Stage (Everything will be on this)
        window = primaryStage;

        //Operaitonal: set a title for primary Stage
        window.setTitle("Varexp DB");

        //Create an FXMLLoader object
        //Load the document
        //Parent root = FXMLLoader.load(getClass().getResource("com.company.View/DBSelector.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("View/DBSelector.fxml"));

        window.setScene(new Scene(root, 600, 400));

        window.show();
    }

}
