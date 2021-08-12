package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Model.Booking;
import Model.Client;
import Model.Trainer;

public class ServerNetwork implements Runnable {
	
	private final Socket _socket;
	private final ServerProtocol _protocol;
	
	public ServerNetwork(Socket socket, ServerProtocol protocol) {
		this._socket = socket;
		this._protocol = protocol;
	}
	
	public void run() {
		try {
			listenToClient();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
		}
	}
	
	public void listenToClient() throws SQLException {
		
		try {
		OutputStream outs = _socket.getOutputStream ();
		InputStream ins = _socket.getInputStream ();
		
		PrintWriter printToClient = new PrintWriter (outs );
		Scanner in = new Scanner(ins );
		
			while (true) {
			String input = in.nextLine();
			
				if (input.equals("LISTALL")) {
					ArrayList<Booking> allBookings = new ArrayList<Booking>();
					ArrayList<String> allBookingsString = new ArrayList<String>();
				
					allBookings = _protocol.listAll();
										
					for (Booking eachBooking : allBookings) {
						allBookingsString.add(eachBooking.returnStringBooking());
					}
					
					printToClient.println(allBookingsString.size());
					
					printToClient.flush();
					
					for (String eachBookingString : allBookingsString) {
						printToClient.println(eachBookingString);
					}
					printToClient.flush();
					
				}
				
				else if (input.equals("LISTCLIENT")) {
					ArrayList<Client> allClients = new ArrayList<Client>();
					ArrayList<Booking> allClientBookings = new ArrayList<Booking>();
					ArrayList<String> allClientsString = new ArrayList<String>();
					ArrayList<String> allBookingsString = new ArrayList<String>();
					
					allClients = _protocol.listClient();
					
					for (Client eachClient : allClients) {
						allClientsString.add(eachClient.returnStringClient());
					}
					
					printToClient.println(allClientsString.size());
					
					printToClient.flush();
					
					for (String eachClientString : allClientsString) {
						printToClient.println(eachClientString);
					}
					printToClient.flush();
					
					String clientId = in.nextLine();
					
					allClientBookings = _protocol.listClientBooking(clientId);
					
					for (Booking eachBooking : allClientBookings) {
						allBookingsString.add(eachBooking.returnStringBooking());
					}

					//send bookings for pt
					printToClient.println(allClientBookings.size());
					
					printToClient.flush();
					
					for (String eachBookingString : allBookingsString) {
						printToClient.println(eachBookingString);
					}
					printToClient.flush();
				}
				
				else if (input.equals("LISTPT")) {
					ArrayList<Trainer> allTrainers = new ArrayList<Trainer>();
					ArrayList<Booking> allPtBookings = new ArrayList<Booking>();
					ArrayList<String> allTrainersString = new ArrayList<String>();
					ArrayList<String> allBookingsString = new ArrayList<String>();
					
					//getting all pts
					allTrainers = _protocol.listPt();
					
					
					//sending all pts
					for (Trainer eachTrainer : allTrainers) {
						allTrainersString.add(eachTrainer.returnStringTrainer());
					}
					
					printToClient.println(allTrainersString.size());
					
					printToClient.flush();
					
					for (String eachTrainersString : allTrainersString) {
						printToClient.println(eachTrainersString);
					}
					printToClient.flush();
					
					//reading pt id
					String ptId = in.nextLine();
					
					//getting bookings for pt
					allPtBookings = _protocol.listPTBooking(ptId);
					
					for (Booking eachBooking : allPtBookings) {
						allBookingsString.add(eachBooking.returnStringBooking());
					}

					//send bookings for pt
					printToClient.println(allPtBookings.size());
					
					printToClient.flush();
					
					for (String eachBookingString : allBookingsString) {
						printToClient.println(eachBookingString);
					}
					printToClient.flush();
				}
				
				else if (input.equals("LISTDAY")) {
					ArrayList<Booking> allBookings = new ArrayList<Booking>();
					ArrayList<Booking> allDateBookings = new ArrayList<Booking>();
					ArrayList<String> allDateBookingStrings = new ArrayList<String>();
					ArrayList<String> allBookingsString = new ArrayList<String>();
				
					allBookings = _protocol.listDay();
										
					for (Booking eachBooking : allBookings) {
						allDateBookingStrings.add(eachBooking.returnDate());
					}
					
					printToClient.println(allDateBookingStrings.size());
					
					printToClient.flush();
					
					for (String eachBookingString : allDateBookingStrings) {
						printToClient.println(eachBookingString);
					}
					printToClient.flush();
					
					String date = in.nextLine();
					
					//returning list of all bookings at specified date
					allDateBookings = _protocol.listDateBooking(date);
					
					for (Booking eachBookings : allDateBookings) {
						allBookingsString.add(eachBookings.returnStringBooking());
					}	
					printToClient.println(allDateBookings.size());
					
					printToClient.flush();
					
					for (String eachBookingString : allBookingsString) {
						printToClient.println(eachBookingString);
					}
					printToClient.flush();
						
					
				}
				
				else {
					System.out.println("Invalid Comment");
				}
			
			}
			
			
		}
		
		catch (IOException e) {
			System.out.println("Error: " + e);
		}catch (NoSuchElementException e) {
			System.out.println("Client Disconnected.");
		}
	}
}

