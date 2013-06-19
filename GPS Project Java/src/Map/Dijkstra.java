package Map;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is to find the shortest path "Dijkstra"
 * 
 * @author Rated-R Coder: Rashid Darwish
 * 
 */
public class Dijkstra {
	private List<Edges> edges;
	private Set<Node> visittedNodes;
	private Set<Node> unVisitedNodes;
	private Map<Node, Node> pathes;
	private Map<Node, Double> distance;
	private DecimalFormat roundObj;

	/**
	 * Constructor
	 * 
	 * @param graph
	 */
	public Dijkstra(Graph graph) {
		this.edges = new ArrayList<Edges>(graph.getEdgesList());
		roundObj = new DecimalFormat("######0.00");
	}

	/**
	 * @return distance Map <Nodes, Double>
	 */
	public Map<Node, Double> getDistance() {
		return distance;
	}

	/**
	 * Run the searching for the shortest path
	 * @param source
	 */
	public void run(Node source) {
		visittedNodes = new HashSet<Node>();
		unVisitedNodes = new HashSet<Node>();
		distance = new HashMap<Node, Double>();
		pathes = new HashMap<Node, Node>();
		distance.put(source, 0.0);
		unVisitedNodes.add(source);
		while (unVisitedNodes.size() > 0) {
			Node node = getMinNodeDistance(unVisitedNodes);
			visittedNodes.add(node);
			unVisitedNodes.remove(node);
			minDistance(node);
		}
	}

	/**
	 * adds the minimum distance
	 * @param node
	 */
	private void minDistance(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(
						target,
						Double.parseDouble(roundObj.format(
								getShortestDistance(node)
								+ getDistance(node, target)).replace(
										",", ".")));
				pathes.put(target, node);
				unVisitedNodes.add(target);
			}
		}

	}

	/**
	 * gets the weight of the edge
	 * @param node
	 * @param target
	 * @return
	 */
	private Double getDistance(Node node, Node target) {
		for (Edges edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Errr");
	}

	/**
	 * gets the neigbours nodes
	 * @param node
	 * @return node
	 */
	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edges edge : edges) {
			if (edge.getSource().equals(node)
					&& !isVisittedNodes(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	/**
	 * returns minimum distance
	 * @param nodes
	 * @return node
	 */
	private Node getMinNodeDistance(Set<Node> nodes) {
		Node minNodeDistance = null;
		for (Node node : nodes) {
			if (minNodeDistance == null) {
				minNodeDistance = node;
			} else if (getShortestDistance(node) < getShortestDistance(minNodeDistance)) {
				minNodeDistance = node;
			}
		}
		return minNodeDistance;
	}

	/**
	 * check if node is visited
	 * @param node
	 * @return boolean
	 */
	private boolean isVisittedNodes(Node node) {
		return visittedNodes.contains(node);
	}

	/**
	 * returns shortest distance to the destination
	 * @param destination
	 * @return double
	 */
	public Double getShortestDistance(Node destination) {
		Double dist = distance.get(destination);
		if (dist == null) {
			return Double.MAX_VALUE;
		} else {
			return dist;
		}
	}

	/**
	 * returns the shortest path
	 * @param destination
	 * @return LinkedList<Nodes> the path
	 */
	public LinkedList<Node> getPathes(Node destination) {
		LinkedList<Node> path = new LinkedList<Node>();
		if (pathes.get(destination) == null) {
			return null;
		}
		path.add(destination);
		while (pathes.get(destination) != null) {
			destination = pathes.get(destination);
			path.add(destination);
		}
		Collections.reverse(path);
		return path;
	}
}
