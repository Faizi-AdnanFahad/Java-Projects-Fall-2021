package model;

public class VaccinationSite {
	
	private final int MAX_NUM_VAC_IN_A_SITE = 4;
	private final int MAX_NUM_OF_APPOINTS = 200;	
	
	private String nameOfTheSite;
	private int limitNumOfDosesFromAddedDistribution;
	
	private VaccineDistribution[] arrayOfVaccinationDistribution;
	private int numDistribution;
	private int accumulatedVaccineNumber;
	
	private HealthRecord[] arrayOfVaccinationAppointments;
	private int numAppointment;	
	private boolean appointmentExist;
	
	
	public VaccinationSite(String nameOfTheSite, int limitNumOfDosesFromAddedDistribution) {
		this.nameOfTheSite = nameOfTheSite;
		this.limitNumOfDosesFromAddedDistribution = limitNumOfDosesFromAddedDistribution;
		this.arrayOfVaccinationDistribution = new VaccineDistribution[MAX_NUM_VAC_IN_A_SITE];
		this.arrayOfVaccinationAppointments = new HealthRecord[MAX_NUM_OF_APPOINTS];
	}
	
	public int getNumberOfAvailableDoses() {
		return this.accumulatedVaccineNumber;
	}
	
	public boolean doesAppointmentExist() {
		return this.appointmentExist;
	}
	
	public int getNumberOfAvailableDoses(String vaccineCodeName) {
		int result = 0;
		for (int i = 0; i < this.numDistribution; i ++) {
			VaccineDistribution vd = this.arrayOfVaccinationDistribution[i];
			if (vd.getVaccine().getCodeNameVaccine().equals(vaccineCodeName)) {
				result = vd.getSupplyNum();
			}
		}
		return result;
	}
	
	public void addDistribution(Vaccine vaccine, int supplyNum) throws UnrecognizedVaccineCodeNameException, TooMuchDistributionException {
		if (vaccine.isVaccineRecognized() && (supplyNum <= this.limitNumOfDosesFromAddedDistribution - this.accumulatedVaccineNumber)) {
			// Checking to see if the vaccine is already included in the array before adding it as a new distribution
			boolean found = false;
			for (int i = 0; !found && i < this.numDistribution; i ++) {
				if (this.arrayOfVaccinationDistribution[i].getVaccine().getCodeNameVaccine().equals(vaccine.getCodeNameVaccine())) {
					this.arrayOfVaccinationDistribution[i].addSupply(supplyNum);
					found = true;
				}
			}
			if (!found) {
				this.arrayOfVaccinationDistribution[this.numDistribution] = new VaccineDistribution(vaccine, supplyNum);
				this.numDistribution ++;
			}
			// Done in both cases
			this.accumulatedVaccineNumber += supplyNum;
		}
		else if (!vaccine.isVaccineRecognized()) {
			throw new UnrecognizedVaccineCodeNameException("Unexpected exception thrown");
		}
		else if ((supplyNum > this.limitNumOfDosesFromAddedDistribution - this.accumulatedVaccineNumber)) {
			throw new TooMuchDistributionException("Unexpected exception thrown");
		}
	}
	
	public void bookAppointment(HealthRecord healthRecord) throws InsufficientVaccineDosesException {
		if (this.numAppointment < this.getNumberOfAvailableDoses()) {
			this.arrayOfVaccinationAppointments[this.numAppointment] = healthRecord;
			String status = "Last vaccination appointment for " + healthRecord.getPatientName() + " with " + this.nameOfTheSite + " succeeded";
			healthRecord.setAppointmentStatus(status);
			this.numAppointment ++;
			this.appointmentExist = true;
		}
		else {
			String status = "Last vaccination appointment for " + healthRecord.getPatientName() + " with " + this.nameOfTheSite + " failed";
			healthRecord.setAppointmentStatus(status);
			throw new InsufficientVaccineDosesException("Unexpected exception thrown");
		}
	}
	
	public void administer(String dateOfVaccination) {
		int indexOfDistSupply = 0;
		for (int i = 0; i < this.numAppointment; i ++) {
			// Administering doses to patients with appointments based on the order doses were distributed
			if (this.arrayOfVaccinationDistribution[indexOfDistSupply].getSupplyNum() != 0) {
				this.arrayOfVaccinationDistribution[indexOfDistSupply].substractSupply(1);
				this.arrayOfVaccinationAppointments[i].addRecord(this.arrayOfVaccinationDistribution[indexOfDistSupply].getVaccine(), this.nameOfTheSite, dateOfVaccination);
			}
			else {
				indexOfDistSupply ++;
				this.arrayOfVaccinationDistribution[indexOfDistSupply].substractSupply(1);
				this.arrayOfVaccinationAppointments[i].addRecord(this.arrayOfVaccinationDistribution[indexOfDistSupply].getVaccine(), this.nameOfTheSite, dateOfVaccination);
			}
			
			// when the vaccine is distributed, make the index of that null.
			this.arrayOfVaccinationAppointments[i] = null;
			// Decrement the supply by 1.
			this.accumulatedVaccineNumber --;
		}
		this.numAppointment = 0;
	}
	
	public String toString() {
		String result = "";
		String seq = "<";
		for (int i = 0; i < this.numDistribution; i ++) {
			if (i < this.numDistribution - 1) {
				seq += this.arrayOfVaccinationDistribution[i].getSupplyNum() + " doses of " + this.arrayOfVaccinationDistribution[i].getVaccine().getManufacturerVaccine() + ", ";
			}
			else {
				seq += this.arrayOfVaccinationDistribution[i].getSupplyNum() + " doses of " + this.arrayOfVaccinationDistribution[i].getVaccine().getManufacturerVaccine();
			}
		}
		seq += ">";
		result = this.nameOfTheSite + " has " + this.accumulatedVaccineNumber + " available doses: " + seq;

		return result;
	}
}