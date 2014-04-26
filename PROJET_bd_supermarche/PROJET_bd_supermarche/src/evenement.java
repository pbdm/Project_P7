import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;


public class evenement extends menu {

	private JPanel contentPane;
	String j;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
			public static void run() {
				
					evenement frame = new evenement();
					frame.setVisible(true);
				
		
	}

	/**
	 * Create the frame.
	 */
	public evenement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		final JTextArea textArea = new JTextArea();
		panel_1.add(textArea, BorderLayout.NORTH);
		
		
		
		final JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnSubmit = new JButton("archive la date");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				JOptionPane.showMessageDialog(null,"merci:)");
				Date d=new Date();
	            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	            String str=sdf.format(d);
	            textArea.setText("Aujourd'hui:"+str+" \n les id de produit peremption sont:");
	

				String requete = "UPDATE dates SET dates='"+str+"';";
				try {
					sta.execute(requete);
					data = new Vector();
					dataRow = new Vector(); 
					col=new Vector();
					try {
						res = sta.executeQuery("select id_produit from produit_magasin WHERE quantite_stock=0");
						while (res.next()) {
							dataRow=new Vector();
							dataRow.add(res.getInt(1));
							data.add(dataRow);
						} 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    col.add("id_produit");
				    TableModel model = new DefaultTableModel(data, col);
				    table=new JTable(model);
				    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
				    table.setRowSorter(sorter);
				    scrollPane.setViewportView(table);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
			}
		});
		panel.add(btnSubmit);
	}

}
