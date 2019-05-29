//Paige Riola - priola - 101 Tantalo - PA3 - 5/14/2018
//Matrix.java - a Matrix ADT of Entries contained within Lists
@SuppressWarnings("overrides")
public class Matrix{
	
	@SuppressWarnings("overrides")
	private class Entry{
		int index;
		double value;
		
		Entry(int index, double value){
			this.index = index;
			this.value = value;
		}
		
		int getIndex(){
			return this.index;
		}
		
		double getValue(){
			return this.value;
		}
		
		void changeValue(double value){
			this.value = value;
		}
		
		public String toString(){
			return "(" + String.valueOf(index) + ", " + String.valueOf(value) + ")";
		}
		
		public boolean equals(Object x){
			boolean eq = false;
			Entry that;
			if(x instanceof Entry){
				that = (Entry) x;
				eq = (this.index == that.index && this.value==that.value);
			}
			return eq;
		}
	}
	
	private int size;
	private int NNZ;
	private List[] row;
	
	// Constructor
	// Makes a new n x n zero Matrix. pre: n>=1
	Matrix(int n){
		if( n < 1 ){
			throw new RuntimeException("Matrix Error: cannot construct a zero or negative size matrix");
		}
		
		size = n;
		NNZ = 0;
		row = new List[n+1];
		
		for(int i = 1; i <= size; i++){
			row[i] = new List();
		}
	}
	
	// Access functions
	
	// Returns n, the number of rows and columns of this Matrix
	int getSize(){
		return size;
	}
	
	// Returns the number of non-zero entries in this Matrix
	int getNNZ(){
		return NNZ;
	}
	
	// overrides Object's equals() method
	@SuppressWarnings("unchecked")
	public boolean equals(Object x){
		boolean eq = false;
		Matrix P;

		if(x instanceof Matrix){
			P = (Matrix) x;
			eq = (this.size==P.size);
			int i = 1;
			while( eq && i <= this.size ){
				eq = this.row[i].equals(P.row[i]);
				i++;
			}
		}
		return eq;
	}
	
	// Manipulation procedures
	
	// sets this Matrix to the zero state
	void makeZero(){
		for(int i = 1; i <= getSize(); i++){
			if(row[i].length() > 0){
				row[i].clear();
			}
		}
		
		NNZ = 0;
	}
	
	// returns a new Matrix having the same entries as this Matrix
	Matrix copy(){
		Matrix M = new Matrix(getSize());
		Entry e;
		
		for(int i = 1; i <= getSize(); i++){
			row[i].moveFront();
			while(row[i].index() != -1){
				e = (Entry) row[i].get();
				M.changeEntry(i,e.getIndex(),e.getValue());
				row[i].moveNext();
			}
		}
		
		return M;
	}
	
	// changes ith row, jth column of this Matrix to x
	// pre: 1<=i<=getSize(), 1<=j<=getSize()
	void changeEntry(int i, int j, double x){
		Entry e;
		
		if(i > getSize() || j > getSize() || i < 1 || j < 1){
			throw new RuntimeException("Matrix Error: changeEntry() called on non-existant Entry");
		}
		
		if(row[i].length() == 0 && x != 0){ //if there is nothing in the row, and the value is not 0
			row[i].prepend(new Entry(j,x));
			NNZ++; //0 to NNZ so increase NNZ
		}
		else if(row[i].length() == 0 && x == 0){
			//do nothing
		}
		else{ //if there is something in the row
			row[i].moveFront();
			
			while(row[i].index() != -1){
				e = (Entry) row[i].get();
				
				if(j > e.getIndex()){ //increment if the cursor col is less than your col
					row[i].moveNext();
				}
				else if(j == e.getIndex()){ //if the cursor col equals the col
					if(x == 0){ //if the new value is 0, delete the entry
						row[i].delete();
						row[i].moveFront();
						NNZ--; //removing an NNZ, so NNZ decrease
					}
					else{ //if the new value is not 0, change the entry value to the new value
						e.changeValue(x); //it was already a NNZ so NNZ does not change
					}
					break;
				}
				else if(j < e.getIndex()){ //if the cursor col is greater than your col, then your col is zero
					if(x != 0){ //if you're value is not zero, you should add an entry to the col with the new value, else do nothing
						row[i].insertBefore(new Entry(j,x));
						NNZ++;
					}
					break;
				}
			}
			
			if(row[i].index() == -1 && x != 0){ //if you reached the end of the row and every previous value was less than your col
				row[i].append(new Entry(j,x)); //and you're value isnt zero, add the new entry to the end of the list
				NNZ++;
			} //else if the value is 0, do nothing
		}
	}
	
