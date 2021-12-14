package model;

public class ConcatAll extends SeqEvaluator {
	
	private int[][] arrayOfOperation;
	private int noO; // number of operations
	
	private int numIncompatableOperation;
	
	public ConcatAll(int maxSeqOperation) {
		this.arrayOfOperation = new int[maxSeqOperation][];
	}

	@Override
	public void addOperation(String operation, int[] seq1, int[] seq2) throws IllegalOperationException {
		if (operation.equals("op:projection")) {
			this.arrayOfOperation[this.noO] = Projection.calculateProjection(seq1, seq2);
			this.noO ++;
		}
		else if (operation.equals("op:sumsOfPrefixes")) {
			this.arrayOfOperation[this.noO] = SumsOfPrefixes.calculateSumPrefix(seq1);
			this.noO ++;
		}
		else if (operation.equals("op:occursWithin")) {
			this.numIncompatableOperation ++;
		}
		else {
			throw new IllegalOperationException("Operation is not recognized!");
		}
	}
	
	public String toString() {
		String result = "";
		
		String seq = "[";
		for (int i = 0; i < this.noO; i ++) {
			for (int m = 0; m < this.arrayOfOperation[i].length; m ++) {
				seq += this.arrayOfOperation[i][m];
				if (this.noO != 1 && m < this.arrayOfOperation[i].length - 1) {
					seq += ", ";
				}
			}
			if ((this.arrayOfOperation[i].length != 0 && this.arrayOfOperation[i].length != 1) && i < this.noO - 2) {
				seq += ", "; 
			}
		}
		seq += "]";
		
		String concatString = "Concat(";
		for (int i = 0; i < this.noO; i ++) {
			concatString += SeqOperation.arrayObjectToString(this.arrayOfOperation[i]);
			if (i < this.noO - 1) {
				concatString += ", ";
			}
		}
		concatString += ")";
		
		if (this.numIncompatableOperation == 0) {
			result = concatString + " = " + seq;
		}
		else {
			result = "Concat cannot be evaluated due to " + this.numIncompatableOperation + " incompatile operations.";
		}
		return result;
	}
}
