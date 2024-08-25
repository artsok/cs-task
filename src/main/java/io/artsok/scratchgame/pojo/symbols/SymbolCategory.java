package io.artsok.scratchgame.pojo.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SymbolCategory {
  @JsonProperty("standard")
  STANDARD,
  @JsonProperty("bonus")
  BONUS
}
