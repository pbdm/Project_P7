import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class achat_produit extends fenetre {
	static achat_produit frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	
	public static void run() {
		frame = new achat_produit();
		frame.setVisible(true);
				
	}

	/**
	 * Create the frame.
	 */
	public achat_produit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblVeuillezDonneLes = new JLabel("veuillez donne les information suivant");
		panel.add(lblVeuillezDonneLes);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JLabel lblId = new JLabel("id_magasin");
		panel_1.add(lblId);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblIdproduit = new JLabel("id_produit");
		panel_2.add(lblIdproduit);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JLabel lblQuantiteachat = new JLabel("quantite_achat");
		panel_3.add(lblQuantiteachat);
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String requete = "INSERT INTO achat_produit VALUES ("+caisse.a+","+textField.getText()+","+textField_1.getText()+","+textField_2.getText()+");";
				try {
					sta.execute(requete);
					frame.setVisible(false);
					int j=JOptionPane.showConfirmDialog(null,"une autre produit?",null,0);
					if(j==JOptionPane.YES_OPTION )
						achat_produit.run();
					else{
						
						payer.run1();
					}	
						
						
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_3.add(btnSubmit);
	}

}
