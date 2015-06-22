import java.util.Scanner;


public class InventoryManagementSystem
{
		// #region Constructors
		public InventoryManagementSystem(String[] args)
		{
			
		}
		public InventoryManagementSystem()
		{
			memory = new DummyDatabase();
		}
		// #endregion
		// #region Variables
		DummyDatabase memory;
		boolean running = true;
		Scanner inputReader = new Scanner(System.in);
		// #endregion
		// #region Methods
		public void WriteIntro()
		{
			while(running)
			{
				System.out.println("");
				System.out.println("Welcome to NB Gardens Inventory Management System");
				System.out.println("");
				System.out.println("1. Print All Stock Information");
				System.out.println("2. Add an Item");
				System.out.println("3. Change the quantity of a stock item");
				System.out.println("");
				System.out.println("Please select an option from the choices above: ");
				switch(inputReader.nextInt())
				{
				case 1:
					memory.WriteAll();
					System.out.println("Press the enter key to continue");
					inputReader.next();
					break;
				case 2:
					System.out.println("What is the name of the item you wish to add?");
					String prodName = inputReader.next();
					System.out.println("What is its current stock?");
					int prodStock = inputReader.nextInt();
					int prodID = memory.generateProductID();
					memory.AddProduct(prodID, prodName, prodStock);
					System.out.println(memory.Find(prodID).getProductInfo());
					break;
				case 3:
					System.out.println("Change Stock Level");
					memory.ListAll();
					System.out.println("Please select the item you wish to edit, select 0 to exit");
					int productSelectedID = inputReader.nextInt();
					if(productSelectedID == 0)
					{
						break;	
					}
					boolean errorInput = true;
					while(errorInput)
					{
						memory.Write(productSelectedID-1);
						errorInput = false;
						System.out.println("Do you wish to increase or decrease the stock?");
						System.out.println("1. Increase");
						System.out.println("2. Decrease");
						switch(inputReader.nextInt())
						{
							case 1:
								System.out.println("How much do you want to increase the stock by?");
								memory.ChangeStock(productSelectedID-1, inputReader.nextInt());
								break;
							case 2:
								System.out.println("How much do you want to decrease the stock by?");
								memory.ChangeStock(productSelectedID-1, -inputReader.nextInt());
								break;
							default:
								System.out.println("That is not a valid Input.");
								errorInput = true;
								break;
						}
						memory.Write(productSelectedID-1);
					}
					break;
				default:
					System.out.println("Sorry, that is not a registered response. Please try again");
					break;
				}
			}
		}
		public void writeToTextFile()
		{
			//TODO write text file exporting method
		}
		// #endregion
}
