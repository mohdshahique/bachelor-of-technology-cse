/**
 * Implementation of conflict serializability
 *
 * @author ashra.
 *         Created Apr 15, 2020.
 */
import java.util.ArrayList; 
import java.util.LinkedList; 
import java.util.List; 

public class conflictSerializability {
	public static void main(final String[] args) { 
		final String schedule = "R2(A);W2(A);R3(C);W2(B);W3(A);W3(C);R1(A);R1(B);W1(A);W1(B)";
		final boolean result = isConflictSerializable(schedule);
		if (result) { 
			System.out.println("Cycle found in the Precedence Graph."); 
			System.out.println("Therefore the schedule is not conflict serializable."); 
		}
		else { 
			System.out.println("Cycle not found in the Precedence Graph."); 
			System.out.println("Therefore the schedule is conflict serializable."); 
		}
	}
	public static boolean isConflictSerializable(final String str) { 
		final String[] tokenArray = str.split(";"); 
		final Graph graph = new Graph(5); 
		int i, j, len, source, destination; 
		char opNum1, operation1, data1; 
		char opNum2, operation2, data2; 
		len = tokenArray.length; 
		System.out.println("Given Schedule : " + str + "\n"); 
		System.out.println("Conflicts And  Precedence Graph Edge"); 
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

class Graph { 
	private final int V; 
	private final List<List> adj; 
	public Graph(final int V) { 
		this.V = V; 
		adj = new ArrayList<>(V);

		for (int i = 0; i < V; i++)
			adj.add(new LinkedList());
	}
	public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) { 
		if (recStack[i]) 
			return true;
		if (visited[i])
			return false;
		visited[i] = true; 
		recStack[i] = true; 
		final List<Integer> children = adj.get(i);
		for (final Integer c : children) 
			if (isCyclicUtil(c, visited, recStack))
				return true;
		recStack[i] = false; 
		return false; 
	}
	public void addEdge(final int source, final int dest) { 
		adj.get(source).add(dest); 
	}
	public boolean isCyclic() { 
		final boolean[] visited = new boolean[V]; 
		final boolean[] recStack = new boolean[V]; 
		for (int i = 0; i < V; i++) 
			if (isCyclicUtil(i, visited, recStack)) {
				return true;
			}
		return false; 
	}
}