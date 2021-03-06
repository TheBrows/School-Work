import java.util.*;
import java.io.*;

public class VectorRunner
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        int selection;
        int quit = 2;
        
        double xa;
        double ya;
        double xb;
        double yb;
        double scalar;
        
        Vector a;
        Vector b;
        
        while( quit == 2 )
        {
            //User chose options
            System.out.println("Select an option by entering the " +
            "corresponding number");
            System.out.println("1. Add two vectors");
      	    System.out.println("2. Subtract two vectors");
            System.out.println("3. Get the magnitude of one vector");
            System.out.println("4. Get the scalar product of a vector and "
                    + "scalar");
            System.out.println("5. Get the dot product of two vectors");
            System.out.println("6. Get the angle between the two vectors");
	    
	    selection = keyboard.nextInt();
	    
	    //3, 4, and 2 need different set ups
            if( selection == 3 )
            {
                //takes in double value and puts it in new vector class
                System.out.println("Please enter the vector like so: x y");
                xa = keyboard.nextDouble();
                ya = keyboard.nextDouble();
                
                a = new Vector(xa,ya);
                
                //calls method of specified action in new vector class
                System.out.println("Result: " + a.magnitude());
            }
            else if( selection == 4 )
            {
                System.out.println("Please enter the vector like so: x y");
                xa = keyboard.nextDouble();
                ya = keyboard.nextDouble();
                
                a = new Vector(xa,ya);
                
                System.out.println("Please enter the scalar");
                
                scalar = keyboard.nextDouble();
                
                System.out.println("Result: " + a.scalarProduct(scalar));
            }
            else if( selection == 2 )
            {
                System.out.println("Please enter the vector you'd like " +
                    "subtracted from like so: x y");
                xa = keyboard.nextDouble();
                ya = keyboard.nextDouble();
                
                System.out.println("Please enter the vector you'd like to " +
                        "subtract with like so: x y");
                xb = keyboard.nextDouble();
                yb = keyboard.nextDouble();
                
                a = new Vector(xa,ya);
                b = new Vector(xb,yb); 
                
                System.out.println("Result: " + a.subtract(b));
            }
            else
            {
                System.out.println("Please enter the first vector like so:" +
                        " x y");
                xa = keyboard.nextDouble();
                ya = keyboard.nextDouble();
                
                System.out.println("Please enter the second vector like so:" +
                        " x y");
                xb = keyboard.nextDouble();
                yb = keyboard.nextDouble();
                
                a = new Vector(xa,ya);
                b = new Vector(xb,yb); 
                
                if( selection == 1 )
                {
                    System.out.println("Result: " + a.add(b));
                }
                if( selection == 5 )
                {
                    System.out.println("Result: " + a.dotProduct(b));
                }
                if( selection == 6 )
                {
                    System.out.println("Result: " + a.angle(b));
                }
            }
            
            System.out.println("Quit? 1 for Yes and 2 for No.");
            
            quit = keyboard.nextInt();
        }
    }
}
