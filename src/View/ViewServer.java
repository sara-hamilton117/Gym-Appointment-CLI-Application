package View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.ServerProtocol;
import Model.Booking;
import Model.Client;
import Model.Specialty;
import Model.Trainer;
import Model.TrainerSpecialty;

//
//Server View
//

public class ViewServer implements Runnable {
	// Storing an object of type ServerProtocol as private
	private ServerProtocol _serverProtocol;
	
	// When creating view server, it is also passing the server protocol as a parameter
	public ViewServer(ServerProtocol serverProtocol) {
		this._serverProtocol = serverProtocol;
	}
	
	//Starting runnable
	public void run(){	
  	System.out.println("Type in a command to execute or type 'HELP' for more information.");
		uInput();
	}
	
	// Help message
	public void help() {

		System.out.println("+-----------------------------------+");
  	System.out.println("|Use one of the following commands: |");
  	System.out.println("+-----------------------------------+");
  	System.out.println("|ADD                                |");
  	System.out.println("|LISTALL                            |");
  	System.out.println("|LISTPT                             |");
  	System.out.println("|LISTCLIENT                         |");
  	System.out.println("|LISTDAY                            |");
  	System.out.println("|DELETE                             |");
  	System.out.println("|HELP                               |");
  	System.out.println("|QUIT                               |");
  	System.out.println("+-----------------------------------+");
    	
	}
	
