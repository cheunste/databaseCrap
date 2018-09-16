package Controller;

import com.company.Database.dbConnector;
import com.company.pcvue.fields.Common;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Stephen on 8/25/2018.
 */
public class TagEditSceneController {

    private final int COMMON_ITEM_LIMIT = 25;
    private final int PREF_SIZE_WIDTH = 225;
    //List of new TExtInput objects
    List<TextInput> textInputs = new ArrayList<>();
    //Member variables
    private String databaseName;
    private ObservableList<String> tagList;
    private Map<String, String> idMap;
    private Stage currentWindow;
    //List of either textField or comboBoxes. You'll need these for reference
    private List<TextInput> textFieldList = new ArrayList<>();
    private List<ComboBox> comboBoxList = new ArrayList<>();

    //FXML widgets
    @FXML
    private ListView TagListView;
    @FXML
    private GridPane leftGridPane;
    @FXML
    private GridPane rightGridPane;
    @FXML
    private VBox CommonView;
    @FXML
    private VBox CommonView2;
    @FXML
    private VBox SourceView;
    @FXML
    private VBox CommandView;
    @FXML
    private Button btnGetInputText;
    @FXML
    private TextField TagFilterField;

    public TagEditSceneController(String databaseName) {
        this.databaseName = databaseName;
        idMap = new HashMap<String, String>();

        tagList = FXCollections.observableArrayList();
    }

    /*
    TODO:
        In the initialize function, the goal here is to
        1) initialize the common fields
        2) initialize the source fields and disable  the visibility to all
        3) initialize the command feilds and disable the visibility to all
        4) Query the entire common table for all the name elements (1st element, 2nd element, etc.)
        5) Combine all the element to get a name
        6) Add the name to the listview
        7) Highlight the first item of the listview. If there are none, then you have to handle it
        8) display the appropriate information
        9) Imiplement the text filter function

         */
    @FXML
    void initialize() {

        getVariableNames();
        filterSetUp();

        //Here we will generate 40 new TextINput objects
        Common common = new Common();

        //Call the Common Varexp's fieldMap.
        Map<String, Integer> commonMap = common.getFieldMap();

        //Traverse its map for its keys and then using the key values, create text input as well as their labels.
        //Note that the fxid is the varexp position
        int columnCount = 0;
        ArrayList<String> cbItems = common.getComboBoxItems();
        for (String key : commonMap.keySet()) {
            //Create text field itmes
            if (!cbItems.contains(key)) {
                TextInput input = new TextInput(
                        "Input" + columnCount,
                        new Label(key),
                        new JFXTextField(),
                        commonMap.get(key)
                );
                if (columnCount < COMMON_ITEM_LIMIT) {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    leftGridPane.add(input.getLabel(), 0, columnCount);
                    leftGridPane.add(input.getTextField(), 1, columnCount);
                    //Finaly, add the input to the list so they can be retrieved later using the input's Name
                    textInputs.add(input);
                } else {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    rightGridPane.add(input.getLabel(), 0, columnCount - COMMON_ITEM_LIMIT);
                    rightGridPane.add(input.getTextField(), 1, columnCount - COMMON_ITEM_LIMIT);
                    //Finaly, add the input to the list so they can be retrieved later using the input's Name
                    textInputs.add(input);
                }
                textFieldList.add(input);
            }
            //Create comboBox items
            else {
                ComboBox cb = new ComboBox(
                        "Input" + columnCount,
                        new Label(key),
                        new JFXComboBox(),
                        commonMap.get(key)
                );
                if (columnCount < COMMON_ITEM_LIMIT) {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    leftGridPane.add(cb.getLabel(), 0, columnCount);
                    leftGridPane.add(cb.getComboBox(), 1, columnCount);
                    //Finaly, add the input to the list so they can be retrieved later using the input's Name
                } else {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    rightGridPane.add(cb.getLabel(), 0, columnCount - COMMON_ITEM_LIMIT);
                    rightGridPane.add(cb.getComboBox(), 1, columnCount - COMMON_ITEM_LIMIT);
                    //Finaly, add the input to the list so they can be retrieved later using the input's Name
                }
                comboBoxList.add(cb);
            }
            columnCount++;
        }


        //Implement the choices in the comboboxes
        //TODO: Implement this
        fillComboBoxChoices(common.getComboBoxChoices());

        //Now load the variable's common fields. This should be a function as you'll need to call it everytime
        //another variable is clicked on the listview
        //TODO: Implement this

        //Now using the variable's command and source field, load the Source and Command textfields
        //TODO: Implement this

        //Finally, load the Source and Command field parameters
        //TODO: Implement this

        //At this point, disable the visibility of all reserved fields
        //TODO: Implement this

        // Use the button to print out the text from Input #4
        //btnGetInputText.setOnAction(e -> {
        //});

    }

