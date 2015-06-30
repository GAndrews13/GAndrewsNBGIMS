import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.logging.Level.*;
import java.util.Date;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import java.io.File;

public class InventoryManagementSystem {
	public InventoryManagementSystem()
	{
		//Create the file for collecting reports
		File directory = new File("Reports");
		try
		{
			directory.mkdir();
		}
		catch (Exception e) 
		{
			ErrorAlert("Could not create report directory", "Error creating report directory", e);
		}
		
		//Product tempProduct = new Product(DateTime(), 1, 13, 13, 11, 1000);
		databaseConnection.readProductEntry();
		//databaseConnection.CreateProductEntry(tempProduct);
		//tempProduct.ProductName(DateTime());
		//UpdateProduct(tempProduct);
		//writeToTxt();
		//#region Demo Code
		//#region Increasing stock message
		//	List<ProductOrderLine> pll = new ArrayList<ProductOrderLine>();
		//	ProductOrderLine pl = new ProductOrderLine(lastOrderLineCreated, productCatalog.get(4));
		//	ProductOrderLine pl2 = new ProductOrderLine(lastOrderLineCreated, productCatalog.get(7));
		//	ProductOrderLine pl3 = new ProductOrderLine(lastOrderLineCreated, productCatalog.get(1));
		//	pl.ChangeQuantity(15);
		//	pl2.ChangeQuantity(50);
		//	pl3.ChangeQuantity(5);
		//	pll.add(pl);
		//	pll.add(pl2);
		//	pll.add(pl3);
			//StockIncreaseAlert("Products Recieved: ", "Product Order Recieved: " + lastOrderLineCreated, pll);
		//#endregion
		//#region error message
			//InventoryManagementSystem.ErrorAlert("Database Connection Error", "DBC019", new Exception("No database found"), Level.SEVERE);
		//#endregion
		//#region Low Stock message
			//Product tempProduct = productCatalog.get(1);
			//tempProduct.RemoveStock(tempProduct.ProductStock()-1);
			//updateProduct(tempProduct.ObjectArray());
		//#endregion
		//#region end of demo
	}
	
