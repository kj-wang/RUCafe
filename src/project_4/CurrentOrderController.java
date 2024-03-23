package project_4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * This class serves as a controller for the currenOrder page and holds the methods to control it
 * It also allows for removing an order, adding it to store order and more
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class CurrentOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button removeButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private TextField subtotalButton;

    @FXML
    private TextField salesTaxButton;

    @FXML
    private TextField totalButton;

    @FXML
    private ListView<String> orderView;

    private MenuController menuController;

    private Order order;

    private StoreOrders storeOrders;

    private int numItems;

    private static final int NO_ITEMS= 0;

    private static final double SALES_TAX = 0.06625;

    /**
     * 	This method sets the local main controller in order to access the universal order
     * @param controller controller needed to reference order and store order
     */
	public void setMainController(MenuController controller) {
		menuController = controller;
        order = menuController.getOrder();
        numItems = menuController.getNumItems();
        storeOrders = menuController.getStoreOrders();
	}

	/**
	 * This function initializes the order and refreshes the page based on the order
	 */
    void initializeOrder(){
        orderView.getItems().clear();
        numItems = menuController.getNumItems();


        MenuItem[] traverseList = order.getOrder();
        
      //add to viewList
        for (int i = 0; i < numItems; i++) {
            orderView.getItems().add(traverseList[i].toString()); 
        }

        ActionEvent event = new ActionEvent();
        subtotal(event);

        //enable remove button if not empty
        if (menuController.getNumItems() != NO_ITEMS) {
        	removeButton.setDisable(false);
            placeOrderButton.setDisable(false);
        }

    }

    /**
     * This function updates StoreOrders and clears the current order
     * @param event event when placeOrder button is clicked
     */
    @FXML
    void placeOrder(ActionEvent event) {
        storeOrders.add(order);
        menuController.setStoreOrders(this.storeOrders); 
        storeOrders = menuController.getStoreOrders(); 
        
        // clear current order
        menuController.setOrder(new Order());
        order = menuController.getOrder();
        initializeOrder();
        removeButton.setDisable(true);
        placeOrderButton.setDisable(true);
    }

    /**
     * This function removes an item from the order
     * @param event event when remove button is clicked
     */
    @FXML
    void remove(ActionEvent event) {
    	//Check if nothing selected
    	if(orderView.getSelectionModel().isEmpty() ){
    	    Alert alert = new Alert(Alert.AlertType.NONE);
	        alert.setTitle("ALERT!");
	        alert.setHeaderText("No Selection Warning");
	        alert.setContentText("Please select an item before attempting to remove it");
	        alert.getButtonTypes().addAll(ButtonType.OK);
	        alert.showAndWait();
	        return;
        }
    	
    	//Tokenizes selected line
        String selectedMenuItem = orderView.getSelectionModel().getSelectedItem();
    	StringTokenizer st = new StringTokenizer(selectedMenuItem, "::");
    	
    	//Remove coffee
    	String menuItemType = st.nextToken();
        if (menuItemType.equals("coffee")) {
            st.nextToken();
            String size = st.nextToken().trim();
            
            //Coffee to be removed
            Coffee temp = new Coffee(size);
            
            st.nextToken(); //skip price and price value
            st.nextToken(); //skip quantity and quantity value

            //add addins coffee object
            while (st.hasMoreTokens()) {
                String addins = st.nextToken().trim();
                temp.add(addins);
            }

            order.remove(temp);

        }
        
        //REMOVE DONUT
        else if (menuItemType.equals("donut")) {
            String typeToken  = st.nextToken();
            st.nextToken(); 
    	    String flavorToken = st.nextToken();

            Donut temp = new Donut(typeToken, flavorToken); 
            order.remove(temp);
        }
        
        	//refresh page
            initializeOrder();

            //REMOVE THE LAST ITEM
        if (menuController.getNumItems() == NO_ITEMS) {
        	removeButton.setDisable(true);
        	placeOrderButton.setDisable(true);
        }     
    }

    /**
     * This function updates the subtotal text area
     * @param event whenever subtotal is needed to update dynamically
     */
    @FXML                                    
    void subtotal(ActionEvent event) {
        double subtotal = order.calculate();
        
        DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
        String subtotalString = dec.format(subtotal);
        subtotalButton.setText(subtotalString);

        double salesTax = subtotal * SALES_TAX;
        String salesTaxString = dec.format(salesTax);
        salesTaxButton.setText(salesTaxString);

        double total = subtotal + salesTax;
        String totalString = dec.format(total);
        totalButton.setText(totalString);
    }

    /**
     * This method is ran when the FXML file is first started
     */
    @FXML
    void initialize() {
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
        assert placeOrderButton != null : "fx:id=\"placeOrderButton\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
        assert subtotalButton != null : "fx:id=\"subtotalButton\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
        assert salesTaxButton != null : "fx:id=\"salesTaxButton\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
        assert totalButton != null : "fx:id=\"totalButton\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
        assert orderView != null : "fx:id=\"orderView\" was not injected: check your FXML file 'CurrentOrder.fxml'.";
     
        removeButton.setDisable(true);
        placeOrderButton.setDisable(true);
    }
}