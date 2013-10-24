import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class JTablePractice implements ActionListener {
	
    JCheckBox lastName = new JCheckBox("Last Name");
    JCheckBox firstName = new JCheckBox("First Name");
    JCheckBox department = new JCheckBox("Department");
    JCheckBox group = new JCheckBox("Group");
    JCheckBox office = new JCheckBox("Office");
    JCheckBox phone = new JCheckBox("Phone");
    JCheckBox currentProejct = new JCheckBox("Current Proejct");
    TableColumnModel tcm;
    Map hiddenColumns;
    JFrame frame;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    JButton close;
    
	public JTablePractice() {
	    try {
			String datafile = "data1.csv";
	        FileReader fin = new FileReader(datafile);
	        DefaultTableModel m = createTableModel(fin, null);
	        
	        frame = new JFrame(); 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        close = new JButton("Done");
	        close.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
	        	
	        });
	        
	        JTable table = new JTable(m);
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setViewportView(table);
	        scrollPane.setPreferredSize(new Dimension(700, 100));
	        
	        //for hide Column
	        tcm = table.getColumnModel();
	        hiddenColumns = new HashMap();
	        
	        //Table Styling
	        table.setIntercellSpacing(new Dimension(20, 0));
	        for(int i = 0; i<6; i++) {
	        	table.getColumnModel().getColumn(i).setPreferredWidth(100);
	        }
	        
	        //Layout for checkbox
	        JPanel checkboxPanel = new JPanel(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        if(shouldFill) {
	        	c.fill = GridBagConstraints.HORIZONTAL;
	        }
	        
	        if(shouldWeightX) {
	        	c.weightx = 1;
	        }
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridx = 0;
	        c.gridy = 0;
	        checkboxPanel.add(lastName, c);
	        
	        //c.fill = GridBagConstraints.HORIZONTAL;
	        c.weightx = 1;
	        c.gridx = 1;
	        c.gridy = 0;
	        checkboxPanel.add(firstName, c);
	        
	        c.weightx = 1;
	        c.gridx = 0;
	        c.gridy = 1;
	        checkboxPanel.add(department, c);
	        
	        c.weightx = 1;
	        c.gridx = 1;
	        c.gridy = 1;
	        checkboxPanel.add(group, c);
	        
	        c.weightx = 1;
	        c.gridx = 0;
	        c.gridy = 2;
	        checkboxPanel.add(office, c);
	        
	        c.weightx = 1;
	        c.gridx = 1;
	        c.gridy = 2;
	        checkboxPanel.add(phone, c);
	        
	        c.weightx = 1;
	        c.gridx = 0;
	        c.gridy = 3;
	        checkboxPanel.add(currentProejct, c);
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(close);
	        
	        frame.add(scrollPane, BorderLayout.NORTH);
	        frame.add(checkboxPanel, BorderLayout.CENTER);
	        frame.add(buttonPanel, BorderLayout.SOUTH);
	        frame.pack();
	        frame.setVisible(true);
	
        
	     } catch (Exception e) {
	            e.printStackTrace();
	     }
        
		lastName.setSelected(true);
	    firstName.setSelected(true);
	    department.setSelected(true);
	    group.setSelected(true);
	    office.setSelected(true);
	    phone.setSelected(true);
	    currentProejct.setSelected(true);
	    
	    lastName.addActionListener(this);
	    firstName.addActionListener(this);
	    department.addActionListener(this);
	    group.addActionListener(this);
	    office.addActionListener(this);
	    phone.addActionListener(this);
	    currentProejct.addActionListener(this);
	}


    public static DefaultTableModel createTableModel(Reader in,
            Vector<Object> headers) {
            DefaultTableModel model = null;
            Scanner s = null;

            try {
                Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
                s = new Scanner(in);

                while (s.hasNextLine()) {
                    rows.add(new Vector<Object>(Arrays.asList(s.nextLine()
                                                               .split("\\s*,\\s*",
                                    -1))));
                }

                if (headers == null) {
                    headers = rows.remove(0);
                    model = new DefaultTableModel(rows, headers);
                } else {
                    model = new DefaultTableModel(rows, headers);
                }

                return model;
            } finally {
                s.close();
            }
        }

    public void hide(String columnName) {
    	int index = tcm.getColumnIndex(columnName);
    	TableColumn column = tcm.getColumn(index);
    	hiddenColumns.put(columnName, column);
    	hiddenColumns.put(":" + columnName, new Integer(index));
    	tcm.removeColumn(column);
    }
    
    public void show(String columnName) {
    	Object o = hiddenColumns.remove(columnName);
    	if(o==null){ 
    		return;
    	}
    	tcm.addColumn((TableColumn) o);
    	o = hiddenColumns.remove(":"+columnName);
    	if(o==null) {
    		return;
    	}
    	int column = ((Integer)o).intValue();
    	int lastColumn = tcm.getColumnCount() - 1;
    	if (column < lastColumn) {
    		tcm.moveColumn(lastColumn, column);
    	}
    }
       
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JCheckBox cb = (JCheckBox)e.getSource();
		String columnName = cb.getText();
		
		if(cb.isSelected()) {
			show(columnName);
		}else {
			hide(columnName);
		}
	}
	
    public static void main(String[] args) {
        // Read a csv file called 'data.txt' and save it to a more
        // correctly named 'data.csv'
    	JTablePractice practice = new JTablePractice();    
}

    }