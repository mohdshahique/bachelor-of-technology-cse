import java.rmi.*;
import java.util.Scanner;


public class MyClient{

public static void main(String args[]){
try{

Adder stub=(Adder) Naming.lookup("rmi://localhost:5000/Adder_remote");
//System.out.println(stub.add(34,82));
Scanner scan=new Scanner(System.in);

while(true){
System.out.println("Enter two numbers to add");
int x1=scan.nextInt();
int x2=scan.nextInt();

System.out.println("Sum="+stub.add(x1,x2));
}

}catch(Exception e){System.out.println(e);}
}

}
