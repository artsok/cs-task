package io.artsok.scratchgame.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
   *
   * @param result - {@link Result}.
   * @return - {@link String}.
   */
  public String convertResultToJson(Result result) {
    try {
      CustomPrettyPrinter prettyPrinter = new CustomPrettyPrinter();
      prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
      objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      ObjectWriter writer = objectMapper.writer(prettyPrinter);

      return writer.writeValueAsString(result);
    } catch (JsonProcessingException e) {
      throw new GenericException("Failed to serialize JSON content", e);
    }
  }

  /**
   * Custom printer to adhere to the output format.
   */
  static class CustomPrettyPrinter extends DefaultPrettyPrinter {

    public CustomPrettyPrinter() {
      super();
      this._arrayIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
      this._objectFieldValueSeparatorWithSpaces = ": ";
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
      return new CustomPrettyPrinter();
    }

    @Override
    public void writeStartArray(JsonGenerator g) throws IOException {
      g.writeRaw('[');
      if (_nesting == 1) {
        g.writeRaw(System.lineSeparator());
      }
      _nesting++;
    }

    @Override
    public void writeEndArray(JsonGenerator g, int nrOfValues) throws IOException {
      _nesting--;
      if (_nesting == 1) {
        g.writeRaw(System.lineSeparator());
        g.writeRaw("  ]");
      } else {
        g.writeRaw(']');
      }
    }

    @Override
    public void beforeArrayValues(JsonGenerator g) throws IOException {
      if (_nesting == 2) {
        g.writeRaw("        ");
      }
    }

    @Override
    public void writeArrayValueSeparator(JsonGenerator g) throws IOException {
      g.writeRaw(", ");
      if (_nesting == 2) {
        g.writeRaw(System.lineSeparator());
        g.writeRaw("        ");
      }
    }
  }

}
