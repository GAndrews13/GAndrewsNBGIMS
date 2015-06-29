import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.logging.Level;

public class Interface {

	private JFrame frmNbGardensInventory;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	static InventoryManagementSystem IMS;
	private DefaultTableModel tModel;
	private TableModelListener tMListern;
	private ActionListener actionListener = new ActionListener(){

		@Override
		//#region Listener
		/**
		 * When a button is pressed on the UI calls the appropiate method
		 * @param arg0
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			switch(arg0.getActionCommand())
			{
				case "AddItem":
					
					break;
				case "ChangeStock":
					
					break;
				case "StockList":
					 IMS.writeToTxt();
					break;
				case "StockReport":
					IMS.writeProductOrderDocument();
					break;
				case "RefreshDatabase":
					refreshTable();
					break;
				case "LoadDatabase":
					IMS.DatabaseLoad();
					break;
				case "ForceSave":
					IMS.DatabaseSave();
					break;
				case "ToggleSim":
					
					break;
			}
		}
		//#endregion
	
	};

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
		 menuItem.setActionCommand("AddItem");
		 menuItem.addActionListener(actionListener);
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Change Stock");
		 menuItem.addActionListener(actionListener);
		 menuItem.setActionCommand("ChangeStock");
		 menu.add(menuItem);
		 //#endregion
		 //#region Admin
		 menu = new JMenu("Admin Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Create stock list TXT");
		 menuItem.setActionCommand("StockList");
		 menuItem.addActionListener(actionListener);
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Create Stock Order Form");
		 menuItem.setActionCommand("StockReport");
		 menuItem.addActionListener(actionListener);
		 menu.add(menuItem);
		 //#endregion
		//#region Database
		 menu = new JMenu("Database Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Refresh Database");
		 menuItem.addActionListener(actionListener);
		 menuItem.setActionCommand("RefreshDatabase");
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Load Database");
		 menuItem.setActionCommand("LoadDatabase");
		 menuItem.addActionListener(actionListener);
		 menu.add(menuItem);
		 menuItem = new JMenuItem("Force save");
		 menuItem.setActionCommand("ForceSave");
		 menuItem.addActionListener(actionListener);
		 menu.add(menuItem);
		 //#endregion
		 //#region Demo
		 menu = new JMenu("Demo Controls");
		 menuBar.add(menu);
		 menuItem = new JMenuItem("Toggle Simulation");
		 menuItem.setActionCommand("ToggleSim");
		 menu.add(menuItem);
		 
		 JScrollPane scrollPane = new JScrollPane();
		 //#region Data View Tab
		 String[] columnNames = {"ProductID","ProductName", "Stock", "Required Stock", "Critical Level", "Cost", "Since Last Review", "Current In Order"};
		 String[][]blankTable = new String[0][8];
		 tModel = new DefaultTableModel(blankTable,columnNames);
		 //Add a listener for changes in the cells
		 tModel.addTableModelListener(tMListern);
		 table_1 = new JTable(tModel);
		 scrollPane = new JScrollPane(table_1);
		 frmNbGardensInventory.getContentPane().add(scrollPane, BorderLayout.CENTER);
		 for(Product product : IMS.ProductLists())
		 {
			 
			tModel.addRow(product.ObjectArray()); 
		 }
		 // #endregion
		 //#endregion
		
	}
	//#region Methods
	public void tableChanged(TableModelEvent inEvent)
	{
		//TODO Detect table changes work
		try
		{
			tModel = (DefaultTableModel)inEvent.getSource();
			int row = inEvent.getFirstRow();
			int column = inEvent.getColumn();
			System.out.println("TESTING TABLE CHANGE");
			IMS.updateProduct(returnRow(row));
		}
		catch (Exception e)
		{
			IMS.ErrorAlert("Unable to Update Entry", "INF01", e, Level.SEVERE);
		}
		
	}
	private Object[] returnRow(int inRow)
	{
		Object[] result = new Object[tModel.getColumnCount()];
		for(int i = 0; i<tModel.getColumnCount();i++)
		{
			result[i] = table_1.getModel().getValueAt(inRow, i);
		}
		return result;
	}
	/**
	 * Refreshes the data in the table from the IMS database 
	 */
	public void refreshTable()
	{
		//TODO Currently removes only odd entries
		//Remove all current entries in the program
		for(int i = 0; i <tModel.getRowCount();i++)
		{
			tModel.removeRow(i);
		}
		//Load all products from the database
		IMS.DatabaseLoad();
		//Refresh the table with the new items
		for(Product product : IMS.ProductLists())
		 {
			tModel.addRow(product.ObjectArray()); 
		 }
	}
	//#endregion
}
	