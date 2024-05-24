package miranda.c482retry;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.Objects;
import javafx.scene.Node;
import javafx.fxml.FXML;
import java.net.URL;

/**
 * Controller class for modifying products in the inventory management system.
 * Allows the user to edit and save details of a product.
 *
 * @author Joao Marcelo Martins Miranda
 *
 */

public class ModifyProductController implements Initializable {

    /**
     * An observable list to store associated parts for a product.
     */
    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * TableView for displaying associated parts.
     */
    @FXML
    public TableView<Part> assocPartsTableView;

    /**
     * TableColumn for displaying the ID of associated parts.
     */
    @FXML
    public TableColumn<Part, Integer> idAssocPartsCol;

    /**
     * TableColumn for displaying the inventory count of associated parts.
     */
    @FXML
    public TableColumn<Part, Integer> invAssocPartsCol;

    /**
     * TableColumn for displaying the name of associated parts.
     */
    @FXML
    public TableColumn<Part, String> nameAssocPartsCol;

    /**
     * TableColumn for displaying the ID of parts.
     */
    @FXML
    public TableColumn<Part, Integer> partIdCol;

    /**
     * TableColumn for displaying the inventory count of parts.
     */
    @FXML
    public TableColumn<Part, Integer> partInvCol;

    /**
     * TableColumn for displaying the name of parts.
     */
    @FXML
    public TableColumn<Part, String> partNameCol;

    /**
     * TableColumn for displaying the price of parts.
     */
    @FXML
    public TableColumn<Part, Double> partPriceCol;

    /**
     * Text field for searching parts by ID or name.
     */
    @FXML
    public TextField partSearchId;

    /**
     * TableView for displaying parts.
     */
    @FXML
    public TableView<Part> partsTableView;

    /**
     * TableColumn for displaying the price of associated parts.
     */
    @FXML
    public TableColumn<Part, Double> priceAssocPartsCol;

    /**
     * Text field for entering the product ID.
     */
    @FXML
    public TextField productIdTxt;

    /**
     * Text field for entering the product's inventory count.
     */
    @FXML
    public TextField productInvTxt;

    /**
     * Text field for entering the product's maximum quantity.
     */
    @FXML
    public TextField productMaxTxt;

    /**
     * Text field for entering the product's minimum quantity.
     */
    @FXML
    public TextField productMinTxt;


    /**
     * Text field for entering the product's name.
     */
    @FXML
    public TextField productNameTxt;

    /**
     * Text field for entering the product's price.
     */
    @FXML
    public TextField productPriceTxt;


