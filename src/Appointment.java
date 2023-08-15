import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Appointment {

	public static void Appointment_Menu() {
		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			displayMenu();
			choice = sc.nextInt();
			sc.nextLine(); // Consume the newline character5

			switch (choice) {
			case 1:
				try {
					Make_Appointment();
				} catch (Exception e1) {
					System.out.println(e1);

				}

				break;
			case 2:
				try {
					Modify_Appointment();
				} catch (Exception e1) {
					System.out.println(e1);

				}
				break;
			case 3:
				try {
					Delete_Appointment();
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
				System.out.println("Exiting the Appointment menu .");
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
				break;
			}
		} while (choice != 0);
	}
	
	private static void displayMenu() {
		System.out.println("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

		System.out.println("-----------------You are in the Appointment Menu-------------------- \nPlease select a option:  \n");
		System.out.println("\nPress 1 for Make a Appointment\nPress 2 for Modify Appointment Details\nPress 3 for Delete Appointment Record\nPress 4 for View Single Record\n"+ "Press 5 for View All Records\nPress 0 for Exit");

	}
	
	
	public static void Make_Appointment() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

		System.out.println("Below is Registration form, and Available doctor detaisl,  Please fill all the details.......\n");
		

		System.out.println("Enter Appointment ID");
		int a_id = sc.nextInt();
		
	    Doctor.PrintDetails();
		System.out.println("Select Dr. Name");
		sc.nextLine();
		String dr_name = sc.nextLine();
		System.out.println("Enter Dr Contact");
		long dr_cont = sc.nextLong();
		System.out.println("Enter Patient Name");
		String p_name = sc.next();
		System.out.println("Enter Date");//........date
		String a_date = sc.next();
		

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		int res=0;
		try {
		String insertQuery = "insert into Appointment " + "values(" + a_id + ",'" + dr_name + "'," + dr_cont + ",'" + p_name+ "','" + a_date + "')";// may be problem will occur
		 res = st.executeUpdate(insertQuery);}
		catch(Exception e) {
			System.out.println("\nThis Appointment_ID is already exists ...Please enter a new Appointment_ID\n");
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
	

	private static void Modify_Appointment() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease Enter Your Appointment ID for update your data");
		int a_id = sc.nextInt();
		System.out.println(".*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.");

		System.out.print("\nPlease Update your Details and Select your Doctor: \n");
		Doctor.PrintDetails();

		
		System.out.println("Select Dr. Name : ");
		sc.nextLine();
		String dr_name = sc.nextLine();
		
		System.out.println("Enter Dr. Contact : ");
		long dr_cont = sc.nextLong();
		
		System.out.println("Enter Patient Name : ");
		String p_name = sc.next();
		
		System.out.println("Enter Date : ");// .................date
		String a_date = sc.next();
		
	
		

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Appointment where Appointment_ID = " + a_id + ";";

		String insertQuery = "insert into Appointment " + "values(" + a_id + ",'" + dr_name + "'," + dr_cont + ",'" + p_name+ "','" + a_date + "')";// problem may occur
		int res1 = st.executeUpdate(deleteQuery);
		int res=0;
		//System.out.println("k==="+res1);
		if(res1==1)
		res = st.executeUpdate(insertQuery);
		//System.out.println(res);

		if (res1 == 0 && res == 0) {
			System.out.println("\nOperation failed.....\nbecause Appointment_ID No. "+a_id+" is not in our database\n");
		}  else {
			//String insertQuery = "insert into Patients " + "values(" + p_id + ",'" + p_name + "'," + p_age + ",'" + p_gen+ "','" + p_num +"','"+ p_add + "')";

			System.out.println("Record modified succesfully");
		}
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");

		st.close();
		con.close();
	}
	
	public static void Delete_Appointment() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Appointment ID");
		int a_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		String deleteQuery = "delete from Appointment where Appointment_ID = " + a_id + ";";
		int res = st.executeUpdate(deleteQuery);
		if (res == 0) {
			System.out.println("Record not deleted...... Please Enter a valid Appointment_ID..");
		} else {
			System.out.println("Record deleted succesfully");
		}
		System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
		st.close();
		con.close();

	}
	
	
	
	public static void PrintDetails() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter Your Appointment ID");
		int a_id = sc.nextInt();

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Doctor_Appointment", "root","bkbhai7582");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from Appointment where Appointment_ID = " + a_id + ";");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Appointment_ID   Dr_Name   Dr. Contact  Patient_Name   Date");
		System.out.println("-----------------------------------------------------------");


		while (rs.next()) {

			System.out.println(rs.getInt("Appointment_ID") + "        " + rs.getString("Dr_Name") + "        " + rs.getLong("Dr_Contact") + "        "+ rs.getString("Patient_Name") + "        " + rs.getString("Date"));


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
		ResultSet rs = st.executeQuery("select * from Appointment");
		System.out.println("\nHere is all the Appointments");
		System.out.println("\n\n----------------------------------------------------------------------------");
		System.out.println("Appointment_ID   Dr_Name   Dr. Contact  Patient_Name   Date");
		System.out.println("----------------------------------------------------------------------------");

		while (rs.next()) {

			System.out.println(rs.getInt("Appointment_ID") + "        " + rs.getString("Dr_Name") + "        " + rs.getLong("Dr_Contact") + "        "+ rs.getString("Patient_Name") + "        " + rs.getString("Date"));

		}
		System.out.println("----------------------------------------------------------------------------");

		System.out.println("\n\n");
		st.close();
		con.close();
	}

	

	

}