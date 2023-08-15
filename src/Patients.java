import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Patients {

	public static void Patient_Menu() {
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			displayMenu();
			choice = sc.nextInt();
			sc.nextLine(); // Consume the newline character5

			switch (choice) {
			case 1:
				try {
					Insert_Patient();
				} catch (Exception e1) {
					System.out.println(e1);

				}

				break;
			case 2:
				try {
					Modify_Patient();
				} catch (Exception e1) {
					System.out.println(e1);

				}
				break;
			case 3:
				try {
					Delete_Patient();
				} catch (Exception e1) {
					System.out.println(e1);
				}

				break;
			case 4:
				try {
					PrintDetails();

				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 5:
				try {
					PrintAllDetails();
				} catch (Exception e) {
					System.out.println(e);
				}

				break;
			case 0:
				System.out.println("Exiting the patient menu .");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				break;
			}
		} while (choice != 0);
	}
	
	private static void displayMenu() {
		System.out.println("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

		System.out.println("-----------------You are in the Patients Menu-------------------- \nPlease select a option:  \n");
		System.out.println("\nPress 1 for Register Patient\nPress 2 for Modify Patient Details\nPress 3 for Delete Patient Record\nPress 4 for View Single Record\n"+ "Press 5 for View All Records\nPress 0 for Exit");

	}
	
	
	public static void Insert_Patient() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

		System.out.println("Below is Registration form, Please fill all the details.......\n");
		System.out.println("Enter Your Patient ID");
		int p_id = sc.nextInt();
		System.out.println("Enter Your Name");
		sc.nextLine();
		String p_name = sc.nextLine();
		System.out.println("Enter Your Age");
		int p_age = sc.nextInt();
		System.out.println("Enter Your Gender");
		String p_gen = sc.next();
		System.out.println("Enter Your Contact Number");
		String p_num = sc.next();
		System.out.println("Enter Your Address");
		String p_add = sc.next();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		int res=0;
		try {
		String insertQuery = "insert into Patients " + "values(" + p_id + ",'" + p_name + "'," + p_age + ",'" + p_gen+ "','" + p_num +"','"+ p_add + "')";
		 res = st.executeUpdate(insertQuery);}
		catch(Exception e) {
			System.out.println("\nThis Patient_ID is already exists ...Please enter a new Patient_ID\n");
		}

		if (res == 0) {
			System.out.println("Record not inserted\n");
		} else {
			
			System.out.println("\n*******Record inserted succesfully*******");
		}
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");

		st.close();
		con.close();

	}
	

	private static void Modify_Patient() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease Enter Your Patient ID for update your data");
		int p_id = sc.nextInt();
		System.out.println(".*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.");

		System.out.print("\nPlease Update your Details below: \n");
		
		System.out.println("Enter Your Name : ");
		sc.nextLine();
		String p_name = sc.nextLine();
		
		System.out.println("Enter Your Age : ");
		int p_age = sc.nextInt();
		
		System.out.println("Enter Your Gender : ");
		String p_gen = sc.next();
		
		System.out.println("Enter Your Contact Number : ");
		String p_num = sc.next();
		
		System.out.println("Enter Your Address : ");
		String p_add = sc.next();
		

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Patients where Patient_ID = " + p_id + ";";

		String insertQuery = "insert into Patients " + "values(" + p_id + ",'" + p_name + "'," + p_age + ",'" + p_gen+ "','" + p_num +"','"+ p_add + "')";
		int res1 = st.executeUpdate(deleteQuery);
		int res=0;
		//System.out.println("k==="+res1);
		if(res1==1)
		res = st.executeUpdate(insertQuery);
		//System.out.println(res);

		if (res1 == 0 && res == 0) {
			System.out.println("\nOperation failed.....\nbecause Patient_ID No. "+p_id+" is not in our database\n");
		} else {
			//String insertQuery = "insert into Patients " + "values(" + p_id + ",'" + p_name + "'," + p_age + ",'" + p_gen+ "','" + p_num +"','"+ p_add + "')";

			System.out.println("Record modified succesfully");
		}
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");

		st.close();
		con.close();
	}
	
	public static void Delete_Patient() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Patient ID");
		int p_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Patients where Patient_ID = " + p_id + ";";
		int res = st.executeUpdate(deleteQuery);
		if (res == 0) {
			System.out.println("Record not deleted...... Please Enter a valid Patient_ID..");
		} else {
			System.out.println("Record deleted succesfully");
		}
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
		st.close();
		con.close();

	}
	
	
	
	private static void PrintDetails() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter Your Patient ID");
		int p_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Patients where Patient_ID = " + p_id + ";");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Patient_ID   Patient_Name   Patient_Age  Patient_Gender   Contact_No     Address");
		System.out.println("----------------------------------------------------------------------------");


		while (rs.next()) {

			System.out.println(rs.getInt("Patient_ID") + "        " + rs.getString("Patient_Name") + "        " + rs.getInt("Patient_Age") + "        "+ rs.getString("Patient_Gender") + "        " + rs.getString("Contact_No") + "        " + rs.getString("Address"));


		}
		System.out.println("---------------------------------------------------------------------------------");

		System.out.println("\n\n");
		st.close();
		con.close();

	}

	


	

	public static void PrintAllDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Patients");
		System.out.println("\n\n-------------------------------------------------------------------------------");
		System.out.println("Patient_ID   Patient_Name   Patient_Age  Patient_Gender   Contact_No    Address");
		System.out.println("-------------------------------------------------------------------------------");

		while (rs.next()) {

			System.out.println(rs.getInt("Patient_ID") + "        " + rs.getString("Patient_Name") + "        " + rs.getInt("Patient_Age") + "        "+ rs.getString("Patient_Gender") + "        " + rs.getString("Contact_No") + "        " + rs.getString("Address"));

		}
		System.out.println("--------------------------------------------------------------------------------");

		System.out.println("\n\n");
		st.close();
		con.close();
	}


}