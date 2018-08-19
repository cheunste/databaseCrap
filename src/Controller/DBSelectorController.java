package Controller;

import com.company.Database.dbConnector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 8/5/2018.
 */
public class DBSelectorController implements Initializable {
    private static final int DOUBLE_CLICK = 2;
    ListView<String> listView;
    ArrayList<String> listofDB;
    @FXML
    JFXListView<String> listPane;

    @FXML
    private JFXButton importBtn;
    @FXML
    private JFXButton exportBtn;
    @FXML
    private JFXButton openBtn;
    @FXML
    private JFXButton settingsBtn;

    @FXML
    private void openMenu() {
    }

    @FXML
    private void exportMenu() throws Exception {
        String selectedDB = listPane.getSelectionModel().getSelectedItem();
        if (selectedDB == null) {
            //Nothing happens here. Either Do nothing or show a popup informing user
        } else {
            ExportSceneController exportScene = new ExportSceneController();
            exportScene.display(selectedDB);
        }
    }

    @FXML
    private void importMenu() throws Exception {
        //ImportVarexpScene.display(listPane.getItems());
        ImportSceneController importScene = new ImportSceneController();
        importScene.display();
    }

    @FXML
    private void settingsMenu() {
    }


    //Initialize the window by adding listeners and other binding calls
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Add list of DB from MYSQL
        listofDB = new ArrayList<>();
        dbConnector db = new dbConnector();
        ArrayList<String> temp = db.showDatabases();
        for (String item : temp) {
            listofDB.add(item);
        }
        listPane.getItems().addAll(listofDB);


        //Button mapping
        importBtn.setOnAction((ActionEvent e) -> {
            try {
                importMenu();
            } catch (Exception x) {
                x.printStackTrace();
            }
        });

        exportBtn.setOnAction(e -> {
            try {
                exportMenu();
            } catch (Exception x) {
                x.printStackTrace();
            }
        });
        openBtn.setOnAction(e -> {
            try {
                openMenu();
            } catch (Exception x) {
                x.printStackTrace();
            }
        });
        settingsBtn.setOnAction(e -> {
            try {
                settingsMenu();
            } catch (Exception x) {

            }
        });
        listPane.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                listPane.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                String databaseSelected = listPane.getSelectionModel().getSelectedItem();
                //When double clicked, this means you should open the database and allow users to edit the DB. This also means you should close
                //the current scene and open up another scene (Make a method for this)
                //TODO: make a method to open up a new scene given the database name
                if (e.getClickCount() == DOUBLE_CLICK) {
                    System.out.println("Double clicked on: " + databaseSelected);
                    openTagEditScene();
                    //Consume the event just incase weird shit happens
                    e.consume();
                }
            }

        });
    }

    public void openTagEditScene() {

    }
}
