package model2;

public class Account1 {
	// Change (Account1 --> Account) when implementing on junit_testing
	private String name;
	private String status;
	private AppStore1 appStore;
	
	private App1[] arrayOfAppsObjects;
	private String[] arrayOfAppSting;

	private int nodS;
	private int nodO;
	
	public Account1(String name, AppStore1 appStore) {
		this.name = name;
		this.appStore = appStore;
		this.arrayOfAppsObjects = new App1[50];
		this.arrayOfAppSting = new String[50];

		this.status = "An account linked to the " 
		+ appStore.getBranch() +" store is created for "
				+ this.name + ".";
	}
	
	public String[] getNamesOfDownloadedApps() {
		String[] result = new String[this.nodS];
		
		for (int i = 0; i < this.nodS; i ++) {
			if (this.arrayOfAppSting[i] != null ) {
				result[i] = this.arrayOfAppSting[i];
			}
		}
		
		return result;
	}
	
	public App1[] getObjectsOfDownloadedApps() {
		App1[] result = new App1[this.nodO];
		for (int i = 0; i < this.nodS; i ++) {
			result[i] = this.arrayOfAppsObjects[i];
		}
		return result;
	}
	
	public void uninstall(String appName) {
		boolean found = false;
		for (int i = 0; i < this.nodS; i ++) {
			if (this.arrayOfAppSting[i].equals(appName)) {
				this.arrayOfAppsObjects[i] = this.arrayOfAppsObjects[i + 1];
				this.arrayOfAppsObjects[i + 1] = null;
				this.nodO -= 1;
				
				this.arrayOfAppSting[i] = this.arrayOfAppSting[i + 1];
				this.arrayOfAppSting[i + 1] = null;
				this.nodS -= 1;
				found = true;
			}
		}
		
		if (!found) {
			this.status = "Error: " + appName + " has not been downloaded for " + this.name + ".";
		}
		else {
			this.status = appName + " is successfully uninstalled for " + this.name + ".";
		}
	}
	
	public void submitRating(String appName, int rating) {
		boolean found = false;
		for (int i = 0; i < this.nodS; i ++) {
			if (this.arrayOfAppSting[i].equals(appName)) {
				this.arrayOfAppsObjects[i].submitRating(rating);
				found = true;
			}
		}
		
		if (!found) {
			this.status = "Error: " + appName + " is not a downloaded app for " + this.name + ".";
		}
		else {
			this.status = "Rating score " + rating + " of " + this.name 
					+ " is successfully submitted for " + appName + ".";
		}
	}
	
	public void switchStore(AppStore1 appStore) {
		this.appStore = appStore;
		this.status = "Account for " 
				+ this.name + " is now linked to the " 
				+ appStore.getBranch() + " store.";
	}
	
	public void download(String appName) {
		boolean errorFound = false;
		for (int i = 0; i < this.nodS; i ++) {
			if (this.arrayOfAppSting[i].equals(appName)) {
				this.status = "Error: " + appName + " has already been downloaded for " + this.name + ".";
				errorFound = true;
			}
		}
		
		if (!errorFound) {
			this.arrayOfAppSting[this.nodS] = appName;
			this.nodS ++;
			
			for (int i = 0; i < this.appStore.getNOA(); i ++) {
				if (this.appStore.getArrayOfApps()[i].getName().equals(appName) ) {
					this.arrayOfAppsObjects[i] = this.appStore.getArrayOfApps()[i];
					this.nodO ++;
				}
			}
			this.status = appName + " is successfully downloaded for " + this.name + ".";
		}
	}
	
	public String toString() {
		return this.status;
	}
}