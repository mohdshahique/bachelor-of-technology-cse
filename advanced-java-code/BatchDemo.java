import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchDemo {
	static ResultSet rs;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		try {
			//1. Register the Driver class
			
			Class.forName("com.mysql.jdbc.Driver");
			
			// Get the Connection Object
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestappdb", "shahimranalam",
					"shahimranalam@1980");
			
			// Create the Statement 
			 stmt = con.createStatement();
			
			
			String sql="insert into user_credentials values(17,'abcde1','abcdepasswd')";
			 stmt.addBatch(sql);
			 
			 stmt.addBatch("insert into user_credentials values(18,'abcde2','abcdepasswd')");
			 
			 stmt.executeBatch();
			 
stmt.addBatch("insert into user_credentials values(19,'abcde3','abcdepasswd')");
stmt.addBatch("insert into user_credentials values(20,'abcde4','abcdepasswd')");

			 stmt.executeBatch();
			 
			//Process the result
				con.close();
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
