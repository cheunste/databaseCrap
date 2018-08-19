package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Stephen on 8/12/2018.
 */
public class ExportSceneController {
    private final int WINDOW_WIDTH = 250;
    private final int SPACING = 10;
    private final String PATH_TO_SCENE = "/View/ExportScene.fxml";
    private Stage exportWindow;
    private String outputDirectory;

    @FXML
    private Label outputLabel;

    @FXML
    private JFXButton exportButton;

    @FXML
    private JFXButton cancelButton;

    public static void confirmButtonEvent() {
        //At this point, call the Export method in the Main class and then close this window
        //TODO: Implement this

    }

    private static String directorySelector(Stage stage) {
        String chosenDirectory = "";
        //Using the try catch block because if the user hits cancel, then that throws a nullpointerexception
        try {
            DirectoryChooser exportDirectoryChooser = new DirectoryChooser();
            exportDirectoryChooser.setTitle("Export Varexp File");
            exportDirectoryChooser.getInitialDirectory();
            chosenDirectory = exportDirectoryChooser.showDialog(stage).getAbsolutePath();
        } catch (NullPointerException e) {
            //Ignore all exception. It just means the user clicked cancel
        } finally {
            return chosenDirectory;
        }
    }

    public void display(String databaseSelected) throws Exception {

        exportWindow = new Stage();

        if (databaseSelected == null) {

            /*
           JFXDialog dialog = new JFXDialog();
           dialog.setContent(new Label("Content"));
           dialog.show();
           */

        } else {

            exportWindow.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource(PATH_TO_SCENE));
            exportWindow.setScene(new Scene(root));
            exportWindow.setTitle("Choose Export Directory:");
            exportWindow.setTitle("Export Varexp");
            exportWindow.showAndWait();
        }

    }

    public void closeButtonEvent() {
        exportWindow.close();

    }

    public void directorySelector(ActionEvent actionEvent) {
        String chosenDirectory = "";
        //Using the try catch block because if the user hits cancel, then that throws a nullpointerexception
        try {
            DirectoryChooser exportDirectoryChooser = new DirectoryChooser();
            exportDirectoryChooser.setTitle("Export Varexp File");
            exportDirectoryChooser.getInitialDirectory();
            chosenDirectory = exportDirectoryChooser.showDialog(exportWindow).getAbsolutePath();
            System.out.println(chosenDirectory);
            outputLabel.setText(chosenDirectory);
            outputDirectory = chosenDirectory;
            exportButton.setDisable(false);
        } catch (NullPointerException e) {
            //Ignore all exception. It just means the user clicked cancel
        } finally {
        }
    }

    public void exportButtonEvent(ActionEvent actionEvent) {

    }

    public void cancelButtonEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