	// returns a new Matrix that is the scalar product of this Matrix with x
	Matrix scalarMult(double x){
		Matrix N = new Matrix(getSize());
		Entry e;
		
		if(x == 0){
			N.makeZero();
		}
		else{
			for(int i = 1; i <= getSize(); i++){
				row[i].moveFront();
				while(row[i].index() != -1){
					e = (Entry) row[i].get();
					N.changeEntry(i,e.getIndex(),e.getValue() * x);
					row[i].moveNext();
				}
			}
		}
		
		return N;
	}
	
	// returns a new Matrix that is the sum of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix add(Matrix M){
		Matrix A = copy();
		Matrix B = M.copy();
		Matrix N = new Matrix(getSize());
		Entry e, f;
		
		A.toString();
		B.toString();
		
		if(A.getSize() != B.getSize()){
			throw new RuntimeException("Matrix Error: add() called on matrices of varying sizes");
		}
		
		if(B.getNNZ() == 0){
			N = copy();
		}
		else if(equals(B)){
			N = scalarMult(2);
		}
		else if(A.scalarMult(-1).equals(B)){
			N.makeZero();
		}
		else{
			for(int i = 1; i <= A.getSize(); i++){
				A.row[i].moveFront();
				B.row[i].moveFront();
				
				while(A.row[i].index() != -1 || B.row[i].index() != -1){
					if(A.row[i].index() != -1 && B.row[i].index() != -1){ //1: Both rows still have data
						e = (Entry) A.row[i].get();
						f = (Entry) B.row[i].get();
						
						if(e.getIndex() == f.getIndex()){
							N.changeEntry(i,e.getIndex(),e.getValue() + f.getValue());
							A.row[i].moveNext();
							B.row[i].moveNext();
						}
						else if(e.getIndex() < f.getIndex()){
							N.changeEntry(i,e.getIndex(),e.getValue());
							A.row[i].moveNext();
						}
						else if(e.getIndex() > f.getIndex()){
							N.changeEntry(i,f.getIndex(),f.getValue());
							B.row[i].moveNext();
						}
					}
					else if(B.row[i].index() == -1){ //2: B has run out of data
						e = (Entry) A.row[i].get();
						N.changeEntry(i,e.getIndex(),e.getValue());
						A.row[i].moveNext();
					}
					else if(A.row[i].index() == -1){ //3: A has run out of data
						f = (Entry) B.row[i].get();
						N.changeEntry(i,f.getIndex(),f.getValue());
						B.row[i].moveNext();
					}
				}
			}
		}
		
		return N;
	}
	
	// returns a new Matrix that is the difference of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix sub(Matrix M){	
		if(getSize() != M.getSize()){
			throw new RuntimeException("Matrix Error: add() called on matrices of varying sizes");
		}
		
		return add(M.scalarMult(-1));
	}
	
	// returns a new Matrix that is the transpose of this Matrix
	Matrix transpose(){
		Matrix N = new Matrix(getSize());
		Entry e;
		int j = 0;
		
		for(int i = 1; i <= getSize(); i++){
			row[i].moveFront();
			while(row[i].index() != -1){
				e = (Entry) row[i].get();
				j = e.getIndex();
				N.changeEntry(j,i,e.getValue());
				row[i].moveNext();
			}
		}
		
		return N;
	}
	
	// returns a new Matrix that is the product of this Matrix with M
	// pre: getSize()==M.getSize()
	Matrix mult(Matrix M){
		Matrix A = copy();
		Matrix B = M.copy().transpose();
		Matrix N = new Matrix(getSize());
		
		if(getSize() != M.getSize()){
			throw new RuntimeException("Matrix Error: mult() called on matrices of varying sizes");
		}
		
		for(int i = 1; i <= A.getSize(); i++){
			if(A.row[i].length() > 0){
				for(int j = 1; j <= B.getSize(); j++){
					if(B.row[j].length() > 0){
						N.changeEntry(i,j,dot(A.row[i],B.row[j]));
					}
				}
			}
		}
		
		return N;
	}

	// Other functions
	
	// overrides Object's toString() method
	public String toString(){
		String matrix = "";
		
		for(int i = 1; i <= getSize(); i++){
			if(row[i].length() > 0){
				matrix += i + ": " + row[i].toString() + "\n";
			}
		}
		
		return matrix;
	}
	
	private static double dot(List P, List Q){
		double dotValue = 0;
		List A = P.copy();
		List B = Q.copy();
		Entry e, f;
		
		A.moveFront();
		B.moveFront();
		
		while(A.index() != -1 && B.index() != -1){
			e = (Entry) A.get();
			f = (Entry) B.get();
			if(e.getIndex() == f.getIndex()){
				dotValue += (e.getValue() * f.getValue());
				A.moveNext();
				B.moveNext();
			}
			else if(e.getIndex() < f.getIndex()){
				A.moveNext();
			}
			else if(e.getIndex() > f.getIndex()){
				B.moveNext();
			}
		}
		
		return dotValue;
	}
}