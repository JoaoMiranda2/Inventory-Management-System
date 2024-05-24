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
import java.util.Objects;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.net.URL;

/**
 * Controller class for modifying a part in the inventory management system.
 * Allows the user to edit and save details of a part.
 *
 * @author Joao Marcelo Martins Miranda
 *
 */

public class ModifyPartController implements Initializable {
    /**
     * The JavaFX stage for the application.
     */
    @FXML
    private Stage stage;

    /**
     * A label to display either "Machine ID" or "Company Name" based on the radio button selected.
     */
    @FXML
    public Label IdOrName;

    /**
     * Text field for entering the part ID.
     */
    @FXML
    public TextField partIdTxt;

    /**
     * Radio button for selecting an In-House part.
     */
    @FXML
    public RadioButton modPartInRBtn;

    /**
     * Radio button for selecting an Outsourced part.
     */
    @FXML
    public RadioButton modPartOutRBtn;

    /**
     * Text field for entering the part's inventory count.
     */
    @FXML
    public TextField partInventoryTxt;

    /**
     * Text field for entering the machine ID (for In-House parts).
     */
    @FXML
    public TextField partMachineIdTxt;

    /**
     * Text field for entering the maximum quantity for the part.
     */
    @FXML
    public TextField partMaxTxt;

    /**
     * Text field for entering the minimum quantity for the part.
     */
    @FXML
    public TextField partMinTxt;

    /**
     * Text field for entering the part's name.
     */
    @FXML
    public TextField partNameTxt;

    /**
     * Text field for entering the part's price.
     */
    @FXML
    public TextField partPriceTxt;

    /**
     * The JavaFX scene for the application.
     */
    @FXML
    private Scene scene;



    /**
     * Handles the action event when the In-House radio button is selected.
     * Changes the label to "Machine ID."
     *
     * @param event The action event triggered by selecting the In-House radio button.
     */
    @FXML
    public void onActionInHouseRadio(ActionEvent event) {
        IdOrName.setText("Machine Id");
    }


    /**
     * Handles the action event when the Outsourced radio button is selected.
     * Changes the label to "Company Name."
     *
     * @param event The action event triggered by selecting the Outsourced radio button.
     */
    @FXML
    public void onActionOutHouseRadio(ActionEvent event) {
        IdOrName.setText("Company Name");
    }


    /**
     * Sets the data of the selected part to the form fields for editing.
     *
     * @param part The part object to be edited.
     */
    @FXML
    public void partData(Part part) {

        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partInventoryTxt.setText(String.valueOf(part.getStock()));
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partMinTxt.setText(String.valueOf(part.getMin()));
        partMaxTxt.setText(String.valueOf(part.getMax()));

        if(part instanceof InHouse) {
            modPartInRBtn.setSelected(true);
            partMachineIdTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
            IdOrName.setText("Machine ID");
        }
        else {
            modPartOutRBtn.setSelected(true);
            partMachineIdTxt.setText(String.valueOf(((Outsourced) part).getCompanyName()));
            IdOrName.setText("Company Name");
        }
    }

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
     * Handles the action event for saving the modified part.
     * Validates input fields, updates the part, and returns to the main form.
     * Returns alerts if needed in order to assist with modifying parts.
     *
     * @param event The action event triggered by clicking the "Save" button.
     * @throws IOException If an I/O error occurs while loading the main form.
     */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {
        //if blank input
        if ((partNameTxt.getText().isEmpty()) || (partPriceTxt.getText().isEmpty()) || (partInventoryTxt.getText().isEmpty()) ||
                (partMinTxt.getText().isEmpty()) || (partMaxTxt.getText().isEmpty()) || (partMachineIdTxt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Modifying Part");
            alert.setHeaderText("Error with Text Field (blank)");
            alert.setContentText("All text fields must be complete to add a part");
            alert.showAndWait();
        }
        else {
            try {
                //If inv is not numeric
                if (!numericValue(partInventoryTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error with Inv Text Field");
                    alert.setContentText("Inv must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //Checks if input is accepted as a double
                if (!numericDouble(partPriceTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error with Price/Cost");
                    alert.setContentText("Price/Cost must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If min is not numeric
                if (!numericValue(partMinTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error with Min Text Field");
                    alert.setContentText("Min must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If max is not numeric
                if (!numericValue(partMaxTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error with Max Text Field");
                    alert.setContentText("Max must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                if ((Integer.parseInt(partMinTxt.getText())) >= (Integer.parseInt(partMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error with Min, and Max values");
                    alert.setContentText("Min value must be lower than Max value");
                    alert.showAndWait();
                    return;
                }
                else if ((Integer.parseInt(partInventoryTxt.getText())) < (Integer.parseInt(partMinTxt.getText())) ||
                        (Integer.parseInt(partInventoryTxt.getText())) > (Integer.parseInt(partMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Part");
                    alert.setHeaderText("Error in the Inventory");
                    alert.setContentText("The Inventory count should be between the Min and Max values");
                    alert.showAndWait();
                    return;
                }
                else if (modPartInRBtn.isSelected()) {
                    InHouse inHousePart = new InHouse(Integer.parseInt(partIdTxt.getText()), partNameTxt.getText(),
                            Double.parseDouble(partPriceTxt.getText()), Integer.parseInt(partInventoryTxt.getText()), Integer.parseInt(partMinTxt.getText()),
                            Integer.parseInt(partMaxTxt.getText()), Integer.parseInt(partMachineIdTxt.getText()));

                    Inventory.updatePart(Integer.parseInt(partIdTxt.getText()), inHousePart);

                } else if (modPartOutRBtn.isSelected()) {
                    Outsourced outSourcedPart = new Outsourced(Integer.parseInt(partIdTxt.getText()), partNameTxt.getText(),
                            Double.parseDouble(partPriceTxt.getText()), Integer.parseInt(partInventoryTxt.getText()), Integer.parseInt(partMinTxt.getText()),
                            Integer.parseInt(partMaxTxt.getText()), partMachineIdTxt.getText());

                    Inventory.updatePart(Integer.parseInt(partIdTxt.getText()), outSourcedPart);
                }

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Inventory Management System");
                stage.show();


            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Modifying Part");
                alert.setHeaderText("Mistake in one or more Text Fields (Invalid Input)");
                alert.setContentText("If In-House is selected, Machine ID can only contain numeric values! ");
                alert.showAndWait();
            }
        }

    }

    /**
     * Handles the action event for displaying the main form.
     *
     * @param event The action event triggered by clicking the "Back" button.
     * @throws IOException If an I/O error occurs while loading the main form.
     */
    @FXML
    public void  onActionDisplayMainForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();

    }

    /**
     * Initializes the controller.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resource bundle that was loaded with this root object, or null if there is no resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
