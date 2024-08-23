// Name: Andy Nguyen
// Course: CS 2336.004 - Feng Ranran
// NETID: axn210059
// Starting date: 6 November 2023
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
  // function to read the galaxy map file
  public static Graph docbandothienha(String fname) {
    try{
      Graph galaxy = new Graph();
      FileReader fr = new FileReader(fname);
      BufferedReader br = new BufferedReader(fr);
      String line;
      // read the file while the end is not reached
      while((line = br.readLine()) != null){
        String[] p = line.split(" "); // split the vertex with its weight with a space
        Vertex origVertex = new Vertex(p[0]); // create a new origin vertex
        galaxy.insert_vertex(origVertex); // insert origin vertex to the graph
        // iterate from 1 to the length of the line
        for(int i = 1; i < p.length; i++){
          String[] edge_part = p[i].split(","); // split the planets & their weights with a comma
          Vertex dstVertex = new Vertex(edge_part[0]); // create a new destination vertex (index 0)
          int weight = Integer.parseInt(edge_part[1]); // the weight is in the index 1 of the edge component (edge,weight)
          Edge edge = new Edge(origVertex, dstVertex, weight); // form a new edge
          galaxy.insert_edge(edge); // insert the edge to the graph
        }
      }
      br.close(); // close the file when done
      return galaxy; // return the whole graph 
    }
    catch(IOException e){
      // display the message to the console if error occurs
      System.out.println("The galaxy map file is unavailable or cannot be accessed");
      System.exit(0); // exit the program if error occurs
      return null; // return null
    }
    catch(ArrayIndexOutOfBoundsException e){
      // display the message to the console if file names got mixed up
      System.out.println("Unsuccessful analysis. Please make sure the name of both files are entered correctly.");
      System.exit(0); // exit the program if error occurs
      return null; // return null
    }
  }
  // function to analyse the pilot routes
  public static void phantichtuyenduong(String fname, Graph galaxy) {
    try{
      FileReader fr = new FileReader(fname);
      FileWriter fw = new FileWriter("patrols.txt");
      BufferedReader br = new BufferedReader(fr);
      BufferedWriter bw = new BufferedWriter(fw);
      String line;
      // store the pilots (in String format) in an array list
      ArrayList<String> pilots = new ArrayList<>();
      // iterate while the end of the file is not yet reached
      while((line = br.readLine()) != null){
        String[] index = line.split(" "); // split the pilots and each planet destination with a space
        String pilot_names = index[0]; // all pilot names are in index 0
        int path_weight = 0; // initialise the path weight to 0
        boolean validity = true; // set the validity boolean to true as default
        try{
          // iterate from 1 to the length of the line
          for(int i = 1; i < index.length-1; i++){
            Vertex origVertex = new Vertex(index[i]); // create a new origin vertex in index[i]
            Vertex dstVertex = new Vertex(index[i+1]); // create a new destination vertex in index[i+1]
            // get the vertices in String format
            String orig = origVertex.getVertex(); 
            String dst = dstVertex.getVertex();
            // get neighbour vertices of the origin vertex
            Map<String, Integer> neighbours = galaxy.get_neighbours(orig);
            if(neighbours.containsKey(dst)){
              path_weight += neighbours.get(dst); // sum all edge weights as the pilots move through each planet among the path
            }
            else{
              validity = false;
              path_weight = 0; // if the path is invalid, set the path weight to 0
              break; // break after the iterations
            }
          }
        }
        catch(NullPointerException npe){ // if the origin vertex is null/not found, the path is invalid
          validity = false;
          path_weight = 0;
        }
        String path_status;
        if(validity == true){
          path_status = "Valid";
        }
        else{
          path_status = "Invalid";
        }
        // add the results to the array list to be displayed
        pilots.add(pilot_names + "\t" + path_weight + "\t" + path_status);
      }

      // sort the array list by weight in ascending order
      Collections.sort(pilots, (x, y) -> {
          String[] pa = x.split("\t");
          String[] pb = y.split("\t");
          int w1 = Integer.parseInt(pa[1]);
          int w2 = Integer.parseInt(pb[1]);
          // compare 2 weights
          if (w1 != w2) {
            // if the weights are not equal, sort by weight in ascending order
              return w1 - w2;
          } 
          else {
            // if there is an equivalent in weight, sort the array list in alphabetical order of the pilot names
              return pa[0].compareTo(pb[0]);
          }
      });
      // display the result
      for(int i = 0; i<pilots.size(); i++){
        if(i == pilots.size()-1){
          bw.write(pilots.get(i));
          break; // the last line does not have the new line at the end
        }
        bw.write(pilots.get(i) + "\n");
      }
      // display this message to the console if the analysis is successful
      System.out.println("Analysis is successful. Please go to \"patrols.txt\" to see the result.");
      // close all files when done
      br.close();
      bw.close();
    }
    catch(IOException e){
      // display the error message to the console if error occurs
      System.out.println("The pilot routes file is unavailable or cannot be accessed.");
      return;
    }
  }
  // main
  public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      String gFile, pFile;
      // ask the user to enter the name of the galaxy map file
      System.out.println("Please enter the name of the file containing the galaxy map:");
      gFile = scan.nextLine();
      // ask the user to enter the name of the pilot routes file
      System.out.println("Please enter the name of the file containing the pilot routes:");
      pFile = scan.nextLine();
      // read the galaxy map file
      Graph galaxy = docbandothienha(gFile);
      // analyse the pilot routes
      phantichtuyenduong(pFile, galaxy);
  }
}