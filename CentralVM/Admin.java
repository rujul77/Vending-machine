package CentralVM;

import Interfaces.AdminAPI;
import BritishCoins.Coin;
import Items.VMItems;

/**
 * The Admin class represents an administrator with control over the vending machine's operations,
 * including depositing and withdrawing money, adding items, and emptying the machine's funds.
 * It implements the AdminAPI interface to provide these functionalities.
 */
public class Admin implements AdminAPI {

    private VendingMachine vendingMachine;

    /**
     * Constructs an Admin with access to the specified vending machine.
     *
     * @param vendingMachine the vending machine the admin manages
     */
    public Admin(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    /**
     * Deposits a coin into the vending machine's balance.
     *
     * @param coin the coin to be deposited
     */
    @Override
    public void depositMoney(Coin coin) {
        this.vendingMachine.depositMoney(coin);
    }

    /**
     * Withdraws a specific coin from the vending machine's balance.
     *
     * @param coin the coin to be withdrawn
     */
    @Override
    public void withdrawMoney(Coin coin) {
        this.vendingMachine.withdrawMoney(coin);
    }

    /**
     * Adds a specified number of an item to the vending machine's stock.
     *
     * @param item     the item to be added
     * @param howMany  the quantity of the item to add
     */
    @Override
    public void addItems(VMItems item, int howMany) {
        this.vendingMachine.addItems(item, howMany);
    }

    /**
     * Withdraws all funds from the vending machine, emptying its balance.
     */
    @Override
    public void withdrawEverything() {
        this.vendingMachine.withdrawEverything();
    }
}

