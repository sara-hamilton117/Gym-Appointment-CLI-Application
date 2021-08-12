//This interacts with the database

package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Booking;
import Model.Client;
import Model.Specialty;
import Model.Trainer;
import Model.TrainerSpecialty;

public class ServerProtocol {
	
	private Connection _connection;
	
	public ServerProtocol(Connection connection) {
		this._connection = connection;
	}
	
	public ArrayList<Booking> listAll() throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT * FROM booking";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			Booking booking = new Booking(result.getString(1),result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), new SimpleDateFormat(result.getString(5)), Integer.parseInt(result.getString(6)), result.getString(7));
			
			bookingList.add(booking);
		}
		
		
		return bookingList;
	}
	
	public ArrayList<Specialty> listSpecialty() throws SQLException{
		ArrayList<Specialty> specialtyList = new ArrayList<Specialty>();
		
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT * FROM specialty";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			Specialty specialty = new Specialty(result.getString(1),  result.getString(2), Integer.parseInt(result.getString(3)));
			
			specialtyList.add(specialty);
		}
		
		return specialtyList;
	}
	
	public ArrayList<TrainerSpecialty> listTS() throws SQLException{
		ArrayList<TrainerSpecialty> tsList = new ArrayList<TrainerSpecialty>();
		
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT * FROM trainer_specialty";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			TrainerSpecialty ts = new TrainerSpecialty(result.getString(1),  result.getString(2));
			
			tsList.add(ts);
		}
		
		return tsList;
	}
		
	public ArrayList<Booking> listDay() throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT booking_date FROM booking";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			Booking booking = new Booking(new SimpleDateFormat(result.getString(1)));
			
			bookingList.add(booking);
		}
		
		return bookingList;
	}
	
	public ArrayList<Trainer> listPt() throws SQLException{
		ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
		
	
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT * FROM personal_trainer";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			Trainer trainer = new Trainer(result.getString(1), result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), result.getString(5), result.getString(6),result.getString(7), result.getString(8));
			
			trainerList.add(trainer);
		}
		
		
		return trainerList;
		}
	
	
	
	public ArrayList<Client> listClient() throws SQLException{
		ArrayList<Client> clientList = new ArrayList<Client>();
		
	
		Statement statement;
	
		statement = _connection.createStatement();
		
		String query = "SELECT * FROM client";
		
		ResultSet result = statement.executeQuery(query);
		
		while(result.next()) {
			Client client = new Client(result.getString(1), result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), result.getString(5), result.getString(6),result.getString(7), result.getString(8));
			
			clientList.add(client);
		}
		
		
		return clientList;
	}
	
	public ArrayList<Booking> listPTBooking(String ptId) throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "SELECT * FROM booking WHERE pt_id = ?";
		
		PreparedStatement pStatement = _connection.prepareStatement(query);
		
		pStatement.setString(1, ptId);
			
		ResultSet result = pStatement.executeQuery();
		
		while(result.next()) {
			Booking booking = new Booking(result.getString(1),result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), new SimpleDateFormat(result.getString(5)), Integer.parseInt(result.getString(6)), result.getString(7));
			
			bookingList.add(booking);
		}
		
		
		return bookingList;
		
	}
	
	public ArrayList<Booking> listDelete(String bookingId) throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "DELETE FROM booking WHERE booking_id = ?";
		
		PreparedStatement pStatement = _connection.prepareStatement(query);
		
		pStatement.setString(1, bookingId);
			
		int result = pStatement.executeUpdate();
		
		if (result > 0) {
		    System.out.println("The booking is deleted.");
		}
		else {
			System.out.println("Error while deleting booking.");
		}
		return bookingList;
		
	}
	
	public ArrayList<Booking> listClientBooking(String clientId) throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "SELECT * FROM booking WHERE client_id = ?";
		
		PreparedStatement pStatement = _connection.prepareStatement(query);
		
		pStatement.setString(1, clientId);
			
		ResultSet result = pStatement.executeQuery();
		
		while(result.next()) {
			Booking booking = new Booking(result.getString(1),result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), new SimpleDateFormat(result.getString(5)), Integer.parseInt(result.getString(6)), result.getString(7));
			
			bookingList.add(booking);
		}
				
		return bookingList;
		
	}
	
	public ArrayList<Booking> listDateBooking(String date) throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "SELECT * FROM booking WHERE booking_date = ?";
		
		PreparedStatement pStatement = _connection.prepareStatement(query);
		
		pStatement.setString(1, date);
			
		ResultSet result = pStatement.executeQuery();
		
		while(result.next()) {
			Booking booking = new Booking(result.getString(1),result.getString(2),result.getString(3), new SimpleDateFormat(result.getString(4)), new SimpleDateFormat(result.getString(5)), Integer.parseInt(result.getString(6)), result.getString(7));
			
			bookingList.add(booking);
		}
				
		return bookingList;
		
	}
	
	public ArrayList<Booking> listAdd(String[] BookingDetails) throws SQLException{
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		
		String query = "INSERT INTO booking (booking_id, client_id, pt_id, booking_date, booking_time, booking_duration, specialty_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pStatement = _connection.prepareStatement(query);
		
		pStatement.setString(1, BookingDetails[0]);
		pStatement.setString(2, BookingDetails[1]);
		pStatement.setString(3, BookingDetails[2]);
		pStatement.setString(4, BookingDetails[3]);
		pStatement.setString(5, BookingDetails[4]);
		pStatement.setString(6, BookingDetails[5]);
			
		int result = pStatement.executeUpdate();
		
		if (result > 0) {
		    System.out.println("The booking is added.");
		}
		else {
			System.out.println("Error while adding booking.");
		}
		return bookingList;
		
	}
	
	public void closeConnection() throws SQLException {
		LocalDateTime dateTime = LocalDateTime.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println("Bye!");
		System.out.println("Server closed at: " + dateTime.format(formatter));
		_connection.close();
		System.exit(0);
		
	}
	
}
	



