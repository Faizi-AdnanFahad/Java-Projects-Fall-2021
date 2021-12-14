package model;

public class StudentRecord {
	
	private String course;
	private Assessment[] arrayOfAssessment;
	private int noA;
	
	public StudentRecord(String course) {
		this.course = course;
		this.arrayOfAssessment = new Assessment[10];
	}
	
	public String getAssessmentReport() {
		String result = "";
		String seq = "[";
		
		for (int i = 0; i < this.noA; i ++) {
			Assessment ass = this.arrayOfAssessment[i];
			if (i == this.noA - 1) {
				seq += ass.getAssessmentName() + " (weight: " + String.format("%.3f", ass.getWeight() * 100) + 
						" percents; marks: " + ass.getMarks() + ")";
			}
			else {
				seq += ass.getAssessmentName() + " (weight: " + String.format("%.3f", ass.getWeight() * 100) + 
						" percents; marks: " + ass.getMarks() + "), ";
			}
		}
		seq += "]";
		
		result = "Number of assessments in " + this.course + ": " + this.noA + " " + seq;

		return result;
	}
	
	public double getCompletionRate() {
		double sum = 0.0;
		for (int i = 0; i < this.noA; i ++) {
			sum += this.arrayOfAssessment[i].getWeight();
		}
		return sum;
	}
	
	public double getRawMarks() {
		double rawMark = 0.0;
		for (int i = 0; i < this.noA; i ++) {
			rawMark += this.arrayOfAssessment[i].getWeight() * this.arrayOfAssessment[i].getMarks();
		}
		return rawMark;
	} 
	
	public String getCourse() {
		return  this.course;
	}
	

	public void addAssessment(String assessmentName, double weight, int mark) {
		if (this.noA < 10) {
			Assessment assesment = new Assessment(assessmentName, weight);
			this.arrayOfAssessment[this.noA] = assesment;
			this.noA ++;
			assesment.setMarks(mark);
		}
	}
	
	public void addAssessment(Assessment assig) {
		if (this.noA < 10) {
			this.arrayOfAssessment[this.noA] = assig;
			this.noA ++;
		}
	}
	
	public void changeMarksOf(String assessmentName, int mark) {
		boolean stay = true;
		for (int i = 0; stay && i < this.noA; i ++) {
			if (this.arrayOfAssessment[i].getAssessmentName().equals(assessmentName)) {
				this.arrayOfAssessment[i].setMarks(mark);
				stay = false;
			}
		}
	}
	
	public void removeAssessment(String assessmentName) {
		for (int i = 0; i < this.noA; i ++) {
			if (this.arrayOfAssessment[i].getAssessmentName().equals(assessmentName)) {
				this.arrayOfAssessment[i] = this.arrayOfAssessment[i + 1];
				this.arrayOfAssessment[i + 1]  = null;
				this.noA -= 1;
			}
		}
	}
}