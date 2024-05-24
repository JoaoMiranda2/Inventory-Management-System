package miranda.c482retry;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * The Inventory class manages the parts and products in the inventory system.
 * It provides methods for adding, looking up, updating, and deleting parts and products.
 *
 * @author Joao Marcelo Martins Miranda
 *
 */

public class Inventory {

    /**
     * The current index for parts.
     */
    public static int partIndex;

    /**
     * The current index for products.
     */
    public static int productIndex;


    /**
     * An observable list that stores all available parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list that stores all available products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Adds a new part to the inventory.
     *
     * @param newPart The new part to add.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
        partIndex++;
    }

    /**
     * Adds a new product to the inventory.
     *
     * @param newProduct The new product to add.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
        productIndex++;
    }

    /**
     * Looks up a part by its ID.
     *
     * @param partId The ID of the part to look up.
     * @return The part with the specified ID, or null if not found.
     */
    public static Part lookupPart(int partId) {
        Part temp = null;
        for (Part part : allParts) {
            if (partId == part.getId()) {
                temp = part;
            }
        }
        return temp;
    }

    /**
     * Looks up a product by its ID.
     *
     * @param productId The ID of the product to look up.
     * @return The product with the specified ID, or null if not found.
     */
    public static Product lookupProduct(int productId) {
        Product temp = null;
        for (Product product : allProducts)
            if (productId == product.getId()) {
                temp = product;
            }
        return temp;
    }

    /**
     * Searches for parts by name and returns a list of matching parts.
     *
     * @param searchName The name to search for.
     * @return A list of parts matching the search criteria.
     */
    public static ObservableList lookupPart(String searchName) {
        ObservableList<Part> foundPart = FXCollections.observableArrayList();

        if (searchName.isEmpty()) foundPart = allParts;
        else {
            for (Part thePart : allParts)
                if (thePart.getName().toLowerCase().contains(searchName.toLowerCase())) {
                    foundPart.add(thePart);
                }
        }
        return foundPart;
    }

    /**
     * Searches for products by name and returns a list of matching products.
     *
     * @param searchName The name to search for.
     * @return A list of products matching the search criteria.
     */
    public static ObservableList<Product> lookupProduct(String searchName) {
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();

        if (searchName.isEmpty()) {
            foundProduct = allProducts;
        } else for (Product theProduct : allProducts)
            if (theProduct.getName().toLowerCase().contains(searchName.toLowerCase())) {
                foundProduct.add(theProduct);
            }
    return foundProduct;
}

    /**
     * Updates an existing part in the inventory.
     *
     * @param index         The ID of the part to update.
     * @param selectedPart  The updated part information.
     */
    public static void updatePart(int index,Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == index) {
                getAllParts().set(i, selectedPart);
                return;
            }
        }
    }

    /**
     * Updates an existing product in the inventory.
     *
     * @param index             The ID of the product to update.
     * @param selectedProduct   The updated product information.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == index) {
                getAllProducts().set(i, selectedProduct);
                return;
            }
        }
    }

    /**
     * Deletes a part from the inventory.
     *
     * @param selectedPart The part to delete.
     * @return True if the part was successfully deleted, false otherwise.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes a product from the inventory.
     *
     * @param selectedProduct The product to delete.
     * @return True if the product was successfully deleted, false otherwise.
     */
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * Gets a list of all parts in the inventory.
     *
     * @return An observable list of all parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of all products in the inventory.
     *
     * @return An observable list of all products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
