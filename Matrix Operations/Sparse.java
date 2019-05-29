//Paige Riola - priola - 101 Tantalo - PA3 - 5/14/2018
//Sparse.java - reads data for 2 matrices from an input file, and performs simple matrix operations on the two matrices,
//              printing these results onto the specified output

import java.io.*;
import java.util.Scanner;

public class Sparse{
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		
		String lineInitial = "";
		String lineA = "";
		String lineB = "";
		
		String[] token;
		String[] matrixA;
		String[] matrixB;
		
		int n,a,b;
		
		int x,y;
		double v;
		
		Matrix A;
		Matrix B;

		if(args.length < 2){
			System.err.println("Usage: Sparse infile outfile");
			System.exit(1);
		}

		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		
		//check if file is empty
		if( !in.hasNextLine() ){
			System.err.println("Error: Input file is empty");
			System.exit(1);
		}
		
		//first line identifies # of lines and # of data in matrix
		
		lineInitial = in.nextLine()+" ";    // add extra space so split works right
        token = lineInitial.split("\\s+");  // split line around white space
		
		//there should be 3 numbers on the first line, else the file is not valid
		if(token.length > 3){
			System.err.println("Input file Error: first line has invalid amount of tokens\nUsage: n a b");
			System.exit(1);
		}
		
		n = Integer.parseInt(token[0]); //the size constraint of both matrices, n x n
		a = Integer.parseInt(token[1]); //the number of values in matrix A
		b = Integer.parseInt(token[2]); //the number of values in matrix B
		
		//if there are more data values listed than the matrix can fit based off of n, the file is invalid
		if(a > (n*n) || b > (n*n)){
			System.err.println("Input file Error: More data points exist than spaces in the matrix");
			System.exit(1);
		}
		
		A = new Matrix(n);
		B = new Matrix(n);
		
		in.nextLine(); //skips the blank line
		
		//for a times, tokenize
		for(int i = 0; i < a; i++){
			lineA += in.nextLine()+" ";
		}
		matrixA = lineA.split("\\s+");
		
		//add these entries to A, use an i += 3 for loop
		for(int i = 0; i < matrixA.length; i += 3){
			x = Integer.parseInt(matrixA[i]);
			y = Integer.parseInt(matrixA[i+1]);
			v = Double.parseDouble(matrixA[i+2]);
			
			A.changeEntry(x,y,v);
		}
		
		in.nextLine(); //skips the blank line
		
		//for b times, tokenize
		for(int i = 0; i < b; i++){
			lineB += in.nextLine()+" ";
		}
		matrixB = lineB.split("\\s+");
		
		//add these entries to B, use an i += 3 for loop
		for(int i = 0; i < matrixB.length; i += 3){
			x = Integer.parseInt(matrixB[i]);
			y = Integer.parseInt(matrixB[i+1]);
			v = Double.parseDouble(matrixB[i+2]);
			
			B.changeEntry(x,y,v);
		}
		
		//A
		out.println("A has " + A.getNNZ() + " non-zero entries:");
		out.println(A.toString());
		
		//B
		out.println("B has " + B.getNNZ() + " non-zero entries:");
		out.println(B.toString());
		
		//1.5*A
		out.println("(1.5)*A =");
		out.println(A.scalarMult(1.5).toString());
		
		//A+B
		out.println("A+B =");
		out.println(A.add(B).toString());
		
		//A+A
		out.println("A+A =");
		out.println(A.add(A).toString());
		
		//B-A
		out.println("B-A =");
		out.println(B.sub(A).toString());
		
		//A-A
		out.println("A-A =");
		out.println(A.sub(A).toString());
		
		//Transpose(A)
		out.println("Transpose(A) =");
		out.println(A.transpose().toString());
		
		//A*B
		out.println("A*B =");
		out.println(A.mult(B).toString());
		
		//B*B
		out.println("B*B =");
		out.print(B.mult(B).toString());
		
		in.close();
		out.close();
	}
}