import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.logging.Level;
import java.util.logging.Logger;

//Possible Logging
public class DatabaseConn {
	// #region Constructors
	/**
	 * Blank Constructor to manipulate 
	 */
	public DatabaseConn()
	{
		createConnection();
		closeConnection();
	}
	/**
	 * Create a database class with a custom connection
	 * @param inDatabaseURL
	 * The URL of the database 
	 */
	public DatabaseConn(String inDatabaseURL)
	{
		databaseURL = inDatabaseURL;
		createConnection();
	}
	//#endregion
	//#region Variables
	/**
	 * The driver used to communicate with the MySQL database
	 */
	static final String databaseDriver = "com.mysql.jdbc.Driver";
	/**
	 * The URL of the database you wish to access
	 */
	static String databaseURL = "jdbc:mysql://Localhost/nbgdatabase";
	/**
	 * The user name used to log into the database server
	 */
	static String user = "mUser";
	/**
	 * The password used to access the database server
	 */
	static String password = "password";
	/**
	 * The connection used in order to link the program with the database
	 */
	static Connection conn;
	/**
	 * The statement that contains the SQL that you wish to submit to the server
	 */
	static Statement statement;
	private static Logger logger = Logger.getLogger(DatabaseConn.class.getName());
	//#endregion
	//#region Methods
	//#region Variable Methods
	public static String getDatabaseURL() {
		return databaseURL;
	}
	public static void setDatabaseURL(String databaseURL) {
		DatabaseConn.databaseURL = databaseURL;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		DatabaseConn.user = user;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		DatabaseConn.password = password;
	}
	public static String getDatabasedriver() {
		return databaseDriver;
	}
	//#endregion
	/**
	 * Creates and confirms a connection has been made to the required server
	 */
	public void createConnection()
	{
		logger.entering(getClass().getName(),"Entering Create Connection Method : " + InventoryManagementSystem.DateTime());
		try
		{
			Class.forName(databaseDriver);
			conn = DriverManager.getConnection(databaseURL,user,password);
			System.out.println("Connection To database created");
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Connection Error", "DBC019", e, Level.SEVERE);
		}
		logger.exiting(getClass().getName(), "Leaving create Connection Method : " + InventoryManagementSystem.DateTime());
	}
	/**
	 * Sends a SQL request to the database to create a record of the given product
	 * @param inProduct
	 * The product that is going to be sent to the SQL server for storage
	 */
	public void CreateProductEntry(Product inProduct)
	{
		createConnection();
		try
		{
			statement = conn.createStatement();
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Creation Error", "DB02",e);
		}
		int productID = inProduct.productID();
		//Check if the product ID is available, if it isn't then assign it a new product ID//TODO Possible
		if(productID <= InventoryManagementSystem.CatalogCount())
		{
			productID = InventoryManagementSystem.CatalogCount()+1;
		}
		String defaultString = "INSERT INTO product VALUE (" + productID+ ", '" +inProduct.ProductName() + "', " + inProduct.ProductStock() + ", " + inProduct.RequiredStock() + ", " + inProduct.CriticalLevel()+ ", " + inProduct.ProductCost() + ", " + inProduct.stockChangeSinceLastReport() + ", " + inProduct.CurrentInOrder() + ", " + "0 )";
		try
		{
			statement.executeUpdate(defaultString);
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Creation Error", "DB03",e);
		}
		closeConnection();
	}
	/**
	 * Read a product from the database
	 */
	public void readProductEntry()
	{
		createConnection();
		Product tempProduct;
		try
		{
			statement = conn.createStatement();
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Read Error", "DB04",e, Level.SEVERE);
		}
		String defaultString = "SELECT ProductID, ProductName, Stock, RequiredStock, CriticalLevel, Cost, sinceLastPurchase, currentInOrder FROM Product";
		try
		{
			ResultSet results = statement.executeQuery(defaultString);
			while(results.next())
			{
				//FIXME this code is not running
				int prodID = results.getInt("ProductID");
				String prodName = results.getString("ProductName");
				int prodStock = results.getInt("Stock");
				int prodReqStock = results.getInt("RequiredStock");
				int prodCriticalLevel = results.getInt("CriticalLevel");
				int prodCost = results.getInt("Cost");
				int prodLastPurchase = results.getInt("sinceLastPurchase");
				int prodCurrentInOrder = results.getInt("currentInOrder");
				
				tempProduct = new Product(prodID, prodName, prodStock, prodReqStock, prodCriticalLevel, prodCost, prodLastPurchase, prodCurrentInOrder);
				InventoryManagementSystem.addProduct(tempProduct);
			}
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Read Error", "DB05",e, Level.SEVERE);
		}
		closeConnection();
	}
	/**
	 * Close the connection to the database
	 */
	public void closeConnection()
	{
		try
		{
			conn.close();
			System.out.println("Connection To database closed");
		}
		catch (SQLException e)
		{
			InventoryManagementSystem.ErrorAlert("Cannot disconnect from database","DB06" , e, Level.SEVERE);
		}
		try
		{
			if(conn != null)
			{
				conn.close();
				System.out.println("Connection To database closed");
			}	
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Cannot disconnect from database","DB07" , e, Level.SEVERE);
		}
		
	}
	//SQL UPDATE METHOD
	/**
	 * Allows the edited version of a product to be saved into the database 
	 * @param inProduct
	 * The product which has been edited from the database variation
	 */
	public void UpdateProduct(Product inProduct)
	{
		try
		{
			String updateString = String.format(
					"ProductName=\"%s\",Stock=%s,RequiredStock=%s,CriticalLevel=%s,Cost=%s,sinceLastPurchase=%s,currentInOrder=%s",
					inProduct.ProductName(),
					inProduct.ProductStock(),
					inProduct.RequiredStock(),
					inProduct.CriticalLevel(),
					inProduct.ProductCost(),
					inProduct.stockChangeSinceLastReport(),
					inProduct.CurrentInOrder()					
					);
			String updateConditions = "productID = " + Integer.toString(inProduct.productID());
			UpdateSQL("product",updateString, updateConditions);
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Cannot update product", "SQLU002", e, Level.SEVERE);
		}
	}
	/**
	 * Generate update method formatting for SQL update requests
	 * @param inTable
	 * @param inObject
	 */
	private void UpdateSQL(String inTable, String inRequest, String inCondition)
	{
		createConnection();
		try
		{
			statement = conn.createStatement();
			String updateString = String.format("UPDATE %s SET %s WHERE %s; ", inTable, inRequest, inCondition);
			statement.executeUpdate(updateString);
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Update Error", "SQLU001", e, Level.SEVERE);
		}
		closeConnection();
	}
	//SQL DELETE METHOD
	/**
	 * Allows you to delete a product from the catalogue
	 */
	public void DeleteProduct(Product inProduct)
	{
		try
		{
			String updateString = String.format(
					"ProductName=\"%s\",Stock=%s,RequiredStock=%s,CriticalLevel=%s,Cost=%s,sinceLastPurchase=%s,currentInOrder=%s",
					inProduct.ProductName(),
					inProduct.ProductStock(),
					inProduct.RequiredStock(),
					inProduct.CriticalLevel(),
					inProduct.ProductCost(),
					inProduct.stockChangeSinceLastReport(),
					inProduct.CurrentInOrder()					
					);
			String updateConditions = "productID = " + Integer.toString(inProduct.productID());
			UpdateSQL("product",updateString, updateConditions);
			//Possible variable length update statements
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Cannot update product", "SQLU002", e, Level.SEVERE);
		}
	}
	/**
	 * General SQL compiler for delete methods
	 */
	private void DeleteItem()
	{
		//SQL Delete item
		createConnection();
		closeConnection();
	}
		//SQL PRODUCT SPERCIFIC METHODS
	//#endregion
}
