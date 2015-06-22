import java.util.Date;

public class Product
{
		// #region Constructors
		/**
		 * Sets up a basic product with the provided criteria
		 * @param inProdID
		 * Unique Product ID
		 * @param inProdName
		 * Products Name
		 * @param inStockLevel
		 * Products Current Stock level and required stock level
		 */
		public Product(int inProdID, String inProdName, int inStockLevel)
		{
			prodID = inProdID;
			stockLevel = inStockLevel;
			estimatedStockLevel = inStockLevel;
			productName = inProdName;
			for(int i = 0;i<14;i++)
			{
				Date d = new Date();
			
				sales[i] = new SalesTrackRecord(d);
			}
		}
		// #endregion
		// #region Variables
		private int prodID;
		private int stockLevel;
		private String productName;
		private SalesTrackRecord[] sales = new SalesTrackRecord[14];
		//The level at which stock should be kept
		private int estimatedStockLevel;
		// #endregion
		// #region Methods
		// #region Variable Modifiers
		/**
		 * Returns the products unique ID
		 */
		public int ProductID()
		{
			return prodID;
		}
		/**
		 * Allows the increase of the stock level
		 * @param inStockAdj
		 * The value by which the stock will be increased
		 */
		public void IncreaseStockLevel(int inStockAdj)
		{
			stockLevel += inStockAdj;
			//If stock levels are being reduced, a sale has been put through and it should be recorded
		}
		@Deprecated
		/**
		 * Allows the decrease of stock level
		 * @param inStockAdj
		 * The value by which stock is decreased
		 */
		public void DecreaseStockLevel(int inStockAdj)
		{
			stockLevel -= inStockAdj;
		}
		/**
		 * Displays the current stock of the item
		 */
		public int StockLevel()
		{
			return stockLevel;
		}
		/**
		 * Returns the products dedicated name
		 */
		public String ProductName()
		{
			return productName;
		}
		/**
		 * Allow the products name to be changed 
		 * @param inName
		 * The new name that is being assigned to the product
		 */
		public void ChangeProductName(String inName)
		{
			productName = inName;
		}
		/**
		 * Returns the stock level required for the product
		 * @return
		 */
		public int StockRequired()
		{
			return estimatedStockLevel;
		}
		/**
		 * Allows the adjustment of the stock level
		 * @param inStockReq
		 * The new level at which stock is to be kept at
		 */
		public void ChangeStockLevelRequired(int inStockReq)
		{
			estimatedStockLevel = inStockReq;
		}
		// #endregion
		public String getProductInfo()
		{
			String[] productInfo = {Integer.toString(prodID),productName,Integer.toString(stockLevel)};
			return String.format("Product ID: %s %nProduct Name: %s %nStock Level: %s %n",productInfo);	
		}
		// #endregion
}
