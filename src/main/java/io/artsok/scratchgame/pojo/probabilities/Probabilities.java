package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Probabilities(
    @JsonProperty("standard_symbols") List<StandardSymbol> standardSymbols,
    @JsonProperty("bonus_symbols") BonusSymbols bonusSymbols
) {

}