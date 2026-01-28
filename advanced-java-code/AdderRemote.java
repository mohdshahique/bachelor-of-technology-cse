import java.rmi.*;
import java.rmi.server.*;

public class AdderRemote extends UnicastRemoteObject implements Adder{

AdderRemote()throws RemoteException{
super();
}

public int add(int x,int y){
System.out.println("Parameters recieved for addition are "+x
+" and "+y);
System.out.println("Returning back sum="+(x+y));
return x+y;
}

}
