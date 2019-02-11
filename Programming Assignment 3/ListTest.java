/*
 * Created by Jessica Pan on 10/28/2018
 * CruzID: jeypan 
 * PA3
 * CMPS101
 *
 * ListTest.java
 * test client for List ADT
 * tests for edge cases in List.java
 */
 
 public class ListTest {
	
	public static void main(String[] args) {
		
		List A = new List();
		List B = new List();
		for(int i = 1; i <= 20; i++){
			A.append(i);
			B.prepend(i);
		}
		
		System.out.println(A);
		System.out.println(B);
		
		A.moveFront();
		while(A.index() >= 0){
			System.out.print(A.get() + " ");
			A.moveNext();
		}
		
		System.out.println();
		
		B.moveBack();
		while(B.index() >= 0){
			System.out.print(B.get() + " ");
			B.movePrev();
		}
		
		System.out.println();
		
		System.out.println(A.equals(B));
		System.out.println(B.equals(A));
		System.out.println(A.equals(A));
		
		A.moveFront();
		for(int i = 0; i < 5; i++){
			A.moveNext(); // at index 5
		}
		A.insertBefore(-1);
		for(int i = 0; i < 9; i++){
			A.moveNext(); // at index 15
		}
		A.insertAfter(-2);
		for(int i = 0; i < 5; i++){
			A.movePrev(); // at index 10
		}
		A.delete();
		System.out.println(A);
		System.out.println(A.length());
		A.clear();
		System.out.println(A.length());
	}
 }
	// Output:
	// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	// 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 
	// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	// false
	// false
	// 1 2 3 4 5 -1 6 7 8 9 10 11 12 13 14 15 -2 16 17 18 19 20
	// 21
	// 0
	