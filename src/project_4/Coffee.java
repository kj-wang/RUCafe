package project_4;

import java.text.DecimalFormat;

/**
 * This class extends the menuItem class and includes specific data and operations specific to coffee
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class Coffee extends MenuItem implements Customizable{
	
	private static final int PRESENT = 1;
	private static final int NOT_PRESENT = 0;
	private int cream = NOT_PRESENT;
	private int syrup = NOT_PRESENT;
	private int milk = NOT_PRESENT;
	private int caramel = NOT_PRESENT;
	private int whippedCream = NOT_PRESENT;
	private String size;   
	private static final double ADDIN_PRICE = 0.20;
	private static final int COFFEE_QUANTITY = 1;
	private static final double SHORT_PRICE = 1.99;
	private static final double TALL_PRICE = 2.49;
	private static final double GRANDE_PRICE = 2.99;
	private static final double VENTI_PRICE = 3.49;

	/**
	 * This method is a constructor for coffee with no input
	 */
	public Coffee() {
		super.setQuantity(COFFEE_QUANTITY);
	}

	/**
	 * This method is a constructor for coffee with size input
	 * @param size size of coffee to be set
	 */
	public Coffee(String size) {
		this.size = size;
		super.setQuantity(COFFEE_QUANTITY);
	}

	/**
	 * This method sets the size of a coffee object
	 * @param size size to be set
	 */
	public void setSize(String size) {
		this.size = size.toLowerCase();
	}

	/**
	 * This method sets the quantity of a coffee object
	 * @param quantity the quantity that is being set
	 */
	public void setQuantity(int quantity) {
		super.setQuantity(quantity);
	}

	/**
	 * Calculates the price of a coffee based on size and add-ins
	 */
	@Override
	public void itemPrice() {
		double price = 0;
		if(size.equals("short")) {
			price = SHORT_PRICE;
		}
		else if(size.equals("tall")) {
			price = TALL_PRICE;
		}
		else if(size.equals("grande")) {
			price = GRANDE_PRICE;
		}
		else if(size.equals("venti")) {
			price = VENTI_PRICE;
		}
		
		//price calculation and set
		price += (cream + syrup + milk + caramel + whippedCream) * ADDIN_PRICE;
		price = price * super.getQuantity();
		super.setPrice(price);
	}

	/**
	 *  Adds a single add-in for a coffee object
	 * @param obj object taken in adding the add ins for the coffee
	 * @return true if the add-in was added successfully, false if not
	 */
	public boolean add(Object obj) {
		if (obj instanceof String) {
			String addin = (String) obj;
			switch (addin) {
				case "cream":
					cream = PRESENT;
					break;
				case "syrup":
					syrup = PRESENT;
					break;
				case "milk":
					milk = PRESENT;
					break;
				case "caramel":
					caramel = PRESENT;
					break;
				case "whippedCream":
					whippedCream = PRESENT;
					break;	
			}

			return true;
		}
		return false;
	}
	
	/**
	 * removes an add-in for a coffee object
	 * @param obj object taken in removing the add ins for the coffee
	 * @return true if the add-in was successfully removed, false if not
	 */
	public boolean remove(Object obj){
		
		if (obj instanceof String) {
			String addin = (String) obj;
			switch (addin) {
				case "cream":
					cream = NOT_PRESENT;
					break;
				case "syrup":
					syrup = NOT_PRESENT;
					break;
				case "milk":
					milk = NOT_PRESENT;
					break;
				case "caramel":
					caramel = NOT_PRESENT;
					break;
				case "whippedCream":
					whippedCream = NOT_PRESENT;
					break;	
			}
			return true;
		}
		return false;
	}
	
	/**
     * This method compares two coffees to see if they are identical 
     * @param obj of type employee extended to part time
     * @return true if they are equal, false otherwise
     */
   @Override
   public boolean equals(Object obj) {    
       if (obj instanceof Coffee) {
    	   Coffee temp = (Coffee) obj; 
    	  if(temp.caramel == caramel && temp.cream == cream && temp.syrup == syrup && temp.milk == milk 
		  			&& temp.whippedCream == whippedCream && temp.size.equals(size)) {		
    		  return true;
    	  }
       }   
       return false; 
   }

	/**
     * This method returns a textual representation of a coffee object based on the add ins, size, and quantity
     * @return String a textual representation
     */
    @Override
    public String toString() { 
        DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
		
        //add-ons
        StringBuilder sb = new StringBuilder();
        if(caramel == PRESENT) {
        	sb.append("::caramel");
        }
        if(whippedCream == PRESENT) {
        	sb.append("::whipped cream");
        }
        if(cream == PRESENT) {
        	sb.append("::cream");
        }
        if(syrup == PRESENT) {
        	sb.append("::syrup");
        }
        if(milk == PRESENT) {
        	sb.append("::milk");
        }
        String textualRepresentation = ( "coffee::size::" + size + "::price " + dec.format(super.getPrice()) + "::quantity " + super.getQuantity()) + sb.toString();	// print addins
        return textualRepresentation;
    }


}
