package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//test comment another and another
public class DiGraphImpl implements DiGraph{

	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {
		nodeList.add(node);
		return true;
	}

	@Override
	public Boolean removeNode(GraphNode node) {
		if (nodeList.contains(node)) {
			nodeList.remove(node);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
			node.setValue(newNodeValue);
			return true;
	}

	@Override
	public String getNodeValue(GraphNode node) {
		return node.getValue();
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode == null || targetToNode == null)
			return false; // nodes don't exist, can't make the edge

		return targetFromNode.addNeighbor(targetToNode, weight);
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		return fromNode.removeNeighbor(toNode);
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		fromNode.getPaths().replace(toNode, newWeight);
		return true;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		return fromNode.getDistanceToNeighbor(toNode);
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		return node.getNeighbors();
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		if (fromNode.getNeighbors().contains(toNode) && toNode.getNeighbors().contains(fromNode)) {
			return true;
		}
		return false;
		
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (targetFromNode.getNeighbors().contains(targetToNode)) {
			return true;
		}
		
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(targetFromNode);
		
		HashMap<GraphNode, Boolean> visited = new HashMap<>();
		for (GraphNode e : nodeList) {
			visited.put(e, false);
		}
		visited.replace(targetFromNode, true);
		
		while (!queue.isEmpty()) {
			GraphNode element = queue.remove();
			List<GraphNode> neighbours = element.getNeighbors();
			
			for (int i = 0; i < neighbours.size(); i++) {
				GraphNode n = neighbours.get(i);
				if (n != null && !visited.get(n)){
					queue.add(n);
					visited.replace(n, true);
				}
				if (n.getValue().equals(targetToNode.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Boolean hasCycles() {
		for (GraphNode node : nodeList) {
	        if (nodeIsReachable(node, node)) {
	            return true;
	        }
	    }
	    return false;
	}

	@Override
	public List<GraphNode> getNodes() {
		return nodeList;
	}

	@Override
	public GraphNode getNode(String nodeValue) {
		for (GraphNode e : nodeList) {
			if (e.getValue().equals(nodeValue)) {
				return e;
			}
		}
		System.out.println("Node with nodeValue does not exist");
		return null;
	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (!nodeIsReachable(targetFromNode, targetToNode)) {
			return 0;
		}
		
		Queue<GraphNode> queue = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();
		Map<GraphNode, Integer> hops = new HashMap<>();
		
		queue.add(targetFromNode);
		hops.put(targetFromNode, 0);
		
		while (queue.peek() != null) {
			GraphNode thisNode = queue.poll();
			
			for (GraphNode thisNeighbor : thisNode.getNeighbors()) {
				if (!visitedNodes.contains(thisNeighbor)) {
					visitedNodes.add(thisNeighbor);
					queue.add(thisNeighbor);
					
					hops.put(thisNeighbor, hops.get(thisNode) + 1);
				}
				
				if (thisNeighbor.getValue().equals(targetToNode.getValue())) {
					return hops.get(thisNeighbor);				
					}
			}
		}
		return 0;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		
		if (!nodeIsReachable(targetFromNode, targetToNode)) {
			return 0;
		}
		
		Queue<GraphNode> queue = new LinkedList<>();
		Set<GraphNode> visitedNodes = new HashSet<>();
		Map<GraphNode, Integer> distance = new HashMap<>();
		
		queue.add(targetFromNode);
		distance.put(targetFromNode, 0);
		
		while (queue.peek() != null) {
			GraphNode thisNode = queue.poll();
			
			for (GraphNode thisNeighbor : thisNode.getNeighbors()) {
				if (!visitedNodes.contains(thisNeighbor)) {
					visitedNodes.add(thisNeighbor);
					queue.add(thisNeighbor);
					
					distance.put(thisNeighbor, distance.get(thisNode) + thisNode.getDistanceToNeighbor(thisNeighbor));
				}
				
				if (thisNeighbor.getValue().equals(targetToNode.getValue())) {
					return distance.get(thisNeighbor);				
					}
			}
		}
		return 0;
	}
	
	
	
}
