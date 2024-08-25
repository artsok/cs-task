package io.artsok.scratchgame.pojo.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Impact {
  @JsonProperty("multiply_reward")
  MULTIPLY_REWARD,
  @JsonProperty("extra_bonus")
  EXTRA_BONUS,
  @JsonProperty("miss")
  MISS
}
