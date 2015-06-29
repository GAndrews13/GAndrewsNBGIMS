import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Interface {

	private JFrame frmNbGardensInventory;
	private JTable table_1;
	JScrollPane scrollPane_1;
	static InventoryManagementSystem IMS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		{
			 IMS = new InventoryManagementSystem();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmNbGardensInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNbGardensInventory = new JFrame();
		frmNbGardensInventory.setTitle("NB Gardens Inventory Management System");
		frmNbGardensInventory.setBounds(100, 100, 651, 530);
		
		JMenuBar menuBar = new JMenuBar();
		frmNbGardensInventory.setJMenuBar(menuBar);
		//UI add functionality to menu's
		//#region Manually control stock 
		JMenu menu = new JMenu("Stock Control");
		 menuBar.add(menu);
		 JMenuItem menuItem = new JMenuItem("Add Item");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Change Stock");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Change Minimum Stock Required");
		 menu.add(menuItem);
		 //#endregion
		 //#region Admin
		 menu = new JMenu("Admin Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Create stock list TXT");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Create stock report");
		 menu.add(menuItem);
		 //#endregion
		//#region Database
		 menu = new JMenu("Database Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Refresh Database");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Load Database");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Force save");
		 menu.add(menuItem);
		 //#endregion
		 //#region Demo
		 menu = new JMenu("Demo Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Toggle Simulation");
		 menu.add(menuItem);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 //#region Data View Tab
		 String[] columnNames = {"ProductID","ProductName", "Stock", "Required Stock", "Critical Level", "Cost", "Since Last Review", "Current In Order", "Product Orders"};
		 String[][]blankTable = new String[0][9];
		 table_1 = new JTable(blankTable,columnNames);
		 scrollPane = new JScrollPane(table_1);
		 frmNbGardensInventory.getContentPane().add(scrollPane, BorderLayout.CENTER);
		 for(Product product : IMS.ProductLists())
		 {
			 
		 }
		 
		 
		 
		 // #endregion
		 //#endregion
		
	}
}
	