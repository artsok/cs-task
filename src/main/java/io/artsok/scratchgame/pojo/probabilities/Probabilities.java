package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public record Probabilities(
    @JsonProperty("standard_symbols") List<StandardSymbol> standardSymbols,
    @JsonProperty("bonus_symbols") BonusSymbols bonusSymbols
) {

}