	// App is always waiting for user input using Scanner
	public void uInput(){
		
		Scanner scanner = new Scanner(System.in);
    	
   	String userInput = scanner.nextLine().toUpperCase();
        	
		if (userInput.equals("HELP")) {
			help();
		}
		
		else if (userInput.equals("QUIT")) {
			try {
				// Closes connection
				_serverProtocol.closeConnection();
			} 
			catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if (userInput.equals("LISTALL")) {
			try {
				System.out.println("The bookings are:");
				printBooking(_serverProtocol.listAll());
			} 
			catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if (userInput.equals("LISTPT")) {
			try {
				System.out.println("The trainers are:");
				printPt(_serverProtocol.listPt());
				
				System.out.println("Enter the PT ID in order to view their bookings:");
				
				String ptId;
				ptId = scanner.nextLine();

				if (ptId.equals("HELP") || (ptId.equals("help"))) {
					help();
				}
	   			
				else if (ptId.equals("QUIT") || (ptId.equals("quit"))) {
					try {
						_serverProtocol.closeConnection();
					} 
					catch (SQLException e) {
						System.out.println("Error: " + e);
					}
				}

				else if (ptId.equals("LISTALL") || (ptId.equals("listall"))) {
					try {
			  			System.out.println("The bookings are:");
			  			printBooking(_serverProtocol.listAll());
			  		} 
						catch (SQLException e) {
			  			System.out.println("Error: " + e);
			  		}
				}
				
				else if (ptId.equals("LISTCLIENT") || (ptId.equals("listclient"))) {
					try {
			  			System.out.println("The clients are:");
			  			printClient(_serverProtocol.listClient());
			  			
			  			System.out.println("Enter the Client ID in order to view their bookings:");
			  			
			  			String clientId;
			  			clientId = scanner.nextLine();
			  			
			  			System.out.println("The booking(s) for client " + clientId +" are:");
			  			printBooking(_serverProtocol.listClientBooking(clientId));
			  			
			  		} 
						catch (SQLException e) {
			  			System.out.println("Error: " + e);
			  		}
				}
				
				else if (ptId.equals("LISTDAY") || (ptId.equals("listday"))) {
					try {
			  			System.out.println("The booking dates are:");
			  			printDate(_serverProtocol.listDay());
			  			
			  			System.out.println("Enter the date in order to view bookings on that day:");
			  			
			  			String date;
			  			date = scanner.nextLine();
			  			
			  			System.out.println("The booking(s) for date " + date +" are:");
			  			printBooking(_serverProtocol.listDateBooking(date));
			  			
			  		} catch (SQLException e) {
			  			System.out.println("Error: " + e);
			  		}
				}
				
				else if (ptId.equals("DELETE") || (ptId.equals("DELETE"))) {
					try {
			  			printBooking(_serverProtocol.listAll());
			  			
			  			System.out.println("Enter the Booking ID in order to delete the booking:");
			  			
			  			String bookingId;
			  			bookingId = scanner.nextLine();
			  			
			  			printBooking(_serverProtocol.listDelete(bookingId));
			  			
			  		} catch (SQLException e) {
			  			System.out.println("Error: " + e);
			  		}
				}
				
				else if (ptId.equals("ADD") || (ptId.equals("add"))) {
					try {
			  			System.out.println("The bookings are:");
			   			printBooking(_serverProtocol.listAll());
			   			System.out.println("The clients are:");
			  			printClient(_serverProtocol.listClient());
			  			System.out.println("The trainers are:");
			  			printPt(_serverProtocol.listPt());
			  			System.out.println("The trainer specialties are:");
			  			printTS(_serverProtocol.listTS());
			  			System.out.println("The specialties are:");
			  			printSpecialty(_serverProtocol.listSpecialty());
			  			
			  			System.out.println("Enter the booking details to add to the bookings:");
			  			
			  			String[] bookingDetails;
			  			String uInput;
			  			uInput = scanner.nextLine();
			  			bookingDetails = uInput.split(", ");
			  			
			  			printBooking(_serverProtocol.listAdd(bookingDetails));
			  			
			  		} catch (SQLException e) {
			  			System.out.println("Error: " + e);
			  		}
				}
				
				else {
				System.out.println("The booking(s) for PT " + ptId +" are:");
					printBooking(_serverProtocol.listPTBooking(ptId));
				}
	   		
			} catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if (userInput.equals("LISTCLIENT")) {
			try {
				System.out.println("The clients are:");
				printClient(_serverProtocol.listClient());
				
				System.out.println("Enter the Client ID in order to view their bookings:");
				
				String clientId;
				clientId = scanner.nextLine();
				
				if (clientId.equals("HELP") || (clientId.equals("help"))) {
				help();
			}
 			
			else if (clientId.equals("QUIT") || (clientId.equals("quit"))) {
				try {
					_serverProtocol.closeConnection();
				} catch (SQLException e) {
					System.out.println("Error: " + e);
				}
			}
			else if (clientId.equals("LISTALL") || (clientId.equals("listall"))) {
				try {
		  			System.out.println("The bookings are:");
		  			printBooking(_serverProtocol.listAll());
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (clientId.equals("LISTPT") || (clientId.equals("listpt"))) {
				try {
					System.out.println("The trainers are:");
		  			printPt(_serverProtocol.listPt());
		  			
		  			System.out.println("Enter the PT ID in order to view their bookings:");
		  			
		  			String ptId;
		  			ptId = scanner.nextLine();
		  			
		  			System.out.println("The booking(s) for PT " + ptId +" are:");
	  				printBooking(_serverProtocol.listPTBooking(ptId));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (clientId.equals("LISTDAY") || (clientId.equals("listday"))) {
				try {
		  			System.out.println("The booking dates are:");
		  			printDate(_serverProtocol.listDay());
		  			
		  			System.out.println("Enter the date in order to view bookings on that day:");
		  			
		  			String date;
		  			date = scanner.nextLine();
		  			
		  			System.out.println("The booking(s) for date " + date +" are:");
		  			printBooking(_serverProtocol.listDateBooking(date));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (clientId.equals("DELETE") || (clientId.equals("DELETE"))) {
				try {
		  			printBooking(_serverProtocol.listAll());
		  			
		  			System.out.println("Enter the Booking ID in order to delete the booking:");
		  			
		  			String bookingId;
		  			bookingId = scanner.nextLine();
		  			
		  			printBooking(_serverProtocol.listDelete(bookingId));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (clientId.equals("ADD") || (clientId.equals("add"))) {
				try {
		  			System.out.println("The bookings are:");
		   			printBooking(_serverProtocol.listAll());
		   			System.out.println("The clients are:");
		  			printClient(_serverProtocol.listClient());
		  			System.out.println("The trainers are:");
		  			printPt(_serverProtocol.listPt());
		  			System.out.println("The trainer specialties are:");
		  			printTS(_serverProtocol.listTS());
		  			System.out.println("The specialties are:");
		  			printSpecialty(_serverProtocol.listSpecialty());
		  			
		  			System.out.println("Enter the booking details to add to the bookings:");
		  			
		  			String[] bookingDetails;
		  			String uInput;
		  			uInput = scanner.nextLine();
		  			bookingDetails = uInput.split(", ");
		  			
		  			printBooking(_serverProtocol.listAdd(bookingDetails));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			else {
				System.out.println("The booking(s) for client " + clientId +" are:");
				printBooking(_serverProtocol.listClientBooking(clientId));
			}
			} catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if (userInput.equals("LISTDAY")) {
			try {
				System.out.println("The booking dates are:");
				printDate(_serverProtocol.listDay());
				
				System.out.println("Enter the date in order to view bookings on that day:");
				
				String date;
				date = scanner.nextLine();
				
				if (date.equals("HELP") || (date.equals("help"))) {
				help();
			}
 			
			else if (date.equals("QUIT") || (date.equals("quit"))) {
				try {
					_serverProtocol.closeConnection();
				} catch (SQLException e) {
					System.out.println("Error: " + e);
				}
			}
			else if (date.equals("LISTALL") || (date.equals("listall"))) {
				try {
		  			System.out.println("The bookings are:");
		  			printBooking(_serverProtocol.listAll());
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (date.equals("LISTPT") || (date.equals("listpt"))) {
				try {
					System.out.println("The trainers are:");
		  			printPt(_serverProtocol.listPt());
		  			
		  			System.out.println("Enter the PT ID in order to view their bookings:");
		  			
		  			String ptId;
		  			ptId = scanner.nextLine();
		  			
		  			System.out.println("The booking(s) for PT " + ptId +" are:");
	  				printBooking(_serverProtocol.listPTBooking(ptId));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (date.equals("LISTCLIENT") || (date.equals("listclient"))) {
				try {
		  			System.out.println("The clients are:");
		  			printClient(_serverProtocol.listClient());
		  			
		  			System.out.println("Enter the Client ID in order to view their bookings:");
		  			
		  			String clientId;
		  			clientId = scanner.nextLine();
		  			
		  			System.out.println("The booking(s) for client " + clientId +" are:");
		  			printBooking(_serverProtocol.listClientBooking(clientId));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (date.equals("DELETE") || (date.equals("DELETE"))) {
				try {
		  			printBooking(_serverProtocol.listAll());
		  			
		  			System.out.println("Enter the Booking ID in order to delete the booking:");
		  			
		  			String bookingId;
		  			bookingId = scanner.nextLine();
		  			
		  			printBooking(_serverProtocol.listDelete(bookingId));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			
			else if (date.equals("ADD") || (date.equals("add"))) {
				try {
		  			System.out.println("The bookings are:");
		   			printBooking(_serverProtocol.listAll());
		   			System.out.println("The clients are:");
		  			printClient(_serverProtocol.listClient());
		  			System.out.println("The trainers are:");
		  			printPt(_serverProtocol.listPt());
		  			System.out.println("The trainer specialties are:");
		  			printTS(_serverProtocol.listTS());
		  			System.out.println("The specialties are:");
		  			printSpecialty(_serverProtocol.listSpecialty());
		  			
		  			System.out.println("Enter the booking details to add to the bookings:");
		  			
		  			String[] bookingDetails;
		  			String uInput;
		  			uInput = scanner.nextLine();
		  			bookingDetails = uInput.split(", ");
		  			
		  			printBooking(_serverProtocol.listAdd(bookingDetails));
		  			
		  		} catch (SQLException e) {
		  			System.out.println("Error: " + e);
		  		}
			}
			else {
				System.out.println("The booking(s) for date " + date +" are:");
				printBooking(_serverProtocol.listDateBooking(date));
			}
			} catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if (userInput.equals("DELETE")) {
			try {
				printBooking(_serverProtocol.listAll());
				
				System.out.println("Enter the Booking ID in order to delete the booking:");
				
				String bookingId;
				bookingId = scanner.nextLine();
				
				printBooking(_serverProtocol.listDelete(bookingId));
				
			} catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else if(userInput.equals("ADD")) {
			try {
				System.out.println("The bookings are:");
	 			printBooking(_serverProtocol.listAll());
	 			System.out.println("The clients are:");
				printClient(_serverProtocol.listClient());
				System.out.println("The trainers are:");
				printPt(_serverProtocol.listPt());
				System.out.println("The trainer specialties are:");
				printTS(_serverProtocol.listTS());
				System.out.println("The specialties are:");
				printSpecialty(_serverProtocol.listSpecialty());
				
				System.out.println("Enter the booking details to add to the bookings:");
				
				String[] bookingDetails;
				String uInput;
				uInput = scanner.nextLine();
				bookingDetails = uInput.split(", ");
				
				printBooking(_serverProtocol.listAdd(bookingDetails));
				
			} catch (SQLException e) {
				System.out.println("Error: " + e);
			}
		}
		
		else{
			System.out.println("Invalid command. Type 'HELP' for more information.");
		}
	
		uInput();
}
	
	public void printBooking(ArrayList<Booking> bookingList) {
		//for each object of type booking in bookingList, displays the bookings
		for (Booking eachBooking : bookingList) {
			eachBooking.printBooking();
		}
	}
	
	public void printDate(ArrayList<Booking> bookingList) {
		for (Booking eachBooking : bookingList) {
			eachBooking.printDate();
		}
	}
	
	public void printPt(ArrayList<Trainer> trainerList) {
		for (Trainer eachTrainer : trainerList) {
			eachTrainer.printPt();
		}
	}
	
	public void printClient(ArrayList<Client> clientList) {
		for (Client eachClient : clientList) {
			eachClient.printClient();
		}
	}
	
	public void printSpecialty(ArrayList<Specialty> specialtyList) {
		for (Specialty eachSpecialty : specialtyList) {
			eachSpecialty.printSpecialty();
		}
	}
	
	public void printTS(ArrayList<TrainerSpecialty> tsList) {
		for (TrainerSpecialty eachTS : tsList) {
			eachTS.printTS();
		}
	}
}

