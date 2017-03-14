package graph;

import java.awt.Point;
import java.util.ArrayList;

public class Edge {
	protected Graph graph;
	private Vertex v1, v2;
	private Point curve = new Point();
	private double length;
	private boolean traversable = true;
	public Edge(Vertex pV1, Vertex pV2, Graph g) {
		v1 = pV1;
		v2 = pV2;
		v1.addEdge(this);
		v2.addEdge(this);
		graph = g;
	}
	public Point getCurve() {
		return curve;
	}
	public void setCurve(Point pCurve) {
		curve = pCurve;
	}
	public boolean isTraversable() {
		return traversable;
	}
	public void setTraversable(boolean pTraversable) {
		traversable = pTraversable;
	}
	public Vertex getVertex1() {
		return v1;
	}
	public void setVertex1(Vertex pV1) {
		v1 = pV1;
		v1.addEdge(this);
		setLength();
	}
	public Vertex getVertex2() {
		return v2;
	}
	public void setVertex2(Vertex pV2) {
		v2 = pV2;
		v2.addEdge(this);
		setLength();
	}
	public double getLength() {
		setLength();
		return length;
	}
	public Vertex getIncidentVertex(Vertex v) {
		for (Vertex v2 : v.getAdjacentVertices()) {
			if (v2.isIncidentTo(this)) {
				return v2;
			}
		}
		return null;
	}
	public void setLength() {
		if (v1 != null || v2 != null) { 
			int x1 = v1 != null ? v1.x : 0;
			int x2 = v2 != null ? v2.x : 0;
			int y1 = v1 != null ? v1.y : 0;
			int y2 = v2 != null ? v2.y : 0;
			length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		} else length = 0;
	}
	public boolean isIncidentTo(Vertex v) {
		return (v1 != null && v == v1) || (v2 != null && v == v2);
	}
	public boolean isLoop() {
		return (v1 == v2);
	}
	public boolean isParallelTo(Edge pE) {
		if (this == pE) return false;
		return (getVertex1() == pE.getVertex1() && getVertex2() == pE.getVertex2()) 
				|| (getVertex2() == pE.getVertex1() && getVertex1() == pE.getVertex2())
				|| (getVertex1() == pE.getVertex2() && getVertex2() == pE.getVertex1());
	}
	public ArrayList<Edge> getParallelEdges() {
		ArrayList<Edge> edges = new ArrayList<>();
		for (Edge e : v1.getEdges()) {
			if (e.isIncidentTo(v2)) {
				edges.add(e);
			}
		}
		return edges;
	}
	public void delete() {
		//v1.removeEdge(this);
		//v2.removeEdge(this);
		graph.removeEdge(this);
	}
	public String toString() {
		String name = "Edge";
		int counter = 0;
		for (Edge e : graph.getEdges()) {
			counter++;
			if (e == this) {
				name += counter;
			}
		}
		return name;
	}
}
