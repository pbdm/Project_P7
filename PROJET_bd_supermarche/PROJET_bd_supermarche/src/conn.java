import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conn {
	Connection conn;
	public conn(){
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String url ="jdbc:postgresql://localhost/supermarche" ;
	String user="postgres";
	String password="860727";
	try {
		conn=DriverManager.getConnection(url,user,password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}