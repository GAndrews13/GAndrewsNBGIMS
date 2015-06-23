import java.util.List;
import java.util.logging.*;

import javax.swing.JOptionPane;

public class InventoryManagementSystem {
// #region variables
	/**
	 * The logger used to record all errors that occur within the InventoryManagementSystem.java runtime
	 */
	private static final Logger logger = Logger.getLogger(InventoryManagementSystem.class.getName());
	/**
	 * The ID of the last sale order created
	 */
	private int lastSaleCreated = 0;
	/**
	 * The ID of the last Invoice created
	 */
	private int lastInvoiceCreated = 0;
	/**
	 * ID of the last order line created
	 */
	private int lastOrderLineCreated = 0;
	/**
	 * The array of products stored within the system
	 */
	private List<Product> productCatalog;
	//#endregion
	// #region Methods
	//#region Variable Accessors
	/**
	 * Allows you to add a new product
	 */
	public void addProduct()
	{
		Product item = new Product("Bieber Gnome",0001,10,7,4,15);
		productCatalog.add(item);
		item = new Product("Bieber Grenade",0001,10,7,4,15);
		productCatalog.add(item);
	}
	/**
	 * Returns the ID of the lastSaleCreated
	 * @return
	 */
	public int LastSaleCreated()
	{
		return lastSaleCreated;
	}
	/**
	 * Returns the ID of the last invoice created within the system
	 * @return
	 */
	public int LastInvoiceCreated()
	{
		return lastInvoiceCreated;
	}
	/**
	 * Returns the ID of the last order line item created
	 * @return
	 */
	public int LastOrderLineCreated()
	{
		return lastOrderLineCreated;
	}
	//#endregion
	static void main(String[] args)
	{
		
	}
	/**
	 * A general alert message should the user need be informed about anything during the programs running
	 * @param inTitle
	 * The message title provided
	 * @param inMessage
	 * The message provided
	 */
	static void GeneralAlert(String inTitle, String inMessage)
	{
		//TODO display alert message
		JOptionPane.showMessageDialog(null, inMessage, "Alert: " + inTitle, JOptionPane.INFORMATION_MESSAGE);
	}
/**
 * Displays an alert informing the user that an invoice has come in and the listed products have had their stock increased	
 * @param inTitle
 * The title of the alert
 * @param inMessage
 * he message contained within the alert
 * @param inProducts
 * The list of products that have had their stock levels adjusted
 */
static void StockIncreaseAlert(String inTitle, String inMessage, List<Product> inProducts)
	{
		String finalString = "";
		for(int i = 0; i<inProducts.size();i++)
		{
			finalString += inProducts.get(i).ProductName()+"("+inProducts.get(i).productID()+(")");
			finalString += "/n";
		}
		JOptionPane pane = new JOptionPane();
		pane.setSize(500, 200*inProducts.size());
		pane.showMessageDialog(null,"Stock Recieved: " + finalString, "Stock Recieved", JOptionPane.INFORMATION_MESSAGE);
	}
/**
 * Displays a message alerting the user to an error that has arisen	
 * @param inTitle
 * the title given to the error by the program
 * @param inLocation
 * the message set by the program detailing its location within the code
 * @param  inE
 * The actual error that has generated the alert
 */
static void ErrorAlert(String inTitle, String inLocation, Exception inE)
	{
		//TODO display alert message#
		
		JOptionPane.showMessageDialog(null, inLocation + ": " + inE.getMessage(), "Error: " + inTitle, JOptionPane.ERROR_MESSAGE);
	}
/**
 * Displays a message alerting the users to low stock for a particular item	
 * @param inProductName
 * The products name
 * @param inProductID
 * the products unique ID
 */
static void LowStockAlert(String inProductName, int inProductID)
	{
		JOptionPane.showMessageDialog(null, "Low Stock:" + inProductName + "(" + inProductID + ")","Low Stock",JOptionPane.WARNING_MESSAGE);
	}
	//#endregion
}
