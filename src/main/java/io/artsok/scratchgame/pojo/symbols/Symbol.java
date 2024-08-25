package io.artsok.scratchgame.pojo.symbol;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper class for the Symbol structure from the configuration class.
 *
 * @param type             - {@link SymbolCategory} - standard or bonus type.
 * @param rewardMultiplier - {@link Double} how much to multiply the reward by.
 * @param impact           - {@link Impact} actions of bonus type.
 * @param extra            - {@link Double} extra reward.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Symbol(
    @JsonProperty("type") SymbolCategory type,
    @JsonProperty("reward_multiplier") Double rewardMultiplier,
    @JsonProperty("impact") Impact impact,
    @JsonProperty("extra") Double extra) {

}

