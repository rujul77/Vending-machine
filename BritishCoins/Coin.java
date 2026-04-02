package BritishCoins;

/**
 * The Coin enum represents various denominations of British currency,
 * with values in pence for each coin type.
 */
public enum Coin {

    ONE_PENCE(1), 
    TWO_PENCE(2), 
    FIVE_PENCE(5), 
    TEN_PENCE(10), 
    TWENTY_PENCE(20), 
    FIFTY_PENCE(50), 
    ONE_POUND(100), 
    TWO_POUNDS(200);
    
    private final int value;

    /**
     * Constructs a Coin with the specified value in pence.
     *
     * @param value the value of the coin in pence
     */
    Coin(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the coin in pence.
     *
     * @return the coin's value in pence
     */
    public int getCoinValue() {
        return this.value;
    }
}
