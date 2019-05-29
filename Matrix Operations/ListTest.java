//Paige Riola - priola - 101 Tantalo - PA3 - 5/14/2018
//ListTest.java - A test of the List ADT
public class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();
      
      for(int i=1; i<=20; i++){
         A.append(i);
         B.prepend(i);
      }
      System.out.println(A);
      System.out.println(B);
      
      for(A.moveFront(); A.index()>=0; A.moveNext()){
         System.out.print(A.get()+" ");
      }
      System.out.println();
      for(B.moveBack(); B.index()>=0; B.movePrev()){
         System.out.print(B.get()+" ");
      }
      System.out.println();
      
      List C = A.copy();
      System.out.println(A.equals(B));
      System.out.println(B.equals(C));
      System.out.println(C.equals(A));
      
      A.moveFront();
      for(int i=0; i<5; i++) A.moveNext(); // at index 5
      A.insertBefore(-1);                  // at index 6
      for(int i=0; i<9; i++) A.moveNext(); // at index 15
      A.insertAfter(-2);
      for(int i=0; i<5; i++) A.movePrev(); // at index 10
      A.delete();
      System.out.println(A);
      System.out.println(A.length());
      A.clear();
      System.out.println(A.length());
	  
	  //
	  
	  A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveFront();
        A.deleteFront();
        if (A.index() != -1) System.out.println("Fail test 1");
        A.moveBack();
        A.deleteFront();
        if (A.index() != 3) System.out.println("Fail test 2");
		
		//
		
		A.moveFront();
		for(int i = 0; i < A.length(); i++){
			System.out.println("index: " + A.index() + " value: " + A.get());
			A.moveNext();
		}
   }
}

// Output of this program:
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// false
// false
// true
// 1 2 3 4 5 -1 6 7 8 9 11 12 13 14 15 -2 16 17 18 19 20
// 21
// 0

