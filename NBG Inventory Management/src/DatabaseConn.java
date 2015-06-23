import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseConn {
	//#region Constructors
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
	static Connection conn = null;
	/**
	 * The statement that contains the SQL that you wish to submit to the server
	 */
	static Statement statement = null;
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
		try
		{
			Class.forName(databaseDriver);
			DriverManager.getConnection(databaseURL,user,password);
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Connection Error", "DBC01", e);
		}
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
		String defaultString = String.format(
				"INSERT INTO %d VALUES (%d,%d,%d,%d,%d,%d,%d,%d)",
				inProduct.productID(),
				inProduct.ProductName(),
				inProduct.ProductStock(),
				inProduct.RequiredStock(),
				inProduct.CriticalLevel(),
				inProduct.ProductCost(),
				inProduct.stockChangeSinceLastReport(),
				inProduct.CurrentInOrder()
				);
		try
		{
			statement.executeUpdate(defaultString);
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Creation Error", "DB03",e);
		}
	}
	public void readProductEntry()
	{
		Product tempProduct;
		try
		{
			statement = conn.createStatement();
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Read Error", "DB04",e);
		}
		String defaultString = "SELECT ProductID, Product Name, Stock, Requried Stock, Critical Level, Cost, sinceLastPurchase, currentInOrder";
		try
		{
			ResultSet results = statement.executeQuery(defaultString);
			while(results.next())
			{
				int prodID = results.getInt("ProductID");
				String prodName = results.getString("Product Name");
				int prodStock = results.getInt("Stock");
				int prodReqStock = results.getInt("Required Stock");
				int prodCriticalLevel = results.getInt("Critical Level");
				int prodCost = results.getInt("Cost");
				int prodLastPurchase = results.getInt("sinceLastPurchase");
				int prodCurrentInOrder = results.getInt("currentInOrder");
				
				tempProduct = new Product(prodID, prodName, prodStock, prodReqStock, prodCriticalLevel, prodCost, prodLastPurchase, prodCurrentInOrder);
			}
		}
		catch (Exception e)
		{
			InventoryManagementSystem.ErrorAlert("Database Record Read Error", "DB05",e);
		}
	}
	//#endregion
}
