package project_4;

/**
 * An instance of this class keeps the list of orders placed by the user and interacts with menuController
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class StoreOrders implements Customizable {

	private Order[] storeOrders;
	private static final int GROWTH_SIZE = 4;
	private int numOrders;
    private static final int INDEX_NOT_FOUND = -1;
    private static final int PTR_INCREMENT = 1; 
    
    
    /**
     * This is a constructor for store orders
     */
    public StoreOrders() {
        storeOrders = new Order[GROWTH_SIZE];
        numOrders = 0;
    }

    /**
     * This is a getter for store orders
     * @return store orders object
     */
	public Order[] getStoreOrders(){
		return storeOrders;
	}

	/**
	 * This is a getter for number of orders
	 * @return number of orders in store order
	 */
	public int getNumOrders() {
		return numOrders;
	}

	/**
	 * This method is used to grow the storeOrders object by the growth size of 4
	 */
	private void grow() { 
        Order[] storeOrders = new Order[numOrders + GROWTH_SIZE];
        for (int i = 0; i < numOrders; i++) {                        
            storeOrders[i] = this.storeOrders[i];
        }
        this.storeOrders = storeOrders;
    }

	/**
	 * This method takes in an order and adds it to store order
	 * @param obj order to be added
	 * @return boolean if order was successfully added
	 */
    public boolean add(Object obj) {
		if (obj instanceof Order) {
			Order order = (Order) obj;
			if (storeOrders.length == numOrders) {
				grow();    
			} 

			storeOrders[numOrders] = order;
			numOrders++;
			return true;
		}
		return false;
	}

    /**
     * This method takes in a order and removes it from store order
     * @param obj order to be removed
     * @return boolean if the object has been successfully removed, false otherwise
     */
	public boolean remove(Object obj){
		 if (obj instanceof Order){
            Order item = (Order) obj;
            int removedIndex = find(item);
            
            if (removedIndex == INDEX_NOT_FOUND){ 
                return false;
            }

            // remove the employee at the returned index
            int ptr1 = removedIndex;
            int ptr2 = ptr1 + PTR_INCREMENT;
    
            //shift the array left to fill empty index
            while(ptr2 < storeOrders.length) {
                storeOrders[ptr1] = storeOrders[ptr2];
                ptr1 ++;
                ptr2 ++;
            }
            numOrders--;
            return true;
        }
        return false;
	}

	 /**
     * This method takes in a menuItem 
     * @param   obj the item that is being looked for
     * @return  index in which the item is, however if it is not found then indexNotFound is returned
     */
    private int find(Object obj) {                      
        if (obj instanceof Order){
            Order item = (Order) obj;
            for(int i = 0; i < storeOrders.length; i++){ 
                if(item.equals(storeOrders[i])){
                    return i;
                }
            }
            return INDEX_NOT_FOUND;
        }   
        return INDEX_NOT_FOUND;
    }	
}
