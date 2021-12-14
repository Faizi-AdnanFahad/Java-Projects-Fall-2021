package model;

public class VaccineDistribution {
	
	private Vaccine vaccine;
	private int supplyNum; // Number of doses
	
	// Attribute for arrayOfRecords to be used in HealthRecord class;
	private String siteName;
	private String dateOfVaccination;
	
	public VaccineDistribution(Vaccine vaccine, int supplyNum) {
		this.vaccine = vaccine;
		this.supplyNum = supplyNum;
	}
	
	public VaccineDistribution(Vaccine vaccine, String siteName, String dateOfVaccination) {
		this.vaccine = vaccine;
		this.siteName = siteName;
		this.dateOfVaccination = dateOfVaccination;
	}
	
	/* Getters for attributes of arrayOfRecords in HealthRecord class*/
	public Vaccine getVaccine() {
		return this.vaccine;
	}
	
	public String getSiteName() {
		return this.siteName;
	}
	
	public String getDateOfVaccination() {
		return this.dateOfVaccination;
	}
	
	public int getSupplyNum() {
		return this.supplyNum;
	}
	
	public void addSupply(int supplyNum) {
		this.supplyNum += supplyNum;
	}
	
	public void substractSupply(int supplyNum) {
		this.supplyNum -= supplyNum;
	}
	
	
	public String toString() {
		return this.supplyNum + " doses of " + this.vaccine.getCodeNameVaccine() + " by " 
					+ this.vaccine.getManufacturerVaccine();
	}
}