package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	
	private String _clientId;
	
	private String _name;
	private String _sex;
	private SimpleDateFormat _dob = new SimpleDateFormat("yyyy-MM-dd");
	private String _email;
	private String _no;
	private String _address1;
	private String _address2;
	
	public Client(String _clientId, String _name, String _sex, SimpleDateFormat _dob, String _email, String _no,
			String _address1, String _address2) {
		super();
		this._clientId = _clientId;
		this._name = _name;
		this._sex = _sex;
		this._dob = _dob;
		this._email = _email;
		this._no = _no;
		this._address1 = _address1;
		this._address2 = _address2;
	}
	
	public void printClient() {
		
		System.out.println(_clientId + ", " + _name + ", " + _sex + ", " + _dob.format(new Date()) + ", " + _email  + ", " + _no + ", " + _address1 + ", " + _address2);
	}
	
	public String returnStringClient() { 
		String client = (_clientId + ", " + _name + ", " + _sex + ", " + _dob.format(new Date()) + ", " + _email  + ", " + _no + ", " + _address1 + ", " + _address2);
		return client;
	}

	public String get_clientId() {
		return _clientId;
	}

	public void set_clientId(String _clientId) {
		this._clientId = _clientId;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_sex() {
		return _sex;
	}

	public void set_sex(String _sex) {
		this._sex = _sex;
	}

	public SimpleDateFormat get_dob() {
		return _dob;
	}

	public void set_dob(SimpleDateFormat _dob) {
		this._dob = _dob;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_no() {
		return _no;
	}

	public void set_no(String _no) {
		this._no = _no;
	}

	public String get_address1() {
		return _address1;
	}

	public void set_address1(String _address1) {
		this._address1 = _address1;
	}

	public String get_address2() {
		return _address2;
	}

	public void set_address2(String _address2) {
		this._address2 = _address2;
	}
	
	
}

