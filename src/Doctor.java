import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
	public static void PrintDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Doctor");
		System.out.println("Dr_ID       Dr_Name        Specialisation   Timing    Contact_No");
		System.out.println("-------------------------------------------------------------------------------");


		while (rs.next()) {
			System.out.println(rs.getString("Dr_ID") + "      " + rs.getString("Dr_Name")+"        "+ rs.getString("Specialisation") + "     " + rs.getString("Timing")+"        "+ rs.getString("Contact_No"));
			System.out.println();

		}
		System.out.println("-------------------------------------------------------------------------------\n\n");

		st.close();
		con.close();

	}

}