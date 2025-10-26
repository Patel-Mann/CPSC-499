
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class jg {
    public static void main(String[] args) throws Exception {
        System.out.println("we are in");
        // --- JGRAPH TEST (Simplified and Corrected) ---
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        URI google = new URI("http://www.google.com");
        URI wikipedia = new URI("http://www.wikipedia.org");
        URI jgrapht = new URI("http://www.jgrapht.org");

        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);

        // Fixed the variable name from hrefGraph to g
        URI start = g.vertexSet().stream()
                .filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny()
                .get();

        System.out.println("\n--- JGraphT Sample Test Output ---");
        System.out.println("Found start URI: " + start);

    }

}