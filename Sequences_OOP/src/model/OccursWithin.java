package model;

public class OccursWithin extends BinarySeqOperation {
	
	public OccursWithin(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
	}
	
	public String toString() {
		String result = "";
		
		boolean matched = OccursWithin.doesOccur(seq1, seq2);
		
		if (matched) {
			result = super.arrayObjectToString(seq1) + 
					" occurs within " + super.arrayObjectToString(seq2);
		}
		else {
			result = super.arrayObjectToString(seq1) + 
					" does not occur within " + super.arrayObjectToString(seq2);
		}
		
		return result;
	}
	
	public static boolean doesOccur(int[] seq1, int[] seq2) {
		boolean matched = false;

		int counter = 0;
		int m = 0;
		
		if (seq1.length < seq2.length) {
			for (int i = 0; i < seq1.length; i ++) {
				for (; m < seq2.length; m ++) {
					if (seq1[i] == seq2[m]) {
						counter ++;
						break;
					}
				}
				if (counter > 0) {
					if ((i != seq1.length - 1) && (m != seq2.length - 1)) {
						if (seq1[i + 1] != seq2[m + 1]) {
							break;
						}
					}
				}
				matched = counter == seq1.length;
			}
			if (seq1.length == 0 && seq2.length != 0) {
				matched = true;
			}
		}
		return matched;
	}
}
