package miranda.c482retry;


/**
 * The Outsourced class represents a part that is obtained from an external company.
 * It is a subclass of the Part class and includes information about the company name.
 *
 * @author Joao Marcelo Martins Miranda
 */

public class Outsourced extends Part {
    /**
     * The name of the company from which the part is outsourced.
     */
    private String companyName;

    /**
     * Constructs a new Outsourced part with the specified attributes.
     *
     * @param id           The unique identifier of the part.
     * @param name         The name of the part.
     * @param price        The price of the part.
     * @param stock        The current stock quantity of the part.
     * @param min          The minimum stock quantity required for the part.
     * @param max          The maximum stock quantity allowed for the part.
     * @param companyName  The name of the external company that provides this part.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock,min,max);
        this.companyName = companyName;
    }

    /**
     * Retrieves the name of the external company that provides this part.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the name of the external company that provides this part.
     *
     * @param companyName The new company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
