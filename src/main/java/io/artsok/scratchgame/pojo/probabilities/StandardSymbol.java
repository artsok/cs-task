package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public record StandardSymbol(
    int column,
    int row,
    Map<SymbolType, Integer> symbols
) {

}