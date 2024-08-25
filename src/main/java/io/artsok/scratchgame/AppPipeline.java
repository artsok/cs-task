package io.artsok.scratchgame;

import io.artsok.scratchgame.pojo.config.ConfigFile;
import io.artsok.scratchgame.pojo.result.Result;
import io.artsok.scratchgame.processor.MatrixProcessor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class AppPipeline {

  private final MatrixProcessor matrixProcessor;


  public Result process(final ConfigFile configFile) {
    final int rows = configFile.rows();
    final int columns = configFile.columns();
    final var probabilities = configFile.probabilities();
    final String[][] matrix2D = matrixProcessor.generated2DMatrix(rows, columns, probabilities);
    return Result.builder().build();
  }

}
