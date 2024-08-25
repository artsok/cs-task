package io.artsok.scratchgame.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.artsok.scratchgame.exception.JsonDeserializationException;
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
      throw new JsonDeserializationException("Failed to deserialize JSON content", e);
    }
  }
}
