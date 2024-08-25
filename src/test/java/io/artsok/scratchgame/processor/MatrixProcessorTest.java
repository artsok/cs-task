package io.artsok.scratchgame.processor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import io.artsok.scratchgame.pojo.probabilities.BonusSymbols;
import io.artsok.scratchgame.pojo.probabilities.Probabilities;
import io.artsok.scratchgame.pojo.probabilities.StandardSymbol;
import io.artsok.scratchgame.pojo.probabilities.SymbolType;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

class MatrixProcessorTest {

  @Spy
  private ProbabilityProcessor probabilityProcessor;

  @InjectMocks
  private MatrixProcessor matrixProcessor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    matrixProcessor = new MatrixProcessor(probabilityProcessor);
  }

  @Test
  void shouldGenerateCorrect2DMatrix() {
    int rows = 2;
    int columns = 2;
    var probabilities = mockProbabilities();

    doReturn(new Point(1, 1)).when(probabilityProcessor).generateRandomPoint(anyInt(), anyInt());
    Pair<String[][], String> result = matrixProcessor.generated2DMatrix(rows, columns, probabilities);

    assertAll("Matrix values",
        () -> assertEquals("F", result.getKey()[0][0]),
        () -> assertEquals("A", result.getKey()[0][1]),
        () -> assertEquals("C", result.getKey()[1][0]),
        () -> assertEquals("10x", result.getKey()[1][1])
    );
  }

  private Probabilities mockProbabilities() {
    return Probabilities.builder()
        .bonusSymbols(
            BonusSymbols.builder().symbols(Map.of(SymbolType._10X, 2)).build()
        )
        .standardSymbols(List.of(
            StandardSymbol.builder()
                .row(0)
                .column(0)
                .symbols(
                    Map.of(
                        SymbolType.A, 1,
                        SymbolType.B, 2,
                        SymbolType.C, 3,
                        SymbolType.D, 4,
                        SymbolType.E, 5,
                        SymbolType.F, 6
                    ))
                .build(),
            StandardSymbol.builder()
                .row(0)
                .column(1)
                .symbols(
                    Map.of(
                        SymbolType.A, 6,
                        SymbolType.B, 5,
                        SymbolType.C, 4,
                        SymbolType.D, 3,
                        SymbolType.E, 2,
                        SymbolType.F, 1
                    ))
                .build(),
            StandardSymbol.builder()
                .row(1)
                .column(0)
                .symbols(
                    Map.of(
                        SymbolType.A, 7,
                        SymbolType.B, 8,
                        SymbolType.C, 10,
                        SymbolType.D, 1
                    ))
                .build(),
            StandardSymbol.builder()
                .row(1)
                .column(1)
                .symbols(
                    Map.of(
                        SymbolType.A, 7,
                        SymbolType.B, 101,
                        SymbolType.F, 100,
                        SymbolType.D, 1
                    ))
                .build())
        )
        .build();
  }
}
