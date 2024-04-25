import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class WeightedEdge {
    int u, v, weight;

    public WeightedEdge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

class WeightedGraph {
    int V;
    List<WeightedEdge>[] adjList;

    public WeightedGraph(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjList[u].add(new WeightedEdge(u, v, weight));
        adjList[v].add(new WeightedEdge(v, u, weight)); // Undirected graph, add edge from v to u as well
    }

    public void printWeightedEdges() {
        for (int i = 0; i < V; i++) {
            for (WeightedEdge edge : adjList[i]) {
                System.out.println(edge.u + " " + edge.v + " " + edge.weight);
            }
        }
    }

    public WeightedGraph.MST getMinimumSpanningTree() {
        return new MST(this);
    }

    static class MST {
        WeightedGraph graph;
        List<WeightedEdge> edges;
        int totalWeight;

        public MST(WeightedGraph graph) {
            this.graph = graph;
            this.edges = new ArrayList<>();
            this.totalWeight = 0;
            findMinimumSpanningTree();
        }

        private void findMinimumSpanningTree() {
            boolean[] visited = new boolean[graph.V];
            PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            pq.addAll(graph.adjList[0]); // Start from vertex 0

            visited[0] = true;
            while (!pq.isEmpty()) {
                WeightedEdge minEdge = pq.poll();
                if (!visited[minEdge.v]) {
                    visited[minEdge.v] = true;
                    edges.add(minEdge);
                    totalWeight += minEdge.weight;
                    for (WeightedEdge edge : graph.adjList[minEdge.v]) {
                        if (!visited[edge.v]) {
                            pq.add(edge);
                        }
                    }
                }
            }
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public void printTree() {
            for (WeightedEdge edge : edges) {
                System.out.println(edge.u + " " + edge.v + " " + edge.weight);
            }
        }
    }
}
