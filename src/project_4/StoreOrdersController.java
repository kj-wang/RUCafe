package project_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * This class serves as a controller for store Orders. It references store order from menucontroller and then displays the multiple orders
 * 
 * @author KJ Wang, Mehdi Kamal
 */
public class StoreOrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Button exportOrdersButton;

    @FXML
    private TextField totalField;

    @FXML
    private ComboBox<Integer> orderNumberButton;

    @FXML
    private ListView<String> orderView;

    private MenuController menuController;

    private StoreOrders storeOrders;

    private int numOrders;

    private Order selectedOrder;

    private static final double SALES_TAX = 0.06625;

    /**
     * This method sets the main controller to a local controller
     * @param controller controller from main
     */
    public void setMainController(MenuController controller) {
		menuController = controller;
		//update local storeOrder
        storeOrders = menuController.getStoreOrders();
	}

    
    /**
     * This method updates the visuals based on what is in StoreOrders 
     */
       void initializeStoreOrders(){
       
    	//clears visual
        orderView.getItems().clear();
        
        //find amount of orders
        numOrders = storeOrders.getNumOrders();
        
        //add to the combobox according to the amt of orders in storeorder
        if (numOrders > 0) {            
            for (int i = 0; i < numOrders; i++) {
                orderNumberButton.getItems().add(i);
            }
             
            orderNumberButton.getSelectionModel().clearAndSelect(0);

            ActionEvent event = new ActionEvent();
            orderNumberSelect(event);


            //enable remove button if not empty
            if (numOrders != 0) {
                exportOrdersButton.setDisable(false);
                cancelOrderButton.setDisable(false);
            } else {
                exportOrdersButton.setDisable(true);
                cancelOrderButton.setDisable(true);
            }

        }
    }

    /**
     * This method cancels a selected order from storeOrders
     * @param event event when cancel button is clicked
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        int orderNumber = (int) orderNumberButton.getValue();

        //list of orders
        Order[] traverseList = storeOrders.getStoreOrders();
        //selected order and length
        selectedOrder = traverseList[orderNumber];
        
        storeOrders.remove(selectedOrder);
        
        numOrders = storeOrders.getNumOrders();
   
        //removes from combobox
        orderNumberButton.getItems().clear();
        totalField.clear();
        initializeStoreOrders();
     
    }

    /**
     * This method exports the every menu item's to string based on the order it is in into a txt file
     * @param event event when export order button is clicked
     */
    @FXML
    void exportOrders(ActionEvent event) {
    	
    	//Store orders empty
    	if (storeOrders.getNumOrders() == 0) {       
    	    Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("ALERT!");
            alert.setHeaderText("No Order Warning");
            alert.setContentText("Please create Orders before attempting to export the StoreOrder");
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.showAndWait();
            return;
        }
    	 
         
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save");
      fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TextFile", "txt"));
      Stage mainStage = new Stage();
	  File targetFile = fileChooser.showSaveDialog(mainStage); //get the reference of the target file

		//In case file not found
		try (PrintWriter pw = new PrintWriter(targetFile);){
			
			Order[] traverseList = storeOrders.getStoreOrders();
			int amountOfOrders = storeOrders.getNumOrders();
			
			
			for(int i=0; i < amountOfOrders; i++) {
                pw.print("Order Number " + i +"\n");
				pw.print(traverseList[i].exportDatabase() );
			}
          
          
		}
		catch (FileNotFoundException e){
			Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("ALERT!");
            alert.setHeaderText("No File Warning");
            alert.getButtonTypes().addAll(ButtonType.OK);
            alert.showAndWait();
            return;
		}
		catch (NullPointerException d) {
		}      
    }

    /**
     *   Adds all the visual data to the listview
     *   @param event event when the comboBox of orders is selected
     */
    @FXML
    void orderNumberSelect(ActionEvent event) {
    	orderView.getItems().clear();
    	
    	//if a order was just cancelled
    	if(orderNumberButton.getValue() ==null) {
    		return;
    	}
    	
        // print the StoreOrders[orderNumber] value
    	int orderNumber = (int) orderNumberButton.getValue();
    	
        //find amount of orders
        numOrders = storeOrders.getNumOrders();

        //list of orders
        Order[] traverseList = storeOrders.getStoreOrders();
        //selected order and length
        selectedOrder = traverseList[orderNumber];
        //selected order's list of items
        MenuItem[] traverseMenuItem = selectedOrder.getOrder();
        //amount of items
        int numItems = selectedOrder.getNumItems();

        //get selected from combobox and display            
        for (int i = 0; i < numItems; i++) {
                orderView.getItems().add(traverseMenuItem[i].toString()); //add to viewList
        }
        total(event);
    }

    /**
     * This function calculates the total to be displayed and updates the textbox
     * @param event event when selected order is changed
     */
    @FXML
    void total(ActionEvent event) {
        if (numOrders > 0) {
            //Order[] traverseList = storeOrders.getStoreOrders();
            double subtotal = selectedOrder.calculate();                    
            double salesTax = subtotal * SALES_TAX;
            double total = subtotal + salesTax;


            //formatting
            DecimalFormat dec = new DecimalFormat("$###,###,###,##0.00");
            String totalString = dec.format(total);
            totalField.setText(totalString);
        }
    }

    /**
     * This method initializes when the controller is first created and disables cancelorders and exportorders
     */
    @FXML
    void initialize() {
        assert cancelOrderButton != null : "fx:id=\"cancelOrderButton\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert exportOrdersButton != null : "fx:id=\"exportOrdersButton\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert totalField != null : "fx:id=\"totalField\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert orderNumberButton != null : "fx:id=\"orderNumberButton\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert orderView != null : "fx:id=\"orderView\" was not injected: check your FXML file 'StoreOrders.fxml'.";

        cancelOrderButton.setDisable(true);
        exportOrdersButton.setDisable(true);
    }
}
