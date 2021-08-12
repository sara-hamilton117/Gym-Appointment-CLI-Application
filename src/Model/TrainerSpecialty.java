package Model;

public class TrainerSpecialty {
	private String _ptId;
	private String _specialtyId;
	
	public TrainerSpecialty(String _ptId, String _specialtyId) {
		super();
		this._ptId = _ptId;
		this._specialtyId = _specialtyId;
	}

	public void printTS() {
		System.out.println(_ptId + ", " + _specialtyId);
	}
	
	public String get_ptId() {
		return _ptId;
	}

	public void set_ptId(String _ptId) {
		this._ptId = _ptId;
	}

	public String get_specialtyId() {
		return _specialtyId;
	}

	public void set_specialtyId(String _specialtyId) {
		this._specialtyId = _specialtyId;
	}
	
	
}

