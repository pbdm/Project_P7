import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.RowSorter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class payer extends menu {
	static payer frame;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	float prix;

	/**
	 * Launch the application.
	 */
	
	public static void run1() {
				
			frame = new payer();
			frame.setVisible(true);
				
	}

	/**
	 * Create the frame.
	 */
	public payer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblListDeAchat = new JLabel("LIST DE ACHAT");
		panel.add(lblListDeAchat, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblMontant = null;
		try {
			res = sta.executeQuery("select prix_total from achat_facture where id_achat="+caisse.a+";");
			res.next();
			prix = res.getFloat(1);
			lblMontant = new JLabel("MONTANT Prix:"+prix);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_1.add(lblMontant);
		
		
		
		JLabel lblPayer = new JLabel("payer");
		panel_1.add(lblPayer);
		
		textField = new JTextField();
		JLabel lblPayeregale = new JLabel("non credit");
		try {
			res = sta.executeQuery("select id_client from achat_facture where id_achat="+caisse.a+";");
			res.next();
			if(res.getInt(1)%2!=1)
				panel_1.add(lblPayeregale);
			
			else{
				panel_1.add(textField);
				textField.setColumns(10);
			}	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnPayer = new JButton("payer");
		btnPayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String requete = "UPDATE achat_facture SET montant_payer="+textField.getText()+"WHERE id_achat="+caisse.a+";";
				try {
					sta.execute(requete);
					JOptionPane.showMessageDialog(null,"merci:)");
					frame.setVisible(false);
					caisse.frame.setVisible(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
			}
		});
		panel_1.add(btnPayer);
		
		
		
		data = new Vector();
		dataRow = new Vector(); 
		col=new Vector();
		try {
			res = sta.executeQuery("select date_achat,id_magasin,id_produit,quantite_achat,prix_achat_client from achat_facture,achat_produit where achat_facture.id_achat="+caisse.a+" and achat_produit.id_achat="+caisse.a+";");
			while (res.next()) {
				dataRow=new Vector();
				dataRow.add(res.getDate(1));
				dataRow.add(res.getInt(2));
				dataRow.add(res.getInt(3));
				dataRow.add(res.getInt(4));
				dataRow.add(res.getFloat(5));
				data.add(dataRow);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    col.add("date_achat");
	    col.add("id_magasin");
	    col.add("id_produit");
	    col.add("quantite_achat");
	    col.add("prix_achat_client");
	    TableModel model = new DefaultTableModel(data, col);
	    table=new JTable(model);
	    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    table.setRowSorter(sorter);
	    scrollPane.setViewportView(table);
	}

}
