import java.sql.*;

public class TestCoonection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Connection con=null;
Statement stmt=null;
ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "shahimranalam",
					"shahimranalam@1980");

			stmt = con.createStatement();

			rs = stmt.executeQuery("select * from city");

			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  "
						+ rs.getString(4) + "  " + rs.getInt(5));

		} catch (SQLException e) {
			System.out.println(" Inside catch SQLException block");
			System.out.println(e.getCause());

			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(" Inside catch Exception block");
			System.out.println(e.getCause());

			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
