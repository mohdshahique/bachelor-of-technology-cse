/**
 * Implementing the concept of associative mapping
 *
 * @author ashra.
 *         Created Oct 10, 2019.
 */
import java.util.*;
import java.math.*;

public class AssociativeMapping {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the Total Memory Size in bits");
		int tM = reader.nextInt(); // getting size of Main memory
		System.out.println("Enter the number of blocks:");
		int nB = reader.nextInt(); // getting the number of blocks in the cache memory
		getAssociativeMappedValue(tM, nB);
	}
	public static void getAssociativeMappedValue(int tM, int nB) {
		int block = (int)(Math.log(nB)/Math.log(2));
		int tag = tM - block;
		System.out.println("Word: "+block+"\nTag: "+tag);
	}
}
