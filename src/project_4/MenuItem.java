package project_4;

/**
 *This is the superclass of all menu items, such as donuts and coffee. Any class defined for
 *a menu item must extend this class. All the subclasses must include a “itemPrice” method for calculating the
 *price of the menu item. -2 points for each violation.
 *
 * @author KJ Wang, Mehdi Kamal
 */

public class MenuItem {
    MenuItem menuItem;
    private int quantity = 0; 
    private double price;

    /**
     * This method sets the price of a given menuItem
     * @param price price to be set
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * This method is used to get the price of a given menuItem
     * @return price as a double
     */
    public double getPrice(){
		itemPrice();
		return price;
	}

    /**
     * This is a setter the quantity of a menuItem
     * @param quantity the quantity that is being set
     */
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
    /**
     * This is a getter for the quantity of a menuItem
     * @return the quantity of the menuItem
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * calculates the price of a given menuItem
     */
    public void itemPrice() {
    }

    /**
     * This method returns a textual representation of a fulltime employees profile, payment, and annual salary
     * @return String a textual representation
     */
    public String toString() { 
      
        return ("this is menuitem::" + quantity);
    }

    /**
     * This method is used to check if MenuItems are equal
     */
    public void equals(){
    }


}
