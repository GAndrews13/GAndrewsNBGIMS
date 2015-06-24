
public class Product {
	// #region Constructors
	@Deprecated
	public Product(String inProductName, int inProductID, int inProductStock, int inProductCost)
	{
		productName = inProductName;
		productID = inProductID;
		productStock = inProductStock;
		productCost = inProductCost;
	}
	public Product(String inProductName, int inID, int inStock, int inProductReqStock, int inCriticalLevel, int inProductCost)
	{
		productName = inProductName;
		productID = inID;
		productStock = inStock;
		requiredStock = inProductReqStock;
		stockCriticalLevel = inCriticalLevel;
		productCost = inProductCost;
	}
	public Product(String inProductName, int inProductID, int inProductReqStock, int inCriticalLevel, int inProductCost)
	{
		productName = inProductName;
		productID = inProductID;
		requiredStock = inProductReqStock;
		stockCriticalLevel = inCriticalLevel;
		productCost = inProductCost;
	}
	/**
	 * The Constructor used for items which have been retrieved from the database
	 */
	public Product(int inProductID, String inProductName, int inProductStock, int inProductRequiredStock, int inProductCriticalLevel, int inProductCost, int inProductLastPurchase, int inProductCurrentInOrder)
	{
		productID = inProductID;
		productName = inProductName;
		productStock = inProductStock;
		requiredStock = inProductRequiredStock;
		stockCriticalLevel = inProductCriticalLevel;
		productCost = inProductCost;
		currentInOrder = inProductCurrentInOrder;
	}
	// #region endregion
	// #region Variables
	/**
	 * The products catalogue name
	 */
	private String productName;
	/**
	 * The products Unique ID provided at insertion into the database
	 */
	private final int productID;
	/**
	 * The Current Stock Levels of the Product
	 */
	private int productStock;
	/**
	 * The stock level that is attained when ordering new stock in 
	 */
	private int requiredStock;
	/**
	 * The level at which stock needs to be reordered
	 */
	private int stockCriticalLevel;
	/**
	 * The number of stock that has been sold since the last report was generated
	 */
	private int stockChangeSinceLast;
	/**
	 * The cost of the product
	 */
	private int productCost;
	/**
	 * The Number of products that are currently being ordered and have yet to arrive
	 */
	private int currentInOrder;
	//#region Variable Manipulators
	/**
	 * Returns the current cost of the product
	 * @return
	 * The cost
	 */
	public int ProductCost()
	{
		return productCost;
	}
	/**
	 * Allows you to set the cost of a product
	 * @param inCost
	 * The desired cost of the product
	 */
	public void ProductCost(int inCost)
	{
		productCost = inCost;
	}
	/**
	 * Returns the number of products that have been purchased and have yet to arrive and inserted into the system
	 * @return
	 */
	public int CurrentInOrder()
	{
		return currentInOrder;
	}
	/**
	 * Adjusts the tracking of the number of products sold since the last stock report 
	 * @param inStockChange
	 */
	private void adjustStockChange(int inStockChange)
	{
		stockChangeSinceLast += inStockChange;
	}
	/**
	 * Allows you to increase the stock level of a product
	 * @param inStockChange
	 * The number of items that are being added to the system
	 */
	public void IncreaseStockLevel(int inStockChange)
	{
		productStock += inStockChange;
		currentInOrder -= inStockChange;
	}
	/**
	 * Used to Reset the number of products sold since the last stock report, used typically after generating a stock report
	 */
	public void resetStockChange()
	{
		stockChangeSinceLast = 0;
	}
	/**
	 * Returns the number of this product that have been sold since the last stock report was created
	 * @return
	 */
	public int stockChangeSinceLastReport()
	{
		return stockChangeSinceLast;
	}
	/**
	 * Returns the name given to the product
	 * @return
	 * The products catalogue name
	 */
	public String ProductName()
	{
		return productName;
	}
	/**
	 * Allows you to set the products catalogue name
	 * @param inProdName
	 * The desired name of the product
	 */
	public void ProductName(String inProdName)
	{
		productName = inProdName;
	}
	/**
	 * Returns the products Unique ID granted to it at Creation
	 * @return
	 * 
	 */
	public int productID()
	{
		return productID;
	}
	/**
	 * Returns the current level of stock recorded in the system
	 * @return
	 */
	public int ProductStock()
	{
		return productStock;
	}
	/**
	 * Removes items from stock, throws a stock alert should the stock go below its critical level 
	 * @param inStockAdj
	 * the number of stock that are being removed
	 */
	public void RemoveStock(int inStockAdj)
	{
		productStock -= inStockAdj;
		if(productStock <= stockCriticalLevel)
		{
			Alert();
		}
	}
	/**
	 * Returns the number of stock that the system will try to attain on creating a purchase order	
	 * @return
	 */
	public int RequiredStock()
	{
		return requiredStock;
	}
	/**
	 * Set the minimum value of stock that should be attained when a purchase order is automatically generated
	 * @param inStockReq
	 */
	public void RequriedStock(int inStockReq)
	{
		requiredStock = inStockReq;
	}
	/**
	 * Returns the level at which a purchase order is automatically generated for the item
	 * @return
	 */
	public int CriticalLevel()
	{
		return stockCriticalLevel;
	}
	/**
	 * Set the level at which products must be reordered
	 * @param inCriticalLevel
	 */
	public void CriticalLevel(int inCriticalLevel)
	{
		stockCriticalLevel = inCriticalLevel;
	}
	// #endregion
	//#endregion
	// #region methods
	/**
	 * Displays an alert when a product reaches its critical stock level
	 */
	private void Alert()
	{
		InventoryManagementSystem.LowStockAlert(productName, productID);
	}
	/**
	 * Writes out the products information to the system (DEBUG CODE)
	 */
	public void SystemWrite()
	{
		System.out.println(String.format("%d: %d: %d: %d: %d",productName,productID,productStock));
	}
	// #endregion
}
