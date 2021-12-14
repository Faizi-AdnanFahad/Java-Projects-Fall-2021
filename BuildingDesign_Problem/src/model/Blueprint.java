package model;

public class Blueprint {
	
	private Floor[] floors;
	private int nof; // Number of floors
	private int maxNumFloors;
	
	public Blueprint(int maxNumFloors) {
		this.maxNumFloors = maxNumFloors;
		this.floors = new Floor[maxNumFloors];
		this.nof = 0;
	}
	
	public Blueprint(Blueprint other) {
		this(other.maxNumFloors);
		for (int i = 0; i < other.nof; i ++ ) {
			this.floors[i] = new Floor(other.floors[i]);
		}
		this.nof = other.nof;
	}
	
	public void addFloorPlan(Floor f) {
		this.floors[this.nof] = f;
		this.nof ++;
	}
	
	public Floor[] getFloors() {
		Floor[] result = new Floor[this.nof];
		for (int i = 0; i < this.nof; i ++ ) {
			result[i] = new Floor(this.floors[i]);
		}
		return result;
	}
	
	public String toString() {
		double percentage = (this.nof / (double) this.maxNumFloors) * 100;
		String percentageS = String.format("%.1f", percentage);
		
		return String.format("%s percents of building blueprint completed (%d out of %d floors)", 
				percentageS, this.nof, this.maxNumFloors);
	}
}