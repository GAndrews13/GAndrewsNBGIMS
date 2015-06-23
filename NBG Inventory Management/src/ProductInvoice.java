
public class ProductInvoice extends ProductOrder {

	//TODO fill
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
		//TODO throw alert (From inventoryManagementSystem class) when finalised
		//Change Stock Levels
	}
}
