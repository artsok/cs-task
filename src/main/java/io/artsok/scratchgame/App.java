package io.artsok.scratchgame;

import io.artsok.scratchgame.pojo.config.ConfigFile;
import io.artsok.scratchgame.util.JsonUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Option;

/**
 * Hello world!
 */
@Slf4j
public class App implements Runnable {

  @Option(names = "--config", description = "Config file path", required = true, converter = FileToStringConverter.class)
  private String configContent;

  @Option(names = "--betting-amount", description = "Betting amount", required = true)
  private double bettingAmount;

  public static void main(String[] args) {
    log.info("Hello Team!");
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  /**
   * The business logic goes here.
   */
  @Override
  public void run() {
    if (configContent.isEmpty()) {
      log.error("Error: The config file does not exist or is not readable.");
      return;
    }

    log.info("Betting Amount: " + bettingAmount);

    ConfigFile configFile = JsonUtils.deserializeFromContent(configContent, ConfigFile.class);
    log.info("File context: {}", configFile);
  }

  /**
   * Custom converter from file to string.
   */
  static class FileToStringConverter implements CommandLine.ITypeConverter<String> {

    @Override
    public String convert(String filePath) throws IOException {
      Path path = new File(filePath).toPath();
      if (!Files.exists(path)) {
        throw new CommandLine.TypeConversionException("File does not exist: " + filePath);
      }

      String content = new String(Files.readAllBytes(path));
      if (content.trim().isEmpty()) {
        throw new CommandLine.TypeConversionException("File content is empty: " + filePath);
      }

      return content;
    }
  }
}
