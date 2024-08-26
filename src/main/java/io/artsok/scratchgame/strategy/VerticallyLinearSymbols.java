package io.artsok.scratchgame.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerticallyLinearSymbols implements LinearSymbolsStrategy {


  //TODO: can be several different items in one column that are same.
  @Override
  public final Map<String, List<String>> process(final String[][] matrix2D,
      final List<List<String>> coveredAreas) {

    //Algorithm
    //1. Count number of all symbols in one column. Map<Symbol, Count> -> get all that more > 2.
    //2. Add finding symbols to result SET
    //3. Continue iteration (move to next column). repeat steps 1,2
    //4. Convert the result to {symbol:same_symbols_vertically}
    for (int i = 0; i < coveredAreas.size(); i++) {
      for (int j = 0; j < coveredAreas.get(i).size(); j++) {
        String s = coveredAreas.get(i).get(j);
      }
    }

    return new HashMap<>();
  }
}