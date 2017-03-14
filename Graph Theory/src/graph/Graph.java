package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph {
	protected ArrayList<Graph> history = new ArrayList<>();
	protected ArrayList<Edge> edges = new ArrayList<>();
	protected ArrayList<Vertex> vertices = new ArrayList<>();
	private boolean bipartite = true;
	private HamiltonianCycle hamCycle;
	public Vertex addVertex(int x, int y) {
		Vertex vert = new Vertex(x, y, this);
		vertices.add(vert);
		return vert;
	}
	public Edge addEdge(Vertex v, Vertex v2) {
		Edge edge = new Edge(v, v2, this);
		edges.add(edge);
		return edge;
	}
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public Vertex getVertex(int vertNum) {
		if (vertNum < 1) return null;
		return vertices.get((vertNum-1));
	}
	public Edge getEdge(int edgeNum) {
		if (edgeNum < 1) return null;
		return edges.get(edgeNum);
	}
	public void removeEdge(Edge edge) {
		//edge = null;
		edge.getVertex1().removeEdge(edge);
		edge.getVertex2().removeEdge(edge);
		edges.remove(edge);
		//addEdge(null, null);
	}
	public boolean isConnected() {
		return (getComponentSets() != null && getComponentSets().size() == 1);
	}
	public void removeVertex(Vertex vertex) {
		int size = vertex.getEdges().size();
		for (int i = 0; i < size; i++) {
			if (vertex.getEdges().size() < 1) break;
			removeEdge(vertex.getEdges().get(0));
		}
		vertices.remove(vertex);
	}
	public ArrayList<Vertex> getIsolatedVertices() {
		ArrayList<Vertex> isolated = new ArrayList<>();
		for (Vertex v : vertices) {
			if (v.isIsolated()) isolated.add(v);
		}
		return isolated;
	}
	public int[][] getAdjacencyMatrix() {
		int[][] matrix = new int[vertices.size()][vertices.size()];
		for (int i = 0; i < matrix.length; i++) {
			Vertex vertex = vertices.get(i);
			for (int j = 0; j < matrix[i].length; j++) {
				Vertex adjacent = vertices.get(j);
				for (Edge e : adjacent.getEdges()) {
					if (e.isIncidentTo(vertex) && adjacent != vertex) {
						matrix[i][j] += 1;
					}
				}
				if (adjacent == vertex && adjacent.isAdjacentTo(vertex)) {
					matrix[i][j] += 2;
				}
			}
		}
		return matrix;
	}
	public boolean isBipartite() {
		return getBipartiteness() != null;
	}
//	public Graph clone() {
//		Graph graph = new Graph();
//		graph.edges = (ArrayList<Edge>) edges.clone();
//		graph.vertices = (ArrayList<Vertex>) vertices.clone();
//		for (Edge e : graph.edges) {
//			e.graph = graph;
//			if (e != null && e.getVertex1().getNumber() < graph.vertices.size()-1 && e.getVertex2().getNumber() < graph.vertices.size()-1) {
//				e.setVertex1(graph.vertices.get(e.getVertex1().getNumber()));
//				e.setVertex2(graph.vertices.get(e.getVertex2().getNumber()));
//			}
//		}
//		for (Vertex v : graph.vertices) {
//			v.graph = graph;
//		}
//		return graph;
//	}
	public ArrayList<ArrayList<Vertex>> getBipartiteness() {
		if (getComponentSets().size() == 0) return null;
		for (ArrayList<Vertex> component : getComponentSets()) {
			if (component.size()%2 == 1) return null;
		}
		
		return null;
	}
	public ArrayList<Vertex> getComponentSet(Vertex v, ArrayList<Vertex> ignores) {
		ArrayList<Vertex> component = DepthFirstSearch(v, ignores, "None");
		return component;
	}
	public ArrayList<ArrayList<Vertex>> getComponentSets() {
		return getComponentSets(vertices, new ArrayList<Vertex>());
	}
	public ArrayList<ArrayList<Vertex>> getComponentSets(ArrayList<Vertex> verts, ArrayList<Vertex> ignores) {
		ArrayList<ArrayList<Vertex>> components = new ArrayList<>();
		if (verts.size() <= 0) return components;
		ArrayList<Vertex> component = getComponentSet(verts.get(0), ignores);
		components.add(component);
		if (component.size() < verts.size()) {
			ArrayList<Vertex> vertCopy = (ArrayList<Vertex>) verts.clone();
			vertCopy.removeAll(component);
			for (ArrayList<Vertex> vertSet : getComponentSets(vertCopy, ignores)) {
				components.add(vertSet);
			}
		}
		return components;
	}
	public ArrayList<ArrayList<Vertex>> getBipartiteSets() {
		if (vertices.size() == 0) return new ArrayList<ArrayList<Vertex>>();
		ArrayList<ArrayList<Vertex>> sets = new ArrayList<>();
		sets.add(new ArrayList<Vertex>());
		sets.add(new ArrayList<Vertex>());
		bipartite = true;
		for (ArrayList<Vertex> component : getComponentSets()) {
			if (component.size() != 1) {
				if (!getBipartiteSet(component.get(0), sets, 1)) break;
			} else {
				sets.get(0).add(component.get(0));
			}
		}
		if (sets.get(1).size() == 0) sets.remove(1);
		return bipartite ? sets : new ArrayList<ArrayList<Vertex>>();
	}
	private boolean getBipartiteSet(Vertex from, ArrayList<ArrayList<Vertex>> sets, int set) {
		if (vertices.size() > 0) {
			int opposite = set == 1 ? 0 : 1;
			for (Vertex v : from.getAdjacentVertices()) {
				if (!bipartite) break;
				if (sets.get(opposite).contains(v)) {
					bipartite = false;
					break;
				};
				if (!sets.get(0).contains(v) && !sets.get(1).contains(v)) {
					for (Vertex v2 : v.getAdjacentVertices()) {
						if (sets.get(set).contains(v2)) {
							bipartite = false;
							break;
						}
					}
					sets.get(set).add(v);
					getBipartiteSet(v, sets, opposite);
				}
			}
		}
		return vertices.size() > 0 ? bipartite : false;
	}
	private ArrayList<Vertex> DepthFirstSearch(Vertex start, ArrayList<Vertex> visited, String type) {
		ArrayList<Vertex> path = new ArrayList<>();
		path.add(start);
		visited.add(start);
		for (Vertex v : start.getAdjacentVertices()) {
			boolean unvisited = true;
			if (type.equals("None")) unvisited = !visited.contains(v);
			if (type.equals("Simple")) unvisited = findInArrayList(visited, v);
			if (unvisited) {
				ArrayList<Vertex> p2 = DepthFirstSearch(v, visited, type);
				for (Vertex vert : p2) {
					path.add(vert);
				}
			}
		}
		return path;
	}
	public ArrayList<Vertex> getShortestPath(Vertex v1, Vertex v2) {
		return DijkstrasAlgorithm(v1, v2);
	}
	public ArrayList<Vertex> getPath() {
		return DepthFirstSearch(getVertex(1), new ArrayList<Vertex>(), "None");
	}
	private ArrayList<Vertex> DijkstrasAlgorithm(Vertex start, Vertex goal) {
		ArrayList<Vertex> path = new ArrayList<>(); // Keep track of the path.
		ArrayList<Object[]> weights = new ArrayList<>(); // Keep a record of all weights
		weights.add(new Object[] {start, 0.0, null}); // Add the base weight.
		for (Vertex v : vertices) {
			if (v != start) {
				weights.add(new Object[] {v, Double.POSITIVE_INFINITY, null}); // Set all vertex's weight except the start to infinity.
			}
		}
		updateVertexWeights(start, weights, weights.get(0));
		Object[] goalData = null;
		Object[] lastVertData = null;
		path.add(goal);
		for (Object[] vertexData : weights) { // After all values have been set search through and find the goal node data.
			if (vertexData[0] == goal) {
				goalData = vertexData;
				lastVertData = (Object[]) goalData[2];
				break;
			}
		}
		for (int i = weights.size(); i > 0; i--) { // Do a backtrace to find the shortest path from the end to the beginning.
			Object[] data = lastVertData;
			if (data == null || data[0] == null) return path;
			path.add((Vertex) data[0]);
			if (data[0] == start) break;
			lastVertData = (Object[]) data[2];
		}
		Collections.reverse(path); // The path is running from the goal to the start, so reverse it.
		return path;
	}
	private void updateVertexWeights(Vertex start, ArrayList<Object[]> weights, Object[] startData) {
		for (Vertex v : start.getAdjacentVertices()) { // Search all vertices adjacent to the start.
			Object[] vertexData = null;
			int vertexDataIndex = -1; // For quick access to the vertex's data.
			Edge incidence = start.getIncidentEdge(v); // Get the edge incident to the start and the adjacent vertex.
			if (incidence != null) {
				double weight = incidence.getLength() + (double) startData[1]; // Set the vertex's weight. Include past weight.
				for (int i = 0; i < weights.size(); i++) { // Find the vertex in the weights array.
					Object[] node = weights.get(i);
					if (node[0] == v) {
						vertexData = node; // Found it save the data.
						vertexDataIndex = i;
						break;
					}
				}
				if (weight < (double) vertexData[1]) {
					weights.get(vertexDataIndex)[2] = startData;
					weights.get(vertexDataIndex)[1] = weight; // Update the vertex's weight.
					updateVertexWeights(v, weights, weights.get(vertexDataIndex));
				}
			}
		}
	}
	public boolean hasEulerCycle() {
		boolean isEulerian = true;
		for (Vertex v : vertices) {
			isEulerian = (v.getDegree()%2 == 0);
			if (!isEulerian) break;
		}
		return (isConnected() && isEulerian);
	}
	public ArrayList<Vertex> getEulerCycle() {
		ArrayList<Vertex> path = new ArrayList<>();
		if (hasEulerCycle()) {
			path.add(vertices.get(0));
			path = fleuryAlgorithm(path, (ArrayList<Vertex>) vertices.clone(), true);
			for (Edge e : edges) {
				e.setTraversable(true);
			}
		}
		return path;
	}
	private ArrayList<Vertex> fleuryAlgorithm(ArrayList<Vertex> path, ArrayList<Vertex> newVerts, boolean initial) {
		Vertex lastVert = path.get(path.size()-1);
		boolean removedVert = false;
		for (int i = 0; i < lastVert.getEdges().size(); i++) {
			Edge e = lastVert.getEdges().get(i);
			if (e.isTraversable()) {
				Vertex thisVert = e.getIncidentVertex(lastVert);
				e.setTraversable(false);
				if (lastVert.getTraversedEdges() == lastVert.getEdges().size()) {
					newVerts.remove(lastVert);
					removedVert = true;
				}
				ArrayList<ArrayList<Vertex>> components = getComponentSets(newVerts, new ArrayList<Vertex>());
				if (components.size() == 1) {
					if ((thisVert != path.get(0) || (thisVert == path.get(0) && thisVert.getDegree() > 1))) {
						path.add(thisVert);
						path = fleuryAlgorithm(path, newVerts, false);
						break;
					}
				} else {
					if (removedVert) {
						newVerts.add(lastVert);
					}
					e.setTraversable(true);
				}
			}
		}
		if (initial && path.size() > 1) {
			path.add(path.get(0));
		}
		return path;
	}
	public ArrayList<Vertex> getHamiltonianCycle() {
		hamCycle = new HamiltonianCycle();
		return hamCycle.getHamiltonianCycle();
	}
	public void cancelHamiltonSearch() {
		if (hamCycle != null) hamCycle.cancel = true;
	}
	public int getHamiltonCycleProgress() {
		return (hamCycle != null) ? hamCycle.progress : 0;
	}
	private class HamiltonianCycle {
		private int[][] graph;
		private int pathCount;	
		private ArrayList<Vertex> used = new ArrayList<>();
		private int[] path;
		protected int progress = 0;
		protected boolean cancel = false;
		public ArrayList<Vertex> getHamiltonianCycle() {
			if (vertices.size() < 1) return new ArrayList<Vertex>();
			path = new int[vertices.size()];
			Arrays.fill(path, -1);
			path[0] = 0;
			graph = getAdjacencyMatrix();
			pathCount = 1;
			progress = 0;
			cancel = false;
			try {
				solveHamiltonian(0);
				return new ArrayList<Vertex>();
			} catch (Exception e) {
				ArrayList<Vertex> vertexPath = new ArrayList<>();
				for (int i = 0; i <= vertices.size(); i++) {
					if (path.length < i % vertices.size()) break;
					vertexPath.add(vertices.get(path[i % vertices.size()]));
				}
				progress = 0;
				return vertexPath;
			}
		}
		private void solveHamiltonian(final int vertex) throws Exception {
			if (cancel) return;
			if (graph[vertex][0] == 1 && pathCount == vertices.size()) throw new Exception("Solution found");
			if (pathCount == vertices.size()) return;
			for (int v = 0; v < vertices.get(vertex).adjacentVertices.size(); v++) {
				int neighbor = ((vertices.get(vertex).adjacentVertices).get(v).getNumber());
				path[pathCount++] = neighbor;  
				
				graph[vertex][neighbor] = 0;
				graph[neighbor][vertex] = 0;
				boolean isPresent = false;
				for (int i = 0; i < pathCount - 1; i++) {
					if (path[i] == neighbor)  {
						isPresent = true;
						break;
					}
				}
				if (!isPresent) solveHamiltonian(neighbor);
				
				graph[vertex][neighbor] = 1;
				graph[neighbor][vertex] = 1;
				
				path[--pathCount] = -1;
				//if (!isPresent) break;
			}
			//progress = 100 - (100 / (vertices.size() / pathCount));
		}
	}
	public boolean findInArrayList(ArrayList<?> list, Object obj) {
		for (Object index : list) {
			if (index == obj) return true;
		}
		return false;
	}
}
