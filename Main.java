
import BritishCoins.*;
import CentralVM.Admin;
import CentralVM.Customer;
import CentralVM.VendingMachine;
import Items.VMItems;

public class Main {
    public static void main(String[] args) throws Exception {

        // Initializing a vending machine with Admin and Customer roles. They both
        // implement their own seperate APIs so a customer will not be able to do admin
        // actions and vice versa
        VendingMachine vm1 = new VendingMachine("Vending machine 1", 50);
        Admin admin = new Admin(vm1);
        Customer customer = new Customer(vm1);

        // Creating items to add to the vending machine
        VMItems coke = new VMItems("Coke", "01", 75); // Costs 75p
        VMItems fanta = new VMItems("Fanta", "02", 99); // Costs £1.50
        VMItems something = new VMItems("something", "03", 10); // Costs £1.50

        // Admin interactions
        System.out.println("**** Admin Interaction testing ****");
        System.out.println("----Admin adding items to the machine----");
        admin.addItems(fanta, 10);
        admin.addItems(coke, 20);
        admin.addItems(something, 1);

        // admin adds a negative or a 0. admin adds more than machine capacity
        admin.addItems(fanta, 0);
        admin.addItems(fanta, -1);
        admin.addItems(fanta, 100); // adding amount more than capacity.
        System.out.println();

        // admin deposit coins
        System.out.println("----Admin depositing coins----");
        admin.depositMoney(Coin.ONE_POUND);
        admin.depositMoney(Coin.TWENTY_PENCE);
        admin.depositMoney(Coin.FIVE_PENCE);
        admin.depositMoney(Coin.FIFTY_PENCE);
        // admin passes an invalid parameter
        admin.depositMoney(null);
        System.out.println();

        // admin withdrawal methods
        System.out.println("----Admin withdrawing coins----");
        admin.withdrawMoney(Coin.ONE_POUND); // successful as it is present in the machine
        admin.depositMoney(Coin.ONE_POUND);

        // admin withdraws a coin not available in the vending machine
        admin.withdrawMoney(Coin.TWO_POUNDS); // unsuccessful as it is not in the machine

        // admin withdraws all coins at once
        admin.withdrawEverything(); // machine not empty, so successful
        admin.withdrawEverything(); // not successful because machine now is empty, nothing to withdraw

        // deposit money again to reset
        admin.depositMoney(Coin.ONE_POUND);
        admin.depositMoney(Coin.TWENTY_PENCE);
        admin.depositMoney(Coin.FIVE_PENCE);
        admin.depositMoney(Coin.FIFTY_PENCE);
        System.out.println();

        // Customer interactions
        System.out.println("**** Testing Customer interactions ****");
        vm1.displayAll();

        System.out.println("----Customer inserting coins----");
        customer.insertCoin(Coin.FIFTY_PENCE);
        customer.insertCoin(Coin.TWENTY_PENCE);
        customer.insertCoin(Coin.FIVE_PENCE); // these are not confirmed, so not added to vending machine list of coins
        // invalid coin
        customer.insertCoin(null);
        System.out.println();

        System.out.println("----Customer Selecting, confirming purchase, and cancelling----");
        // customer selects an item that costs exactly the amount inserted
        customer.selectItem("01");
        customer.selectItem("02"); // now allowed to select another item while in the middle of a transaction.
        customer.confirmPurchase();
        System.out.println();

        // customer selects an item that cost less than inserted amount
        customer.insertCoin(Coin.ONE_POUND);
        customer.selectItem("01");
        customer.confirmPurchase();
        System.out.println();

        // customer selects an item that is more than the inserted coin
        customer.insertCoin(Coin.ONE_PENCE);
        customer.selectItem("01");
        customer.confirmPurchase();
        System.out.println();

        // customer inserts right amount but vending machine does not have the right set
        // of change.
        customer.insertCoin(Coin.ONE_POUND);
        customer.selectItem("02");
        customer.confirmPurchase();
        System.out.println();

        // cancelling transation before purchose
        customer.insertCoin(Coin.TWO_POUNDS);
        customer.selectItem("01");
        customer.cancelAndRefund();
        System.out.println();

        // final testing for customer
        customer.insertCoin(Coin.FIFTY_PENCE);
        customer.insertCoin(Coin.TWENTY_PENCE);
        customer.insertCoin(Coin.FIVE_PENCE);
        customer.selectItem("01");
        customer.confirmPurchase();
        System.out.println();

        // getting the final state of the vending machine
        System.out.println("----Final vending machine stats----");
        System.out.println(vm1.toString());

        // final stats after withdrawing all the money
        admin.withdrawEverything();
        System.out.println(vm1.toString());
    }
}
