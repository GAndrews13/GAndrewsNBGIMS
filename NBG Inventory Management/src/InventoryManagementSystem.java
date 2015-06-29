import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.logging.Level.*;
import java.util.Date;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;


public class InventoryManagementSystem {
	public InventoryManagementSystem()
	{
		//Remove
		//Product tempProduct = new Product(DateTime(), 1, 13, 13, 11, 1000);
		databaseConnection.readProductEntry();
		//databaseConnection.CreateProductEntry(tempProduct);
		//tempProduct.ProductName(DateTime());
		//databaseConnection.UpdateProduct(tempProduct);
		writeToTxt();
	}
	
	// #region variables
	/**
	 * Creates and retains the connection to the database as well as methods involving the database
	 */
	private DatabaseConn databaseConnection = new DatabaseConn();
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
	private static List<Product> productCatalog = new ArrayList<Product>();
	/**
	 * the directory in which all reports are being exported to by default
	 */
	private String reportDirectory = "C:/Users/gandrews/workspace/GandrewsNBGIMS/Report Directory/";
	//#endregion
	// #region Methods
		/**
	 * Updates all product information into the database
	 */
	public void DatabaseSave()
	{
		for(int i = 0; i<productCatalog.size();i++)
		{
			databaseConnection.UpdateProduct(productCatalog.get(i));
		}
		JOptionPane.showMessageDialog(null,"Database Saved ","Save Method Complete",  JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Loads the database refresh
	 */
	public void DatabaseLoad()
	{
		productCatalog = new ArrayList<Product>();
		databaseConnection.readProductEntry();
		JOptionPane.showMessageDialog(null,"Database Loaded","Load Method Complete",  JOptionPane.INFORMATION_MESSAGE);
	}
	//#region Variable Accessors
	/**
	 * Allows you to change the directory of generated stock reports
	 * @param inString
	 */
	public void ReportDirectory(String inString)
	{
		reportDirectory = inString;
	}
	/**
	 * The Directory which automatically generated reports are saved
	 * @return
	 */
	public String ReportDirectory()
	{
		return reportDirectory;
	}
		/**
	 * Adds a precreated product to the digital record
	 * @param inProduct
	 */
	public static void addProduct(Product inProduct)
	{
		productCatalog.add(inProduct);
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
	/**
	 * Returns the list of products gathered
	 * @return
	 */
	public List<Product> ProductLists()
	{
		return productCatalog;
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
		logger.log(Level.FINE, "General Alert: " + inTitle);
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
	@SuppressWarnings("static-access") //Reason being that the custom size is required
	static void StockIncreaseAlert(String inTitle, String inMessage, List<ProductOrderLine> inProducts)
		{
			String finalString = "";
			for(int i = 0; i<inProducts.size();i++)
			{
				finalString += inProducts.get(i).Product().ProductName()+"("+inProducts.get(i).Product().productID()+(")");
				finalString += "/n";
			}
			logger.log(Level.FINE, "Stock Increased: " + inTitle);
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
	static public int CatalogCount() 
	{
		return productCatalog.size();
	}
	static void ErrorAlert(String inTitle, String inLocation, Exception inE)
		{	
			logger.log(Level.WARNING, "Error Recived", inE);
			JOptionPane.showMessageDialog(null, inLocation + ": " + inE.getMessage() + ": " + inE.getCause(), "Error: " + inTitle, JOptionPane.ERROR_MESSAGE);
		}
	/**
	 * Retrives the id of the last sale order created
	 * @return
	 */
	public int getLastSaleCreated() {
		return lastSaleCreated;
	}
	/**
	 * sets the last sale order ID
	 * @param lastSaleCreated
	 */
	public void setLastSaleCreated(int lastSaleCreated) {
		this.lastSaleCreated = lastSaleCreated;
	}
	/**
	 * Returns the last product invoice order ID
	 * @return
	 */
	public int getLastInvoiceCreated() {
		return lastInvoiceCreated;
	}
	/**
	 * Allows you to set the last invoice id that was used
	 * @param lastInvoiceCreated
	 */
	public void setLastInvoiceCreated(int lastInvoiceCreated) {
		this.lastInvoiceCreated = lastInvoiceCreated;
	}
	/**
	 * returns the last orderline item ID that was used
	 * @return
	 */
	public int getLastOrderLineCreated() {
		return lastOrderLineCreated;
	}
	/**
	 * sets the last order line created ID
	 * @param lastOrderLineCreated
	 */
	public void setLastOrderLineCreated(int lastOrderLineCreated) {
		this.lastOrderLineCreated = lastOrderLineCreated;
	}
	static void ErrorAlert(String inTitle, String inLocation, Exception inE, Level inLevel)
	{	
		logger.log(inLevel, "Error Recived: "+ inLevel.getName(), inE);
		JOptionPane.showMessageDialog(null, inLocation + ": " + inE.getMessage() + ": " + inE.getCause(), "Error: " + inTitle, JOptionPane.ERROR_MESSAGE);
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
			logger.log(Level.FINE, "Low Stock Reported: " + inProductName + "(" + inProductID + ")");
			JOptionPane.showMessageDialog(null, "Low Stock:" + inProductName + "(" + inProductID + ")","Low Stock",JOptionPane.WARNING_MESSAGE);
		}
	/**
	 * Returns the date and time (used in reporting errors and changes in the system)
	 * @return
	 */
	static String DateTime()
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:MM:SS");
		return dateFormat.format(date);
		
	}
	/**
	 * Returns a useable datetime for file name
	 */
	public String FileNameGenerator()
	{
		String tempDate = DateTime();
		tempDate = tempDate.replace('/', '-');
		tempDate = tempDate.replace(':', '-');
		String temp = reportDirectory+ "ProductReport " + tempDate + ".txt" ;
		return temp;
	}
	public String FileNameGenerator(boolean isOrder)
	{
		String tempDate = DateTime();
		tempDate = tempDate.replace('/', '-');
		tempDate = tempDate.replace(':', '-');
		String temp = reportDirectory+ "Stock Order " + tempDate + ".txt" ;
		return temp;
	}
	/**
	 * Writes out the products stored into a text file, while returning the file location
	 */
	public String writeToTxt()
	{
		PrintWriter writer = null;
		String fileDirectory = FileNameGenerator();
		
		try
		{
			writer = new PrintWriter(fileDirectory, "UTF-8");
			try
			{
				writer.println("<StartOfReport>");
				writer.println("<DateTime: " +DateTime()+">");
				for(int i = 0; i<productCatalog.size();i++)
				{
					writer.println(String.format(
							"Product ID: (%s) Product Name: (%s) Current Stock: (%s) Recommended Stock Level: (%s) Critical Stock Level: (%s) Product Cost: (%s)", 
							productCatalog.get(i).productID(),
							productCatalog.get(i).ProductName(),
							productCatalog.get(i).ProductStock(),
							productCatalog.get(i).RequiredStock(),
							productCatalog.get(i).CriticalLevel(),
							productCatalog.get(i).ProductCost()
							));
					writer.flush();
				}
				writer.println("<EndOfReport>");
				writer.flush();
			}
			catch (Exception e)
			{
				InventoryManagementSystem.ErrorAlert("Error Writing To Report File", "WTT02", e, Level.WARNING);
			}
			finally
			{
				if(writer!=null)
				{
					writer.close();
				}
			}
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Error Writing Report File", "WTT01", e, Level.SEVERE);
		}
		finally
		{
			if(writer!= null)
			{
				writer.close();
			}
		}
		JOptionPane.showMessageDialog(null,"Report Created at " + fileDirectory,"Text Report Created",  JOptionPane.INFORMATION_MESSAGE);
		return fileDirectory;
		
	}
	/**
	 * Generate a product order for low stock and new items
	 */
	public void writeProductOrderDocument()
	{
		List<Product> toBeOrdered = new ArrayList<Product>();
		for(Product prod : productCatalog)
		{
			if(prod.ProductStock() <= prod.CriticalLevel() && prod.ProductStock() != prod.RequiredStock())
			{
				toBeOrdered.add(prod);
			}
		}
		
		if(toBeOrdered.size() > 0)
		{
			String fileDirectory = FileNameGenerator(true);
			PrintWriter pw = null;
			int totalCost = 0;
			try
			{	
				pw = new PrintWriter(fileDirectory,"UTF-8");
				pw.println("Product Order Form");;
				pw.println("Date Of Product Order Creation: " + DateTime());
				for(Product prod : toBeOrdered)
				{
					int changeInStockRequired = prod.RequiredStock() - prod.ProductStock(); 
					int changeInStockCost = changeInStockRequired * prod.ProductCost();
					totalCost += changeInStockCost;
					pw.println(String.format("Name: %s \r\n Product Quantity: %s \r\n Product Cost: %s",
							prod.ProductName(),
							changeInStockRequired,changeInStockCost
							));
				}
				//TODO finance changer (100=1£ ext..)
				pw.println("Total Cost: " + totalCost);
				pw.println("End of Product Order Form");
				pw.flush();
				pw.close();
				JOptionPane.showMessageDialog(null,"Stock Order Created at " + fileDirectory,"Stock Order Report Created",  JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e)
			{
				ErrorAlert("Error creating product order form", "IMSPO001", e, Level.SEVERE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Sufficiant Stock, no order created","No Stock Order Form Created", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Update a product based on the Object array passed
	 */
	public void updateProduct(Object[] inObject)
	{
		for(int i  = 0; i<productCatalog.size();i++)
		{
			if(productCatalog.get(i).productID() == (int)inObject[0])
			{
				Product tempProduct = new Product((int) inObject[0], (String)inObject[1], (int) inObject[2], (int) inObject[3], (int) inObject[4], (int) inObject[5], (int) inObject[6], (int) inObject[7]);
				Product initialProduct = productCatalog.get(i);
				productComparison(initialProduct, tempProduct);
				
				productCatalog.set(i, tempProduct);
			}
		}
	}
	private void productComparison(Product inOriginal, Product inChanged)
	{
		//TODO execute changes
	}
	//#endregion
}
