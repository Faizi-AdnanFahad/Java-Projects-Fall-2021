package model;

public class Assessment {
	
	private String assessmentName;
	private double weight;
	private int mark;
	
	private String status;
	
	public Assessment(String assessmentName, double weight ) {
		this.assessmentName = assessmentName;
		this.weight = weight;
		this.mark = 0;
		
		this.status = "Assessment created: "
				+ this.assessmentName + " accounts for "
				+ String.format("%.3f", this.weight * 100) + " percents of the course.";
	}
	
	public String getAssessmentName() {
		return this.assessmentName;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public int getMarks() {
		return this.mark;
	}
	
	public void setMarks(int mark) {
		if (mark <= 100) {
			this.status = "Marks of assessment " + this.assessmentName
					+ " (accounting for " + String.format("%.3f", this.weight * 100) 
					+ " percents of the course) is changed from " + this.mark + " to " + mark + ".";
			this.mark = mark;
		}
	}
	
	public void setWeight(double weight) {
		this.status = "Weight of assessment " + this.assessmentName
				+ " (with marks " + this.mark 
				+ ") is changed from " + String.format("%.3f", this.weight * 100) 
				+ " percents to " + String.format("%.3f", weight * 100) + " percents.";
		this.weight = weight;
	}
	
	public String toString() {
		return this.status;
	}
}