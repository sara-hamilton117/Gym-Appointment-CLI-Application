package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Model.Booking;
import Model.Client;
import Model.Trainer;

public class ClientNetwork {
	private final Socket _socket;
	
	public ClientNetwork(Socket socket) {
		this._socket = socket;
	}
		
	public void sendToServer() {
		try {
			OutputStream outs = _socket.getOutputStream ();
			//InputStream ins = _socket.getInputStream ();
			
			PrintWriter printToServer = new PrintWriter (outs);
			//Scanner in = new Scanner(ins);
			
			Scanner scan = new Scanner(System.in);	
			
			while (true){
			
			String userInput = scan.nextLine();
			
			
			printToServer.println(userInput);
			
			printToServer.flush();
			}
			
		}
			
			catch (IOException e) {
				System.out.println("Error: " + e);
			}
	}
	
	public ArrayList<Booking> listAll() {
		
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println("LISTALL");
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");
				
				Booking booking = new Booking(details[0], details[1], details[2], new SimpleDateFormat(details[3]), new SimpleDateFormat(details[4]), Integer.parseInt(details[5]), details[6]);
				
				bookingList.add(booking);
			}
			
			
		}
		catch(IOException e) {
			
		}
		return bookingList;
		
	}

	public ArrayList<Client> listClient() {
		
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		try {
			
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println("LISTCLIENT");
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");

				Client client = new Client(details[0], details[1], details[2], new SimpleDateFormat(details[3]), details[4], details[5], details[6], details[7]);
				
				clientList.add(client);
			}
			
		
		}
		catch(IOException e) {
			
		}
		return clientList;
	}
	
	public ArrayList<Trainer> listPt() {
		
		ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
		
		try {
			
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println("LISTPT");
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");

				Trainer trainer = new Trainer(details[0], details[1], details[2], new SimpleDateFormat(details[3]), details[4], details[5], details[6], details[7]);
				
				trainerList.add(trainer);
			}
			
		
		}
		catch(IOException e) {
			
		}
		return trainerList;
	}

	public ArrayList<Booking> listPTBooking(String ptId) {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println(ptId);
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");
				
				Booking booking = new Booking(details[0], details[1], details[2], new SimpleDateFormat(details[3]), new SimpleDateFormat(details[4]), Integer.parseInt(details[5]), details[6]);
				
				bookingList.add(booking);
			}
		}
		catch (IOException e) {
			
		}
		
		return bookingList;
	
	}
	
	public ArrayList<Booking> listClientBooking(String clientId) {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println(clientId);
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");
				
				Booking booking = new Booking(details[0], details[1], details[2], new SimpleDateFormat(details[3]), new SimpleDateFormat(details[4]), Integer.parseInt(details[5]), details[6]);
				
				bookingList.add(booking);
			}
		}
		catch (IOException e) {
			
		}
		
		return bookingList;
	
	}
	
	public ArrayList<Booking> listDay() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println("LISTDAY");
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");
				
				Booking booking = new Booking(new SimpleDateFormat(details[0]));
				
				bookingList.add(booking);
			}
		}
		catch (IOException e) {
			
		}
		
		return bookingList;
	
	}
	
	public ArrayList<Booking> listDateBooking(String date) {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			OutputStream outs = _socket.getOutputStream();
			
			PrintWriter printToServer = new PrintWriter(outs);
			
			printToServer.println(date);
			
			printToServer.flush();
			
			InputStream ins = _socket.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			
			ArrayList<String> allReceived = new ArrayList<String>();
			String str;
			
			int counter = 0;
			
			counter = Integer.parseInt(reader.readLine());
						
			for (int i = 0; i < counter; i++) {
				str = reader.readLine();
				allReceived.add(str);
			}
			
			//Convert strings into bookings
			for(String string : allReceived) {
				String[] details = string.split(", ");
				
				Booking booking = new Booking(details[0], details[1], details[2], new SimpleDateFormat(details[3]), new SimpleDateFormat(details[4]), Integer.parseInt(details[5]), details[6]);
				
				bookingList.add(booking);
			}
		}
		catch (IOException e) {
			
		}
		
		return bookingList;
	
	}

	
}

