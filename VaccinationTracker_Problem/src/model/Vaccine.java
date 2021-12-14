package model;

public class Vaccine {
	
	private String codeNameVaccine;
	private String typeVaccine;
	private String manufacturerVaccine;
	
	private String[] RECOGNIZED_VACCINES = {"mRNA-1273", "BNT162b2", "Ad26.COV2.S", "AZD1222"};
	private boolean recognizedVaccine;
	
	public Vaccine(String codeNameVaccine, String typeVaccine, String manufacturerVaccine) {
		// Checking to see if the vaccine is amongst one of the approved ones in Canada.
		this.recognizedVaccine = false;
		for (int i = 0; !this.recognizedVaccine && i < this.RECOGNIZED_VACCINES.length; i ++) {
			this.recognizedVaccine = this.RECOGNIZED_VACCINES[i].equals(codeNameVaccine);
		}
		// Initializing attributes
		this.codeNameVaccine = codeNameVaccine;
		this.typeVaccine = typeVaccine;
		this.manufacturerVaccine = manufacturerVaccine;
	}
	
	/* Additional Getters retrieving the values of private attributes in this class. */
	public String getCodeNameVaccine() {
		return this.codeNameVaccine;
	}

	public String getTypeVaccine() {
		return this.typeVaccine;
	}

	public String getManufacturerVaccine() {
		return this.manufacturerVaccine;
	}
	
	public boolean isVaccineRecognized() {
		return this.recognizedVaccine;
	}

	
	public String toString() {
		String result = "";
		if (this.recognizedVaccine) {
			result = "Recognized vaccine: " + this.codeNameVaccine 
			+ " (" + this.typeVaccine 
			+ "; " + this.manufacturerVaccine + ")";
		}
		else {
			result = "Unrecognized vaccine: " + this.codeNameVaccine 
					+ " (" + this.typeVaccine 
					+ "; " + this.manufacturerVaccine + ")";
		}
		return result;
	}
}