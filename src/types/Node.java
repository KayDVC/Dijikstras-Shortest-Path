package types;
import java.util.ArrayList;

public class Node {

	public Node upper, lower, next, prev;
	public Integer[] coords;
	public Character val;
	public String move;
	// The current shortest path to Tron.
	public Integer distance;
	// Shortest path to Tron in order.
	public ArrayList <Node> path;

	public Node(Integer []c, char v){
		upper = lower = next = prev = null;
		coords = c;
		val = Character.valueOf(v);
		move = null;
		path = new ArrayList<>();
		distance = Integer.MAX_VALUE;

	}

	public void checkDistance(Integer prevDistance, Node prev){
		Integer temp = prevDistance + 1;

		// take the shorter of two distances and set that to the node's
		// distance from Tron or other Node
		if (temp < distance){

			distance = temp;
			path.add(prev);

			for (Node n : prev.path){
				if (!path.contains(n)){
					path.add(n);
				}
			}
		}
	}

}

