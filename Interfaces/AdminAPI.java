package Interfaces;

import BritishCoins.Coin;
import Items.VMItems;

/**
 * The AdminAPI interface provides methods for administrative actions on the vending machine, 
 * such as depositing and withdrawing money, adding items, and withdrawing all funds.
 */
public interface AdminAPI {

    /**
     * Deposits a specified coin into the vending machine, increasing its balance.
     * 
     * @param coin the coin to be deposited into the vending machine
     */
    public void depositMoney(Coin coin);

    /**
     * Withdraws a specified coin from the vending machine, decreasing its balance.
     * 
     * @param coin the coin to be withdrawn from the vending machine
     */
    public void withdrawMoney(Coin coin);

    /**
     * Adds a specified number of items to the vending machine's stock.
     * 
     * @param item the item to be added to the vending machine
     * @param howMany the quantity of the item to be added
     */
    public void addItems(VMItems item, int howMany);

    /**
     * Withdraws all funds from the vending machine, setting its balance to zero.
     */
    public void withdrawEverything();
}