package model2;

public class AppStore1 {
	// Change (AppStore --> AppStore) when implementing on junit_testing

	private String branchName;
	private App1[] arrayOfApps;
	private int noa;
	
	public AppStore1(String branchName, int maxNumOfApps) {
		this.branchName = branchName;
		this.arrayOfApps = new App1[maxNumOfApps];	
	}
	
	public App1[]	getArrayOfApps() {
		return this.arrayOfApps;
	}
	
	public int getNOA() {
		return this.noa;
	}
	
	public String getBranch() {
		return this.branchName;
	}
	
	public App1 getApp(String appName) {
		App1 app = null;
		for (int i = 0; i < this.noa; i ++) {
			if (this.arrayOfApps[i].getName().equals(appName)) {
				app = this.arrayOfApps[i];
			}
		}
		return app;
	}
	
	public String[] getStableApps(int numUpdateMax) {
		String[] result = new String[this.noa];
		int count = 0;
		
		for (int i = 0; i < this.noa; i ++) {
			App1 app = this.arrayOfApps[i];
			if (this.arrayOfApps[i].getUpdateHistory().length >= numUpdateMax) {
				result[count] = app.getName() 
						+ " (" + app.getUpdateHistory().length + " versions; "
								+ "Current Version: " + app.getWhatIsNew() + ")";
				count ++;
			}
		}
		
		String[] finalResult = new String[count];
		for (int i = 0; i < count; i ++) {
				finalResult[i] = result[i];
		}
		return finalResult;
		
	}
	
	public void addApp(App1 newApp) {
		this.arrayOfApps[this.noa] = newApp;
		this.noa ++;
	}
}