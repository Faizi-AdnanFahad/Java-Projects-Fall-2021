package model;

public class Projection extends BinarySeqOperation {

	public Projection(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
	}


	public String toString() {
		String results = "";
		results = "Projecting " + super.arrayObjectToString(seq1) 
		+ " to " + super.arrayObjectToString(seq2) + " results in: " 
		+ super.arrayObjectToString(Projection.calculateProjection(seq1, seq2));
		return results;
	}

	public static int[] calculateProjection(int[] seq1, int[] seq2) {
		int[] tempProjectingArray = new int[seq2.length]; // the general number storer for the case where all numbers are included in the first array. 
		int counter = 0;
		for (int i = 0; i < seq2.length; i ++) {
			for (int m = 0; m < seq1.length; m ++) {
				if (seq2[i] == seq1[m]) {
					tempProjectingArray[counter] = seq2[i];
					counter ++;
					break;
				}
			}
		}

		int[] projectingArray = new int[counter];

		for (int i = 0; i < counter; i ++) {
			projectingArray[i] = tempProjectingArray[i];
		}

		return projectingArray;
	}
}