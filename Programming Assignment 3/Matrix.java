/*
 * Created by Jessica Pan on 10/28/2018
 * CruzID: jeypan 
 * PA3
 * CMPS101
 *
 * Matrix.java
 * Implementation of Matrix ADT
 */
 
 @SuppressWarnings("overrides")
 public class Matrix{
	
	@SuppressWarnings("overrides")
	private class Entry{
		int column;
		double value;

		Entry(int column, double value){
			this.column = column;
			this.value = value;
		}
		
		public boolean equals(Object x){
			boolean equal = false;
			Entry n;
			if (x instanceof Entry){
				n = (Entry) x;
				equal = ((this.column == n.column) && (this.value == n.value));
			}
			return equal;
		}
		
		public String toString() {
			return "( " + column + ", " + value + " )";
		}
	}
		
	private List[] row;
	private int NNZ = 0;	// number of non-zero entries
	private int matrixSize = 0;
		
	// Constructor
	Matrix(int n) { // Makes a new n x n zero Matrix. pre: n>=1
		if (n < 1){
			throw new RuntimeException("Error: matrix needs to be greater than 0x0");
		}
		
		//DON'T INCLUDE INDEX AT 0		
		matrixSize = n;
		row = new List[matrixSize + 1]; //make column for rows of Lists
		for	(int i = 1; i <= matrixSize; i++){
			row[i] = new List();	//storing Lists into row 
		}	
	}	

	// Access functions
	int getSize() {	// Returns n, the number of rows and columns of this Matrix
		return matrixSize;	
	}
	
	int getNNZ() { // Returns the number of non-zero entries in this Matrix
		return NNZ;
	}
	
	public boolean equals(Object x) { // overrides Object's equals() method
		boolean equal = false;
		Matrix A = (Matrix) x;
		equal = (matrixSize == A.matrixSize);
		
		if (equal == true){
			for (int i = 1; i <= matrixSize; i++){
				//comparing sizes
				if(this.row[i] == null && A.row[i] == null){
					equal = true;
				}else if ((row[i].length() != 0 && A.row[i].length() != 0)){
					if ((row[i].length() != A.row[i].length()) && (this.getNNZ() != A.getNNZ())){
						equal = false;
						break;
					}else{
						//comparing every element in Lists
						row[i].moveFront();
						A.row[i].moveFront();
						while (row[i].index() != -1 && A.row[i].index() != -1){
							if (row[i].get() != A.row[i].get()){
								equal = false;
								break;
							}
							row[i].moveNext();
							A.row[i].moveNext();
						}equal = true;
					}
				}else if((row[i].length() == 0 && A.row[i].length() == 0)){
					equal = true;	
				}					
			}
		}
		return equal;
	}
	
	// Manipulation procedures
	void makeZero() { // sets this Matrix to the zero state
		if (getSize() < 1){
			System.out.println("Error: Matrix size is less than 1x1, needs to be greater than or equal to 1x1");
			return;
		}
		
		NNZ = 0;
		row = new List[matrixSize + 1];
		for (int i = 1; i <= getSize(); i++){
			//row[i].clear();
			row[i] = new List();
		}
	}
	
	Matrix copy() { // returns a new Matrix having the same entries as this Matrix
		Matrix dup = new Matrix(matrixSize);
		for (int i = 1; i <= matrixSize; i++){
			if (row[i].length() > 0){
				row[i].moveFront();
				while (row[i].index() != -1){
					Entry n = (Entry) row[i].get();
					dup.row[i].append(n);
					dup.NNZ++;
					//prevent ourselves from doing extra work
					if (dup.NNZ == this.NNZ){
						return dup;
					}
					row[i].moveNext();
				}
			}
				
		}
		return dup;
	}
	
	void changeEntry(int i, int j, double x) {
	 // changes ith row, jth column of this Matrix to x
	 // pre: 1<=i<=getSize(), 1<=j<=getSize()
		if (!(i >= 1 && i <= getSize()) || !(j >= 1 && j <= getSize())) {
			throw new RuntimeException("row and column index are invalid.");
		}

		Entry newEnt = new Entry(j,x);
		// list at row[i] isn't empty
		if (row[i].length() > 0) {
			row[i].moveFront();
			Entry E;
			for(int k = 0; k <= j; k++){
				E = (Entry) row[i].get();
				int col = E.column;
				if (col == j){
					// case 4
					if (x != 0){
						E.value = x;
						break;
					}
					// case 2
					if (x == 0){
						row[i].delete();
						NNZ--;
						break;
					}
				}else if (col < j){
					row[i].moveNext();		
					// if we fall off the list, part of case 3
					if (row[i].index() < 0){
						if (x != 0){
							row[i].append(newEnt);
							NNZ++;
							break;
						}else{	// case 1, if x = 0
							break;
						}
					}
				}else{ // col > j
					// part of case 3
					if (x != 0){
						row[i].insertBefore(newEnt);
						NNZ++;
						break;
					}else{
						break;
					}
				}
			}
		}else if (row[i].length() == 0) { // if list at row[i] is empty and we insert new one
			if (x != 0){
				row[i].append(newEnt);
				NNZ++;
			}
		}
	}
	
	Matrix scalarMult(double x) {
	 // returns a new Matrix that is the scalar product of this Matrix with x
		Matrix newM = new Matrix(getSize());
		Entry E;
		for(int i = 1; i <= getSize(); i++){
			if(row[i].length() > 0){
				row[i].moveFront();
				while(row[i].index() != -1){
					E = (Entry)row[i].get();
					double mul = (E.value) * x;
					newM.changeEntry(i, E.column, mul);
					row[i].moveNext();
				}
			}
		}
		return newM;
	}
	 
	Matrix add(Matrix M) {
	 // returns a new Matrix that is the sum of this Matrix with M
	 // pre: getSize()==M.getSize()
		if(getSize() != M.getSize()){
			throw new RuntimeException("Error: trying to add different sized matrices.");
		}
		
		if(this == M){
			return M.scalarMult(2.0);
		}
		
		Matrix newM = new Matrix(getSize());
			
		for(int i = 1; i <= getSize(); i++){
			List aList = row[i];
			aList.moveFront();
			List bList = M.row[i];
			bList.moveFront();
			
			if(aList.length() > 0 && bList.length() > 0){
				aList.moveFront();
				bList.moveFront();
				double k1, k2;
				while((aList.index() >= 0) || (bList.index() >= 0)){
					if((aList.index() >= 0) && (bList.index() >= 0)){	
						if(((Entry)aList.get()).column == ((Entry)bList.get()).column){
							if((((Entry)aList.get()).value + ((Entry)bList.get()).value) != 0){
								k1 = ((Entry)aList.get()).value;
								k2 = ((Entry)bList.get()).value;
								newM.changeEntry(i, ((Entry)aList.get()).column, (k1 + k2));
								aList.moveNext();
								bList.moveNext();
							}else{
								aList.moveNext();
								bList.moveNext();
							}
						}else if(((Entry)aList.get()).column < ((Entry)bList.get()).column){
							k1 = ((Entry)aList.get()).value;
							newM.changeEntry(i, ((Entry)aList.get()).column, k1);
							aList.moveNext();
						}else{ //A.column > B.column
							k1 = ((Entry)bList.get()).value;
							newM.changeEntry(i, ((Entry)bList.get()).column, k1);
							bList.moveNext();
						}
					}else if(aList.index() >= 0){
						k1 = ((Entry)aList.get()).value;
						newM.changeEntry(i, ((Entry)aList.get()).column, k1);
						aList.moveNext();
					}else{ // M.row[i].index() >= 0
						k1 = ((Entry)bList.get()).value;
						newM.changeEntry(i, ((Entry)bList.get()).column, k1);
						bList.moveNext();
					}
				}
			}else if(aList.length() > 0 && bList.length() == 0){
				aList.moveFront();
				while(aList.index() != -1){
					newM.changeEntry(i, ((Entry)aList.get()).column, ((Entry)aList.get()).value);
					aList.moveNext();
				}
			}else if(aList.length() == 0 && bList.length() > 0){
				bList.moveFront();
				while(bList.index() != -1){
					newM.changeEntry(i, ((Entry)bList.get()).column, ((Entry)bList.get()).value);
					bList.moveNext();
				}
			}	
		}
		return newM;
	}
		
	
	
	Matrix sub(Matrix M) {
	 // returns a new Matrix that is the difference of this Matrix with M
	 // pre: getSize()==M.getSize()
		if(getSize() != M.getSize()){
			throw new RuntimeException("Error: trying to subtract different sized matrices.");
		}
		
		Matrix newM = new Matrix(getSize());
		
		if(this == M){
			// all zeros
			return newM;
		}
		
		for(int i = 1; i <= getSize(); i++){
			List aList = row[i];
			aList.moveFront();
			List bList = M.row[i];
			bList.moveFront();
			
			if(aList.length() > 0 && bList.length() > 0){
				aList.moveFront();
				bList.moveFront();
				double k1, k2;
				while((aList.index() >= 0) || (bList.index() >= 0)){
					if((aList.index() >= 0) && (bList.index() >= 0)){	
						if(((Entry)aList.get()).column == ((Entry)bList.get()).column){
							if((((Entry)aList.get()).value - ((Entry)bList.get()).value) != 0){
								k1 = ((Entry)aList.get()).value;
								k2 = ((Entry)bList.get()).value;
								newM.changeEntry(i, ((Entry)aList.get()).column, (k1 - k2));
								aList.moveNext();
								bList.moveNext();
							}else{
								aList.moveNext();
								bList.moveNext();
							}
						}else if(((Entry)aList.get()).column < ((Entry)bList.get()).column){
							k1 = ((Entry)aList.get()).value;
							newM.changeEntry(i, ((Entry)aList.get()).column, k1);
							aList.moveNext();
						}else{ //A.column > B.column
							k1 = ((Entry)bList.get()).value;
							newM.changeEntry(i, ((Entry)bList.get()).column, (-1)*k1);
							bList.moveNext();
						}
					}else if(aList.index() >= 0){
						k1 = ((Entry)aList.get()).value;
						newM.changeEntry(i, ((Entry)aList.get()).column, k1);
						aList.moveNext();
					}else{ // M.row[i].index() >= 0
						k1 = ((Entry)bList.get()).value;
						newM.changeEntry(i, ((Entry)bList.get()).column, (-1) * k1);
						bList.moveNext();
					}
				}
			}else if(aList.length() > 0 && bList.length() == 0){
				aList.moveFront();
				while(aList.index() != -1){
					newM.changeEntry(i, ((Entry)aList.get()).column, ((Entry)aList.get()).value);
					aList.moveNext();
				}
			}else if(aList.length() == 0 && bList.length() > 0){
				bList.moveFront();
				while(bList.index() != -1){
					newM.changeEntry(i, ((Entry)bList.get()).column, (-1)*((Entry)bList.get()).value);
					bList.moveNext();
				}
			}	
		}
		return newM;
	}
	
	Matrix transpose() {
	 // returns a new Matrix that is the transpose of this Matrix
		Matrix newM = new Matrix(getSize());
		Entry E;
		for(int i = 1; i <= getSize(); i++){
			if(row[i].length() > 0){
				row[i].moveFront();
				while (row[i].index() != -1){
					E = (Entry)row[i].get();
					newM.changeEntry(E.column, i, E.value);
					row[i].moveNext();
				}
			}
		}
		return newM;
	}
	
	private static double dot(List P, List Q) {
		// returns the sum of multiplying the two lists
		// does most of the math work of the mult method
		double sum = 0.0;
		P.moveFront();
		Q.moveFront();
		while(P.index() >= 0 && Q.index() >= 0){
			if (((Entry)P.get()).column == ((Entry)Q.get()).column){
				sum += ((((Entry)P.get()).value)*(((Entry)Q.get()).value));
				P.moveNext();
				Q.moveNext();
			}else if (((Entry)P.get()).column < ((Entry)Q.get()).column){
				// mult by 0
				P.moveNext();
			}else{
				// mult by 0
				Q.moveNext();
			}
		}
		return sum;
	}
	
	Matrix mult(Matrix M) {
	 // returns a new Matrix that is the product of this Matrix with M
	 // pre: getSize()==M.getSize()
		if (getSize() != M.getSize()){
			throw new RuntimeException("Error: multiplying different sized matrices");
		}
		
		Matrix newM = new Matrix(getSize());
		Matrix mT = M.transpose();
		for(int i = 1; i <= getSize(); i++){
			if(row[i].length() == 0){
				// go to next row
				continue;
			}
			for(int j = 1; j <= getSize(); j++){
				// after transpose of M, columns become rows
				if(mT.row[j].length() > 0){
					double mul = dot(row[i], mT.row[j]);
					newM.changeEntry(i, j, mul);
				}else{
					continue;
				}
			}
		}
		return newM;	 
	}
	
	// Other functions
	// overrides Object's toString() method
	public String toString() {
		String str = "";
		for(int i = 1; i <= getSize(); i++){
			if(this.row[i] == null){
				str += "";
			}else if(row[i].length() == 0){
				continue;
			}
			str += (i + ": " + String.valueOf(row[i]) + " \n");
		}
		return str;
	}

 }