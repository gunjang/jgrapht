package org.jgrapht.generate;


import org.jgrapht.VertexFactory;
import org.jgrapht.WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleWeightedBipartiteGraphMatrixGenerator<V, E> extends WeightedGraphGeneratorAdapter<V, E, V> {

    List<V> first;

    List<V> second;

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public SimpleWeightedBipartiteGraphMatrixGenerator<V, E> first(List<? extends V> first) {
        this.first = new ArrayList<V>(first);
        return this;
    }

    public SimpleWeightedBipartiteGraphMatrixGenerator<V, E> second(List<? extends V> second) {
        this.second = new ArrayList<V>(second);
        return this;
    }

    @Override
    public void generateGraph(WeightedGraph<V, E> target, VertexFactory<V> vertexFactory, Map<String, V> resultMap) {

      if (weights == null)
        throw new IllegalArgumentException("Graph may not be constructed without weight-matrix specified");

      if (first == null || second == null)
        throw new IllegalArgumentException("Graph may not be constructed without either of vertex-set partitions specified");

      assert second.size() == weights.length;

      for (V vertex : first) {
        target.addVertex(vertex);
      }

      for (V vertex : second) {
        target.addVertex(vertex);
      }

      for (int i=0; i < first.size(); ++i) {

        assert first.size() == weights[i].length;

        for (int j=0; j < second.size(); ++j) {
          target.setEdgeWeight(
            target.addEdge(first.get(i), second.get(j)),
            weights[i][j]
          );
        }
      }

    }

}
