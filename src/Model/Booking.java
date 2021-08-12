package Model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
	
	private String _bookingId;
	private String _clientId;
	private String _ptId;
	private SimpleDateFormat _bookingDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat _bookingTime = new SimpleDateFormat("hh:mm:ss");
	private Integer _bookingDuration;
	private String _specialtyId;
	
	public Booking(String _bookingId, String _clientId, String _ptId, SimpleDateFormat _boookingDate,
			SimpleDateFormat _bookingTime, Integer _bookingDuration, String _specialtyId) {
		super();
		
		this._bookingId = _bookingId;
		this._clientId = _clientId;
		this._ptId = _ptId;
		this._bookingDate = _boookingDate;
		this._bookingTime = _bookingTime;
		this._bookingDuration = _bookingDuration;
		this._specialtyId = _specialtyId;
	}
	

	public Booking(SimpleDateFormat _bookingDate) {
		super();
		this._bookingDate = _bookingDate;
	}

	public void printBooking() {
		
		System.out.println(_bookingId + ", " + _clientId + ", " + _ptId + ", " + _bookingDate.format(new Date()) + ", " + _bookingTime.format(new Date()) + ", " + _bookingDuration.toString() + ", " + _specialtyId );
	}
	
	public String returnStringBooking() {
		
		String booking = (_bookingId + ", " + _clientId + ", " + _ptId + ", " + _bookingDate.format(new Date()) + ", " + _bookingTime.format(new Date()) + ", " + _bookingDuration.toString() + ", " + _specialtyId );
		return booking;
	}
	
	public String returnDate() {
		String datebooking = (_bookingDate.format(new Date()));
		return datebooking;
	}
	
	public void printDate() {
		System.out.println(_bookingDate.format(new Date()));
	}
	
	public String get_bookingId() {
		return _bookingId;
	}

	public void set_bookingId(String _bookingId) {
		this._bookingId = _bookingId;
	}

	public String get_clientId() {
		return _clientId;
	}

	public void set_clientId(String _clientId) {
		this._clientId = _clientId;
	}

	public String get_ptId() {
		return _ptId;
	}

	public void set_ptId(String _ptId) {
		this._ptId = _ptId;
	}

	public SimpleDateFormat get_boookingDate() {
		return _bookingDate;
	}

	public void set_boookingDate(SimpleDateFormat _boookingDate) {
		this._bookingDate = _boookingDate;
	}

	public SimpleDateFormat get_bookingTime() {
		return _bookingTime;
	}

	public void set_bookingTime(SimpleDateFormat _bookingTime) {
		this._bookingTime = _bookingTime;
	}

	public int get_bookingDuration() {
		return _bookingDuration;
	}

	public void set_bookingDuration(int _bookingDuration) {
		this._bookingDuration = _bookingDuration;
	}

	public String get_specialtyId() {
		return _specialtyId;
	}

	public void set_specialtyId(String _specialtyId) {
		this._specialtyId = _specialtyId;
	}
	
}
