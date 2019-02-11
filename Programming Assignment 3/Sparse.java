import java.io.*;
import java.util.Scanner;

/*
 * Created by Jessica Pan on 10/28/2018
 * CruzID: jeypan 
 * PA3
 * CMPS101
 *
 * Sparse.java
 * Top level client for Matrix ADT
 */
 
 public class Sparse {
	 
	public static void main (String[] args) throws FileNotFoundException{
		if (args.length != 2){
			System.err.println("You need to pass an input file and output file!");
		}
		
		String infile = String.format("%s", args[0]);
		String outfile = String.format("%s", args[1]);
		
		Scanner in = new Scanner(new File(infile));
		
		// first line: n(matrix size), a(NNZ in matrix A), b(NNZ in matrix B)
		int matrixSize;
		int aNNZ;
		int bNNZ;
		
		matrixSize = in.nextInt();
		aNNZ = in.nextInt();
		bNNZ = in.nextInt();
		
		Matrix A = new Matrix(matrixSize);
		Matrix B = new Matrix(matrixSize);
		// first line for matrix A: row, column, value
		int row, column;
		double value;
		
		in.nextLine();
		
		for(int i = 0; i < aNNZ; i++){
			if(in.hasNext()){
				row = in.nextInt();
				column = in.nextInt();
				value = in.nextDouble();
				A.changeEntry(row, column, value);
			}
		}
		
		in.nextLine();

		for(int i = 0; i < bNNZ; i++){
			if(in.hasNext()){
				row = in.nextInt();
				column = in.nextInt();
				value = in.nextDouble();
				B.changeEntry(row, column, value);
			}
		}
		
		PrintWriter out = new PrintWriter(outfile);
		
		out.println("A has " + aNNZ + " non-zero entries:");
		out.println(A);
		
		out.println("B has " + bNNZ + " non-zero entries:");
		out.println(B);
		
		out.println("(1.5)*A =");
		out.println(A.scalarMult(1.5));
		
		out.println("A+B =");
		out.println(A.add(B));
		
		out.println("A+A =");
		out.println(A.add(A));
		
		out.println("B-A =");
		out.println(B.sub(A));
		
		out.println("A-A =");
		out.println(A.sub(A));
		
		out.println("Transpose(A) =");
		out.println(A.transpose());
		
		out.println("A*B =");
		out.println(A.mult(B));
		
		out.println("B*B =");
		out.println(B.mult(B));
		
		in.close();
		out.close();
		
	}
 }
		
		