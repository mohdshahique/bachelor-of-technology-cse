/**
 * Implementing the concept of state diagram
 *
 * @author ashra.
 *         Created Sep 26, 2019.
 */
import java.util.*;

public class StateDiagram {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the number of columns in the reservation table:");
		int n = reader.nextInt(); // getting number of columns in the reservation table
		System.out.println("Enter the forbidden latencies:");
		long fl = reader.nextLong(); // getting the forbidden latencies
		getCollisionVector(n, fl);
	}
	public static void getCollisionVector(int n, Long fl) {
		int C[] = new int [n-1]; // defining the length of the collision vector
		int rC[] = new int [n-1]; //reverse array for calculation
		int flLength = String.valueOf(fl).length();
		/*** Checking for the final collision vector ***/
		if(flLength <= C.length) {
			while(fl != 0) {
				if((fl%10) != 0)
					C[((int)(fl%10))-1] = 1;
				fl/=10;
			}
		}
		else
			System.out.println("Error! The number of forbidden latencies are more than the column length!");
		
		System.out.print("\nCollision Vector: ");
		for(int i=(C.length-1); i>=0; i--) 
			rC[(C.length-1)-i] = C[i];
		
		System.out.println(Arrays.toString(rC));
		/*** Checking for the state diagram ***/
		Arrays.setAll(C, i -> 0);
		/*** Performing the right shift on CV ***/
		System.out.print("\nCV 1-bit right shifted: ");
		for(int i=0; i<C.length; i++) {
			if(i==(C.length-1))
				C[i] = rC[0];
			else
				C[i] = rC[i+1];
		}
		System.out.println(Arrays.toString(C));
		int sd[] = new int [n-1];
		System.out.print("\nState Diagram: ");
		for(int i=0; i<sd.length; i++) {
			if(C[i]==1 || rC[i]==1)		//condition for getting the state diagram
				sd[i] = 1;
		}
		System.out.println(Arrays.toString(sd));
	}
}
