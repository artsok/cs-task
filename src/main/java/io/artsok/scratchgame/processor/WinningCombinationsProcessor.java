package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.pojo.wincombinations.WhenCondition;
import io.artsok.scratchgame.pojo.wincombinations.WinCombination;
import io.artsok.scratchgame.strategy.LinearSymbolsStrategy;
import io.artsok.scratchgame.strategy.VerticallyLinearSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * Check do we have winning winCombinations or not.
 */
@Slf4j
public class WinningCombinationsProcessor {

  public Map<String, List<String>> process(String[][] matrix2D,
      Map<String, WinCombination> winCombinations) {
    final Map<String, Integer> symbolsCount = countTheSymbolInMatrix(matrix2D);

    final Map<WhenCondition, Map<String, WinCombination>> groupedByAction = winCombinations.entrySet()
        .stream()
        .collect(Collectors.groupingBy(
            entry -> entry.getValue().whenCondition(),
            Collectors.toMap(Entry::getKey, Entry::getValue)
        ));

    final CompletableFuture<Map<String, List<String>>> sameSymbolsStrategy = CompletableFuture.supplyAsync(
        () -> {
          final Map<String, List<String>> result = new HashMap<>();
          groupedByAction.getOrDefault(WhenCondition.SAME_SYMBOLS, Map.of())
              .forEach((name, object) -> {
                final int sameSymbolsCount = object.count();
                symbolsCount.forEach((key, value) -> {
                  if (value == sameSymbolsCount) {
                    result.put(key, List.of(name));
                  }
                });
              });
          return result;
        });

    final CompletableFuture<Map<String, List<String>>> linearSymbolsStrategy = CompletableFuture.supplyAsync(
        () -> {
          final Map<String, List<String>> result = new HashMap<>();
          groupedByAction.getOrDefault(WhenCondition.LINEAR_SYMBOLS, Map.of())
              .forEach((name, object) -> {

                //TODO: implement logic
                if ("same_symbols_vertically".equalsIgnoreCase(name)) {
                  final LinearSymbolsStrategy strategy = new VerticallyLinearSymbols();
                  final Map<String, List<String>> process = strategy.process(matrix2D,
                      object.coveredAreas());

                }

              });
          return result;
        });

    final CompletableFuture<Map<String, List<String>>> combinedFuture = sameSymbolsStrategy.thenCombine(
        linearSymbolsStrategy,
        (sameSymbolsResult, linearSymbolsResult) -> {
          //merge two results
          sameSymbolsResult.forEach((key, value) ->
              linearSymbolsResult
                  .merge(key, value, (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).toList()));
          return linearSymbolsResult;
        });

    try {
      return combinedFuture.get().entrySet().stream()
          .filter(entry -> Objects.nonNull(entry.getKey()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread was interrupted: {}", e.getMessage());
      return new HashMap<>();
    } catch (ExecutionException e) {
      log.error("ExecutionException occurred: {}", e.getCause().getMessage());
      return new HashMap<>();
    }

  }

  private Map<String, Integer> countTheSymbolInMatrix(final String[][] matrix) {
    final Map<String, Integer> symbolCountMap = new HashMap<>();
    for (final String[] row : matrix) {
      for (final String symbol : row) {
        symbolCountMap.merge(symbol, 1, Integer::sum);
      }
    }
    return symbolCountMap;
  }
}
