package project_4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * This class serves as a controller for the menu, the initial page, and stores the StoreOrder and Order to be transferred between controllers
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainMenu;

    @FXML
    private Button DonutOrderButton;

    @FXML
    private Button coffeeButton;

    @FXML
    private Button currentOrderButton;

    @FXML
    private Button storeOrderButton;

    private Order order = new Order();

    private StoreOrders storeOrders = new StoreOrders();

    /**
     * This is a getter for store orders
     * @return storeOrders object
     */
    public StoreOrders getStoreOrders(){
        return storeOrders;
    }
  
    /**
     * This is a getter for order
     * @return order object
     */
    public Order getOrder(){
        return order;
    }
    
    /**
     * This is a setter for order
     * @param order order object
     */
    public void setOrder(Order order) {
    	this.order = order;

    }

    /**
     * This is a getter for store order
     * @return store order object
     */
    public StoreOrders getStoreOrder(){
        return storeOrders;
    }
    
    /**
     * This is a setter for store order
     * @param storeOrders storeOrder that is being set
     */
    public void setStoreOrders(StoreOrders storeOrders) {
    	this.storeOrders = storeOrders;
    }

    /**
     * This is a getter for numItems
     * @return getNums from the order
     */
    public int getNumItems(){
        return order.getNumItems();
    }

    /**
     * This is used to open coffee order and set the main controller
     * @param event when the coffee button is opened
     */
    @FXML
    void openCoffeeOrder(ActionEvent event) {
    	BorderPane root = null;
		Stage window = new Stage();
		try {		
			//Loader stuff
			FXMLLoader coffeeLoader = new FXMLLoader(getClass().getResource("OrderingCoffee.fxml")); //creates loader based on ordering cofee
			root = (BorderPane) coffeeLoader.load(); //creates new page based on coffee loader
			
			//visuals
			window.setTitle("Ordering Coffee");
			window.setScene(new Scene(root,750,560));
			window.setResizable(false);
			
			//transfer MainMenu order to coffeeController
			OrderingCoffeeController orderingCoffeeController = coffeeLoader.getController(); // reference of the coffee controller
			orderingCoffeeController.setMainController(this); 
           
			window.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * This is used to open donut order and set the main controller
     * @param event when the donut button is opened
     */
    @FXML
    void openDonutOrder(ActionEvent event) {
	    BorderPane root = null;
		Stage donutWindow = new Stage();
		try {
			//Loader stuff
            FXMLLoader donutLoader = new FXMLLoader(getClass().getResource("OrderingDonuts.fxml")); // set loader to reference fxml
			root = (BorderPane) donutLoader.load();

            //Visuals
			donutWindow.setTitle("Ordering Donuts");
			donutWindow.setScene(new Scene(root,780,520));
			donutWindow.setResizable(false);
			
			
			//transfer MainMenu order to donutController
			OrderingDonutsController orderingDonutsController = donutLoader.getController(); 
            orderingDonutsController.setMainController(this); 
           
			
			donutWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * This is used to open current order and set the main controller
     * @param event when the current order button is pressed
     */
    @FXML
    void openCurrentOrder(ActionEvent event) {
    	BorderPane root = null;
		Stage currentOrderWindow = new Stage();
		try {
			//Loader stuff
            FXMLLoader currentOrderLoader = new FXMLLoader(getClass().getResource("CurrentOrder.fxml")); 
			root = (BorderPane) currentOrderLoader.load();

            //Visuals
			currentOrderWindow.setTitle("Current Order");
			currentOrderWindow.setScene(new Scene(root,550,450));
			currentOrderWindow.setResizable(false);
			
			
			//transfer MainMenu order to donutController
			CurrentOrderController currentOrderController = currentOrderLoader.getController(); 
            currentOrderController.setMainController(this); 
            currentOrderController.initializeOrder();
           
			
			currentOrderWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * This is used to open store orders and set the main controller
     * @param event when the store orders button is pressed
     */
    @FXML
    void openStoreOrder(ActionEvent event) {
        BorderPane root = null;
		Stage storeOrderWindow = new Stage();
		try {
			//Loader stuff
            FXMLLoader storeOrderLoader = new FXMLLoader(getClass().getResource("StoreOrders.fxml")); // set loader to reference fxml
			root = (BorderPane) storeOrderLoader.load();

            //Visuals
			storeOrderWindow.setTitle("Store Order");
			storeOrderWindow.setScene(new Scene(root,600,400));
			storeOrderWindow.setResizable(false);
			
			
			//transfer MainMenu order to donutController
			StoreOrdersController storeOrdersController = storeOrderLoader.getController(); 
            storeOrdersController.setMainController(this); 
            storeOrdersController.initializeStoreOrders();
           
			
			storeOrderWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * This method is ran when the FXML file is first started
     */
    @FXML
    void initialize() {
        assert mainMenu != null : "fx:id=\"mainMenu\" was not injected: check your FXML file 'Menu.fxml'.";
        assert DonutOrderButton != null : "fx:id=\"DonutOrderButton\" was not injected: check your FXML file 'Menu.fxml'.";
        assert coffeeButton != null : "fx:id=\"coffeeButton\" was not injected: check your FXML file 'Menu.fxml'.";
        assert currentOrderButton != null : "fx:id=\"currentOrderButton\" was not injected: check your FXML file 'Menu.fxml'.";
        assert storeOrderButton != null : "fx:id=\"storeOrderButton\" was not injected: check your FXML file 'Menu.fxml'.";

    }
}

