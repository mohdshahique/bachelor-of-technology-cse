import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
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
			
			con.setAutoCommit(false);
			int count=stmt.executeUpdate("insert into user_credentials values(17,'ab1','abcdpasswd')");
			count+=stmt.executeUpdate("insert into user_credentials values(16,'ab2','uvwxpasswd')");
			
				System.out.println(count);
			
			
			con.commit();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			try {
				con.rollback();
				 rs=stmt.executeQuery("select * from user_credentials");
					while (rs.next())
						System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
