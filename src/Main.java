

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc= new Scanner(System.in);
		int ch;

		do {
			displayMenu();
			ch = sc.nextInt();
			sc.nextLine();

			switch (ch) {
			case 1:
				Patients.Patient_Menu();

				break;
			case 2:
				Doctor.PrintDetails();
				break;
			case 3:

				Appointment.Appointment_Menu();
				break;
			case 0:
				System.out.println("Are you sure to exit from the Main menu..!\n");
				System.out.println("Press y/Y for exit");
				char o=sc.next().charAt(0);
				if(o=='Y'||o=='y') {
					System.out.println("Thank you....");
					System.exit(0);
				}
				
				else
					break;
			default:
				System.out.println("Invalid Choice..... Please select a valid option.");
				//break;
			}
		} while (ch<4);

		sc.close();
	}

	private static void displayMenu() {
		
		System.out.println(":.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.MEDICARE HOSPITAL:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.:.");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("\n\n");
		System.out.println(".*.*.*.*.*.*.*.*.*.*.*.*.*You are in Main Menu.*.*.*.*.*.*.*.*.*.*.*.*.*\n");
		System.out.println("Please Select an option...");
		System.out.println("\n");
		System.out.println("Press 1 for Patient Details ");
		System.out.println("Press 2 for Doctor Details ");
		System.out.println("Press 3 for Take an Appointment ");
		System.out.println("Press 0 for Exiting Main Menu");

	}

}