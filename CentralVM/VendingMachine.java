package CentralVM;
import BritishCoins.Coin;
import Exceptions.NotEnoughChangeException;
import Exceptions.NotFullPaidException;
import Exceptions.OutOfStockException;
import Items.VMItems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * The VendingMachine class represents a vending machine with a set capacity and a collection of items.
 * It supports functions such as adding items, managing coins, processing transactions,
 * and providing change for purchases.
 */
public class VendingMachine {

    private final String name;
    private final int capacity;
    private int remainingCapacity;
    private int realBalance;
    private int temporaryBalance; // Balance for unconfirmed transactions
    private HashMap<VMItems, Integer> itemStock;
    private ArrayList<Coin> listOfCoins;
    private ArrayList<Coin> tempListOfCoins = new ArrayList<>();
    private VMItems tempSelectItem;
    private boolean isItemSelected; // false

    /**
     * Constructs a VendingMachine instance with the specified name and capacity.
     *
     * @param name     the name of the vending machine
     * @param capacity the total capacity of the vending machine
     */
    public VendingMachine(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.realBalance = 0;
        this.temporaryBalance = 0;
        this.itemStock = new HashMap<>();
        this.listOfCoins = new ArrayList<>();
    }

    /**
     * Creates a copy of a vending machine instance with the given name and capacity.
     *
     * @param name     the name of the new vending machine
     * @param capacity the capacity of the new vending machine
     * @return a new instance of VendingMachine
     */
    public static VendingMachine createVendingMachine(String name, int capacity) {
        return new VendingMachine(name, capacity);
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getRemainingCapacity() {
        return this.remainingCapacity;
    }

    public int getRealBalance() {
        return this.realBalance;
    }

    public int getTemporaryBalance() {
        return this.temporaryBalance;
    }

    public ArrayList<Coin> getListOfCoins() {
        return this.listOfCoins;
    }

    public VMItems getTempSelectItem() {
        return this.tempSelectItem;
    }

    /**
     * Displays all items and their details in the vending machine.
     */
    public void displayAll() {
        System.out.println("---- Items in Vending Machine ----");
        if (itemStock.isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (Map.Entry<VMItems, Integer> entry : this.itemStock.entrySet()) {
                VMItems item = entry.getKey();
                System.out.println("Item: " + item.getName() +
                                   ", Code: " + item.getItemCode() +
                                   ", Price: " + item.getPrice()/100 + "." + item.getPrice()%100);
            }
        }
        System.out.println("--------------------------------------------");
    }

    /**
     * Adds a specified quantity of an item to the vending machine if capacity allows.
     *
     * @param item     the item to be added
     * @param quantity the quantity of the item to add
     */
    void addItems(VMItems item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Item not added, Quantity must be a positive integer");
        } else if (quantity > this.getRemainingCapacity()) {
            System.out.println("Error: Quantity exceeds the capacity of the vending machine");
        } else {
            this.itemStock.put(item, itemStock.getOrDefault(item, 0) + quantity);
            this.remainingCapacity -= quantity;
            System.out.println("Successfully added " + quantity + " " + item.getName() + "(s). Remaining capacity: " + this.remainingCapacity);
        }
    }

    /**
     * Deposits a coin into the vending machine's real balance.
     *
     * @param coin the coin to be deposited
     */
    void depositMoney(Coin coin) {
        if (coin == null) {
            System.out.println("Error: Coin cannot be null.");
        } else {
            this.realBalance += coin.getCoinValue();
            this.listOfCoins.add(coin);
            System.out.println("Deposit was successful!");
        }
    }

    /**
     * Withdraws a specified coin from the vending machine if it exists.
     *
     * @param coin the coin to be withdrawn
     */
    void withdrawMoney(Coin coin) {
        if (!this.listOfCoins.contains(coin)) {
            System.out.println("Error: The vending machine does not contain this coin");
        } else {
            this.listOfCoins.remove(coin);
            this.realBalance -= coin.getCoinValue();
            System.out.println("Successful withdrawal of " + coin);
        }
    }

    /**
     * Withdraws all funds from the vending machine.
     */
    void withdrawEverything() {
        if (this.listOfCoins.isEmpty()) {
            System.out.println("Error: There is nothing to withdraw!");
        } else {
            this.listOfCoins.clear();
            this.realBalance = 0;
            System.out.println("Everything has been withdrawn");
        }
    }


    //Cusomter methods below

