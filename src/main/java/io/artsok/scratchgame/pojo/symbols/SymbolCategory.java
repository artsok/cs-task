package io.artsok.scratchgame.pojo.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SymbolCategory {
  @JsonProperty("standard")
  STANDARD,
  @JsonProperty("bonus")
  BONUS
}
