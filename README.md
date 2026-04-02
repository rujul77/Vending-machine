# Vending Machine Program

project completed January 2025

## Program Description

This vending machine program allows customers to select and purchase drinks using British pound coins. The machine manages stock, provides change, and supports administrative functions such as refilling stock and managing funds. 

## Running the Program

To run the program:
1. Navigate to the `Main.java` file.
2. Run it from there.  
All tests are hard-coded and printed; Simply execute the file.

### Features:
- **Customer Actions:** Customers can select items, insert coins, cancel purchases, and confirm transactions.
- **Admin Actions:** Admins can set machine capacity, add items, and deposit/withdraw funds.

## Interfaces and Classes

### Interfaces

- **AdminAPI**  
  Defines methods for admin interactions such as setting capacity, depositing money, and adding items to the machine.

- **CustomerAPI**  
  Defines methods for customer actions, including selecting an item, inserting coins, canceling transactions, and taking purchased items.

### Classes

- **VendingMachine**  
  Core class that manages the vending machine’s functionality, including processing purchases, handling transactions, managing inventory, and providing error handling.

- **VMItems**  
  Represents an item within the vending machine, with attributes such as name, code, and price for each type of drink.

- **Coin (Enum)**  
  Enum representing British pound coins, containing values for each coin denomination used in transactions.

- **Customer**  
  Implements `CustomerAPI`, allowing customers to interact with the vending machine.

- **Admin**  
  Implements `AdminAPI`, enabling the owner to perform administrative functions such as stock refills and fund management.

### Exception Classes

- **OutOfStockException**  
  Thrown when a customer selects an item that is currently out of stock.

- **InsufficientFundsException**  
  Thrown when a customer’s inserted coins are insufficient to cover the selected item’s price.

- **InvalidCodeException**  
  Thrown when a customer enters an invalid code that does not match any item in the machine.