    public void setCurrentWindow(Stage stage) {
        this.currentWindow = stage;
    }

    public void setCommonScene() {

    }

    //This function gets the variable names and their ID given the database (set in constructor) and formats it.
    // It returns a void as it will be set to a member variable dictionary (You'll need this when making edits)
    private void getVariableNames() {
        dbConnector conn = new dbConnector();
        String getTagNameCmd = "select variable_id,1st_element, 2nd_element, 3rd_element,4th_element,5th_element,6th_element,7th_to_12th from common;";
        ArrayList<ArrayList<String>> temp;
        temp = conn.readDatabase(databaseName, getTagNameCmd);

        //Traverse the arraylist
        for (ArrayList<String> item : temp) {
            int arraySize = item.size();
            String tagName = "";
            for (int i = 1; i < arraySize; i++) {
                tagName += item.get(i) + ".";
            }
            //This line is a  way of removing multiple ".." from the end of the String. I hope you know regex...Java regex
            tagName = tagName.replaceAll("\\.{1,}\\Z", "");
            idMap.put(tagName, item.get(0));
            tagList.add(tagName);
            //TagListView.getItems().add(tagName);
        }
        //TagListView.setItems(tagList);
    }

    /*
    This function sets up the filtering funciton
     */
    private void filterSetUp() {
        FilteredList<String> filteredData = new FilteredList<>(tagList, s -> true);
        TagListView.setItems(filteredData);

        TagFilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchTerm = TagFilterField.getText();
            if (searchTerm == null || searchTerm.length() == 0) {
                filteredData.setPredicate(s -> true);
            } else {
                filteredData.setPredicate(s -> s.contains(searchTerm));
            }
        });
    }

    //This function fills out the comboBox Choices for the Common Varexp Variable
    private void fillComboBoxChoices(LinkedHashMap<String, String[]> comboBoxChoicesMap) {

        for (ComboBox comboBoxItems : comboBoxList) {
            String id = comboBoxItems.getComboBox().getId();
            String text = comboBoxItems.getLabel().getText();
            for (String choices : comboBoxChoicesMap.get(text)) {
                comboBoxItems.addDropDownChoice(choices);
            }
        }
    }

    //This is a public inner class  to create a textfield item along with its assocated label
    class TextInput {
        private final String name;
        private final Label label;
        private final JFXTextField textField;

        public TextInput(String name, Label label, JFXTextField textField, int varexpId) {
            this.name = name;
            this.label = label;
            this.textField = textField;
            this.textField.setId(String.valueOf(varexpId));
            this.textField.setPrefWidth(PREF_SIZE_WIDTH);
        }

        public String getName() {
            return name;
        }

        public Label getLabel() {
            return label;
        }

        public TextField getTextField() {
            return textField;
        }
    }

//This is a public inner class  to create a combobox item along with its assocated label.
//It also provides a method to add items to the box itself

    /**
     * Public Class that creates a Combo Box group (consists of label, name, combo box and id for combo box)
     */
    class ComboBox {
        private final String name;
        private final Label label;
        private final JFXComboBox comboBox;

        public ComboBox(String name, Label label, JFXComboBox comboBox, int varexpId) {
            this.name = name;
            this.label = label;
            this.comboBox = comboBox;
            this.comboBox.setId(String.valueOf(varexpId));
            this.comboBox.setPrefWidth(PREF_SIZE_WIDTH);
        }

        public String getName() {
            return name;
        }

        public Label getLabel() {
            return label;
        }

        public JFXComboBox getComboBox() {
            return comboBox;
        }

        public void setComboBox() {
        }

        public void addDropDownChoice(String choice) {
            comboBox.getItems().add(choice);
        }
    }
}




