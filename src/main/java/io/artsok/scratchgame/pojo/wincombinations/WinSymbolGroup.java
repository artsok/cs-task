package io.artsok.scratchgame.pojo.wincombinations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WinGroup {
  @JsonProperty("standard")
  STANDARD,
  @JsonProperty("bonus")
  BONUS
}
