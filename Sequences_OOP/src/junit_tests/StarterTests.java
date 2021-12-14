package junit_tests;

import static org.junit.Assert.*;
import model.*;

import org.junit.Test;

/*
 * Requirement: Any classes you create must reside in the `model` package and be imported properly.
 * For example, creating a new class `Foo` in the `model` package should result in:
 * 	import model.Foo;
 * 
 * All attributes you declare in the model classes must be private. 
 * 	If necessary, define public accessors/getters and/or mutators/setters for these private attributes. 
 */

public class StarterTests {
	/* 
	 * Programming Requirements:
	 * 
	 * 	- You are only allowed to use primitive arrays (e.g., int[], String[], Product[]) 
	 * 		for declaring attributes and implementing the idea of collections.
	 * 	- Any use of a Java library class or method is forbidden 
	 * 		(that is, use selections and loops to build your solution from scratch instead):
	 * 	- Here are some examples of forbidden classes/methods: 
	 * 		- Arrays class (e.g., Arrays.copyOf)
	 * 		- System class (e.g., System.arrayCopy)
	 * 		- ArrayList class
	 * 		- String class (e.g., substring).
	 * 	- The use of some library classes does not require an import statement, 
	 * 		but these classes are also forbidden to be used.
	 * 	- Here are the exceptions (library methods which you are allowed to use if needed):
	 * 		- String class (equals, format)
	 * 
	 * Violating the above programming requirements will result in a penalty (see lab instructions for details). 
	 * 
	 * Tests included in this class serve as documentation on how instances of an Apple AppStore operates.
	 * 
	 * Before attempting this lab, it is expected that you already completed background study materials:
	 * 	1. Review Tutorial Series on OOP in Java (Part 1 and Part 2): 
	 * 		https://www.eecs.yorku.ca/~jackie/teaching/tutorials/index.html#refurbished_store
	 * 	2. Written Notes on Reference-Typed, Multi-Valued Attributes:
	 * 		https://www.eecs.yorku.ca/~jackie/teaching/lectures/2021/F/EECS2030/notes/EECS2030_F21_Tracing_PointCollectorTester.pdf
	 * 	3. Written Notes on Inferring Classes from JUnit Tests:
	 * 		https://www.eecs.yorku.ca/~jackie/teaching/lectures/2021/F/EECS2030/notes/EECS2030_F21_Inferring_Classes_from_JUnit.pdf 
	 * 
	 * Be sure to also read the following sections from your Lab1 instructions PDF:
	 * 	- The `Requirements of this Lab` section (page 3) 
	 * 	- Section 2.3 The Expression Evaluator Problem
	 * 	- Section 2.4 Hints and Requirements
	 * 
	 * Programming IDEs such as Eclipse are able to fix some compilation errors for you. 
	 * However, you are advised to follow the guidance as specified in the written notes above
	 * to fix these compilation errors manually, because: 
	 * 	1) it helps you better understand how the intended classes and methods work together; and 
	 * 	2) you may be tested in a written test or exam without the assistance of IDEs.
	 */
	
	/*
	 * Reflections:
	 * - You might be able to manage to pass all starter tests rather quickly.
	 * - However, gain the most from this lab by considering the inheritance hierarchy being hinted:
	 * 		+ Can you create protected attributes and/or methods that minimize code duplicates?
	 * 		+ Can you create overridden methods such that dynamic binding is exploited?    
	 * 			e.g., some abstract method(s) in the parent class?
	 */

	/*
	 * Tests related to the Projection class.
	 */ 
	
	@Test
	public void test_projection_01() {
		int[] seq1a = {1, 3, 5};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		
		/*
		 * Project the 1st sequence to the 2nd sequence:
		 * 	The resulting sequence contains 
		 * 		all those elements from the 2nd sequence which also appear in the 1st sequence,
		 * 		while maintaining their order. 
		 */
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		SeqOperation op = binOp; // 2nd sequence may contain duplicates
		assertEquals("Projecting [1, 3, 5] to [2, 1, 6, 3, 1, 4, 5, 3] results in: [1, 3, 1, 5, 3]", op.toString());
		
		op = new Projection(seq2, seq1a); // 1st sequence may contain duplicates
		assertEquals("Projecting [2, 1, 6, 3, 1, 4, 5, 3] to [1, 3, 5] results in: [1, 3, 5]", op.toString());
		
		/* more examples on sequence projections */
		
		int[] seq1b = {1, 3, 5, 3}; 
		op = new Projection(seq1b, seq2);
		assertEquals("Projecting [1, 3, 5, 3] to [2, 1, 6, 3, 1, 4, 5, 3] results in: [1, 3, 1, 5, 3]", op.toString());
		
		op = new Projection(seq2, seq1b);
		assertEquals("Projecting [2, 1, 6, 3, 1, 4, 5, 3] to [1, 3, 5, 3] results in: [1, 3, 5, 3]", op.toString());
		
		/*
		 * You may want to also test:
		 * 	1) Projecting an empty seq1 [] over a non-empty seq2 should result in an empty sequence.
		 * 	2) Projecting a non-empty seq1 over an empty seq2 [] should result in an empty sequence.  
		 */
	}
	
