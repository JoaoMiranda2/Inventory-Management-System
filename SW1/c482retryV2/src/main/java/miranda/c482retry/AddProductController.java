package miranda.c482retry;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Node;
import java.util.Objects;
import javafx.fxml.FXML;
import java.net.URL;

/** The AddProductController class controls the behavior of the Add Product form in the Inventory Management System.
 *
 * @author Joao Marcelo Martins Miranda
 */

public class AddProductController implements Initializable {

    /**
     * The primary stage used for the user interface.
     */
    @FXML
    private Stage stage;

    /**
     * The scene associated with the user interface.
     */
    @FXML
    private Scene scene;


    /**
     * TableView for displaying parts.
     */
    @FXML
    public TableView<Part> partsTableView;

    /**
     * TableColumn for displaying part IDs in the parts table.
     */
    @FXML
    public TableColumn <Part, Integer> partIdCol;

    /**
     * TableColumn for displaying part names in the parts table.
     */
    @FXML
    public TableColumn <Part, String> partNameCol;

    /**
     * TableColumn for displaying part inventory levels in the parts table.
     */
    @FXML
    public TableColumn <Part, Integer> partInvCol;

    /**
     * TableColumn for displaying part prices in the parts table.
     */
    @FXML
    public TableColumn <Part, Double> partPriceCol;

    /**
     * TableView for displaying associated parts.
     */
    @FXML
    public TableView <Part> assocPartsTableView;

    /**
     * ObservableList for storing associated parts.
     */
    @FXML
    private ObservableList <Part> associatedParts = FXCollections.observableArrayList();

    /**
     * TableColumn for displaying associated part IDs.
     */
    @FXML
    public TableColumn <Part, Integer> idAssocPartsCol;

    /**
     * TableColumn for displaying associated part names.
     */
    @FXML
    public TableColumn <Part, String> nameAssocPartsCol;

    /**
     * TableColumn for displaying associated part inventory levels.
     */
    @FXML
    public TableColumn <Part, Integer> invAssocPartsCol;

    /**
     * TableColumn for displaying associated part prices.
     */
    @FXML
    public TableColumn <Part, Double> priceAssocPartsCol;


    /**
     * TextField for entering product inventory.
     */
    @FXML
    public TextField productInvTxt;

    /**
     * TextField for entering product maximum stock level.
     */
    @FXML
    public TextField productMaxTxt;

    /**
     * TextField for entering product minimum stock level.
     */
    @FXML
    public TextField productMinTxt;

    /**
     * TextField for entering product name.
     */
    @FXML
    public TextField productNameTxt;

    /**
     * TextField for entering product price.
     */
    @FXML
    public TextField productPriceTxt;

