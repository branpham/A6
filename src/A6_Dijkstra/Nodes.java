package A6_Dijkstra;

import java.util.HashMap;
import java.util.HashSet;

public class Nodes implements Comparable<Nodes>{
	public long idNum;
	public String name, previous;
	public int inEdgeno;
	public double distance;

	HashSet<String> edgeIds = new HashSet<String>();
	HashMap<String, Edges> edges = new HashMap<String, Edges>();

	public Nodes(long idNum, String label) {
		this.idNum = idNum; 
		this.name = label;
	}
	
	public void setPrevious(String prev) {
		this.previous = prev;
	}
	
	public void setDistance(double d) {
		this.distance = d;
	}

	public String getName(){
		return name;
	}

	public int getInEdges(){
		return inEdgeno;
	}
	
	public HashMap<String,Edges> getEdges(){
		return edges;
	}
	
	public void setinEdges(int newEdges) {
		this.inEdgeno = newEdges;
	}
	
	public HashSet<String> getIds(){
		return edgeIds;
	}
	
	public long getIdNum() {
		return idNum;
	}
	
	public void setListEdges(HashMap<String, Edges> yah) {
		this.edges = yah;
	}
	
	public void increaseie() {
		inEdgeno++;
	}
	
	public void decreaseie() {
		inEdgeno--;
	}

	@Override
	public int compareTo(Nodes o) {
		int n = 0;
		// TODO Auto-generated method stub
		if(o.distance > this.distance) {
			n = -1;
		} else if(o.distance > this.distance) { 
			n = 1;
		}
		return n;
	}
}
