/**
 * Implementing the concept of parallel processing on matrix multiplication
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
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the dimension of the matrix:");
		int dm = reader.nextInt(); //getting dimension of the matrix
		/*** initialization ***/
		m1 = new int[dm][dm];
		m2 = new int[dm][dm];
		result = new int [dm][dm];
		/*** input ***/
		System.out.println("Enter the first matrix:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				try {
					m1[i][j]=reader.nextInt();
				}catch(Exception e) {}
			}
		}
		System.out.println("Enter the second matrix:");
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				try {
					m2[i][j]=reader.nextInt();
				}catch(Exception e) {}
			}
		}
		/*** matrix multiplication ***/
		for(int i=0; i<dm; i++) {
			for(int j=0; j<dm; j++) {
				for (int k=0; k<dm; k++) {
					try {
					new MatrixMultiplication(i, j, k).start(); //formula for multiplying two matrices
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
	}
}
