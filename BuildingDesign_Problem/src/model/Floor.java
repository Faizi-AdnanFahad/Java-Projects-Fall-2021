package model;

public class Floor {
	
	private Unit[] units;
	private int nou; // Number of units
	private final int MAX_NUM_OF_UNITS = 20;
	
	private int maxCapacity; // in square feet
	
	public Floor(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.units = new Unit[this.MAX_NUM_OF_UNITS];
		this.nou = 0;
	}
	
	// Copy Constructor
	public Floor(Floor other) {
		this(other.maxCapacity);
		for (int i = 0; i < other.nou; i ++) {
			// For this lab, composition at units class is not required. However, for complete composition it is a better idea to avoid aliasing at units level for each floor object.
			this.units[i] = other.units[i];
		}
		this.nou = other.nou;
	}
	
	public void addUnit(String function, int width, int length) throws InsufficientFloorSpaceException {
		int currentSpace = width * length;
		if (this.maxCapacity - this.getUtilizedSpace() < currentSpace) {
			throw new InsufficientFloorSpaceException("Error! ...");
		}
		else {
			this.units[this.nou] = new Unit(function, width, length);
			this.nou ++;
		}
		
	}
	
	public String toString() {
		//"Floor's utilized space is 144 sq ft (356 sq ft remaining): [Master Bedroom: 144 sq ft (18' by 8')]"
		
		String list = "[";
		for (int i = 0; i < this.nou; i ++) {
			Unit u = this.units[i];
			list += String.format("%s: %d sq ft (%d' by %d')", 
					u.getFunction(), u.getAreaInSquareFeet(), u.getWidth(), u.getLength());
			if (i < this.nou - 1) {
				list += ", ";
			}
		}
		list += "]";
		
		int utalizedSpace = this.getUtilizedSpace();
		String result = String.format("Floor's utilized space is %d sq ft (%d sq ft remaining): %s", 
				utalizedSpace, this.maxCapacity - utalizedSpace, list);
		return result ;
	}
	
	public int getUtilizedSpace() {
		// Used to calculate the total area used so far in the array
		int total = 0;
		
		for (int i = 0; i < this.nou; i ++) {
			total += this.units[i].getAreaInSquareFeet();
		}
		return total;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		Floor other = (Floor) obj;
		boolean equal = this.maxCapacity == other.maxCapacity;
		if (equal) {
			for (int i = 0; equal && i < this.nou; i ++) {
				Unit u = this.units[i];
				equal = this.numberOfEqualUnits(u) == other.numberOfEqualUnits(u);
			}
		}
		return equal;
	}
	
	public int numberOfEqualUnits(Unit u) { /* return value should larger >= 1*/
		int result = 0; 
		
		for (int i = 0; i < this.nou; i ++) {
			if (this.units[i].equals(u)) {
				result ++;
			}
		}
		return result;
	}
}