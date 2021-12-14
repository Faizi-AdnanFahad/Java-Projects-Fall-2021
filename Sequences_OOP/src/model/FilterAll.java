package model;

public class FilterAll extends SeqEvaluator {

	private String[] arrayOfOperation;
	private int noO; // number of operations

	private int numIncompatableOperation;


	public FilterAll(int maxSeqOperation) {
		this.arrayOfOperation = new String[maxSeqOperation];
	}

	public String toString() {
		String result = "";

		String seq = "";
		for (int i = 0; i < this.noO; i ++) {
			seq += this.arrayOfOperation[i];
			if (i < this.noO - 1) {
				seq += ", ";
			}
		}
		if (this.numIncompatableOperation == 0) {
			result = "Filter result is: " + seq;
		}
		else {
			result = "Filter cannot be evaluated due to " + this.numIncompatableOperation + " incompatile operations.";
		}
		return result;
	}

	@Override
	public void addOperation(String operation, int[] seq1, int[] seq2) throws IllegalOperationException {
		if (operation.equals("op:occursWithin")) {
			if (OccursWithin.doesOccur(seq1, seq2)) {
				this.arrayOfOperation[this.noO] = "true";
				this.noO ++;
			}
			else {
				this.arrayOfOperation[this.noO] = "_";
				this.noO ++;
			}
		}
		else if (operation.equals("op:projection") || operation.equals("op:sumsOfPrefixes")) {
			this.numIncompatableOperation ++;
		}
		else {
			throw new IllegalOperationException("Operation is not recognized!");
		}
	}

}
