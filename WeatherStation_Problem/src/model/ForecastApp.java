package model;

public class ForecastApp extends WeatherApp {
	
	private int[] arrayMaxOfTemp;
	private int[] arrayOfTotalTemp;
	private int[] arrayOfAirPressure;
	private boolean[] arrayOfRainTracker;
	private int[] tempCounter;

	public ForecastApp(String name, int maxWeatherStation) {
		super(name, maxWeatherStation);
		this.arrayOfAirPressure = new int[maxWeatherStation];
		this.arrayOfTotalTemp = new int[maxWeatherStation];
		this.arrayMaxOfTemp = new int[maxWeatherStation];
		this.arrayOfRainTracker = new boolean[maxWeatherStation];
		this.tempCounter = new int[maxWeatherStation];
	}

	public String toString() {
		String result = "";

		String listOfWorkStation = "<";
		for (int i = 0; i < this.noW; i ++) {
			if (this.tempCounter[i] > 0) {
				if (this.arrayOfRainTracker[i]) {
					listOfWorkStation += this.arrayOfWeatherStation[i].getWorkStationName() 
									  + " {max temperature: " + this.arrayMaxOfTemp[i] 
									  + ", avg temperature: " 
								      + String.format("%.1f", (double) this.arrayOfTotalTemp[i] / this.tempCounter[i]) 
									  + ", likely to rain}";
				}
				else {
					listOfWorkStation += this.arrayOfWeatherStation[i].getWorkStationName() 
									  + " {max temperature: " + this.arrayMaxOfTemp[i] 
									  + ", avg temperature: " 
									  + String.format("%.1f", (double) this.arrayOfTotalTemp[i] / this.tempCounter[i]) 
									  + ", unlikely to rain}";
				}
			}
			else {
				listOfWorkStation += this.arrayOfWeatherStation[i].getWorkStationName();
			}

			if (i < this.noW - 1) {
				listOfWorkStation += ", ";
			}
		}
		listOfWorkStation += ">";

		if (this.noW == 0) {
			result = "Weather Forecast App " + this.name + " is connected to no stations.";
		}
		else {
			result = "Weather Forecast App " + this.name + " is connected to " + this.noW
					+ " stations: " + listOfWorkStation + ".";
		}
		return result;
	}

	public void addStat(String stationName, int temprature, int airPressure) {
		int index = -1;
		for (int i = 0; i < this.noW; i ++) {
			if (this.arrayOfWeatherStation[i].getWorkStationName().equals(stationName)) {
				index = i;
			}
		}

		if (index != -1) {
			if (this.arrayMaxOfTemp[index] < temprature) {
				this.arrayMaxOfTemp[index] = temprature;
			}

			if (airPressure < this.arrayOfAirPressure[index]) {
				this.arrayOfRainTracker[index] = true;
			}
			else {
				this.arrayOfRainTracker[index] = false;
			}
			
			this.arrayOfTotalTemp[index] += temprature;
			this.tempCounter[index] ++; // For calculating the average
			this.arrayOfAirPressure[index] = airPressure;
		}
	}
}