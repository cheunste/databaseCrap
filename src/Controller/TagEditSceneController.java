package Controller;

import com.company.Database.dbConnector;
import com.company.pcvue.fields.Common;
import com.company.pcvue.fields.VarexpTuple;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String TEXT_FIELD = "TF";
    private String COMBO_BOX = "CB";

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

        //Get the tagnames and load them to observable list
        getVariableNames();

        //Set up the filter
        filterSetUp();

        //Here we will generate 40 new TextINput objects
        Common common = new Common();

        //Call the Common Varexp's fieldMap.
        Map<String, VarexpTuple> commonMap = common.getFieldMap();

        //Traverse its map for its keys and then using the key values, create text input as well as their labels.
        //Note that the fxid is the varexp position
        int columnCount = 0;

        for (String key : commonMap.keySet()) {
            String widgetType = (String) commonMap.get(key).getWidgetType();
            int position = (Integer) commonMap.get(key).getPosition();
            Boolean visible = (Boolean) commonMap.get(key).getVisibility();
            //Create text Field items for common variable fields
            if (widgetType.equals(TEXT_FIELD) && visible) {
                TextInput input = new TextInput(
                        "" + position,
                        new Label(key),
                        new JFXTextField(),
                        (Integer) commonMap.get(key).getPosition(),
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
            //Create Combobox Item for common variable fields
            else if (widgetType.equals(COMBO_BOX) && visible) {
                ComboBox cb = new ComboBox(
                        "" + position,
                        new Label(key),
                        new JFXComboBox(),
                        (Integer) commonMap.get(key).getPosition(),
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
        fillComboBoxChoices();

        //Now load the variable's common fields and set up the listeners. This should be a function as you'll need to call it everytime
        //another variable is clicked on the listview
        //TODO: Implement this
        setUpCommonVariableListener();

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

    public void saveCurrentTag() {

    }

    //This function gets the variable names and their ID given the database (set in constructor) and formats it.
    // It returns a void as it will be set to a member variable dictionary (You'll need this when making edits)
    private void getVariableNames() {
        dbConnector conn = new dbConnector();
        //Form the variable name
        String getTagNameCmd = "select variable_id,1st_element, 2nd_element, 3rd_element,4th_element,5th_element,6th_element,7th_to_12th from common;";
        ArrayList<ArrayList<String>> temp;

        //Query the tag name in the selected DB
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
        }
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
    private void fillComboBoxChoices() {


        for (ComboBox comboBoxItems : comboBoxList) {
            String id = comboBoxItems.getComboBox().getId();
            String text = comboBoxItems.getLabel().getText();
            for (String choice : (String[]) comboBoxItems.getTupleData().getUserText()) {
                comboBoxItems.addDropDownChoice(choice);
            }
        }
    }

    //This function sets up listeners to all the Common Variable's textfield and combo boxes
    private void setUpCommonVariableListener() {
        for (TextInput textFieldInput : textFieldList) {
            textFieldInput.getTextField().textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    //TODO: Implement the change listener so that the textfield turns red whenever the user goes over the text limit
                    System.out.println(textFieldInput.getName() + " " + newValue);
                }
            });
        }
        for (ComboBox comboBoxInput : comboBoxList) {
            comboBoxInput.getComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    //TODO: Implement the change listener so that whenever a certain choice is clicked, different things happens
                    System.out.println(comboBoxInput.getName() + " " + newValue);
                }
            });
        }
    }

    //This is a public inner class  to create a textfield item along with its assocated label
    class TextInput {
        private final String name;
        private final Label label;
        private final JFXTextField textField;
        private final VarexpTuple tupleData;

        public TextInput(String name, Label label, JFXTextField textField, int varexpId, VarexpTuple tupleData) {
            this.name = name;
            this.label = label;
            this.textField = textField;
            this.textField.setId(String.valueOf(varexpId));
            this.textField.setPrefWidth(PREF_SIZE_WIDTH);
            this.tupleData = tupleData;
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

        public VarexpTuple getTupleData() {
            return tupleData;
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
        private final VarexpTuple tupleData;

        public ComboBox(String name, Label label, JFXComboBox comboBox, int varexpId, VarexpTuple tupleData) {
            this.name = name;
            this.label = label;
            this.comboBox = comboBox;
            this.comboBox.setId(String.valueOf(varexpId));
            this.comboBox.setPrefWidth(PREF_SIZE_WIDTH);
            this.tupleData = tupleData;
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

        public VarexpTuple getTupleData() {
            return tupleData;
        }
    }
}




