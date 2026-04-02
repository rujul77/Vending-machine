package Interfaces;

import BritishCoins.Coin;

/**
 * The CustomerAPI interface provides methods for customer interactions with the vending machine, 
 * including inserting coins, selecting an item, confirming a purchase, and canceling transactions.
 */
public interface CustomerAPI {

    /**
     * Inserts a coin into the vending machine for an upcoming purchase.
     *
     * @param coin the coin to be inserted into the machine
     */
    public void insertCoin(Coin coin);

    /**
     * Selects an item by its code, typically displaying the item's price for customer confirmation.
     *
     * @param code the unique code representing the item to be selected
     */
    public void selectItem(String code);

    /**
     * Completes the transaction for the selected item, deducting the cost from the balance.
     */
    public void confirmPurchase();

    /**
     * Cancels the current transaction and returns any inserted funds to the customer.
     */
    public void cancelAndRefund();
}
