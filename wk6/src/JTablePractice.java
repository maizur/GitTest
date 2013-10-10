import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class JTablePractice {

	public static void main(String[] args) {
		
		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() {
				return 7;
			}
			
			public int getRowCount() {
				return 10;
			}
			
			public Object getValueAt(int row, int col) {
				return new Integer(row*col);
			}
		};
		
		JTable table = new JTable(dataModel);
		JScrollPane content = new JScrollPane(table);
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		
		JCheckBox checkbox = new JCheckBox();
		JLabel lastname = new JLabel("Last Name");
		JLabel firstname = new JLabel("First Name");
		JLabel department = new JLabel("Departement");
		JLabel group = new JLabel("Group");
		JLabel office = new JLabel("Office");
		JLabel phone = new JLabel("Phone");
		JLabel project = new JLabel("Current Project");
		
		//setLayout(new BorderLayout());
		
	}

}
