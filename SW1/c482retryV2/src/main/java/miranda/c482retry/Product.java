package miranda.c482retry;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * The Product class represents a product in the inventory management system.
 * It includes information such as ID, name, price, stock quantity, minimum stock, and maximum stock.
 * Products can have associated parts, which are stored in an observable list.
 *
 * @author Joao Marcelo Martins Miranda
 */

public class Product {
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructs a new Product with the specified attributes.
     *
     * @param id     The unique identifier of the product.
     * @param name   The name of the product.
     * @param price  The price of the product.
     * @param stock  The current stock quantity of the product.
     * @param min    The minimum stock quantity required for the product.
     * @param max    The maximum stock quantity allowed for the product.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Retrieves the unique identifier of the product.
     *
     * @return The product's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the product.
     *
     * @param id The new ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the current stock quantity of the product.
     *
     * @return The product's stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the current stock quantity of the product.
     *
     * @param stock The new stock quantity to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retrieves the minimum stock quantity required for the product.
     *
     * @return The product's minimum stock quantity.
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets the minimum stock quantity required for the product.
     *
     * @param min The new minimum stock quantity to set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Retrieves the maximum stock quantity allowed for the product.
     *
     * @return The product's maximum stock quantity.
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the maximum stock quantity allowed for the product.
     *
     * @param max The new maximum stock quantity to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds an associated part to the product.
     *
     * @param part The part to add as an associated part.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes an associated part from the product.
     *
     * @param selectedAssociatedPart The associated part to remove.
     */
    public void deleteAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Retrieves a list of all associated parts for the product.
     *
     * @return An observable list of associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
