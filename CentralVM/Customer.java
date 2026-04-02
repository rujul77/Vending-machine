package CentralVM;

import Interfaces.CustomerAPI;
import BritishCoins.Coin;

/**
 * The Customer class represents a customer interacting with the vending machine.
 * It implements the CustomerAPI interface to provide customer functionalities such as
 * inserting coins, selecting items, confirming purchases, and canceling transactions.
 */
public class Customer implements CustomerAPI {

    private VendingMachine vendingMachine;

    /**
     * Constructs a Customer with a specified vending machine to interact with.
     *
     * @param vendingMachine the vending machine the customer interacts with
     */
    public Customer(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;   
    }

    /**
     * Inserts a coin into the vending machine.
     *
     * @param coin the coin to be inserted
     */
    @Override
    public void insertCoin(Coin coin) {
        this.vendingMachine.insertCoin(coin);
    }

    /**
     * Confirms the purchase of the selected item.
     */
    @Override
    public void confirmPurchase() {
        this.vendingMachine.confirmPurchase();
    }

    /**
     * Selects an item in the vending machine by its code.
     *
     * @param code the code of the item to be selected
     */
    @Override
    public void selectItem(String code) {
        this.vendingMachine.selectItem(code);
    }

    /**
     * Cancels the current transaction and refunds any inserted funds.
     */
    @Override
    public void cancelAndRefund() {
        this.vendingMachine.cancelAndRefund();
    }
}

