/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import java.util.Map;
import java.util.Set;

public class GraphInstanceTest {
    
    private Graph<String> graph;

    // Helper method to create a fresh empty graph instance
    private Graph<String> emptyInstance() {
        return Graph.empty();  // Assuming Graph.empty() gives us a fresh empty graph
    }

    // This method must be public for JUnit to recognize it as setup
    @Before
    public void setUp() {
        graph = emptyInstance();  // Initialize a fresh empty graph before each test
    }

    // Test for add() method
    @Test
    public void testAddVertex() {
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        graph.add("A");
        assertFalse(graph.add("A")); // Adding a duplicate should return false
    }

    // Test for set() method (adding edges)
    @Test
    public void testAddEdge() {
        graph.add("A");
        graph.add("B");
        int weight = graph.set("A", "B", 5);
        
        assertEquals(0, weight); // No previous edge, so it should return 0
        Map<String, Integer> targets = graph.targets("A");
        assertEquals(1, targets.size());
        assertTrue(targets.containsKey("B"));
    }

    @Test
    public void testUpdateEdgeWeight() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5); // First add an edge
        int prevWeight = graph.set("A", "B", 10); // Update the edge weight
        
        assertEquals(5, prevWeight); // Previous weight was 5
        Map<String, Integer> targets = graph.targets("A");
        assertEquals(1, targets.size());
        assertTrue(targets.containsKey("B"));
    }

    @Test
    public void testRemoveEdge() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        graph.set("A", "B", 0); // Removing the edge by setting its weight to 0
        
        Map<String, Integer> targets = graph.targets("A");
        assertFalse(targets.containsKey("B")); // The edge should no longer exist
    }

    // Test for remove() method (removing vertices)
    @Test
    public void testRemoveVertex() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
        
        Map<String, Integer> targets = graph.targets("B");
        assertTrue(targets.isEmpty()); // "B" should have no outgoing edges anymore
    }

    @Test
    public void testRemoveNonExistingVertex() {
        graph.add("A");
        assertFalse(graph.remove("B")); // "B" does not exist
    }

    // Test for vertices() method
    @Test
    public void testVertices() {
        graph.add("A");
        graph.add("B");
        
        Set<String> vertices = graph.vertices();
        assertTrue(vertices.contains("A"));
        assertTrue(vertices.contains("B"));
    }

    // Test for sources() method
    @Test
    public void testSources() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        Map<String, Integer> sources = graph.sources("B");
        assertEquals(1, sources.size());
        assertTrue(sources.containsKey("A"));
    }

    // Test for targets() method
    @Test
    public void testTargets() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        Map<String, Integer> targets = graph.targets("A");
        assertEquals(1, targets.size());
        assertTrue(targets.containsKey("B"));
    }
}