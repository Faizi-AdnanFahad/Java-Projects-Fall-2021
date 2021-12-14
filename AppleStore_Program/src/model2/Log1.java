package model2;

public class Log1 {
	// Change (Log1 --> Log) when implementing on junit_testing

	private String versionNum;
	private String[] arrayOfFixes;
	private int nof;
	
	public Log1(String versionNum) {
		this.versionNum = versionNum;
		this.arrayOfFixes = new String[10];
	}
	
	public String getVersion() {
		return this.versionNum;
	}
	
	public int getNumberOfFixes() {
		return this.nof;
	}
	
	public String getFixes() {
		String result = "[";
		for (int i = 0; i < this.nof; i ++) {
			if (i == this.nof - 1) {
				result += this.arrayOfFixes[i];
			}
			else {
				result += this.arrayOfFixes[i] + ", ";
			}
		}
		result += "]";
		return result;
	}
	
	public void addFix(String fix) {
		this.arrayOfFixes[this.nof] = fix;
		this.nof ++;
	}
	
	public String toString() {
		return "Version " + this.versionNum + " contains " + this.nof + " fixes " + this.getFixes();
	}
}