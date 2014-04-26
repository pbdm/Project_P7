import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;

import javax.swing.JLabel;


public class analyse extends fenetre {
	private JPanel contentPane;
	protected Vector data;
	protected Vector dataRow;
	protected Vector col;
	protected ResultSet res;
	protected JTable table;
	protected JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
		public static void run() {
			try {
				analyse frame = new analyse();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	

	/**
	 * Create the frame.
	 */
	public analyse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JButton btnProduitPlusVendu = new JButton("produit plus vendu");
		btnProduitPlusVendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_vendu();");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_produit");
				col.add("nom_produit");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
				
			
			}
		});
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(btnProduitPlusVendu);
		
		JButton btnPouduitPlusVendu = new JButton("pouduit plus vendu hos promotion");
		btnPouduitPlusVendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_vendu_hp();");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_produit");
				col.add("nom_produit");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
				
			
			}
		});
		panel.add(btnPouduitPlusVendu);
		
		JButton btnProduitEstLe = new JButton("produit est le plus rentable en volume");
		btnProduitEstLe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_rentable_vol();");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_produit");
				col.add("nom_produit");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
			}
		});
		panel.add(btnProduitEstLe);
		
		JButton btnProduitEstLe_1 = new JButton("produit est le plus rentable en qualit\u00E9");
		btnProduitEstLe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_rentable_qual(0);");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add("anoyme");
						data.add(dataRow);
					
					} 
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_rentable_qual(1);");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add("cartepayement");
						data.add(dataRow);
					
					} 
					res = sta.executeQuery("select id_produit,nom_produit from produit where id_produit=produit_plus_rentable_qual(2);");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getString(2));
						dataRow.add("cartefielite");
						data.add(dataRow);
					
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_produit");
				col.add("nom_produit");
				col.add("type_client");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
			}
		});
		panel.add(btnProduitEstLe_1);
		
		JButton btnMargeBnCiaire = new JButton("marge b\u00E9n\u00E9\u001Cciaire par magasin");
		btnMargeBnCiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_magasin,marge_mag(id_magasin)from magasin;");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_magasin");
				col.add("marge");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
			}
		});
		panel.add(btnMargeBnCiaire);
		
		JButton btnPanierMoyen = new JButton("panier moyen");
		btnPanierMoyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = new Vector();
				dataRow = new Vector(); 
				col=new Vector();
				try {
					res = sta.executeQuery("select id_client,panier_client(id_client)from client;");
					while (res.next()) {
						dataRow=new Vector();
						dataRow.add(res.getInt(1));
						dataRow.add(res.getFloat(2));
						data.add(dataRow);
					} 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				col.add("id_client");
				col.add("panier moyen");
				
				table=new JTable(data,col);
				scrollPane.setViewportView(table);
			}
		});
		panel.add(btnPanierMoyen);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);	
	}

}
