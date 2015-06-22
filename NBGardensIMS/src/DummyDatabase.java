import java.util.ArrayList;
import java.util.List;

public class DummyDatabase
{
		 	// #region Constructors
			public DummyDatabase()
			{
				AddProduct(1000001,"Machine Gun Gnome",156);
				AddProduct(1000002,"Hot Tub 5000", 16);
				AddProduct(1000003,"Bieber Gnome",23);
				AddProduct(1000004,"Cheese Deluxe Gnome",255);
				AddProduct(1000005,"Ex-minion Cake Gnome", 98);
			}
			// #endregion
			// #region Variables
			List<Product> database = new ArrayList<Product>();		
			// #endregion
			// #region Methods
			/**
			 * Returns a unique product ID
			 * @return
			 */
			public int generateProductID()
			{
				return database.get(database.size()-1).ProductID()+1;
			}
			public void AddProduct(int inProductID, String inProductName, int inStockLevel)
			{
				database.add(new Product(inProductID,inProductName,inStockLevel));
			}
			public void WriteAll()
			{
				for(Product p : database)
				{
					System.out.println(p.getProductInfo());
					
				}
			}
			public void Write(int inProductNumber)
			{
				System.out.println(database.get(inProductNumber).getProductInfo());
			}
			public void ListAll()
			{
				for(int i = 0; i< database.size();i++)
				{
					System.out.println(i+1 +":" + database.get(i).ProductName());
				}
			}
			public void ChangeStock(int inProductPlacement, int inStockChange)
			{
				database.get(inProductPlacement).IncreaseStockLevel(inStockChange);
			}
			// #endregion
			public Product Find(int inProductID)
			{
				for(int i = 0; i<database.size();i++)
				{
					if(database.get(i).ProductID() == inProductID)
					{
						return database.get(i);
					}
				}
				return null;
			}

}