    /**
     * Handles the action event for searching for a part by ID or name.
     * Displays a warning dialog if no results are found.
     *
     * @param event The action event triggered by clicking the "Search" button.
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
                    Alert alert = new Alert(Alert.AlertType.WARNING);

                    alert.setTitle("Searching Part");
                    alert.setHeaderText("No results!");
                    alert.setContentText("Retry with a valid Name or Part ID.");
                    alert.showAndWait();
                }
            }
            catch(NumberFormatException e) {
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
     * Sets the data of the selected product to the form fields for editing.
     *
     * @param product The product object to be edited.
     */
    @FXML
    public void productData(Product product) {
        productIdTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(String.valueOf(product.getStock()));
        productPriceTxt.setText(String.valueOf(product.getPrice()));
        productMinTxt.setText(String.valueOf(product.getMin()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        assocPartsTableView.setItems(product.getAllAssociatedParts());

        for(int i = 0; i < product.getAllAssociatedParts().size(); i++) {
            associatedParts.add(product.getAllAssociatedParts().get(i));
        }
    }

    /**
     * Handles the action event for adding an associated part to the product.
     * Displays an error dialog if no part is selected.
     *
     * @param event The action event triggered by clicking the "Add" button.
     */
    @FXML
    public void onActionAddAssocPart(ActionEvent event) {

        Part part = partsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Adding Associated Part");
            alert.setHeaderText("Part Not Selected");
            alert.setContentText("Select part and retry!");
            alert.showAndWait();
        }
        else {
            associatedParts.add(part);
            assocPartsTableView.setItems(associatedParts);
        }
    }


    /**
     * Handles the action event for removing an associated part from the product.
     * Displays a confirmation dialog before deletion.
     *
     * @param event The action event triggered by clicking the "Remove" button.
     */
    @FXML
    public void onActionRemovePart(ActionEvent event) {
        Part part = assocPartsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

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
                assocPartsTableView.setItems(associatedParts);
            }
            else {
            }
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

    public boolean numericDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles the action event for saving the modified product.
     * Validates input fields, updates the product, and returns to the main form.
     * Returns alerts if needed in order to assist with modifying products.
     *
     * @param event The action event triggered by clicking the "Save" button.
     * @throws IOException If an I/O error occurs while loading the main form.
     */
    @FXML
    public void onActionSaveAddProduct(ActionEvent event) throws IOException {
        //if blank input
        if ((productNameTxt.getText().isEmpty()) || (productPriceTxt.getText().isEmpty()) || (productInvTxt.getText().isEmpty()) ||
                (productMinTxt.getText().isEmpty()) || (productMaxTxt.getText().isEmpty())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Modifying Part");
            alert.setHeaderText("Error with Text Field (blank)");
            alert.setContentText("All text fields must be complete to add a product");
            alert.showAndWait();
        }

        else {
            try {
                //If inv is not numeric
                if (!numericValue(productInvTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Product");
                    alert.setHeaderText("Error with Inv Text Field");
                    alert.setContentText("Inv must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //Checks if input is accepted as a double
                if (!numericDouble(productPriceTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Product");
                    alert.setHeaderText("Error with Price/Cost");
                    alert.setContentText("Price/Cost must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If min is not numeric
                if (!numericValue(productMinTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Product");
                    alert.setHeaderText("Error with Min Text Field");
                    alert.setContentText("Min must only contain numeric values");
                    alert.showAndWait();
                    return;
                }
                //If max is not numeric
                if (!numericValue(productMaxTxt.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying ProductPart");
                    alert.setHeaderText("Error with Max Text Field");
                    alert.setContentText("Max must only contain numeric values");
                    alert.showAndWait();
                    return;
                }

                if ((Integer.parseInt(productMinTxt.getText())) >= (Integer.parseInt(productMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Product");
                    alert.setHeaderText("Error with Min, and Max values");
                    alert.setContentText("Min value must be lower than Max value");
                    alert.showAndWait();
                    return;
                }
                else if ((Integer.parseInt(productInvTxt.getText())) < (Integer.parseInt(productMinTxt.getText())) ||
                        (Integer.parseInt(productInvTxt.getText())) > (Integer.parseInt(productMaxTxt.getText()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Modifying Product");
                    alert.setHeaderText("Error with the Inventory");
                    alert.setContentText("The Inventory number should be between the Min and Max values");
                    alert.showAndWait();
                    return;
                }
                Product product = new Product(Integer.parseInt(productIdTxt.getText()), productNameTxt.getText(), Double.parseDouble(productPriceTxt.getText()),
                        Integer.parseInt(productInvTxt.getText()), Integer.parseInt(productMinTxt.getText()), Integer.parseInt(productMaxTxt.getText()));
                Inventory.updateProduct(Integer.parseInt(productIdTxt.getText()), product);
                for (int i = 0; i < associatedParts.size(); i++) {
                    product.addAssociatedPart(associatedParts.get(i));
                }
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Inventory Management System");
                stage.show();
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Modifying Product");
                alert.setHeaderText("Mistake in one or more Text Fields (Invalid Input)");
                alert.setContentText("Check if inputs are correctly filled, correct invalid input to add product");
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
    public void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();
    }

    /**
     * Initializes the controller.
     *
     * @param url           The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resource bundle that was loaded with this root object, or null if there is no resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets the data inside the tables
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

