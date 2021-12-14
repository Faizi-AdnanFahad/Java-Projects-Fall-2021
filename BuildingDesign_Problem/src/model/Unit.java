package model;

public class Unit {
	
	private String function;
	private int width;
	private int length;
	private String mode;  /* feet or meters */
	
	private final String FEET_MODE = "feet";
	private final String METERS_MODE = "meters";
	private final double FOOT_IN_METERS = 0.3048;
	
	public Unit(String function, int width, int length) {
		this.function = function;
		this.width = width;
		this.length = length;
		this.mode = this.FEET_MODE;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		// Reaching this line means both 'this' and 'obj' are pointing to objects of the same dynamic type.
		Unit other = (Unit) obj;
		return this.function.equals(other.function) 
				&& (this.getAreaInSquareFeet() == other.getAreaInSquareFeet());
	}
	
	public String toString() {
		String result = "";
		if (this.mode.equals(this.FEET_MODE)) {
			result = 
					String.format("A unit of %d square feet (%d' wide and %d' long) functioning as %s", this.getAreaInSquareFeet(), 
							this.width, this.length, this.function);
		}
		else if (this.mode.equals(this.METERS_MODE)) {
			result = 
					String.format("A unit of %.2f square meters (%.2f m wide and %.2f m long) functioning as Master Room", 
							this.getAreaInSquareMeters(), this.width * this.FOOT_IN_METERS, this.length * this.FOOT_IN_METERS, this.function);
		}
		return result;
	}
	
	public void toogleMeasurement() {
		if (this.mode.equals(this.FEET_MODE)) {
			this.mode = this.METERS_MODE;
		}
		else { /* mode is "meters" */
			this.mode = this.FEET_MODE;
		}
	}
	
	public int getAreaInSquareFeet() {
		return this.width * this.length;
	}
	
	public double getAreaInSquareMeters() {
		return (this.width * this.FOOT_IN_METERS) * (this.length * this.FOOT_IN_METERS);
	}
	
	// Getters
	
	public String getFunction() {
		return this.function;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getLength() {
		return this.length;
	}
}