
        
        System.out.println("----Customer Selecting, confirming purchase, and cancelling----");
        //customer selects an item that costs exactly the amount inserted
        customer.selectItem("01");
        customer.selectItem("02"); // now allowed to select another item while in the middle of a transaction.
        customer.confirmPurchase();
        System.out.println();