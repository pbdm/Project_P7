import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;


public class menu extends fenetre {

	private JPanel contentPane;
	protected JPanel panel_2;
	protected JTable table;
	protected JScrollPane scrollPane;
	protected ResultSet res;
	protected Vector data;
	protected Vector dataRow;
	protected Vector col;
	protected CardLayout c;
	
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void run() {
		menu frame = new menu();
		frame.setVisible(true);
			
	}
	public void est(final String a){
	
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10,a);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		JButton btnAjoute = new JButton("Ajoute "+a);
		panel_10.add(btnAjoute);
		
		
		JButton btnModifier = new JButton("modifier "+a);
		panel_10.add(btnModifier);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*if(a=="client") 
					clientmod.run();
				else if(a=="produit") 
					produitmod.run();
				else if(a=="magasin") 
					magasinmod.run();
				else if(a=="cartefiedlite") 
					cartefiedlitemod.run();
				else if(a=="cartepayement") 
					cartepayementmod.run();
				else if(a=="achat") 
					achatmod.run();
				else if(a=="facture") 
					facturemod.run();
				else if(a=="produit_mag") 
					produit_magmod.run();
				else if(a=="categorie") 
					categoriemod.run();
				else if(a=="cataloge") 
					catalogemod.run();
				else if(a=="reduit") 
					reduitmod.run();
				else if(a=="bon_achat") 
					bon_achatmod.run();
				else if(a=="cataloge_produit") 
					cataloge_produitmod.run();*/
			}
		});
		
		JButton btnSuprime = new JButton("suprime "+a);
		panel_10.add(btnSuprime);
		
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public menu()  {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSupermarche = new JLabel("Supermarche");
		lblSupermarche.setFont(new Font("ËÎÌו", Font.PLAIN, 67));
		lblSupermarche.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSupermarche, BorderLayout.NORTH);
		
		
//centre------------------------------------------
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);	
		
//est---------------------------------------------------------------	
		c=new CardLayout(0,0);
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(c);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11,"");
		
