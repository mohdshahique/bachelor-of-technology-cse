/**
 * Implementing the concept of speed-up factor
 *
 * @author ashra.
 *         Created Oct 2, 2019.
 */
import java.util.*;

public class SpeedUpFactor {
	public static void main(String args[]) {
		Scanner reader = new Scanner(System.in);
		System.out.println("1: SpeedUp by Performance \n2: SpeedUp by Execution time \nChoose one of the options to proceed further!");
		int choice = reader.nextInt();
		double speedup = 0.0;
		switch(choice) {
			case 1:
				System.out.println("Enter the value of performance for entire task using enhancement:");
				int pe = reader.nextInt();
				System.out.println("Enter the value of performance for entire task without enhancement:");
				int pw = reader.nextInt();
				speedup = (double)pe/pw; //formula for speedup factor
				break;
			case 2:
				System.out.println("Enter the value of execution time in seconds without using enhancement:");
				int ew = reader.nextInt();
				System.out.println("Enter the value of execution time in seconds using enhancement:");
				int ee = reader.nextInt();
				speedup = (double)ew/ee;  //formula for speedup factor
				break;
			default:
				System.out.println("Invalid choice!");
		}
		System.out.println("Speedup Factor: "+speedup); //speedup enhanced
		
		/*** Overall Speedup ***/
		System.out.println("Enter the value for fraction enhanced:");
		double fraction = reader.nextDouble();
		double overallspeedup = 1/((1-fraction)+(fraction/speedup));//formula for overall speedup
		System.out.println("Overall Speedup: "+overallspeedup);
	}
}
