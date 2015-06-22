import java.util.Date;

public class SalesTrackRecord {

	public SalesTrackRecord(Date inDate)
	{
		sales = 0;
		saleDate = inDate;
	}
	
	private int sales;
	private Date saleDate;
	
	public int sales()
	{
		return sales;
	}
	public void sales(int inSales)
	{
		sales += inSales;
	}
	public Date saleDate()
	{
		return saleDate;
	}
	public void setDate(Date inDate)
	{
		saleDate = inDate;
	}
	public void incrementDate(int inDaysForward)
	{
		//TODO add a way to change date

		
	}
}
