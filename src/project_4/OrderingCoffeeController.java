package project_4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * An instance of this class is a subclass of menuItem and this class holds the methods to add a coffee to an order and interact with the gui
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class OrderingCoffeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox whippedCreamButton;

    @FXML
    private CheckBox milkButton;

    @FXML
    private CheckBox creamButton;

    @FXML
    private CheckBox syrupButton;

    @FXML
    private CheckBox caramelButton;

    @FXML
    private ComboBox<String> sizeButton;

    @FXML
    private ComboBox<Integer> quantityButton;

    @FXML
    private TextField subtotal;                           

    @FXML
    private Button addToOrderButton;

    private Coffee coffee;

    private Order order;

    private MenuController menuController;

    private static final String ZERO_DOLLARS = "$0.00";

    private static final int MIN_QUANTITY = 1;

    private static final int MAX_QUANTITY = 10;

    /**
     * This method gets reference of main controller
     * @param controller the controller that is being passed as reference
     */
    public void setMainController(MenuController controller) {
		menuController = controller;
        this.order = menuController.getOrder();
	}
    
        
    
    /**
     * This method adds a coffee order to the universal order
     * @param event event when addToOrderButton is clicked
     */
    @FXML
    void addToOrder(ActionEvent event) { 
        //confirmation window
        Alert alert = new Alert(AlertType.CONFIRMATION, "Add Order?" , ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //Creates a coffee
            coffeeCreator();
            coffee.itemPrice();
            //adds coffee to universal order
            this.order.add(coffee);
            menuController.setOrder(order);
            this.order = menuController.getOrder();  
        }

    }

    /**
     * This method updates coffee in order to calculate subtotal
     * @param event event when coffee is changed
     */
    @FXML
    void subtotal(ActionEvent event) {        
    	
    	//disable add-ons until quantity & size are selected
        if (!sizeButton.getSelectionModel().isEmpty() && !quantityButton.getSelectionModel().isEmpty()) {
            whippedCreamButton.setDisable(false);
            milkButton.setDisable(false);
            creamButton.setDisable(false);
            syrupButton.setDisable(false);
            caramelButton.setDisable(false);
            addToOrderButton.setDisable(false);
            quantityButton.setDisable(false);
        }

      	coffeeCreator(); 
        
        //subtotal calculation
        double subtotalDouble = 0.0;
        coffee.itemPrice();                         
        subtotalDouble = coffee.getPrice();
            
        //formatting for subtotal
        DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
        String subtotalString = (String) dec.format(subtotalDouble);
        subtotal.setText(subtotalString);
        
    }

    /**
     * This method creates a coffee object to be added to the order
     */
    private void coffeeCreator(){
        coffee = new Coffee();
    	  		
        if (!sizeButton.getSelectionModel().isEmpty()){		
            coffee.setSize((String) sizeButton.getValue()); 
        }
        
        //ADD-INS
        if (whippedCreamButton.isSelected()){               
            coffee.add("whippedCream");
        }
        else {
        	coffee.remove("whippedCream");
        }
        if (milkButton.isSelected()) {
            coffee.add("milk");
        }
        else {
        	coffee.remove("milk");
        }
        if (creamButton.isSelected()) {
            coffee.add("cream");
        }
        else {
        	coffee.remove("cream");
        }
        if (syrupButton.isSelected()) {
            coffee.add("syrup");
        }
        else {
        	coffee.remove("syrup");
        }
        if (caramelButton.isSelected()) {
            coffee.add("caramel");
        }
        else {
        	coffee.remove("caramel");
        }
            
        // quantity
        if (!quantityButton.getSelectionModel().isEmpty()) {
             coffee.setQuantity((int) quantityButton.getValue());     
        }    
    }


    /**
     * This method sets as a starting point for when the controller is initially created
     */
    @FXML
    void initialize() {
        assert whippedCreamButton != null : "fx:id=\"whippedCreamButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert milkButton != null : "fx:id=\"milkButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert creamButton != null : "fx:id=\"creamButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert syrupButton != null : "fx:id=\"syrupButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert caramelButton != null : "fx:id=\"caramelButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert sizeButton != null : "fx:id=\"sizeButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert quantityButton != null : "fx:id=\"quantityButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert subtotal != null : "fx:id=\"subtotal\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        assert addToOrderButton != null : "fx:id=\"addToOrderButton\" was not injected: check your FXML file 'OrderingCoffee.fxml'.";
        
        //disable all buttons until quantity and size selected
        creamButton.setDisable(true);
        milkButton.setDisable(true);
        syrupButton.setDisable(true);
        caramelButton.setDisable(true);
        whippedCreamButton.setDisable(true);
        addToOrderButton.setDisable(true);
        quantityButton.setDisable(true);
        
        //fill comboboxes
        sizeButton.getItems().addAll("Short" , "Tall", "Grande", "Venti");
     
        for (int i = MIN_QUANTITY; i <= MAX_QUANTITY; i++){
            quantityButton.getItems().add(i);
        }
        
        quantityButton.setValue(MIN_QUANTITY);
        subtotal.setText(ZERO_DOLLARS);
    }
}