    /**
     * Inserts a coin into the temporary balance for a potential purchase.
     *
     * @param coin the coin to be inserted
     */
    void insertCoin(Coin coin) {
        if (coin != null) {
            this.temporaryBalance += coin.getCoinValue();
            this.tempListOfCoins.add(coin);
            System.out.println("You have inserted " + (coin.getCoinValue() < 100 ? coin.getCoinValue() + "p" : "£" + coin.getCoinValue() / 100));
        } else {
            System.out.println("Error: coin cannot be null");
        }
    }

    /**
     * Selects an item by code for a potential purchase if it is available in stock.
     *
     * @param code the code of the item to select
     */
    void selectItem(String code) {
        if (isItemSelected) { //not selected
            System.out.println("Error: Complete or cancel the current purchase before selecting a new item.");
            return;
        }

        try {
            boolean itemFound = false;
            for (Map.Entry<VMItems, Integer> entry : this.itemStock.entrySet()) {
                VMItems item = entry.getKey();
                if (code.equals(item.getItemCode())) {
                    itemFound = true;
                    if (entry.getValue() > 0) {
                        System.out.println("You have selected " + item.getName() + ". Confirm Purchase?");
                        this.tempSelectItem = item;
                        this.isItemSelected = true;
                    } else {
                        throw new OutOfStockException("Item is out of stock");
                    }
                    break;
                }
            }
            if (!itemFound) {
                System.out.println("Invalid item code");
            }
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Confirms the selected item purchase if sufficient funds are available.
     */
    void confirmPurchase() {
        if (!isItemSelected) {
            System.out.println("Error: No item selected to confirm purchase.");
            return;
        }

        try {
            int selectedItemPrice = this.tempSelectItem.getPrice();
            if (this.temporaryBalance < selectedItemPrice) {
                throw new NotFullPaidException("Error: Insufficient balance to confirm purchase.");
            }

            int changeNeeded = this.temporaryBalance - selectedItemPrice;
            ArrayList<Coin> changeInCoins = calculateChangeInCoins(changeNeeded);

            if (changeInCoins == null) {
                throw new NotEnoughChangeException("Unable to provide exact change. Cancelling transaction and refunding amount.");
            }

            this.temporaryBalance -= selectedItemPrice;
            this.realBalance += selectedItemPrice;
            this.listOfCoins.addAll(this.tempListOfCoins);

            if (changeInCoins.isEmpty()) {
                System.out.println("You have inserted the right number of coin(s).No change to be provided");
            } else {
                System.out.println("Here is your change: " + changeInCoins);
                this.listOfCoins.removeAll(changeInCoins);
            }
            
            System.out.println("Enjoy your product");
            resetTransaction();

        } catch (NotFullPaidException | NotEnoughChangeException e) {
            System.out.println(e.getMessage());
            cancelAndRefund();
        }
    }

    /**
     * Calculates and returns the exact change in coins, if available.
     *
     * @param changeAmount the amount of change to provide
     * @return an ArrayList of coins for the exact change, or null if exact change is not possible
     */
    private ArrayList<Coin> calculateChangeInCoins(int changeAmount) {
        ArrayList<Coin> changeCoins = new ArrayList<>();
        ArrayList<Coin> sortedCoins = new ArrayList<>(this.listOfCoins);
        sortedCoins.sort(Comparator.comparingInt(Coin::getCoinValue).reversed()); //sort coins by descending order

        for (Coin coin : sortedCoins) {
            while (changeAmount >= coin.getCoinValue() && Collections.frequency(this.listOfCoins, coin) > Collections.frequency(changeCoins, coin)) {
                changeCoins.add(coin);
                changeAmount -= coin.getCoinValue();
            }
        }

        return changeAmount == 0 ? changeCoins : null;
    }

    /**
     * Cancels the current transaction and refunds any inserted coins.
     */
    void cancelAndRefund() {
        if (!isItemSelected) {
            System.out.println("Error: No item selected to cancel.");
            return;
        }

        System.out.println("Purchase canceled. £" + this.temporaryBalance/100 + "." + this.temporaryBalance %100 + " returned.");
        this.tempListOfCoins.removeAll(this.tempListOfCoins);
        resetTransaction();
    }

    /**
     * Resets the vending machine's state after a transaction. Helper method
     */
    private void resetTransaction() {
        this.isItemSelected = false;
        this.tempSelectItem = null;
        this.temporaryBalance = 0;
        this.tempListOfCoins.clear();
    }

    /**
     * Returns a string representation of the vending machine's status.
     *
     * @return a string with balance and remaining capacity information
     */
    public String toString() {
        int capacity = getRemainingCapacity();
        int balance = getRealBalance();
        return "Vending machine stats: \n" + "Balance: " + (balance / 100) + " pounds and " + (balance % 100) + " pence\n" + "Remaining space in the vending machine: " + capacity + "\n";
    }
}
