package graph;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vertex {
	protected Graph graph;
	protected ArrayList<Edge> edges = new ArrayList<Edge>();
	protected ArrayList<Vertex> adjacentVertices = new ArrayList<>();
	protected int x, y;
	public Vertex(int pX, int pY, Graph g) {
		x = pX;
		y = pY;
		graph = g;
	}
	public ArrayList<Vertex> getAdjacentVertices() {
		ArrayList<Vertex> adjacent = new ArrayList<>();
		for (int i = 0; i < adjacentVertices.size(); i++) {
			Vertex v = adjacentVertices.get(i);
			if (v.getIncidentEdge(this) != null) { 
				adjacent.add(v);
			}
		}
		return adjacent;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setPosition(int pX, int pY) {
		x = pX;
		y = pY;
	}
	public void setPosition(Point n) {
		setPosition((int) n.getX(), (int) n.getY());
	}
	public void delete() {
		for (Edge e : edges) {
			if (e.getVertex1() != this) {
				e.setVertex2(null);
			} else e.setVertex1(null);
		}
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void removeEdge(Edge edge) {
		if (edge.getVertex1() == this) {
			adjacentVertices.remove(edge.getVertex2());
		} else  {
			adjacentVertices.remove(edge.getVertex1());
		} 
		edges.remove(edge);
	}
	public void addEdge(Edge edge) {
		if (!findInArrayList(edges, edge)) {
			if (!findInArrayList(adjacentVertices, edge.getVertex1()) && !findInArrayList(adjacentVertices, edge.getVertex2()))
			if (edge.getVertex1() == this && edge.getVertex2() != null) {
				adjacentVertices.add(edge.getVertex2());
			} else if (edge.getVertex2() == this && edge.getVertex1() != null) {
				adjacentVertices.add(edge.getVertex1());
			} 
			edges.add(edge);
		}
	}
	public boolean isIsolated() {
		return edges.size() == 0;
	}
	public boolean isIncidentTo(Edge e) {
		boolean incidence = false;
		for (Edge edge : edges) {
			if (e == edge) {
				incidence = true;
				break;
			}
		}
		return incidence;
	}
	public int getDegree() {
		int degree = 0;
		for (Edge e : edges) {
			degree += (e.isTraversable() ? 1 : 0);
			if (e.isTraversable() && e.isLoop()) degree++;
		}
		return degree;
	}
	public int getTraversedEdges() {
		int num = 0;
		for (Edge e : edges) {
			num += (e.isTraversable() ? 0 : 1);
		}
		return num;
	}
	public boolean isAdjacentTo(Vertex v) {
		boolean adjacence = false;
		ArrayList<Vertex> adjacent = getAdjacentVertices();
		for (Vertex aV : adjacent) {
			if (aV == v) {
				adjacence = true;
				break;
			}
		}
		return adjacence;
	}
	public Edge getIncidentEdge(Vertex v2) {
		Edge edge = null;
		for (Edge e : edges) {
			if (e.isIncidentTo(this) && e.isIncidentTo(v2) && e.isTraversable()) {
				edge = e;
				break;
			}
		}
		return edge;
	}
	public ArrayList<Edge> getParallelEdges() {
		ArrayList<Edge> edges = new ArrayList<>();
		for (Edge e : edges) {
			if (e.isIncidentTo(e.getIncidentVertex(this))) {
				edges.add(e);
			}
		}
		return edges;
	}
	public String toString() {
		String name = "Vertex";
		int counter = 0;
		for (Vertex v : graph.getVertices()) {
			counter++;
			if (v == this) {
				name += counter;
				break;
			}
		}
		return name;
	}
	public int getNumber() {
		int counter = 0;
		for (Vertex v : graph.getVertices()) {
			if (v == this) {
				break;
			}
			counter++;
		}
		return counter;
	}
	public boolean findInArrayList(ArrayList<?> list, Object obj) {
		for (Object index : list) {
			if (index == obj) return true;
		}
		return false;
	}
}
