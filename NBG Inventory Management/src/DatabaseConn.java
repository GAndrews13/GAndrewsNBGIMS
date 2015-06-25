import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO Logging


public class DatabaseConn {
	// #region Constructors
	/**
	 * Blank Constructor to manipulate 
	 */
	public DatabaseConn()
	{
		createConnection();
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
			System.out.println("Connection To Datebase Complete");
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
		try
		{
			statement = conn.createStatement();
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Creation Error", "DB02",e);
		}
		//String defaultString = String.format("INSERT INTO product (productID,productName,productStock,requiredStock,criticalLevel,productCost,stockChangeSinceLastReport,currentInOrder) VALUES (%d,'%d',%d,%d,%d,%d,%d,%d.%d)", inProduct.productID(), inProduct.ProductName(), inProduct.ProductStock(),	inProduct.RequiredStock(),	inProduct.CriticalLevel(),	inProduct.ProductCost(), inProduct.stockChangeSinceLastReport(), inProduct.CurrentInOrder());
		String defaultString = "INSERT INTO product VALUE (" + inProduct.productID()+ ", '" +inProduct.ProductName() + "', " + inProduct.ProductStock() + ", " + inProduct.RequiredStock() + ", " + inProduct.CriticalLevel()+ ", " + inProduct.ProductCost() + ", " + inProduct.stockChangeSinceLastReport() + ", " + inProduct.CurrentInOrder() + ", " + "0 )";
		
		System.out.println(defaultString);
		try
		{
			statement.executeUpdate(defaultString);
			System.out.println("Product Created");
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Creation Error", "DB03",e);
		}
	}
	/**
	 * Read a product from the database
	 */
	public void readProductEntry()
	{
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
			System.out.println("test");
			while(results.next())
			{
				//FIXME this code is not running
				System.out.println("test 2");
				int prodID = results.getInt("ProductID");
				String prodName = results.getString("ProductName");
				int prodStock = results.getInt("Stock");
				int prodReqStock = results.getInt("RequiredStock");
				int prodCriticalLevel = results.getInt("CriticalLevel");
				int prodCost = results.getInt("Cost");
				int prodLastPurchase = results.getInt("sinceLastPurchase");
				int prodCurrentInOrder = results.getInt("currentInOrder");
				
				tempProduct = new Product(prodID, prodName, prodStock, prodReqStock, prodCriticalLevel, prodCost, prodLastPurchase, prodCurrentInOrder);
				//Debug code to print product info
				tempProduct.SystemWrite();
				//FIXME help
				InventoryManagementSystem.addProduct(tempProduct);
			}
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Read Error", "DB05",e, Level.SEVERE);
		}
	}
	/**
	 * Close the connection to the database
	 */
	public void closeConnection()
	{
		try
		{
			conn.close();
			System.out.println("Connection To Database Closed");
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
				System.out.println("Connection To Database Closed");
			}	
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Cannot disconnect from database","DB07" , e, Level.SEVERE);
		}
		
	}
	//SQL UPDATE METHOD
	//SQL DELETE METHOD
	//SQL PRODUCT SPERCIFIC METHODS
	//UI TABLE METHODS
	//#endregion
}
