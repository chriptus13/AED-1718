package series.serie3.parte2;

import java.util.HashSet;

public class Vertex {
    static int numVertex;
    public String value;
    public int id, degree;
    public HashSet<Edge> adjSet;

    public Vertex(String s) {
        value = s;
        id = numVertex++;
        adjSet = new HashSet<>();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vertex && ((Vertex) obj).value.equals(value);
    }
}
