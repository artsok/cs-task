package io.artsok.scratchgame.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.artsok.scratchgame.pojo.symbols.Impact;
import io.artsok.scratchgame.pojo.symbols.Symbol;
import io.artsok.scratchgame.pojo.symbols.SymbolCategory;
import io.artsok.scratchgame.pojo.symbols.SymbolType;
import io.artsok.scratchgame.pojo.wincombinations.WhenCondition;
import io.artsok.scratchgame.pojo.wincombinations.WinCombination;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class RewardCalculationProcessorTest {

  private final RewardCalculationProcessor rewardCalculationProcessor = new RewardCalculationProcessor();

  @Test
  void shouldCalculateRewardSameSymbolStrategyWithBonusMultiplier() {
    final Map<String, List<String>> appliedWinningCombinations = Map.of("B",
        List.of("same_symbol_3_times"));
    final SymbolType bonusSymbol = SymbolType._10X;
    final Map<SymbolType, Symbol> symbols = Map.of(SymbolType.B, Symbol
            .builder()
            .rewardMultiplier(3.0)
            .type(SymbolCategory.STANDARD)
            .build(),
        SymbolType._10X, Symbol
            .builder()
            .impact(Impact.MULTIPLY_REWARD)
            .rewardMultiplier(10.0)
            .type(SymbolCategory.BONUS)
            .build());
    final BigDecimal betAmount = new BigDecimal(100);
    final Map<String, WinCombination> allWinCombinations = Map.of("same_symbol_3_times",
        WinCombination.builder()
            .whenCondition(WhenCondition.SAME_SYMBOLS)
            .rewardMultiplier(1)
            .build());
    final BigDecimal result = rewardCalculationProcessor.process(appliedWinningCombinations,
        bonusSymbol, symbols, betAmount, allWinCombinations);

    assertEquals(3000, result.intValue());
  }

  @Test
  void shouldCalculateRewardSameSymbolStrategyWithExtraBonus() {
    final Map<String, List<String>> appliedWinningCombinations = Map.of("B",
        List.of("same_symbol_3_times"));

    final SymbolType bonusSymbol = SymbolType.PLUS_1000;

    final Map<SymbolType, Symbol> symbols = Map.of(SymbolType.B, Symbol
            .builder()
            .rewardMultiplier(3.0)
            .type(SymbolCategory.STANDARD)
            .build(),
        SymbolType.PLUS_1000, Symbol
            .builder()
            .impact(Impact.EXTRA_BONUS)
            .extra(1000.0)
            .type(SymbolCategory.BONUS)
            .build());

    final BigDecimal betAmount = new BigDecimal(100);

    final Map<String, WinCombination> allWinCombinations = Map.of("same_symbol_3_times",
        WinCombination.builder()
            .whenCondition(WhenCondition.SAME_SYMBOLS)
            .rewardMultiplier(1)
            .build());
    final BigDecimal result = rewardCalculationProcessor.process(appliedWinningCombinations,
        bonusSymbol, symbols, betAmount, allWinCombinations);

    assertEquals(1300, result.intValue());
  }


  @Test
  void shouldCalculateRewardWithBothStrategies() {
    final Map<String, List<String>> appliedWinningCombinations = Map.of(
        "A", List.of("same_symbol_5_times", "same_symbols_vertically"),
        "B", List.of("same_symbol_3_times", "same_symbols_vertically"));

    final SymbolType bonusSymbol = SymbolType.PLUS_1000;

    final Map<SymbolType, Symbol> symbols = Map.of(
        SymbolType.A, Symbol
            .builder()
            .rewardMultiplier(5.0)
            .type(SymbolCategory.STANDARD)
            .build(),
        SymbolType.B, Symbol
            .builder()
            .rewardMultiplier(3.0)
            .type(SymbolCategory.STANDARD)
            .build(),

        SymbolType.PLUS_1000, Symbol
            .builder()
            .impact(Impact.EXTRA_BONUS)
            .extra(1000.0)
            .type(SymbolCategory.BONUS)
            .build());

    final BigDecimal betAmount = new BigDecimal(100);

    final Map<String, WinCombination> allWinCombinations = Map.of(
        "same_symbol_3_times", WinCombination.builder()
            .whenCondition(WhenCondition.SAME_SYMBOLS)
            .rewardMultiplier(1)
            .build(),
        "same_symbol_5_times", WinCombination.builder()
            .whenCondition(WhenCondition.SAME_SYMBOLS)
            .rewardMultiplier(5)
            .build(),
        "same_symbols_vertically", WinCombination.builder()
            .whenCondition(WhenCondition.LINEAR_SYMBOLS)
            .rewardMultiplier(2)
            .build()
    );
    final BigDecimal result = rewardCalculationProcessor.process(appliedWinningCombinations,
        bonusSymbol, symbols, betAmount, allWinCombinations);

    assertEquals(6600, result.intValue());
  }

}