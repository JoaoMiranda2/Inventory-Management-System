package miranda.c482retry;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Node;
import java.util.Objects;
import javafx.fxml.FXML;
import java.net.URL;

/**
 * The MainFormController class is responsible for controlling the main form of the Inventory Management System.
 * It handles actions such as adding, modifying, and deleting parts and products, as well as searching for parts and products.
 * This controller initializes and populates the parts and products tables with data and provides functionality for user interactions.
 *
 * @author Joao Marcelo Martins Miranda
 *
 */

public class MainFormController implements Initializable {


    /**
     * The JavaFX stage for the application.
     */
    @FXML
    private Stage stage;

    /**
     * The JavaFX scene for the application.
     */
    @FXML
    private Scene scene;

    /**
     * Table view for displaying parts.
     */
    @FXML
    public TableView<Part> partsTableView;

    /**
     * Column for displaying part IDs.
     */
    @FXML
    public TableColumn <Part, Integer> partIdCol;

    /**
     * Column for displaying part names.
     */
    @FXML
    public TableColumn <Part, String> partNameCol;

    /**
     * Column for displaying part prices.
     */
    @FXML
    public TableColumn <Part, Double> partPriceCol;

    /**
     * Column for displaying part inventory counts.
     */
    @FXML
    public TableColumn <Part, Integer> partInvCol;

    /**
     * Table view for displaying products.
     */
    @FXML
    public TableView<Product> productsTableView;

    /**
     * Column for displaying product IDs.
     */
    @FXML
    public TableColumn <Part, Integer> productIdCol;

    /**
     * Column for displaying product names.
     */
    @FXML
    public TableColumn <Part, String> productNameCol;

    /**
     * Column for displaying product prices.
     */
    @FXML
    public TableColumn <Part, Double> productPriceCol;

    /**
     * Column for displaying product inventory counts.
     */
    @FXML
    public TableColumn <Part, Integer> productInvCol;

    /**
     * Text field for searching parts by ID or name.
     */
    @FXML
    public TextField partSearchId;

