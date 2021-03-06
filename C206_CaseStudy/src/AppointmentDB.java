import java.util.ArrayList;
/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * 19030158, 11 Aug 2020 3:58:02 pm
 * Created by jenessa
 */

public class AppointmentDB {

private static ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public static void populateAppointmentDB() {
		appointmentList.add(new Appointment("13/08", "7:30 P.M", "John Smith", "CBR 1000"));
		appointmentList.add(new Appointment("15/08", "12:45 P.M", "Jane Doe", "Bike oil"));
		System.out.println("pop added");
	}
	
	public static void cleanupAppointmentDB() {
		appointmentList.clear();
		
	}
	
	
	public static void handleAppointmentOptions() {
		//show appointment menu, ask user for input, process the choice
		int subOption = 0;
		showAppointmentMenu();
		subOption = Helper.readInt("What would you like to do?: " );
		processOption(subOption);
	}
	
	public static void showAppointmentMenu() {
		System.out.println("Appointment Menu");
		System.out.println("1: View appointment list");
		System.out.println("2. Add in an appointment");
		System.out.println("3. Delete an appointment");
		System.out.println("4. Update an appointment");
		System.out.println("5. Quit");
		Helper.line(80, "-");
	}	
	
	public static void processOption(int subOption) {
		while (subOption != 5) {
			if (subOption == 1) {
				viewAllAppointment(appointmentList);
			
		
			} else if (subOption  == 2) {
				Appointment appt = inputAppointment();
				addAppointment(appointmentList, appt);
				
		
			} else if (subOption  == 3) {
				viewAllAppointment(appointmentList);
				delAppointment(appointmentList, selectAppointment());
				
			} else if (subOption == 4) {
				viewAllAppointment(appointmentList);
				updateAppt(appointmentList, updateAppointmentSelection());
				
			}else if (subOption == 5) {
				System.out.println("Goodbye!");
				
			}else  {
				//invalid option
				System.out.println("Invalid type");
			}
			//show the menu again & ask for option
			handleAppointmentOptions();	
		}
	}
	//==========Option 1 ==============
	public static String retrieveAllAppointment(ArrayList<Appointment> appointmentList) {
		String output = "";
		
		for (Appointment i: appointmentList) {
			output += String.format("%-20s %-20s %-80s %-80s\n" ,  i.getDate(), i.getTime(), i.getCustomerName(),i.getProductDetail());
		}
		return output;
	}
	
	public static void viewAllAppointment(ArrayList<Appointment> appointmentList) {
		Helper.line(80, "-");
		System.out.println("Displaying appointments");
		Helper.line(80, "-");

		String output = String.format("%-20s %-20s %-80s %-80s\n", "Date", "Time", "Customer name", "Product ordered");
		output+= retrieveAllAppointment(appointmentList);
		System.out.println(output);
		
		char date = Helper.readChar("Would you like to search the list by date? (Y/N): ");
		if (date == 'Y' || date == 'y') {
			output = String.format("%-20s %-20s %-80s %-80s\n", "Date", "Time", "Customer name", "Product ordered");
			String input = Helper.readString("Please enter date (DD/MM): ");
			for (Appointment i : appointmentList) {
				if (input.equals(i.getDate())) {
					output+= String.format("%-20s %-20s %-80s %-80s\n", i.getDate(), i.getTime(), i.getCustomerName(), i.getProductDetail());
					System.out.println(output);
				
				}
			}
			
		}
		
	}
	
	
	//==========Option 2 ==============
	public static Appointment inputAppointment () {
		String date = Helper.readString("Enter date of appointment (DD/MM): ");
		String time = Helper.readString("Enter time of appointment (HH:MM P.M/A.M): ");
		String name = Helper.readString("Enter name of customer: ");
		String product = Helper.readString("Enter product to collect: ");
		
		Appointment appt = new Appointment (date, time, name, product);
		return appt;
	}
	
	
	public static void addAppointment(ArrayList<Appointment> appointmentList, Appointment appt) {
		//request user for the bike to add to the list
		Helper.line(80, "-");
		System.out.println("Creating a new appointment");
		Helper.line(80, "-");	
		appointmentList.add(appt);
	}

	//==========Option 3 ==============
	public static String selectAppointment() {
		String customer = Helper.readString("Enter customer's name to delete from appointment list: ");
		return customer;
	}
	
	public static void delAppointment(ArrayList<Appointment> appointmentList, String name) {
		Helper.line(80, "-");
		System.out.println("Deleting an appointment");
		Helper.line(80, "-");
		String selection = selectAppointment();
		for (Appointment i : appointmentList) {
			if (i.getCustomerName().equalsIgnoreCase(name)) {
				
				System.out.println("Appointment deleted");
				appointmentList.remove(i);
				break;
			} else {
				System.out.println("No such appointment with customer name found");
				break;
			}
		} 
	}
	public static String updateAppointmentSelection() {
		String customer = Helper.readString("Enter customer's name to update from appointment list: ");
		return customer;
	}
	public static void updateAppt (ArrayList<Appointment> appointmentList, String customer) {
		Helper.line(80, "-");
		System.out.println("Updating an appointment");
		Helper.line(80, "-");
		for (int n = 0; n < appointmentList.size(); n++) {
			if (appointmentList.get(n).getCustomerName().equalsIgnoreCase(customer)) {
				String newDate = Helper.readString("Enter date of appointment: ");
				String newTime = Helper.readString("Enter timing of appointment: ");
				String newProduct = Helper.readString("Enter new product to be collected by customer: ");
				Appointment updatedProduct = new Appointment (newDate, newTime,customer, newProduct);
				appointmentList.set(n, updatedProduct);
				System.out.println("Appointment updated");
				break;
			} else {
				System.out.println("Customer name not in the appointment list");
			}
			
		}
	}
}