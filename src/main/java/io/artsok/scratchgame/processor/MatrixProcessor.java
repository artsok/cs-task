package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.pojo.probabilities.Probabilities;
import io.artsok.scratchgame.pojo.probabilities.SymbolType;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Processor for generation 2D matrix.
 */
@RequiredArgsConstructor
public class MatrixProcessor {

  private final ProbabilityProcessor probabilityProcessor;

  public Pair<String[][], String> generated2DMatrix(final int rows, final int columns,
      final Probabilities probabilities) {

    final String[][] matrix = new String[rows][columns];

    probabilities.standardSymbols().forEach(it -> {
      final int row = it.row();
      final int column = it.column();
      final Map<SymbolType, Integer> symbols = it.symbols();
      matrix[row][column] = probabilityProcessor.getMaxProbabilityValue(symbols);
    });

    final var cell = probabilityProcessor.generateRandomPoint(rows, columns);
    final var bonusSymbol = probabilityProcessor.getMaxProbabilityValue(
        probabilities.bonusSymbols().getSymbols());

    matrix[cell.x][cell.y] = bonusSymbol;

    return Pair.of(matrix, bonusSymbol);
  }

}
