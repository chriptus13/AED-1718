package series.serie3.parte2;

public class Edge {
    public Vertex adj;

    public Edge(Vertex v) {
        adj = v;
    }

    @Override
    public int hashCode() {
        return adj.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Edge && ((Edge) obj).adj.value.equals(adj.value);
    }
}