	// #region variables
	/**
	 * Creates and retains the connection to the database as well as methods involving the database
	 */
	private static DatabaseConn databaseConnection = new DatabaseConn();
	/**
	 * The logger used to record all errors that occur within the InventoryManagementSystem.java runtime
	 */
	private static final Logger logger = Logger.getLogger(InventoryManagementSystem.class.getName());
	/**
	 * The ID of the last sale order created
	 */
	private static int lastSaleCreated = 0;
	/**
	 * The ID of the last Invoice created
	 */
	private static int lastInvoiceCreated = 0;
	/**
	 * ID of the last order line created
	 */
	private static int lastOrderLineCreated = 0;
	/**
	 * The array of products stored within the system
	 */
	private static List<Product> productCatalog = new ArrayList<Product>();
	/**
	 * the directory in which all reports are being exported to by default
	 */
	private String reportDirectory = System.getProperty("user.dir")+ "\\Reports\\";
	//#endregion
	// #region Methods
		/**
	 * Updates all product information into the database
	 */
	public void DatabaseSave()
	{
		for(int i = 0; i<productCatalog.size();i++)
		{
			updateProduct(productCatalog.get(i));
		}
		JOptionPane.showMessageDialog(null,"Database Saved ","Save Method Complete",  JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * Loads the database refresh
	 */
	public void DatabaseLoad()
	{
		for(int i = 0; i< productCatalog.size();i++)
		{
			productCatalog.remove(i);
		}
		//productCatalog = new ArrayList<Product>();
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
	public ArrayList<Product> ProductLists()
	{
		return (ArrayList<Product>) productCatalog;
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
			String finalString = "\r\n";
			for(int i = 0; i<inProducts.size();i++)
			{
				//text
				finalString += String.format("Product Name: (%s) %-35s Quantity: %-35d", inProducts.get(i).Product().productID(),inProducts.get(i).Product().ProductName(),inProducts.get(i).Quantity());
				finalString += "\r\n";
				//Logic
				//update product
				inProducts.get(i).Product().IncreaseStockLevel(inProducts.get(i).Quantity());
				//update database
				updateProduct(inProducts.get(i).Product());
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
			JOptionPane.showMessageDialog(null, "Low Stock:" + " (" + inProductID + ") " + inProductName ,"Low Stock",JOptionPane.WARNING_MESSAGE);
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
							"Product ID: %s %n Product Name: %s %n Current Stock: %s %n Recommended Stock Level: %s %n Critical Stock Level: %s %n Product Cost: %s", 
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
					pw.println(String.format("Product Name: %s \r\n Product Quantity: %s \r\n Product Cost: %s",
							prod.ProductName(),
							changeInStockRequired,changeInStockCost
							));
				}
				//Possible finance changer (100=1£ ext..)
				pw.println("Total Cost: " + totalCost);
				pw.println("Product Order Form For NBGardens");
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
	public static void updateProduct(Object[] inObject)
	{
		for(int i  = 0; i<productCatalog.size();i++)
		{
			try
			{
				//Find matching product in the database based on their unique ID
				if(productCatalog.get(i).productID() == (int)inObject[0])
				{
					int tempID = 0, tempStock = 0, tempReq = 0, tempCrit = 0, tempCost = 0, tempSLP = 0, tempCIO = 0;
					String tempName = "";
					try
					{
						tempID = Integer.parseInt(inObject[0].toString());
						tempName = (String)inObject[1];
						tempStock = Integer.parseInt(inObject[2].toString());
						tempReq = Integer.parseInt(inObject[3].toString());
						tempCrit = Integer.parseInt(inObject[4].toString());
						tempCost = Integer.parseInt(inObject[5].toString());
						tempSLP = Integer.parseInt(inObject[6].toString());
						tempCIO = Integer.parseInt(inObject[7].toString());
						
						Product tempProduct = new Product(tempID, tempName, tempStock, tempReq, tempCrit, tempCost, tempSLP, tempCIO);
						//Throw up messages and alerts //FIXME Messages should throw automatically on adjustments //Remove or remove editing messages
						Product originalProduct = productCatalog.get(i);
						if(tempProduct.ProductStock() <= tempProduct.CriticalLevel())
						{
							LowStockAlert(tempProduct.ProductName(),tempProduct.productID());
						}
						if(tempProduct.ProductStock()> originalProduct.ProductStock())
						{
							ArrayList<ProductOrderLine> tempArray = new ArrayList<ProductOrderLine>();
							tempArray.add(new ProductOrderLine(lastOrderLineCreated,tempProduct));
							StockIncreaseAlert("Stock has increased","The following items have increased: ", tempArray);
						}
						
						productCatalog.set(i, tempProduct);
						try
						{
							databaseConnection.UpdateProduct(tempProduct);
						}
						catch (Exception e) 	
						{
							InventoryManagementSystem.ErrorAlert("Error Updating Products", "IMSUP019", e, Level.WARNING);
						}
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(null,"Value's entered are not of the correct type","Incorrect Value Entered", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch (Exception e)
			{
				InventoryManagementSystem.ErrorAlert("Error Updating Products", "IMSUP001", e, Level.WARNING);
			}
		}
	}
	/**
	 * Allows a product to be updated in the database
	 * @param inProduct
	 */
	public static void updateProduct(Product inProduct)
	{
		try
		{
			databaseConnection.UpdateProduct(inProduct);
			System.out.println("Product Changed: " + inProduct.ProductName());
		}
		catch (Exception e) 
		{
			InventoryManagementSystem.ErrorAlert("Error Updating Products", "IMSUP019", e, Level.WARNING);
		}
	}
	/**
	 * Refreshes the database
	 */
	public static void ReadDatabase()
	{
		productCatalog.removeAll(productCatalog);
		databaseConnection.readProductEntry();
	}
	//#endregion
}