	@Test
	public void test_projection_02() { //
		int[] seq1a = {1, 3, 5};
		int[] seq2 = {};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [1, 3, 5] to [] results in: []", binOp.toString());
	}
	
	@Test
	public void test_projection_03() { //
		int[] seq1a = {};
		int[] seq2 = {1, 2, 3};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [] to [1, 2, 3] results in: []", binOp.toString());
	}
	
	@Test
	public void test_projection_04() { //
		int[] seq1a = {};
		int[] seq2 = {};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [] to [] results in: []", binOp.toString());
	}
	
	@Test
	public void test_projection_05() { //
		int[] seq1a = {1};
		int[] seq2 = {1};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [1] to [1] results in: [1]", binOp.toString());
	}
	
	@Test
	public void test_projection_06() { //
		int[] seq1a = {1, 0, 4, 6};
		int[] seq2 = {1, 0, 4, 6};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [1, 0, 4, 6] to [1, 0, 4, 6] results in: [1, 0, 4, 6]", binOp.toString());
	}
	
	@Test
	public void test_projection_07() { //
		int[] seq1a = {1, 6, 4, 6};
		int[] seq2 = {1, 0, 4, 6};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [1, 6, 4, 6] to [1, 0, 4, 6] results in: [1, 4, 6]", binOp.toString());
	}
	
	@Test
	public void test_projection_08() { //
		int[] seq1a = {3, 2, 1, 0};
		int[] seq2 = {4, 5, 6, 7};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [3, 2, 1, 0] to [4, 5, 6, 7] results in: []", binOp.toString());
	}
	
	@Test
	public void test_projection_09() { //
		int[] seq1a = {1, 1, 1};
		int[] seq2 = {1, 1, 1};
		
		BinarySeqOperation binOp = new Projection(seq1a, seq2);
		assertEquals("Projecting [1, 1, 1] to [1, 1, 1] results in: [1, 1, 1]", binOp.toString());
	}
	
	/*
	 * Tests related to the OccursWithin class.
	 */
	
	@Test
	public void test_occurs_within_01() {
		int[] seq1a = {1, 6, 1};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		
		/*
		 * Does the 1st sequence appear as part of the 2nd sequence? 
		 */
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		SeqOperation op = binOp;
		assertEquals("[1, 6, 1] does not occur within [2, 1, 6, 3, 1, 4, 5, 3]", op.toString());
		
		int[] seq1b = {3, 1, 4, 5}; 
		op = new OccursWithin(seq1b, seq2);
		assertEquals("[3, 1, 4, 5] occurs within [2, 1, 6, 3, 1, 4, 5, 3]", op.toString());
		
		/*
		 * You may want to also test:
		 * 	1) An empty seq1 [] occurs within any seq2 (either empty or non-empty).
		 * 	2) A non-empty seq1 does not occur within an empty seq2 []. 
		 * 	3) A sequence does not occur within another shorter sequence.  
		 */
	}
	
	@Test
	public void test_occurs_within_02() { //
		int[] seq1a = {};
		int[] seq2 = {2, 1};
		
		/*
		 * Does the 1st sequence appear as part of the 2nd sequence? 
		 */
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[] occurs within [2, 1]", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_03() { //
		int[] seq1a = {1, 2, 3};
		int[] seq2 = {};
		
		/*
		 * Does the 1st sequence appear as part of the 2nd sequence? 
		 */
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[1, 2, 3] does not occur within []", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_04() { //
		int[] seq1a = {1, 2, 3};
		int[] seq2 = {2};
		
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[1, 2, 3] does not occur within [2]", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_05() { //
		int[] seq1a = {1, 1};
		int[] seq2 = {2, 1, 1, 1, 2};
		
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[1, 1] occurs within [2, 1, 1, 1, 2]", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_06() { //
		int[] seq1a = {1, 1};
		int[] seq2 = {2, 1, 1, 2};
		
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[1, 1] occurs within [2, 1, 1, 2]", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_07() { //
		int[] seq1a = {1};
		int[] seq2 = {1, 2};
		
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[1] occurs within [1, 2]", binOp.toString());
	}
	
