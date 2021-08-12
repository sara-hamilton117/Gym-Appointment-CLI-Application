package View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.ClientNetwork;
import Model.Booking;
import Model.Client;
import Model.Trainer;

//
//Client View
//

public class ViewClient {
	
		private ClientNetwork _network;
		
		public ViewClient(ClientNetwork network) {
			this._network = network;
		}
		
		public void run() throws IOException {
			System.out.println("Type in a command to execute or type 'HELP' for more information.");
			uInput();
		}
		
		public void help() {

		System.out.println("+-----------------------------------+");
	    	System.out.println("|Use one of the following commands: |");
	    	System.out.println("+-----------------------------------+");
	    	System.out.println("|LISTALL                            |");
	    	System.out.println("|LISTPT                             |");
	    	System.out.println("|LISTCLIENT                         |");
	    	System.out.println("|LISTDAY                            |");
	    	System.out.println("|HELP                               |");
	    	System.out.println("|QUIT                               |");
	    	System.out.println("+-----------------------------------+");
	    	
		}
		
		public void uInput() throws IOException{
			
			Scanner scanner = new Scanner(System.in);
	    	
	    	String userInput = scanner.nextLine().toUpperCase();
	    
	    	
	    	if (userInput.equals("HELP")) {
	    		help();
	    	}
	    	
	    	else if (userInput.equals("QUIT")) {
	    		System.out.println("Bye!");
	    		System.exit(0);
	    	}
	    	
	    	else if (userInput.equals("LISTALL")) {
	    		System.out.println("The bookings are:");
	    		printBooking(_network.listAll());
	    	}
	    	
	    	else if (userInput.equals("LISTCLIENT")) {
	    		System.out.println("The clients are:");
	    		printClient(_network.listClient());
	    		
	    		System.out.println("Enter the Client ID in order to view their bookings:");
    			
    			String clientId;
       			clientId = scanner.nextLine();
       			
       			System.out.println("The booking(s) for client " + clientId +" are:");
    			printBooking(_network.listClientBooking(clientId));
	    	}
	    	
	    	else if (userInput.equals("LISTPT")) {
	    		System.out.println("The personal trainers are:");
	    		printPt(_network.listPt());
	    		
	    		System.out.println("Enter the PT ID in order to view their bookings:");
    			
    			String ptId;
       			ptId = scanner.nextLine();
       			
       			System.out.println("The booking(s) for PT " + ptId +" are:");
    			printBooking(_network.listPTBooking(ptId));
	    	}
	    	
	    	else if (userInput.equals("LISTDAY")) {
	    		System.out.println("The booking dates are:");
	    		printDate(_network.listDay());
	    		
	    		System.out.println("Enter the date in order to view bookings on that day:");
    			
    			String date;
       			date = scanner.nextLine();
       			
       			System.out.println("The booking(s) for date " + date + " are:");
    			printBooking(_network.listDateBooking(date));
	    	}
	    	
	    	else {
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
		
		public void printClient(ArrayList<Client> clientList) {
			//for each object of type client in bookingList, displays the bookings
			for (Client eachClient : clientList) {
				eachClient.printClient();
			}
		}
		
		public void printPt(ArrayList<Trainer> trainerList) {
			//for each object of type trainer in bookingList, displays the bookings
			for (Trainer eachTrainer : trainerList) {
				eachTrainer.printPt();
			}
		}
		
		public void printDate(ArrayList<Booking> bookingList) {
			//for each object of type booking in bookingList, displays the bookings
			for (Booking eachBooking : bookingList) {
				eachBooking.printDate();
			}
		}
}

