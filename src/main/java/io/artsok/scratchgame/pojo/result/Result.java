package io.artsok.scratchgame.pojo.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
@JsonPropertyOrder({"matrix", "reward", "applied_winning_combinations", "applied_bonus_symbol"})
public record Result(
    List<List<String>> matrix,
    int reward, //final reward which user won
    @JsonProperty("applied_winning_combinations")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String, List<String>> appliedWinningCombinations,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("applied_bonus_symbol")
    String appliedBonusSymbol
) {

}