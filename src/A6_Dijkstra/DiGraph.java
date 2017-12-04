package A6_Dijkstra;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class DiGraph implements DiGraph_Interface {
	Map<String, Nodes> nodes = new HashMap<String, Nodes>();
	Map<Nodes, List> nodesnedges = new HashMap<Nodes, List>();
	HashMap<String, String> ids = new HashMap<String, String>();
	public long edgect = 0;
	public long nodect = 0;
	boolean[] markV = new boolean[(int) nodect];
	Map<Integer, String> vs = new HashMap<Integer, String>();
	Map<Nodes, Boolean> known = new HashMap<Nodes, Boolean>();



	int ed = (int) edgect;

	// in here go all your data and methods for the graph
	// and the topo sort operation

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(idNum < 0 || nodes.containsKey(label) || ids.containsKey(Long.toString(idNum))) {
			return false;
		} else {
			nodes.put(label, new Nodes(idNum, label));

			ids.put(Long.toString(idNum),Long.toString(idNum));
			vs.put((int) nodect, label);
			nodect++;

			return true;
		}
	}

	@Override
	public boolean addEdge(long idNum, String source, String destination, long weight, String eLabel) {
		// check if other starts/destinations exist
		// add into edge map
		if(idNum < 0 || !nodes.containsKey(source) || !nodes.containsKey(destination)) {
			return false;
		} else if(nodes.get(source).getEdges().containsKey(destination) || nodes.get(source).getIds().contains(Long.toString(idNum))) {
			return false;
		} else {
			Edges newE = new Edges(idNum, weight, source, destination);
			nodes.get(source).getEdges().put(destination, newE);
			nodes.get(source).getIds().add(Long.toString(idNum));
			nodes.get(destination).increaseie();
			edgect++;
			return true;
		}
	}

	@Override
	public boolean delEdge(String source, String destination) {
		if(nodes.containsKey(source) && nodes.containsKey(destination)) {
			if(nodes.get(source).getEdges().containsKey(destination)) {
				Long removethis = nodes.get(source).getEdges().get(destination).getIdNum();
				nodes.get(source).getIds().remove(Long.toString(removethis));
				nodes.get(source).getEdges().remove(destination);
				nodes.get(destination).decreaseie();
				edgect--;
				return true;
			}
		} 
		return false;
	}

	@Override
	public boolean delNode(String nlabel) {
		// check if the node exists by its label
		if (nodes.containsKey(nlabel)) {
			// retrieve edges this node is connected to
			for(String destination: nodes.get(nlabel).getEdges().keySet()) {
				nodes.get(destination).decreaseie();
			}
			// decrement the numNodes
			int temp = nodes.get(nlabel).getEdges().size();
			edgect -= temp;
			ids.remove(Long.toString(nodes.get(nlabel).getIdNum()));
			// delete the node from the hashmap
			nodes.remove(nlabel);
			nodect--;
			return true;
		}
		return false;
	}

	@Override
	public long numNodes() {
		return nodect;
	}

	@Override
	public long numEdges() {
		return edgect;
	}

	@Override
	public String[] topoSort() {
		// traverse and find if there is a child vertex.
		// after each traverse add to visited list
		// if completely explored add to stack.
		// add to stack if no child
		Queue<Nodes> q = new LinkedList<Nodes>();
		String[] names = new String[(int) nodect];
		int zero = 0;

		for(Nodes n : nodes.values()){
			if(n.getInEdges() == zero){
				q.add(n);
			}
		}

		int ct = 0;


		for(ct = 0; !q.isEmpty(); ct++) {
			Nodes current = q.remove();
			names [ct] = current.getName();
			delNode(current.getName());

			for(Edges edgez:current.getEdges().values()){
				if(nodes.get(edgez.getDestination()).getInEdges() == zero){
					q.add(nodes.get(edgez.getDestination()));
				}
			}
		}

		if(ct != names.length){
			return null;
		}
		return names ;


		// rest of your code to implement the various operations
	}


	public ShortestPathInfo[] shortestPath(String label) {
		//		ShortestPathInfo[] shortestPath = new ShortestPathInfo[(int) nodect];
		//		PriorityQueue<Nodes> PriorityQueue = new PriorityQueue<Nodes>();
		//		//		Map<String, Edges> spiedges = new HashMap<String, Edges>();
		//		//		Map<String, Edges> added = new HashMap<String, Edges>();
		//		
		//		
		//		Nodes n;
		//		int i = 1;
		//		Long dist = (long) 0;
		//		Long min = Long.MAX_VALUE;
		//		
		//		Long random = (long) 500;
		//		
		//		
		// initializes known node-boolean set

		for(String w: nodes.keySet()) {			
			known.put(nodes.get(w), false);			
		}


		// get the startVertex
		Nodes startVertex = nodes.get(label);
		ShortestPathInfo[] shortestPathArray = new ShortestPathInfo[nodes.size()];

		int i = 0;
		this.dijkstrasShortestPath(startVertex, startVertex);
		for (HashMap.Entry<String, Nodes> entry : nodes.entrySet()) {
			Nodes currentVertex = entry.getValue();
			long totalDistance;
			if (currentVertex.distance == Double.POSITIVE_INFINITY) {
				totalDistance = -1;
			} else {
				totalDistance = (long)currentVertex.distance;
			}
			shortestPathArray[i] = new ShortestPathInfo(currentVertex.name, totalDistance);
			i++;
		}
		return shortestPathArray;
	}




	//		// put source in first index of spi array
	//		shortestPath[0] = new ShortestPathInfo(source, 0);
	//		PriorityQueue.add(nodes.get(source));

	public void dijkstrasShortestPath(Nodes source, Nodes destination) {


		// initialize the start vertex
		source.setDistance(0);
		known.replace(source, false);
		source.setPrevious(null);

		// create priority queue
		PriorityQueue<Nodes> pq = new PriorityQueue<>();
		pq.add(source);

		// for each vertex in the DiGraph, if the vertex is not the startVertex then set distance to infinity
		// and set previous to null;
		for (Map.Entry<String, Nodes> entry : nodes.entrySet()) {
			Nodes currentNode = entry.getValue();
			if (currentNode.getIdNum() != source.getIdNum()) {
				currentNode.setDistance(Double.POSITIVE_INFINITY);
				currentNode.setPrevious(null);
				known.replace(currentNode, false);;
			}
		}

		while(!pq.isEmpty()) {

			Nodes currentNode = pq.poll();


			if(!known.get(currentNode)) {
				known.replace(currentNode, true);

				for (ConcurrentHashMap.Entry<String, Edges> entry : currentNode.getEdges().entrySet()) {
					Edges adjEdge = entry.getValue();
					Nodes adjNode = nodes.get(entry.getKey());

					Double minDistance = currentNode.distance + adjEdge.weight;
					if (adjNode.distance > minDistance) {
						adjNode.setDistance(minDistance);
						adjNode.setPrevious(currentNode.name);


						pq.add(adjNode);
					}
				}
			}
		}
		long finalDistance;
		if (destination.distance== Double.POSITIVE_INFINITY) {
			finalDistance = -1;
		} else {
			finalDistance = (long)destination.distance;
		}
		ShortestPathInfo shortestPath = new ShortestPathInfo(destination.name, finalDistance);
	}


}



