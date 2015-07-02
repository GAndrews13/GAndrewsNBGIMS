import java.util.List;
public abstract class ProductOrder {

	/**
	 * The list of product orders that are contained within the purchase
	 */
	private List<ProductOrderLine> productsOrdered;
	/**
	 * The total cost of the products ordered
	 */
	private int totalCost;
	/**
	 * Accessor for the total cost of all items in the product order
	 * @return
	 */
	public int TotalCost()
	{
		calculateTotalCost();
		return totalCost;
	}
	/**
	 * Calculates the total cost
	 */
	private void calculateTotalCost()
	{
		int value = 0;
		for(int i = 0; i<productsOrdered.size();i++)
		{
			value += productsOrdered.get(i).CalculateCost();
		}
		totalCost = value;
	}
	public List<ProductOrderLine> ProductsOrdered()
	{
		return productsOrdered;
	}
	
}
