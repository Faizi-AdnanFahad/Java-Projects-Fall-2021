package model;

public class HealthRecord {
	
	private String patientName;
	private String appointmentStatus;
	
	private VaccineDistribution[] arrayOfRecords;
	private int noR;
	
	
	public HealthRecord(String patientName, int numOfDosesLimit) {
		this.patientName = patientName;
		this.arrayOfRecords = new VaccineDistribution[numOfDosesLimit];
	}
	
	public String getPatientName() {
		return this.patientName;
	}
	
	public String getVaccinationReceipt() {
		String result = "";
		String seq = "[";
		if (this.noR == 0) {
			result = this.patientName + " has not yet received any doses.";
		}
		else {
			for (int i = 0; i < this.noR; i ++) {
				VaccineDistribution record = this.arrayOfRecords[i];
				if (i == this.noR - 1) {
					seq += this.seqGenerator(record);
					}
				else {
					seq += this.seqGenerator(record) + "; ";
				}
			}
			seq += "]";
			result = "Number of doses " + this.patientName + " has received: " + this.noR + " " + seq;
		}
		return result;
	}
	
	public String getAppointmentStatus() {
		String result = "";
		if (this.appointmentStatus == null) {
			result = "No vaccination appointment for " + this.patientName + " yet";
		}
		else {
			result = this.appointmentStatus;
		}
		return result;
	}
	
	public void setAppointmentStatus(String newStatus) {
		this.appointmentStatus = newStatus;
	}
	
	
	public void addRecord(Vaccine vaccine, String nameOfTheSite, String vaccinationDate) {
		VaccineDistribution vd = new VaccineDistribution(vaccine, nameOfTheSite, vaccinationDate);
		this.arrayOfRecords[this.noR] = vd;
		this.noR ++;
	}
	
	// Helper Methods
	public String seqGenerator(VaccineDistribution record) {
		return record.getVaccine().toString() + " in " + record.getSiteName() + " on " + record.getDateOfVaccination();
	}
}