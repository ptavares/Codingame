import java.util.*;
import java.util.stream.IntStream;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        ArrayList<Vertex> nodes = new ArrayList<>();
        IntStream.range(0, N).forEach(value -> nodes.add(new Vertex(Integer.toString(value), Integer.toString(value))));

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            System.err.println("Edge " + i + " = " + N1 + " " + N2);
            edges.add(new Edge(N1 + "_" + N2, nodes.get(N1), nodes.get(N2), 0));
            edges.add(new Edge(N2 + "_" + N1, nodes.get(N2), nodes.get(N1), 0));

        }
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);

        List<Integer> outs = new LinkedList<>();
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            //map.get(EI).isOut = true;
            outs.add(EI);
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            System.err.println("From = " + SI);
            List<LinkedList<Vertex>> results = new ArrayList<>();
            for (Integer out : outs) {
                dijkstra.execute(nodes.get(SI));
                LinkedList<Vertex> result = dijkstra.getPath(nodes.get(out));
                System.err.println("result = " + result);
                if (result != null)
                    results.add(result);
            }
            int pathSize = Integer.MAX_VALUE;
            LinkedList<Vertex> resultToPrint = null;
            for (LinkedList<Vertex> result : results) {
                if (result.size() < pathSize) {
                    pathSize = result.size();
                    resultToPrint = result;
                }
            }
            System.out.println(resultToPrint.get(resultToPrint.size() - 2) + " " + resultToPrint.get(resultToPrint.size() - 1));
        }
    }

    public static class Vertex {
        final private String id;
        final private String name;


        public Vertex(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Edge {
        private final String id;
        private final Vertex source;
        private final Vertex destination;
        private final int weight;

        public Edge(String id, Vertex source, Vertex destination, int weight) {
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String getId() {
            return id;
        }

        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " " + destination;
        }
    }

    public static class Graph {
        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
        }

        public List<Vertex> getVertexes() {
            return vertexes;
        }

        public List<Edge> getEdges() {
            return edges;
        }


    }

    public static class DijkstraAlgorithm {

        private final List<Vertex> nodes;
        private final List<Edge> edges;
        private Set<Vertex> settledNodes;
        private Set<Vertex> unSettledNodes;
        private Map<Vertex, Vertex> predecessors;
        private Map<Vertex, Integer> distance;

        public DijkstraAlgorithm(Graph graph) {
            // create a copy of the array so that we can operate on this array
            this.nodes = new ArrayList<Vertex>(graph.getVertexes());
            this.edges = new ArrayList<Edge>(graph.getEdges());
        }

        public void execute(Vertex source) {
            settledNodes = new HashSet<Vertex>();
            unSettledNodes = new HashSet<Vertex>();
            distance = new HashMap<Vertex, Integer>();
            predecessors = new HashMap<Vertex, Vertex>();
            distance.put(source, 0);
            unSettledNodes.add(source);
            while (unSettledNodes.size() > 0) {
                Vertex node = getMinimum(unSettledNodes);
                settledNodes.add(node);
                unSettledNodes.remove(node);
                findMinimalDistances(node);
            }
        }

        private void findMinimalDistances(Vertex node) {
            List<Vertex> adjacentNodes = getNeighbors(node);
            for (Vertex target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node)
                        + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    predecessors.put(target, node);
                    unSettledNodes.add(target);
                }
            }

        }

        private int getDistance(Vertex node, Vertex target) {
            for (Edge edge : edges) {
                if (edge.getSource().equals(node)
                        && edge.getDestination().equals(target)) {
                    return edge.getWeight();
                }
            }
            throw new RuntimeException("Should not happen");
        }

        private List<Vertex> getNeighbors(Vertex node) {
            List<Vertex> neighbors = new ArrayList<Vertex>();
            for (Edge edge : edges) {
                if (edge.getSource().equals(node)
                        && !isSettled(edge.getDestination())) {
                    neighbors.add(edge.getDestination());
                }
            }
            return neighbors;
        }

        private Vertex getMinimum(Set<Vertex> vertexes) {
            Vertex minimum = null;
            for (Vertex vertex : vertexes) {
                if (minimum == null) {
                    minimum = vertex;
                } else {
                    if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                        minimum = vertex;
                    }
                }
            }
            return minimum;
        }

        private boolean isSettled(Vertex vertex) {
            return settledNodes.contains(vertex);
        }

        private int getShortestDistance(Vertex destination) {
            Integer d = distance.get(destination);
            if (d == null) {
                return Integer.MAX_VALUE;
            } else {
                return d;
            }
        }

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public LinkedList<Vertex> getPath(Vertex target) {
            LinkedList<Vertex> path = new LinkedList<Vertex>();
            Vertex step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            return path;
        }
    }
}