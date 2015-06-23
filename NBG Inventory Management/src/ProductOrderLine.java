
public class ProductOrderLine
{
	/**
	 * The unique ID of the product order line
	 */
	private final int orderLineID;
	/**
	 * The product that the order line refers to
	 */
	private Product productID;
	/**
	 * the quantity of the product which are being ordered
	 */
	private int quantity;
	/**
	 * The total cost of the products being ordered
	 */
	private double totalCost;
	
	/**
	 * the The default constructor that assigns the unique ID
	 * @param inOrderID
	 */
	public ProductOrderLine(int inOrderID)
	{
		orderLineID = inOrderID;
	}
	/**
	 * Allows you to set the product that this particular product order refers to 
	 * @param inProduct
	 */
	public void SetProduct(Product inProduct)
	{
		productID = inProduct;
	}
	/**
	 * Returns the product that the product order line referes to 
	 * @return
	 */
	public Product Product()
	{
		return productID;
	}
	/**
	 * Returns the number of items that are in this particular product order
	 * @return
	 */
	public int Quantity()
	{
		return quantity;
	}
	/**
	 * Allows you to change the quantity of the product in this order. Should it be set to 0 (or lower) the product is then removed and the product order cancelled
	 * @param inChange
	 */
	public void ChangeQuantity(int inChange)
	{
		int newValue = quantity += inChange;
		if(newValue <= 0)
		{
			RemoveProduct();
		}
		else
		{
			quantity = newValue;
		}
	}
	/**
	 * Removes the product order from the system
	 */
	private void RemoveProduct()
	{
		//FIXME BadCode: CHANGE
		productID = null;
	}
	/**
	 * Calculates the total cost of the products involved in this part of the order and returns it
	 */
	public int CalculateCost()
	{
		return productID.ProductCost()*productID.ProductCost();
	}
}