    /**
     * TextField for searching parts by ID or name.
     */
    @FXML
    public TextField partSearchId;
    private Alert alert;

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
     * Handles the action when the "Save Product" button is clicked.
     * Validates input fields, checks for errors, and adds a new product to the inventory.
     * Returns alerts if needed in order to assist with adding products.
     *
     * @param event The event triggered by clicking the "Save Product" button.
     * @throws IOException If there is an I/O error while loading the main form.
     */
    @FXML
    public void onActionSaveAddProduct(ActionEvent event) throws IOException {
        //if blank input
        if ((productNameTxt.getText().isEmpty()) || (productPriceTxt.getText().isEmpty()) || (productInvTxt.getText().isEmpty()) ||
                (productMinTxt.getText().isEmpty()) || (productMaxTxt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Adding Product");
            alert.setHeaderText("Error with Text Field (blank)");
            alert.setContentText("All text fields must be complete to add a part");
            alert.showAndWait();
        }
        else {
            try {
                //If inv is not numeric
                if (!numericValue(productInvTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with Inv Text Field");
                    alert.setContentText("Inv must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //Checks if input is accepted as a double
                if (!numericDouble(productPriceTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with Price/Cost");
                    alert.setContentText("Price/Cost must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If min is not numeric
                if (!numericValue(productMinTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with Min Text Field");
                    alert.setContentText("Min must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If max is not numeric
                if (!numericValue(productMaxTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with Max Text Field");
                    alert.setContentText("Max must only contain numeric values");
                    alert.showAndWait();
                    return;
                }

                if ((Integer.parseInt(productMinTxt.getText())) >= (Integer.parseInt(productMaxTxt.getText()))) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with Min, and Max values");
                    alert.setContentText("Min value must be lower than Max value");
                    alert.showAndWait();
                    return;
                }
                else if ((Integer.parseInt(productInvTxt.getText())) < (Integer.parseInt(productMinTxt.getText())) ||
                        (Integer.parseInt(productInvTxt.getText())) > (Integer.parseInt(productMaxTxt.getText()))) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Adding Product");
                    alert.setHeaderText("Error with the Inventory");
                    alert.setContentText("The Inventory number should be between the Min and Max values");
                    alert.showAndWait();
                    return;
                }
                Product product = new Product(
                        Inventory.productIndex, productNameTxt.getText(), Double.parseDouble(productPriceTxt.getText()),
                        Integer.parseInt(productInvTxt.getText()), Integer.parseInt(productMinTxt.getText()),
                        Integer.parseInt(productMaxTxt.getText()));
                Inventory.addProduct(product);

                for (int i = 0; i < associatedParts.size(); i++) {
                    product.addAssociatedPart(associatedParts.get(i));
                }

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Inventory Management System");
                stage.show();
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Adding Product");
                alert.setHeaderText("Mistake in one or more Text Fields (Invalid Input)");
                alert.setContentText("Check if inputs are correctly filled, correct invalid input to add product");
                alert.showAndWait();
            }
        }
    }

    /**
     * Adds a selected part to the list of associated parts for the product.
     *
     * @param event The event triggered by clicking the "Add" button.
     */
    @FXML
    public void onActionAddAssocPart(ActionEvent event) {

        Part part = partsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Adding Associated Part");
            alert.setHeaderText("Part Not selected");
            alert.setContentText("Select part and retry!");
            alert.showAndWait();
        }
        else {
            associatedParts.add(part);
            assocPartsTableView.setItems(associatedParts);
        }
    }


    /**
     * Removes a selected associated part from the list of associated parts for the product.
     *
     * @param event The event triggered by clicking the "Remove" button.
     */
    @FXML
    public void onActionRemoveAssocPart(ActionEvent event) {

        Part part = assocPartsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Removing Associated Part");
            alert.setHeaderText("Part Not selected");
            alert.setContentText("Select part and retry!");
            alert.showAndWait();
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);

            alert2.setTitle("Removing Associated Part");
            alert2.setHeaderText("Remove");
            alert2.setContentText("Are you sure about removing this part?");

            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {
                associatedParts.remove(part);
            }
            else {
            }
        }
    }


    /**
     * Searches for a part by its ID or name and updates the parts table with the search results.
     *
     * @param event The event triggered by clicking the "Search" button.
     */
    @FXML
    public void onActionSearchPartId(ActionEvent event) {

        String s = partSearchId.getText().toLowerCase();

        ObservableList<Part> part = Inventory.lookupPart(s);

        if (part.size() == 0) {
            try {
                int index = Integer.parseInt(s);
                Part part2 = Inventory.lookupPart(index);
                if (part2 != null) {
                    part.add(part2);
                }
                else {
                    alert = new Alert(Alert.AlertType.WARNING);

                    alert.setTitle("Searching Part");
                    alert.setHeaderText("No results!");
                    alert.setContentText("Retry with a valid Name or Part ID.");
                    alert.showAndWait();
                }
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Searching Part");
                alert.setHeaderText("No results!");
                alert.setContentText("Retry with a valid Name or Part ID.");
                alert.showAndWait();
            }
        }
        partsTableView.setItems(part);
    }

    /**
     * Switches to the main form view when the "Display Main Form" button is clicked.
     *
     * @param event The event triggered by clicking the "Display Main Form" button.
     * @throws IOException If there is an I/O error while loading the main form.
     */
    @FXML
    public void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * This method is called automatically by JavaFX.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        idAssocPartsCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameAssocPartsCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invAssocPartsCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceAssocPartsCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
