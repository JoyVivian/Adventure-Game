package util;

import java.util.ArrayList;
import java.util.List;

import graph.Graph;

public class FindDanger {
  private final Graph graph;

  FindDanger(Graph graph) {
    this.graph = graph;
  }

  public List<Integer> findAdjacent(int distance, int node) {
    List<Integer> result = new ArrayList<>();
    dfs(2, node, -1, result);
    return result;
  }

  private void dfs(int distance, int node, int parent, List<Integer> findNodes) {
    if (distance == 0) {
      findNodes.add(node);
      return;
    }

    List<Integer> tmp = this.graph.getAdjacentNodes(node);

    for (int i : tmp) {
      if (i != parent) {
        dfs(distance - 1, i, node, findNodes);
      }
    }
  }
}
