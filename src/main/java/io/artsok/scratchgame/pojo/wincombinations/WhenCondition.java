package io.artsok.scratchgame.pojo.wincombinations;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WhenCondition {
  @JsonProperty("same_symbols") SAME_SYMBOLS, //if one symbol repeated in the matrix
  @JsonProperty("linear_symbols") LINEAR_SYMBOLS; //if it matches to probabilities.win_combinations.{X}.covered_areas
}
