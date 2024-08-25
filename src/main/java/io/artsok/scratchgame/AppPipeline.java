package io.artsok.scratchgame;

import io.artsok.scratchgame.pojo.config.ConfigFile;
import io.artsok.scratchgame.pojo.result.Result;
import io.artsok.scratchgame.pojo.symbols.SymbolType;
import io.artsok.scratchgame.processor.BonusProcessor;
import io.artsok.scratchgame.processor.MatrixProcessor;
import io.artsok.scratchgame.processor.RewardCalculationProcessor;
import io.artsok.scratchgame.processor.WinningCombinationsProcessor;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;


@RequiredArgsConstructor
public class AppPipeline {

  private final MatrixProcessor matrixProcessor;

  private final WinningCombinationsProcessor winningCombinationsProcessor;

  private final BonusProcessor bonusProcessor;

  private final RewardCalculationProcessor rewardCalculationProcessor;

  public Result process(final BigDecimal betAmount, final ConfigFile configFile) {
    final int rows = configFile.rows();
    final int columns = configFile.columns();
    final var probabilities = configFile.probabilities();
    final Pair<String[][], String> matrix2DWithBonusCell = matrixProcessor.generated2DMatrix(rows,
        columns, probabilities);

    final var winCombinations = configFile.winCombinations();
    final String[][] matrix2D = matrix2DWithBonusCell.getKey();
    final Map<String, List<String>> appliedWinningCombinations = winningCombinationsProcessor
        .process(matrix2D, winCombinations);
    final SymbolType bonusSymbol = bonusProcessor.process(matrix2DWithBonusCell.getValue());

    final BigDecimal reward = Optional.of(appliedWinningCombinations).filter(it -> !it.isEmpty())
        .map(it -> rewardCalculationProcessor.process(appliedWinningCombinations,
            bonusSymbol, configFile.symbols(), betAmount, winCombinations)).orElse(BigDecimal.ZERO);

    final List<List<String>> matrix = Arrays.stream(matrix2D)
        .map(Arrays::asList)
        .toList();

    return Result.builder()
        .matrix(matrix)
        .reward(reward.toString())
        .appliedWinningCombinations(appliedWinningCombinations)
        .appliedBonusSymbol(bonusSymbol.getName())
        .build();
  }

}
