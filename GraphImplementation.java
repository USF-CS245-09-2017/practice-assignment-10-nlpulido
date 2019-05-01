import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class GraphImplementation implements Graph {
	/*
	* A two dimensional array for matrix representation
	* and a size of vertices
	*/
	public int[][] adjMatrix;
	public int size;

	/*
	* A constructor which creates a two dimensional array with the size of 
	* the amount of vertices. Also sets the size variable to be the amount of vertices.
	*/
	public GraphImplementation(int vertices){
		adjMatrix = new int[vertices][vertices];
		size = vertices;
	}

	/*
	* This function adds an edge between two vertices, src and target
	*/
	public void addEdge(int src, int tar){
		adjMatrix[src][tar] = 1;
	}

	/*
	* This function returns an array of vertex ID's that contain the neighbors of the parameter entered vertex.
	*/
	public int[] neighbors(int vertex){
		int tempSize = 0;
		for (int i = 0; i < size; i++){
			if (adjMatrix[vertex][i] > 0){
				tempSize++;
			}
		}

		int[] neighbors = new int[tempSize];

		for (int i = 0, j = 0; i < size; i++){
			if (adjMatrix[vertex][i] > 0){
				neighbors[j++] = i;
			}
		}
		return neighbors;
	}

	/*
	* Prints to console an ordering of vertices in which for every edge
	* v1 to v2, v1 comes before v2.
	*/
	public List<Integer> topologicalSort(){
		List<Integer> schedule = new LinkedList<Integer>();
		int incident[] = new int[size];

		/* initialize all vertices as not visited */
		Arrays.fill(incident, 0);

		/* column wise add each row to the visited array */
		for (int j = 0; j < size; j++){
			for (int k = 0; k < size; k++){
				incident[k] += adjMatrix[j][k]; 
			}
		}

		/* iterate through the visited array and check if it equals to zero */
		for (int l = 0; l < size; l++){
			for (int m = 0; m < size; m++){

				/* if the index == 0, subtract the neighbors of the index by 1 in the incident array */
				if (incident[m] == 0){
					int[] neighbors = this.neighbors(m);
					for(int n = 0; n < neighbors.length; n++){
						incident[neighbors[n]] -= 1;
					}

					/* add the index to the schedule linked list */
					schedule.add(m);

					/* set it to -1 so it doesn't get checked again */
					incident[m] = -1;
				}
			}
		}

		return schedule;

	}
}