	@Test
	public void test_occurs_within_08() { //
		int[] seq1a = {4};
		int[] seq2 = {1, 2, 4};
		
		BinarySeqOperation binOp = new OccursWithin(seq1a, seq2);
		assertEquals("[4] occurs within [1, 2, 4]", binOp.toString());
	}
	
	
	/*
	 * Tests related to the SumsOfPrefixes class.
	 */
	
	@Test
	public void test_sums_of_prefixes_01() {
		int[] seq1 = {1};
		/*
		 * `seq1` has prefixes: [], [1]
		 * Produce another sequence where each element is the sum of each prefix. 
		 */
		SeqOperation op = new SumsOfPrefixes(seq1);
		assertEquals("Sums of prefixes of [1] is: [0, 1]", op.toString());
		
		int[] seq2 = {1, 6};
		/*
		 * `seq2` has prefixes: [], [1], [1, 6]
		 * Produce another sequence where each element is the sum of each prefix. 
		 */
		op = new SumsOfPrefixes(seq2);
		assertEquals("Sums of prefixes of [1, 6] is: [0, 1, 7]", op.toString());
		
		int[] seq3 = {1, 6, 1};
		/*
		 * `seq3` has prefixes: [], [1], [1, 6], [1, 6, 1]
		 * Produce another sequence where each element is the sum of each prefix. 
		 */
		op = new SumsOfPrefixes(seq3);
		assertEquals("Sums of prefixes of [1, 6, 1] is: [0, 1, 7, 8]", op.toString());
		
		/*
		 * You may want to also test the case where an empty sequence has only one prefix: []  
		 */
	}
	
	@Test
	public void test_sums_of_prefixes_02() {
		int[] seq1 = {};
		
		SeqOperation op = new SumsOfPrefixes(seq1);
		assertEquals("Sums of prefixes of [] is: [0]", op.toString());
		
	}
	
	@Test
	public void test_sums_of_prefixes_03() {
		int[] seq1 = {2, 0};
		
		SeqOperation op = new SumsOfPrefixes(seq1);
		assertEquals("Sums of prefixes of [2, 0] is: [0, 2, 2]", op.toString());
	}
	
	/*
	 * Tests related to the ConcatAll class.
	 */
	
