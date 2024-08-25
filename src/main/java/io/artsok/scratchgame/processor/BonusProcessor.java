package io.artsok.scratchgame.processor;

import io.artsok.scratchgame.pojo.symbols.SymbolCategory;
import io.artsok.scratchgame.pojo.symbols.SymbolType;

/**
 * Applied bonus Symbol.
 */
public class BonusProcessor {

  public SymbolType process(String bonusSymbol) {
    final var symbolType = SymbolType.fromName(bonusSymbol);
    if (symbolType.getCategory() == SymbolCategory.BONUS && symbolType == SymbolType.MISS) {
      return null;
    }

    return symbolType;
  }

}
