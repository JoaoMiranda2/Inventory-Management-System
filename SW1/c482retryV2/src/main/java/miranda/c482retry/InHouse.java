package miranda.c482retry;

/**
 * The InHouse class represents a part that is manufactured in-house.
 * It extends the Part class and includes an additional attribute for the machine ID.
 *
 * @author Joao Marcelo Martins Miranda
 */

public class InHouse extends Part {
    private int machineID;

    /**
     * Constructs an InHouse part with the specified attributes.
     *
     * @param id       The unique identifier for the part.
     * @param name     The name or description of the part.
     * @param price    The price of the part.
     * @param stock    The current stock level of the part.
     * @param min      The minimum allowed stock level for the part.
     * @param max      The maximum allowed stock level for the part.
     * @param machineID The ID of the machine used to manufacture the part.
     */
    public InHouse(int id, String name, double price, int stock,int min, int max, int machineID) {
        super(id,name,price,stock,min,max);
        this.machineID = machineID;
    }

    /**
     * Gets the machine ID associated with this in-house part.
     *
     * @return The machine ID.
     */
    public int getMachineId() {
        return machineID;
    }

    /**
     * Sets the machine ID associated with this in-house part.
     *
     * @param machineID The machine ID to set.
     */
    public void setMachineId(int machineID) {
        this.machineID = machineID;
    }
}