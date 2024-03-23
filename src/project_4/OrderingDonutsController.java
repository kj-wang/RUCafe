package project_4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * This controller controls the donut page and holds all the methods that add a donut to the order
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class OrderingDonutsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> donutTypeButton;

    @FXML
    private TextField subtotalButton;

    @FXML
    private ListView<String> flavorsView;

    @FXML
    private ListView<String> donutOrderView;

    @FXML
    private ComboBox<Integer> donutQuantityButton;

    @FXML
    private Button addDonutButton;

    @FXML
    private Button removeDonutButton;

    @FXML
    private Button addToOrderButton;

	private MenuController menuController;

    private Order order;
    
    private Donut donut; 

    private int amountOfDonuts;

    Order donutList = new Order();

    private static final String ZERO_DOLLARS = "$0.00";

    private static final int MIN_QUANTITY = 1;

    private static final int MAX_QUANTITY = 10;

    /**
     * This method gives access to the main controller (menuController)
     * @param controller the main controller being sent in
     */
	public void setMainController(MenuController controller) {
		menuController = controller;
        order = menuController.getOrder();
	}


	/**
	 * This method adds a donut to the donutList order that is within the donut ordering UI itself
	 * @param event when add button is pressed
	 */
    @FXML
    void addDonut(ActionEvent event) {
        // check all the fields: quantity, flavor, type
        donutCreator();
        donutList.add(donut);

        //subtotal should update
        subtotal(event);

        
        //enable add to order
        addToOrderButton.setDisable(false);
        
        donutOrderUpdater();
    }

   /**
    * This updates the list view to have the newest items in donut list
    */
    void donutOrderUpdater(){
        //update list
        amountOfDonuts = donutList.getNumItems();
        donutOrderView.getItems().clear();
        MenuItem[] traverseList = donutList.getOrder();
        for (int i = 0; i < amountOfDonuts; i++) {
            traverseList[i].itemPrice();                          
            donutOrderView.getItems().add(traverseList[i].toString());
      }
    }

    /**
     * This adds all the donuts made in the UI to the universal order
     * @param event when add to order button is pressed
     */
    @FXML
    void addToOrder(ActionEvent event) {
        //confirmation window
        Alert alert = new Alert(AlertType.CONFIRMATION, "Add Order?" , ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            amountOfDonuts = donutList.getNumItems();        
            MenuItem[] traverseList = donutList.getOrder();
            //increments subtotal based on arraylist
            for (int i = 0; i < amountOfDonuts; i++) {
                //temp donut for updating list price
                Donut temp = (Donut) traverseList[i];
                order.add(temp);
            
            }

        menuController.setOrder(order);
        this.order = menuController.getOrder();  
        
        removeDonutButton.setDisable(false);
        }
    }

  
    /**
     * This removes a donut from the donut list
     * @param event when the remove donut button is pressed
     */
    @FXML
    void removeDonut(ActionEvent event) {
    	if(donutOrderView.getSelectionModel().isEmpty() ){
    	    Alert alert = new Alert(Alert.AlertType.NONE);
	        alert.setTitle("ALERT!");
	        alert.setHeaderText("No Selection Warning");
	        alert.setContentText("Please select a donut before attempting to remove it");
	        alert.getButtonTypes().addAll(ButtonType.OK);
	        alert.showAndWait();
	        return;
        }
    	
    	
        String selectedDonut = donutOrderView.getSelectionModel().getSelectedItem();
    	StringTokenizer st = new StringTokenizer(selectedDonut, "::");
    	
    	st.nextToken();
    	String typeToken  = st.nextToken();
        st.nextToken(); 
    	String flavorToken = st.nextToken();
    	
    	Donut temp = new Donut(typeToken, flavorToken); 
        donutList.remove(temp);
        
        
        //if list is empty, disable add to order button
        if(donutList.getNumItems() == 0) {
        	addToOrderButton.setDisable(true);
        }
        
        donutOrderUpdater();
        subtotal(event);
    }

    /**
     * This method creates a donut object based on quantity, type, and flavor
     */
    void donutCreator(){
        donut = new Donut();

        if(!donutQuantityButton.getSelectionModel().isEmpty() && !donutTypeButton.getSelectionModel().isEmpty() 
        		&& !flavorsView.getSelectionModel().isEmpty()) {
        	addDonutButton.setDisable(false);
            removeDonutButton.setDisable(false);

        }
        
    	//SET QUANTITY
    	if (!donutQuantityButton.getSelectionModel().isEmpty()) {
            donut.setQuantity((int) donutQuantityButton.getValue());
        }  
    	
    	///SET TYPE
    	if (!donutTypeButton.getSelectionModel().isEmpty()) {
            donut.setType(donutTypeButton.getValue());
        }
    	
    	//SETFLAVOR
    	if (!flavorsView.getSelectionModel().isEmpty()) {
            donut.setFlavor(flavorsView.getSelectionModel().getSelectedItem());
        }
    	
    	//calculate price
    	donut.itemPrice();
    }


    /**
     * This calculates the subtotal and occupies the subtotal field
     * @param event when add/remove donut button is called
     */
    @FXML
    void subtotal(ActionEvent event) {            
    	double subtotal = 0.0;
    	
        // test case for if quantity is selected and donut type is not 
    	if(donutQuantityButton.getSelectionModel().isEmpty() || donutTypeButton.getSelectionModel().isEmpty()) {
    		return; 
    	}

    	//dynamically update based on current comboboxes 
    	donutCreator();
    	
        amountOfDonuts = donutList.getNumItems();        
        MenuItem[] traverseList = donutList.getOrder();
        //increments subtotal based on arraylist
        for (int i = 0; i < amountOfDonuts; i++) {
            //temp donut for updating list price
            Donut temp = (Donut) traverseList[i];
            temp.itemPrice();
            
            subtotal += temp.getPrice();
      }
        
        //subtotal Formatting
        DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
        String subtotalString = dec.format(subtotal);
        subtotalButton.setText(subtotalString);
    }

    
    /**
     * This method is ran when the FXML file is first started
     */
    @FXML
    void initialize() {
        assert donutTypeButton != null : "fx:id=\"donutTypeButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert subtotalButton != null : "fx:id=\"subtotalButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert flavorsView != null : "fx:id=\"flavorsView\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert donutOrderView != null : "fx:id=\"donutOrderView\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert donutQuantityButton != null : "fx:id=\"donutQuantityButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert addDonutButton != null : "fx:id=\"addDonutButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert removeDonutButton != null : "fx:id=\"removeDonutButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        assert addToOrderButton != null : "fx:id=\"addToOrderButton\" was not injected: check your FXML file 'OrderingDonuts.fxml'.";
        
        donutTypeButton.getItems().addAll("yeast", "cake", "donutHole");
        
        for (int i = MIN_QUANTITY; i <= MAX_QUANTITY; i++){
            donutQuantityButton.getItems().add(i);
        }
            
        flavorsView.getItems().addAll("vanilla", "chocolate", "strawberry");
        subtotalButton.setText(ZERO_DOLLARS);
        addDonutButton.setDisable(true);
        removeDonutButton.setDisable(true);
        addToOrderButton.setDisable(true);
       
    }
}
