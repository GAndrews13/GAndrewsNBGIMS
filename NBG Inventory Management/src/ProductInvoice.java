
public class ProductInvoice extends ProductOrder
{
	/**
	 * The unique ID of the invoice
	 */
	private final int invoiceID;
	/**
	 * The Default constructor provides its unique ID
	 * @param inID
	 */
	public ProductInvoice(int inID)
	{
		invoiceID = inID;
	}
	/**
	 * Throws an alert when the invoice is finalised and the stock is added to the system
	 */
	private void Alert()
	{
		InventoryManagementSystem.StockIncreaseAlert("Stock Has Increased", "The following items have increased in stock: ", super.ProductsOrdered());
	}
	/**
	 * Increase stock levels based on the items in the invoice
	 */
	public void increaseStock()
	{
		for(int i = 0; i<super.ProductsOrdered().size();i++)
		{
			super.ProductsOrdered().get(i).Product().IncreaseStockLevel(super.ProductsOrdered().get(i).Quantity());
		}
		//TODO Product: Change Stock Levels	
		Alert();
	}
}
