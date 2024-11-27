# Revenge of Tie Fighter Patrols (CS 2336 Fall 2023)

Analyse a galaxy map, then create a list of all pilots, with their routes' distances, and check the validity of their routes.
This project is created on November 2023.

Full scenario: Darth Vader wants to check that his TIE fighter pilots are patrolling adequately-sized regions of 
the galaxy. He has data files that contain the patrol coordinates for each of his pilots. With the fear of being force choked, write
a program that analyses the data and determines if the patrol path is valid.

Graph.java: 
* A galaxy map will be stored in a graph.
* Each patrol will have a patrol route associated with their name.
* Determine if the patrol route is valid based on the graph structure: Does the given path exist in the graph?
* Pilot names and planet names are singular words.
* The graph will be divided into two objects: edges and vertices.
* The graph allows new insertions.

Input:
* First, prompt the user for the file name containing the galaxy map.
* Next, prompt the user for the file name containing pilot routes.

Galaxy map file:
* Create the graph.
* Each line contains a vertex and a list of edges with weights.
* Each edge will be represented as <adjacent vertex>,<weight>
* Line example: "Hoth Endor,9 Coruscant,3 Tatooine,1 Dagobah,5"
* All vertices have at least one edge.
Pilot routes file:
* Each line in the file contains pilot name followed by list of planet routes.
* Each planet will be separated by a space: "Sully_Olvar Tatooine Dagobah Naboo Yavin Alderaan"
* The first vertex is the starting point.
* Each line represents a different pilot.
Output:
* <pilot name> \t <path weight> \t <valid/invalid>
* All input/outputs are performed with files.
