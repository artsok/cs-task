package io.artsok.scratchgame.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.artsok.scratchgame.exception.GenericException;
import io.artsok.scratchgame.pojo.result.Result;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class JsonUtils {

  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Deserialize from string to object.
   *
   * @param content    - {@link String}.
   * @param targetType - {@link Class}
   * @param <T>        - any pojo class.
   * @return - Deserialized object.
   * @throws IOException - {@link IOException}.
   */
  public <T> T deserializeFromContent(String content, Class<T> targetType) {
    try {
      return objectMapper.readValue(content, targetType);
    } catch (JsonProcessingException e) {
      throw new GenericException("Failed to deserialize JSON content", e);
    }
  }

  /**
   * Serialize from object to string.
   * @param result - {@link Result}.
   * @return - {@link String}.
   */
  public String convertResultToJson(Result result) {
    try {
      ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
      return writer.writeValueAsString(result);
    } catch (JsonProcessingException e) {
      throw new GenericException("Failed to serialize JSON content", e);
    }
  }
}
