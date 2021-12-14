package model;

public class WeatherApp {

	protected String name;

	protected WeatherStation[] arrayOfWeatherStation;
	protected int noW;

	public WeatherApp(String name, int maxWeatherStation) {
		this.name = name;
		this.arrayOfWeatherStation = new WeatherStation[maxWeatherStation];
		this.noW = 0;
	}

	protected void addWorkStation(WeatherStation w) {
		this.arrayOfWeatherStation[this.noW] = w;
		this.noW ++;
	}

	public String getAppName() {
		return this.name;
	}
}