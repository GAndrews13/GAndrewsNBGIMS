import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.table.DefaultTableModel;


public class Interface {

	private JFrame frmNbGardensInventory;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		//TODO add functionality to menu's
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
		 //#endregion
		 //#region Table
		 table_1 = new JTable();
		 table_1.setModel(new DefaultTableModel(
		 	new Object[][] {
		 	},
		 	new String[] {
		 	}
		 ));
		 frmNbGardensInventory.getContentPane().add(table_1, BorderLayout.EAST);
		 //#endregion
		 
		
		/*
		MenuItem menuOption = new JMenuItem("Export To Text File");
		menuOption.getAccessibleContext().setAccessibleDescription("Exports current stock levels to a TXT file");
		menuBar.add(menuOption);
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.NORTH);
		*/
	}

}
