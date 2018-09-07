package Controller;

import com.company.pcvue.fields.Common;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Stephen on 8/25/2018.
 */
public class CommonTagSceneController {

    private Stage currentWindow;


    public CommonTagSceneController() {
        Common common = new Common();
    }

    @FXML
    void initialize() {

    }

    public void setCurrentWIndow(Stage stage) {
        this.currentWindow = stage;
    }

    public void setCommonScene() {

    }

}
