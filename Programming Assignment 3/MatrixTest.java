/*
 * Created by Jessica Pan on 10/28/2018
 * CruzID: jeypan 
 * PA3
 * CMPS101
 *
 * MatrixTest.java
 * test client for Matrix ADT
 * tests for edge cases in Matrix.java
 */
 
 public class MatrixTest {
	
	public static void main(String[] args) {
		
		int matrixSize = 5;
		Matrix A = new Matrix(matrixSize);
		Matrix B = new Matrix(matrixSize);
				
		for(int i = 1; i <= matrixSize; i++){
			for(int j = 1; j <= matrixSize; j++){
				A.changeEntry(i, j, j);
				B.changeEntry(i, j, matrixSize-j);
			}
		}
		
		System.out.println("A" + A + "\n NNZ: " + A.getNNZ() + "\n Size:" + A.getSize() + "\n");
		System.out.println("B" + B + "\n NNZ: " + B.getNNZ() + "\n Size:" + B.getSize() + "\n");
		
		//scalarMult
		System.out.println("scalarMult_A");
		Matrix C = A.scalarMult(2);
		System.out.println(C + "\n NNZ: " + C.getNNZ() + "\n Size:" + C.getSize() + "\n");
		
		System.out.println("scalarMult_B");
		Matrix D = B.scalarMult(0.0);
		System.out.println(D + "\n NNZ: " + D.getNNZ() + "\n Size:" + D.getSize() + "\n");
		
		//add
		System.out.println("add_A");
		Matrix E = A.add(B);
		System.out.println(E + "\n NNZ: " + E.getNNZ() + "\n Size:" + E.getSize() + "\n");
		
		System.out.println("add_B");
		Matrix F = B.add(A);
		System.out.println(F + "\n NNZ: " + F.getNNZ() + "\n Size:" + F.getSize() + "\n");
		
		
		//sub
		System.out.println("sub_A");
		Matrix G = A.sub(B);
		System.out.println(G + "\n NNZ: " + G.getNNZ() + "\n Size:" + G.getSize() + "\n");
		
		System.out.println("sub_B");
		Matrix H = B.sub(A);
		System.out.println(H + "\n NNZ: " + H.getNNZ() + "\n Size:" + H.getSize() + "\n");
		
		//mult
		System.out.println("mult_A");
		Matrix I = A.mult(B);
		System.out.println(I + "\n NNZ: " + I.getNNZ() + "\n Size:" + I.getSize() + "\n");
		
		System.out.println("mult_B");
		Matrix J = B.mult(A);
		System.out.println(J + "\n NNZ: " + J.getNNZ() + "\n Size:" + J.getSize() + "\n");
		
		//transpose
		System.out.println("transpose_A");
		Matrix K = A.transpose();
		System.out.println(K + "\n NNZ: " + K.getNNZ() + "\n Size:" + K.getSize() + "\n");
		
		System.out.println("transpose_B");
		Matrix L = B.transpose();
		System.out.println(L + "\n NNZ: " + L.getNNZ() + "\n Size:" + L.getSize() + "\n");
		
		//copy
		System.out.println("copy_A");
		Matrix M = A.copy();
		System.out.println(M + "\n NNZ: " + M.getNNZ() + "\n Size:" + M.getSize() + "\n");
		
		//equals
		System.out.println("equals A & copy of A");
		System.out.println(A.equals(M));
		System.out.println("equals B & copy of A");
		System.out.println(B.equals(M));		
		System.out.println("equals B & A");
		System.out.println(B.equals(A));
		System.out.println("equals A & A");
		System.out.println(A.equals(A));		
		
		//makeZero
		System.out.println("makeZero_copy of A");
		M.makeZero();
		System.out.println(M);
		System.out.println("\n NNZ: " + M.getNNZ() + "\n Size:" + M.getSize() + "\n");		

	}
 }