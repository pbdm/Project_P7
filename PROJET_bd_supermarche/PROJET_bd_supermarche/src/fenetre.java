import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


public class fenetre extends JFrame {

	private JPanel contentPane;
	Statement sta;
	Connection conn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		fenetre frame = new fenetre();
		frame.setVisible(true);
				
	
	}

	/**
	 * Create the frame.
	 */
	public fenetre() {
		try {
			Class.forName("org.postgresql.Driver");
			String url ="jdbc:postgresql://localhost/supermarche" ;
			String user="postgres";
			String password="860727";
			conn=DriverManager.getConnection(url,user,password);
			sta = conn.createStatement();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnVoirLaBd = new JButton("voir la BD");
		btnVoirLaBd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.run();
			}
		});
		panel.add(btnVoirLaBd);
		
		JButton btnCaisse = new JButton("CAISSE");
		btnCaisse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caisse.run();
			}
		});
		panel.add(btnCaisse);
		
		JButton btnAnalyse = new JButton("ANALYSE");
		btnAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				analyse.run();
			}
		});
		panel.add(btnAnalyse);
		
		JButton btnvnementsAssocisAutomatiquement = new JButton("\u00E9v\u00E8nements associ\u00E9s automatiquement");
		btnvnementsAssocisAutomatiquement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				evenement.run();
			}
		});
		panel.add(btnvnementsAssocisAutomatiquement);
		
		JLabel lblSupermarche = new JLabel("Supermarche");
		lblSupermarche.setFont(new Font("ËÎÌו", Font.PLAIN, 22));
		lblSupermarche.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSupermarche, BorderLayout.NORTH);
	}

}
