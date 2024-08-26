package io.artsok.scratchgame.strategy;

import java.util.List;
import java.util.Map;

public interface LinearSymbolsStrategy {

  Map<String, List<String>> process(final String[][] matrix2D,
      final List<List<String>> coveredAreas);

}
