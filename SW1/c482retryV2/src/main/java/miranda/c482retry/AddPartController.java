package miranda.c482retry;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.Objects;
import javafx.fxml.FXML;
import java.net.URL;

/** The AddPartController class controls the behavior of the Add Part form in the Inventory Management System.
 *
 * @author Joao Marcelo Martins Miranda
 */

public class AddPartController implements Initializable {


    /**
     * The RadioButton for adding an In-House part.
     */
    @FXML
    public RadioButton addPartInRBtn;

    /**
     * The RadioButton for adding an Outsourced part.
     */
    @FXML
    public RadioButton addPartOutRBtn;

    /**
     * The TextField for entering the part's ID.
     */
    @FXML
    public TextField partIdTxt;

    /**
     * The TextField for entering the part's inventory level.
     */
    @FXML
    public TextField partInventoryTxt;

    /**
     * The TextField for entering the part's machine ID (for InHouse parts).
     */
    @FXML
    public TextField partMachineIdTxt;

    /**
     * The TextField for entering the maximum stock level for the part.
     */
    @FXML
    public TextField partMaxTxt;

    /**
     * The TextField for entering the minimum stock level for the part.
     */
    @FXML
    public TextField partMinTxt;

    /**
     * The TextField for entering the part's name.
     */
    @FXML
    public TextField partNameTxt;

    /**
     * The TextField for entering the part's price.
     */
    @FXML
    public TextField partPriceTxt;

    /**
     * The Label used to display either "Machine Id" or "Company Name" based on the part type.
     */
    @FXML
    public Label IdOrName;

    /**
     * The Stage used for the user interface.
     */
    @FXML
    private Stage stage;

    /**
     * Checks if a given string can be parsed into an integer.
     *
     * @param str The string to be checked for numeric content.
     * @return {@code true} if the string contains a valid integer, {@code false} otherwise.
     */
    public boolean numericValue(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a given string can be parsed into a double.
     *
     * @param str The string to be checked for double content.
     * @return {@code true} if the string contains a valid double, {@code false} otherwise.
     */
    public boolean numericDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles the action when the "Save Part" button is clicked.
     * Validates input fields, checks for errors, and adds a new part to the inventory.
     * Returns alerts if needed in order to assist with adding parts.
     *
     * @param event The event triggered by clicking the "Save Part" button.
     * @throws IOException If there is an I/O error while loading the main form.
     */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        //if blank input
        if ((partNameTxt.getText().isEmpty()) || (partPriceTxt.getText().isEmpty()) || (partInventoryTxt.getText().isEmpty()) ||
                (partMinTxt.getText().isEmpty()) || (partMaxTxt.getText().isEmpty()) || (partMachineIdTxt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Adding Part");
            alert.setHeaderText("Error with Text Field (blank)");
            alert.setContentText("All text fields must be complete to add a part");
            alert.showAndWait();
        }
        else {
            try {
                //If inv is not numeric
                if (!numericValue(partInventoryTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with Inv Text Field");
                    alert.setContentText("Inv must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //Checks if input is accepted as a double
                if (!numericDouble(partPriceTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with Price/Cost");
                    alert.setContentText("Price/Cost must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If min is not numeric
                if (!numericValue(partMinTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with Min Text Field");
                    alert.setContentText("Min must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If max is not numeric
                if (!numericValue(partMaxTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with Max Text Field");
                    alert.setContentText("Max must only contain numeric values");
                    alert.showAndWait();
                    return;
                }


                //check if inputed values are obeying the min is less than max
                if ((Integer.parseInt(partMinTxt.getText())) >= (Integer.parseInt(partMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with Min, and Max values");
                    alert.setContentText("Min value must be lower than Max value");
                    alert.showAndWait();
                    return;
                }
                //Check inv value is less than greater than min AND less than max
                else if ((Integer.parseInt(partInventoryTxt.getText())) < (Integer.parseInt(partMinTxt.getText())) ||
                        (Integer.parseInt(partInventoryTxt.getText())) > (Integer.parseInt(partMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Part");
                    alert.setHeaderText("Error with the Inventory");
                    alert.setContentText("The Inventory number should be between the Min and Max values");
                    alert.showAndWait();
                    return;
                }
                else if (addPartInRBtn.isSelected()) {
                    InHouse inHousePart = new InHouse(Inventory.partIndex, partNameTxt.getText(), Double.parseDouble(partPriceTxt.getText()),
                            Integer.parseInt(partInventoryTxt.getText()), Integer.parseInt(partMinTxt.getText()), Integer.parseInt(partMaxTxt.getText()),
                            Integer.parseInt(partMachineIdTxt.getText()));
                    Inventory.addPart(inHousePart);
                }
                else if (addPartOutRBtn.isSelected()) {
                    Outsourced outsourcedPart = new Outsourced(Inventory.partIndex, partNameTxt.getText(), Double.parseDouble(partPriceTxt.getText()),
                            Integer.parseInt(partInventoryTxt.getText()), Integer.parseInt(partMinTxt.getText()), Integer.parseInt(partMaxTxt.getText()),
                            partMachineIdTxt.getText());
                    Inventory.addPart(outsourcedPart);
                }
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Inventory Management System");
                stage.show();

            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Adding Part");
                alert.setHeaderText("Mistake in one or more Text Fields (Invalid Input)");
                alert.setContentText("If In-House is selected, Machine ID can only contain numeric values!");
                alert.showAndWait();
            }
        }
    }


    /**
     * Switches to the main form view when the "Display Main Form" button is clicked.
     * @param event The event triggered by clicking the "Display Main Form" button.
     * @throws IOException If there is an I/O error while loading the main form.
     */
    @FXML
    public void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();

    }

    /**
     * Switches the label text to "Machine ID" when the "In-House" radio button is selected.
     * @param event The event triggered by selecting the "In-House" radio button.
     */
    @FXML
    public void onActionInHouseRadio(ActionEvent event) {
        IdOrName.setText("Machine ID");
    }

    /**
     * Switches the label text to "Company Name" when the "Outsourced" radio button is selected.
     * @param event The event triggered by selecting the "Outsourced" radio button.
     */
    @FXML
    public void onActionOutRadio(ActionEvent event) {
        IdOrName.setText("Company Name");
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * This method is called automatically by JavaFX.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}
