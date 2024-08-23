// Name: Andy Nguyen
// Course: CS 2336.004 - Feng Ranran
// NETID: axn210059
// Starting date: 6 November 2023
import java.io.*;
import java.util.Map;
import java.util.HashMap;
// Vertex class
class Vertex {
  // member(s)
  private String vertex;
  // default constructor
  public Vertex(String vertex) { // create a new vertex
    this.vertex = vertex;
  }
  // accessor
  public String getVertex() {
    return vertex;
  }
  // mutator
  public void setVertex(String vertex) {
    this.vertex = vertex;
  }
}

// Edge class
// all edges will have weights
class Edge {
  // member(s)
  private Vertex origVertex, dstVertex;
  private int weight;
  // constructor
  public Edge(Vertex origVertex, Vertex dstVertex, int weight) { // create a new edge
    this.origVertex = origVertex;
    this.dstVertex = dstVertex;
    this.weight = weight;
  }
  // accessors and mutators
  public Vertex getOrigVertex() {
    return origVertex;
  }
  public void setOrigVertex(Vertex origVertex) {
    this.origVertex = origVertex;
  }
  public Vertex getDstVertex() {
    return dstVertex;
  }
  public void setDstVertex(Vertex dstVertex) {
    this.dstVertex = dstVertex;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }
}

// Graph class
// weighted & undirected
public class Graph {
  // member(s)
  private Map<String, Map<String, Integer>> adjList; // use hash map to store the adjacency list
  // default constructor
  public Graph() {
    adjList = new HashMap<>();
  }
  // function to insert new vertices
  public void insert_vertex(Vertex v) { // parameter: Vertex
    String vertex = v.getVertex(); // access the vertex in String format
    HashMap hm = new HashMap<>(); // create a new hash map
    if (!adjList.containsKey(vertex)) {
      adjList.put(vertex, hm); // insert new vertices to the adjacency list if it's absent
    }
  }
  // function to create new edges
  public void insert_edge(Edge e){
    // access the origin vertex (starting point)
    String origVertex = e.getOrigVertex().getVertex();
    // access the destination vertex (ending point)
    String dstVertex = e.getDstVertex().getVertex();
    // get the edge's weight
    int weight = e.getWeight();
    if (!adjList.containsKey(origVertex)) {
      insert_vertex(e.getOrigVertex()); // insert the origin vertex if it's absent
    }
    if (!adjList.containsKey(dstVertex)) {
      insert_vertex(e.getDstVertex()); // insert the destination vertex if it's absent
    }
    // connect the origin and destination vertices and assign the weight for their edges
    adjList.get(origVertex).put(dstVertex, weight);           
    adjList.get(dstVertex).put(origVertex, weight);
  }
  // function to get neighbour vertices
  public Map<String, Integer> get_neighbours(String vertex) {
    if (!adjList.containsKey(vertex)) {
      return null; // if the vertex is absent, return null
    }
    return adjList.get(vertex); // otherwise return the vertex
  }
}