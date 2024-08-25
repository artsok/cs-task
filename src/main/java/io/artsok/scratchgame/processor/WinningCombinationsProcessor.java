package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.pojo.wincombinations.WhenCondition;
import io.artsok.scratchgame.pojo.wincombinations.WinCombination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

    final CompletableFuture<HashMap<String, List<String>>> sameSymbolsStrategy = CompletableFuture.supplyAsync(
        () -> {
          final HashMap<String, List<String>> result = new HashMap<>();
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

    final CompletableFuture<HashMap<String, List<String>>> linearSymbolsStrategy = CompletableFuture.supplyAsync(
        () -> {
          final HashMap<String, List<String>> result = new HashMap<>();
          groupedByAction.getOrDefault(WhenCondition.LINEAR_SYMBOLS, Map.of())
              .forEach((name, object) -> {
                //TODO
              });
          return result;
        });

    final CompletableFuture<HashMap<String, List<String>>> combinedFuture = sameSymbolsStrategy.thenCombine(
        linearSymbolsStrategy,
        (sameSymbolsResult, linearSymbolsResult) -> {
          sameSymbolsResult.forEach((key, value) ->
              linearSymbolsResult.merge(key, value,
                  (v1, v2) -> Stream.concat(v1.stream(), v2.stream())
                      .toList()));
          return linearSymbolsResult;
        });

    try {
      return combinedFuture.get();
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
