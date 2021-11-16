package graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.LocationImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test whether the Graph can be
 * stored correctly.
 */
public class GraphTest {
  private Graph graph;

  @Before
  public void setUp() throws Exception {
    graph = new GraphImpl(3, 3);
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
  }

  @Test
  public void getVnum() {
    assertEquals(9, graph.getVnum());
  }

  @Test
  public void getRowNum() {
    assertEquals(3, graph.getRowNum());
  }

  @Test
  public void getColNum() {
    assertEquals(3, graph.getColNum());
  }

  @Test
  public void addEdge() {
    graph.addEdge(1, 4);
    List<Integer> firstAdj = new ArrayList<>();
    firstAdj.add(2);
    firstAdj.add(3);
    firstAdj.add(4);

    List<Integer> seconAdj = new ArrayList<>();
    seconAdj.add(1);

    assertEquals(firstAdj, graph.getAdjacentNodes(1));
    assertEquals(seconAdj, graph.getAdjacentNodes(2));
  }

  @Test
  public void getAdjacentNodes() {
    List<Integer> firstAdj = new ArrayList<>();
    firstAdj.add(2);
    firstAdj.add(3);

    assertEquals(firstAdj, graph.getAdjacentNodes(1));
  }

  @Test
  public void getAdjacentLocations() {
    LocationImpl locationImpl1 = new LocationImpl(1, 1);
    LocationImpl locationImpl2 = new LocationImpl(1, 2);
    LocationImpl locationImpl3 = new LocationImpl(1, 3);
    List<LocationImpl> locationImpls = new ArrayList<>();
    locationImpls.add(locationImpl2);
    locationImpls.add(locationImpl3);

    assertEquals(locationImpls, graph.getAdjacentLocations(locationImpl1));
  }

  @Test
  public void getDegree() {
    assertEquals(2, graph.getDegree(1));
  }

  @Test
  public void testToString() {
    assertEquals("GraphImpl{vertexNum=9, edgeNum=2}\n" +
            "adjacentList={1=[2, 3], 2=[1], 3=[1], 4=[], 5=[], " +
            "6=[], 7=[], 8=[], 9=[]}}", graph.toString());
  }

  @Test
  public void getLocation() {
    LocationImpl locationImpl1 = new LocationImpl(1, 1);
    assertEquals(locationImpl1, graph.getLocation(1));

    LocationImpl locationImpl3 = new LocationImpl(1, 3);
    assertEquals(locationImpl3, graph.getLocation(3));
  }
}