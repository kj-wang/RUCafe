package project_4;

import java.text.DecimalFormat;


/**
 * This class extends the menuItem class and includes specific data and operations specific to donut
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class Donut extends MenuItem {
	private String flavor;
	private String type;
	private static final int INIT_QUANTITY = 1;
	
	/**
	 * This method is a constructor for donut
	 */
	public Donut() {
		super.setQuantity(INIT_QUANTITY);
	}

	/**
	 * This method is a contructor for donut
	 * @param type type of donut
	 * @param flavor flavor of donut
	 */
	public Donut(String type, String flavor) {
		this.type = type;
		this.flavor = flavor;
		super.setQuantity(INIT_QUANTITY);
		
	}
	
	/**
	 * This is a setter for the donut type
	 * @param type type of donut
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This is a setter for the donut flavor
	 * @param flavor flavor of donut
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}


	/*
	 * This calculates the price of a single donut based on the type
	 */
	@Override
	public void itemPrice() {	// made public
		double price = 0;
		if(type.equals("yeast")) {
			price = 1.39;
		}
		else if(type.equals("cake")) {
			price = 1.59;
		}
		else if(type.equals("donutHole")) {
			price = .33;
		}
		
		price = price * super.getQuantity();
		super.setPrice(price);
	}
	
	/**
	 * Compares an object to a donut to see if they are equal
	 * @param obj object to be compared to given donut
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {    
	    if (obj instanceof Donut) {
	  	   Donut temp = (Donut) obj;
	    	   
	   	   //checks to see if same type of donut
	   	   if(temp.type.equals(type) && temp.flavor.equals(flavor)) {
	   		   	return true;
	   	   }
	    }
  
	    return false; 
	}


	/**
     * This method returns a textual representation of a fulltime employees profile, payment, and annual salary
     * @return String a textual representation
     */
    @Override
    public String toString() { 
        DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
        String textualRepresentation = ( "donut::" + type + "::flavor::" + flavor + "::price " + dec.format(super.getPrice()) + "::quantity " + super.getQuantity());
        return textualRepresentation;
    }

}