//west---------------------	
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		//client--------------------------------------------------------------
		JButton btnListClient = new JButton("Table CLIENT");
		btnListClient.setForeground(Color.BLACK);
		btnListClient.setBackground(Color.GREEN);
		btnListClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				est("client");
				c.show(panel_2,"client");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from client");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add(res.getString(3));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_client");
			    col.add("nom");
			    col.add("prenom");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			}
		});
		
		panel.add(btnListClient);
		//cp-----------------------------------------------
		JButton btnTableCart = new JButton("Table CARTEPAYEMENT");
		btnTableCart.setForeground(Color.BLACK);
		btnTableCart.setBackground(Color.GREEN);
		btnTableCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				est("cartepayement");
				c.show(panel_2,"cartepayement");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from cartepayement");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						dataRow.add(res.getFloat(3));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_client");
			    col.add("credit_calcule");
			    col.add("taux_credit");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			}
		});
		panel.add(btnTableCart);
		//cf-----------------------------------------------------------------
		JButton btnTableCartefi = new JButton("Table CARTEFIEDLITE");
		btnTableCartefi.setForeground(Color.BLACK);
		btnTableCartefi.setBackground(Color.GREEN);
		btnTableCartefi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				est("cartefiedlite");
				c.show(panel_2,"cartefiedlite");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from cartefiedlite");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_client");
			    col.add("point");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			}
		});
		panel.add(btnTableCartefi);
		//facture--------------------------------------
		JButton btnTableFacture = new JButton("Table FACTURE");
		btnTableFacture.setBackground(Color.RED);
		btnTableFacture.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("facture");
				c.show(panel_2,"facture");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from achat_facture");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getInt(2));
						dataRow.add(res.getDate(3));
						dataRow.add(res.getFloat(4));
						dataRow.add(res.getFloat(5));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_achat");
			    col.add("id_client");
			    col.add("date_achat");
			    col.add("prix_total");
			    col.add("montant_payer");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTableFacture);
		
		//magasin---------------------------------------------
		JButton btnListMagasin = new JButton("Table MAGASIN");
		btnListMagasin.setBackground(Color.BLUE);
		btnListMagasin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				est("magasin");
				c.show(panel_2,"magasin");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from magasin");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_magasin");
			    col.add("nom");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			}
		});
		
		//achat-------------------------------------------------
		JButton btnTableAchat = new JButton("Table ACHAT");
		btnTableAchat.setBackground(Color.RED);
		btnTableAchat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("achat");
				c.show(panel_2,"achat");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from achat_produit");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
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
			    col.add("id_achat");
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
		});
		panel.add(btnTableAchat);
		panel.add(btnListMagasin);
		
		//produit_mag-----------------------------------------------------------
		JButton btnTableProduitmag = new JButton("Table PRODUIT_MAG");
		btnTableProduitmag.setBackground(Color.BLUE);
		btnTableProduitmag.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("produit_mag");
				c.show(panel_2,"produit_mag");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from produit_magasin");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getInt(2));
						dataRow.add(res.getFloat(3));
						dataRow.add(res.getInt(4));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_produit");
			    col.add("id_magasin");
			    col.add("prix_vente");
			    col.add("quantite_stock");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTableProduitmag);
		
		JButton btnListProduit = new JButton("Table PRODUIT");
		btnListProduit.setBackground(Color.BLUE);
		btnListProduit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("produit");
				c.show(panel_2,"produit");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from produit");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add(res.getFloat(3));
						dataRow.add(res.getInt(4));
						dataRow.add(res.getDate(5));
						dataRow.add(res.getInt(6));
						dataRow.add(res.getInt(7));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_produit");
			    col.add("nom");
			    col.add("prix");
			    col.add("reference_loc");
			    col.add("date_peremption");
			    col.add("id_categorie");
			    col.add("quantite_lim");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnListProduit);
		//categorie-----------------------------
		JButton btnList = new JButton("Table CATEGORIE");
		btnList.setBackground(Color.BLUE);
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				est("categorie");
				c.show(panel_2,"categorie");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from categorie");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add(res.getInt(3));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				col.add("id_categorie");
				col.add("nom");
				col.add("id_souscategorie");
				TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			}
		});
		panel.add(btnList);
		
		//cataloge-----------------------------------------------------
		JButton btnTableCataloge = new JButton("Table CATALOGE");
		btnTableCataloge.setBackground(Color.YELLOW);
		btnTableCataloge.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("cataloge");
				c.show(panel_2,"cataloge");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from cataloge");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getDate(2));
						dataRow.add(res.getDate(3));
						dataRow.add(res.getInt(4));
						dataRow.add(res.getInt(5));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_cataloge");
			    col.add("date_debut");
			    col.add("date_fin");
			    col.add("id_reduit_imme");
			    col.add("id_bon_ahcat");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTableCataloge);
		
		//reduit imme----------------------
		JButton btnTable = new JButton(" Table REDUIT_IMME");
		btnTable.setBackground(Color.YELLOW);
		btnTable.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("reduit_imme");
				c.show(panel_2,"reduit_imme");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from reduit_imme");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						dataRow.add(res.getFloat(3));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_reduit_imme");
			    col.add("montant_reduit");
			    col.add("pourcentage_reduit");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTable);
		
		//-----------bonachat
		JButton btnTableBonachat = new JButton("Table BON_ACHAT");
		btnTableBonachat.setBackground(Color.YELLOW);
		btnTableBonachat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("bon_achat");
				c.show(panel_2,"bon_achat");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from bon_achat");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_bon_achat");
			    col.add("montant calcule");
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTableBonachat);
		
		//-------cataloge_produit
		JButton btnTableCatalogeproduit = new JButton("Table CATALOGE_PRODUIT");
		btnTableCatalogeproduit.setBackground(Color.YELLOW);
		btnTableCatalogeproduit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				est("cataloge_produit");
				c.show(panel_2,"cataloge_produit");
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select * from cataloge_produit");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getInt(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    col.add("id_cataloge");
			    col.add("id_produit");
			   
			    TableModel model = new DefaultTableModel(data, col);
			    table=new JTable(model);
			    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			    scrollPane.setViewportView(table);
			   
			}
		});
		panel.add(btnTableCatalogeproduit);
	
	}

}
