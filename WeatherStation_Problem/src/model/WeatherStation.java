package model;

public class WeatherStation {

	private String name;

	private WeatherApp[] arrayOfApps;
	private int noA;

	public WeatherStation(String name, int maxApp) {
		this.name = name;
		this.arrayOfApps = new WeatherApp[maxApp];
		this.noA = 0;
	}

	public SensorApp[] getSensors() {
		SensorApp[] temp = new SensorApp[this.noA];

		int counter = 0;
		for (int i = 0; i < this.noA; i ++) {
			if (this.arrayOfApps[i] instanceof SensorApp) {
				temp[counter] = (SensorApp) this.arrayOfApps[i];
				counter ++;
			}
		}

		SensorApp[] result = new SensorApp[counter];

		for (int i = 0; i < counter; i ++) {
			result[i] = temp[i];
		}

		return result;
	}

	public void connect(WeatherApp app) {
		this.arrayOfApps[this.noA] = app;
		this.noA ++;

		app.addWorkStation(this);
	}

	public String toString() {
		String result = "";

		String listOfApp = "<";
		for (int i = 0; i < this.noA; i ++) {
			if (this.arrayOfApps[i] instanceof SensorApp) {
				listOfApp += "Weather Sensor App " + this.arrayOfApps[i].getAppName();
			}
			else if (this.arrayOfApps[i] instanceof ForecastApp) {
				listOfApp += "Weather Forecast App " + this.arrayOfApps[i].getAppName();
			}

			if (i < this.noA - 1) {
				listOfApp += ", ";
			}
		}
		listOfApp += ">";

		if (this.noA == 0) {
			result = this.name + " has no connected apps.";
		}
		else {
			result = this.name + " is connected by " + this.noA + " apps: " + listOfApp + ".";
		}

		return result;
	}

	public WeatherApp[] getArrayOfApps() {
		return this.arrayOfApps;
	}

	public int getNoA() {
		return this.noA;
	}

	public String getWorkStationName() {
		return this.name;
	}
}