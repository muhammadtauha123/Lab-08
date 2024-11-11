/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;


public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // Additional test: ensure the result of empty() is a Graph type
    @Test
    public void testEmptyGraphType() {
        Graph<String> graph = Graph.empty();
        assertTrue("Graph.empty() should return an instance of Graph",
                graph instanceof Graph);
    }

    // Additional test: ensure that multiple calls to empty() return independent instances
    @Test
    public void testMultipleEmptyGraphs() {
        Graph<String> graph1 = Graph.empty();
        Graph<String> graph2 = Graph.empty();
        
        // Ensure that they are not the same instance
        assertNotSame("Each call to Graph.empty() should return a new instance", graph1, graph2);
        
        // Ensure both are still empty graphs
        assertTrue(graph1.vertices().isEmpty());
        assertTrue(graph2.vertices().isEmpty());
    }
}
