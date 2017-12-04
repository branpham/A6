package A6_Dijkstra;

public class Edges {
	public long idNum, weight;
	public String source, destination;
	
	
	public Edges(long idNum, long weight, String source, String destination){
		this.idNum = idNum;
		this.weight = weight;
		this.source = source;
		this.destination = destination;
		
	}
	
	public void setWeight(long newweight) {
		this.weight = newweight;
	}
	public void setSource(String nsource) {
		this.source = nsource;
	}
	public void setidNum(long newidNum) {
		this.idNum = newidNum;
	}
	public void setDestination(String ndest){
		this.destination = ndest;
	}
	public String getDestination() {
		return destination;
	}
	public String getSource() {
		return source;
	}

	public long getIdNum() {
		// TODO Auto-generated method stub
		return idNum;
	}
	
	
}
