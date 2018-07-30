/**
 * Created by Stephen on 7/28/2018.
 */

import com.company.Database.dbConnector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class GUI extends Application {

    private final FileChooser fileDialog = new FileChooser();
    private Stage primaryStage;
    //private BorderPane rootLayout;
    private Pane rootLayout;
    @FXML
    private ListView<String> databaseListView;
    private ObservableList<String> databaseObservableList = FXCollections.observableArrayList();
    private HTMLEditor resumeEditor;

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

        //resumeEditor = new HTMLEditor();
        //resumeEditor.setPrefSize(600,300);

        ////Filter only HTML files
        //fileDialog.getExtensionFilters()
        //        .add(new ExtensionFilter("HTML Files","*.htm","*.html"));

        //Button openBtn = new Button("Open");
        //Button saveBtn = new Button("Save");
        //Button closeBtn = new Button("Close");

        //openBtn.setOnAction(e->openFile());
        //saveBtn.setOnAction(e->saveFile());
        //closeBtn.setOnAction(e->primaryStage.close());
        //HBox buttons = new HBox(20,openBtn,saveBtn,closeBtn);
        //buttons.setAlignment(Pos.CENTER_RIGHT);
        //VBox root = new VBox(resumeEditor,buttons);
        //root.setSpacing(20);
        //root.setStyle("-fx-padding:10;"+
        //        "-fx-border-style: solid-inside;"+
        //        "-fx-border-width: 2;"+
        //        "-fx-border-insets: 5;"+
        //        "-fx-border-radius: 5;"+
        //        "-fx-border-color: blue;"
        //);
        //Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        //primaryStage.setTitle("Editing Resume in HTML format");
        //primaryStage.show();
    }

    private void openFile() {
        fileDialog.setTitle("Open Resume");
        File file = fileDialog.showOpenDialog(primaryStage);
        if (file == null) {
            return;
        }
        try {
            //Read the file and popualte the HTML editor
            byte[] resume = Files.readAllBytes(file.toPath());
            resumeEditor.setHtmlText(new String(resume));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        fileDialog.setTitle("Save Resume");
        fileDialog.setInitialFileName("untitled.htm");
        File file = fileDialog.showSaveDialog(primaryStage);
        if (file == null) {
            return;
        }
        try {
            String html = resumeEditor.getHtmlText();
            Files.write(file.toPath(), html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
