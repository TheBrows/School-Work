//Paige Riola - priola - 101 Tantalo - PA3 - 5/14/2018
//MatrixTest.java - a test of the Matrix ADT
public class MatrixTest{
   public static void main(String[] args){
      int i, j, n=100000;
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
      A.changeEntry(3,3,9); B.changeEntry(3,3,1);

	  System.out.println("Test 1: A");
      System.out.println(A.getNNZ());
      System.out.println(A);

      System.out.println("Test 2: B");
	  System.out.println(B.getNNZ());
      System.out.println(B);

      System.out.println("Test 3: A*1.5");
	  Matrix C = A.scalarMult(1.5);
      System.out.println(C.getNNZ());
      System.out.println(C);

      System.out.println("Test 4: A+A");
	  Matrix D = A.add(A);
      System.out.println(D.getNNZ());
      System.out.println(D);

      System.out.println("Test 5: A-A");
	  Matrix E = A.sub(A);
      System.out.println(E.getNNZ());
      System.out.println(E);
	  
	  System.out.println("Test 6: A+B");
	  Matrix I = A.add(B);
      System.out.println(I.getNNZ());
      System.out.println(I);
	  
	  System.out.println("Test 7: B^T");
      Matrix F = B.transpose();
      System.out.println(F.getNNZ());
      System.out.println(F);

      System.out.println("Test 8: B*B");
	  Matrix G = B.mult(B);
      System.out.println(G.getNNZ());
      System.out.println(G);
	  
	  System.out.println("Test 9: copy A");
      Matrix H = A.copy();
      System.out.println(H.getNNZ());
      System.out.println(H);
	  
	  System.out.println("Test 10: equals");
      System.out.println(A.equals(H));
      System.out.println(A.equals(B));
      System.out.println(A.equals(A));

      System.out.println("Test 11: makeZero");
	  A.makeZero();
      System.out.println(A.getNNZ());
      System.out.println(A);
	  
	  //These are the script tests you failed
	  
	  System.out.println("BIG Test 1:");
	  A = new Matrix(10);
	  System.out.println("A size: " + A.getSize());
      B = new Matrix(15);
	  System.out.println("B size: " + B.getSize());
      if (A.equals(B)) System.out.println("failed 1");
      B = new Matrix(10);
      if (!A.equals(B)) System.out.println("failed 2");
      A.changeEntry(5, 5, 5);
      B.changeEntry(5, 5, 5);
      A.makeZero();
      B.makeZero();
      if (!A.equals(B)) System.out.println("failed 3");
	  
	  System.out.println("BIG Test 2:");
	  A = new Matrix(10);
		A.changeEntry(2, 1, 2);
		A.changeEntry(3, 1, 5);
		A.changeEntry(1, 2, 2);
		A.changeEntry(1, 3, 5);
		A.changeEntry(1, 1, 4);
		A.changeEntry(2, 2, 2);
		A.changeEntry(2, 5, 0);
		A.changeEntry(2, 3, 0);
		A.changeEntry(3, 3, 5);
		if (A.getNNZ() != 7) System.out.println("failed 1");
		A.changeEntry(1, 3, 0);
		A.changeEntry(3, 1, 0);
		A.changeEntry(3, 3, 0);
		if (A.getNNZ() != 4) System.out.println("failed 2");
		A.changeEntry(7, 6, 42);
		A.changeEntry(10, 1, 24);
		if (A.getNNZ() != 6) System.out.println("failed 3");
		A.changeEntry(7, 6, 0);
		if (A.getNNZ() != 5) System.out.println("failed 4");
		A.makeZero();
		A.changeEntry(5, 5, 5);
		if (A.getNNZ() != 1) System.out.println("failed 5");
   }
}