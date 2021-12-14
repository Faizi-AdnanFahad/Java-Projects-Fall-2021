package model;

public abstract class SeqEvaluator {

	public abstract void addOperation(String operation, int[] seq1, int[] seq2) throws IllegalOperationException;
}
