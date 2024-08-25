package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StandardSymbol(
    int column,
    int row,
    Map<SymbolType, Integer> symbols
) {

}