package io.artsok.scratchgame.pojo.probabilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BonusSymbols {

  private Map<SymbolType, Integer> symbols;

}