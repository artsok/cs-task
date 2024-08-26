package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.exception.GenericException;
import io.artsok.scratchgame.pojo.symbols.Symbol;
import io.artsok.scratchgame.pojo.symbols.SymbolType;
import io.artsok.scratchgame.pojo.wincombinations.WinCombination;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Reward calculation.
 * Note (3): If one symbols matches more than winning winCombinations
 * then reward should be multiplied. formula:
 * (SYMBOL_1 * WIN_COMBINATION_1_FOR_SYMBOL_1 * WIN_COMBINATION_2_FOR_SYMBOL_1).
 * Note (4): If the more than one symbols matches any winning winCombinations
 * then reward should be summed. formula:
 * (SYMBOL_1 * WIN_COMBINATION_1_FOR_SYMBOL_1 * WIN_COMBINATION_2_FOR_SYMBOL_1)
 * + (SYMBOL_2 * WIN_COMBINATION_1_FOR_SYMBOL_2)
 */
public class RewardCalculationProcessor {

  public BigDecimal process(final Map<String, List<String>> appliedWinningCombinations,
      final SymbolType bonusSymbol, final Map<SymbolType, Symbol> symbols,
      final BigDecimal betAmount,
      final Map<String, WinCombination> allWinCombinations) {

    final BigDecimal totalSymbolsReward = appliedWinningCombinations.entrySet().stream()
        .map(entry -> {
          final var symbolName = entry.getKey();
          final List<String> listOfAppliedWinningCombinations = entry.getValue();

          //From symbols we should get object for symbolName that won
          final SymbolType symbol = SymbolType.fromName(symbolName);
          final BigDecimal symbolReward = BigDecimal.valueOf(symbols.get(symbol).rewardMultiplier());

          //For each win combination, we should map details
          final BigDecimal winCombinationReward = listOfAppliedWinningCombinations.stream()
              .map(combination -> {
                WinCombination winCombination = allWinCombinations.get(combination);
                return BigDecimal.valueOf(winCombination.rewardMultiplier());
              })
              .reduce(BigDecimal.ONE, BigDecimal::multiply);

          //total reward for one symbol
          return symbolReward.multiply(winCombinationReward).multiply(betAmount);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

    return addBonus(totalSymbolsReward, bonusSymbol, symbols);
  }

  private BigDecimal addBonus(final BigDecimal totalSymbolsReward, final SymbolType bonusSymbol,
      final Map<SymbolType, Symbol> symbols) {
    if (Objects.nonNull(bonusSymbol)) {
      final Symbol symbol = symbols.get(bonusSymbol);
      return switch (symbol.impact()) {
        case EXTRA_BONUS -> totalSymbolsReward.add(BigDecimal.valueOf(symbol.extra()));
        case MULTIPLY_REWARD ->
            totalSymbolsReward.multiply(BigDecimal.valueOf(symbol.rewardMultiplier()));
        default -> throw new GenericException("Unknown impact type: " + symbol.impact());
      };
    }

    return totalSymbolsReward;
  }


}