	@Test
	public void test_contact_all_01() {
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		int[] seq3 = {7, 8};
		try {
			/* 
			 * Add the 1st operation as a projection, which  
			 * 	takes `seq1` and `seq2` as inputs and results in another sequence.
			 * 
			 * Assume that when "op:projection" is specified, 
			 * 	both the second and third arguments are always non-null.
			 */
			evaluator.addOperation("op:projection", seq1, seq2);
			
			/* 
			 * Add the 2nd operation as a sum of prefixes, which  
			 * 	takes `seq1` as input and results in another sequence.
			 * 
			 * Assume that when "op:sumsOfPrefixes" is specified, 
			 * 	the third argument is always null.
			 */
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			
			/*
			 * Add the 3rd operation.
			 */
			evaluator.addOperation("op:projection", seq3, seq2);
			
			/*
			 * The result of ConcatAll is the concatenation of the resulting sequences from the added operations.
			 * For example: 
			 * 	- 1st added operation (projection) results in [1, 3, 1, 5, 3].
			 * 	- 2nd added operation (sum of prefixes) results in [0, 1, 4, 9].
			 * 	- 3rd added operation (projection) results in [].
			 * 	- The concatenation of these three resulting sequences is as shown in the expected string below. 
			 */
			assertEquals("Concat([1, 3, 1, 5, 3], [0, 1, 4, 9], []) = [1, 3, 1, 5, 3, 0, 1, 4, 9]", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The concatenation involves more added operations.
			 * 	- Some added operations at the beginning or in the middle of the list may result in empty sequences.  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_05() { //
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		int[] seq3 = {7, 8};
		try {
			evaluator.addOperation("op:projection", seq1, seq2);
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			evaluator.addOperation("op:projection", seq3, seq2);
			
			assertEquals("Concat([], [0], []) = [0]", evaluator.toString());
	
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_06() { //
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {};
		int[] seq2 = {};
		int[] seq3 = {};
		try {
			evaluator.addOperation("op:projection", seq1, seq2);
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			evaluator.addOperation("op:projection", seq3, seq2);
			
			assertEquals("Concat([], [0], []) = [0]", evaluator.toString());
	
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_07() { //
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 2};
		int[] seq2 = {};
		int[] seq3 = {3, 4};
		try {
			evaluator.addOperation("op:projection", seq1, seq3);
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			evaluator.addOperation("op:projection", seq3, seq2);
			
			assertEquals("Concat([], [0, 1, 3], []) = [0, 1, 3]", evaluator.toString());
	
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_02() {
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		int[] seq3 = {7, 8};
		try {
			/* 
			 * Add the 1st operation which results in another sequence.
			 */
			evaluator.addOperation("op:projection", seq1, seq2);
			
			/*
			 * Add the 2nd operation which does NOT result in another sequence.
			 * This operation is incompatible with ConcatAll.
			 */
			evaluator.addOperation("op:occursWithin", seq1, seq3);
			
			/* 
			 * Add the 3rd operation which results in another sequence.
			 */
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			
			/*
			 * Add the 4th operation which does NOT result in another sequence.
			 * This operation is incompatible with ConcatAll.
			 */
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			
			/*
			 * ConcatAll can only function when each of the added operation results in a sequence.
			 * Otherwise, report how many such incompatible operations (each of which not resulting in a sequence) there are.  
			 */
			assertEquals("Concat cannot be evaluated due to 2 incompatile operations.", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The concatenation involves more added operations that are incompatible.  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_08() { //
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		int[] seq3 = {7, 8};
		try {
			/* 
			 * Add the 1st operation which results in another sequence.
			 */
			evaluator.addOperation("op:projection", seq1, seq2);
			evaluator.addOperation("op:occursWithin", seq1, seq3);
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);
			evaluator.addOperation("op:occursWithin", seq3, seq2);

			assertEquals("Concat cannot be evaluated due to 8 incompatile operations.", evaluator.toString());
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_contact_all_09() { //
		/*
		 * Create a ConcatAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {2, 1, 6, 3, 1, 4, 5, 3};
		int[] seq3 = {7, 8};
		try {
			/* 
			 * Add the 1st operation which results in another sequence.
			 */
			evaluator.addOperation("op:projection", seq1, seq2);
			evaluator.addOperation("op:occursWithin", seq1, seq3);
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);

			assertEquals("Concat cannot be evaluated due to 1 incompatile operations.", evaluator.toString());
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	/*
	 * Tests related to the FilterAll class.
	 */
	
	@Test
	public void test_filter_all_01() {
		/*
		 * Create a FilterAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		int[] seq3 = {4, 5, 3};
		int[] seq4 = {2, 1, 6, 3, 1, 4, 5, 3};
		try {
			/* 
			 * Add the 1st operation, which  
			 * 	takes `seq1` and `seq2` as inputs and does not result in a sequence.
			 * 
			 * Assume that when "op:occursWithin" is specified, 
			 * 	both the second and third arguments are always non-null.
			 */
			evaluator.addOperation("op:occursWithin", seq1, seq4);
			
			/* 
			 * Add the 2nd operation.
			 */
			evaluator.addOperation("op:occursWithin", seq2, seq4);
			
			/*
			 * Add the 3rd operation.
			 */
			evaluator.addOperation("op:occursWithin", seq3, seq4);
			
			/*
			 * The result of FilterAll indicates the resulting value of each added operation.
			 * For example: 
			 * 	- 1st added operation results in true 	(because seq1 occurs within seq4).
			 * 	- 2nd added operation results in _ 		(i.e., filtered out, because seq2 does not occur within seq4).
			 * 	- 3rd added operation results in true 	(because seq3 occurs within seq4). 
			 */
			assertEquals("Filter result is: true, _, true", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The filter evaluator stores more added operations.
			 * 	- Some added operations at the beginning or end of the list may result in false (which will be filtered out).  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_filter_all_04() { //
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		int[] seq3 = {4, 5, 3};
		int[] seq4 = {2, 1, 6, 3, 1, 4, 5, 3};
		try {
			evaluator.addOperation("op:occursWithin", seq1, seq2);
			evaluator.addOperation("op:occursWithin", seq2, seq4);
			evaluator.addOperation("op:occursWithin", seq3, seq4);
			assertEquals("Filter result is: _, _, true", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The filter evaluator stores more added operations.
			 * 	- Some added operations at the beginning or end of the list may result in false (which will be filtered out).  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_filter_all_05() { //
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		int[] seq3 = {4, 5, 3};
		int[] seq4 = {2, 1, 6, 3, 1, 4, 5, 3};
		try {
			evaluator.addOperation("op:occursWithin", seq1, seq4);
			evaluator.addOperation("op:occursWithin", seq2, seq4);
			evaluator.addOperation("op:occursWithin", seq4, seq1);
			assertEquals("Filter result is: true, _, _", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The filter evaluator stores more added operations.
			 * 	- Some added operations at the beginning or end of the list may result in false (which will be filtered out).  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_filter_all_06() { //
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		int[] seq3 = {4, 5, 3};
		int[] seq4 = {2, 1};
		try {
			evaluator.addOperation("op:occursWithin", seq1, seq4);
			evaluator.addOperation("op:occursWithin", seq2, seq4);
			evaluator.addOperation("op:occursWithin", seq4, seq1);
			assertEquals("Filter result is: _, _, _", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The filter evaluator stores more added operations.
			 * 	- Some added operations at the beginning or end of the list may result in false (which will be filtered out).  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_filter_all_07() { //
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1};
		int[] seq2 = {1, 8};
		int[] seq3 = {4, 1, 8};
		int[] seq4 = {2, 1, 4, 1, 8, 4};
		try {
			evaluator.addOperation("op:occursWithin", seq1, seq2);
			evaluator.addOperation("op:occursWithin", seq2, seq3);
			evaluator.addOperation("op:occursWithin", seq3, seq4);
			assertEquals("Filter result is: true, true, true", evaluator.toString());

		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	@Test
	public void test_filter_all_02() {
		/*
		 * Create a FilterAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		int[] seq3 = {4, 5, 3};
		int[] seq4 = {2, 1, 6, 3, 1, 4, 5, 3};
		try {
			/* 
			 * Add the 1st operation which results in a true/false value.
			 */
			evaluator.addOperation("op:occursWithin", seq1, seq4);
			
			/*
			 * Add the 2nd operation which does NOT result in a true/false value.
			 * This operation is incompatible with FilterAll.
			 */
			evaluator.addOperation("op:projection", seq1, seq3);
			
			/*
			 * Add the 3rd operation which does NOT result in a true/false value.
			 * This operation is incompatible with FilterAll.
			 */
			evaluator.addOperation("op:sumsOfPrefixes", seq1, null);
			
			/* 
			 * Add the 4th operation which results in a true/false value.
			 */
			evaluator.addOperation("op:occursWithin", seq2, seq4);
			
			/*
			 * FilterAll can only function when each of the added operation results in a true/false value.
			 * Otherwise, report how many such incompatible operations (each of which not resulting in a true/false value) there are.  
			 */
			assertEquals("Filter cannot be evaluated due to 2 incompatile operations.", evaluator.toString());
			
			/*
			 * You may also want to test cases where:
			 * 	- The concatenation involves more added operations that are incompatible.  
			 */
		}
		catch(IllegalOperationException e) {
			fail();
		}
	}
	
	/*
	 * More tests related to the ContactAll and FilterAll classes.
	 */
	
	@Test
	public void test_contact_all_03() {
		/*
		 * Create a ContactAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new ConcatAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		try {
			/* 
			 * It is assumed that the operation to add can only be one of the following:
			 * 	- op:occursWithin
			 * 	- op:projection
			 * 	- op:sumsOfPrefixes
			 */
			evaluator.addOperation("op:someInvalidOp1", seq1, seq2);
			fail();
		}
		catch(IllegalOperationException e) {
			
		}
	}
	
	@Test
	public void test_filter_all_03() {
		/*
		 * Create a FilterAll evaluator which can hold
		 * 	no more than 10 sequence operations.
		 */
		SeqEvaluator evaluator = new FilterAll(10);
		
		int[] seq1 = {1, 6, 3, 1};
		int[] seq2 = {7, 8};
		try {
			/* 
			 * It is assumed that the operation to add can only be one of the following:
			 * 	- op:occursWithin
			 * 	- op:projection
			 * 	- op:sumsOfPrefixes
			 */
			evaluator.addOperation("op:someInvalidOp2", seq1, seq2);
			fail();
		}
		catch(IllegalOperationException e) {
			
		}
	}
	
	/*
	 * Reflections:
	 * - You might have managed to pass all starter tests rather quickly.
	 * - However, gain the most from this lab by considering the inheritance hierarchy being hinted:
	 * 		+ Can you create protected attributes and/or methods that minimize code duplicates?
	 * 		+ Can you create overridden methods such that dynamic binding is exploited?
	 * 			e.g., some abstract method(s) in the parent class?    
	 */
}
