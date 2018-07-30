/**
 * Created by Stephen on 7/28/2018.
 */

import com.company.Database.dbConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;
import java.util.ArrayList;

public class GUI extends Application {

    private Stage primaryStage;

    //private BorderPane rootLayout;
    private Pane rootLayout;

    @FXML
    private ListView<String> databaseListView;

    private ObservableList<String> databaseObservableList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Varexp Database");

        Parent root = FXMLLoader.load(getClass().getResource("views/MenuScene.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("views/MenuScene.fxml"));
            //rootLayout = (BorderPane)loader.load();
            rootLayout = (Pane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
