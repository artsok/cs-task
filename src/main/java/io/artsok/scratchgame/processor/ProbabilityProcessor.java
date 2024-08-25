package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.exception.GenericProcessorException;
import io.artsok.scratchgame.pojo.probabilities.SymbolType;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class ProbabilityProcessor {

  /**
   * Get symbol with MAX probability (to calculate to probability percentage just sum all symbols
   * probability numbers and divide individual symbol's probability number to total probability
   * numbers)
   *
   * @param symbols -{@link Map} of symbol and integer value.
   * @return - {@link String}
   */
  public String getMaxProbabilityValue(Map<SymbolType, Integer> symbols) {
    final int total = symbols.values().stream().mapToInt(Integer::intValue).sum();
    final Map<String, Double> probabilities = new HashMap<>();

    for (Map.Entry<SymbolType, Integer> entry : symbols.entrySet()) {
      final String symbol = entry.getKey().getName();
      final int probabilityNumber = entry.getValue();
      final double probabilityPercentage = (double) probabilityNumber / total * 100;
      probabilities.put(symbol, probabilityPercentage);
    }

    final Optional<Entry<String, Double>> result = probabilities.entrySet()
        .stream()
        .max(Map.Entry.comparingByValue());

    return result.map(Map.Entry::getKey).orElseThrow(() -> new GenericProcessorException("s"));
  }

  //Bonus symbol can be generated randomly in any cell(s) in the matrix


  /**
   * Generate random cell.
   *
   * @param n - int value.
   * @param m - int value.
   * @return - {@link Point}.
   */
  public Point generateRandomPoint(final int n, final int m) {
    if (n <= 0 || m <= 0) {
      throw new IllegalArgumentException("Array dimensions must be greater than 0");
    }

    final int randomRow = ThreadLocalRandom.current().nextInt(0, n);
    final int randomColumn = ThreadLocalRandom.current().nextInt(0, m);

    return new Point(randomRow, randomColumn);
  }

}
