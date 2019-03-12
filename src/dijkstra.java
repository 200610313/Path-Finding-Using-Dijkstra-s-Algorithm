/**
 * Implementation of Dijkstra's algorithm
 */
public class dijkstra {
    private int graphWithoutVehicle[][] = new int[][]
            {
                    {0, 55, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {55, 0, 78, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 78, 0, 108, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 108, 0, 99, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 99, 0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {27, 0, 0, 0, 0, 0, 57, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 28, 0, 0, 0, 57, 0, 78, 0, 0, 0, 54, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 30, 0, 0, 0, 78, 0, 107, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 30, 0, 0, 0, 107, 0, 99, 0, 0, 0, 67, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 27, 0, 0, 0, 99, 0, 0, 0, 0, 0, 71, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 0, 63, 0, 0, 0, 96, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 63, 0, 82, 0, 0, 0, 91, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 82, 0, 104, 0, 0, 0, 82, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 104, 0, 97, 0, 0, 0, 74, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 71, 0, 0, 0, 97, 0, 0, 0, 0, 0, 71},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 0, 0, 0, 0, 0, 68, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 91, 0, 0, 0, 68, 0, 88, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 82, 0, 0, 0, 88, 0, 96, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 74, 0, 0, 0, 96, 0, 96},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 71, 0, 0, 0, 96, 0}
            };

    private int graphWithVehicle[][] = new int[][]
            {
                    {0, 0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 78, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 108, 0, 0, 0, 0, 0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 57, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 28, 0, 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 30, 0, 0, 0, 0, 0, 107, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 99, 0, 0, 0, 67, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 63, 0, 0, 0, 96, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 0, 0, 82, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 104, 0, 0, 0, 82, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 0, 0, 0, 74, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 91, 0, 0, 0, 68, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 82, 0, 0, 0, 88, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 71, 0, 0, 0, 96, 0}
            };

    private String[] intersections =
            {
                    "Rizal-Neri",
                    "Tiano-Neri",
                    "Velez-Neri",
                    "Pabayo-Neri",
                    "Corrales-Neri",
                    "Rizal-Abejuela",
                    "Tiano-Abejuela",
                    "Velez-Abejuela",
                    "Pabayo-Abejuela",
                    "Corrales-Abejuela",
                    "Rizal-Chavez" ,
                    "Tiano-Chavez" ,
                    "Velez-Chavez" ,
                    "Pabayo-Chavez" ,
                    "Corrales-Chavez",
                    "Rizal-Hayes" ,
                    "Tiano-Hayes" ,
                    "Velez-Hayes" ,
                    "Pabayo-Hayes" ,
                    "Corrales-Hayes"
            };

    //variables for dijkstra's algorithm
    private int adjacencyMatrix[][];
    private int startVertex;
    private int vTotal;
    private int[] calculatedDistances;
    private int[] predecessors;
    private stack s = new stack();

    /**
     * Initializes the adjacency matrix to be selected, the source vertex, and the total number of nodes to be used as initial info for the dijkstra algorithm
     * @param m The adjacency matrix to be used
     * @param s The source node
     * @param t The total number of nodes
     */
    public dijkstra(int m, int s, int t) {
        //Decide which graph to choose
        if (m == 0)
            adjacencyMatrix = graphWithoutVehicle;
        else
            adjacencyMatrix = graphWithVehicle;

        //setting values
        startVertex = s;
        vTotal = t;
        calculatedDistances = new int[vTotal];

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[vTotal];
        // isVisited[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] isVisited = new boolean[vTotal];

        // Initialize all distances as
        // INFINITE and isVisited[] as false
        for (int vertexIndex = 0; vertexIndex < vTotal; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            isVisited[vertexIndex] = false;
    }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;
        int[] parents = new int[vTotal];
        parents[startVertex] = 0;
        // Parent array to store shortest
        // path tree

        // The starting vertex does not
        // have a parent


        // Find shortest path for all
        // vertices

        // Pick the minimum distance vertex
        // from the set of vertices not yet
        // processed.
        for (int i = 1; i < vTotal; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < vTotal;
                 vertexIndex++) {
                if (!isVisited[vertexIndex] &&
                    shortestDistances[vertexIndex] < shortestDistance) {

                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];//to accumulate
                }
            }

            // Mark the picked vertex as
            // processed
            isVisited[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < vTotal;
                 vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        calculatedDistances = shortestDistances;
        predecessors = parents;

    }

    /**
     * Returns the distance from source to node
     * @param d The vertex
     * @return Returns the distance from source to node
     */
    public int getDistance(int d) {
        return (calculatedDistances[d]);
    }

    /**
     *Retrieves the path from source to p
     *
     * @param p The destination vertex
     */
    public void getPath(int p)
    {
        int ctr = 0;
        s.push(p);
        ctr++;

        int lastVertexOnPath = p;
        while (lastVertexOnPath != startVertex){
            lastVertexOnPath = predecessors[lastVertexOnPath];
            s.push(lastVertexOnPath);
            ctr++;
        }
        int[] pathArray = new int[ctr];

        for (int i = 0; i<ctr; i++){
            pathArray[i] = s.peek();
            s.pop();
        }

        for (int i = 0; i<ctr; i++){
            if (i==ctr-1){
                System.out.print(" "+getIntersection(pathArray[i]));
            }
            else
                System.out.print(" "+getIntersection(pathArray[i])+" =>");
        }
        s.clear();
    }

    /**
     *Returns intersection name of vertex in
     *
     * @param in
     * @return Returns intersection name of vertex in
     */
    public String getIntersection(int in){
        return intersections[in];
    }
}
