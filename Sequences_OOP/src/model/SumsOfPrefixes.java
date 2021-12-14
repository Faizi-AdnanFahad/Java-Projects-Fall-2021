package model;

public class SumsOfPrefixes extends SeqOperation {
	
	protected int[] sequence;
	
	public SumsOfPrefixes(int[] sequence) {
		this.sequence = sequence;
	}
	
	public String toString() {		
		int[] sumPrefix = SumsOfPrefixes.calculateSumPrefix(this.sequence);
		
		String sumPrefixInString = super.arrayObjectToString(sumPrefix);
		return "Sums of prefixes of " 
			+ super.arrayObjectToString(this.sequence) + " is: " 
			+ sumPrefixInString;
	}
	
	public static int[] calculateSumPrefix(int[] seq) {
		int[] result = new int[seq.length + 1];
		
		for (int i = 0; i < result.length; i ++) {
			int sum = 0;
			for (int m = 0; m < i; m ++) {
				sum += seq[m];
			}
			result[i] = sum;
		}
		return result;
	}
	
}