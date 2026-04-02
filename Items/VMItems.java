package Items;

/**
 * Represents an item in the vending machine with a name, item code, and price.
 */
public class VMItems {

    private final String name; 
    private final String itemCode;
    private final int price;

    /**
     * Constructs a new VMItems instance with the specified name, item code, and price.
     *
     * @param name     the name of the item
     * @param itemCode the unique code representing the item
     * @param price    the price of the item in pence
     */
    public VMItems(String name, String itemCode, int price) {
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the code of the item.
     *
     * @return the unique code of the item
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * Returns the price of the item.
     * Note: The price is in pence, where 150 represents £1.50 and 75 represents £0.75.
     *
     * @return the price of the item in pence
     */
    public int getPrice() {
        return this.price;
    }
}