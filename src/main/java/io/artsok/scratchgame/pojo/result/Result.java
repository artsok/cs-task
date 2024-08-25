package io.artsok.scratchgame.pojo.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Builder;

@Builder
public record Result(
    List<List<String>> matrix,
    int reward, //final reward which user won
    @JsonProperty("applied_winning_combinations") Map<String, List<String>> appliedWinningCombinations,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("applied_bonus_symbol") String appliedBonusSymbol
) {

}