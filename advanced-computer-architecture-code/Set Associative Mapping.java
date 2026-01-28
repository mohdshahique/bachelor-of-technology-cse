/**
 * Implementing the concept of set associative mapping
 *
 * @author ashra.
 *         Created Oct 10, 2019.
 */
import java.util.*;
import java.math.*;

public class SetAssociativeMapping {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the Total Memory Size in bits:");
		int tM = reader.nextInt(); // getting size of Main memory
		System.out.println("Enter the number of blocks:");
		int nB = reader.nextInt(); // getting the number of blocks in the cache memory
		System.out.println("Enter the number of sets in cache memory:");
		int nSC = reader.nextInt(); // getting the number of sets in cache
		getAssociativeMappedValue(tM, nB, nSC);
	}
	public static void getAssociativeMappedValue(int tM, int nB, int nSC) {
		int block = (int)(Math.log(nB)/Math.log(2));
		int set = (int)(Math.log(nSC)/Math.log(2));
		int tag = tM - (block+set);
		System.out.println("Word: "+block+"\nSet: "+set+"\nTag: "+tag);
	}
}
