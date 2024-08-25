package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.artsok.scratchgame.exception.SymbolNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SymbolType {
  A("A"),
  B("B"),
  C("C"),
  D("D"),
  E("E"),
  F("F"),
  _10X("10x"),
  _5X("5x"),
  PLUS_1000("+1000"),
  PLUS_500("+500"),
  MISS("MISS");

  private final String name;

  @JsonCreator
  public static SymbolType fromName(String name) {
    return java.util.Arrays.stream(values())
        .filter(type -> name.equalsIgnoreCase(type.name))
        .findFirst()
        .orElseThrow(() -> new SymbolNotFoundException("Unknown bonus symbol: " + name));
  }

}