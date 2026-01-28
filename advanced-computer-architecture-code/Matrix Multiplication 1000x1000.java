/**
 * Implementing the concept of parallel processing on matrix multiplication on 1000x1000 matrix
 *
 * @author ashra.
 *         Created Sep 22, 2019.
 */
import java.util.*;
import java.io.*;

public class MatrixMultiplication extends Thread {
	/*** declaration ***/
	private int i,j,k;
	static int m1[][];
	static int m2[][];
	static int result[][];
	/*** multi-threading ***/
	public MatrixMultiplication ( int i, int j, int k) {
		this.i = i; 
		this.j = j; 
		this.k = k;
	}
	public void run() {
		result[i][j] += m1[i][k]*m2[k][j];
	}
	public static void main(String args[]) {
		long start = System.currentTimeMillis(); //timestamp before execution
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the dimension of the matrix:");
		int dm = reader.nextInt(); //getting dimension of the matrix
		
		/*** initialization ***/
		m1 = new int[dm][dm];
		m2 = new int[dm][dm];
		result = new int [dm][dm];
		int max = 10;
		int min = 1;
		int range = max - min + 1;
		
		/*** input ***/
		//System.out.println("First matrix:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				try {
					m1[i][j]=(int)(Math.random()*range)+min;
				}catch(Exception e) {}
			}
		}
		//System.out.println("Second matrix:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				try {
					m2[i][j]=(int)(Math.random()*range)+min;
				}catch(Exception e) {}
			}
		}
		//the output matrix 1
		System.out.println("Matrix 1:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) 
				System.out.print(m1[i][j]+" ");
			System.out.println();
		}
		//the output matrix 2
		System.out.println("Matrix 2:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) 
				System.out.print(m2[i][j]+" ");
			System.out.println();
		}
		
		
		/*** matrix multiplication ***/
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				for (int k=0; k<dm; k++) {
					try {
					new MatrixMultiplication(i, j, k).start(); //formula for multiplying two matrices
					Thread.sleep(120);
					}catch(Exception e) {}
				}
			}
		}
		/*** Output ***/
		System.out.println("The matrix after parallel processing multiplication:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) 
				System.out.print(result[i][j]+" ");
			System.out.println();
		}
		long end = System.currentTimeMillis(); //timestamp after execution
		float sec = (end - start) / 1000F; 
		System.out.println(sec + " seconds");
	}
}
