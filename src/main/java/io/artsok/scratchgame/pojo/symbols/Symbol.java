package io.artsok.scratchgame.pojo.symbols;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Wrapper class for the Symbol structure from the configuration class.
 *
 * @param type             - {@link SymbolCategory} - standard or bonus type.
 * @param rewardMultiplier - {@link Double} how much to multiply the reward by.
 * @param impact           - {@link Impact} actions of bonus type.
 * @param extra            - {@link Double} extra reward.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public record Symbol(
    SymbolCategory type,
    @JsonProperty("reward_multiplier") Double rewardMultiplier,
    Impact impact,
    Double extra) {

}

