import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.ServerProtocol;
import Controller.ClientNetwork;
import Controller.ServerNetwork;
import View.ViewServer;
import View.ViewClient;

//
//Main Application
//

public class gym {
	public static void main (String[] args){
		//	Setting format of Date & Time to display on Server
		LocalDateTime dateTime = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		// Argument for Client on run.sh file
		if(args.length == 0 || args[0].equals("-c") ){
			
			try {
				// Creating socket
				Socket socket = new Socket("localhost", 8888);
				
				// Establishing socket connections between Client View and Client Network
				ClientNetwork cNetwork = new ClientNetwork (socket);
												
				ViewClient vClient = new ViewClient(cNetwork);
				
				vClient.run();
			} catch (IOException e) {
				// If server not running, display error.
				System.out.println("No Server Running.");
				System.out.println("Error: " + e);
			}
			
			
		}
		
		// Argument for Server on run.sh file
		else if(args[0].equals("-s")){
			
			try{
				//Making connection to DB
				String dbUrl = "jdbc:mysql://localhost/gym";
	 			String username = "admin";
				String password = "root";
		
				// DriverManager connection
				Connection conn = DriverManager.getConnection(dbUrl, username, password);
			
				// Start server if there is a connection
				if (conn != null) {
					System.out.println("Connection Successful!");
					System.out.println("Welcome to the Gym Server!");
					System.out.println("Server started at: " + dateTime.format(formatter));
				}
				
				ServerProtocol servProtocol = new ServerProtocol(conn);
				ViewServer vServer = new ViewServer(servProtocol);
				
				// Start a thead
				Thread sThread = new Thread(vServer);
				sThread.start();
				
	        	try {
							// Creating socket for multithreading
	        		ServerSocket sSocket = new ServerSocket(8888);
	        		
	        		System.out.println("Multithreading server started at: " + dateTime.format(formatter));
	        		
							// Server Network socket connecting to Server Protocol
	        		while (true) {
	        			Socket socket = sSocket.accept();
	        			
	        			ServerNetwork sNetwork = new ServerNetwork(socket, servProtocol);
	        			
								// New thread connection
	        			Thread thread = new Thread(sNetwork);
	        			thread.start();
								System.out.println("Client Connected!");
	        		}
	        	}

	        	catch (IOException ex) {
        	    	System.out.println("Socket could not be initialised: " + ex);	
	        	}
			}
				
			catch (SQLException ex){
				System.out.println("MySQL error: " + ex);			
			}
			
		}
	
	}

}