    /**
     * Text field for searching products by ID or name.
     */
    @FXML
    public TextField productSearchId;
    /**
     * Handles the action event triggered by clicking the "Search" button for parts.
     * Searches for a part by name or ID and updates the parts table accordingly.
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
        //sets the data in parts table
        partsTableView.setItems(part);
    }

    /**
     * Opens the "Add Part" form when the corresponding button is clicked.
     *
     * @param event The action event triggered by clicking the "Add Part" button.
     * @throws IOException If an I/O error occurs while loading the "Add Part" form.
     */
    @FXML
    public void onActionAddPartForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPart.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Part Form");
        stage.show();
    }

    /**
     * Opens the "Modify Part" form with the selected part's data preloaded when the "Modify" button is clicked.
     *
     * @param event The action event triggered by clicking the "Modify" button.
     * @throws IOException If an I/O error occurs while loading the "Modify Part" form.
     */
    @FXML
    public void onActionModPart(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        loader.load();

        ModifyPartController modifyPartController = loader.getController();

        Part part = partsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Modifying Part");
            alert.setHeaderText("Part Not Selected");
            alert.setContentText("Select a part and retry!");
            alert.showAndWait();
        }
        else {
            //passing selected part to ModifyPartController
            modifyPartController.partData(part);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.setTitle("Modify Part Form");
            stage.show();
        }
    }

    /**
     * Deletes the selected part from the parts table and asks for confirmation before deletion.
     *
     * @param event The action event triggered by clicking the "Delete" button.
     * @throws IOException If an I/O error occurs during part deletion.
     */
    @FXML
    public void onActionDeletePart(ActionEvent event) throws IOException {

        Part part = partsTableView.getSelectionModel().getSelectedItem();

        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Deleting a Part");
            alert.setHeaderText("Part Not Selected");
            alert.setContentText("Select a part and retry!");
            alert.showAndWait();
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);

            alert2.setTitle("Deleting Part");
            alert2.setHeaderText("Delete");
            alert2.setContentText("Are you sure that you want to delete this part?");

            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {
                Inventory.deletePart(part);
            } else {
            }
        }
    }

    /**
     * Handles the action event triggered by clicking the "Search" button for products.
     * Searches for a product by name or ID and updates the products table accordingly.
     *
     * @param event The action event triggered by clicking the "Search" button.
     */
    @FXML
    public void onActionSearchProductId(ActionEvent event){

        String s = productSearchId.getText().toLowerCase();

        ObservableList<Product> product = Inventory.lookupProduct(s);

        if (product.size() == 0) {
            try {
                int index = Integer.parseInt(s);
                Product product2 = Inventory.lookupProduct(index);
                if (product2 != null) {
                    product.add(product2);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);

                    alert.setTitle("Searching Product");
                    alert.setHeaderText("No results!");
                    alert.setContentText("Retry with a valid Name or Product ID.");
                    alert.showAndWait();
                }
            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Searching Product");
                alert.setHeaderText("No results!");
                alert.setContentText("Retry with a valid Name or Product ID.");
                alert.showAndWait();
            }
        }
        productsTableView.setItems(product);
    }

    /**
     * Opens the "Add Product" form when the corresponding button is clicked.
     *
     * @param event The action event triggered by clicking the "Add Product" button.
     * @throws IOException If an I/O error occurs while loading the "Add Product" form.
     */
    @FXML
    public void onActionAddProductForm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddProduct.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Product Form");
        stage.show();
    }



    /**
     * Opens the "Modify Product" form with the selected product's data preloaded when the "Modify" button is clicked.
     *
     * @param event The action event triggered by clicking the "Modify" button.
     * @throws IOException If an I/O error occurs while loading the "Modify Product" form.
     */
    @FXML
    public void onActionModProductForm(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        loader.load();

        ModifyProductController modifyProductController = loader.getController();

        Product product = productsTableView.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Modifying Product");
            alert.setHeaderText("Product Not Selected");
            alert.setContentText("Select a product and retry!");
            alert.showAndWait();
        }
        else {
            modifyProductController.productData(product);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.setTitle("Modify Product Form");
            stage.show();
        }
    }


    /**
     * Deletes the selected product from the products table and asks for confirmation before deletion.
     * Checks if the product has associated parts and prevents deletion if it does.
     *
     * @param event The action event triggered by clicking the "Delete" button.
     * @throws IOException If an I/O error occurs during product deletion.
     */
    @FXML
    public void onActionDeleteProductForm(ActionEvent event) throws IOException {

        Product product = productsTableView.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Deleting Product");
            alert.setHeaderText("Product Not Selected");
            alert.setContentText("Select a product and retry!");
            alert.showAndWait();
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);

            alert2.setTitle("Deleting Product");
            alert2.setHeaderText("Delete");
            alert2.setContentText("Are you sure that you want to delete this product?");

            Optional<ButtonType> result = alert2.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                if (product.getAllAssociatedParts().size() == 0) {
                    Inventory.deleteProduct(product);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Deleting Product");
                    alert.setHeaderText("Can't Delete Product With Parts Already Associated.");
                    alert.setContentText("Remove all associated parts before deleting product!");
                    alert.showAndWait();
                }
            }
            else {
            }
        }
    }

    private static boolean firstTime = true;


    /**
     * Initializes and adds test data to the parts and products tables.
     * This method is called when the application starts to populate the initial data.
     */
    private void addTestData() {
        if(!firstTime) {
            return;
        }
        firstTime = false;

        InHouse inHousePart1 = new InHouse(Inventory.partIndex, "RGB Lighting", 13.99, 9, 2, 99, 42069);
        Inventory.addPart(inHousePart1);

        Outsourced outsourcedPart1 = new Outsourced(Inventory.partIndex, "Graphics Card", 350.49, 6, 1, 1000, "Gigabyte");
        Inventory.addPart(outsourcedPart1);

        Outsourced outsourcedPart2 = new Outsourced(Inventory.partIndex, "Motherboard", 160.99, 56, 1, 100, "MSI");
        Inventory.addPart(outsourcedPart2);

        Product product1 = new Product(Inventory.productIndex, "Personal Computer", 1100, 15, 1, 35);
        Inventory.addProduct(product1);

        Product product2 = new Product(Inventory.productIndex, "Lighting", 49.99, 25, 1, 35);
        Inventory.addProduct(product2);

        Product product3 = new Product(Inventory.productIndex, "Laptop", 412.99, 9, 1, 16);
        Inventory.addProduct(product3);
    }


    /**
     * Initializes the main form when the application starts.
     * Populates the parts and products tables with data and sets up table columns.
     *
     * @param url            The URL used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resource bundle that was loaded with this root object, or null if there is no resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addTestData();

        partsTableView.setItems(Inventory.getAllParts());
        productsTableView.setItems(Inventory.getAllProducts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Closes the application when the "Exit" button is clicked.
     *
     * @param event The action event triggered by clicking the "Exit" button.
     * @throws IOException If an I/O error occurs while closing the application.
     */
    @FXML
    public void onActionExit(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}