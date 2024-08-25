package io.artsok.scratchgame.pojo.symbols;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.artsok.scratchgame.exception.SymbolNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SymbolType {
  A("A", SymbolCategory.STANDARD),
  B("B", SymbolCategory.STANDARD),
  C("C", SymbolCategory.STANDARD),
  D("D", SymbolCategory.STANDARD),
  E("E", SymbolCategory.STANDARD),
  F("F", SymbolCategory.STANDARD),
  _10X("10x", SymbolCategory.BONUS),
  _5X("5x", SymbolCategory.BONUS),
  PLUS_1000("+1000", SymbolCategory.BONUS),
  PLUS_500("+500", SymbolCategory.BONUS),
  MISS("MISS", SymbolCategory.BONUS);

  private final String name;
  private final SymbolCategory category;

  /**
   * Method to convert 10x,5x,+1000,+500 (workaround).
   *
   * @param name - {@link String}.
   * @return - {@link SymbolType}.
   */
  @JsonCreator
  public static SymbolType fromName(String name) {
    return java.util.Arrays.stream(values())
        .filter(type -> type.name.equalsIgnoreCase(name))
        .findFirst()
        .orElseThrow(() -> new SymbolNotFoundException("Unknown symbol: " + name));
  }
}