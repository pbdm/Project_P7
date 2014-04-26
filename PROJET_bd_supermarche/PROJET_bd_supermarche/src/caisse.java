import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class caisse extends fenetre {
	static caisse frame;
	static int a;//nouveau id_achat
	private JPanel contentPane;
	protected ResultSet res;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	
	public static void run() {
		frame = new caisse();
		frame.setVisible(true);
				
		
	}

	/**
	 * Create the frame.
	 */
	public caisse() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVeuillezDonneLes = new JLabel("veuillez donne les information suivant");
		lblVeuillezDonneLes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblVeuillezDonneLes, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblIdclient = new JLabel("id_client");
		panel_1.add(lblIdclient);
		
		textField = new JTextField();
		textField.setText("1");
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDate = new JLabel("date_achat");
		panel_2.add(lblDate);
		
		textField_1 = new JTextField();
		textField_1.setText("2010-10-10");
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					res=sta.executeQuery("select max(id_achat) from achat_facture");
					res.next();	
					a=res.getInt(1)+1;	//nouveau id_achat
					String requete = "INSERT INTO achat_facture VALUES ("+a+","+textField.getText()+",'"+textField_1.getText()+"',0,0);";
					sta.execute(requete);
					frame.setEnabled(false);
					achat_produit.run();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
		});
		panel_2.add(btnSubmit);
	}

}
