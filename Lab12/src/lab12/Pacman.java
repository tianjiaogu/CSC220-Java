package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		while(end != null & end.content!='S') {
			end.content = '.';
			end = end.parent;// TODO for assignment12
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			for(int i = 0; i < height; i++) {
				for(int j=0; j< width; j++) {
					output.write(maze[i][j].content);
				}
			output.write("\n");
			}
			output.close();
						
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content;
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			
			String[] dim = input.readLine().split(" ");
			 height = Integer.parseInt(dim[0]); 
			 width = Integer.parseInt(dim[1]); 
			
			maze = new Node[height][width];
			String currentLine = input.readLine();
			int row =0;
			while(currentLine != null) {
				
				for(int i=0; i<width; i++) {
					char c = currentLine.charAt(i); 
					Node s = new Node(row,i,c);
					maze[row][i] = s;
					if (c=='S') {
						startX = row;
						startY = i;
					}
				}
				currentLine = input.readLine();		
				row = row+1;
			} 
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		
		Node north, south, east, west;
		ArrayList<Node> neighbor = new ArrayList<Node>();
		
		
		if(inMaze(currentNode.row-1,maze.length)) {
			north = maze[currentNode.row-1][currentNode.col];
			if (!north.isVisited() && !north.isWall()) {
				north.parent = currentNode;
				north.visited = true;
				neighbor.add(north);  
			}
		}
		
		if(inMaze(currentNode.row+1,maze.length)) {
			south = maze[currentNode.row+1][currentNode.col];
			if (!south.isVisited() && !south.isWall()) {
				south.parent = currentNode;
				south.visited = true;
				neighbor.add(south);  
			}
		}
		
		
		if(inMaze(currentNode.row,maze.length)) {
			east = maze[currentNode.row][currentNode.col+1];
			if (!east.isVisited() && !east.isWall()) {
				east.parent=currentNode;
				east.visited = true;
				neighbor.add(east);  
			}
		}
		

		if(inMaze(currentNode.row,maze.length)) {
			west = maze[currentNode.row][currentNode.col-1];
			if (west.isVisited() && !west.isWall()) {
				west.parent=currentNode;
				west.visited = true;
				neighbor.add(west);  
			}
		}
		
		return neighbor;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		LinkedList<Node> queue = new LinkedList<Node>();
		
		Node StartNode = maze[startX][startY];
		
		queue.add(StartNode);
		while(queue.size()!=0) {
			Node curr = queue.poll();
			
		if(curr.content == 'G') {
			backtrack(curr);
			writeOutput();
			return;
		}
		else {
			ArrayList<Node> neighbor = getNeighbors(StartNode);
			for (Node next: neighbor) {
				queue.add(next);
			}
		}
		
		}
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Stack<Node> stack = new Stack<Node>();
		
		Node StartNode = maze[startX][startY];
		
		stack.push(StartNode);
		while(stack.size()!=0) {
			Node curr = stack.pop();
			
		if(curr.content == 'G') {
			backtrack(curr);
			writeOutput();
			return;
		}
		else {
			ArrayList<Node> neighbor = getNeighbors(StartNode);
			for (Node next: neighbor) {
				stack.push(next);
			}
		}
		
		}
	}

	public static void main(String[] args){
		
	Pacman lib3 = new Pacman("classic.txt", "classic_output.txt");    
	    
	    //System.out.println(lib3.toString());
	    
	    lib3.writeOutput(); 
	    
	 }
	
}
