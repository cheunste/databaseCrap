package Controller;

import com.company.Database.dbConnector;
import com.company.pcvue.fields.Common;
import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.VarexpTuple;
import com.company.pcvue.fields.VarexpVariable;
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

    //Constants
    private final int COMMON_ITEM_LIMIT = 25;
    private final int PREF_SIZE_WIDTH = 225;

    private final String VARIABLE_TYPE_COMBOBOX = "1";
    private final String SOURCE_OF_VARIABLE_COMBOBOX = "17";

    //List of new TExtInput objects
    //Member variables
    private String databaseName;
    private String TEXT_FIELD = "TF";
    private String COMBO_BOX = "CB";

    private ObservableList<String> tagList;
    private Map<String, String> idMap;
    private Stage currentWindow;
    //List of either textField or comboBoxes. You'll need these for reference
    private List<TextInput> commonTextFieldList = new ArrayList<>();
    private List<ComboBox> commonComboBoxList = new ArrayList<>();

    private List<TextInput> commandTextFieldList = new ArrayList<>();
    private List<ComboBox> commandComboBoxList = new ArrayList<>();

    private List<TextInput> sourceTextFieldList = new ArrayList<>();
    private List<ComboBox> sourceComboBoxList = new ArrayList<>();

    //FXML widgets
    @FXML
    private ListView TagListView;
    @FXML
    private GridPane commonViewLeftGridPane;
    @FXML
    private GridPane commonViewRightGridPane;

    @FXML
    private VBox SourceView;
    @FXML
    private VBox CommandView;
    @FXML
    private Button btnGetInputText;
    @FXML
    private TextField TagFilterField;

    @FXML
    private GridPane CommandGridPane;
    @FXML
    private GridPane SourceGridPane;

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
                    commonViewLeftGridPane.add(input.getLabel(), 0, columnCount);
                    commonViewLeftGridPane.add(input.getTextField(), 1, columnCount);
                } else {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    commonViewRightGridPane.add(input.getLabel(), 0, columnCount - COMMON_ITEM_LIMIT);
                    commonViewRightGridPane.add(input.getTextField(), 1, columnCount - COMMON_ITEM_LIMIT);
                }
                //Finaly, add the input to the list so they can be retrieved later using the input's Name
                commonTextFieldList.add(input);
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
                    commonViewLeftGridPane.add(cb.getLabel(), 0, columnCount);
                    commonViewLeftGridPane.add(cb.getComboBox(), 1, columnCount);
                } else {
                    //Now, add the new TextInput Label and TExtField to the GridPane
                    commonViewRightGridPane.add(cb.getLabel(), 0, columnCount - COMMON_ITEM_LIMIT);
                    commonViewRightGridPane.add(cb.getComboBox(), 1, columnCount - COMMON_ITEM_LIMIT);
                }
                //Finaly, add the input to the list so they can be retrieved later using the input's Name
                commonComboBoxList.add(cb);
            }
            columnCount++;
        }

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

        //Implement the choices in the comboboxes
        fillComboBoxChoices(commonComboBoxList);

        //Set up Listeners for the Common Text Fields
        setTextFieldListener(commonTextFieldList);

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

    /**
     * This function sets up the filtering function. However, it does NOT use any regex or wildcard characters
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
    private void fillComboBoxChoices(List<ComboBox> comboBoxList) {
        for (ComboBox comboBoxItems : comboBoxList) {
            for (String choice : (String[]) comboBoxItems.getTupleData().getUserText()) {
                comboBoxItems.addDropDownChoice(choice);
            }
        }
    }

    //This function sets up listeners to all the Common Variable's textfield and combo boxes
    //For textfields, you need to either check to see if a value is within the value limit or if it is within the byte limit
    // Not all textfields have a value limit, if it doesn't have a value limit, then go with the byte limit
    private void setUpCommonVariableListener() {
        setTextFieldListener(commonTextFieldList);

        for (ComboBox comboBoxInput : commonComboBoxList) {
            comboBoxInput.getComboBox().getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    System.out.println(comboBoxInput.getName() + " " + newValue);


                    /*
                     Now, what you want to do is call getUserText() (Should be String[])
                     get the index of whatever the user selected
                     and then match it with the valueStringArray (Should also be a String[] and must match the getUserText)

                    */
                    String[] userDisplayText = (String[]) comboBoxInput.getTupleData().getUserText();
                    String[] valueStringArray = (String[]) comboBoxInput.getTupleData().getValueOptions();

                    //Find the index of the combobox that the user selected
                    int index = -1;
                    for (int i = 0; i < userDisplayText.length; i++) {
                        if (userDisplayText[i].equals(newValue)) {
                            index = i;
                            break;
                        }
                    }
                    String comboBoxValueItem = valueStringArray[index];

                    /*
                    //TODO: Implement the change listener so that whenever "Variable Type" or "Source of Variable" is changed
                     */
                    if (comboBoxInput.getName().equals(VARIABLE_TYPE_COMBOBOX)) {
                        //Populate the selected Variable Type Choice
                        /*
                        At this point, you now need a case-switch statement to insert or remove command textfields
                        and combo boxes into the GUI
                        * */

                        System.out.println(comboBoxInput.getName() + " " + newValue);
                        VarexpFactory factory = new VarexpFactory();
                        VarexpVariable commandVariable = factory.declareNewVariable(comboBoxValueItem);
                        populateNonCommonVariables(commandVariable.getFieldMap(), CommandGridPane, commandTextFieldList, commandComboBoxList, "Command: " + newValue);
                    }
                    if (comboBoxInput.getName().equals(SOURCE_OF_VARIABLE_COMBOBOX)) {

                        //Populate the selected Source choice
                        System.out.println(comboBoxInput.getName() + " " + newValue);
                        VarexpFactory factory = new VarexpFactory();
                        VarexpVariable sourceVariable = factory.declareNewVariable(comboBoxValueItem);
                        populateNonCommonVariables(sourceVariable.getFieldMap(), SourceGridPane, sourceTextFieldList, sourceComboBoxList, "Source: " + newValue);
                    }
                }
            });
        }

    }


    /**
     * This method sets up listeners to textfields. Note that there is priority with this
     * <p>
     * The method takes the 'value limit' of the variable first and determines if it is out of range.
     * Not all Varexp Variables that uses a textfield have a 'value limit' with their variable, and in this case
     * the byte size of the variable will be used to see if it is out of range or not
     * <p>
     * Method will highlight a textfield **YELLOW** if it is out of the value scope of a variable
     * Method will highlight a textfield **RED** if it is the wrong type (ie using a char for an int field, or vice versa)
     * <p>
     * This method will also remove the CSS classes applied when the text clears
     *
     * @param textFieldList List of TExtInput classes
     */
    private void setTextFieldListener(List<TextInput> textFieldList) {
        for (TextInput textFieldInput : textFieldList) {
            //These two lines determine the value limit of a text field...if available. Some fields isn't given a value limit
            //But instead a Byte limit. However, this should take priority, if it exists
            String[] valueLimit = (String[]) textFieldInput.getTupleData().getValueOptions();
            int valueLimitLength = valueLimit.length;

            //This is to get the byteLimit. Almost all variables have a byte limit. If it is zero, then it is most likely
            //A reserved variable and that shouldn't be touched in the first place

            int byteLimit = (int) textFieldInput.getTupleData().getByteSize();


            textFieldInput.getTextField().textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    //TODO: Implement the change listener so that the textfield turns red whenever the user goes over the text limit
                    System.out.println(textFieldInput.getName() + " " + newValue);
                    /*
                        TODO: Implement the following:
                        For when valueLimit has a value
                        3) If it is NOT within value limit, then add the CSS Class for the field
                         //Note that you haven't handled the case where chars are entered into values that are meant to be int
                         //Or the opposite case where int is entered into a value that's meant to be a char
                         //Or negative numbers
                        4) If it is within the limit, remove the CSS class

                        For when valueLImit does NOT have a value (the else statement)
                        1) check the length of the newValue
                        2) If the newValue length exceeds the byteLimit, then throw an error

                     */
                    if (newValue.length() > 0) {
                        if (valueLimitLength > 1) {
                            int lowerLimit = Integer.parseInt(valueLimit[0]);
                            int upperLimit = Integer.parseInt(valueLimit[1]);
                            int newValueInt = 0;
                            try {
                                newValueInt = Integer.parseInt(newValue);
                                //Tests to see if the number is within the value limits
                                if (newValueInt < lowerLimit || newValueInt > upperLimit) {
                                    System.out.println("Warning: Limit exceeded for: " + textFieldInput.getLabel().getText());
                                    textFieldInput.addWarning();
                                } else {
                                    System.out.println("Within limit. Remove class");
                                    textFieldInput.clear();
                                }
                            }
                            //Reaching here means that a user entered in letters in a TF meant for numbers
                            catch (NumberFormatException e) {
                                textFieldInput.clear();
                                textFieldInput.addError();
                            }
                        }
                        //For length verification
                        else {
                            int newValueLength = newValue.length();
                            if (newValueLength > byteLimit) {
                                System.out.println("Warning: Byte Limit exceeded" + textFieldInput.getLabel().getText());
                                textFieldInput.addWarning();
                            } else {
                                System.out.println("Within byte limit");
                                textFieldInput.clear();
                            }
                        }
                    }
                    //Reaching here means the textfield itself is empty. Nothing should be thrown here
                    else if (newValue.length() <= 0) {
                        textFieldInput.clear();
                    }
                }
            });
        }
    }

    /**
     * This method will populate all non Common Variables into the GUI
     *
     * @param fieldMap  the Map variable of a Varexp Varialbe. THis is retrieved from a VarexpVariable's getFieldMap()
     * @param pane      This is a GridPane. This should either be commonViewLeftGridPane, commonViewRightGridPane, SourceGridPane or CommandGridPane
     * @param tfList    A list of TextInput classes. Class is defined as an inner class here
     * @param cbList    A list of ComboBox Classes. Class is defined as an inner class here
     * @param labelText This is the text that will be displayed
     */
    private void populateNonCommonVariables(Map<String, VarexpTuple> fieldMap, GridPane pane, List<TextInput> tfList, List<ComboBox> cbList, String labelText) {

        //Traverse its map for its keys and then using the key values, create text input as well as their labels.
        //Note that the fxid is the varexp position
        int columnCount = 1;

        //Removes everything from the GridPane. Chances are if you've reached here, you need to populate new items into
        //a gridpane

        //Remove all items from the GridPane
        pane.getChildren().clear();

        //Removes all items from the (Souce or COmmand) TextViewList and ComboBoxList
        tfList.clear();
        cbList.clear();

        //Create a header label. This will tell the user what Command or Source Variable they're working on

        Label headerLabel = new Label(labelText);

        headerLabel.getStyleClass().add("header");

        pane.add(headerLabel, 0, 0);


        for (String key : fieldMap.keySet()) {
            String widgetType = (String) fieldMap.get(key).getWidgetType();
            int position = (Integer) fieldMap.get(key).getPosition();
            Boolean visible = (Boolean) fieldMap.get(key).getVisibility();

            if (widgetType.equals(TEXT_FIELD) && visible) {
                TextInput input = new TextInput(
                        "" + position,
                        new Label(key),
                        new JFXTextField(),
                        (Integer) fieldMap.get(key).getPosition(),
                        fieldMap.get(key)
                );

                pane.add(input.getLabel(), 0, columnCount);
                pane.add(input.getTextField(), 1, columnCount);
                tfList.add(input);
            }
            //Create Combobox Item for common variable fields
            else if (widgetType.equals(COMBO_BOX) && visible) {
                ComboBox cb = new ComboBox(
                        "" + position,
                        new Label(key),
                        new JFXComboBox(),
                        (Integer) fieldMap.get(key).getPosition(),
                        fieldMap.get(key)
                );
                pane.add(cb.getLabel(), 0, columnCount);
                pane.add(cb.getComboBox(), 1, columnCount);
                cbList.add(cb);
            }
            columnCount++;
        }

        //Populate the comboboxes choices
        fillComboBoxChoices(cbList);

        // Apply text field listeners to all the textfields
        setTextFieldListener(tfList);
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

        //This method clears the CSS highlight for the textfield
        public void clear() {
            textField.getStyleClass().remove("warning");
            textField.getStyleClass().remove("error");
        }

        public void addError() {
            textField.getStyleClass().add("error");
        }

        public void addWarning() {
            textField.getStyleClass().add("warning");
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
