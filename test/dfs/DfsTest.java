package dfs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import graph.GraphImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This class is used to test the Dfs to find
 * whether it could find the  path to
 * a specific node or not.
 */
public class DfsTest {
  private Dfs dfs;
  private Graph graph;

  @Before
  public void setUp() throws Exception {
    graph = new GraphImpl(2, 4);
    graph.addEdge(1, 5);
    graph.addEdge(1, 2);
    graph.addEdge(2, 6);
    graph.addEdge(3, 6);
    graph.addEdge(3, 7);
    graph.addEdge(3, 4);
    graph.addEdge(4, 7);
    graph.addEdge(4, 8);
    graph.addEdge(6, 7);
    graph.addEdge(7, 8);
  }

  @Test
  public void hasPathTo() {
    dfs = new DfsImpl(graph, 2);
    assertTrue(dfs.hasPathTo(4));
  }

  @Test
  public void pathTo() {
    dfs = new DfsImpl(graph, 2);
    List<Integer> expectPath = new ArrayList<>();
    expectPath.add(6);
    expectPath.add(3);
    expectPath.add(7);
    expectPath.add(4);

    List<Integer> actualPath = new ArrayList<>();
    for (int w: dfs.pathTo(4)) {
      actualPath.add(w);
    }

    assertEquals(expectPath, actualPath);
  }

  @Test
  public void pathLen() {
    dfs = new DfsImpl(graph, 2);
    assertEquals(4, dfs.pathLen(4));
  }
}