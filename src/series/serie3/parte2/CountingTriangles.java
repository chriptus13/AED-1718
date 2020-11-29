package series.serie3.parte2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CountingTriangles {
    static HashMap<String, Vertex> vertexs = new HashMap<>();

    public static void main(String[] args) {
        String filename = args[0];
        load(filename);
        System.out.println(countTriangles());
    }

    private static int countTriangles() {
        int count = 0;
        for (Vertex v : vertexs.values())
            if (v.degree >= 2) {
                for (Edge e1 : v.adjSet)
                    if (e1.adj.id > v.id) {
                        boolean rightPos = false;
                        for (Edge e2 : v.adjSet) {
                            if (rightPos && e2.adj.id > v.id && e1.adj.adjSet.contains(e2)) count++;
                            if (e2.equals(e1)) rightPos = true;
                        }
                    }
            }
        return count;
    }

    private static void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                String str = br.readLine();
                String s1 = str.substring(0, str.indexOf(' '));
                String s2 = str.substring(str.indexOf(' ') + 1);
                Vertex a, b;
                if ((a = vertexs.get(s1)) == null) {
                    a = new Vertex(s1);
                    vertexs.put(s1, a);
                }
                if ((b = vertexs.get(s2)) == null) {
                    b = new Vertex(s2);
                    vertexs.put(s2, b);
                }
                a.adjSet.add(new Edge(b));
                a.degree++;
                b.adjSet.add(new Edge(a));
                b.degree++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
