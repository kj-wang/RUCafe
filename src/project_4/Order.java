package project_4;

/**
 * An instance of this class holds a list of menu items added by the user, it also implements customizable
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class Order implements Customizable {
	
    private MenuItem[] order;
    private int numItems = 0;
    private static final int INDEX_NOT_FOUND = -1;
    private static final int GROWTH_SIZE = 4;

    /**
     * This method is a getter for number of items
     * @return numItems
     */
    public int getNumItems(){
        return numItems;
    }
	
    /**
     * This method is a getter for list of menuitems
     * @return order object
     */
    public MenuItem[] getOrder() {
        return order;
    }

    /**
     * This method is a constructor for the order class
     */
    public Order() {
        order = new MenuItem[GROWTH_SIZE];
        numItems = 0;
    }

    /**
     * This method calculates the total price of all menu items combined
     * @return double total price
     */
    public double calculate() {             
    	double totalPrice = 0;
    	for(int i = 0; i < numItems; i++) {
    		totalPrice += order[i].getPrice(); 
    	}
    	return totalPrice;
    }

    
    /**
     * This method helps grow the order list capacity increase by 4 (GROWTH_SIZE)
     */
    private void grow() { 
        MenuItem[] order = new MenuItem[numItems + GROWTH_SIZE];
        for (int i = 0; i < numItems; i++) {
            order[i] = this.order[i];
        }
        this.order = order;
    }
	
    
    /**
     * This method adds an item to the order
     * @param obj the item that is being added
     * @return true if added, false if the item cannot be
     */
    public boolean add(Object obj) {              
       
        if (obj instanceof MenuItem){
            MenuItem item = (MenuItem) obj;
            
            if(find(item) != INDEX_NOT_FOUND){
            	
            	int itemIndex = find(item);
            	//increase quantity
                order[itemIndex].setQuantity(item.getQuantity()+order[itemIndex].getQuantity());
                return true;
            }
            
            //check if employee list must be expanded
            if (numItems == order.length){ 
                grow();
            }

            //Add newitem
            order[numItems] = item;      
            numItems++;
            return true; 
        }
        return false;
    }

    /**
     * This method removes a menu item from the order
     * @param obj to be removed
     * @return true if removed, false if not found
     */
    public boolean remove(Object obj) { 
        if (obj instanceof MenuItem){
            MenuItem item = (MenuItem) obj;
            int removedIndex = find(item);
            
            if (removedIndex == INDEX_NOT_FOUND){ 
                return false;
            }

            // remove the employee at the returned index
            int ptr1 = removedIndex;
            int ptr2 = ptr1 + 1;
    
            //shift the array left to fill empty index
            while(ptr2 < order.length) {
                order[ptr1] = order[ptr2];
                ptr1 ++;
                ptr2 ++;
            }
            numItems--;
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
           if (obj instanceof Donut){
            Donut item = (Donut) obj;
            for(int i = 0; i < numItems; i++){ 
                if(item.equals(order[i])){
                	
                    return i;
                }
            }
            return INDEX_NOT_FOUND;
        }

        if (obj instanceof Coffee){
            Coffee item = (Coffee) obj; 
            for(int i = 0; i < numItems; i++){ 
                if(item.equals(order[i])){
                    return i;
                }
            }
            return INDEX_NOT_FOUND;
        }
        return INDEX_NOT_FOUND;
    }


    /**
     * This method prints the earning statements for all employees in no particular order
     * @return output string
     */
    public String exportDatabase() {
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < numItems; i++){ 
    		str.append(this.order[i].toString() + "\n");
        }
    	return str.toString();
    } 
    
   
    /**
     * This is a method used to check if two orders are equal
     * @param obj the order that is being checked
     * @return return true if they are equal, false if not
     */
    public boolean equals(Object obj) {
    	
    	if(obj instanceof Order) {
    		Order tempOrder = (Order) obj;
    		MenuItem[] tempList = tempOrder.getOrder();
    		
    		if(tempOrder.getNumItems() != numItems) {
    			return false;
    		}
    		
    		for(int i = 0; i < tempOrder.getNumItems(); i++) {
    			if(!tempList[i].equals(order[i])) {
    				return false;
    			}
    		}
    		
    		return true;
    	}
    	return false;
    }
}