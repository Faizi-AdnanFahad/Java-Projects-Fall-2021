package model2;

public class App1 {
	// Change (App1 --> App) when implementing on junit_testing
	private String appName;
	private Log1[] arrayOfUpdateLogs;
	private int noa;
	
	private int[] arrayOfRatings;
	private int nor;
	
	private int rate1;
	private int rate2;
	private int rate3;
	private int rate4;
	private int rate5;
	private double average;
	
	public App1(String appName, int maxRatingAllowed) {
		this.appName = appName;
		this.arrayOfUpdateLogs = new Log1[20];
		
		this.arrayOfRatings = new int[maxRatingAllowed];
	}
	
	public String getName() {
		return this.appName;
	}
	
	public String getWhatIsNew() {
		String result = "";
		if (this.noa == 0)  {
			result = "n/a";
		}
		else {
			result = this.arrayOfUpdateLogs[this.noa - 1].toString();
		}
		return result;
	}
	
	public Log1[] getUpdateHistory() {
		Log1[] logs = new Log1[this.noa];
		for (int i = 0; i < this.noa; i ++) {
			logs[i] = this.arrayOfUpdateLogs[i];
		}
		return logs;
	}
	
	public Log1 getVersionInfo(String versionNum) {
		Log1 l = null;
		for (int i = 0; i < this.noa; i ++) {
			if (this.arrayOfUpdateLogs[i].getVersion().equals(versionNum)) {
				l = this.arrayOfUpdateLogs[i];
			}
		}		
		return l;
	}
	
	public String getRatingReport() {
		String result = "";
		
		double sum = 0;
		int howMany = 0;
		for (int i = 0; i < this.nor; i ++) {
			sum += this.arrayOfRatings[i];
			howMany ++;
		}
		
		if (howMany != 0) {
			this.average = sum/howMany;
		}
		
		if (this.nor == 0) {
			result = "No ratings submitted so far!";
		}
		else {
			result = "Average of " + this.nor + " ratings: " + String.format("%.1f", this.average) + " "
					+ "(Score 5: " + this.rate5 +", "
					+ "Score 4: " + this.rate4 + ", "
					+ "Score 3: " + this.rate3 +", "
					+ "Score 2: " + this.rate2 + ", "
					+ "Score 1: " + this.rate1 +")";
		}
		return result;
	}
	
	public void releaseUpdate(String updateNum) {
		Log1 l = new Log1(updateNum);
		this.arrayOfUpdateLogs[this.noa] = l;
		this.noa ++;
		
	}
	
	public void submitRating(int rate) {
		if (rate == 1) {
			this.rate1 ++;
		}
		else if (rate == 2) {
			this.rate2 ++;
		}
		else if (rate == 3) {
			this.rate3 ++;
		}
		else if (rate == 4) {
			this.rate4 ++;
		}
		else if (rate == 5) {
			this.rate5 ++;
		}
		this.arrayOfRatings[this.nor] = rate;
		this.nor ++;
	}
	
	public String toString() {
//		return this.appName + " (Current Version: " + this.getWhatIsNew() + "; Average Rating: " + String.format("%.1f", this.average) + ")";
		String averageString = "";
		if (this.average == 0) {
			averageString = "n/a";
		}
		else {
			averageString = String.format("%.1f", this.average);
		}
		return this.appName + " (Current Version: " + this.getWhatIsNew() + "; Average Rating: " + averageString + ")";
	}
}