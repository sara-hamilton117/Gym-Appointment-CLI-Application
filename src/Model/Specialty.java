package Model;

public class Specialty {
	private String _specialtyId;
	private String _name;
	private Integer _duration;
	
	public Specialty(String _specialtyId, String _name, Integer _duration) {
		super();
		this._specialtyId = _specialtyId;
		this._name = _name;
		this._duration = _duration;
	}
	
	public void printSpecialty() {
		System.out.println(_specialtyId + ", " + _name + ", " + _duration.toString());
	}

	public String get_specialtyId() {
		return _specialtyId;
	}

	public void set_specialtyId(String _specialtyId) {
		this._specialtyId = _specialtyId;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public Integer get_duration() {
		return _duration;
	}

	public void set_duration(Integer _duration) {
		this._duration = _duration;
	}
	
	
}

