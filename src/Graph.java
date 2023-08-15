
import java.util.*;

import types.Node;

public class Graph {

	// constants
	final int X = 0;
	final int Y = 1;

	// member vars
	ArrayList<Node> board;
	ArrayList<Node> bugs;
	Integer rows, columns;
	Node tron;

	// constructor
	public Graph(){
		board = new ArrayList<>();
		bugs = new ArrayList<>();
		rows = columns = 0 ;
	}

	// Getters & Setters
	public void setTron(Node tron) {
		this.tron = tron;
	}

	public Node getTron() {
		return tron;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public Integer getRows() {
		return rows;
	}

	// Sorts bug in ascending alphabetical order using "check behind" algorithm.
	public void sortBugs(){

		bugs.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.val.compareTo(o2.val);
			}
		});

		for (Node bug: bugs){

			String s = "";
			if (bug.coords[Y] > tron.coords[Y]){
				s+='u';
			}else if (bug.coords[Y] < tron.coords[Y]){
				s+='d';
			}

			if (bug.coords[X] > tron.coords[X]){
				s+='l';
			}else if (bug.coords[X] < tron.coords[X]){
				s+='r';
			}

			bug.move = s;
		}
	}

	// Print bug name, distance, initial position, and shortest path to Tron.
	public void printBugs(){
		sortBugs();

		System.out.println("\n\n" + String.format("%-7s", "Bugs") + String.format("%-5s", "Move") + " " + String.format("%-10s" , "Distance") + "| Path To Tron" + "\n" + "-".repeat(37));
		for (Node bug : bugs){
			System.out.print(String.format("%-5s", ("👾 " + bug.val)) + ": " + String.format("%-5s", bug.move) + " " + String.format("%-10d" , bug.distance) +
					"| (" + bug.coords[X] + ", " + bug.coords[Y] + ")");

			for (Node pos : bug.path){
				
				System.out.print(" (" + pos.coords[X] + ", " + pos.coords[Y] + ")" + (pos == bug.path.get(bug.path.size() - 1) ? "" : ",") );
			}

			System.out.println();
		}

	}


	public void printBoard(){

		System.out.println("\nGame board: \n" + "-".repeat(columns+5)) ;

		//tempRow to be used to differentiate between rows
		int tempRow = -1;

		System.out.print("\n  ");

		for (int i=0; i<columns; i++){
			System.out.print(i);
		}

		for (Node n: board){
			Integer nRow = n.coords[Y];

			// only evaluates to true on the first position of each row aka first
			// "#" node of row
			if (nRow>tempRow){
				System.out.print("\n" + nRow + " " +n.val);
				tempRow++;
			}
			// if not first "#" of row, just append to console
			else{
				System.out.print(n.val);
			}

		}

		}

	public void startGame(){		

		getMoveFromPlayer();

		printBoard();

		shortestPath();
	}

	// Attempt to move tron. Recurse until successful.
	public void getMoveFromPlayer(){
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nPlease enter your move [u(p), d(own), l(eft), or r(ight)]: ");

		String choice = input.next().toLowerCase();

		// loop when invalid 
		while (!(choice.equals("u") || choice.equals("r") || choice.equals("l") || choice.equals("d")) 
		|| !moveTron(choice) )
		{
			System.out.println("\tInvalid entry! Try again.");

			System.out.print("\nPlease enter your move [u(p), d(own), l(eft), or r(ight)]: ");

			choice = input.next().toLowerCase();
		}

		input.close();

	}

	// checks if move can be done; Allows move as long as it's not into a barrier.
	public boolean moveTron(String choice){

		boolean validMove = true;
		String dir = "";

		switch (choice) {
			case "l" -> {
				dir = "left" ;
				if (tron.prev.val != ' ') {
					validMove = false;
					break;
				}
				tron.val = 32;
				setTron(tron.prev);
				tron.val = Character.valueOf('T');
				break;
			}
			case "r" -> {
				dir = "right" ;
				if (tron.next.val != ' ') {
					validMove = false;
					break;
				}
				tron.val = 32;
				setTron(tron.next);
				tron.val = Character.valueOf('T');
				break;
			}
			case "u" -> {
				dir = "up" ;
				if (tron.upper.val != ' ') {
					validMove = false;
					break;
				}
				tron.val = 32;
				setTron(tron.upper);
				tron.val = Character.valueOf('T');
				break;
			}
			case "d" -> {
				dir = "down" ;
				if (tron.lower.val != ' ') {
					validMove = false;
					break;
				}
				tron.val = 32;
				setTron(tron.lower);
				tron.val = Character.valueOf('T');
				break;
			}
		}

		System.out.println("\n\t" + (validMove ? "✅ Moved" : "❌ Failed to move") + " Tron " + dir + "\n");
		return validMove;
	}

	// Implements Dijkstra's algorithm by adding the shortest distance from
	// Tron to every other tile on the board excluding barrier
	public void shortestPath(){

		tron.distance = Integer.valueOf(0);
		Node curr = tron;
		ArrayList<Node> unvisited = board;
		Queue<Node> q = new LinkedList<Node>();

		q.add(curr);

		while(!q.isEmpty()) {

			for (Node adj : new ArrayList<Node>(Arrays.asList(curr.upper, curr.lower, curr.next, curr.prev))){

				// Check if Node is in unvisited list.
				if (adj != null && unvisited.contains(adj) && adj.val != '#'){
					adj.checkDistance(curr.distance, curr);
					q.add(adj);
				}
				// Remove from unvisited list.
				else{
					unvisited.remove(adj);
				}
			}

			// remove curr node from list of nodes to be explored and get new
			// node from queue
			q.remove();
			unvisited.remove(curr);
			curr = q.peek();
		}

	}


}



