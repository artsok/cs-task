package io.artsok.scratchgame.pojo.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.artsok.scratchgame.pojo.probabilities.Probabilities;
import io.artsok.scratchgame.pojo.symbols.Symbol;
import io.artsok.scratchgame.pojo.symbols.SymbolType;
import io.artsok.scratchgame.pojo.wincombinations.WinCombination;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ConfigFile(
    int columns,
    int rows,
    Map<SymbolType, Symbol> symbols,
    Probabilities probabilities,
    @JsonProperty("win_combinations") Map<String, WinCombination> winCombinations
) {

}
