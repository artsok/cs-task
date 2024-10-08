package io.artsok.scratchgame.pojo.wincombinations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public record WinCombination(
    @JsonProperty("reward_multiplier") double rewardMultiplier,
    @JsonProperty("when") WhenCondition whenCondition,
    Integer count,
    String group,
    @JsonProperty("covered_areas") List<List<String>> coveredAreas) {

}
