/**
 * Implementation of view serializability
 *
 * @author ashra.
 *         Created Apr 15, 2020.
 */
public class viewSerializability {
	public static void main(String[] args) { 
		String schedule = "R2(C);R2(B);W2(B);R3(B);R3(C);R1(A);W1(A);W3(B);W3(C);R2(A);R1(B);W1(B);W2(A)"; 
				boolean result = isConflictSerializable(schedule);
				if (result) { 
					System.out.println("As the schedule is not conflict serializable.");
					System.out.println("Therefore the schedule may/may not be view serializable."); 
				}
				else { 
					System.out.println("As the schedule is conflict serializable."); 
					System.out.println("Therefore the schedule is view serializable."); 
				}
	}
	public static boolean isConflictSerializable(String str) { 
		String[] tokenArray = str.split(";");
		Graph graph = new Graph(5);
		int i, j, len, source, destination;
		char opNum1, operation1, data1;
		char opNum2, operation2, data2;
		len = tokenArray.length;
		System.out.println("Given Schedule : " + str + "\n"); 
		System.out.println("Conflicts  &   Precedence Graph Edge"); 
		for (i = 0; i < len; i++) { 
			for (j = i; j < len; j++) { 
				opNum1 = tokenArray[i].charAt(1);
				opNum2 = tokenArray[j].charAt(1); 
				operation1 = Character.toLowerCase(tokenArray[i].charAt(0)); 
				operation2 = Character.toLowerCase(tokenArray[j].charAt(0)); 
				data1 = tokenArray[i].charAt(3); 
				data2 = tokenArray[j].charAt(3);
				if (opNum1 != opNum2 && data1 == data2 && (operation1 == 'w' || operation2 == 'w')) {   
					source = Integer.parseInt(String.valueOf(opNum1));
					destination = Integer.parseInt(String.valueOf(opNum2));
					graph.addEdge(source, destination);
					System.out.print(tokenArray[i] + "  " + tokenArray[j] + "   ");
					System.out.println(opNum1 + " -> " + opNum2); 
				}
			}
		}
		return graph.isCyclic() ? true : false; 
	}
}
